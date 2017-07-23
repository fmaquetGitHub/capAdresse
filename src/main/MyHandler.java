package main;

import java.io.IOException;
import java.io.OutputStream;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHandler implements HttpHandler {

	public void handle(HttpExchange t) throws IOException {

		String response = null;

		WebService.setUserName("soldatmaquet");
		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
		searchCriteria.setQ("Ennevelin");
		ToponymSearchResult searchResult;
		try {
			searchResult = WebService.search(searchCriteria);
			
			for (Toponym toponym : searchResult.getToponyms()) {
				System.out.println(toponym.getName() + " "
						+ toponym.getCountryName());
				response += toponym.getName() + "\n";

				t.sendResponseHeaders(200, response.length());
				OutputStream os = t.getResponseBody();
				os.write(response.getBytes());
				os.close();

			}
		} catch (Exception e) {

			e.printStackTrace();
			response = "KO!";
		}

	}
}