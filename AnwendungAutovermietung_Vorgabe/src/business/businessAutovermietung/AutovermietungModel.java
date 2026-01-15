package business.businessAutovermietung;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fileCreatorsZastepinski.ConcreteCsvReaderCreator;
import fileCreatorsZastepinski.ConcreteTxtReaderCreator;
import fileCreatorsZastepinski.ReaderCreatorZastepinski;
import fileCreatorsZastepinski.ReaderProductZastepinski;
import ownUtil.Observable;
import ownUtil.Observer;

public class AutovermietungModel implements Observable {

	// speichert temporaer ein Objekt vom Typ Auto
	// public Auto auto;
	
	// Liste aller Autos
	public ArrayList<Auto> autos = new ArrayList<>();
	
	public ArrayList<Auto> getAutos() {
		return autos;
	}
	
	public void addAuto(Auto auto) {
		// Ergänzen
		if (auto == null) return;
		this.autos.add(auto);
		notifyObservers();
	}
	
	// Singleton Pattern
	public static AutovermietungModel autovermietungModel = null;

	private AutovermietungModel() {
	}
	
	public static AutovermietungModel getInstance() {
		if (autovermietungModel == null) {
			autovermietungModel = new AutovermietungModel();
		}
		return autovermietungModel;
	}

	public void nehmeAutoAuf(String kennzeichen, String typ, String modell, float tagespreis, String[] vermietetVonBis) {
		addAuto(new Auto(kennzeichen, typ, modell, tagespreis, vermietetVonBis));
	}

	// Wichtige Methode zum anzeigen von Autos
	public String zeigeAutosAn() {
		StringBuilder sb = new StringBuilder();
		for(Auto a : autos) {
			sb.append(a.gibAutoZurueck(' '));
		}
		return sb.toString();
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
		for(Auto auto : this.getAutos()) {
			aus.write(auto.gibAutoZurueck(';'));
		}
		//aus.write(autos.get(0).gibAutoZurueck(';')); // Schreibt nur das erste Auto in Csv
		aus.close();
	}
	
	public void leseAutovermietungAusCsvDatei() throws IOException{
		ReaderCreatorZastepinski creator = new ConcreteCsvReaderCreator();
		ReaderProductZastepinski reader = creator.factoryMethod();
		
		String[] zeile;
		
		while((zeile = reader.leseAusDatei()) != null) {
			Auto auto = new Auto(
				zeile[0], 
		  		zeile[1], 
		  		zeile[2], 
		  		Float.parseFloat(zeile[3]), 
		  		zeile[4].split("_")
		  	);
			addAuto(auto);
		}
		
		reader.schliesseDatei();
		
		// addAuto ruft schon auf
		//notifyObservers();
	}
	
	public void leseAutovermietungAusTxtDatei() throws IOException{
		ReaderCreatorZastepinski creator = new ConcreteTxtReaderCreator();
		ReaderProductZastepinski reader = creator.factoryMethod();
			
		String[] zeile;
		
		while((zeile = reader.leseAusDatei()) != null) {
			Auto auto = new Auto(
				zeile[0], 
		  		zeile[1], 
		  		zeile[2], 
		  		Float.parseFloat(zeile[3]), 
		  		zeile[4].split("_")
		  	);
			addAuto(auto);
		}
		
		reader.schliesseDatei();
		
		// addAuto ruft schon auf
		//notifyObservers();
	}
	
	// Liste in der Observer gespeichert werden
	public List<Observer> observers = new ArrayList<>();

	@Override
	public void addObserver(Observer obs) {
		if (observers.contains(obs)) return;
			observers.add(obs);
	}

	@Override
	public void removeObserver(Observer obs) {
		if(!observers.contains(obs)) return; 
			observers.remove(obs);
	}

	@Override
	public void notifyObservers() {
		for(Observer obs : observers) {
			obs.update();
		}
		
	}
	
}
