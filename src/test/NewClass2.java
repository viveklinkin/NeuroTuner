/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import connect.ListenEvents;
import connect.SimpleBlinkDetector;
import looper.ChangeEvent;
import looper.ChangeListener;
import looper.Looper;
import looper.SelectEvent;
import looper.SelectListener;

/**
 *
 * @author VIVEK
 */
public class NewClass2 implements ChangeListener, SelectListener {

    public NewClass2(){
        Looper looper = new Looper();
        looper.addIter(1, this);
        looper.addIter(2, this);
        looper.addIter(3, this);
        looper.setChangeListener(this);
        
        SimpleBlinkDetector sbd = new SimpleBlinkDetector();
        sbd.informOnBlink(looper);
        sbd.listen();
    }
    public static void main(String args[]) {
        NewClass2 newC = new NewClass2();
        
    }

    @Override
    public void onChange(ChangeEvent e) {
        System.out.println("Change " + (Integer) e.getNewLoopable().getObject());
    }

    @Override
    public void onSelect(SelectEvent event) {
        System.out.println("Strength of blink " + event.getMap().get(ListenEvents.BLINK_EVENT.getKeyValue())
                + "\nSelected: " + (Integer)event.getLoopable().getObject() + "\n");
    }
}
