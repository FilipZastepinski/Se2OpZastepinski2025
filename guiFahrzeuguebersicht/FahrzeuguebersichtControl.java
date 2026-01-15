package gui.guiFahrzeuguebersicht;

import java.io.IOException;

import business.businessAuto.AutovermietungModel;
import business.businessMotorrad.*;
import javafx.stage.Stage;
import ownUtil.Observer;

public class FahrzeuguebersichtControl implements Observer {
	private FahrzeuguebersichtView fahrzeuguebersichtView;
	private AutovermietungModel autovermietungModel;
	private MotorradModel motorradModel;

	public FahrzeuguebersichtControl(Stage primaryStage) {
		// this.autovermietungModel = new AutovermietungModel();
		this.autovermietungModel = AutovermietungModel.getInstance();
		this.motorradModel = MotorradModel.getInstance();
		this.fahrzeuguebersichtView = new FahrzeuguebersichtView(this, primaryStage, autovermietungModel, motorradModel);
		this.autovermietungModel.addObserver(this);
	}

	@Override
	public void update() {
		if (!autovermietungModel.getAutos().isEmpty()) {
			this.fahrzeuguebersichtView.txtAnzeigeAutos.setText(autovermietungModel.zeigeAutosAn());
		}

	}

	public void leseMotorraederAusCsvDatei() {
		try {
			this.motorradModel.leseMotorradAusCsvDatei();
		} catch (IOException exc) {
			this.fahrzeuguebersichtView.zeigeFehlermeldungsfensterAn("IOException beim Lesen von Motorraedern!");
		} catch (Exception exc) {
			this.fahrzeuguebersichtView.zeigeFehlermeldungsfensterAn("Unbekannter Fehler beim Lesen von " + " Motorraedern!");
			System.out.println(exc);
		}
	}

}
