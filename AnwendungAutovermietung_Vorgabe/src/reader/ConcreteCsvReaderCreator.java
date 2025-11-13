package reader;

import java.io.IOException;

public class ConcreteCsvReaderCreator extends ReaderCreator {

	@Override
	public ReaderProduct factoryMethod() throws IOException {
		return new ConcreteCsvReaderProduct();
	}

}
