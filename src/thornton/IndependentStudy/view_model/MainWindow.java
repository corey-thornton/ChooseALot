package thornton.IndependentStudy.view_model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainWindow {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView imgView1;

	@FXML
	private ComboBox<String> lotComboBox;

	@FXML
	private Button button;

	@FXML
	private ImageView imgView2;

	@FXML
	private TextArea OpenSpotsText;

	private static final String IMAGE_PATH = "..\\..\\";
	private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: #4EA24E; -fx-border-color:  #39ff14; -fx-border-width: 3; ";
	private static final String IDLE_BUTTON_STYLE = "-fx-background-color:  #228b22; -fx-border-color:  #39ff14 ; -fx-border-width: 3; ";
	private static final String CLICKED_BUTTON_STYLE = "-fx-background-color:  #124712; -fx-border-color:  #39ff14 ; -fx-border-width: 3; ";

	@FXML
	void ButtonCLick(ActionEvent event) {
		if (this.lotComboBox.getValue() == null) {
			this.OpenSpotsText.setText("Must Select A lot First");
			return;
		}
		StringBuilder sb = new StringBuilder();
		String imageName = this.lotComboBox.getValue().replace(" ", "") + ".jpg";
		try {
			BufferedReader is;
			if(imageName.equals("realisticLot1.jpg")) {
				Process p = Runtime.getRuntime().exec("py thresholding.py 14 " + imageName + " (50,90)");
				 is = new BufferedReader(new InputStreamReader(p.getInputStream()));
			}
			else if(imageName.equals("realisticLot2.jpg")) {
				Process p = Runtime.getRuntime().exec("py thresholding.py 20 " + imageName + " (50,90)");
				 is = new BufferedReader(new InputStreamReader(p.getInputStream()));
			}
			else if(imageName.equals("realisticLot3.jpg")) {
				Process p = Runtime.getRuntime().exec("py thresholding.py 12 " + imageName + " (60,90)");
				 is = new BufferedReader(new InputStreamReader(p.getInputStream()));
			}
			else {

			Process p = Runtime.getRuntime().exec("py thresholding.py 20 " + imageName + " (30,90)");
			is = new BufferedReader(new InputStreamReader(p.getInputStream()));
			}
			String line;

			while ((line = is.readLine()) != null)

				sb.append(line);

		} catch (IOException e) {

			e.printStackTrace();
		}

		Image img1 = new Image("file:" + IMAGE_PATH + imageName);
		Image img2 = new Image("file:" + IMAGE_PATH + "thresholdImage.jpg");

		this.imgView1.setImage(img1);
		this.imgView2.setImage(img2);
		this.OpenSpotsText.setText(sb.toString());

	}

	@FXML
	void initialize() {

		assert imgView1 != null : "fx:id=\"imgView1\" was not injected: check your FXML file 'mainWindow.fxml'.";
		assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'mainWindow.fxml'.";
		assert imgView2 != null : "fx:id=\"imgView2\" was not injected: check your FXML file 'mainWindow.fxml'.";
		assert OpenSpotsText != null : "fx:id=\"OpenSpotsText\" was not injected: check your FXML file 'mainWindow.fxml'.";
		assert lotComboBox != null : "fx:id=\"lotComboBox\" was not injected: check your FXML file 'mainWindow.fxml'.";
		this.button.setOnMousePressed(e -> button.setStyle(CLICKED_BUTTON_STYLE));
		this.button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_STYLE));
		this.button.setOnMouseExited(e -> button.setStyle(IDLE_BUTTON_STYLE));
		ObservableList<String> parkingLots = FXCollections.observableArrayList("Lot 1", "Lot 2", "Lot 3", "Lot 4",
				"Lot 5", "Lot 6", "Lot 7","realistic Lot 1","realistic Lot 2","realistic Lot 3");
		this.lotComboBox.getItems().addAll(parkingLots);

	}
}
