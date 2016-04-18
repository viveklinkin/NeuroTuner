/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package looper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VIVEK
 */
public class Looper implements Runnable,Observer {

    private List<Loopable> iterableList;
    private Thread thread;
    private boolean startFlag;
    private int select = 0;
    private ChangeListener changeListener = new DEFAULT_CHANGE_LISTENER();
    private static final long SLEEP_MILLIS = 1500;
    private ChangeEvent changeEvent;

    public Looper(List<Loopable> iterableList) {
        this.iterableList = iterableList;
    }

    public Looper() {
        this.iterableList = new ArrayList();
    }

    public void setIterableList(List<Loopable> iterableList) {
        this.iterableList = iterableList;
    }

    public void addIter(Object obj) {
        if (obj instanceof Loopable) {
            iterableList.add((Loopable) obj);
        } else {
            this.iterableList.add(new Loopable(obj));
        }
    }

    public void addIter(Object obj, SelectListener listener) {
        if (obj instanceof Loopable) {
            Loopable localLoopable = (Loopable) obj;
            localLoopable.setListener(listener);
            this.iterableList.add(localLoopable);
        } else {
            Loopable localLoopable = new Loopable(obj);
            localLoopable.setListener(listener);
            this.iterableList.add(localLoopable);
        }
    }

    public Loopable getCurrentItem() {
        return iterableList.get(select);
    }

    public void startLooping() {
        System.out.println("looping Thread beings");
        if (thread == null) {
            thread = new Thread(this);
            startFlag = true;
            thread.start();
        } else {
            resume();
        }
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

    @Override
    public void update(Observable o, Object arg) {
        if (getCurrentItem().getListener() != null) {
            getCurrentItem().getListener().onSelect(new SelectEvent((Map<String, Object>) arg, getCurrentItem()));
        }
    }

    private final class DEFAULT_CHANGE_LISTENER implements ChangeListener {

        @Override
        public void onChange(ChangeEvent e) {
            System.out.println("Changed New:" + e.getNewLoopable());
        }
    }

    protected ChangeEvent getChangeEvent() {
        int newSelect = select;
        int oldSelect = (select - 1 + iterableList.size()) % iterableList.size();
        changeEvent = new ChangeEvent(oldSelect, newSelect, iterableList);
        return changeEvent;
    }
}