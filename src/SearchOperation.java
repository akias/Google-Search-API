import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SearchOperation {
	/*
	 * get the data with google Search API
	 */
	public void getResult() throws IOException {
		//key
		String key = "AIzaSyAuMADMrBJfDyVcgC-Z_5uhtq62-F77tEM";
		//id
		String id = "004531866512371169202:ix8k493jqge";
		int start = 1;
		//10 loops and 1 loop will get 10 result
		for (int i = 0; i < 10; i++) {
			String result = "";
			URL url = new URL("https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=" + id
					+ "&q=quick+ticket&start=" + start + "&num=10&alt=json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			//System.out.println("-------------Output from Server -----------" + i + "\n");
			while ((output = br.readLine()) != null) {
				result += output;
			}
			conn.disconnect();
			start = start + 10;
			parseResult(result);

		}
	}
	
	/*
	 * change the JSON data to String
	 */
	public void parseResult(String result) {
		String title = "";
		String link = "";
		Result re = new Result();
		JSONObject dataJson = JSONObject.fromObject(result);
		JSONArray items = dataJson.getJSONArray("items");
		for (int i = 0; i < items.size(); i++) {
			JSONObject info = items.getJSONObject(i);
			//id is the key and set id auto_increment
			re.setTitle(info.getString("title"));
			re.setLink(info.getString("link"));
			System.out.print(title + "      " + link + "\n");
			boolean res = DatabaseOperation.getInstance().saveSite(re);
			if (res == true) {
				System.out.println(i + "succeed to insert data to database");
			} else {
				System.out.println("failed to insert data to database");
			}
		}
	}
}
