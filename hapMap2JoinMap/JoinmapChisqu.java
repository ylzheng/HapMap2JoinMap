/*@author: Yueli Zheng
 *Email: yzheng@g.clemson.edu
 *School of Computing, Clemson University
 *Copyright (c) 2013, Yueli Zheng. All rights reserved.<br> Yueli Zheng
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package hapMap2JoinMap;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JoinmapChisqu extends JoinmapMarker {
	private static Map<String, Double> genoTypeExpectedFreq = new HashMap<>();
	
	static{
		genoTypeExpectedFreq.put("ac", 0.25); 
		genoTypeExpectedFreq.put("ad", 0.25); 
		genoTypeExpectedFreq.put("bc", 0.25); 
		genoTypeExpectedFreq.put("bd", 0.25); 
		genoTypeExpectedFreq.put("ee", 0.25); 
		genoTypeExpectedFreq.put("eg", 0.25); 
		genoTypeExpectedFreq.put("fe", 0.25); 
		genoTypeExpectedFreq.put("fg", 0.25);
		genoTypeExpectedFreq.put("hh", 0.25); 
		genoTypeExpectedFreq.put("hk", 0.5); 
		genoTypeExpectedFreq.put("kk", 0.25); 
		genoTypeExpectedFreq.put("ll", 0.5); 
		genoTypeExpectedFreq.put("lm", 0.5); 
		genoTypeExpectedFreq.put("nn", 0.5);
		genoTypeExpectedFreq.put("np", 0.5); 
		
	}
	
	public static double joinmapCalChisqu(String goodLociThMS){
		String progenyMarkerline = goodLociThMS;
		getMarkerSimilarity(progenyMarkerline); //super
		double acChisqu = 0, adChisqu = 0, bcChisqu = 0, bdChisqu = 0, eeChisqu = 0, egChisqu = 0, feChisqu = 0, 
				fgChisqu = 0, hhChisqu = 0, hkChisqu = 0, kkChisqu = 0, llChisqu = 0, lmChisqu = 0, nnChisqu = 0, 
				npChisqu = 0;
		Map<String, Double> genoTypeObservedFreq = new HashMap<>();
		double oneMarkerLine = 0;
		double chiSqu = 0;
		for (Map.Entry<String, Double> entry : progenyFrequency.entrySet()){
			if (entry.getValue() != 0 && entry.getKey() != "__"){
				oneMarkerLine = oneMarkerLine + entry.getValue();
				genoTypeObservedFreq.put(entry.getKey(), entry.getValue());
			}
		} 
		for (Map.Entry<String, Double> entry :genoTypeObservedFreq.entrySet()){
			switch(entry.getKey()){
			//abxcd 
			case "ac"://1
				acChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("ac")), 2.0)/
				(oneMarkerLine*genoTypeExpectedFreq.get("ac"));
			    break;
			/*case "ca":
				acChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("ca")), 2.0)/
				oneMarkerLine*genoTypeExpectedFreq.get("ca");
				break;*/
			case "ad"://2
				adChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("ad")), 2.0)/
						(oneMarkerLine*genoTypeExpectedFreq.get("ad"));
			    break;
			/*case "da":
				adChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("da")), 2.0)/
				oneMarkerLine*genoTypeExpectedFreq.get("da");
				break;*/
			case "bc"://3
				bcChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("bc")), 2.0)/
						(oneMarkerLine*genoTypeExpectedFreq.get("bc"));
			    break;
			/*case "cb":
				bcChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("cb")), 2.0)/
						oneMarkerLine*genoTypeExpectedFreq.get("cb");
				break;*/
			case "bd"://4
				bdChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("bd")), 2.0)/
						(oneMarkerLine*genoTypeExpectedFreq.get("bd"));
			    break;
			/*case "db":
				bdChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("db")), 2.0)/
						oneMarkerLine*genoTypeExpectedFreq.get("db");
				break;*/
			//efxeg
			case "ee"://5
				eeChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("ee")), 2.0)/
						(oneMarkerLine*genoTypeExpectedFreq.get("ee"));
			    break;
			case "eg"://6
				egChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("eg")), 2.0)/
						(oneMarkerLine*genoTypeExpectedFreq.get("eg"));
				break;
			/*case "ge":
				egChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("ge")), 2.0)/
						oneMarkerLine*genoTypeExpectedFreq.get("ge");
			    break;*/
			case "fe"://7
				feChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("fe")), 2.0)/
						(oneMarkerLine*genoTypeExpectedFreq.get("fe"));
				break;
			/*case "ef":
				feChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("ef")), 2.0)/
						oneMarkerLine*genoTypeExpectedFreq.get("ef");
			    break;*/
			case "fg"://8
				fgChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("fg")), 2.0)/
						(oneMarkerLine*genoTypeExpectedFreq.get("fg"));
				break;
			/*case "gf":
				fgChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("gf")), 2.0)/
						oneMarkerLine*genoTypeExpectedFreq.get("gf");
			    break;*/
			//hkxhk
			case "hh"://9
				double tmp = entry.getValue();
				hhChisqu = Math.pow((tmp - oneMarkerLine*genoTypeExpectedFreq.get("hh")), 2.0)/
						(oneMarkerLine*genoTypeExpectedFreq.get("hh"));
			    break;
			case "hk"://10
				hkChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("hk")), 2.0)/
						(oneMarkerLine*genoTypeExpectedFreq.get("hk"));
				break;
			/*case "kh":
				hkChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("kh")), 2.0)/
						oneMarkerLine*genoTypeExpectedFreq.get("kh");
			    break;*/
			case "kk"://11
				kkChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("kk")), 2.0)/
						(oneMarkerLine*genoTypeExpectedFreq.get("kk"));
				break;
			//lmxll
			case "ll"://12
				llChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("ll")), 2.0)/
						(oneMarkerLine*genoTypeExpectedFreq.get("ll"));
			    break;
			/*case "ml":
				lmChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("ml")), 2.0)/
						oneMarkerLine*genoTypeExpectedFreq.get("ml");
				break;*/
			case "lm"://13
				lmChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("lm")), 2.0)/
						(oneMarkerLine*genoTypeExpectedFreq.get("lm"));
			    break;
			//nnxnp
			case "nn"://14
				nnChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("nn")), 2.0)/
						(oneMarkerLine*genoTypeExpectedFreq.get("nn"));
			    break;
			case "np"://15
				npChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("np")), 2.0)/
						(oneMarkerLine*genoTypeExpectedFreq.get("np"));
				break;
			/*case "pn":
				npChisqu = Math.pow((entry.getValue() - oneMarkerLine*genoTypeExpectedFreq.get("pn")), 2.0)/
						oneMarkerLine*genoTypeExpectedFreq.get("pn");
			    break;*/
			}
			
		}
		chiSqu = acChisqu + adChisqu + bcChisqu + bdChisqu + eeChisqu + egChisqu + feChisqu + 
				fgChisqu + hhChisqu + hkChisqu + kkChisqu + llChisqu + lmChisqu + nnChisqu + 
				npChisqu;
		return chiSqu;
	}
	

}
