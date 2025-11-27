package gui.Autovermietung;

import java.io.IOException;
import business.AutovermietungModel;
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
		
		this.anwModel.addObserver(this);
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
    	
    	this.anwModel.notifyObservers();
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

	@Override
	public void update() {
		if(this.anwModel.getAuto() != null){
	   		anwView.txtAnzeige.setText(
	   			this.anwModel.getAuto().gibAutoZurueck(' '));
	   	}
	}

}
