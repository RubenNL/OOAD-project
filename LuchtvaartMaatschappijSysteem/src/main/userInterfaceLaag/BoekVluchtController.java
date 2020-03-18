package main.userInterfaceLaag;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import main.domeinLaag.Land;
import main.domeinLaag.Luchthaven;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class BoekVluchtController implements Initializable {

	@FXML
	private ComboBox<String> vertrekVliegveld;

	@FXML
	private ComboBox<?> aankomstVliegveld;

	@FXML
	private DatePicker vertrekDatum;

	@FXML
	private ComboBox<?> vertrekTijd;

	@FXML
	private ComboBox<?> klasse;

	@FXML
	private Spinner stoelen;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		vertrekVliegveld.getItems().setAll(Luchthaven.geefAlle().keySet());
	}
}
