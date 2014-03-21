/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restaurantj2me;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author Eya
 */
public class Connexion {


//    Display dis =  Display.getDisplay(this);    
    String url;
    Client[] clients;

   public String ConnexionChaine(String login, String pwd) 
    {
       
        HttpConnection hc;
        DataInputStream dc;
        StringBuffer str = new StringBuffer("");
        
        String nom1, pd, parametre;
        nom1 = login;
        pd = pwd;
        parametre = "mail=" + nom1 + "&password=" + pd.replace(' ', '+');
        try {
             url = "http://127.0.0.1/J2ME/login.php?";
            hc = (HttpConnection) Connector.open(url + parametre);
            dc = new DataInputStream(hc.openInputStream());
            int ch;
            while ((ch = dc.read()) != -1) {
                str.append((char) ch);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String resultat = str.toString().trim();
        return resultat;
   
    }
       public Client[] ConnexionListe(String login,String pwd) {
        try {
           String parametre; 
             parametre = "mail=" + login + "&password=" + pwd.replace(' ', '+');
            // this will handle our XML
            ClientHandler clientsHandler = new ClientHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            url = "http://127.0.0.1/J2ME/RecuperationProfil.php?";
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection  hc = (HttpConnection) Connector.open(url + parametre);
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, clientsHandler);
            // display the result
            clients = clientsHandler.getClient();
        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        
        return clients;
    }

     public String Modification_profil(String login, String pwd,String nlogin, String npwd,String nom, String prenom,String date) 
    {
       
        HttpConnection hc;
        DataInputStream dc;
        StringBuffer str = new StringBuffer("");
        String parametre;  
        parametre = "mail=" + login + "&pwd=" + pwd + "&nmail=" + nlogin + "&npwd=" + npwd + "&nom=" + nom + "&prenom=" + prenom + "&date=" + date;
        try {
             url = "http://127.0.0.1/J2ME/modifier_profil.php?";
            hc = (HttpConnection) Connector.open(url + parametre);
            dc = new DataInputStream(hc.openInputStream());
            int ch;
            while ((ch = dc.read()) != -1) {
                str.append((char) ch);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String resultat = str.toString().trim();
        return resultat;
   
    }

    
    }

   
    




