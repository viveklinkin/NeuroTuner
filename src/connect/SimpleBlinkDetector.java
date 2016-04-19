/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.util.Map;
import java.util.Observable;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import looper.Looper;
import org.json.JSONObject;
import parser.CSVUtils;

/**
 *
 * @author VIVEK
 */
public class SimpleBlinkDetector extends Observable implements ConnectionObserver {

    private SimpleConnector simpleConnector;
    private Looper looper;
    private ListenEvents listenFor = ListenEvents.BLINK_EVENT;
    private StreamListener listener;

    public SimpleBlinkDetector() {
        try {
            this.simpleConnector = new SimpleConnector();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "There was a problem with connecting\n" + e.getMessage());
        }
    }

    public void informOnBlink(Looper looper) {
        this.deleteObservers();
        this.looper = looper;
        this.addObserver(looper);
    }

    public void listen() {
        listener = new StreamListener(simpleConnector, this, listenFor);
        listener.startListening();
    }

    @Override
    public void callOnDetection(Map<?, ?> map) {
        System.out.println("detected");
        setChanged();
        notifyObservers(map);
    }

    @Override
    public void callOnConnectionSuccessful() {
        System.out.println("Connected");
        looper.startLooping();
        listener.stopListening();
    }

    public void listenTo(ListenEvents evt) {
        this.listenFor = evt;
    }
    
    public void stop(){
        this.looper.pause();
        this.simpleConnector.stopConnecting();
    }

    @Override
    public void callOnPacketReceived(JSONObject jobj) {
        logger.Logger.log(CSVUtils.toCSV(jobj));
    }
}