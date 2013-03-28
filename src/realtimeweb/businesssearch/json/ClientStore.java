package realtimeweb.businesssearch.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import realtimeweb.businesssearch.util.JsonConverter;

/**
 * This method is intended for internal use only.
 * 
 * This is the class that is used to store internal results. 
 * @author acbart
 *
 */
class ClientStore {

	private HashMap<String, String> jsonData;
	
	/**
	 * Create a client store from a specified file.
	 * @param source
	 */
	public ClientStore(String source, boolean recording) {
		jsonData = new HashMap<String, String>();
		if (!recording) {
			try {
				this.load(source);
			} catch (IOException e) {
				System.err.println("Couldn't find client-side datastore! Your Jar might be corrupt.");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Look up a given query in the client store. If it is not found, returns the empty string.
	 * @param key
	 * @return
	 */
	public String getData(String key) {
		if (jsonData.containsKey(key)) {
			return jsonData.get(key);
		} else {
			return "";
		}
	}
	
	public void putData(String key, String value) {
		jsonData.put(key, value);
	}

	/**
	 * Create a client store based on the default store.
	 */
	public ClientStore(boolean recording) {
		this("../cache.json", recording);
	}

	/**
	 * Load the client store from a file.
	 * @param source
	 * @throws IOException
	 */
	public void load(String source) throws IOException {
		InputStreamReader is = new InputStreamReader(getClass().getResourceAsStream(source));
		BufferedReader clientData = new BufferedReader(is);
		String line;
		while ((line = clientData.readLine()) != null) {
			try {
				HashMap<String, Object> hm = (HashMap<String, Object>) JsonConverter.convertToMap(line);
				for (Entry<String, Object> es : hm.entrySet()) {
					jsonData.put(es.getKey(), es.getValue().toString());
				}
			} catch (ParseException e) {
				System.err.println("Client-Side Data couldn't be parsed! Your jar might be corrupt.");
				e.printStackTrace();
			}
		}
		clientData.close();
	}
	
	public synchronized void save(String source, boolean append) throws IOException {
		FileWriter fw = new FileWriter(source, append);
		BufferedWriter clientFile = new BufferedWriter(fw);
		JSONValue.writeJSONString(jsonData, clientFile);
		clientFile.close();
	}
}