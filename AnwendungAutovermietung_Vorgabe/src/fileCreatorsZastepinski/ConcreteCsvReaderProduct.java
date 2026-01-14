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

		String zeile = ein.readLine();
		if (zeile == null) {
			return null;
		}

		return zeile.split(";");

	}

	@Override
	public void schliesseDatei() throws IOException {
		ein.close();

	}

}
