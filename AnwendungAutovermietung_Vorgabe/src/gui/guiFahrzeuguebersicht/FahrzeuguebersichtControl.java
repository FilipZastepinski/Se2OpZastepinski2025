package gui.guiFahrzeuguebersicht;

import business.AutovermietungModel;
import javafx.stage.Stage;
import ownUtil.Observer;

public class FahrzeuguebersichtControl implements Observer {
	private FahrzeuguebersichtView fahrzeuguebersichtView;
	private AutovermietungModel autovermietungModel;

	public FahrzeuguebersichtControl(Stage primaryStage) {
		// this.autovermietungModel = new AutovermietungModel();
		this.autovermietungModel = AutovermietungModel.getInstance();
		this.fahrzeuguebersichtView = new FahrzeuguebersichtView(this, primaryStage, autovermietungModel);
		this.autovermietungModel.addObserver(this);
	}
	
	@Override
	public void update() {
		if (autovermietungModel.getAuto() != null) {
			
			String erg = null;
			
			for(int i = 0; i < autovermietungModel.getAuto().size(); i++) {
				erg += autovermietungModel.getAuto().get(i).gibAutoZurueck(' ');
			}
			
			this.fahrzeuguebersichtView.txtAnzeigeAutos.setText(
					erg);
		}

	}
}
