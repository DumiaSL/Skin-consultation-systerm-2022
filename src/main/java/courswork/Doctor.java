package courswork;

import java.io.Serializable;

/**
 *
 * @author Dumidu
 */

public class Doctor extends Person implements Comparable, Serializable {
    public static int Total_numbers_doctors=0;//Total numbers in center.this is static ine. also size of array we can get this value
    private int doc_cent_number;//doctor unique number in center.Like center Id
    private String medical_licence_number;
    private String doc_type; //Type of  specialisation

    //constructor
    public Doctor(String name, String surname, String date_birthday, String mobile_number, int doc_cent_number, String medical_licence_number, String doc_type) {
        super(name, surname, date_birthday, mobile_number);
        this.doc_cent_number = doc_cent_number;
        this.medical_licence_number = medical_licence_number;
        this.doc_type = doc_type;
        Total_numbers_doctors++;
    }

    //Getters
    public String getMedical_licence_number() {
        return medical_licence_number;
    }

    public String getDoc_type() {
        return doc_type;
    }

    public int getDoc_cent_number() {
        return doc_cent_number;
    }

    //Setters
    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }
    public void setMedical_licence_number(String medical_licence_number) {
        this.medical_licence_number = medical_licence_number;
    }

    //compare using doctor surname
    @Override
    public int compareTo(Object o) {
        Doctor doc=(Doctor) o;
        if (this.getSurname().compareToIgnoreCase(doc.getSurname())>0){
            return 1;
        }else if (this.getSurname().compareToIgnoreCase(doc.getSurname())<0){
            return -1;
        }else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return super.toString()+
                "In Doctor class\n" +
                "Doctor center ID : " + this.doc_cent_number + "\n" +
                "Doctor medical_licence_number : " + this.medical_licence_number + "\n"+
                "Doctor medical licence number : " + this.medical_licence_number + "\n" +
                "Doctor type : " + this.doc_type + "\n" +
                "Memory Location : "+getClass().getName() + "@" + Integer.toHexString(hashCode())+"\n"+
                "-------------------------------------------------------\n";
    }
}
