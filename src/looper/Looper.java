/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package looper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VIVEK
 */
public class Looper<T> implements Runnable {

    private List<T> iterableList;
    private Thread thread;
    private boolean startFlag;
    private int select = 0;
    private ChangeListener changeListener = new DEFAULT_CHANGE_LISTENER();
    private static final long SLEEP_MILLIS = 1000;
    private ChangeEvent<T> changeEvent;

    public Looper(List<T> iterableList) {
        this.iterableList = iterableList;
    }

    public Looper() {
        this.iterableList = new ArrayList<T>();
    }

    public void setIterableList(List<T> iterableList) {
        this.iterableList = iterableList;
    }

    public void addIter(T obj) {
        this.iterableList.add(obj);
    }

    public T getCurrentItem() {
        return iterableList.get(select);
    }

    public void startLooping() {
        thread = new Thread(this);
        startFlag = true;
        thread.start();
    }

    public void pause() {
        startFlag = false;
    }

    public void resume() {
        startFlag = true;
        thread.start();
    }

    public void setChangeListener(ChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    @Override
    public void run() {
        while (startFlag) {
            try {
                select = ++select % iterableList.size();
                if (changeListener != null) {
                    changeListener.onChange(getChangeEvent());
                }
                Thread.sleep(SLEEP_MILLIS);
            } catch (InterruptedException ex) {
                Logger.getLogger(Looper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private final class DEFAULT_CHANGE_LISTENER implements ChangeListener {

        @Override
        public void onChange(ChangeEvent e) {
            System.out.println("Changed New:" + e.getNewObject());
        }
    }

    protected ChangeEvent getChangeEvent() {
        int newSelect = select;
        int oldSelect = (select - 1 + iterableList.size()) % iterableList.size();
        changeEvent = new ChangeEvent<T>(oldSelect, newSelect, iterableList);
        return changeEvent;
    }
}