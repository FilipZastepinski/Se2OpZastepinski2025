package reader;

import java.io.IOException;

public class ConcreteTxtReaderCreator extends ReaderCreator {

	@Override
	public ReaderProduct factoryMethod() throws IOException {
		return new ConcreteTxtReaderProduct();
	}

}
