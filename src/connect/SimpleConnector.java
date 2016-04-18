/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;      
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;


/**
 *
 * @author VIVEK
 */
public class SimpleConnector {

    private Socket mSocket;
    private OutputStream outStream;
    private InputStream inStream;
    private PrintWriter outStreamWriter;
    private BufferedReader inStreamReader;
    private static final JSONObject authJsonObject = new JSONObject();

    public static void main(String args[]) throws Exception {
        SimpleConnector conn = new SimpleConnector();
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

    public void initConnection() {
        send(authJsonObject.toString());
    }
    
    public void send(String msg) {
        outStreamWriter.println(msg);
    }
   public BufferedReader getInputStreamReader(){
       return this.inStreamReader;
   }
   public void stopConnecting(){
        
   }
}