/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restaurantj2me;

import java.io.IOException;
import java.util.Date;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;

/**
 * @author Eya
 */
public class inscription extends MIDlet implements CommandListener,Runnable{
    Form inscrit = new Form("                      Formulaire d'inscription");
    Image alert;
    Image inscription;
    Alert erreur; 
    Display dis =  Display.getDisplay(this); 
    TextField tnom= new TextField("Nom", null, 50, TextField.ANY);
    TextField tprenom= new TextField("Prenom", null, 50, TextField.ANY);
    TextField tmail= new TextField("Login", null, 50, TextField.ANY);
    TextField tpassword= new TextField("Mot de passe", null, 50, TextField.ANY);
    DateField date = new DateField("Date naissance", DateField.DATE);
    Command ajouter = new Command("inscrire", Command.OK, 0);
    Command retour = new Command("retour", Command.CANCEL, 0);
    String type_client;
    String resultat;
    Connexion cx = new Connexion();
    Image Ajout;
    Alert Aj;
    ChoiceGroup choix;
    String dt;

    public void startApp() {
        choix = new ChoiceGroup(null,List.MULTIPLE);
          try {
            alert= Image.createImage("/erreur.gif");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
          erreur = new Alert(null,null, alert, AlertType.ERROR);
         try {
          Ajout = Image.createImage("/confirmer.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
          Aj = new Alert(null,"                  Ajout éffectué",Ajout, AlertType.INFO);
              Date d = new Date();
                  try {
            inscription= Image.createImage("/inscription.jpg");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    date.setDate(d);
    inscrit.append(inscription);
    inscrit.setCommandListener(this);
    inscrit.addCommand(ajouter);
    inscrit.addCommand(retour);
    inscrit.append(tnom);
    inscrit.append(tprenom);
    inscrit.append(tmail);
    inscrit.append(tpassword);
    inscrit.append(date);
    choix.append("restaurateur", null);
    choix.append("client", null);
    inscrit.append(choix);
    dis.setCurrent(inscrit);
          
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
       if(c == ajouter){
           for (int i = 0; i < choix.size(); i++) {
                if(choix.isSelected(i)){
                   type_client = choix.getString(i);
                    System.out.println(type_client);
                }
                
           dt =date.getDate().toString().replace(' ','*');
           if((tmail.getString().length()==0)||(tpassword.getString().length()==0)||(tnom.getString().length()==0)||(tprenom.getString().length()==0)||(type_client==""))
          {
          erreur.setString("il y a un champ vide");
          dis.setCurrent(erreur);
          }
           validate(); 
            }
                               
        }
    }

    public void run() {
        
       resultat = cx.insertionClient (tmail.getString(), tpassword.getString(), tnom.getString(), tprenom.getString(), type_client, dt) ;
    if(resultat.equalsIgnoreCase("valide"))
            {
            dis.setCurrent(Aj);
            } 
    }
    public void validate() {
        Thread t = new Thread(this);
        t.start();
    }    
    
}
