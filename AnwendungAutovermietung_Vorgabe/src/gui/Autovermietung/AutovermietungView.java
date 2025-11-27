package gui.Autovermietung;

import business.AutovermietungModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.MeldungsfensterAnzeiger;

public class AutovermietungView {
	
	private AutovermietungControl anwControl;
	private AutovermietungModel anwModel;
	
	public AutovermietungView(AutovermietungControl anwControl, Stage primaryStage,AutovermietungModel anwModel) {
		this.anwControl = anwControl;
		this.anwModel = anwModel;
		
		Scene scene = new Scene(this.pane, 700, 340);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Verwaltung einer Autovermietung");
    	primaryStage.show();
    	this.initKomponenten();
		this.initListener();
	}
	
	//---Anfang Attribute der grafischen Oberflaeche---
	 Pane pane     					= new  Pane();
	 Label lblEingabe    	 		= new Label("Eingabe");
	 Label lblAnzeige   	 	    	= new Label("Anzeige");
	 Label lblKennzeichen 			= new Label("Kennzeichen:");
	 Label lblTyp   		 			= new Label("Typ:");
	 Label lblModell  	 			= new Label("Modell:");
	 Label lblTagespreis   			= new Label("Tagespreis:");
	 Label lblVermietetVonBis  		= new Label("Vermietet von-bis:");
	TextField txtKennzeichen 	 	= new TextField();
	 TextField txtTyp				= new TextField();
	 TextField txtModell				= new TextField();
	 TextField txtTagespreis			= new TextField();
	 TextField txtVermietetVonBis		= new TextField();
	 TextArea txtAnzeige  			= new TextArea();
	 Button btnEingabe 		 		= new Button("Eingabe");
	 Button btnAnzeige 		 		= new Button("Anzeige");
	 MenuBar mnbrMenuLeiste  		= new MenuBar();
	 Menu mnDatei             		= new Menu("Datei");
	 MenuItem mnItmCsvImport 		= new MenuItem("csv-Import");
	 MenuItem mnItmTxtImport 		= new MenuItem("txt-Import");
	 MenuItem mnItmCsvExport 		= new MenuItem("csv-Export");    
	//-------Ende Attribute der grafischen Oberflaeche-------
	
	void initKomponenten(){
       	// Labels
    	lblEingabe.setLayoutX(20);
    	lblEingabe.setLayoutY(40);
    	Font font = new Font("Arial", 24); 
    	lblEingabe.setFont(font);
    	lblEingabe.setStyle("-fx-font-weight: bold;"); 
    	lblAnzeige.setLayoutX(400);
    	lblAnzeige.setLayoutY(40);
      	lblAnzeige.setFont(font);
       	lblAnzeige.setStyle("-fx-font-weight: bold;"); 
       	lblKennzeichen.setLayoutX(20);
    	lblKennzeichen.setLayoutY(90);
    	lblTyp.setLayoutX(20);
    	lblTyp.setLayoutY(130);
    	lblModell.setLayoutX(20);
    	lblModell.setLayoutY(170);
    	lblTagespreis.setLayoutX(20);
    	lblTagespreis.setLayoutY(210);
    	lblVermietetVonBis.setLayoutX(20);
    	lblVermietetVonBis.setLayoutY(250);    	
       	pane.getChildren().addAll(lblEingabe, lblAnzeige, 
       		lblKennzeichen, lblTyp, lblModell,
       		lblTagespreis, lblVermietetVonBis);
    
    	// Textfelder
     	txtKennzeichen.setLayoutX(170);
    	txtKennzeichen.setLayoutY(90);
    	txtKennzeichen.setPrefWidth(200);
    	txtTyp.setLayoutX(170);
    	txtTyp.setLayoutY(130);
    	txtTyp.setPrefWidth(200);
    	txtModell.setLayoutX(170);
    	txtModell.setLayoutY(170);
    	txtModell.setPrefWidth(200);
      	txtTagespreis.setLayoutX(170);
    	txtTagespreis.setLayoutY(210);
    	txtTagespreis.setPrefWidth(200);
    	txtVermietetVonBis.setLayoutX(170);
    	txtVermietetVonBis.setLayoutY(250);
    	txtVermietetVonBis.setPrefWidth(200);
      	pane.getChildren().addAll( 
     		txtKennzeichen, txtTyp, txtModell,
     		txtTagespreis, txtVermietetVonBis);
     	
        // Textbereich	
        txtAnzeige.setEditable(false);
     	txtAnzeige.setLayoutX(400);
    	txtAnzeige.setLayoutY(90);
     	txtAnzeige.setPrefWidth(270);
    	txtAnzeige.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeige); 
       	
        // Buttons
        btnEingabe.setLayoutX(20);
        btnEingabe.setLayoutY(290);
        btnAnzeige.setLayoutX(400);
        btnAnzeige.setLayoutY(290);
        pane.getChildren().addAll(btnEingabe, btnAnzeige); 
        
 		// Menue
  	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
  	    this.mnDatei.getItems().add(mnItmCsvImport);
  	    this.mnDatei.getItems().add(mnItmTxtImport);
  	    this.mnDatei.getItems().add(new SeparatorMenuItem());
  	    this.mnDatei.getItems().add(mnItmCsvExport);
 	    pane.getChildren().add(mnbrMenuLeiste);
   }
   
    void initListener() {
	    btnEingabe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
        	    anwControl.nehmeAutoAuf();
            }
	    });
	    btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	    		anwControl.zeigeAutosAn();
	        } 
   	    });
	    mnItmCsvImport.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	    		anwControl.leseAusDatei("csv");
	    	}
	    });
	    mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	anwControl.leseAusDatei("txt");
		    }
    	});
	    mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				anwControl.schreibeAutoInCsvDatei();
			}	
	    });
    }
    
    void zeigeInformationsfensterAn(String meldung){
    	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
    		"Information", meldung).zeigeMeldungsfensterAn();
    }	
    
    void zeigeFehlermeldungsfensterAn(String meldung){
       	new MeldungsfensterAnzeiger(AlertType.ERROR,
        	"Fehler", meldung).zeigeMeldungsfensterAn();
    }

}
