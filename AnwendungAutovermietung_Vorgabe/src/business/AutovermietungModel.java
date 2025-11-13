package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import reader.ConcreteCsvReaderCreator;
import reader.ConcreteTxtReaderCreator;
import reader.ReaderCreator;
import reader.ReaderProduct;

public class AutovermietungModel {

	// speichert temporaer ein Objekt vom Typ Auto
	public Auto auto;

	public AutovermietungModel() {

	}

	public void nehmeAutoAuf(String kennzeichen, String typ, String modell, float tagespreis,
			String[] vermietetVonBis) {
		this.auto = new Auto(kennzeichen, typ, modell, tagespreis, vermietetVonBis);
	}

	public String zeigeAutosAn() {
		return this.auto.gibAutoZurueck(' ');
	}

	public void leseAusDatei(String typ) throws IOException {
		if ("csv".equals(typ)) {
			ReaderCreator readerCreator = new ConcreteCsvReaderCreator();
			ReaderProduct readerProduct = readerCreator.factoryMethod();

			String[] zeile = readerProduct.leseAusDatei();

			this.auto = new Auto(
					zeile[0],
					zeile[1],
					zeile[2], Float.parseFloat(zeile[3]),
					zeile[4].split("_"));

			readerProduct.schließeDatei();

		} else { // Txt-Datei Lesen
			ReaderCreator readerCreator = new ConcreteTxtReaderCreator();
			ReaderProduct readerProduct = readerCreator.factoryMethod();
			
			String[] zeile = readerProduct.leseAusDatei();
			
			this.auto = new Auto(
					zeile[0],
					zeile[1],
					zeile[2], Float.parseFloat(zeile[3]),
					zeile[4].split("_"));

			readerProduct.schließeDatei();
		}
	}
	
	// Alternative
	
	/*	public void leseAusDatei(String typ) throws IOException {
		String[] daten;
	    
	    if ("txt".equals(typ)) {
	        daten = leseAutovermietungDateiAusTxt();
	    } else if ("csv".equals(typ)) {
	        daten = leseAutovermietungDatenAusCsv();
	    } else {
	        throw new IllegalArgumentException("Unbekannter Dateityp: " + typ);
	    }

	    // Überprüfen, ob Daten erfolgreich gelesen wurden
	    if (daten != null && daten.length >= 5) {
	        // Initialisieren des Autovermietung-Objekts mit den gelesenen Daten
	       		this.auto = new Auto(
					zeile[0],
					zeile[1],
					zeile[2], Float.parseFloat(zeile[3]),
					zeile[4].split("_"));
	        );
	    } else {
	        throw new IOException("Fehler beim Lesen der Daten aus der Datei");
	    }
		
	}
	
	//Ergänzen der Methode leseAutovermietungDatenAusCsv
	public String[] leseAutovermietungDatenAusCsv() throws IOException {
		ReaderCreator creator = new ConcreteReaderCreator();
		ReaderProduct reader = creator.factoryMethod("csv");
		String [] aus = reader.leseAusDatei();
		reader.schließeReader();
		return aus;
	}
	
	//Ergänzen der Methode leseAutovermietungDatenAusTxt
	public String[] leseAutovermietungDateiAusTxt() throws IOException {
		ReaderCreator creator = new ConcreteReaderCreator();
		ReaderProduct reader = creator.factoryMethod("txt");
		String [] aus = reader.leseAusDatei();
		reader.schließeReader();
		return aus;
	}
	
	*/
	
	

	public void schreibeAutoInCsvDatei() throws IOException {
		BufferedWriter aus = new BufferedWriter(new FileWriter("AutosAusgabe.csv", true));
		aus.write(auto.gibAutoZurueck(';'));
		aus.close();
	}

}
