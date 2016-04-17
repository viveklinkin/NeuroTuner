/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import event.ListenEventObserver;
import event.ListenEvents;
import org.json.JSONObject;
import parser.JsonUtils;

/**
 *
 * @author VIVEK
 */
public class ConnectionListener implements Runnable {

    private SimpleConnector simpleConnector;
    private ListenEvents listenFor = ListenEvents.RAW_VALUE_EVENT;
    private ListenEventObserver eventObserver;
    
    public ConnectionListener(SimpleConnector simpleConnector){
        this.simpleConnector = simpleConnector;
    }
    public void listenFor(ListenEvents event){
        this.listenFor = event;
    }
    @Override
    public void run() {
        String str;
        try {
            simpleConnector.initConnection();
            while ((str = simpleConnector.getInputStreamReader().readLine()) != null) {
                System.out.println("Ping: " + str);
                for (String jsonString : JsonUtils.getJSONStringsFromBlob(str)) {
                    if (jsonString.indexOf("{") > -1) {
                        JSONObject jobj = new JSONObject(jsonString);
                        if(JsonUtils.parseJSONAsMap(jobj).containsKey(listenFor.getKeyValue())){
                            eventObserver.exec();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void startListening() {
        Thread t = new Thread(this);
        t.start();
    }
    
}
