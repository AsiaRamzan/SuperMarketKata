package cdl.com;

import java.io.FileNotFoundException;




public class MainProgram {
	public static void main(String s[]) throws FileNotFoundException {
		ReadStockFile rsFile=new ReadStockFile();
		ScanInput scInput = new ScanInput();
		System.out.println("............................................"
				+"\nWelcome to ABC supermarket checkout system"
				+ "\n............................................"
				+ "\n\nStart scan... ");
		rsFile.readFile();
		scInput.getScanInput();

	}
}
