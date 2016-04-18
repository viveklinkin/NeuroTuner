/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package looper;

/**
 *
 * @author VIVEK
 */
public class Loopable {

    private Class type;
    private Object obj;
    private SelectListener listener;

    public Loopable(Object obj) {
        this.obj = obj;
    }

    public Object getObject() {
        return obj;
    }

    public Object getType() {
        return type.cast(obj);
    }

    public void setListener(SelectListener listener) {
        this.listener = listener;
    }

    public SelectListener getListener() {
        return this.listener;
    }
}
