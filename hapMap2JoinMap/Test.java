package hapMap2JoinMap;

public class Test {
	/*public static void main(String[] arguments){
		//String file = "/Users/yzheng/cugi/hapMapData2joinmap/HapMap-part.hmp.txt";
		String file = "/Users/yzheng/cugi/hapMapData2joinmap/HapMap_multiDad.hmp.txt";
		String outputfile1 = "/Users/yzheng/cugi/hapMapData2joinmap/HapMap-part.hmp.output.joinmap";
		String outputfile2 = "/Users/yzheng/cugi/hapMapData2joinmap/HapMap-part.hmp.output.badLoci";
		String outputfile3 = "/Users/yzheng/cugi/hapMapData2joinmap/HapMap-part.hmp.output.statisticFile";
		String momCol = "mom000_M00314_5_MyPlate1_B08_X5";
		String dadCol = "dad000_M00314_5_MyPlate1_B09_X5";
		HapMap2JoinMap A = new HapMap2JoinMap(file);
		A.findMOMDADData(momCol, dadCol);
		A.parentalGenotypeMiss();
		A.unmappable();
		A.mapping();
		A.selectColumnOfMapData();
		A.selectedMapConverttoJoinmap();
		A.printout(outputfile1, outputfile2,outputfile3);
	}*/
	
	public static void main(String[] args){
	    if (args.length != 11){
          System.out.println("Usage: /cugi/software/jdk1.7.0_25/bin/java -jar hapMap2JoinMap01.jar HapMap.hmp.txt "
          		+ "output.joinmap output.bad_loci output.stats momDataName "
          		+ "dadDataName ToleranceThreshold MarkerSimilarity outputThreshold.joinmap "
          		+ "outputMarkerSimilarity.joinmap outputChisq.joinmap");
          System.exit(1);
        }
		HapMap2JoinMap_v02 A = new HapMap2JoinMap_v02(args[0]);
		A.findMOMDADData(args[1], args[2]);
		A.parentalGenotypeMiss();
		A.unmappable();
		A.mapping();
		A.selectColumnOfMapData();
		/*switch(args[6]){
		case"ToleranceThreshHold":
			A.selectedMapConverttoJoinmap(Double.parseDouble(args[6]));
	    case"MarkerSimilarity":
	    	A.selectedMapConverttoJoinmap(Double.parseDouble(args[6]));
		}*/
		A.selectedMapConverttoJoinmap();
		A.moveOutParentProgenyUnmatch();
		A.markerGenotypeMissingFiler(Double.parseDouble(args[3]));
		A.markerSimilarityFilter(Double.parseDouble(args[4]));
		A.chSquFilter();
		A.printout(args[5], args[6], args[7], args[8], args[9], args[10]);
	}

}
