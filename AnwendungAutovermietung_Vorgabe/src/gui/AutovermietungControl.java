package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import business.Auto;
import business.AutovermietungModel;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AutovermietungControl {
	
	private AutovermietungModel anwModel;
	private AutovermietungView anwView;

	public AutovermietungControl(Stage primaryStage) {
		
		// Model abgekappselt
		this.anwModel = new AutovermietungModel();
		// View greift auf Control und Model zu
		this.anwView = new AutovermietungView(this,primaryStage,anwModel);
		
	}
	
	void nehmeAutoAuf(){
    	try{ 
    		anwModel.nehmeAutoAuf(anwView.txtKennzeichen.getText(), anwView.txtTyp.getText(), anwView.txtModell.getText(), Float.parseFloat(anwView.txtTagespreis.getText()),
    				anwView.txtVermietetVonBis.getText().split(";"));
    		anwView.zeigeInformationsfensterAn("Das Auto wurde aufgenommen!");
       	}
       	catch(Exception exc){
       		anwView.zeigeFehlermeldungsfensterAn(exc.getMessage());
     	}
    }
	

    void zeigeAutosAn(){
    	if(anwModel.auto != null){
    		anwModel.zeigeAutosAn();
    		anwView.txtAnzeige.setText(anwModel.auto.gibAutoZurueck(' '));
    	}
    	else{
    		anwView.zeigeInformationsfensterAn("Bisher wurde kein Auto aufgenommen!");
    	}
    }    
		  
    void leseAusDatei(String typ){
    	try {
      		anwModel.leseAusDatei(typ);
      		
      		if("csv".equals(typ)){
      	  		anwView.zeigeInformationsfensterAn(
      	  	   			"Das Auto wurde gelesen aus csv");
      		}else if("txt".equals(typ)){
      			anwView.zeigeInformationsfensterAn(
      	  	   			"Das Auto wurde gelesen aus txt");
      		} else {
      			anwView.zeigeInformationsfensterAn(
      	  	   			"Fehler beim Lesen der Daten!");
      		}
       
		}
		catch(IOException exc){
			anwView.zeigeFehlermeldungsfensterAn(
				"IOException beim Lesen!");
		}
		catch(Exception exc){
			anwView.zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Lesen!");
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

}
