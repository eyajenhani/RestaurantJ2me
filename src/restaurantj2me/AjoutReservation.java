/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantj2me;

import java.io.IOException;
import java.util.Date;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;

/**
 * @author hp
 */
public class AjoutReservation extends MIDlet implements CommandListener, Runnable {
    Form inscrit = new Form("formulaire de reservation");
    Image alert;
    //Image inscription;
    Alert erreur; 
    Display disp =  Display.getDisplay(this); 
    
      
     TextField theure= new TextField("Hheure de la resevation", null, 50, TextField.ANY);
     DateField tdate_reservation = new DateField(" La Date de la reservation", DateField.DATE);
     TextField tnbrpersonnes = new TextField("Le nombre de personnes", null, 50, TextField.NUMERIC);
    //TextField tpassword= new TextField("Mot de passe", null, 50, TextField.ANY);
    Command ajouter = new Command("Ajouter", Command.OK, 0);
    Command retour = new Command("retour", Command.CANCEL, 0);
    
  
    Connexion cx = new Connexion();
    Image reserv;
    Alert Aj;
    ChoiceGroup choix;
    String dt;

    public void startApp() {
         try {
          reserv = Image.createImage("/reservation.jpg");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
         Date d = new Date();
        inscrit.append(reserv);
        
        inscrit.append(theure);
        inscrit.append(tdate_reservation);
       tdate_reservation.setDate(d);
        inscrit.append(tnbrpersonnes);
        inscrit.addCommand(ajouter);
        inscrit.addCommand(retour);
        inscrit.setCommandListener(this);
       // inscrit.append(alert);
        disp.setCurrent(inscrit);
   
    
    
  
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
