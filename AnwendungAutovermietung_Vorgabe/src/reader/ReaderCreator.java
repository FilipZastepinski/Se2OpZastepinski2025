package reader;

import java.io.IOException;

public abstract class ReaderCreator {
	
	public abstract ReaderProduct factoryMethod() throws IOException;

}
