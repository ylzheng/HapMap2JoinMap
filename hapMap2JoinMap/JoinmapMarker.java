/*@author: Yueli Zheng
 *Email: yzheng@g.clemson.edu
 *School of Computing, Clemson University
 *Copyright (c) 2013, Yueli Zheng. All rights reserved.<br> Yueli Zheng
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package hapMap2JoinMap;

//import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
//import org.junit.*;

public class JoinmapMarker {
	//public static Map<String, Double> markerHashMap = new HashMap<String, Double>();
	
	public static Map<String, Double> progenyFrequency = new HashMap<String, Double>();
	
	public static double getMarkerSimilarity(String progenyMarker){
		double acCounter = 0, adCounter = 0, bcCounter = 0, bdCounter = 0, eeCounter = 0, egCounter = 0, feCounter = 0, 
				fgCounter = 0, hhCounter = 0, hkCounter = 0, kkCounter = 0, llCounter = 0, lmCounter = 0, nnCounter = 0, 
				npCounter = 0, nullCounter = 0;
		String progMarker = progenyMarker;
		String[] pMarker = progMarker.split("\t");
		double markerCounter = 0;
		
		for (int i = 0; i < ((progenyMarker.length()+1)/3); i++){
		/*suppose x is the number of markers, therefore {2x+x-1 = line.lenth} and Marker number = (line.length +1)/3.*/
			switch(pMarker[i]){
			//abxcd
			case "ac":
				acCounter++;
				markerCounter++;
			    break;
			case "ca":
				acCounter++;
				markerCounter++;
				break;
			case "ad":
				adCounter++;
				markerCounter++;
			    break;
			case "da":
				adCounter++;
				markerCounter++;
				break;
			case "bc":
				bcCounter++;
				markerCounter++;
			    break;
			case "cb":
				bcCounter++;
				markerCounter++;
				break;
			case "bd":
				bdCounter++;
				markerCounter++;
			    break;
			case "db":
				bdCounter++;
				markerCounter++;
				break;
			//efxeg
			case "ee":
				eeCounter++;
				markerCounter++;
			    break;
			case "eg":
				egCounter++;
				markerCounter++;
				break;
			case "ge":
				egCounter++;
				markerCounter++;
			    break;
			case "fe":
				feCounter++;
				markerCounter++;
				break;
			case "ef":
				feCounter++;
				markerCounter++;
			    break;
			case "fg":
				fgCounter++;
				markerCounter++;
				break;
			case "gf":
				fgCounter++;
				markerCounter++;
			    break;
			//hkxhk
			case "hh":
				hhCounter++;
				markerCounter++;
			    break;
			case "hk":
				hkCounter++;
				markerCounter++;
				break;
			case "kh":
				hkCounter++;
				markerCounter++;
			    break;
			case "kk":
				kkCounter++;
				markerCounter++;
				break;
			//lmxll
			case "ll":
				llCounter++;
				markerCounter++;
			    break;
			case "ml":
				lmCounter++;
				markerCounter++;
				break;
			case "lm":
				lmCounter++;
				markerCounter++;
			    break;
			//nnxnp
			case "nn":
				nnCounter++;
				markerCounter++;
			    break;
			case "np":
				npCounter++;
				markerCounter++;
				break;
			case "pn":
				npCounter++;
				markerCounter++;
			    break;
			//__
			case "__":
				nullCounter++;
				markerCounter++;
				break;
			}
		}
		
		progenyFrequency.put("ac", acCounter); 
		progenyFrequency.put("ad", adCounter); 
		progenyFrequency.put("bc", bcCounter); 
		progenyFrequency.put("bd", bdCounter); 
		progenyFrequency.put("ee", eeCounter); 
		progenyFrequency.put("eg", egCounter); 
		progenyFrequency.put("fe", feCounter); 
		progenyFrequency.put("fg", fgCounter);
		progenyFrequency.put("hh", hhCounter); 
		progenyFrequency.put("hk", hkCounter); 
		progenyFrequency.put("kk", kkCounter); 
		progenyFrequency.put("ll", llCounter); 
		progenyFrequency.put("lm", lmCounter); 
		progenyFrequency.put("nn", nnCounter);
		progenyFrequency.put("np", npCounter); 
		progenyFrequency.put("__", nullCounter);
		
		
		/*List<Double> sortedValues = new ArrayList<>(progenyFrequency.values());
		Collections.sort(sortedValues);
		for (Double value : sortedValues) { 
			 System.out.println("value " + value);
		}*/
		
		progenyFrequency = MapUtil.sortByValue(progenyFrequency);
		//Assert.assertEquals( 16, progenyFrequency.size() );
        Double previous = null;
        //String previousKey = null;
        for(Map.Entry<String, Double> entry : progenyFrequency.entrySet()) {
            //Assert.assertNotNull( entry.getValue() );
        	if (entry.getValue() != null){
        		//System.out.println("entry: " + entry.getKey() + " -- " + entry.getValue() + " ---p1 " + previous);
        		if (previous != null) {
                    if( entry.getValue() < previous ){
                        System.out.println("joinmapMarker sorting is wrong");
                    }
                }
                previous = entry.getValue();
                //System.out.println("p2 " + previous);
                //previousKey = entry.getKey();
                //System.out.println("entry: " + entry.getKey() + " -- " + entry.getValue());
        	}
        }
        
        /*for(Map.Entry<String, Double> entry : progenyFrequency.entrySet()) {
            Assert.assertNotNull( entry.getValue() );
            if (previous != null) {
                Assert.assertTrue( entry.getValue() >= previous );
            }
            previous = entry.getValue();
            //previousKey = entry.getKey();
            System.out.println("entry: " + entry.getKey() + " -- " + entry.getValue());
        }*/
        return previous/markerCounter;
	}
	
	public static class MapUtil
	{
	    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map )
	    {
	        List<Map.Entry<K, V>> list =
	            new LinkedList<Map.Entry<K, V>>( map.entrySet() );
	        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
	        {
	            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
	            {
	                /*System.out.println("o1.getValue() " + o1.getKey() +"  " + o1.getValue() + "  o2.getValue()  " 
	                + o2.getKey() + "  " +  o2.getValue());*/
	            	return (o1.getValue()).compareTo( o2.getValue() );
	            }
	        } );

	        Map<K, V> result = new LinkedHashMap<K, V>();
	        for (Map.Entry<K, V> entry : list)
	        {
	            result.put( entry.getKey(), entry.getValue() );
	        }
	        return result;
	    }
	}

}
