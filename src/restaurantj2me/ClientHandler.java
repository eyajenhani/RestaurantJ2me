package restaurantj2me;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ClientHandler extends DefaultHandler{
    private Vector clients;  
    String nomTag = "close";
    String prenTag = "close";
    String mailTag = "close";
    String dateTag = "close";
    String pwdTag = "close";
    public ClientHandler() {
        clients = new Vector();
    }

    public Client[] getClient() {
        Client[] clientss = new Client[clients.size()];
        clients.copyInto(clientss);
        return clientss;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Client currentClient;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("client")) {

            if (currentClient != null) {
                throw new IllegalStateException("already processing a clients");
            }
            currentClient = new Client();
        } else if (qName.equals("mail")) {
            mailTag = "open";
        } else if (qName.equals("pwd")) {
            pwdTag = "open";
        } else if (qName.equals("nom")) {
            nomTag = "open";
        }else if (qName.equals("prenom")) {
            prenTag = "open";
        }else if (qName.equals("date_naissance")) {
            dateTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("client")) {
            // we are no longer processing a <reg.../> tag
            clients.addElement(currentClient);
            currentClient = null;
        }  
        else if (qName.equals("mail")) {
            mailTag = "close";
        }
        else if (qName.equals("pwd")) {
            pwdTag = "close";
        }
        else if (qName.equals("nom")) {
            nomTag = "close";
        }
        else if (qName.equals("prenom")) {
            prenTag = "close";
        }
        else if (qName.equals("date_naissance")) {
            dateTag = "close";
        } 
    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentClient != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (mailTag.equals("open")) {
                String mail = new String(ch, start, length).trim();
                currentClient.setMail(mail);
            } else
                if (pwdTag.equals("open")) {
                String pwd = new String(ch, start, length).trim();
                currentClient.setPassword(pwd);
            } else
                    if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentClient.setNom(nom);
            }
            else
                    if (prenTag.equals("open")) {
                String prenom = new String(ch, start, length).trim();
                currentClient.setPrenom(prenom);
            }
            else
                    if (dateTag.equals("open")) {
                String date_naissance = new String(ch, start, length).trim();
                currentClient.setDate(date_naissance);
            }
        }
    }
    
}
