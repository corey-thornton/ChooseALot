package thornton.IndependentStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {
	public static final String MAIN_WINDOW_TITLE = "Project 1";
	public static final String MAIN_WINDOW = "view/mainWindow.fxml";
	

	/**
	 * JavaFX entry point.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param primaryStage
	 *            the main window hook
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		URL location = Main.class.getResource(Main.MAIN_WINDOW);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(location);
		loader.load();
		Parent parent = loader.getRoot();
		Scene scene = new Scene(parent);
		primaryStage.setTitle(Main.MAIN_WINDOW_TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	/**
	 * Java entry point
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
		installPythonModules();
		
		
	}
	
	private static void installPythonModules() {
		try {
			Process installCV2 = Runtime.getRuntime().exec("py -m pip install opencv-python");
			Process installPIL = Runtime.getRuntime().exec("py -m pip install pillow");
			installCV2.waitFor();
			installPIL.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
