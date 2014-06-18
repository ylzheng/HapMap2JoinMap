package hapMap2JoinMap;

import java.util.HashMap;
import java.util.Map;

public class Abxcd {
	private static Map<String, String> GENOTYPECASES = new HashMap<String, String>(100);
    private static Map<String, String> IMPOSSIBLE_GENOTYPE = new HashMap<String, String>(20);
	
	static{
		GENOTYPECASES.put("\t","\t");
		IMPOSSIBLE_GENOTYPE.put("IMPOSSIBLE_Abxcd", "NULLVALUE");
		GENOTYPECASES.put("N","__");
		//<abxcd>1, RY
		GENOTYPECASES.put("RYM","ac");
		GENOTYPECASES.put("RYW","ad");
		GENOTYPECASES.put("RYS","bc");
		GENOTYPECASES.put("RYK","bd");
		//<abxcd>2, MK
		GENOTYPECASES.put("MKR","ac");
		GENOTYPECASES.put("MKW","ad");
		GENOTYPECASES.put("MKS","bc");
		GENOTYPECASES.put("MKY","bd");
		//<abxcd>3, WS
		GENOTYPECASES.put("WSR","ac");
		GENOTYPECASES.put("WSM","ad");
		GENOTYPECASES.put("WSK","bc");
		GENOTYPECASES.put("WSY","bd");
		//<abxcd>4, SW
		GENOTYPECASES.put("SWR","ac");
		GENOTYPECASES.put("SWK","ad");
		GENOTYPECASES.put("SWM","bc");
		GENOTYPECASES.put("SWY","bd");
		//<abxcd>5, KM
		GENOTYPECASES.put("KMR","ac");
		GENOTYPECASES.put("KMS","ad");
		GENOTYPECASES.put("KMW","bc");
		GENOTYPECASES.put("KMY","bd");
		//<abxcd>6, YR		
		GENOTYPECASES.put("YRM","ac");
		GENOTYPECASES.put("YRS","ad");
		GENOTYPECASES.put("YRW","bc");
		GENOTYPECASES.put("YRK","bd");
	}
	public static boolean isAbxcd(String str) {// for example : str = "MC" ; ch = "C"
    	switch(str){
		case "RY"://1
		    break;
		case "MK"://2
		    break;
		case "WS"://3
			//System.out.println("case \"KM\"://3   ");
		    break;
		case "SW"://4
			//System.out.println("case \"KM\"://4   ");
		    break;
		case "KM"://5
			//System.out.println("case \"KM\"://5   ");
			break;
		case "YR"://6
		    break;
		}
		//System.out.println("Abxcd.isAbxcd does not work");
		return IMPOSSIBLE_GENOTYPE.containsKey("IMPOSSIBLE_Abxcd");
	}
	
	public static String getAbxcd(String str, char ch) {// for example : str = "MC" ; ch = "C"
		String tmp_ch2str = Character.toString(ch);
		String strConcatCh = str.concat(tmp_ch2str);
		//System.out.println("str + tmp_ch2str -> " + tmp_ch2str + " ::: " +str);
		//System.out.println("str.charAt(0):  " + str.charAt(0)  + "  str.charAt(1):  "+ str.charAt(1));
		if (isAbxcd(str)) {
			if (ch == 'N'){
				//System.out.println("case N, get N  " + GENOTYPECASES.get("N"));
				return GENOTYPECASES.get("N");
			}
			else if (ch == '\t'){
				//System.out.println("case tab, get tab  " + GENOTYPECASES.get("\t"));
				return GENOTYPECASES.get("\t");
			}
			else if(GENOTYPECASES.containsKey(strConcatCh)){
				//System.out.println("case not N, get not N  " + GENOTYPECASES.get(str.concat(tmp_ch2str)) +" ::: " + str.concat(tmp_ch2str));
				return GENOTYPECASES.get(str.concat(tmp_ch2str));
			}
			else{
				//System.out.println("str.charAt(0)  :" + str.charAt(0)  + "str.charAt(1)  :  "+ str.charAt(1));
				String ch2Str = Character.toString(ch);
				return ch2Str;
			}
		}
		System.out.println("Abxcd.getAbxcd does not work");
		return IMPOSSIBLE_GENOTYPE.get("IMPOSSIBLE_Abxcd");
	}
}
