package gui.guiFahrzeuguebersicht;

import java.io.IOException;

import business.businessAutovermietung.AutovermietungModel;
import business.businessFahrradverkauf.FahrradModel;
import javafx.stage.Stage;
import ownUtil.Observer;

public class FahrzeuguebersichtControl implements Observer {
	private FahrzeuguebersichtView fahrzeuguebersichtView;
	private AutovermietungModel autovermietungModel;
	private FahrradModel fahrradModel;

	public FahrzeuguebersichtControl(Stage primaryStage) {
		// this.autovermietungModel = new AutovermietungModel();
		this.autovermietungModel = AutovermietungModel.getInstance();
		this.fahrradModel = FahrradModel.getInstance();
		this.fahrzeuguebersichtView = new FahrzeuguebersichtView(this, primaryStage, autovermietungModel, fahrradModel);
		this.autovermietungModel.addObserver(this);
		this.fahrradModel.addObserver(this);
	}
	
	public void leseFahrradAusCsvDatei() {
		try {
			this.fahrradModel.leseFahrradAusCsvDatei();
		} catch (IOException exc) {
			this.fahrzeuguebersichtView.zeigeFehlermeldungsfensterAn("IOException beim Lesen von Fahrrad!");
		} catch (Exception exc) {
			this.fahrzeuguebersichtView.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Lesen von Fahrrad");		
			exc.printStackTrace();
		}
	}
	
	@Override
	public void update() {
		if (!autovermietungModel.getAutos().isEmpty()) {
			this.fahrzeuguebersichtView.txtAnzeigeAutos.setText(
					autovermietungModel.zeigeAutosAn());
		}
		//if(!fahrradModel.getFahrrad().isEmpty()) {
		//	this.fahrzeuguebersichtView.txtAnzeigeFahrrad.setText(
		//			fahrradModel.zeigeFahrradAn());
		//}

	}
}
