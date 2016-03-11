/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import looper.ChangeEvent;
import looper.ChangeListener;
import looper.Looper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author VIVEK
 */
public class NewClass1 implements ChangeListener {

    static Looper<Integer> loop;

    public static void main(String args[]) throws Exception {
        JFrame frame = new JFrame("Sample");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton b = new JButton("click me!!");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected:" + getObj());
            }
        });
        frame.add(b);
        frame.setVisible(true);
        loop = new Looper<Integer>();
        loop.addIter(1);
        loop.addIter(2);
        loop.addIter(3);
        loop.addIter(4);
        loop.addIter(5);
        loop.startLooping();
    }

    static String getObj() {
        return loop.getCurrentItem() + "";
    }

    @Override
    public void onChange(ChangeEvent e) {
        System.out.println("Changed New:" + e.getNewObject());
    }
}
