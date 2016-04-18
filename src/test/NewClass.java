/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import looper.Looper;

/**
 *
 * @author VIVEK
 */
public class NewClass {

    public static void main(String args[]) throws Exception {
        Looper loop = new Looper();
        loop.addIter(1);
        loop.addIter(2);
        loop.addIter(3);
        loop.addIter(4);
        loop.addIter(5);
        loop.startLooping();
        while (true) {
            Thread.sleep(400);
            System.out.println(((Integer)loop.getCurrentItem().getObject()).intValue() +"");
        }
    }
}
