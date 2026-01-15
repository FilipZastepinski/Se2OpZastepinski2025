package business.businessMotorrad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MotorradModel {

	// speichert temporaer ein Objekt vom Typ Auto
	// public Auto auto;

	// Liste aller Autos
	public ArrayList<Motorrad> motorrad = new ArrayList<>();

	public ArrayList<Motorrad> getMotorrad() {
		return motorrad;
	}

	// Singleton Pattern
	public static MotorradModel motorradmodel = null;

	private MotorradModel() {
	}

	public static MotorradModel getInstance() {
		if (motorradmodel == null) {
			motorradmodel = new MotorradModel();
		}
		return motorradmodel;
	}

	public void leseMotorradAusCsvDatei() throws IOException {
		BufferedReader ein = new BufferedReader(new FileReader("Motorrad.csv"));
		ArrayList<Motorrad> ergebnis = new ArrayList<>();
		String zeileStr = ein.readLine();
		while (zeileStr != null) {
			String[] zeile = zeileStr.split(";");
			ergebnis.add(new Motorrad(zeile[0], zeile[1], zeile[2]));
			zeileStr = ein.readLine();
		}
		ein.close();
		this.motorrad = ergebnis;
	}

}