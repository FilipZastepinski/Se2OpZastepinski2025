package fileCreatorsZastepinski;

import java.io.IOException;

public class ConcreteTxtReaderCreator extends ReaderCreatorZastepinski {

	@Override
	public ReaderProductZastepinski factoryMethod() throws IOException {
		return new ConcreteTxtReaderProduct();
	}

}
