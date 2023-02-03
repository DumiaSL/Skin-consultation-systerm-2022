/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courswork;

import java.io.Serializable;

/**
 *
 * @author Dumidu
 */
public class Patient extends Person implements Serializable {
    public static int Total_numbers_patient=0;//Total numbers of patients in center.this is static ine. also size of array we can get this value
    private String patient_id;//this id is unique one
    private int countOfConsultaions=0;

    //constructor
    public Patient(String name, String surname, String date_birthday, String mobile_number, String patient_id) {
        super(name, surname, date_birthday, mobile_number);
        this.patient_id = patient_id;
        Total_numbers_patient++;
    }

    //Getters
    public String getPatient_id() {
        return patient_id;
    }

    public int getCountOfConsultaions() {
        return countOfConsultaions;
    }

    //Setters
    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    //Checking patient s' first consultation
    public boolean checkNew(){
        return this.countOfConsultaions==0;
    }

    //Increment 1+ for one consultation
    public void con_plus(){
        countOfConsultaions++;
    }

    @Override
    public String toString() {
        return super.toString()+
                "In Patient class\n" +
                "Patient ID : " + this.patient_id + "\n" +
                "Patient count of consultations : " + this.countOfConsultaions + "\n"+
                "Memory Location : "+getClass().getName() + "@" + Integer.toHexString(hashCode())+"\n"+
                "-------------------------------------------------------";
    }
}
