package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropReader {
	public static Properties prop;
	
    // Method to load and retrieve properties from the config.properties file
	public static Properties property() {
		prop = new Properties();
		
		try {
            // Create a FileInputStream to read the config.properties file
			FileInputStream input = new FileInputStream("config.properties");
			
			try {
                // Load the properties from the FileInputStream into the Properties object
				prop.load(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
        // Return the Properties object containing the loaded properties
		return prop;
	}
}