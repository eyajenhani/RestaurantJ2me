/*
 * To change this template, choose Tools | Templates
 * and open thetemplate in the editor.
 */

package restaurantj2me;

/**
 *
 * @author hp
 */
public class Reservation {
     private int num_reservation;
    private String date;
    private String heure;
   private int nbr_personne;
   // private int id_clientabonne;

    public Reservation(int num_reservation, String date, String heure, int nbr_personnes) {
        this.num_reservation = num_reservation;
        this.date = date;
        this.heure = heure;
        this.nbr_personne = nbr_personne;
        //this.id_clientabonne = id_clientabonne;
        //this.id_resto = id_resto;
    }
    private int id_resto;

    public Reservation() {
      
    }

//    public void setId_clientabonné(int id_clientabonné) {
//        this.id_clientabonne = id_clientabonné;
//    }

//   // public void setId_resto(int id_resto) {
//        this.id_resto = id_resto;
//    }

//    public int getId_clientabonné() {
//        return id_clientabonne;
//    }

//    public int getId_resto() {
//        return id_resto;
//    }

    public int getNum_reservation() {
        return num_reservation;
    }

    public void setNum_reservation(int num_reservation) {
        this.num_reservation = num_reservation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String  heure) {
        this.heure = heure;
    }

    public int getNbr_personne() {
        return nbr_personne;
    }

    public void setNbr_personnes(int nbr_personnes) {
        this.nbr_personne = nbr_personne;
    }
    Reservation getReservation() {
        return null;
    }

    
    
//    public int hashCode() {
//        int hash = 3;
//        hash = 59 * hash + this.num_reservation;
//        hash = 59 * hash + Objects.hashCode(this.date);
//        hash = 59 * hash + Objects.hashCode(this.heure);
//        hash = 59 * hash + this.nbr_personnes;
//        return hash;
//    }

    
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Reservation other = (Reservation) obj;
//        if (this.num_reservation != other.num_reservation) {
//            return false;
//        }
//        if (!Objects.equals(this.date, other.date)) {
//            return false;
//        }
//        if (!Objects.equals(this.heure, other.heure)) {
//            return false;
//        }
//        if (this.nbr_personnes != other.nbr_personnes) {
//            return false;
//        }
//        return true;
//    }
//    

    void setNbr_personnes(String nbr_personnes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
