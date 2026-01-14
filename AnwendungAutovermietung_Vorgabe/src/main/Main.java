package main;

import gui.guiAutovermietung.AutovermietungControl;
import gui.guiFahrzeuguebersicht.FahrzeuguebersichtControl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		//new AutovermietungAnwendungssystem(primaryStage);
		
		// Fenster zur Autovermietung
		new AutovermietungControl(primaryStage);
		
		// Fenster zur Fahrzeuguebersicht
		Stage fensterFahrzeuguebersicht = new Stage();
		new FahrzeuguebersichtControl(
				fensterFahrzeuguebersicht);
	}	
	
	public static void main(String[] args){
		launch(args);
	}
}
