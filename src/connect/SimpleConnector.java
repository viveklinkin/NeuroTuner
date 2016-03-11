/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import com.sun.xml.internal.ws.message.saaj.SAAJHeader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import org.json.JSONObject;

/**
 *
 * @author VIVEK
 */
public class SimpleConnector implements Runnable {

    private Socket mSocket;
    private OutputStream outStream;
    private InputStream inStream;
    private PrintWriter outStreamWriter;
    private BufferedReader inStreamReader;
    private static final JSONObject authJsonObject = new JSONObject();

    public static void main(String args[]) throws Exception {
        SimpleConnector conn = new SimpleConnector();
        conn.startListening();
    }

    public SimpleConnector() throws Exception {
        mSocket = new Socket("127.0.0.1", 13854);
        outStream = mSocket.getOutputStream();
        outStreamWriter = new PrintWriter(outStream, true);
        inStream = mSocket.getInputStream();
        inStreamReader = new BufferedReader(new InputStreamReader(inStream));
        authJsonObject.put("enableRawOutput", true);
        authJsonObject.put("format", "Json");
    }

    @Override
    public void run() {
        String str;
        try {
            initConnection();
            while ((str = inStreamReader.readLine()) != null) {
                System.out.println(str);
                /*String[] arrayOfString = str.split("/\r/");
                for (int i = 0; i < arrayOfString.length; i++) {
                    if (arrayOfString[i].indexOf("{") > -1) {
                        JSONObject jobj = new JSONObject(arrayOfString[i]);
                        parsePacket(jobj);
                    }
                }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parsePacket(JSONObject paramJSONObject) {
        Iterator localIterator = paramJSONObject.keys();
        while (localIterator.hasNext()) {
            Object localObject = localIterator.next();
            String str = localObject.toString();
            System.out.println("hello");
            try {
                System.out.println(paramJSONObject.getString(localObject.toString()));
            } catch (Exception localException) {
                localException.printStackTrace();
            }
        }
    }
    
    private void initConnection(){
        send(authJsonObject.toString());
    }
    
    public void startListening() {
        Thread t = new Thread(this);
        t.start();
    }
    
    public void send(String msg) {
        outStreamWriter.println(msg);
    }
}
