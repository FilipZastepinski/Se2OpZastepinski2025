package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import fileCreatorsZastepinski.ConcreteCsvReaderCreator;
import fileCreatorsZastepinski.ConcreteTxtReaderCreator;
import fileCreatorsZastepinski.ReaderCreatorZastepinski;
import fileCreatorsZastepinski.ReaderProductZastepinski;
import ownUtil.Observable;
import ownUtil.Observer;

public class AutovermietungModel implements Observable {

	// speichert temporaer ein Objekt vom Typ Auto
	public Auto auto;
	
	public Auto getAuto() {
		return auto;
	}
	
	// -------- Singleton start -------
	
	public static AutovermietungModel autovermietungModel;

	private AutovermietungModel() {

	}
	
	public static AutovermietungModel getInstance() {
		if (autovermietungModel == null) {
			autovermietungModel = new AutovermietungModel();
		}
		return autovermietungModel;
	}
	
	// -------- Singleton ende -------

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
		
		notifyObservers();
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
		
		notifyObservers();
	}
	
	// Liste aller Beobachter/ Observer
	public Vector<Observer> observers = new Vector<Observer>();
	
	@Override
	public void addObserver(Observer obs) {
		if(observers.contains(obs)) return;
		this.observers.addElement(obs);
	}

	@Override
	public void removeObserver(Observer obs) {
		if(!observers.contains(obs)) return;
		this.observers.removeElement(obs);	
	}

	@Override
	public void notifyObservers() {
		for(Observer obs : observers){
			obs.update();
		}
		
	}
	
	

}
