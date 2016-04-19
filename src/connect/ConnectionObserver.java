/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author VIVEK
 */
public interface ConnectionObserver {
    public void callOnDetection(Map<?,?> eventData);
    public void callOnConnectionSuccessful();
    public void callOnPacketReceived(JSONObject jobj);
}
