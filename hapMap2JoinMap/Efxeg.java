package hapMap2JoinMap;

import java.util.HashMap;
import java.util.Map;

public class Efxeg {
	private static Map<String, String> GENOTYPECASES = new HashMap<String, String>(100);
    private static Map<String, String> IMPOSSIBLE_GENOTYPE = new HashMap<String, String>(20);
    static{
		GENOTYPECASES.put("N","__");
		GENOTYPECASES.put("\t","\t");
		IMPOSSIBLE_GENOTYPE.put("IMPOSSIBLE_Efxeg", "NULLVALUE");
		//<efxeg>1, RM same A
		GENOTYPECASES.put("RMA","ee");
		GENOTYPECASES.put("RMM","eg");
		GENOTYPECASES.put("RMR","fe");
		GENOTYPECASES.put("RMS","fg");
		//<efxeg>2, RW same A
		GENOTYPECASES.put("RWA","ee");
		GENOTYPECASES.put("RWW","eg");
		GENOTYPECASES.put("RWR","fe");
		GENOTYPECASES.put("RWK","fg");
		//<efxeg>3, MR same A
		GENOTYPECASES.put("MRA","ee");
		GENOTYPECASES.put("MRR","eg");
		GENOTYPECASES.put("MRM","fe");
		GENOTYPECASES.put("MRS","fg");
		//<efxeg>4, MW same A
		GENOTYPECASES.put("MWA","ee");
		GENOTYPECASES.put("MWW","eg");
		GENOTYPECASES.put("MWM","fe");
		GENOTYPECASES.put("MWY","fg");
		//<efxeg>5, WR same A
		GENOTYPECASES.put("WRA","ee");
		GENOTYPECASES.put("WRR","eg");
		GENOTYPECASES.put("WRW","fe");
		GENOTYPECASES.put("K","fg");
		//<efxeg>6, WM same A
		GENOTYPECASES.put("WMA","ee");
		GENOTYPECASES.put("WMM","eg");
		GENOTYPECASES.put("WMW","fe");
		GENOTYPECASES.put("WMY","fg");
		
		//<efxeg>1, RS same G
		GENOTYPECASES.put("RSG","ee");
		GENOTYPECASES.put("RSS","eg");
		GENOTYPECASES.put("RSR","fe");
		GENOTYPECASES.put("RSM","fg");
		//<efxeg>2, RK same G
		GENOTYPECASES.put("RKG","ee");
		GENOTYPECASES.put("RKK","eg");
		GENOTYPECASES.put("RKR","fe");
		GENOTYPECASES.put("RKW","fg");
		//<efxeg>3, SR same G
		GENOTYPECASES.put("SRG","ee");
		GENOTYPECASES.put("SRR","eg");
		GENOTYPECASES.put("SRS","fe");
		GENOTYPECASES.put("SRM","fg");
		//<efxeg>4, SK same G
		GENOTYPECASES.put("SKG","ee");
		GENOTYPECASES.put("SKK","eg");
		GENOTYPECASES.put("SKS","fe");
		GENOTYPECASES.put("SKT","fg");
		//<efxeg>5, KR same G
		GENOTYPECASES.put("KRG","ee");
		GENOTYPECASES.put("KRR","eg");
		GENOTYPECASES.put("KRK","fe");
		GENOTYPECASES.put("KRW","fg");
		//<efxeg>6, KS same G
		GENOTYPECASES.put("KSG","ee");
		GENOTYPECASES.put("KSS","eg");
		GENOTYPECASES.put("KSK","fe");
		GENOTYPECASES.put("KSY","fg");
		
		//<efxeg>1, YS same C
		GENOTYPECASES.put("YSC","ee");
		GENOTYPECASES.put("YSS","eg");
		GENOTYPECASES.put("YSY","fe");
		GENOTYPECASES.put("YSK","fg");
		//<efxeg>2, YM same C
		GENOTYPECASES.put("YMC","ee");
		GENOTYPECASES.put("YMM","eg");
		GENOTYPECASES.put("YMY","fe");
		GENOTYPECASES.put("YMW","fg");
		//<efxeg>3, SY same C
		GENOTYPECASES.put("SYC","ee");
		GENOTYPECASES.put("SYY","eg");
		GENOTYPECASES.put("SYS","fe");
		GENOTYPECASES.put("SYK","fg");
		//<efxeg>4, SM same C
		GENOTYPECASES.put("SMC","ee");
		GENOTYPECASES.put("SMM","eg");
		GENOTYPECASES.put("SMS","fe");
		GENOTYPECASES.put("SMR","fg");
		//<efxeg>5, MY same C
		GENOTYPECASES.put("MYC","ee");
		GENOTYPECASES.put("MYY","eg");
		GENOTYPECASES.put("MYM","fe");
		GENOTYPECASES.put("MYW","fg");
		//<efxeg>6, MS same C
		GENOTYPECASES.put("MSC","ee");
		GENOTYPECASES.put("MSS","eg");
		GENOTYPECASES.put("MSM","fe");
		GENOTYPECASES.put("MSR","fg");
		
		//<efxeg>1, YW same T
		GENOTYPECASES.put("YWT","ee");
		GENOTYPECASES.put("YWW","eg");
		GENOTYPECASES.put("YWY","fe");
		GENOTYPECASES.put("YWM","fg");
		//<efxeg>2, YK same T
		GENOTYPECASES.put("YKT","ee");
		GENOTYPECASES.put("YKK","eg");
		GENOTYPECASES.put("YKY","fe");
		GENOTYPECASES.put("YKS","fg");
		//<efxeg>3, WY same T
		GENOTYPECASES.put("WYT","ee");
		GENOTYPECASES.put("WYY","eg");
		GENOTYPECASES.put("WYW","fe");
		GENOTYPECASES.put("WYM","fg");
		//<efxeg>4, WK same T
		GENOTYPECASES.put("WKT","ee");
		GENOTYPECASES.put("WKK","eg");
		GENOTYPECASES.put("WKW","fe");
		GENOTYPECASES.put("WKR","fg");
		//<efxeg>5, KY same T
		GENOTYPECASES.put("KYT","ee");
		GENOTYPECASES.put("KYY","eg");
		GENOTYPECASES.put("KYK","fe");
		GENOTYPECASES.put("KYS","fg");
		//<efxeg>6, KW same T
		GENOTYPECASES.put("KWT","ee");
		GENOTYPECASES.put("KWW","eg");
		GENOTYPECASES.put("KWK","fe");
		GENOTYPECASES.put("KWR","fg");
	}	
    public static boolean isEfxeg(String str) {// for example : str = "MC" ; ch = "C"
    	switch(str){
		case "RM"://1sameA
		    break;
		case "RW"://2sameA
		    break;
		case "MR"://3sameA
		    break;
		case "MW"://4sameA
		    break;
		case "WR"://5sameA
		    break;
		case "WM"://6sameA
		    break;
		case "RS"://1sameG
			break;
		case "RK"://2sameG
		    break;
		case "SR"://3sameG
		    break;
		case "SK"://4sameG
		    break;
		case "KR"://5sameG
		    break;
		case "KS"://6sameG
		    break;
		case "YS"://1sameC
		    break;
		case "YM"://2sameC
		    break;
		case "SY"://3sameC
		    break;
		case "SM"://4sameC
		    break;
		case "MY"://5sameC
		    break;
		case "MS"://6sameC
		    break;
		case "YW"://1sameT
			break;
		case "YK"://2sameT
		    break;
		case "WY"://3sameT
		    break;
		case "WK"://4sameT
		    break;
		case "KY"://5sameT
		    break;
		case "KW"://6sameT
		    break;
		}
		//System.out.println("Efxeg.isEfxeg does not work");
		return IMPOSSIBLE_GENOTYPE.containsKey("IMPOSSIBLE_Efxeg");
	}
	
	public static String getEfxeg(String str, char ch) {// for example : str = "MC" ; ch = "C"
		String tmp_ch2str = Character.toString(ch);
		String strConcatCh = str.concat(tmp_ch2str);
		if (isEfxeg(str)) {
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
		System.out.println("Efxeg.getEfxeg does not work");
		return IMPOSSIBLE_GENOTYPE.get("IMPOSSIBLE_Efxeg");
	}
}
