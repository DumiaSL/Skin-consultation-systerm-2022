/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courswork;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Dumidu
 */
public abstract class Person implements Serializable {
    private String name;
    private String surname;
    private String date_birthday;
    private String mobile_number;

    //constructor
    public Person(String name, String surname, String date_birthday, String mobile_number) {
        this.name = name;
        this.surname = surname;
        this.date_birthday = date_birthday;
        this.mobile_number = mobile_number;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setDate_birthday(String date_birthday) {
        this.date_birthday = date_birthday;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    //Getters
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getDate_birthday() {
        return date_birthday;
    }
    public String getMobile_number() {
        return mobile_number;
    }


    @Override
    public String toString() {
        return "-------------------------------------------------------\n" +
                "In Person class\n" +
                "Name : " + this.name + "\n" +
                "Surname : " + this.surname + "\n" +
                "Birthday : " + this.date_birthday + "\n" +
                "Mobile number : " + this.mobile_number + "\n";
    }
}
