package main;

import gui.Autovermietung.AutovermietungControl;
import gui.Autovermietunguebersicht.AutovermietunguebersichtControl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		//new AutovermietungAnwendungssystem(primaryStage);
		
		new AutovermietungControl(primaryStage);
		
		Stage stage = new Stage();
		
		new AutovermietunguebersichtControl(stage);
	}	
	
	public static void main(String[] args){
		launch(args);
	}
}
