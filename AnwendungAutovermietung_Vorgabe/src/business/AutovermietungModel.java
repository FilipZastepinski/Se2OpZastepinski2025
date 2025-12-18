package business;

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
	public ArrayList<Auto> auto = new ArrayList<Auto>();
	
	public ArrayList<Auto> getAuto() {
		return auto;
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
		this.auto.add(new Auto(kennzeichen, typ, modell, tagespreis, vermietetVonBis));
	}
	
	public void addAuto(Auto auto) {
		this.auto.add(auto);
	}

	public String zeigeAutosAn() {
		String aus = null;
			for(Auto a : auto) {
				aus += a.gibAutoZurueck(' ');
			}
			return aus;
	}

	public void leseAusDatei(String typ) throws IOException{
		
		String[] daten = null;
		
      		if("csv".equals(typ)){
      			daten = leseAutovermietungAusCsvDatei();
      			System.out.println("csv wird gelesen!");
      		}else if("txt".equals(typ)) {
      			daten = leseAutovermietungAusTxtDatei();
      			System.out.println("txt wird gelesen!");
      		}
      		
      	// Überprüfen, ob Daten erfolgreich gelesen wurden
    		if (daten != null && daten.length >= 5) {
    			// Initialisieren des Getraenkemarkt-Objekts mit den gelesenen Daten
    			this.auto.add(new Auto(daten[0], daten[1], daten[2],
    					Float.parseFloat(daten[4]), daten[5].split("_")));
    		} else {
    			throw new IOException("Fehler beim Lesen der Daten aus der Datei");
    		}
      		
	}

	public void schreibeAutoInCsvDatei() throws IOException {
		BufferedWriter aus = new BufferedWriter(new FileWriter("AutosAusgabe.csv", true));
		aus.write(auto.get(0).gibAutoZurueck(';'));
		aus.close();
	}
	
	public String[] leseAutovermietungAusCsvDatei() throws IOException{
		
		ReaderCreatorZastepinski creator = new ConcreteCsvReaderCreator();
		ReaderProductZastepinski reader = creator.factoryMethod();
		String[] aus = reader.leseAusDatei();
		reader.schliesseDatei();
		return aus;
		
	}
	
	public String[] leseAutovermietungAusTxtDatei() throws IOException{
			
		ReaderCreatorZastepinski creator = new ConcreteTxtReaderCreator();
		ReaderProductZastepinski reader = creator.factoryMethod();
		String[] aus = reader.leseAusDatei();
		reader.schliesseDatei();
		return aus;
			
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
