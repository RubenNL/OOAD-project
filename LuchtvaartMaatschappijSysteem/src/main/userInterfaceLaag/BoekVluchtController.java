package main.userInterfaceLaag;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.domeinLaag.Boeking;
import main.domeinLaag.Luchthaven;
import main.domeinLaag.Vlucht;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class BoekVluchtController<Treemap> implements Initializable {
	@FXML
	private ComboBox<String> vertrekVliegveld;
	@FXML
	private ComboBox<String> aankomstVliegveld;;
	@FXML
	private ComboBox<String> vertrekTijd;
	@FXML
	private ComboBox<String> klasse;
	@FXML
	private Spinner stoelen;
	@FXML
	private Button buttonOK;
	@FXML
	private Integer max;
	@FXML
	private Button buttonCancel;
	private Boeking boeking;
	private Luchthaven vertrekPunt;
	private Luchthaven aankomstPunt;
	private String vertrekTijdString;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		boeking=new Boeking();
		vertrekVliegveld.getItems().setAll(Luchthaven.geefAlle().keySet());
	}
	public void vertrek() {
		vertrekPunt=Luchthaven.geefAlle().get(vertrekVliegveld.getValue());
		aankomstVliegveld.getItems().clear();
		Set<String> aankomstSet = new HashSet<String>();
		for(Map.Entry<Integer,Vlucht> entry : Vlucht.geefAlle().entrySet()) {
			Integer key = entry.getKey();
			Vlucht value = entry.getValue();
			if(vertrekPunt==value.getVertrekPunt()) {
				aankomstSet.add(value.getBestemming().geefNaam());
			}
		}
		aankomstVliegveld.getItems().setAll(aankomstSet);
	}
	private String dateFormatter(Calendar calendar) {
		Date date = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
		return dateFormat.format(date);
	}
	public void bestemming() {
		aankomstPunt=Luchthaven.geefAlle().get(aankomstVliegveld.getValue());
		for(Map.Entry<Integer,Vlucht> entry : Vlucht.geefAlle().entrySet()) {
			Integer key = entry.getKey();
			Vlucht value = entry.getValue();
			if(vertrekPunt==value.getVertrekPunt() && aankomstPunt==value.getBestemming()) {
				vertrekTijd.getItems().add(dateFormatter(value.getVertrekTijd()));
			}
		}
	}
	public void vertrekTijdSelected() {
		vertrekTijdString=vertrekTijd.getValue();
		for(Map.Entry<Integer,Vlucht> entry : Vlucht.geefAlle().entrySet()) {
			Integer key = entry.getKey();
			Vlucht value = entry.getValue();
			if(vertrekPunt==value.getVertrekPunt() && aankomstPunt==value.getBestemming() && vertrekTijdString.equals(dateFormatter(value.getVertrekTijd()))) {
				boeking.setVlucht(value);
				SpinnerValueFactory factory=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,boeking.getVlucht().getBeschikbarePlaatsen(),1);
				stoelen.setValueFactory(factory);
			}
		}
	}
	public void ok() {
		boeking.setStoelen((Integer)stoelen.getValue());
		try {
			boeking.bewaar();
			toonMelding("Boeking opgeslagen, Dit waren de gegevens:\nstoelen:"+boeking.getStoelen()+"\nvliegtuig:"+boeking.getVlucht().getVliegtuig().geefNaam()+"\nvertrekTijd:"+(new SimpleDateFormat("yyyy-mm-dd hh:mm")).format(boeking.getVlucht().getVertrekTijd().getTime())+"\nvertrek:"+boeking.getVlucht().getVertrekPunt().geefNaam()+"\naankomst:"+boeking.getVlucht().getBestemming().geefNaam());
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
