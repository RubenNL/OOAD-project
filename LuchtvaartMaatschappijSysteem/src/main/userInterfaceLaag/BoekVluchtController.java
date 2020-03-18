package main.userInterfaceLaag;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;

public class BoekVluchtController {

	@FXML
	private ComboBox<?> vetrekVliegveld;

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
}
