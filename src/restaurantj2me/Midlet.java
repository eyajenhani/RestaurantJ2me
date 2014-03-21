package restaurantj2me;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.Date;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.*;

/**
 * @author Eya
 */
public class Midlet extends MIDlet implements CommandListener,Runnable{
Display dis =  Display.getDisplay(this);  
Connexion cx = new Connexion();
String resultat;
String reponse;
  Client[] clients;

//Paged'accueil
    Form accueil = new Form("                           RESTO-TUNISIE");
    Image imageAccueil;
    Image alert;
    Alert erreur;  
    TextField mail= new TextField("Login", null, 50, TextField.ANY);
    TextField pwd= new TextField("Mot de passe", null, 50, TextField.PASSWORD);
    Command cnx = new Command("Connecter", Command.OK, 0);
    
    //client profil
    Form profil = new Form("                           Profil");
    StringItem snom = new StringItem("Nom", null);
    StringItem sprenom = new StringItem("Prénom", null);
    StringItem smail = new StringItem("Mail", null);
    StringItem spassword = new StringItem("Mot de passe", null);
    StringItem sdate= new StringItem("Date naissance", null);
    
    Ticker tick_client = new Ticker("Vous êtes le bienvenus dans votre espace");
    Command modifier_p = new Command("Modifier profil", Command.OK, 0);
    Command ch_nom= new Command("Chercher par nom", Command.OK, 2);
    Command ch_sp= new Command("Chercher par Spécialité", Command.OK, 3);
    Command ch_type = new Command("Chercher par type", Command.OK, 4);
    Command t_soire = new Command("Trouver votre soiré", Command.OK, 5);
    Command reservation = new Command("Consulter vos réservation", Command.OK, 6);
    Command nouv = new Command("Nouveautés", Command.OK, 1);
    Command deco = new Command("Deconnecter", Command.CANCEL, 0);
    //modifier profil
       TextField tnom= new TextField("Nom", null, 50, TextField.ANY);
    TextField tprenom= new TextField("Prenom", null, 50, TextField.ANY);
    TextField tmail= new TextField("Login", null, 50, TextField.ANY);
    TextField tpassword= new TextField("Mot de passe", null, 50, TextField.ANY);
    DateField date = new DateField("Date naissance", DateField.DATE);
    
    
    
    
    
    
    
    
    
    public void startApp() {
        //paged'accueil
         try {
            alert= Image.createImage("/erreur.gif");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
erreur = new Alert(null,"votre mail ou mot de passe est invalide", alert, AlertType.ERROR);
          try {
             imageAccueil = Image.createImage("/logo.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
      accueil.append(imageAccueil);
      accueil.append(mail);
      accueil.append(pwd);
      accueil.setCommandListener(this);
      accueil.addCommand(cnx);
      dis.setCurrent(accueil);
      //Profil
    profil.setTicker(tick_client);
    profil.append(snom);
    profil.append(sprenom);
    profil.append(smail);
    profil.append(spassword);
    profil.append(sdate);
    profil.setCommandListener(this);
    profil.addCommand(t_soire);
    profil.addCommand(modifier_p);
    profil.addCommand(ch_nom);
    profil.addCommand(ch_type);
    profil.addCommand(ch_sp);
    profil.addCommand(reservation);
    profil.addCommand(nouv);
    profil.addCommand(deco);
    //modifier profil
     Date d = new Date();
    date.setDate(d);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
      if (c == cnx) {
          reponse="connexionchaine";
            validate();
        }
    }

       public void run() {
           if (reponse=="connexionchaine")
           {
      resultat=cx.ConnexionChaine(mail.getString(), pwd.getString());
 if(resultat.equalsIgnoreCase("invalide"))
         {
         dis.setCurrent(erreur);
         }        
 else if(resultat.equalsIgnoreCase("client"))
 {
    clients= cx.ConnexionListe(mail.getString(), pwd.getString());
            if (clients.length > 0) {
                for (int i = 0; i < clients.length; i++) {
                    snom.setText(clients[i].getNom());
                    sprenom.setText(clients[i].getPrenom());
                    smail.setText(clients[i].getMail());
                    spassword.setText(clients[i].getPassword());
                     sdate.setText(clients[i].getDate());
                    
                }
            }

 dis.setCurrent(profil);
 } }           
 else if (reponse=="connexionchaine")
               {
       }
           
}
       public void validate() {
        Thread t = new Thread(this);
        t.start();
    }
       
}
