package restaurantj2me;

import java.io.IOException;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.*;

/**
 * @author Eya
 */
public class Midlet extends MIDlet implements CommandListener,Runnable{
Display dis =  Display.getDisplay(this);    
String url;
String resultat;
Connexion t = new Connexion();
 Client[] clients;
//Paged'accueil
    Form accueil = new Form("                           RESTO-TUNISIE");
    Image imageAccueil;
    Image alert;
    Alert erreur;  
    TextField login= new TextField("Login", null, 50, TextField.ANY);
    TextField pwd= new TextField("Mot de passe", null, 50, TextField.PASSWORD);
    Command cnx = new Command("Connecter", Command.OK, 0);
    
    //client profil
    Form profil = new Form("                           Profil");
    StringItem nom = new StringItem("Nom", null);
    StringItem prenom = new StringItem("Prénom", null);
    StringItem mail = new StringItem("Mail", null);
    StringItem password = new StringItem("Mot de passe", null);
    StringItem date= new StringItem("Date", null);
    Ticker tick_client = new Ticker("Vous êtes le bienvenus dans votre espace");
    Command p_modifier = new Command("Modifier profil", Command.OK, 0);
    Command ch_nom= new Command("Chercher par nom", Command.OK, 2);
    Command ch_sp= new Command("Chercher par Spécialité", Command.OK, 3);
    Command ch_type = new Command("Chercher par type", Command.OK, 4);
    Command t_soire = new Command("Trouver votre soiré", Command.OK, 5);
    Command reservation = new Command("Consulter vos réservation", Command.OK, 6);
    Command nouv = new Command("Nouveautés", Command.OK, 1);
    Command deco = new Command("Deconnecter", Command.CANCEL, 0);
    
    
    
    
    
    
    
    
    
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
      accueil.append(login);
      accueil.append(pwd);
      accueil.setCommandListener(this);
      accueil.addCommand(cnx);
      dis.setCurrent(accueil);
      //Profil
    profil.setTicker(tick_client);
    profil.append(nom);
    profil.append(prenom);
    profil.append(mail);
    profil.append(password);
    profil.append(date);
    profil.setCommandListener(this);
    profil.addCommand(t_soire);
    profil.addCommand(p_modifier);
    profil.addCommand(ch_nom);
    profil.addCommand(ch_type);
    profil.addCommand(ch_sp);
    profil.addCommand(reservation);
    profil.addCommand(nouv);
    profil.addCommand(deco);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
      if (c == cnx) {
   
    validate();
 
        }
      if (c == deco) {
            dis.setCurrent(accueil);
        }
    }

       public void run() {
      
        resultat = t.ConnexionChaine(login.getString(),pwd.getString());
       
 if(resultat.equalsIgnoreCase("invalide"))
         {
         dis.setCurrent(erreur);
         }   
 else if(resultat.equalsIgnoreCase("client"))
 {  
     
clients=t.ConnexionListe(mail.getText(),pwd.getString());
        if (clients.length > 0) {
                for (int i = 0; i < clients.length; i++) {
                    nom.setText(clients[i].getNom());
                   prenom.setText(clients[i].getPrenom());
                    mail.setText(clients[i].getMail());
                    password.setText(clients[i].getPassword());
                    date.setText(clients[i].getDate());
                }
            }
        dis.setCurrent(profil);
 }
}
   
       public void validate() {
        Thread th = new Thread(this);
        th.start();
    }
       
}
