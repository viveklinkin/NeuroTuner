/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package looper;

import java.util.Map;

/**
 *
 * @author VIVEK
 */
public class SelectEvent {

    private Map<String, Object> jsonMap;
    private Loopable loopable;

    public SelectEvent(Map<String, Object> map, Loopable loopable) {
        this.jsonMap = map;
        this.loopable = loopable;
    }

    public Map<String, Object> getMap() {
        return this.jsonMap;
    }

    public Loopable getLoopable() {
        return this.loopable;
    }
}
