/*@author: Yueli Zheng
 *Email: yzheng@g.clemson.edu
 *School of Computing, Clemson University
 *Copyright (c) 2013, Yueli Zheng. All rights reserved.<br> Yueli Zheng
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package hapMap2JoinMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HapMap2JoinMap_v02 {
	// Map<String,List<String>> mapTest = new  HashMap<String,List<String>>(15);
	ArrayList<String> input = new ArrayList<>();
	ArrayList<String> momInput = new ArrayList<>();
	ArrayList<String> dadInput = new ArrayList<>();
	ArrayList<String> genoTypeMiss = new ArrayList<>();
	ArrayList<String> unmap = new ArrayList<>();
	ArrayList<String> map = new ArrayList<>();
	ArrayList<String> selectedDataofMap = new ArrayList<>();
	ArrayList<String> selectedDataofMapNoParent = new ArrayList<>();
	ArrayList<String> momDad = new ArrayList<>();// genotype of the valid data from mom and dad data  
	String outputFile1; //joinmap file
	String outputBadLoci; // badLoci file
	String statisticFile; /* include the statistic data such as # of loci examined, # of loci examined,  
	# of loci where both parents are homozygous, # of loci where parents do not match progeny genotypes, # of good loci
	Of the good loci: # with lmxll, # with nnxnp, # with efxeg, # with abxcd, # with hkxkh */
	String joinMapTheshold;
	String joinMapMarkerSimilarity;
	String joinmapChisqu;
	ArrayList<String> genotype = new ArrayList<>();
	ArrayList<String> dataJoinmap = new ArrayList<>();
	ArrayList<Integer> momPosition = new ArrayList<>();
	ArrayList<Integer> dadPosition = new ArrayList<>();
	ArrayList<Integer> momPosition_map = new ArrayList<>();
	ArrayList<Integer> dadPosition_map = new ArrayList<>();
	ArrayList<String> dataJoinmap_badLoci = new ArrayList<>();
	ArrayList<String> dataJoinmap_goodLoci = new ArrayList<>();
	ArrayList<String> dataJoinmap_goodLoci_threhhold = new ArrayList<>();
	ArrayList<String> dataJoinmapGoodLociThresholdMarkerSimilarity = new ArrayList<>();
	ArrayList<String> dataJoinmap_gpmissing_markerSi_Chisqu = new ArrayList<>();
	double genotymissing_threshold;
	double markerSimilarityThreshold;
	//double chisquThreshold;
	int countRecord;
	
	HapMap2JoinMap_v02(String filename){
		try{
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			String line = reader.readLine();
			//System.out.println("Input----" + line);
			while(line != null){
				input.add(line);
				line = reader.readLine();
				//System.out.println("Input----" + line);
				
			}
			reader.close();
			System.out.println("STEP1: Finish read in the input file!");
			countRecord = input.size() - 1 ;
			//System.out.println("Input----" + input);
		}
		catch (Exception e){
			System.out.println("Error" + e.getMessage());
		}
	}
	
	// find out the data of MOM and DAD 
	public void findMOMDADData(String momColName, String dadColName){
		System.out.println("STEP2: Finish find out the data for MOM and DAD!");	
    //public void findMOMDADData( ){
		// find out the column title of MOM and DAD 
		int colMom = 0;
		int colDad = 0;
		int momPos = 0;
		int dadPos = 0;
		int countDelimiter = 0;
		int lineLength1 = input.get(0).length();
		
		for (int i = 0; i < lineLength1; i++){
			if (input.get(0).charAt(i) == '\t'){
				countDelimiter++;
				//System.out.println("countDelimiter" + countDelimiter);
			}
			if ((i+momColName.length()) <= input.get(0).length() 
					&& input.get(0).substring(i,(i+momColName.length())).equals(momColName)){
			    colMom = countDelimiter;
			    //System.out.println("for MOM" + colMom);
			}
			if ((i+dadColName.length()) <= input.get(0).length() && input.get(0).
					substring(i,(i+dadColName.length())).equals(dadColName)){
		        colDad = countDelimiter;
		        //System.out.println("for Dad" + colDad);
		    }	
		}
		// find out the column of MOM and DAD. 
		for (int i = 1; i < input.size(); i++){
			int countMomDad = 0;
			int lineLength = input.get(i).length();
			//System.out.println("lineLength" + lineLength);
			//int j = 0; // char index in one line
			for (int j = 0; j < lineLength; j++){
				//System.out.println(j);
				if (input.get(i).charAt(j) == '\t'){
					countMomDad++;
					//System.out.println("countMomDad" + countMomDad);
				}
				if (countMomDad == colMom && (j + 1) < lineLength && input.get(i).charAt(j+1) != '\t'){
					//System.out.println("MOMCol -> " + input.get(i).charAt(j+1) + ";" + "->  i :j  " + i +":" +(j+1));
					momInput.add(input.get(i).substring(j+1,j+2)); 
					momPos = j+1;
					momPosition.add(momPos);
					//System.out.println("i = " + i + "  ---m" + momInput +"momPos -> " + momPos);
				}
				if (countMomDad == colDad && (j + 1) < lineLength && input.get(i).charAt(j+1) != '\t'){
					//System.out.println("DadCol -> " + input.get(i).charAt(j+1) + ";" + "->  i :j  " + i +":" +(j+1));
					dadInput.add(input.get(i).substring(j+1, j+2));
					dadPos = j+1;
					dadPosition.add(dadPos);
					//System.out.println("i = " + i + "  ---d" + dadInput +"dadPos -> " + dadPos);
				}
			}
		}
		
	}
	//if either parent is N --> Parental Genotype missing (I: BAD LOCI)
	public void parentalGenotypeMiss(){
		//System.out.println("....-> "+ momInput.size());
		//System.out.println(momInput.get(1).length());
		for (int i = 0; i < momInput.size(); i++){
			if (momInput.get(i).charAt(0) == 'N' || dadInput.get(i).charAt(0) == 'N'){
				genoTypeMiss.add(input.get(i+1));
			}
        
		}
		System.out.println("STEP3: Print out Genotype missing data!");
		for (int i = 0; i < genoTypeMiss.size(); i++){
		    //System.out.println(genoTypeMiss.get(i));
		}
	}

	//if both parents are homogeneous--> Unmappable (II: BAD LOCI)
	public void unmappable(){
		for (int i = 0; i < momInput.size(); i++){
			if (momInput.get(i).charAt(0) == 'A' || momInput.get(i).charAt(0) == 'C' || 
				 momInput.get(i).charAt(0) == 'T' || momInput.get(i).charAt(0) == 'G'){
				//System.out.println(momInput.get(i).charAt(0) + "  ->momInput.get(i)");
				if (dadInput.get(i).charAt(0) == 'A' || dadInput.get(i).charAt(0) == 'C' ||
					     dadInput.get(i).charAt(0) == 'T' || dadInput.get(i).charAt(0) == 'G'){
					//System.out.println(dadInput.get(i).charAt(0) + "  ->dadInput.get(i)");
					unmap.add(input.get(i+1));
				}
			}
		}
		System.out.println("STEP4: Print out unmappable data!");
		for (int i = 0; i < unmap.size(); i++){
		    //System.out.println(unmap.get(i));
		}
	}
	
	//other case --> PRINT both mom and dad column are non-N letters and not homogeneous
	public void mapping(){
		for (int i = 0; i < momInput.size(); i++){
			if (momInput.get(i).charAt(0) != 'N' && dadInput.get(i).charAt(0) != 'N'){
				if (momInput.get(i).charAt(0) == 'B' || momInput.get(i).charAt(0) == 'D' ||
					momInput.get(i).charAt(0) == 'H' || momInput.get(i).charAt(0) == 'V' ||
					dadInput.get(i).charAt(0) == 'B' || dadInput.get(i).charAt(0) == 'D' ||
					dadInput.get(i).charAt(0) == 'H' || dadInput.get(i).charAt(0) == 'V'){
					System.out.println("B H D V Weired sign for genotype!");
				}
				if (momInput.get(i).charAt(0) == 'R' || momInput.get(i).charAt(0) == 'W' ||
					momInput.get(i).charAt(0) == 'Y' || momInput.get(i).charAt(0) == 'K' ||
					momInput.get(i).charAt(0) == 'S' || momInput.get(i).charAt(0) == 'M' ||
					dadInput.get(i).charAt(0) == 'R' || dadInput.get(i).charAt(0) == 'W' ||
					dadInput.get(i).charAt(0) == 'Y' || dadInput.get(i).charAt(0) == 'K' ||
					dadInput.get(i).charAt(0) == 'S' || dadInput.get(i).charAt(0) == 'M'){
					map.add(input.get(i+1));
					momPosition_map.add(momPosition.get(i));
					dadPosition_map.add(dadPosition.get(i));
				}
			}
		}
		System.out.println("STEP5: print out map data!");
		for (int i = 0; i < map.size(); i++){
		    //System.out.println(map.get(i));
		}
	}
	
	public void selectColumnOfMapData(){
		System.out.println("STEP6: select the data columns from the mapping data. ");
		String tmp_mapNoParent = "";
		//according to the mom and dad date find out the corresponding genotype.
		for (int i = 0; i < momInput.size(); i++){
			String tmp_m = Character.toString(momInput.get(i).charAt(0));
			String tmp_d = Character.toString(dadInput.get(i).charAt(0));
			String tmp_md = tmp_m.concat(tmp_d);
			if (PossibleGenotype.isGenotype(tmp_md)){
				//System.out.println("momDad,tmp_md->" + tmp_md + " i ->" + i);
				momDad.add(tmp_md);
				int count = momDad.size();
				//System.out.println("momDad->" + momDad + "momDad.get(0)  " + momDad.get(count-1) + "  count -> " + count);
				genotype.add(PossibleGenotype.getGenotype(momDad.get(count-1)));
				//System.out.println("PossibleGenotype.getGenotype(momDad) ->" + genotype);
			}
		}
		//get rid of data of mom and dad 
		for (int i = 0; i < map.size(); i++){
            int firstTabPos = firstTabPosition(i,map);
            int QCPos = map.get(i).indexOf("QC+");
            int mapSeqStartPos = QCPos + 4;
            //System.out.println("mapSeqStartPos ->" + mapSeqStartPos);
			String tmp_map = map.get(i).substring(0, (firstTabPos+1)).
					         concat(genotype.get(i)).concat("\t").
					         concat(map.get(i).substring((QCPos+4), map.get(i).length()));
			selectedDataofMap.add(tmp_map);
			/*System.out.println("momPos -> " + momPosition_map.get(i) + " :: " + "dadPos -> " + dadPosition_map.get(i) 
					          + " -> map.get(i).length() -> " + map.get(i).length());*/
			//case1: M||D----------------------------------------------------------D||M
			if ((momPosition_map.get(i) == mapSeqStartPos || dadPosition_map.get(i) == mapSeqStartPos) &&
				(momPosition_map.get(i) == (map.get(i).length()-1) || dadPosition_map.get(i) == (map.get(i).length()-1))){
				//System.out.println("case1");
				tmp_mapNoParent = map.get(i).substring(0, (firstTabPos+1)).
		                          concat(genotype.get(i)).concat("\t").
		                          concat(map.get(i).substring((mapSeqStartPos+1), (map.get(i).length()-2)));
				//System.out.println("tmp_mapNoParent111 -> " + tmp_mapNoParent);
			}
			/*case2: M-----------D--------------------------------------------------
			         D-----------M--------------------------------------------------*/
			if ((momPosition_map.get(i) == mapSeqStartPos && dadPosition_map.get(i) < (map.get(i).length()-1)) ){
				//System.out.println("case2:1");
				tmp_mapNoParent = map.get(i).substring(0, (firstTabPos+1)).
                        concat(genotype.get(i)).concat("\t").
                        concat(map.get(i).substring((mapSeqStartPos+1), dadPosition_map.get(i))).
                        concat(map.get(i).substring((dadPosition_map.get(i)+1),(map.get(i).length()-1)));
			}
			if ((dadPosition_map.get(i) == mapSeqStartPos && momPosition_map.get(i) < (map.get(i).length()-1)) ){
				//System.out.println("case2:2");
				tmp_mapNoParent = map.get(i).substring(0, (firstTabPos+1)).
                        concat(genotype.get(i)).concat("\t").
                        concat(map.get(i).substring((mapSeqStartPos + 1), momPosition_map.get(i))).
                        concat(map.get(i).substring((momPosition_map.get(i)+1), (map.get(i).length()-1)));
			}
			/*case3: --------------------------------M------------------------------D
			 *       --------------------------------D------------------------------M
			 */
			if ((momPosition_map.get(i) > mapSeqStartPos && dadPosition_map.get(i) == (map.get(i).length()-1)) ){
				//System.out.println("case3:1");
                //System.out.println("map.get(i).++++++++++++" +  map.get(i).charAt((map.get(i).length()-1)));
				tmp_mapNoParent = map.get(i).substring(0, (firstTabPos+1)).
                        concat(genotype.get(i)).concat("\t").
                        concat(map.get(i).substring((mapSeqStartPos), momPosition_map.get(i))).
                        concat(map.get(i).substring((momPosition_map.get(i)+1),(map.get(i).length()-2)));
			}
			if ((dadPosition_map.get(i) > mapSeqStartPos && momPosition_map.get(i) == (map.get(i).length()-1)) ){
				//System.out.println("case3:2");
				tmp_mapNoParent = map.get(i).substring(0, (firstTabPos+1)).
                        concat(genotype.get(i)).concat("\t").
                        concat(map.get(i).substring((mapSeqStartPos), dadPosition_map.get(i))).
                        concat(map.get(i).substring((dadPosition_map.get(i)+1),(map.get(i).length()-1)));
			}
			/*case4: ----------------------M------------D----------------------------
			 *       ----------------------D------------M----------------------------
			 */
			if(dadPosition_map.get(i) > momPosition_map.get(i) && momPosition_map.get(i) > mapSeqStartPos && dadPosition_map.get(i) < (map.get(i).length()-1)){
				//System.out.println("case4:1");
			    tmp_mapNoParent = map.get(i).substring(0, (firstTabPos+1)).
		                          concat(genotype.get(i)).concat("\t").
		                          concat(map.get(i).substring(mapSeqStartPos, momPosition_map.get(i))).
		                          concat(map.get(i).substring(momPosition_map.get(i)+1,dadPosition_map.get(i))).
		                          concat(map.get(i).substring(dadPosition_map.get(i)+1,(map.get(i).length()-1)));
			}
			if(dadPosition_map.get(i) < momPosition_map.get(i) && dadPosition_map.get(i) > mapSeqStartPos && momPosition_map.get(i) < (map.get(i).length()-1)){
				//System.out.println("case4:2");
			    tmp_mapNoParent = map.get(i).substring(0, (firstTabPos+1)).
			    		          concat(genotype.get(i)).concat("\t").
                                  concat(map.get(i).substring(mapSeqStartPos, dadPosition_map.get(i))).
                                  concat(map.get(i).substring(dadPosition_map.get(i)+1,momPosition_map.get(i))).
                                  concat(map.get(i).substring(momPosition_map.get(i)+1,(map.get(i).length()-1)));
			}
			selectedDataofMapNoParent.add(tmp_mapNoParent);		
			//System.out.println("tmp_mapNoParent -> " + tmp_mapNoParent);
		}
	}
	
	//convert the mapping date into IUPAC code (includes 2 steps)
	public void selectedMapConverttoJoinmap(){
		System.out.println("STEP7: convert the IUPAC code into the corresponding JOINMAP codes!");
		for (int i = 0; i < selectedDataofMapNoParent.size(); i++){
			String tmp_str = "";
			int firstTabPos = firstTabPosition(i, map);
			int startPosIUPAC = firstTabPos + 9;
			String tmp_genotype = genotype.get(i).substring(1,6);
			//System.out.println("tmp_genotype   -> " + tmp_genotype);
			for (int j = startPosIUPAC; j < selectedDataofMapNoParent.get(i).length(); j++){
				//System.out.println("firstTabPos   "+firstTabPos + "::::"+ startPosIUPAC +"....."+ tmp_genotype+"...." + momDad.get(i) +"....." + selectedDataofMap.get(i).charAt(13));
				String tmp_letter = FindLetterTools.findGenotype(tmp_genotype, momDad.get(i), selectedDataofMapNoParent.get(i).charAt(j));
				//System.out.println("tmp_letter -> " + tmp_letter);
				tmp_str = tmp_str.concat(tmp_letter);
				//System.out.println("tmp_letter -> " + tmp_str);
			}
			String tmp_str2 = selectedDataofMapNoParent.get(i).substring(0,startPosIUPAC).concat(tmp_str);
            dataJoinmap.add(tmp_str2);
			//System.out.println("tmp_str -> " + tmp_str);
			//System.out.println("tmp_str2 -> " + tmp_str2);  
		}
		for (int i = 0; i < dataJoinmap.size(); i++){
			//System.out.println("dataJoinmap ++-> " + dataJoinmap.get(i));
		}
	}
		
	public void moveOutParentProgenyUnmatch(){
		//check if each record contains uppercase letter which means that parents in the record can not account for child genotypes
		System.out.println("STEP8: Seperate and move out the record whose parents cannot account for child genotype!");
		for (int i = 0; i < dataJoinmap.size(); i++ ){
			int firstTabPos = firstTabPosition(i, map);	
			boolean findUppercase = FindLetterTools.isUpperCase(dataJoinmap.get(i).substring(firstTabPos+7, dataJoinmap.get(i).length()));
			if (findUppercase){
				dataJoinmap_badLoci.add(dataJoinmap.get(i));
			}else{
				dataJoinmap_goodLoci.add(dataJoinmap.get(i));
			}
			
		}
		//System.out.println("dataJoinmap_goodLoci -> ");
		for (int i = 0; i < dataJoinmap_goodLoci.size(); i++){
		    //System.out.println(dataJoinmap_goodLoci.size() +"---" + dataJoinmap_goodLoci.get(i));
		}
		//System.out.println("dataJoinmap_badLoci -> ");
		for (int i = 0; i < dataJoinmap_badLoci.size(); i++){
			//System.out.println(dataJoinmap_badLoci.get(i));
		}
	}
	//implementation of the filtering the genotype missing according to the user specified threshHold. 
	public ArrayList<String> markerGenotypeMissingFiler(double userGPThreshold){
		genotymissing_threshold = userGPThreshold;
		//System.out.println("---------------+++++++++++++++++++++++ ");
		dataJoinmap_goodLoci_threhhold = JoinmapTools.filterGoodLocibyTolerance(dataJoinmap_goodLoci, genotymissing_threshold);
		//System.out.println("dataJoinmap_goodLoci_threhhold -> ");
		for (int i = 0; i < dataJoinmap_goodLoci_threhhold.size(); i++){
		    //System.out.println(dataJoinmap_goodLoci_threhhold.size() +"---" + dataJoinmap_goodLoci_threhhold.get(i));
		}
		return dataJoinmap_goodLoci_threhhold;
	}

	//implementation of the marker similarity less than the threshHold that user specify.
	public ArrayList<String> markerSimilarityFilter(double userMarSimiThreshold){
		markerSimilarityThreshold = userMarSimiThreshold;
		dataJoinmapGoodLociThresholdMarkerSimilarity = JoinmapTools.filterByMarkerSimilarity(dataJoinmap_goodLoci_threhhold, markerSimilarityThreshold);
		for(int i = 0; i < dataJoinmapGoodLociThresholdMarkerSimilarity.size(); i++){
			//System.out.println("markerSimilarityData==== " + dataJoinmapGoodLociThresholdMarkerSimilarity.get(i));
		}
		return dataJoinmapGoodLociThresholdMarkerSimilarity;
	}
	
		//implementation of failed chi squ with significance level 0.0001.
	public ArrayList<String> chSquFilter(){
		dataJoinmap_gpmissing_markerSi_Chisqu = JoinmapTools.filterByChiSqDeviation(dataJoinmapGoodLociThresholdMarkerSimilarity);
		for(int i = 0; i < dataJoinmap_gpmissing_markerSi_Chisqu.size(); i++){
			//System.out.println("chisqu==== " + dataJoinmap_gpmissing_markerSi_Chisqu.get(i));
		}
		return dataJoinmap_gpmissing_markerSi_Chisqu;
	}

	public Map<String, Integer> statisticCountF15TypeMarkers(ArrayList<String> joinmapDataSelected){
		ArrayList<String> joinmapData = joinmapDataSelected;
		int countAbxcd = 0,countEfxeg = 0, countHkxhk = 0, countLmxll = 0, countNnxnp = 0;
		Map<String, Integer> countLociNum = new HashMap<>();
		for (int i = 0; i < joinmapData.size(); i++){
			int firstTabPos = firstTabPosition(i, joinmapData);
			String genotype_tmp = joinmapData.get(i).substring(firstTabPos+2,firstTabPos+7);
			//System.out.println("i = " + i + "  joinmapDataSelected -> " + genotype_tmp + "   firstTabPos --- > " + firstTabPos);
			switch (genotype_tmp){ 
			    case "abxcd":
	         	  countAbxcd++;
	         	  break;
	            case "efxeg":
	         	  countEfxeg++;
	         	  break;
	            case "hkxhk":
	         	  countHkxhk++;
	         	  break;
	            case "lmxll":
	         	  countLmxll++;
	         	  break;
	            case "nnxnp":
	         	  countNnxnp++;
	         	  break;
		    }
		}
		/*System.out.println("# with abxcd: " + countAbxcd + " \n" + "# with efxeg: " + countEfxeg + " \n" +
				"# with hkxhk: " + countHkxhk + " \n" + "# with lmxll: " + countLmxll + " \n" + "# with nnxnp: "
				+ countNnxnp); */
		countLociNum.put("abxcd", countAbxcd);
		countLociNum.put("efxeg", countEfxeg);
		countLociNum.put("hkxhk", countHkxhk);
		countLociNum.put("lmxll", countLmxll);
		countLociNum.put("nnxnp", countNnxnp);
		return countLociNum;
	}
    
	// find first tab position to delete subString after first tab to the data progeny.
	public static int firstTabPosition(int number, ArrayList<String> datasetName){
		for(int i = number; i < datasetName.size(); i++){
			int count = 0;
			for (int j = 0; j < datasetName.get(i).length(); j++){
				if (datasetName.get(i).charAt(j) == '\t'){
					count++;
					if (count == 1){
					   //System.out.println("first tab:  " + j);
					   return j;
					}
				}
			}
			
		}
		System.out.println("Can not find match tab");
		return 0;
		
	}
	// output of joinmap format datafile.
	public void printout(String joinMapFileName, String badLociFile, String statisticFileName, String joinMapFileNameThreshold, String joinMapFileNameMarkerSimilarity, 
			String joinMapFileNameChisq){
		// output good loci data
		outputFile1 = joinMapFileName;
		PrintWriter outputStream1 = null;
		try {
			outputStream1 = new PrintWriter(outputFile1);
		}catch(FileNotFoundException e){
			System.out.println("error opening the file" + outputFile1);
			System.exit(0);
		}
		outputStream1.println("name = output.joinmap");
		outputStream1.println("popt = CP");
		outputStream1.println("nloc = " + countRecord);// output total number of loci examined
		outputStream1.println("nind = ???");
		for (int count = 0; count < dataJoinmap_goodLoci.size(); count++){
			outputStream1.println(dataJoinmap_goodLoci.get(count));
		}
		outputStream1.close();
		// output bad loci file
		outputBadLoci = badLociFile;
		PrintWriter outputStream2 = null;
		try {
			outputStream2 = new PrintWriter(outputBadLoci);
		}catch(FileNotFoundException e){
			System.out.println("error opening the file" + outputBadLoci);
			System.exit(0);
		}
		outputStream2.println("This file output the bad loci. The bad loci is composed of three types" + "\n"
				+ "1. Data where either parent is \"N\"" + "\n"
				+ "2. The loci where parents are both homozygous" + "\n"
				+ "3. Parents cannot account for child genotypes" + "\n");
		outputStream2.println("Data where either parent is \"N\"");
		for (int i = 0; i < genoTypeMiss.size(); i++){
			outputStream2.println(genoTypeMiss.get(i));
		}
		outputStream2.println("The loci where parents are both homozygous");
		for (int i = 0; i < unmap.size(); i++){
		    outputStream2.println(unmap.get(i));
		}
		outputStream2.println("Parents cannot account for child genotypes");
		for (int i = 0; i < dataJoinmap_badLoci.size(); i++){
		    outputStream2.println(dataJoinmap_badLoci.get(i));
		}
		outputStream2.close();
		
		// output statistic file
		statisticFile = statisticFileName;
		PrintWriter outputStream3 = null;
		try {
			outputStream3 = new PrintWriter(statisticFile);
		}catch(FileNotFoundException e){
			System.out.println("error opening the file" + statisticFile);
			System.exit(0);
		}
		int numberHomozygous = unmap.size();
		int numberParentsIsN = genoTypeMiss.size();
		int numberdUnmatchProgeny = dataJoinmap_badLoci.size();
		int numberGoodLoci = dataJoinmap_goodLoci.size();
		int numberGoodLoci_Threshold = dataJoinmap_goodLoci_threhhold.size();
		int numberGoodLoci_MarkerSimilarity = dataJoinmapGoodLociThresholdMarkerSimilarity.size();
		int numberGoodLoci_Chisq = dataJoinmap_gpmissing_markerSi_Chisqu.size();
		
		Map<String, Integer> countGoodLoci = statisticCountF15TypeMarkers(dataJoinmap_goodLoci);
		Map<String, Integer> countGoodLociThreshold = statisticCountF15TypeMarkers(dataJoinmap_goodLoci_threhhold);
		Map<String, Integer> countGoodLociMarkerSimilarity = 
				statisticCountF15TypeMarkers(dataJoinmapGoodLociThresholdMarkerSimilarity);
		Map<String, Integer> countGoodLociChisq = statisticCountF15TypeMarkers(dataJoinmap_gpmissing_markerSi_Chisqu);
		outputStream3.println("Total # of loci examined: " + countRecord);// output total number of loci examined
		outputStream3.println("the bad loci data includes " + "\n"
				+ " 1. # of the loci where either the parents is \"N\": " + numberParentsIsN + "\n" 
				+ " 2. # of the loci where parents are both homozygous: " + numberHomozygous + "\n" 
				+ " 3. # of the loci where parents cannot account for child genotypes: " + numberdUnmatchProgeny);
		//goodLoci
		outputStream3.println("# of good loci with " + ": " + numberGoodLoci);
		outputStream3.println( "# with abxcd: " + countGoodLoci.get("abxcd") + " \n" 
		                     + "# with efxeg: " + countGoodLoci.get("efxeg") + " \n" 
				             + "# with hkxhk: " + countGoodLoci.get("hkxhk") + " \n" 
		                     + "# with lmxll: " + countGoodLoci.get("lmxll") + " \n" 
				             + "# with nnxnp: " + countGoodLoci.get("nnxnp"));  
		//goodLociThreshold
		outputStream3.println("# of good loci with threshold " + genotymissing_threshold + ": " + numberGoodLoci_Threshold);
		outputStream3.println( "# with abxcd: " + countGoodLociThreshold.get("abxcd") + " \n" 
		                     + "# with efxeg: " + countGoodLociThreshold.get("efxeg") + " \n" 
				             + "# with hkxhk: " + countGoodLociThreshold.get("hkxhk") + " \n" 
		                     + "# with lmxll: " + countGoodLociThreshold.get("lmxll") + " \n" 
				             + "# with nnxnp: " + countGoodLociThreshold.get("nnxnp"));  
		//goodLociMarkerSimilarity
		outputStream3.println("# of good loci with threshold " +  genotymissing_threshold + " and " 
		                     + "marker Similarity " + markerSimilarityThreshold 
		                     + ": " + numberGoodLoci_MarkerSimilarity);
		outputStream3.println( "# with abxcd: " + countGoodLociMarkerSimilarity.get("abxcd") + " \n" 
		                     + "# with efxeg: " + countGoodLociMarkerSimilarity.get("efxeg") + " \n" 
				             + "# with hkxhk: " + countGoodLociMarkerSimilarity.get("hkxhk") + " \n" 
		                     + "# with lmxll: " + countGoodLociMarkerSimilarity.get("lmxll") + " \n" 
				             + "# with nnxnp: " + countGoodLociMarkerSimilarity.get("nnxnp"));  
		//goodLociChisq
		outputStream3.println("# of good loci with threshold " +  genotymissing_threshold + " marker Similarity " + markerSimilarityThreshold 
                             + " and default Chi-sq significance degree(0.0001) " + ": " + numberGoodLoci_Chisq);
		outputStream3.println( "# with abxcd: " + countGoodLociChisq.get("abxcd") + " \n" 
		                     + "# with efxeg: " + countGoodLociChisq.get("efxeg") + " \n" 
				             + "# with hkxhk: " + countGoodLociChisq.get("hkxhk") + " \n" 
		                     + "# with lmxll: " + countGoodLociChisq.get("lmxll") + " \n" 
				             + "# with nnxnp: " + countGoodLociChisq.get("nnxnp"));  
		outputStream3.close();			
		//output joinmap data with theshold specified ---joinMapFileNameThreshold.{joinMapMarkerSimilarity;String joinmapChisqu;}
		joinMapTheshold = joinMapFileNameThreshold;
		PrintWriter outputStream4 = null;
		try {
			outputStream4 = new PrintWriter(joinMapTheshold);
		}catch(FileNotFoundException e){
			System.out.println("error opening the file" + joinMapTheshold);
			System.exit(0);
		}
		outputStream4.println("name = output_ToleranceThreshold.joinmap");
		outputStream4.println("popt = CP");
		outputStream4.println("nloc = " + countRecord);// output total number of loci examined
		outputStream4.println("nind = ???");
		for (int count = 0; count < dataJoinmap_goodLoci_threhhold.size(); count++){
			outputStream4.println(dataJoinmap_goodLoci_threhhold.get(count));
		}
		outputStream4.close();
		//output joinmap data with marker similarity specified
		joinMapMarkerSimilarity = joinMapFileNameMarkerSimilarity;
		PrintWriter outputStream5 = null;
		try {
			outputStream5 = new PrintWriter(joinMapMarkerSimilarity);
		}catch(FileNotFoundException e){
			System.out.println("error opening the file" + joinMapMarkerSimilarity);
			System.exit(0);
		}
		outputStream5.println("name = output_MarkerSimilarity.joinmap");
		outputStream5.println("popt = CP");
		outputStream5.println("nloc = " + countRecord);// output total number of loci examined
		outputStream5.println("nind = ???");
		for (int count = 0; count < dataJoinmapGoodLociThresholdMarkerSimilarity.size(); count++){
			outputStream5.println(dataJoinmapGoodLociThresholdMarkerSimilarity.get(count));
		}
		outputStream5.close();
		// output joinmap data with chisqu specified
		joinmapChisqu = joinMapFileNameChisq;
		PrintWriter outputStream6 = null;
		try {
			outputStream6 = new PrintWriter(joinmapChisqu);
		}catch(FileNotFoundException e){
			System.out.println("error opening the file" + joinmapChisqu);
			System.exit(0);
		}
		outputStream6.println("name = output_Chisq.joinmap");
		outputStream6.println("popt = CP");
		outputStream6.println("nloc = " + countRecord);// output total number of loci examined
		outputStream6.println("nind = ???");
		for (int count = 0; count < dataJoinmap_gpmissing_markerSi_Chisqu.size(); count++){
			outputStream6.println(dataJoinmap_gpmissing_markerSi_Chisqu.get(count));
		}
		outputStream6.close();	      
	}		
}
