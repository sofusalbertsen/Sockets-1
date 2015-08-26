/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets.selfmade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sofus
 */
public class WaitServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(12345);
        Socket s;
        while (true) {
            s = ss.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(),
                    true);
            // waits for data and reads it in until connection dies
            // readLine() blocks until the server receives a new line from client
            String str;
            while ((str = in.readLine()) != null) {
                if (str.equals("!!")) {
                    System.exit(0);
                }
                out.println("waiting for " + str + " sec");
                try {
                    Thread.sleep((Integer.parseInt(str)*1000));
                    System.out.println("done waiting");
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } catch (NumberFormatException ex){
                    out.println("NaN");
                }
            }

        }
    }
}