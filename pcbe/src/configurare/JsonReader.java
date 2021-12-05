package configurare;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;
/**
 * 
 * @author diana
 *
 */
public class JsonReader {
	private ConfigurationDetails configurare;
	public JsonReader() {
		 try {   
			 	/**TODO: un folder de resurse unde sa fie JSON file*/
	            Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\diana\\git\\Sistem-birocratic\\Sistem birocratic\\Configurare.json"));
	            Gson gson = new Gson();
	            setConfigurare(gson.fromJson(reader,ConfigurationDetails.class));
	            reader.close();
	            
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	}
	public ConfigurationDetails getConfigurare() {
		return configurare;
	}
	private void setConfigurare(ConfigurationDetails configurare) {
		this.configurare = configurare;
	}
}
