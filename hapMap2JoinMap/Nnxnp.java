package hapMap2JoinMap;

import java.util.HashMap;
import java.util.Map;

public class Nnxnp {
    
	private static Map<String, String> GENOTYPECASES = new HashMap<String, String>(100);
    private static Map<String, String> IMPOSSIBLE_GENOTYPE = new HashMap<String, String>(20);
	
	static{
		GENOTYPECASES.put("N","__");
		GENOTYPECASES.put("\t","\t");
		//<nnxnp>1 AR
		GENOTYPECASES.put("ARA","nn");
		GENOTYPECASES.put("ARR","np");
		//<nnxnp>2 AW
		GENOTYPECASES.put("AWA","nn");
		GENOTYPECASES.put("AWW","np");
		//<nnxnp>3 AM
		GENOTYPECASES.put("AMA","nn");
		GENOTYPECASES.put("AMM","np");
		//<nnxnp>4 GS
		GENOTYPECASES.put("GSG","nn");
		GENOTYPECASES.put("GSS","np");
		//<nnxnp>5 GK
		GENOTYPECASES.put("GKG","nn");
		GENOTYPECASES.put("GKK","np");
		//<nnxnp>6 GR
		GENOTYPECASES.put("GRG","nn");
		GENOTYPECASES.put("GRR","np");
		//<nnxnp>7 CM
		GENOTYPECASES.put("CMC","nn");
		GENOTYPECASES.put("CMM","np");
		//<nnxnp>8 CS
		GENOTYPECASES.put("CSC","nn");
		GENOTYPECASES.put("CSS","np");
		//<nnxnp>9 CY
		GENOTYPECASES.put("CYC","nn");
		GENOTYPECASES.put("CYY","np");
		//<nnxnp>10 TW
		GENOTYPECASES.put("TWT","nn");
		GENOTYPECASES.put("TWW","np");
		//<nnxnp>11 TK
		GENOTYPECASES.put("TKT","nn");
		GENOTYPECASES.put("TKK","np");
		//<nnxnp>12 TY
		GENOTYPECASES.put("TYT","nn");
		GENOTYPECASES.put("TYY","np");	
		IMPOSSIBLE_GENOTYPE.put("IMPOSSIBLE_Nnxnp", "NULLVALUE");
	}
	public static boolean isNnxnp(String str) {// for example : str = "MC" ; ch = "C"
		switch(str){
		case "AR":
		    break;
		case "AM":
		    break;
		case "AW":
		    break;
		case "GS":
		    break;
		case "GK":
		    break;
		case "GR":
		    break;
		case "CM":
			break;
		case "CS":
		    break;
		case "CY":
		    break;
		case "TW":
		    break;
		case "TK":
		    break;
		case "TY":
		    break;
		}
		//System.out.println("Nnxnp.isNnxnp does not work");
		return IMPOSSIBLE_GENOTYPE.containsKey("IMPOSSIBLE_Nnxnp");
	}
	
	public static String getNnxnp(String str, char ch) {// for example : str = "MC" ; ch = "C"
		String tmp_ch2str = Character.toString(ch);
		String strConcatCh = str.concat(tmp_ch2str);
		if (isNnxnp(str)) {
			if (ch == 'N'){
				//System.out.println("case N, get N  " + GENOTYPECASES.get("N"));
				return GENOTYPECASES.get("N");
			}
			else if (ch == '\t'){
				//System.out.println("case tab, get tab  " + GENOTYPECASES.get("\t"));
				return GENOTYPECASES.get("\t");
			}
			else if(GENOTYPECASES.containsKey(strConcatCh)){
				return GENOTYPECASES.get(str.concat(tmp_ch2str));
			}
			else{
				//System.out.println("str.charAt(0)  :" + str.charAt(0)  + "str.charAt(1)  :  "+ str.charAt(1));
				String ch2Str = Character.toString(ch);
				return ch2Str;
			}
		}
		System.out.println("Nnxnp.getNnxnp does not work");
		return IMPOSSIBLE_GENOTYPE.get("IMPOSSIBLE_Nnxnp");
	}		
}
