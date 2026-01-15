package gui.guiFahrzeuguebersicht;

import business.businessAuto.AutovermietungModel;
import business.businessMotorrad.Motorrad;
import business.businessMotorrad.MotorradModel;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class FahrzeuguebersichtView {

	private FahrzeuguebersichtControl fahrzeuguebersichtControl;
	private AutovermietungModel autovermietungModel;
	private MotorradModel motorradModel;
	// ---Anfang Attribute der grafischen Oberflaeche---
	private Pane pane = new Pane();
	
	private Label lblAnzeigeAutos = new Label("Anzeige Autos");
	TextArea txtAnzeigeAutos = new TextArea();
	private Button btnAnzeigeAutos = new Button("Anzeige");
	
	private Label lblAnzeigeMotorraeder = new Label("Anzeige Motorraeder");
	TextArea txtAnzeigeMotoraeder = new TextArea();
	private Button btnAnzeigeMotoraeder = new Button("csv-Import und Anzeige");
	
	
	// -------Ende Attribute der grafischen Oberflaeche-------

	public FahrzeuguebersichtView(FahrzeuguebersichtControl fahrzeuguebersichtControl, Stage primaryStage,
			AutovermietungModel autovermietungModel, MotorradModel motorradModel) {
		Scene scene = new Scene(this.pane, 560, 340);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Anzeige von Fahrzeugen");
		primaryStage.show();
		this.fahrzeuguebersichtControl = fahrzeuguebersichtControl;
		this.autovermietungModel = autovermietungModel;
		this.motorradModel = motorradModel;
		this.initKomponenten();
		this.initListener();
		
		this.initKomponentenMotorrad();
		this.initListenerMotorrad();
	}

	private void initKomponenten() {
		// Label
		Font font = new Font("Arial", 20);
		lblAnzeigeAutos.setLayoutX(310);
		lblAnzeigeAutos.setLayoutY(40);
		lblAnzeigeAutos.setFont(font);
		lblAnzeigeAutos.setStyle("-fx-font-weight: bold;");
		pane.getChildren().add(lblAnzeigeAutos);
		// Textbereich
		txtAnzeigeAutos.setEditable(false);
		txtAnzeigeAutos.setLayoutX(310);
		txtAnzeigeAutos.setLayoutY(90);
		txtAnzeigeAutos.setPrefWidth(220);
		txtAnzeigeAutos.setPrefHeight(185);
		pane.getChildren().add(txtAnzeigeAutos);
		// Button
		btnAnzeigeAutos.setLayoutX(310);
		btnAnzeigeAutos.setLayoutY(290);
		pane.getChildren().add(btnAnzeigeAutos);
	}
	
	private void initKomponentenMotorrad() {
		// Label
				Font font = new Font("Arial", 20);
				lblAnzeigeMotorraeder.setLayoutX(30);
				lblAnzeigeMotorraeder.setLayoutY(40);
				lblAnzeigeMotorraeder.setFont(font);
				lblAnzeigeMotorraeder.setStyle("-fx-font-weight: bold;");
				pane.getChildren().add(lblAnzeigeMotorraeder);
				// Textbereich
				txtAnzeigeMotoraeder.setEditable(false);
				txtAnzeigeMotoraeder.setLayoutX(30);
				txtAnzeigeMotoraeder.setLayoutY(90);
				txtAnzeigeMotoraeder.setPrefWidth(220);
				txtAnzeigeMotoraeder.setPrefHeight(185);
				pane.getChildren().add(txtAnzeigeMotoraeder);
				// Button
				btnAnzeigeMotoraeder.setLayoutX(30);
				btnAnzeigeMotoraeder.setLayoutY(290);
				pane.getChildren().add(btnAnzeigeMotoraeder);
	}

	private void initListener() {
		btnAnzeigeAutos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				zeigeAutosAn();
			}
		});
	}
	
	private void initListenerMotorrad() {
		btnAnzeigeMotoraeder.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				zeigeMotorraederAn();
			}
		});
	}

	public void zeigeAutosAn() {
		if (!autovermietungModel.getAutos().isEmpty()) {
			txtAnzeigeAutos.setText(autovermietungModel.zeigeAutosAn());
		} else {
			zeigeInformationsfensterAn("Bisher wurde kein Auto aufgenommen!");
		}
	}

	private void zeigeMotorraederAn() {
		fahrzeuguebersichtControl.leseMotorraederAusCsvDatei();
		if (motorradModel.getMotorrad().size() > 0) {
			StringBuffer text = new StringBuffer();
			for (Motorrad sh : motorradModel.getMotorrad()) {
				text.append(sh.gibMotorradZurueck(' ') + "\n");
			}
			this.txtAnzeigeMotoraeder.setText(text.toString());
		} else {
			zeigeInformationsfensterAn("Es gibt keine Motorraeder in der csv-Datei!");
		}
	}

	private void zeigeInformationsfensterAn(String meldung) {
		new MeldungsfensterAnzeiger(AlertType.INFORMATION, "Information", meldung).zeigeMeldungsfensterAn();
	}

	void zeigeFehlermeldungsfensterAn(String meldung) {
		new MeldungsfensterAnzeiger(AlertType.ERROR, "Fehler", meldung).zeigeMeldungsfensterAn();
	}

}
