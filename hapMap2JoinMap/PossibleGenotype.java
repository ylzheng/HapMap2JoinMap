package hapMap2JoinMap;

import java.util.HashMap;
import java.util.Map;

public class PossibleGenotype {
	private static Map<String, String> POSSIBLE_GENOTYPE;
	private static Map<String, String> IMPOSSIBLE_GENOTYPE;
	
	static{
		POSSIBLE_GENOTYPE = new HashMap<String, String>(100);
		IMPOSSIBLE_GENOTYPE = new HashMap<String, String>(20);
		//<abxcd> 6types
	    POSSIBLE_GENOTYPE.put("RY", "<abxcd>");
	    POSSIBLE_GENOTYPE.put("MK", "<abxcd>");
	    POSSIBLE_GENOTYPE.put("WS", "<abxcd>");
	    POSSIBLE_GENOTYPE.put("SW", "<abxcd>");
	    POSSIBLE_GENOTYPE.put("KM", "<abxcd>");
	    POSSIBLE_GENOTYPE.put("YR", "<abxcd>");
	  
	    //<efxeg>24types
	    POSSIBLE_GENOTYPE.put("RM", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("RW", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("MR", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("MW", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("WR", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("WM", "<efxeg>");
	    
	    POSSIBLE_GENOTYPE.put("RS", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("RK", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("SR", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("SK", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("KR", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("KS", "<efxeg>");
	    
	    POSSIBLE_GENOTYPE.put("YS", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("YM", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("SY", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("SM", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("MY", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("MS", "<efxeg>");
	    
	    POSSIBLE_GENOTYPE.put("YW", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("YK", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("WY", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("WK", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("KY", "<efxeg>");
	    POSSIBLE_GENOTYPE.put("KW", "<efxeg>");
	    
	    //<hkxhk>6types
	    POSSIBLE_GENOTYPE.put("RR", "<hkxhk>");
	    POSSIBLE_GENOTYPE.put("YY", "<hkxhk>");
	    POSSIBLE_GENOTYPE.put("SS", "<hkxhk>");
	    POSSIBLE_GENOTYPE.put("WW", "<hkxhk>");
	    POSSIBLE_GENOTYPE.put("KK", "<hkxhk>");
	    POSSIBLE_GENOTYPE.put("MM", "<hkxhk>");
	    
	    //<lmxll>12types
	    POSSIBLE_GENOTYPE.put("RA", "<lmxll>");
	    POSSIBLE_GENOTYPE.put("MA", "<lmxll>");
	    POSSIBLE_GENOTYPE.put("WA", "<lmxll>");
	    
	    POSSIBLE_GENOTYPE.put("SG", "<lmxll>");
	    POSSIBLE_GENOTYPE.put("KG" ,"<lmxll>");
	    POSSIBLE_GENOTYPE.put("RG", "<lmxll>");
	    
	    POSSIBLE_GENOTYPE.put("MC", "<lmxll>");
	    POSSIBLE_GENOTYPE.put("SC", "<lmxll>");
	    POSSIBLE_GENOTYPE.put("YC", "<lmxll>");
	    
	    POSSIBLE_GENOTYPE.put("WT", "<lmxll>");
	    POSSIBLE_GENOTYPE.put("KT", "<lmxll>");
	    POSSIBLE_GENOTYPE.put("YT", "<lmxll>");
	    
	    //<nnxnp>12types
	    POSSIBLE_GENOTYPE.put("AR", "<nnxnp>");
	    POSSIBLE_GENOTYPE.put("AW", "<nnxnp>");
	    POSSIBLE_GENOTYPE.put("AM", "<nnxnp>");
	    
	    POSSIBLE_GENOTYPE.put("GS", "<nnxnp>");
	    POSSIBLE_GENOTYPE.put("GK", "<nnxnp>");
	    POSSIBLE_GENOTYPE.put("GR", "<nnxnp>");
	    
	    POSSIBLE_GENOTYPE.put("CM", "<nnxnp>");
	    POSSIBLE_GENOTYPE.put("CS", "<nnxnp>");
	    POSSIBLE_GENOTYPE.put("CY", "<nnxnp>");
	    
	    POSSIBLE_GENOTYPE.put("TW", "<nnxnp>");
	    POSSIBLE_GENOTYPE.put("TK", "<nnxnp>");
	    POSSIBLE_GENOTYPE.put("TY", "<nnxnp>");
	    
	    IMPOSSIBLE_GENOTYPE.put("IMPOSSIBLE", "NULLVALUE");
	    
	}
	public static boolean isGenotype(String str) {
		return POSSIBLE_GENOTYPE.containsKey(str);
	}
	
	public static String getGenotype(String str) {
		if (isGenotype(str)) {
			return POSSIBLE_GENOTYPE.get(str);
		}
		return IMPOSSIBLE_GENOTYPE.get(str);
		
	}
  
}
