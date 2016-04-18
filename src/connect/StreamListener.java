/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import parser.JsonUtils;

/**
 *
 * @author VIVEK
 */
public class StreamListener implements Runnable {

    private SimpleConnector simpleConnector;
    private ListenEvents listenFor = ListenEvents.RAW_VALUE_EVENT;
    private ConnectionObserver observer;
    public static List<JSONObject> Logger = new ArrayList<>();
    private static boolean connected = false;
    private boolean runFlag = true;

    public StreamListener(SimpleConnector simpleConnector, ConnectionObserver observer, ListenEvents listenEvent) {
        this.simpleConnector = simpleConnector;
        this.observer = observer;
        this.listenFor = listenEvent;
    }

    public void listenFor(ListenEvents event) {
        this.listenFor = event;
    }

    @Override
    public void run() {
        while (runFlag) {
            try {
                simpleConnector.initConnection();
                String str;
                while ((str = simpleConnector.getInputStreamReader().readLine()) != null) {
                    //System.out.println("Ping: " + str);
                    if (!connected) {
                        updateConnected(str);
                    }
                    if (connected) {
                        for (String jsonString : JsonUtils.getJSONStringsFromBlob(str)) {
                            JSONObject jobj = new JSONObject(jsonString);
                            Map<String, Object> jsonMap = JsonUtils.parseJSONAsMap(jobj);
                            if (blinkOccurred(jsonMap)) {
                                System.out.print(jobj);
                                observer.callOnDetection(jsonMap);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean blinkOccurred(Map<String, Object> jsonMap) {
        return jsonMap.containsKey(listenFor.getKeyValue());
    }

    public void updateConnected(String str) {
        if (!str.contains("status")) {
            connected = true;
            observer.callOnConnectionSuccessful();
        }
    }

    public void startListening() {
        this.simpleConnector.initConnection();
        Thread t = new Thread(this);
        this.runFlag = true;
        t.start();
    }

    public void stopListening() {
        this.runFlag = false;
    }
}