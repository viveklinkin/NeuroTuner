/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author VIVEK
 */
public class JsonUtils {

    public static List<String> getJSONStringsFromBlob(String str) {
        List<String> listOfString = Arrays.asList(str.split("/\r/"));
        for (Iterator<String> iter = listOfString.iterator(); iter.hasNext();) {
            String currentObject = iter.next();
            if (currentObject.indexOf("{") > -1) {
                try {
                    JSONObject jobj = new JSONObject(currentObject);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    iter.remove();
                }
            }
        }
        return listOfString;
    }

    public static void getString(JSONObject paramJSONObject, String key) {
        Iterator localIterator = paramJSONObject.keys();
        while (localIterator.hasNext()) {
            Object localObject = localIterator.next();
            String str = localObject.toString();
            try {
                System.out.println(paramJSONObject.getString(localObject.toString()));
            } catch (Exception localException) {
                localException.printStackTrace();
            }
        }
    }

    public static Map<String, Object> parseJSONAsMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<>();

        Iterator<String> iterator = object.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = parseJSONArrayAsList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = parseJSONAsMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> parseJSONArrayAsList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<>();
        for (int iter = 0; iter < array.length(); iter++) {
            Object value = array.get(iter);
            if (value instanceof JSONArray) {
                value = parseJSONArrayAsList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = parseJSONAsMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
}