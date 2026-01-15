package business.businessMotorrad;

public class Motorrad {
	
	String kennzeichen;
	String aufbauart;
	int monatspreis;
	
	public Motorrad(String kennzeichen, String aufbauart, String monatspreis) {
		super();
		this.kennzeichen = kennzeichen;
		this.aufbauart = aufbauart;
		this.monatspreis = Integer.parseInt(monatspreis);
	}
	
	public String gibMotorradZurueck(char trenner){
  		return this.getKennzeichen() + trenner 
  			+ this.getAufbauart() + trenner
  		    + this.getMonatspreis();
   	}

	public String getKennzeichen() {
		return kennzeichen;
	}

	public void setKennzeichen(String kennzeichen) {
		this.kennzeichen = kennzeichen;
	}

	public String getAufbauart() {
		return aufbauart;
	}

	public void setAufbauart(String aufbauart) {
		this.aufbauart = aufbauart;
	}

	public int getMonatspreis() {
		return monatspreis;
	}

	public void setMonatspreis(int monatspreis) {
		this.monatspreis = monatspreis;
	}	
}
