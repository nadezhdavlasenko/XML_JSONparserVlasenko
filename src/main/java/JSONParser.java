import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.json.JSONObject;


public class JSONParser {

    private static final JSONParser jsonParser = new JSONParser();

    private JSONParser() {
    }

    public static JSONParser getJsonParser() {
        return jsonParser;
    }

    public String getJSONStringFromAPI(String urlString){

        String jsonString = null;
        try {
            URL url = new URL(urlString);
            //Parse URL into HttpURLConnection in order to open the connection in order to get the JSON data
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //Set the request to GET or POST as per the requirements
            conn.setRequestMethod("GET");
            //Use the connect method to create the connection bridge
            conn.connect();
            //Get the response status of the Rest API
            int responsecode = conn.getResponseCode();
            System.out.println("Response code is: " +responsecode);
            //Iterating condition to if response code is not 200 then throw a runtime exception
            //else continue the actual process of getting the JSON data
            if(responsecode != 200)
                throw new RuntimeException("HttpResponseCode: " +responsecode);
            else
            {
                InputStream is = url.openStream();
                try {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                    jsonString = readAll(rd);

                } finally {
                    is.close();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    private  String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public List getMapFromJSONString(String jsonString){
        JsonParser parser = new JsonParser();
        List list =  new Gson().fromJson(parser.parse(jsonString), List.class);
        return list;
    }

    public Map getMapFromList(List<Map> list, String key, String value){
        Map resultMap = new HashMap();
        for (Map map : list) {
            resultMap.put(map.get(key), map.get(value));
        }
        return resultMap;
    }
}
