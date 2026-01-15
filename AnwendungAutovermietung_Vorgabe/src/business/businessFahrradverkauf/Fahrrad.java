package business.businessFahrradverkauf;

public class Fahrrad {

	private String name;
	private int preis;
	private String art;

	public Fahrrad(String name, int preis, String art) {
		super();
		this.name = name;
		this.preis = preis;
		this.art = art;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPreis() {
		return preis;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public String gibFahrradZurueck(char trenner) {
		return this.getName() + trenner 
				+ this.getPreis() + trenner 
				+ this.getArt() + trenner;
	}

}