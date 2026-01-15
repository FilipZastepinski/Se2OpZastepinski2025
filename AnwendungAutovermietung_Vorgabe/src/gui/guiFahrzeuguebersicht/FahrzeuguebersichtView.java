package gui.guiFahrzeuguebersicht;

import java.io.IOException;

import business.businessAutovermietung.AutovermietungModel;
import business.businessFahrradverkauf.Fahrrad;
import business.businessFahrradverkauf.FahrradModel;
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
	private FahrradModel fahrradverkaufModel;
	
	// ---Anfang Attribute der grafischen Oberflaeche---
	private Pane pane = new Pane();
	
	private Label lblAnzeigeAutos = new Label("Anzeige Autos");
	TextArea txtAnzeigeAutos = new TextArea();
	private Button btnAnzeigeAutos = new Button("Anzeige Autos");
	
	private Label lblAnzeigeFahrrad = new Label("Anzeige Fahrraeder");
	TextArea txtAnzeigeFahrrad = new TextArea();
	private Button btnAnzeigeFahrrad = new Button("Anzeige Fahrraeder");
	// -------Ende Attribute der grafischen Oberflaeche-------

	public FahrzeuguebersichtView(FahrzeuguebersichtControl FahrzeuguebersichtControl, Stage primaryStage,
			AutovermietungModel autovermietungModel, FahrradModel fahrradverkaufModel) {
		Scene scene = new Scene(this.pane, 560, 340);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Anzeige von Fahrzeugen");
		primaryStage.show();
		
		this.fahrzeuguebersichtControl = fahrzeuguebersichtControl;
		this.autovermietungModel = autovermietungModel;
		this.fahrradverkaufModel = fahrradverkaufModel;
		
		this.initKomponentenAutos();
		this.initListenerAutos();
		
		this.initKomponentenFahrraeder();
		this.initListenerFahrraeder();
	}

	private void initKomponentenAutos() {
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

	private void initListenerAutos() {
		btnAnzeigeAutos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				zeigeAutosAn();
			}
		});
	}
	
	private void initKomponentenFahrraeder() {
		// Label
		Font font = new Font("Arial", 20);
		lblAnzeigeFahrrad.setLayoutX(20);
		lblAnzeigeFahrrad.setLayoutY(40);
		lblAnzeigeFahrrad.setFont(font);
		lblAnzeigeFahrrad.setStyle("-fx-font-weight: bold;");
		pane.getChildren().add(lblAnzeigeFahrrad);
		// Textbereich	
		txtAnzeigeFahrrad.setEditable(false);
		txtAnzeigeFahrrad.setLayoutX(20);
		txtAnzeigeFahrrad.setLayoutY(90);
		txtAnzeigeFahrrad.setPrefWidth(220);
		txtAnzeigeFahrrad.setPrefHeight(185);
		pane.getChildren().add(txtAnzeigeFahrrad);
		// Button
		btnAnzeigeFahrrad.setLayoutX(20);
		btnAnzeigeFahrrad.setLayoutY(290);
		pane.getChildren().add(btnAnzeigeFahrrad);
	}

	private void initListenerFahrraeder() {
		btnAnzeigeFahrrad.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				zeigeFahrraederAn();
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

	public void zeigeFahrraederAn() {
		try {
			fahrradverkaufModel.leseFahrradAusCsvDatei();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (fahrradverkaufModel.getFahrrad().size() > 0) {
			StringBuffer text = new StringBuffer();
			for (Fahrrad fahrrad : fahrradverkaufModel.getFahrrad()) {
				text.append(fahrrad.gibFahrradZurueck(' ') + "\n");
			}
			txtAnzeigeFahrrad.setText(text.toString());
		} else {
			zeigeInformationsfensterAn("Bisher wurde kein Fahrrad aufgenommen!");
		}
	}

	private void zeigeInformationsfensterAn(String meldung) {
		new MeldungsfensterAnzeiger(AlertType.INFORMATION, "Information", meldung).zeigeMeldungsfensterAn();
	}
	
	public void zeigeFehlermeldungsfensterAn(String meldung) {
		new MeldungsfensterAnzeiger(AlertType.ERROR, "Fehler", meldung).zeigeMeldungsfensterAn();
	}

}
