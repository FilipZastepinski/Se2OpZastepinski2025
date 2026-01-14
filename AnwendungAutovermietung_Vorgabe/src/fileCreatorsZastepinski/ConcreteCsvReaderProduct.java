package fileCreatorsZastepinski;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConcreteCsvReaderProduct extends ReaderProductZastepinski {
	
	BufferedReader ein;
	
	public ConcreteCsvReaderProduct() throws IOException {
		ein = new BufferedReader(new FileReader("Auto.csv"));
	}

	@Override
	public String[] leseAusDatei() throws IOException {
		
		String zeile;
		String[] ergebnisZeile;

		try {
			zeile = this.ein.readLine();
			
			if(zeile == null) {
				return null;
			}
			
			ergebnisZeile = zeile.split(";");
			
		} catch (IOException e) {
			throw new IOException("Fehler beim Lesen der Datei");
		}
		
		System.out.println("Csv leseAusDatei ausgabe ergebnisZeile: " + 
		" " + ergebnisZeile[0].toString() + 
		" " + ergebnisZeile[1].toString() +
		" " + ergebnisZeile[2].toString() +
		" " + ergebnisZeile[3].toString() +
		" " + ergebnisZeile[4].toString() );
		
		return ergebnisZeile;
		
	}

	@Override
	public void schliesseDatei() throws IOException {
		ein.close();
		
	}

}
