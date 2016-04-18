/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

/**
 *
 * @author VIVEK
 */
public enum ListenEvents {
    BLINK_EVENT("blinkStrength"),
    ATTENTION_EVENT("attention"),
    MEDITATION_EVENT("meditation"),
    RAW_VALUE_EVENT("rawEeg"),
    E_SENSE_EVENT("eSense"),
    POOR_SIGNAL_EVENT("poorSignalLevel");
    
    private String keyValue;    
    private ListenEvents(String key){
        this.keyValue = key;
    }
    public String getKeyValue(){
        return keyValue;
    }
}
