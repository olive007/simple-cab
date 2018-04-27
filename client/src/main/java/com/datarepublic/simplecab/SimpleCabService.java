package com.datarepublic.simplecab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface SimpleCabService {

	static final String REST_SERVICE_URI = "http://localhost:8080/api";
	
	static String readDataFromApi(String apiRessource) {
    	
    	String result = "";
		try {
			URL url = new URL(REST_SERVICE_URI+apiRessource);
			InputStream is =  url.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return "0";
        }
        catch (IOException e) {
            e.printStackTrace();
            return "0";
        }
        return result;

    }
	
	public static void deleteCache() {
		readDataFromApi("/clearCache");
	}
	
	public static Integer getMedallionsSummary(List<String> medallions, Date pickupDate) {
		return getMedallionsSummary(medallions, pickupDate, false);
	}

	public static Integer getMedallionsSummary(List<String> medallions, Date pickupDate, boolean ignoreCache) {

		String url = "/nbrTripByPickupDateAndMedallions";
		
		if (medallions.size() == 0) {
			return -1;
		}
		
		if (ignoreCache == true) {
			url = "/fresh"+url;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		url += "/"+dateFormat.format(pickupDate)+"/";

		for (int i = medallions.size(); --i >= 1; ) {
			url += medallions.get(i)+",";
		}
		url += medallions.get(0);
		//System.out.println(url);
		return Integer.parseInt(readDataFromApi(url));
	}
	
}
