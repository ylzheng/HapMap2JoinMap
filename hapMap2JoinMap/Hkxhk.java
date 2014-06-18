package hapMap2JoinMap;

import java.util.HashMap;
import java.util.Map;

public class Hkxhk {
	private static Map<String, String> GENOTYPECASES = new HashMap<String, String>(100);
    private static Map<String, String> IMPOSSIBLE_GENOTYPE = new HashMap<String, String>(20);
	
	static{
		GENOTYPECASES.put("\t","\t");
		IMPOSSIBLE_GENOTYPE.put("IMPOSSIBLE_Hkxhk", "NULLVALUE");
		GENOTYPECASES.put("N","__");
		//<hkxhk> RR
		GENOTYPECASES.put("RRA","hh");
		GENOTYPECASES.put("RRR","hk");
		GENOTYPECASES.put("RRG","kk");
		//<hkxhk> YY
		GENOTYPECASES.put("YYT","hh");
		GENOTYPECASES.put("YYY","hk");
		GENOTYPECASES.put("YYC","kk");
		//<hkxhk> SS
		GENOTYPECASES.put("SSG","hh");
		GENOTYPECASES.put("SSS","hk");
		GENOTYPECASES.put("SSC","kk");
		//<hkxhk> WW
		GENOTYPECASES.put("WWA","hh");
		GENOTYPECASES.put("WWW","hk");
		GENOTYPECASES.put("WWT","kk");
		//<hkxhk> KK
		GENOTYPECASES.put("KKG","hh");
		GENOTYPECASES.put("KKK","hk");
		GENOTYPECASES.put("KKT","kk");
		//<hkxhk> MM
		GENOTYPECASES.put("MMA","hh");
		GENOTYPECASES.put("MMM","hk");
		GENOTYPECASES.put("MMC","kk");
	}
	public static boolean isHkxhk(String str) {// for example : str = "MC" ; ch = "C"
    	switch(str){
		case "RR"://1
		    break;
		case "YY"://2
		    break;
		case "SS"://3
		    break;
		case "WW"://4
		    break;
		case "KK"://5
		    break;
		case "MM"://6
		    break;
		}
		//System.out.println("Hkxhk.isHkxhk does not work");
		return IMPOSSIBLE_GENOTYPE.containsKey("IMPOSSIBLE_Hkxhk");
	}
	
	public static String getHkxhk(String str, char ch) {// for example : str = "MC" ; ch = "C"
		String tmp_ch2str = Character.toString(ch);
		String strConcatCh = str.concat(tmp_ch2str);
		if (isHkxhk(str)) {
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
		System.out.println("Hkxhk.getHkxhk does not work");
		return IMPOSSIBLE_GENOTYPE.get("IMPOSSIBLE_Hkxhk");
	}
}
