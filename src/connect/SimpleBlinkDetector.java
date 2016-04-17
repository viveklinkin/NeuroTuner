/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import javax.swing.JOptionPane;
import looper.Looper;

/**
 *
 * @author VIVEK
 */
public class SimpleBlinkDetector {

    private SimpleConnector simpleConnector;

    public SimpleBlinkDetector(SimpleConnector simpleConnector, Looper looper) {
        this.simpleConnector = simpleConnector;
        if(simpleConnector == null){
            throw new NullPointerException("SimpleConnector in BlinkDetecter is unset!");
        }
    }

    public SimpleBlinkDetector() {
        try {
            this.simpleConnector = new SimpleConnector();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "There was a problem with connecting\n" + e.getMessage());
        }
    }
}