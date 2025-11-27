package gui.Autovermietunguebersicht;

import business.AutovermietungModel;
import javafx.stage.Stage;

public class AutovermietunguebersichtControl {
	private AutovermietunguebersichtView autovermietunguebersichtView;
	private AutovermietungModel autovermietungModel;

	public AutovermietunguebersichtControl(Stage primaryStage) {
		// this.autovermietungModel = new AutovermietungModel();
		this.autovermietungModel = AutovermietungModel.getInstance();
		this.autovermietunguebersichtView = new AutovermietunguebersichtView(this, primaryStage, autovermietungModel);
	}
}
