package thornton.IndependentStudy.view_model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private Button button;

    @FXML
    private ImageView imgView2;

    @FXML
    private TextArea OpenSpotsText;
    
    private static final String IMAGE_PATH = "..\\..\\";

    @FXML
    void ButtonCLick(ActionEvent event) {
    	StringBuilder sb = new StringBuilder();
    	try {

			Process p = Runtime.getRuntime().exec("py thresholding.py 14 aerial2.jpg (50,120)");
			BufferedReader is = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			

			while ((line = is.readLine()) != null)
				//System.out.println(line);
				sb.append(line);
				
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image img1 = new Image("file:" + IMAGE_PATH + "aerial2.jpg");
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

    }
}




