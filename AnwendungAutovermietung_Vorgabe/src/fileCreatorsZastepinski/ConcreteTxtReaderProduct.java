package fileCreatorsZastepinski;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConcreteTxtReaderProduct extends ReaderProductZastepinski {

	BufferedReader ein;

	public ConcreteTxtReaderProduct() throws IOException {
		ein = new BufferedReader(new FileReader("Auto.txt"));
	}

	@Override
	public String[] leseAusDatei() throws IOException {
		String[] ergebnisZeile = new String[5];
		

		int i = 0;
		while(i < ergebnisZeile.length) {
			String zeile = this.ein.readLine();
			if(zeile == null) return null;
			//System.out.println("txt leseAusDatei ausgabe:" + zeile + " laenge: "+ ergebnisZeile.length);
			ergebnisZeile[i] = zeile;
			i++;
		}
		
		return ergebnisZeile;

	}

	@Override
	public void schliesseDatei() throws IOException {
		ein.close();
	}

}