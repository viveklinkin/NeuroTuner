/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import java.io.File;
import java.io.PrintWriter;

/**
 *
 * @author VIVEK
 */
public class Logger {

    public static PrintWriter printWriter;

    public static void log(String s) {
        try {
            if (printWriter == null) {
                printWriter = new PrintWriter(getFileName(), "UTF-8");
            }
            printWriter.println(s);
        } catch (Exception e) {
            System.out.println("SSSSSSSSSSSSSSSSOMMMMMMMMMMMMMMMEEEEEEEEEEETHING wrong!");
        }
    }

    public static String getFileName() {
        String name = "log";
        int i = 1;
        while (i++ > 0) {
            File fname = new File(name + i + ".csv");
            if (!fname.exists()) {
                name = fname.getName();
                break;
            }
        }
        return name;
    }
}
