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
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author ACER
 */
public class ex4 {
    public static void main(String[] args) {
        try{
            URL u = new URL("http://ice10.outlaw.fm/KIEV2");
            InputStream  inputstream = u.openStream();
            inputstream = new BufferedInputStream(inputstream);
            
            Player mp3Player = new Player(inputstream);
            mp3Player.play();
        } catch (MalformedURLException e) {
            //handle
        }catch (JavaLayerException ex) {
            Logger.getLogger(metmoi2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(metmoi2.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
}
