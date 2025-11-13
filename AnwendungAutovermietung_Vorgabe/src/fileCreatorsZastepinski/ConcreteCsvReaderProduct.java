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
		
		String[] ergebnisZeile;

		try {
			ergebnisZeile = this.ein.readLine().split(";");
		} catch (IOException e) {
			throw new IOException("Fehler beim Lesen der Datei");
		}

		return ergebnisZeile;
	}

	@Override
	public void schliesseDatei() throws IOException {
		ein.close();
		
	}

}
