/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package restaurantj2me;

/**
 *
 * @author hp
 */
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReservationHandler extends DefaultHandler{
    private Vector reservations ;  
    String num_reservationTag = "close";
    String dateTag = "close";
    String heureTag = "close";
    
    String nbr_personneTag = "close";
    public ReservationHandler() {
        reservations = new Vector();
    }

    public Reservation[] getReservation() {
        Reservation[] reservationss = new Reservation[reservations.size()];
        reservations.copyInto(reservationss);
        return reservationss;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Reservation currentReservation;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("reservation")) {

            if (currentReservation != null) {
                throw new IllegalStateException("already processing a reservations");
            }
            currentReservation = new Reservation();
        } else if (qName.equals("date_reservation")) {
            dateTag = "open";
        } else if (qName.equals("heure")) {
            heureTag = "open";
        } else if (qName.equals("nb_personne")) {
            nbr_personneTag = "open";
        }
       
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("reservation")) {
            // we are no longer processing a <reg.../> tag
            reservations.addElement(currentReservation);
            currentReservation = null;
        }  
        else if (qName.equals("date_reservation")) {
            dateTag = "close";
        }
        else if (qName.equals("heure")) {
            heureTag = "close";
        }
        else if (qName.equals("nb_personne")) {
            nbr_personneTag = "close";
        }
        
    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentReservation != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (dateTag.equals("open")) {
                String date = new String(ch, start, length).trim();
                currentReservation.setDate(date);
            } else
                if (heureTag.equals("open")) {
                String heure = new String(ch, start, length).trim();
                currentReservation.setHeure(heure);
            } else
                    if (nbr_personneTag.equals("open")) {
                String  nbr_personnes = new String(ch, start, length).trim();
                currentReservation.setNbr_personnes(nbr_personnes);
            }
            
            
        }
    }
    
    }

