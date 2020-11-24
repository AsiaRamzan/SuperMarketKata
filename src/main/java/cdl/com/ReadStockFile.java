package cdl.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;



public class ReadStockFile {
	public static TreeMap<String, Double> itemPriceMap = new TreeMap<String, Double>();
	public static TreeMap<String, ItemOffer> itemOfferMap = new TreeMap<String, ItemOffer>();
	
	static String item;
	static Double price;
	static int quantity;
	static Double offerPrice;
	
	public static void readFile() throws FileNotFoundException {int line=0;boolean offer=false;
		File file = new File("testData.txt"); 
		Scanner sc = new Scanner(file); 
		while (sc.hasNextLine()) { 
			String str=sc.nextLine();line++;
		    //ignore first line 
		    if(line!=1) {
			      String[] stringToSplit = str.split("\t");
			      item=stringToSplit[0];
			      price=Double.parseDouble(stringToSplit[1]);
			      if(stringToSplit.length==3) {offer=true;
			    	  String furtherSplit[] = stringToSplit[2].split(" ");
			    	  quantity=Integer.parseInt(furtherSplit[0]);
				      offerPrice=Double.parseDouble(furtherSplit[2]);
			      }
			      if(offer==true) {
			    	  itemOfferMap.put(item, new ItemOffer(item, quantity, offerPrice));
			    	  offer=false;
			      }
			      itemPriceMap.put(item, price);
			  }
		 }		
//	    ScanInput();
	   }
}
