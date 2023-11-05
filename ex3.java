/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eiu.cit.netprog;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author ACER
 */
public class ex3 {
    public static void main(String[] args) {

        try {

            URL u = new URL("https://www.tanbinhtech.com:8443/sample1.wav");
            
            InputStream in = u.openStream();
            
            in = new BufferedInputStream(in);

            AudioInputStream audio = AudioSystem.getAudioInputStream(u);

            AudioFormat format = audio.getFormat();

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            SourceDataLine sound = (SourceDataLine) AudioSystem.getLine(info);

            sound.open(format);

            sound.start();

            byte[] line = new byte[4096];

            int count = -1;

            while ((count = audio.read(line)) != -1) {
                sound.write(line, 0, count);

            }

            sound.drain();

            sound.close();

            audio.close();

        } catch (MalformedURLException ex) {

        } catch (UnsupportedAudioFileException ex) {

        } catch (LineUnavailableException ex) {

        } catch (IOException ex) {
            Logger.getLogger(metmoi4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
