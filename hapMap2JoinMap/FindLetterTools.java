package hapMap2JoinMap;



//This class is to find the corresponding ab, cd, ef, eg, hk, lm, ll, nn, np for the letter A,C,G,T, R,Y,S,W,K,M
public class FindLetterTools {
	
	public static String findGenotype(String genotype, String momdad, char linech){
		switch(genotype){
		case "abxcd":
			//System.out.println("ABXCD! momdad + linech  " + genotype + ":" + momdad + " : " + linech );
			return Abxcd.getAbxcd(momdad, linech);
		case "efxeg":
			return Efxeg.getEfxeg(momdad, linech);
		case "hkxhk":
			return Hkxhk.getHkxhk(momdad, linech);
		case "lmxll":
			//System.out.println("Class FindLetterTools.findGenotype find the genotype -> lmxll! momdad + linech  " + momdad + " : " + linech );
			//System.out.println("Lmxll.getLmxll(momdad, linech)   " + Lmxll.getLmxll(momdad, linech));
			return Lmxll.getLmxll(momdad, linech);
		case "nnxnp":
			return Nnxnp.getNnxnp(momdad, linech);
	    }
		//System.out.println("Class FindLetterTools.findGenotype can not find the genotype!");
		return "not find any genotype";
	}
	
	public static boolean isUpperCase(String s)
	{
		for (int i=0; i<s.length(); i++)
		{
			if (Character.isUpperCase(s.charAt(i)))
			{
				return true;
			}
		}
		return false;
	}
	
