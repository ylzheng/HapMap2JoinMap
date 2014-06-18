package hapMap2JoinMap;

import java.util.HashMap;
import java.util.Map;

public class Lmxll {
    private static Map<String, String> GENOTYPECASES = new HashMap<String, String>(100);
    private static Map<String, String> IMPOSSIBLE_GENOTYPE = new HashMap<String, String>(20);
    
	static{
		GENOTYPECASES.put("N","__");
		GENOTYPECASES.put("\t","\t");
		//<lmxll> RA
		GENOTYPECASES.put("RAA","ll");
		GENOTYPECASES.put("RAR","lm");
		//<lmxll> MA
		GENOTYPECASES.put("MAA","ll");
		GENOTYPECASES.put("MAM","lm");
		//<lmxll> WA
		GENOTYPECASES.put("WAA","ll");
		GENOTYPECASES.put("WAW","lm");
		//<lmxll> SG
		GENOTYPECASES.put("SGG","ll");
		GENOTYPECASES.put("SGS","lm");
		//<lmxll> KG
		GENOTYPECASES.put("KGG","ll");
		GENOTYPECASES.put("KGK","lm");
		//<lmxll> RG
		GENOTYPECASES.put("RGG","ll");
		GENOTYPECASES.put("RGR","lm");
		//<lmxll> MC
		GENOTYPECASES.put("MCC","ll");
		GENOTYPECASES.put("MCM","lm");
		//<lmxll> SC
		GENOTYPECASES.put("SCC","ll");
		GENOTYPECASES.put("SCS","lm");
		//<lmxll> YC
		GENOTYPECASES.put("YCC","ll");
		GENOTYPECASES.put("YCY","lm");
		//<lmxll> WT
		GENOTYPECASES.put("WTT","ll");
		GENOTYPECASES.put("WTW","lm");
		//<lmxll> KT
		GENOTYPECASES.put("KTT","ll");
		GENOTYPECASES.put("KTK","lm");
		//<lmxll> YT
		GENOTYPECASES.put("YTT","ll");
		GENOTYPECASES.put("YTY","lm");
		IMPOSSIBLE_GENOTYPE.put("IMPOSSIBLE_Lmxll", "NULLVALUE");
	}
	public static boolean isLmxll(String str) {// for example : str = "MC" ; ch = "C"
		switch(str){
		case "RA":
		    break;
		case "MA":
		    break;
		case "WA":
		    break;
		case "SG":
		    break;
		case "KG":
		    break;
		case "RG":
		    break;
		case "MC":
			break;
		case "SC":
		    break;
		case "YC":
		    break;
		case "WT":
		    break;
		case "KT":
		    break;
		case "YT":
		    break;
		}
        return IMPOSSIBLE_GENOTYPE.containsKey("IMPOSSIBLE_Lmxll");
    }
	
	public static String getLmxll(String str, char ch) {// for example : str = "MC" ; ch = "C"
		String tmp_ch2str = Character.toString(ch);
		
		if (isLmxll(str)) {
			if (ch == 'N'){
				//System.out.println("case N, get N  " + GENOTYPECASES.get("N"));
				return GENOTYPECASES.get("N");
			}
			else if (ch == '\t'){
				//System.out.println("case tab, get tab  " + GENOTYPECASES.get("\t"));
				return GENOTYPECASES.get("\t");
			}
			else if(ch != str.charAt(0) && ch != str.charAt(1)){
				String ch2Str = Character.toString(ch);
				return ch2Str;
			}
			else{
				//System.out.println("case not N, get not N  " + GENOTYPECASES.get(str.concat(tmp_ch2str)) +" ::: " + str.concat(tmp_ch2str));
				return GENOTYPECASES.get(str.concat(tmp_ch2str));
			}
		}
		System.out.println("Lmxll.getLmxll does not work");
		return IMPOSSIBLE_GENOTYPE.get("IMPOSSIBLE_Lmxll");
	}

}
