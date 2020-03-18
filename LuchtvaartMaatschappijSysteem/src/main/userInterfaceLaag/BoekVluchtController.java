package main.userInterfaceLaag;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.domeinLaag.Boeking;
import main.domeinLaag.Luchthaven;

import java.net.URL;
import java.util.ArrayList;
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

	@FXML
	private Button buttonOK;

	@FXML
	private Button buttonCancel;

	private Boeking boeking;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		boeking=new Boeking();
		vertrekVliegveld.getItems().setAll(Luchthaven.geefAlle().keySet());
	}
	public void vertrek() {
		System.out.println(vertrekVliegveld.getValue());
	}
	public void bestemming() {
		System.out.println(aankomstVliegveld.getValue());
	}
	public void ok() {
		try {
			boeking.bewaar();
			Stage stage = (Stage) buttonOK.getScene().getWindow();
			stage.close();
		} catch (IllegalStateException ei) {
			toonMelding(ei.getMessage());
		}
	}

	public void cancel() {
		Stage stage = (Stage) buttonCancel.getScene().getWindow();
		stage.close();
	}

	private void toonMelding(String tekstMessage) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setResizable(true);
		alert.onShownProperty().addListener(e -> {
			Platform.runLater(() -> alert.setResizable(false));
		});
		alert.setTitle("Waarschuwing!");
		alert.setContentText(tekstMessage);
		alert.showAndWait();
	}
}