	/*private static Map<String, String> GENOTYPECASES;
	
	static{
		GENOTYPECASES = new HashMap<String, String>(100);
		//<abxcd>, RY
		GENOTYPECASES.put("M","ac");
		GENOTYPECASES.put("W","ad");
		GENOTYPECASES.put("S","bc");
		GENOTYPECASES.put("K","bd");
		//<abxcd>, MK
		GENOTYPECASES.put("R","ac");
		GENOTYPECASES.put("W","ad");
		GENOTYPECASES.put("S","bc");
		GENOTYPECASES.put("Y","bd");
		//<abxcd>, WS
		GENOTYPECASES.put("R","ac");
		GENOTYPECASES.put("M","ad");
		GENOTYPECASES.put("K","bc");
		GENOTYPECASES.put("Y","bd");
		//<abxcd>, SW
		GENOTYPECASES.put("R","ac");
		GENOTYPECASES.put("K","ad");
		GENOTYPECASES.put("M","bc");
		GENOTYPECASES.put("Y","bd");
		//<abxcd>, KM
		GENOTYPECASES.put("R","ac");
		GENOTYPECASES.put("S","ad");
		GENOTYPECASES.put("W","bc");
		GENOTYPECASES.put("Y","bd");
		//<abxcd>, YR		
		GENOTYPECASES.put("M","ac");
		GENOTYPECASES.put("S","ad");
		GENOTYPECASES.put("W","bc");
		GENOTYPECASES.put("K","bd");
		
		//<efxeg>1, RM, same A
		GENOTYPECASES.put("A","ee");
		GENOTYPECASES.put("M","eg");
		GENOTYPECASES.put("R","fe");
		GENOTYPECASES.put("S","fg");
		//<efxeg>2, RW same A
		GENOTYPECASES.put("A","ee");
		GENOTYPECASES.put("W","eg");
		GENOTYPECASES.put("R","fe");
		GENOTYPECASES.put("K","fg");
		//<efxeg>3, MR same A
		GENOTYPECASES.put("A","ee");
		GENOTYPECASES.put("R","eg");
		GENOTYPECASES.put("M","fe");
		GENOTYPECASES.put("S","fg");
		//<efxeg>4, MW same A
		GENOTYPECASES.put("A","ee");
		GENOTYPECASES.put("W","eg");
		GENOTYPECASES.put("M","fe");
		GENOTYPECASES.put("Y","fg");
		//<efxeg>5, WR same A
		GENOTYPECASES.put("A","ee");
		GENOTYPECASES.put("R","eg");
		GENOTYPECASES.put("W","fe");
		GENOTYPECASES.put("K","fg");
		//<efxeg>6, WM same A
		GENOTYPECASES.put("A","ee");
		GENOTYPECASES.put("M","eg");
		GENOTYPECASES.put("W","fe");
		GENOTYPECASES.put("Y","fg");
		
		//<efxeg>1, RS same G
		GENOTYPECASES.put("G","ee");
		GENOTYPECASES.put("S","eg");
		GENOTYPECASES.put("R","fe");
		GENOTYPECASES.put("M","fg");
		//<efxeg>2, RK same G
		GENOTYPECASES.put("G","ee");
		GENOTYPECASES.put("K","eg");
		GENOTYPECASES.put("R","fe");
		GENOTYPECASES.put("W","fg");
		//<efxeg>3, SR same G
		GENOTYPECASES.put("G","ee");
		GENOTYPECASES.put("R","eg");
		GENOTYPECASES.put("S","fe");
		GENOTYPECASES.put("M","fg");
		//<efxeg>4, SK same G
		GENOTYPECASES.put("G","ee");
		GENOTYPECASES.put("K","eg");
		GENOTYPECASES.put("S","fe");
		GENOTYPECASES.put("T","fg");
		//<efxeg>5, KR same G
		GENOTYPECASES.put("G","ee");
		GENOTYPECASES.put("R","eg");
		GENOTYPECASES.put("K","fe");
		GENOTYPECASES.put("W","fg");
		//<efxeg>6, KS same G
		GENOTYPECASES.put("G","ee");
		GENOTYPECASES.put("S","eg");
		GENOTYPECASES.put("K","fe");
		GENOTYPECASES.put("Y","fg");
		
		//<efxeg>1, YS same C
		GENOTYPECASES.put("C","ee");
		GENOTYPECASES.put("S","eg");
		GENOTYPECASES.put("Y","fe");
		GENOTYPECASES.put("K","fg");
		//<efxeg>2, YM same C
		GENOTYPECASES.put("C","ee");
		GENOTYPECASES.put("M","eg");
		GENOTYPECASES.put("Y","fe");
		GENOTYPECASES.put("W","fg");
		//<efxeg>3, SY same C
		GENOTYPECASES.put("C","ee");
		GENOTYPECASES.put("Y","eg");
		GENOTYPECASES.put("S","fe");
		GENOTYPECASES.put("K","fg");
		//<efxeg>4, SM same C
		GENOTYPECASES.put("C","ee");
		GENOTYPECASES.put("M","eg");
		GENOTYPECASES.put("S","fe");
		GENOTYPECASES.put("R","fg");
		//<efxeg>5, MY same C
		GENOTYPECASES.put("C","ee");
		GENOTYPECASES.put("Y","eg");
		GENOTYPECASES.put("M","fe");
		GENOTYPECASES.put("W","fg");
		//<efxeg>6, MS same C
		GENOTYPECASES.put("C","ee");
		GENOTYPECASES.put("S","eg");
		GENOTYPECASES.put("M","fe");
		GENOTYPECASES.put("R","fg");
		
		//<efxeg>1, YW same T
		GENOTYPECASES.put("T","ee");
		GENOTYPECASES.put("W","eg");
		GENOTYPECASES.put("Y","fe");
		GENOTYPECASES.put("M","fg");
		//<efxeg>2, YK same T
		GENOTYPECASES.put("T","ee");
		GENOTYPECASES.put("K","eg");
		GENOTYPECASES.put("Y","fe");
		GENOTYPECASES.put("S","fg");
		//<efxeg>3, WY same T
		GENOTYPECASES.put("T","ee");
		GENOTYPECASES.put("Y","eg");
		GENOTYPECASES.put("W","fe");
		GENOTYPECASES.put("M","fg");
		//<efxeg>4, WK same T
		GENOTYPECASES.put("T","ee");
		GENOTYPECASES.put("K","eg");
		GENOTYPECASES.put("W","fe");
		GENOTYPECASES.put("R","fg");
		//<efxeg>5, KY same T
		GENOTYPECASES.put("T","ee");
		GENOTYPECASES.put("Y","eg");
		GENOTYPECASES.put("K","fe");
		GENOTYPECASES.put("S","fg");
		//<efxeg>6, KW same T
		GENOTYPECASES.put("T","ee");
		GENOTYPECASES.put("W","eg");
		GENOTYPECASES.put("K","fe");
		GENOTYPECASES.put("R","fg");

		//<hkxhk> RR
		GENOTYPECASES.put("A","hh");
		GENOTYPECASES.put("R","hk");
		GENOTYPECASES.put("G","kk");
		//<hkxhk> YY
		GENOTYPECASES.put("T","hh");
		GENOTYPECASES.put("Y","hk");
		GENOTYPECASES.put("C","kk");
		//<hkxhk> SS
		GENOTYPECASES.put("G","hh");
		GENOTYPECASES.put("S","hk");
		GENOTYPECASES.put("C","kk");
		//<hkxhk> WW
		GENOTYPECASES.put("A","hh");
		GENOTYPECASES.put("W","hk");
		GENOTYPECASES.put("T","kk");
		//<hkxhk> KK
		GENOTYPECASES.put("G","hh");
		GENOTYPECASES.put("K","hk");
		GENOTYPECASES.put("T","kk");
		//<hkxhk> MM
		GENOTYPECASES.put("A","hh");
		GENOTYPECASES.put("M","hk");
		GENOTYPECASES.put("C","kk");
		
		//<lmxll> RA
		GENOTYPECASES.put("A","ll");
		GENOTYPECASES.put("R","lm");
		//<lmxll> MA
		GENOTYPECASES.put("A","ll");
		GENOTYPECASES.put("M","lm");
		//<lmxll> WA
		GENOTYPECASES.put("A","ll");
		GENOTYPECASES.put("W","lm");
		//<lmxll> SG
		GENOTYPECASES.put("G","ll");
		GENOTYPECASES.put("S","lm");
		//<lmxll> KG
		GENOTYPECASES.put("G","ll");
		GENOTYPECASES.put("K","lm");
		//<lmxll> RG
		GENOTYPECASES.put("G","ll");
		GENOTYPECASES.put("R","lm");
		//<lmxll> MC
		GENOTYPECASES.put("C","ll");
		GENOTYPECASES.put("M","lm");
		//<lmxll> SC
		GENOTYPECASES.put("C","ll");
		GENOTYPECASES.put("S","lm");
		//<lmxll> YC
		GENOTYPECASES.put("C","ll");
		GENOTYPECASES.put("Y","lm");
		//<lmxll> WT
		GENOTYPECASES.put("T","ll");
		GENOTYPECASES.put("W","lm");
		//<lmxll> KT
		GENOTYPECASES.put("T","ll");
		GENOTYPECASES.put("K","lm");
		//<lmxll> YT
		GENOTYPECASES.put("T","ll");
		GENOTYPECASES.put("Y","lm");
		
		//<nnxnp>1 AR
		GENOTYPECASES.put("A","ll");
		GENOTYPECASES.put("R","lm");
		//<nnxnp>2 AW
		GENOTYPECASES.put("A","ll");
		GENOTYPECASES.put("W","lm");
		//<nnxnp>3 AM
		GENOTYPECASES.put("A","ll");
		GENOTYPECASES.put("M","lm");
		//<nnxnp>4 GS
		GENOTYPECASES.put("G","ll");
		GENOTYPECASES.put("S","lm");
		//<nnxnp>5 GK
		GENOTYPECASES.put("G","ll");
		GENOTYPECASES.put("K","lm");
		//<nnxnp>6 GR
		GENOTYPECASES.put("G","ll");
		GENOTYPECASES.put("R","lm");
		//<nnxnp>7 CM
		GENOTYPECASES.put("C","ll");
		GENOTYPECASES.put("M","lm");
		//<nnxnp>8 CS
		GENOTYPECASES.put("C","ll");
		GENOTYPECASES.put("S","lm");
		//<nnxnp>9 CY
		GENOTYPECASES.put("C","ll");
		GENOTYPECASES.put("Y","lm");
		//<nnxnp>10 TW
		GENOTYPECASES.put("T","ll");
		GENOTYPECASES.put("W","lm");
		//<nnxnp>11 TK
		GENOTYPECASES.put("T","ll");
		GENOTYPECASES.put("K","lm");
		//<nnxnp>12 TY
		GENOTYPECASES.put("T","ll");
		GENOTYPECASES.put("Y","lm");		
	}*/
}
