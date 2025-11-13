package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConcreteCsvReaderProduct extends ReaderProduct {

	BufferedReader ein;

	public ConcreteCsvReaderProduct() throws IOException {
		ein = new BufferedReader(new FileReader("Auto.csv"));
	}

	@Override
	public String[] leseAusDatei() throws IOException {
		String[] arr;

		try {
			arr = this.ein.readLine().split(";");
		} catch (IOException e) {
			throw new IOException("Fehler beim Lesen der Datei");
		}

		return arr;
	}

	@Override
	public void schließeDatei() throws IOException {
		ein.close();
	}

}
