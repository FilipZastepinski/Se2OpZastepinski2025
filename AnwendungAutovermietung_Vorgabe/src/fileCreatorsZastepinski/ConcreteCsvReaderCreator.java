package fileCreatorsZastepinski;

import java.io.IOException;

public class ConcreteCsvReaderCreator extends ReaderCreatorZastepinski {

	@Override
	public ReaderProductZastepinski factoryMethod() throws IOException {
		return new ConcreteCsvReaderProduct();
	}

}
