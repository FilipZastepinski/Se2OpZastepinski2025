package gui.guiFahrzeuguebersicht;

import business.AutovermietungModel;
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
	// ---Anfang Attribute der grafischen Oberflaeche---
	private Pane pane = new Pane();
	private Label lblAnzeigeAutos = new Label("Anzeige Autos");
	TextArea txtAnzeigeAutos = new TextArea();
	private Button btnAnzeigeAutos = new Button("Anzeige");
	// -------Ende Attribute der grafischen Oberflaeche-------

	public FahrzeuguebersichtView(FahrzeuguebersichtControl FahrzeuguebersichtControl, Stage primaryStage,
			AutovermietungModel autovermietungModel) {
		Scene scene = new Scene(this.pane, 560, 340);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Anzeige von Fahrzeugen");
		primaryStage.show();
		this.fahrzeuguebersichtControl = fahrzeuguebersichtControl;
		this.autovermietungModel = autovermietungModel;
		this.initKomponenten();
		this.initListener();
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

	private void initListener() {
		btnAnzeigeAutos.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				zeigeAutosAn();
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

	private void zeigeInformationsfensterAn(String meldung) {
		new MeldungsfensterAnzeiger(AlertType.INFORMATION, "Information", meldung).zeigeMeldungsfensterAn();
	}

}
