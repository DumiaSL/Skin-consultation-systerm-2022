package courswork;

import java.io.Serializable;

/**
 *
 * @author Dumidu
 */
public class Consultation implements Serializable {

    public static int costforfirstcon=15;// first consultation is £15 per hour
    public static int costfornrlcon=25;//£25 per hour
    private String consultation_date;
    private String consultation_time;

    private Patient patient;

    private Doctor doctor;

    private int cost;
    private String notes;

    private int app_hours;

    //constructor
    public Consultation(String consultation_date, String consultation_time, int app_hours, String notes,Doctor doctor,Patient patient) {
        this.consultation_date = consultation_date;
        this.consultation_time = consultation_time;
        this.app_hours=app_hours;
        this.notes = encrypt(notes);
        this.doctor=doctor;
        this.patient=patient;
        calculate_cost(patient);
        patient.con_plus();
    }

    //Setters
    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setConsultation_time(String consultation_time) {
        this.consultation_time = consultation_time;
    }

    public void setConsultation_date(String consultation_date) {
        this.consultation_date = consultation_date;
    }

    public void setNotes(String notes) {
        this.notes = encrypt(notes);
    }

    //Getters

    public String getConsultation_time() {
        return consultation_time;
    }

    public int getCost() {
        return cost;
    }

    public Patient getPatient() {
        return patient;
    }

    public int getApp_hours() {
        return app_hours;
    }

    public void setApp_hours(int app_hours) {
        this.app_hours = app_hours;
        calculate_cost(this.patient);
    }

    public String getConsultation_date() {
        return consultation_date;
    }

    public String getNotes() {
        return decrypt(notes);
    }
    public String getEncNotes() {
        return notes;
    }

    public Doctor getDoctor() {
        return doctor;
    }


    private void calculate_cost(Patient patient){
        if (patient.checkNew()){
            this.cost=this.app_hours*costforfirstcon;
        }else {
            this.cost=this.app_hours*costfornrlcon;
        }
    }

    // Encryption code
    public static String encrypt(String plainText) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            encryptedText.append((char) (c + 5));
        }
        return encryptedText.toString();
    }

    // Decryption code
    public static String decrypt(String encryptedText) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < encryptedText.length(); i++) {
            char c = encryptedText.charAt(i);
            decryptedText.append((char) (c - 5));
        }
        return decryptedText.toString();
    }

    @Override
    public String toString() {
        return "----------------------------------------------------------------------------------------------------------------------------\n" +
                "In Consultation \n"+
                "consultation date : " + consultation_date + '\n' +
                "consultation time : " + consultation_time + '\n' +
                "----Doctor----"+"\n"+
                doctor+ "\n" +
                "----Patient----"+"\n"+
                patient+ "\n" +
                "cost : "+cost+ "\n" +
                "Encrypt note : "+notes+ "\n" +
                "Real note : "+getNotes()+ "\n" +
                "appointment hours : "+app_hours+ "\n" +
                "------------------------------------------------------------------------------------------------------------------------------";
    }
}
