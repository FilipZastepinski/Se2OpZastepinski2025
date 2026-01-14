package gui.guiAutovermietung;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import business.Auto;
import business.AutovermietungModel;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ownUtil.Observer;

public class AutovermietungControl implements Observer {
	
	private AutovermietungModel anwModel;
	private AutovermietungView anwView;

	public AutovermietungControl(Stage primaryStage) {
		
		// Model abgekappselt
		//this.anwModel = new AutovermietungModel();
		this.anwModel = AutovermietungModel.getInstance();
		// View greift auf Control und Model zu
		this.anwView = new AutovermietungView(this,primaryStage,anwModel);
		
		// Observer zur Liste hinzufuegen
		this.anwModel.addObserver(this);
		
	}
	
	void nehmeAutoAuf(){
    	try{ 
    		anwModel.nehmeAutoAuf(anwView.txtKennzeichen.getText(),
    				anwView.txtTyp.getText(),
    				anwView.txtModell.getText(),
    				Float.parseFloat(anwView.txtTagespreis.getText()),
    				anwView.txtVermietetVonBis.getText().split(";"));
    		anwView.zeigeInformationsfensterAn("Das Auto wurde aufgenommen!");
       	}
       	catch(Exception exc){
       		anwView.zeigeFehlermeldungsfensterAn(exc.getMessage());
     	}
    	this.anwModel.notifyObservers();
    	
    }
	

    void zeigeAutosAn(){
    	// Pruefe ob NICHT leer ist
    	if(!anwModel.getAutos().isEmpty()){
    		anwModel.zeigeAutosAn();
    		anwView.txtAnzeige.setText(anwModel.zeigeAutosAn());
    	}
    	else{
    		anwView.zeigeInformationsfensterAn("Bisher wurde kein Auto aufgenommen!");
    	}
    }    
		  
    void leseAusDatei(String typ){
    	try {
    		if ("csv".equals(typ)) {
    			//anwModel.leseAusDatei(typ);
    			anwModel.leseAutovermietungAusCsvDatei();
    				anwView.zeigeInformationsfensterAn(
      	  	   		"Das Auto wurde gelesen aus der "+ typ + "!");
    		}else if("txt".equals(typ)) {
    			//anwModel.leseAusDatei(typ);
    			anwModel.leseAutovermietungAusTxtDatei();
    				anwView.zeigeInformationsfensterAn(
          	  	   		"Das Auto wurde gelesen aus der "+ typ + "!");
    		}else {
    			anwView.zeigeFehlermeldungsfensterAn(typ + " entspricht nicht csv oder txt");
    		}
    		// Wird schon in anwModel.leseAutovermietungAusCsv/TxtDatei ausgefuehrt
    		//this.anwModel.notifyObservers();
      	  		
		}
		catch(IOException exc){
			anwView.zeigeFehlermeldungsfensterAn(
				"IOException beim Lesen!");
		}
		catch(Exception exc){
			exc.printStackTrace();
		    anwView.zeigeFehlermeldungsfensterAn(
		        "Unbekannter Fehler beim Lesen: " + exc.getClass().getSimpleName()
		        + " - " + exc.getMessage()
		    );
		}
	}
		
	void schreibeAutoInCsvDatei() {
		try {
			anwModel.schreibeAutoInCsvDatei();
   			anwView.zeigeInformationsfensterAn(
	   			"Die Autos wurden gespeichert!");
		}	
		catch(IOException exc){
			anwView.zeigeFehlermeldungsfensterAn(
				"IOException beim Speichern!");
		}
		catch(Exception exc){
			anwView.zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Speichern!");
		}
	}

	@Override
	public void update() {
		// Pruefe ob NICHT leer ist
		if(!anwModel.getAutos().isEmpty()){
    		anwView.txtAnzeige.setText(anwModel.zeigeAutosAn());
    	}
	}

}
