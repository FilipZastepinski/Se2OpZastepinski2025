package business.businessFahrradverkauf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import business.businessAutovermietung.Auto;
import fileCreatorsZastepinski.ConcreteCsvReaderCreator;
import fileCreatorsZastepinski.ReaderCreatorZastepinski;
import fileCreatorsZastepinski.ReaderProductZastepinski;
import ownUtil.Observable;
import ownUtil.Observer;

public class FahrradModel implements Observable {
	
	// Fahrraeder Liste
	private ArrayList<Fahrrad> fahrraeder = new ArrayList<>();
	
	// Singleton Pattern
	private static FahrradModel theInstance = null;

	private FahrradModel() {
	}
	
	public static FahrradModel getInstance() {
		if(theInstance == null) {
			theInstance = new FahrradModel();
		} 
		return theInstance;
	}
	
	/*public void leseFahrradAusCsvDatei() throws IOException{
		BufferedReader ein = new BufferedReader(new FileReader("Fahrraeder.csv"));

		String zeileStr;
		String[] zeile;
		
		while((zeileStr = ein.readLine()) != null) {
			
			zeile = zeileStr.split(";");
			Fahrrad fahrrad = new Fahrrad(
				zeile[0], 
		  		Integer.parseInt(zeile[1]), 
		  		zeile[2] 
		  	);
			addFahrrad(fahrrad);
		}
		
		ein.close();
		
		// addFahrrad ruft schon auf
		//notifyObservers();
	}*/
	
	public void leseFahrradAusCsvDatei() throws IOException{
		BufferedReader ein = new BufferedReader(new FileReader("Fahrraeder.csv"));

		ArrayList<Fahrrad> ergebnis = new ArrayList<>();
		String zeileStr = ein.readLine();
		
		while (zeileStr != null) {
			String[] zeile = zeileStr.split(";");
			ergebnis.add(new Fahrrad(zeile[0], Integer.parseInt(zeile[1]), zeile[2]));
			zeileStr = ein.readLine();
		}
		ein.close();
		this.fahrraeder = ergebnis;
		
	}
	
	/*public String zeigeFahrradAn() {
		StringBuilder sb = new StringBuilder();
		for(Fahrrad f: fahrraeder) {
			sb.append(f.gibFahrradZurueck(' '));
		}
		return sb.toString();
	}*/

	public ArrayList<Fahrrad> getFahrrad() {
		return fahrraeder;
	}
	
	/*public void addFahrrad(Fahrrad fahrrad) {
		this.fahrraeder.add(fahrrad);
		notifyObservers();
	}*/
	
	// Observer Pattern
	private ArrayList<Observer> observers = new ArrayList<>();
	
	@Override
	public void addObserver(Observer obs) {
		this.observers.add(obs);
	}

	@Override
	public void removeObserver(Observer obs) {
		this.observers.remove(obs);
	}

	@Override
	public void notifyObservers() {
		for(Observer obs: observers) {
			obs.update();
		}
		
	}

}
