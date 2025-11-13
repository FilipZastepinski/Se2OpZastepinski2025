package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import fileCreatorsZastepinski.ConcreteCsvReaderCreator;
import fileCreatorsZastepinski.ConcreteTxtReaderCreator;
import fileCreatorsZastepinski.ReaderCreatorZastepinski;
import fileCreatorsZastepinski.ReaderProductZastepinski;

public class AutovermietungModel {

	// speichert temporaer ein Objekt vom Typ Auto
	public Auto auto;

	public AutovermietungModel() {

	}

	public void nehmeAutoAuf(String kennzeichen, String typ, String modell, float tagespreis, String[] vermietetVonBis) {
		this.auto = new Auto(kennzeichen, typ, modell, tagespreis, vermietetVonBis);
	}

	public String zeigeAutosAn() {
		return this.auto.gibAutoZurueck(' ');
	}

	/*public void leseAusDatei(String typ) throws IOException{
      		if("csv".equals(typ)){
      			leseAutovermietungAusCsvDatei();
      			System.out.println("csv wird gelesen!");
      		}else if("txt".equals(typ)) {
      			leseAutovermietungAusTxtDatei();
      			System.out.println("txt wird gelesen!");
      		}		
      		
	}*/

	public void schreibeAutoInCsvDatei() throws IOException {
		BufferedWriter aus = new BufferedWriter(new FileWriter("AutosAusgabe.csv", true));
		aus.write(auto.gibAutoZurueck(';'));
		aus.close();
	}
	
	public void leseAutovermietungAusCsvDatei() throws IOException{
		
		ReaderCreatorZastepinski creator = new ConcreteCsvReaderCreator();
		
		ReaderProductZastepinski reader = creator.factoryMethod();
		
		String[] zeile = reader.leseAusDatei();
		this.auto 
			= new Auto(zeile[0], 
  			zeile[1], 
  			zeile[2], 
  			Float.parseFloat(zeile[3]), 
  			zeile[4].split("_"));
		reader.schliesseDatei();
	}
	
	public void leseAutovermietungAusTxtDatei() throws IOException{
			
			ReaderCreatorZastepinski creator = new ConcreteTxtReaderCreator();
			
			ReaderProductZastepinski reader = creator.factoryMethod();
			
			String[] zeile = reader.leseAusDatei();
			this.auto 
				= new Auto(zeile[0], 
	  			zeile[1], 
	  			zeile[2], 
	  			Float.parseFloat(zeile[3]), 
	  			zeile[4].split("_"));
			reader.schliesseDatei();
	}
	
	

}
