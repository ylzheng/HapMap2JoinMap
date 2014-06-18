/*@author: Yueli Zheng
 *Email: yzheng@g.clemson.edu
 *School of Computing, Clemson University
 *Copyright (c) 2013, Yueli Zheng. All rights reserved.<br> Yueli Zheng
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package hapMap2JoinMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JoinmapTools {
	/*This method is used to filter the joinmap data with tolerance threshhold of missing data by marker.
	*/
	private static Map<String, Double> Df = new HashMap<>();
	private static Map<String, Double> Signif = new HashMap<>();
	private static Map<String, Double> x2_chsq = new HashMap<>();
	static{
		Df.put("abxcd", 3.0);
		Df.put("efxeg", 3.0);
		Df.put("hkxhk", 2.0);
		Df.put("lmxll", 1.0);
		Df.put("nnxnp", 1.0);
		Signif.put("signif", 0.0001);
		x2_chsq.put("abxcd", 21.0);
		x2_chsq.put("efxeg", 21.0);
		x2_chsq.put("hkxhk", 18.5);
		x2_chsq.put("lmxll", 15.0);
		x2_chsq.put("nnxnp", 15.0);
	}
	
	public static ArrayList<String> filterGoodLocibyTolerance(ArrayList<String> goodLoci, double threshHold){
		ArrayList<String> goodLociforJoinMap = goodLoci;
		ArrayList<String> goodLociThreshHold = new ArrayList<>();
		double tolerenceThreshHold = threshHold;
		double[] numberofGenotypeMissing = new double[goodLociforJoinMap.size()]; 
		double[] numberofGenotypeGood = new double[goodLociforJoinMap.size()];
		double[] tolerance = new double[goodLociforJoinMap.size()];
		for(int i = 0; i < goodLociforJoinMap.size(); i++){
			int firstTab = HapMap2JoinMap_v02.firstTabPosition(i, goodLociforJoinMap);
			int genotypeStartPos = firstTab + 9;
			for(int j = genotypeStartPos; j < goodLociforJoinMap.get(i).length(); j++){
				if(goodLociforJoinMap.get(i).charAt(j) == '_' && goodLociforJoinMap.get(i).charAt(j) != '\t'){
					numberofGenotypeMissing[i] = numberofGenotypeMissing[i] + 1;
				}else if(goodLociforJoinMap.get(i).charAt(j) != '_' && goodLociforJoinMap.get(i).charAt(j) != '\t'){
					numberofGenotypeGood[i]++;
				}
			}
		}
		for(int i = 0; i < goodLociforJoinMap.size(); i++){
			tolerance[i] = numberofGenotypeGood[i]/(numberofGenotypeMissing[i] + numberofGenotypeGood[i]);
			//System.out.println(tolerance[i] + "    xxxxxxxxxxxnumberofGenotypeGood[i]/(numberofGenotypeMissing[i] + numberofGenotypeGood[i])");
			if(tolerance[i] >= tolerenceThreshHold){
				//System.out.println("goodLociforJoinMap.get(i)   " + goodLociforJoinMap.get(i));
				goodLociThreshHold.add(goodLociforJoinMap.get(i));
			}
		}
		return goodLociThreshHold;
	}
	//This method is used to filter data with marker similarity w/in indir >= 95%
	public static ArrayList<String> filterByMarkerSimilarity(ArrayList<String> goodLociThreshHold, double markerSimThreshHold){
		ArrayList<String> goodLociTh = goodLociThreshHold;
		ArrayList<String> goodLociThMarkerSimilarity = new ArrayList<String>();
		double markerSimTh = markerSimThreshHold;
		String[] progenyGenotype = new String[goodLociTh.size()];
		double[] markerSimilarity = new double[goodLociTh.size()];   
		for (int i = 0; i < goodLociTh.size(); i++){
			int firstTab = HapMap2JoinMap_v02.firstTabPosition(i, goodLociTh);
			progenyGenotype[i] = goodLociTh.get(i).substring((firstTab+9),goodLociTh.get(i).length()); 
			markerSimilarity[i] = JoinmapMarker.getMarkerSimilarity(progenyGenotype[i]);
			//System.out.println("markerSimilarity[i]" + markerSimilarity[i]);
			if (markerSimilarity[i] <= markerSimTh){
				goodLociThMarkerSimilarity.add(goodLociTh.get(i));
			}
			/*switch(parentalGenotype){
			case "abxcd":
			case "efxeg":
			case "hkxhk":
			case "lmxll":
			case "nnxnp":
			}*/
		}
		return goodLociThMarkerSimilarity;
	}
	
	//This method is used to filter the failed chi sq(default significance level: 0.0001) 
	public static ArrayList<String> filterByChiSqDeviation(ArrayList<String> goodLoci_thresh_marSi){
		ArrayList<String> goodLociThresholdMarkerSi = goodLoci_thresh_marSi;
		ArrayList<String> goodLociThresholdMarkerSiChisq = new ArrayList<>();
		String[] progenyMarker = new String[goodLociThresholdMarkerSi.size()];
		double[] chiSq = new double[goodLociThresholdMarkerSi.size()];
		for (int i = 0; i < goodLociThresholdMarkerSi.size(); i++){
			int firstTab = HapMap2JoinMap_v02.firstTabPosition(i, goodLociThresholdMarkerSi);
			String parentalGenotype = goodLociThresholdMarkerSi.get(i).substring((firstTab+2), (firstTab+7));
			progenyMarker[i] = goodLociThresholdMarkerSi.get(i).substring((firstTab+9), goodLociThresholdMarkerSi.get(i).length()); 
			chiSq[i] = JoinmapChisqu.joinmapCalChisqu(progenyMarker[i]);
			if (chiSq[i] > x2_chsq.get(parentalGenotype)){
				goodLociThresholdMarkerSiChisq.add(goodLociThresholdMarkerSi.get(i));
			}
		}
		return goodLociThresholdMarkerSiChisq;
	}
}
