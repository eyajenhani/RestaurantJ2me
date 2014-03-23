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
    
    // profil client
    Form profilc = new Form("                           Profil");
    StringItem snom = new StringItem("Nom", null);
    StringItem sprenom = new StringItem("Prénom", null);
    StringItem smail = new StringItem("Mail", null);
    StringItem spassword = new StringItem("Mot de passe", null);
    StringItem sdate= new StringItem("Date naissance", null);
    Ticker tick_client = new Ticker("Vous êtes le bienvenus dans votre espace");
    Command modifier_p = new Command("Modifier profil", Command.OK, 0);
    Command deco = new Command("Deconnecter", Command.CANCEL, 0);
    // profil restaurateur
    Form profilr = new Form("                           Profil");
    Command identifier = new Command("identifier votre espace", Command.SCREEN, 0);
    //modifier profil
    Image confirmer;
    Alert ok;
    Form modifier_profil = new Form("                           Modifier Profil");
    TextField tnom= new TextField("Nom", null, 50, TextField.ANY);
    TextField tprenom= new TextField("Prenom", null, 50, TextField.ANY);
    TextField tmail= new TextField("Login", null, 50, TextField.ANY);
    TextField tpassword= new TextField("Mot de passe", null, 50, TextField.ANY);
    DateField date = new DateField("Date naissance", DateField.DATE);
    Command modifier = new Command("Modifier", Command.OK,0);
    Command retour = new Command("Retoure", Command.CANCEL, 0);

      
    public void startApp() {
        //paged'accueil
         try {
            alert= Image.createImage("/erreur.gif");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
erreur = new Alert(null,null, alert, AlertType.ERROR);
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
      //Profil client

      
      
      
      
       //Profil restaurateur  
    profilr.addCommand(identifier);
  
    //modifier profil
          try {
          confirmer = Image.createImage("/confirmer.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
          ok = new Alert(null,"        Taitement effectuer avec succé", confirmer, AlertType.INFO);

    Date d = new Date();
    date.setDate(d);
    modifier_profil.setCommandListener(this);
    modifier_profil.addCommand(modifier);
    modifier_profil.addCommand(retour);
    modifier_profil.setTicker(tick_client);
    modifier_profil.append(tnom);
    modifier_profil.append(tprenom);
    modifier_profil.append(tmail);
    modifier_profil.append(tpassword);
    modifier_profil.append(date);
     
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
      else if (c==modifier_p)
      {
      dis.setCurrent(modifier_profil);
      }
      else if(c == modifier)
      {
          if((tmail.getString().length()==0)||(tpassword.getString().length()==0)||(tnom.getString().length()==0)||(tprenom.getString().length()==0))
          {
          erreur.setString("il y a un champ vide");
          dis.setCurrent(erreur);
          }
          else
          {
       
      reponse="modifier";
            validate();
      }
      }
       else if((c == retour)&&(d==modifier_profil))
      {Profil();
      dis.setCurrent(profilc);
      }
         else if(c ==deco)
      {
     dis.setCurrent(accueil);
     mail.setString("");
     pwd.setString("");
      }
        else if(c ==identifier)
      {
          
    dis.setCurrent(new makerRestaurent(this, d));
      }
    }

       public void run() {
           if (reponse=="connexionchaine")
           {
      resultat=cx.ConnexionChaine(mail.getString(), pwd.getString());
 if(resultat.equalsIgnoreCase("invalide"))
         {erreur.setString("votre mail ou mot de passe est invalide");
         dis.setCurrent(erreur);
         }        
 else if(resultat.equalsIgnoreCase("client"))
 {
   Profil();
   AjoutProfil(profilc);
  dis.setCurrent(profilc);   
           
 }
  else if(resultat.equalsIgnoreCase("restaurateur"))
 {  
     Profil(); 
     AjoutProfil(profilr);
    dis.setCurrent(profilr);    
           
 }   
 else if (reponse=="modifier")
               {
                   String d =date.getDate().toString().replace(' ','*');                 
resultat=cx.Modification_profil (mail.getString(),pwd.getString(),tmail.getString(),tpassword.getString(),tnom.getString(),tprenom.getString(),d);     
    dis.setCurrent(ok);
    
               }
           
}
       }
         public void Profil()
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
} 
            public void AjoutProfil(Form f)
           {
    f.setTicker(tick_client);
    f.append(snom);
    f.append(sprenom);
    f.append(smail);
    f.append(spassword);
    f.append(sdate);
    f.setCommandListener(this);
    f.addCommand(modifier_p);
    f.addCommand(deco);
} 
       public void validate() {
        Thread t = new Thread(this);
        t.start();
    }
       
}
