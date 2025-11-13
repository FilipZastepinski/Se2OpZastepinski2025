package fileCreatorsZastepinski;

import java.io.IOException;

public abstract class ReaderProductZastepinski {

	public abstract String[] leseAusDatei() throws IOException;
	
	public abstract void schliesseDatei() throws IOException;
}
