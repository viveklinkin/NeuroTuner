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
public class ChangeEvent<T> {
    private int oldSelect, newSelect;
    private List<T> iterableList;
    
    public ChangeEvent(int oldSelect, int newSelect, List<T> iterableList){
        this.oldSelect = oldSelect;
        this.newSelect = newSelect;
        this.iterableList = iterableList;
    }
    
    public T getOldObject(){
        return iterableList.get(oldSelect);
    }
    
    public T getNewObject(){
        return iterableList.get(newSelect);
    }
    
}
