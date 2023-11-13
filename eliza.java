/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eiu.cit.netprog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author ACER
 */
public class eliza {

    public static final String SERVER = "telehack.com";

    public static final int PORT = 23;

    public static final int TIMEOUT = 15000;

    public static void main(String[] args) {

        Socket socket = null;

        try {

            socket = new Socket(SERVER, PORT);
            socket.setSoTimeout(TIMEOUT);
            OutputStream os = socket.getOutputStream();
            Writer wt = new OutputStreamWriter(os, "UTF-8");
            wt = new BufferedWriter(wt);
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            int c;
            int pre = -1;
            while ((c = in.read()) != -1) {
                System.out.print((char) c);

                if ((char) c == '.') {
                    if ((char) pre == '\n') {
                        break;
                    }

                }
                pre = c;
            }
            wt.write("eliza" + "\r\n");
            wt.flush();
            readfirst(reader);
            
            BufferedReader terminal = new BufferedReader(new InputStreamReader(System.in));
            String line = terminal.readLine();

            while (!(line.equals("quit"))) {
                wt.write(line + "\r\n");
                wt.flush();
                readeliza(reader);
                line = terminal.readLine();
            }
            reader.close();
            wt.close();
            os.close();

        } catch (IOException ex) {
            Logger.getLogger(eliza.class.getName()).log(Level.SEVERE, null, ex);
        } finally { // dispose
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    // ignore
                }
            }
        }

    }

    static void readfirst(BufferedReader reader) {

        int c;
        int count = 0;
        try {
            while ((c = reader.read()) != -1) {
                if ((char) c == '\r') {
                    if (count == 2) {
                        break;
                    } else {
                        count++;
                    }
                }
                if (count == 1) {
                    System.out.print((char) c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readeliza(BufferedReader reader) {
        int c;
        int count = 0;
        try {
            while ((c = reader.read()) != -1) {
                if ((char) c == '\r') {
                    if (count == 3) {
                        break;
                    } else {
                        count++;
                    }
                }
                if (count == 2) {
                    System.out.print((char) c);
                }
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
