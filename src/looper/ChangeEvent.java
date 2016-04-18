/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package looper;

import java.util.List;

/**
 *
 * @author VIVEK
 */
public class ChangeEvent {
    private int oldSelect, newSelect;
    private List<Loopable> iterableList;
    
    public ChangeEvent(int oldSelect, int newSelect, List<Loopable> iterableList){
        this.oldSelect = oldSelect;
        this.newSelect = newSelect;
        this.iterableList = iterableList;
    }
    
    public Loopable getOldLoopable(){
        return iterableList.get(oldSelect);
    }
    
    public Loopable getNewLoopable(){
        return iterableList.get(newSelect);
    }
    
}
