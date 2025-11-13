package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConcreteTxtReaderProduct extends ReaderProduct {

	BufferedReader ein;

	public ConcreteTxtReaderProduct() throws IOException {
		ein = new BufferedReader(new FileReader("Auto.txt"));
	}

	@Override
	public String[] leseAusDatei() throws IOException {
		String[] arr = new String[5];

		String zeile = this.ein.readLine();

		for (int i = 0; i < arr.length; i++) {
			arr[i] = zeile;
			zeile = this.ein.readLine();

		}

		return arr;

	}

	@Override
	public void schließeDatei() throws IOException {
		ein.close();
	}

}
