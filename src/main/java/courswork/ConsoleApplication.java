/*Maddumage Dumidu Fernando - w1870577 / 20200515
        All copyright reserved.*/

package courswork;

import courswork.grafic.Gui_Main;
import courswork.grafic.Gui_Splash_Screen;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class ConsoleApplication {

    public static Scanner scan=new Scanner(System.in);
    public static Random ran=new Random();

    public static SkinConsultationManager manager;

    static {
        try {
            manager = new WestminsterSkinConsultationManager();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        //Start program
        new Gui_Splash_Screen();
    }

    public static void consolePart() throws Exception {

        // start console part
        String menu_option_input;

        System.out.println("\033[31mDoctor part : \033[0m");
        while (true) {
            System.out.println("\033[31m|~~~ Console Menu ~~~|\033[0m");
            System.out.println("Enter [A] to Add a new doctor");
            System.out.println("Enter [D] to Delete a doctor");
            System.out.println("Enter [P] to Print the list of the doctors");
            System.out.println("Enter [S] to Save in a file ");
            System.out.println("Enter [G] Start Patient GUI Part");
            System.out.println("Enter [M] For More Options");
            System.out.println("Enter [Q] to Quit the Program");

            System.out.print("\u001B[33mSelect menu option : \033[0m");
            menu_option_input = scan.next().toUpperCase();
            System.out.println();
            if (menu_option_input.equals("A")) {
                addNewDoctor();
            } else if (menu_option_input.equals("D")) {
                System.out.print("Enter doctor medical licence number want to remove : ");
                manager.removeDoctor(scan.next());
            } else if (menu_option_input.equals("P")) {
                manager.printTheListOfDoctors();
            } else if (menu_option_input.equals("S")) {
                manager.saveInFile("Doctor");
                System.out.println();
            }else if (menu_option_input.equals("G")) {
                new Gui_Main().tablePart_Panel();
                break;
            }else if (menu_option_input.equals("M")) {
                more_option();
            }else if (menu_option_input.equals("Q")) {
                System.exit(0);
            }else {
                System.out.println("Entered Wrong input.Try again\n");
            }
        }
    }


    //Add new doctor
    public static void addNewDoctor(){
        //Checking center doctors full
        if (manager.returnDoctorArrayList().size()<manager.return_memebrs_can_hold_center()) {
            System.out.print("\u001B[33mAdding new Doctor\033[0m \n");
            System.out.print("Enter doctor first name: ");
            String docName = scan.next();
            System.out.print("Enter  Dr." + docName + " surname : ");
            String docSurName = scan.next();

            System.out.print("Enter doctor Dr." + docName + " doctor specialisation type : ");
            String docType = scan.next();

            //create new doctor
            Doctor doctor = new Doctor(capitalize(docName), capitalize(docSurName), get_Birthday(docName), get_mobile(docName), get_DocNum(), get_MedicalNum(docName), capitalize(docType));

            manager.addNewDoctor(doctor);

            //print new doctor details
            System.out.println("\n-------------------------------------------------------");
            System.out.println("Successfully add doctor");
            System.out.println("Doctor Center ID : " + doctor.getDoc_cent_number());
            System.out.println("Doctor name : " + doctor.getName() + " " + doctor.getSurname());
            System.out.println("Doctor medical licence number : " + doctor.getMedical_licence_number());
            System.out.println("Doctor mobile number : " + doctor.getMobile_number());
            System.out.println("Doctor type : " + doctor.getDoc_type());
            System.out.println("-------------------------------------------------------");
            System.out.println("Total number of doctors in the centre : " + Doctor.Total_numbers_doctors);

        }else {
            System.out.println("Doctor list full.");
        }
        System.out.println();

        //if want to save
        //saveInFile(doc_file,doctor_list,"Doctor");
    }

    //First letter capital in String
    public static final String capitalize(String str) {
        if (str == null || str.length() == 0) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


    //return valid user birthday
    public static String get_Birthday(String name){
        String birthday;
        DateTimeFormatter formater= DateTimeFormatter.ofPattern("yyyy/MM/dd");

        while (true){
            System.out.print("Enter Dr." + name + " Birthday [ YYYY/MM/DD ] : ");
            birthday=scan.next();
            try {
                formater.parse(birthday);
                int user_year=Integer.parseInt(""+birthday.charAt(0)+birthday.charAt(1)+birthday.charAt(2)+birthday.charAt(3));
                int current_year= Calendar.getInstance().get(Calendar.YEAR);
                //Checking in (75-20) years birth year
                if (( user_year >= (current_year-75)) && (user_year < (current_year-20))){
                    break;
                }else {
                    System.out.println("use  "+(current_year-75)+"-"+(current_year-20));
                }
            }catch (Exception e){
                System.out.println("Invalid date type");
            }
        }
        return birthday;
    }

    //get unique  valid doctor center number
    public static int  get_DocNum(){

        int doc_cent_number;

        if (manager.returnDoctorArrayList().size() == 0) {
            doc_cent_number=ran.nextInt(0, 10);
        } else {
            outerloop:
            while (true) {
                Boolean docnum_flag=true;
                doc_cent_number = ran.nextInt(0, 10);
                for (Doctor element : manager.returnDoctorArrayList()) {
                    if (doc_cent_number == element.getDoc_cent_number()) {
                        docnum_flag=false;
                        break ;
                    }
                }
                if (docnum_flag){
                    break ;
                }
            }
        }
        return doc_cent_number;
    }

    //get valid doctor medical number
    public static String get_MedicalNum(String name){
        String docLicenceNumber;

        if (manager.returnDoctorArrayList().size() == 0) {
            System.out.print("Enter  Dr." + name + " medical licence number : ");
            docLicenceNumber = "D"+scan.next();
        } else {
            while (true) {
                boolean check_flag = true;
                System.out.print("Enter doctor Dr." + name + " medical licence number : ");
                docLicenceNumber = "D"+scan.next();
                for (Doctor element : manager.returnDoctorArrayList()) {
                    if (docLicenceNumber.equals(element.getMedical_licence_number())) {
                        System.out.println("Medical licence number repeat");
                        check_flag = false;
                        break;
                    } else {
                        check_flag = true;
                    }
                }
                if (check_flag) {
                    break;
                }
            }
        }
        return docLicenceNumber;
    }

    //get valid mobile number
    public static String get_mobile(String name){
        String docMobileNum;
        while (true){
            System.out.print("Enter  Dr." + name + " mobile number : ");
            docMobileNum = scan.next();
            try{
                Long.parseLong(docMobileNum);
                if (docMobileNum.length()==10){
                    break;
                }else {
                    System.out.println("Use 10 digits");
                }
            }catch (Exception e){
                if (String.valueOf(docMobileNum.charAt(0)).equals("+")){
                    if (docMobileNum.length()==12){
                        break;
                    }else {
                        System.out.println("For + number use 12 digits");
                    }
                }else {
                    System.out.println("Use correct number");
                }
            }
        }
        return docMobileNum;
    }

    //more option part
    public static void more_option() throws IOException {
        String menu_option_input2;
        while (true) {
            System.out.println("\033[31m|~~~ More option Menue ~~~|\033[0m");
            System.out.println("Enter [R] to Generate doctor report");
            System.out.println("Enter [A] to Delete a Patient");
            System.out.println("Enter [P] to Print the list of the Patient");
            System.out.println("Enter [S] to Save in a file Patient");
            System.out.println("Enter [T] to Delete a Consultation");
            System.out.println("Enter [H] to Print the list of the Consultation");
            System.out.println("Enter [K] to Save in a file Consultation");
            System.out.println("Enter [Q] Go back main menu");

            System.out.print("\u001B[33mSelect menu option : \033[0m");
            menu_option_input2 = scan.next().toUpperCase();
            System.out.println();
            if (menu_option_input2.equals("A")) {
                System.out.print("Enter Patient ID want to remove : ");
                manager.removePatient(scan.next());
            } else if (menu_option_input2.equals("P")) {
                manager.printTheListOfPatient();
            } else if (menu_option_input2.equals("S")) {
                manager.saveInFile("Patient");
                System.out.println();
            } else if (menu_option_input2.equals("T")) {
                System.out.print("Enter Patient ID number : ");
                manager.removeConsultation(scan.next());
            }else if (menu_option_input2.equals("H")) {
                manager.printTheListOfConsultation();
            }else if (menu_option_input2.equals("K")) {
                manager.saveInFile("Consultation");
                System.out.println();
            } else if (menu_option_input2.equals("R")) {
                manager.generateReport();
            }else if (menu_option_input2.equals("Q")) {
                break;
            }
        }
    }
}
