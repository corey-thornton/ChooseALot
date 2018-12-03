package drivers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OpenSpotDriver {

	public static void main(String[] args) {
		try {
			Process p = Runtime.getRuntime().exec("py thresholding.py");
			
			BufferedReader is = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			
			

			while ((line = is.readLine()) != null)
			   System.out.println(line);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
