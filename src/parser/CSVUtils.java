/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author VIVEK
 */
public class CSVUtils {

    private static String blinkStrength = "0";
    private static String alphaLow = "0";
    private static String alphaHigh = "0";
    private static String betaLow = "0";
    private static String betaHigh = "0";
    private static String gammaLow = "0";
    private static String gammaHigh = "0";
    private static String delta = "0";
    private static String theta = "0";

    public static String toCSV(JSONObject json) {
        try {
            Map<String, Object> map = JsonUtils.parseJSONAsMap(json);
            if (map.containsKey("blinkStrength")) {
                blinkStrength = (String) map.get("blinkStrength");
                return (alphaLow + "," + alphaHigh + "," + betaLow + "," + betaHigh + ","
                        + gammaLow + "," + gammaHigh + "," + delta + "," + theta + "," + blinkStrength);
            }
            if (map.containsKey("eegPower")) {
                Map<String, String> vals = (Map<String, String>) map.get("eegPower");
                alphaLow = vals.get("lowAlpha");
                alphaHigh = vals.get("highAlpha");
                betaLow = vals.get("lowBeta");
                betaHigh = vals.get("highBeta");
                gammaLow = vals.get("lowgamma");
                gammaHigh = vals.get("highGamma");
                delta = vals.get("delta");
                theta = vals.get(theta);

                return (alphaLow + "," + alphaHigh + "," + betaLow + "," + betaHigh + ","
                        + gammaLow + "," + gammaHigh + "," + delta + "," + theta + "," + blinkStrength);
            }
        } catch (JSONException ex) {
            Logger.getLogger(CSVUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
