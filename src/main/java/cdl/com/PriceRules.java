package cdl.com;

import java.util.*;


	public class PriceRules {
		static ScanInput scnInput = new ScanInput();
		static ReadStockFile rsFile=new ReadStockFile();
		static Map<String, Integer> scanItemfreqMap = new HashMap<String, Integer>();
		static TreeMap<String, Double> savingItemMap = new TreeMap<String, Double>();
		static List<String> reducereplist = new ArrayList<String>();//temp

		static Double totalPrice=0.0;
		static Double totSaving=0.0;

		public static double calculateTotal() {double totPrice=0.0;
			for(int i=0;i<scnInput.itemDataList.size(); i++) {
				Iterator it = scnInput.itemDataList.get(i).entrySet().iterator();
				 while (it.hasNext()) {
				    	Map.Entry pair = (Map.Entry)it.next();
				    	String key= pair.getKey()+"";
				    	Double value= Double.parseDouble(pair.getValue()+"");
				    	totPrice+=value;
				    	System.out.println(key+ "  --  "+ value );
				    	reducereplist.add(key);
				    	Set<String> unique = new HashSet<String>(reducereplist);
				    	for (String key2 : unique) {
				    	    scanItemfreqMap.put(key2, Collections.frequency(reducereplist, key2));
				    	}
				    }
			}totalPrice=totPrice;
			reducereplist = new ArrayList<String>();
			return totalPrice;
		}

		public static double totSaving() {double tot=0.0;
			Iterator it = savingItemMap.entrySet().iterator();
			 while (it.hasNext()) {
			    	Map.Entry pair = (Map.Entry)it.next();
			    	String key= pair.getKey()+"";
			    	tot+=Double.parseDouble(pair.getValue()+"");
			 }if(tot!=0.0)System.out.println("*Today's total savings *** "+tot);return tot;
		}

		public static double getMultibuySaving() {double saving=0.0;
			Iterator it = scanItemfreqMap.entrySet().iterator();
			 while (it.hasNext()) {
			    	Map.Entry pair = (Map.Entry)it.next();
			    	String key= pair.getKey()+"";
			    	int qty=Integer.parseInt(pair.getValue()+"");
			    	saving=getDifference(key, qty);
			    	totalPrice-=saving;
			    	totSaving=saving;
			    	savingItemMap.put(key, saving);
			    	if(saving!=-0.0)System.out.println(saving+ " * Multibuy Savings on * "+key);
			 }return saving;
		}

		public static double getDifference(String item, int qty){double p=0.0;double calTotal, calOffer = 0, discount=0.0; int value=qty, value1=qty;
			Iterator it = rsFile.itemOfferMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        ItemOffer exampleOfGetValue = rsFile.itemOfferMap.get(pair.getKey());
		        if(pair.getKey().equals(item) && qty == exampleOfGetValue.quantity ) {
		        	double indItemPrice= getItemPrice(item);
			    	calTotal=indItemPrice*qty;
			    	return calTotal-exampleOfGetValue.price;
		        }
		        if(pair.getKey().equals(item) && qty >= exampleOfGetValue.quantity) {
		        	int i=qty;
		        	while(i>=exampleOfGetValue.quantity) {
		        		value=value-exampleOfGetValue.quantity;
		        		double indItemPrice= getItemPrice(item);
		        		calTotal=indItemPrice*value1;
		        		if(value<exampleOfGetValue.quantity) {
		        			discount+=calTotal-(value*indItemPrice+exampleOfGetValue.price);
		        		}
		        		if(value1>=exampleOfGetValue.quantity){
		        			calOffer=exampleOfGetValue.price*(qty/exampleOfGetValue.quantity);
		        			discount=(((qty/exampleOfGetValue.quantity)*exampleOfGetValue.quantity*indItemPrice)-calOffer);
		        			value1=value1-exampleOfGetValue.quantity;
				    	}i=i-exampleOfGetValue.quantity;
		        	}
		        	return discount;
		         }
		    }return -0.0;
		}

		public static double getItemPrice(String item) {
			 Iterator it = rsFile.itemPriceMap.entrySet().iterator();
			 while (it.hasNext()) {
			    	Map.Entry pair = (Map.Entry)it.next();
			    	String key= pair.getKey()+"";
			    	if(key.equals(item)) {
			    		return Double.parseDouble(pair.getValue()+"");
			    	}
			 }return -0.0;
		}
		public static int testValue(int k) {
			return 1+k;
		}

	}


