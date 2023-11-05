/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eiu.cit.netprog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author ACER
 */
public class ex1 {
    public static void main(String[] args) {
        try {

            URL url = new URL("https://www.tanbinhtech.com:8443/sample1.wav");
            java.io.InputStream in = url.openStream();
            String file="E:\\MyFile\\sample1.mav";
            File file2 = new File(file);
            OutputStream fos = new FileOutputStream(file2);

            int c;
            byte[] a = new byte[1024];
            int i = 0;
            while ((c = in.read()) != -1) {
                a[i] += (byte)c;
                i++;
            }
            fos.write(a);
            fos.close();

            //url.getContent();
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
