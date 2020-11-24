package cdl.com;

import java.util.*;



public class ScanInput {
	public static HashMap<String, Double> scanItemDataMap = new HashMap<String, Double>();
	public static ArrayList<HashMap<String, Double>>  itemDataList =  new ArrayList<HashMap<String, Double>>();
	static ReadStockFile rsFile=new ReadStockFile();
	static PriceRules priceRule= new PriceRules();

	public static void getScanInput() {Double dbPrice;
		Scanner scanItem = new Scanner(System.in);
		String inputItem = scanItem.next();
		if(verifyInput(inputItem.toUpperCase())!=null){
			System.out.println("Scanned item::::::: "+ inputItem.toUpperCase()+ "\nPress q to Quit ");
			while (!inputItem.equals("q")) {
				if(verifyInput(inputItem.toUpperCase())!=null){
					dbPrice=inputItemPriceMatch(inputItem.toUpperCase());
					scanItemDataMap.put(inputItem.toUpperCase(), dbPrice);
					itemDataList.add(scanItemDataMap);scanItemDataMap = new HashMap<String, Double>();
					priceRule.calculateTotal();
					priceRule.getMultibuySaving();
          priceRule.totSaving();
          priceRule.totalPrice/=100.0;
					System.out.println("Total Price :: £"+ priceRule.totalPrice);
				}
					System.out.println("Please Scan an item");
					inputItem = scanItem.next();if(inputItem.equals("q")){System.out.println("Thank you for Shopping"+ "\nTotal Price :: £"+ priceRule.totalPrice);priceRule.totSaving();break;}
					if(verifyInput(inputItem.toUpperCase())==null)System.out.println("Item is not present in SKU");
					else System.out.println("Scanned item::::::: "+ inputItem.toUpperCase()+ "\nPress q to Quit ");

			}priceRule.totalPrice=0.0;
		}
	}

	public static Double inputItemPriceMatch(String item) {Double value=-0.0;
		Iterator it = rsFile.itemPriceMap.entrySet().iterator();
	    while (it.hasNext()) {
	    	Map.Entry pair = (Map.Entry)it.next();
	    	String key= pair.getKey()+"";
	    	value= Double.parseDouble(pair.getValue()+"");
	    	if(key.equals(item))
	    		return value;
	    }
		return value;
	}
	public static String verifyInput(String scanitem) {
		Iterator it = rsFile.itemPriceMap.entrySet().iterator();
	    while (it.hasNext()) {
	    	Map.Entry pair = (Map.Entry)it.next();
	    	String key= pair.getKey()+"";
	    	if(key.equals(scanitem))
	    		return key;
	    }
		return null;
	}

}
