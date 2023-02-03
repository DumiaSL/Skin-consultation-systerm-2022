package courswork;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static courswork.ConsoleApplication.scan;

public final class  WestminsterSkinConsultationManager implements SkinConsultationManager {

    private   ArrayList<Doctor> doctor_list=new ArrayList<Doctor>();// Doctors stored list.The centre can allocate a maximum of 10 doctors
    public  int doctors_can_hold_center =10;
    private  ArrayList<Patient> patient_list=new ArrayList<>();// Patient stored list.
    private  ArrayList<Consultation> consultation_list=new ArrayList<>();// Consultation stored list.

    public  File doc_file =new File("db_FileSave/doctorList.txt");//file path for doctor stored data
    public  File pa_file =new File("db_FileSave/patientList.txt");//file path for patient stored data
    public  File co_file =new File("db_FileSave/consultationList.txt");//file path for consultation stored data

    public WestminsterSkinConsultationManager() throws IOException {
        //Loading Data from file to array lists
        doctor_list=loadDateFromfiletoarray(doctor_list,doc_file,"Doctor");
        patient_list=loadDateFromfiletoarray(patient_list,pa_file,"Patient");
        consultation_list=loadDateFromfiletoarray(consultation_list,co_file,"Consultation");

        Doctor.Total_numbers_doctors=doctor_list.size();
        Patient.Total_numbers_patient=patient_list.size();
    }

    @Override
    public void addNewDoctor(Doctor doctor) {
        //add new doctor to list
        doctor_list.add(doctor);
    }

    @Override
    public void removeDoctor(String doctor_medical_num) {
        boolean check_doc=false;
        for (Doctor element:doctor_list){
            if (doctor_medical_num.equals(element.getMedical_licence_number())){
                //when found doctor
                check_doc=true;
                //print removed doctor details
                System.out.println("\n-------------------------------------------------------");
                System.out.println("Successfully remove doctor");
                System.out.println("Doctor Center ID : " + element.getDoc_cent_number());
                System.out.println("Doctor name : "+element.getName()+ " "+ element.getSurname());
                System.out.println("Doctor medical licence number : "+ element.getMedical_licence_number());
                System.out.println("Doctor mobile number : "+element.getMobile_number());
                System.out.println("Doctor type : "+element.getDoc_type());
                System.out.println("-------------------------------------------------------");
                //Delete doctor consultations
                String option;
                while (true){
                    System.out.println("Do you want cancel all consultations this doctor?[Y/N]");
                    option=scan.next().toUpperCase();
                    Boolean found=false;
                    if (option.equals("Y")){
                        Boolean is_done=true;
                        while (is_done){
                            is_done=false;
                            for (Consultation co:consultation_list){
                                if (co.getDoctor().getDoc_cent_number()==element.getDoc_cent_number()){
                                    consultation_list.remove(co);
                                    is_done=true;
                                    found=true;
                                    break;
                                }
                            }
                        }

                        if (found){
                            System.out.println("Successfully removed doctor current consultations");
                        }else {
                            System.out.println("No any consultations found this doctor");
                        }
                        break;
                    } else if (option.equals("N")) {
                        break;
                    }else {
                        System.out.println("wrong input");
                    }
                }
                doctor_list.remove(element);
                System.out.println("Total number of doctors in the centre : " + --Doctor.Total_numbers_doctors);

                break;
            }
        }
        if (!(check_doc)){
            System.out.println("No Doctor found ");
        }
        System.out.println();
    }

    @Override
    public void printTheListOfDoctors() {
        //sort doctors base on surname
        Collections.sort(doctor_list);
        System.out.println("Doctor Details");
        System.out.println("Total number of doctors in the centre : " + Doctor.Total_numbers_doctors);
        for (Doctor element:doctor_list){
            System.out.println("-------------------------------------------------------");
            System.out.println("Doctor Center ID : " + element.getDoc_cent_number());
            System.out.println("Doctor name : Dr."+element.getName()+ " "+ element.getSurname());
            System.out.println("Doctor medical licence number : "+ element.getMedical_licence_number());
            System.out.println("Doctor Birthday : "+ element.getDate_birthday());
            System.out.println("Doctor mobile number : "+element.getMobile_number());
            System.out.println("Doctor type : "+ element.getDoc_type());
            System.out.println("-------------------------------------------------------\n");
        }
        if (doctor_list.size()==0){
            System.out.println("No doctors yet");
            System.out.println();
        }
    }

    @Override
    public void saveInFile(String storeName) throws IOException {
        ObjectOutputStream oos = null;
        File fileName;
        ArrayList listName;
        if (storeName.equals("Doctor")){
            fileName=this.doc_file;
            listName=this.doctor_list;
        }else if (storeName.equals("Patient")){
            fileName=this.pa_file;
            listName=this.patient_list;
        }else {
            fileName=this.co_file;
            listName=this.consultation_list;
        }



        try {
            oos=new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(listName);
            System.out.println("Successful added data!!! in "+ storeName);
        }catch (Exception e){
//            System.out.println(e);
            System.out.println("Error in "+ storeName);
        }finally {
            oos.close();
        }
    }


    @Override
    public void removePatient(String patientID) {
        boolean check_pai=false;
        for (Patient element:patient_list){
            if (patientID.equals(element.getPatient_id())){
                //when found doctor
                check_pai=true;
                //print removed doctor details
                System.out.println("\n-------------------------------------------------------");
                System.out.println("Successfully remove Patient");
                System.out.println("Patient ID : " + element.getPatient_id());
                System.out.println("Patient name : "+element.getName()+ " "+ element.getSurname());
                System.out.println("Patient Birthday : "+ element.getDate_birthday());
                System.out.println("Patient mobile number : "+element.getMobile_number());
                System.out.println("Patient count Consultation : "+element.getCountOfConsultaions());
                System.out.println("-------------------------------------------------------");
                //Delete Patient consultations
                String option;
                while (true){
                    System.out.println("Do you want cancel all consultations this Patient?");
                    option=scan.next().toUpperCase();
                    Boolean found=false;
                    if (option.equals("Y")){
                        Boolean is_done=true;
                        while (is_done){
                            is_done=false;
                            for (Consultation co:consultation_list){
                                if (co.getPatient().getPatient_id().equals(element.getPatient_id())){
                                    consultation_list.remove(co);
                                    is_done=true;
                                    found=true;
                                    break;
                                }
                            }
                        }

                        if (found){
                            System.out.println("Successfully removed Patient current consultations");
                        }else {
                            System.out.println("No any consultations found this Patient");
                        }
                        break;
                    } else if (option.equals("N")) {
                        break;
                    }else {
                        System.out.println("wrong input");
                    }
                }
                doctor_list.remove(element);
                System.out.println("Total number of Patient in the centre : " + patient_list.size());

                //if want to save
                // saveInFile(pa_file,patient_list,"Patient");
                break;
            }
        }
        if (!(check_pai)){
            System.out.println("No Patient found ");
        }
        System.out.println();
    }

    @Override
    public void printTheListOfPatient() {
        System.out.println("Patient Details");
        System.out.println("Total number of Patient in the centre : " + patient_list.size());
        for (Patient patient:patient_list){
            System.out.println("\n-------------------------------------------------------");
            System.out.println("~~~~ Patient ~~~~");
            System.out.println("Patient ID : " + patient.getPatient_id());
            System.out.println("Patient name : "+patient.getName()+ " "+ patient.getSurname());
            System.out.println("Patient Birthday : "+ patient.getDate_birthday());
            System.out.println("Patient mobile number : "+patient.getMobile_number());
            System.out.println("Patient count Consultation : "+patient.getCountOfConsultaions());
            System.out.println("-------------------------------------------------------");
        }
        if (consultation_list.size()==0){
            System.out.println("No Patient yet");
        }
        System.out.println();
    }

    @Override
    public void removeConsultation(String patientID) throws IOException {
        boolean check_flag=false;
        for (Consultation co:consultation_list){
            if (patientID.equals(co.getPatient().getPatient_id())){
                check_flag=true;
                System.out.println("-------------------------------------------------------");
                System.out.println("---Consultation---");
                System.out.println("Doctor Center ID : " + co.getDoctor().getDoc_cent_number() +"  Doctor name : Dr."+ co.getDoctor().getName()+ " "+ co.getDoctor().getSurname());
                System.out.println("Patient ID : "+ co.getPatient().getPatient_id() +"  Patient name : "+ co.getPatient().getName()+ " "+ co.getPatient().getSurname());
                System.out.println("Consultation Date : "+ co.getConsultation_date() +"  Consultation Time : "+co.getConsultation_time());
                System.out.println("Consultation hours : "+ co.getApp_hours() +"  Consultation cost : "+co.getCost());
                System.out.println("Consultation note : "+co.getNotes());
                System.out.println("-------------------------------------------------------\n");
                while (true){
                    System.out.print("Enter Yes[Y] to remove above Consultation or Next[N] : ");
                    String remove_confirm=scan.next().toUpperCase();
                    if (remove_confirm.equals("Y")){
                        consultation_list.remove(co);
                        System.out.println("Successfully removed Consultation");
                        break;
                    } else if (remove_confirm.equals("N")) {
                        break;
                    }else {
                        System.out.println("Wrong Input");
                    }
                }
//                if want to save
//                manager.saveInFile("Consultation");
            }
        }

        if (!(check_flag)){
            System.out.println("Not found Consultation");
        }
        System.out.println();
    }

    @Override
    public void printTheListOfConsultation() {
        System.out.println("Consultation Details");
        System.out.println("Total number of Consultation in the centre : " + consultation_list.size());
        for (Consultation co:consultation_list){
            System.out.println("-------------------------------------------------------");
            System.out.println("---Consultation---");
            System.out.println("Doctor Center ID : " + co.getDoctor().getDoc_cent_number() +"  Doctor name : Dr."+ co.getDoctor().getName()+ " "+ co.getDoctor().getSurname());
            System.out.println("Patient ID : "+ co.getPatient().getPatient_id() +"  Patient name : "+ co.getPatient().getName()+ " "+ co.getPatient().getSurname());
            System.out.println("Consultation Date : "+ co.getConsultation_date() +"  Consultation Time : "+co.getConsultation_time());
            System.out.println("Consultation hours : "+ co.getApp_hours() +"  Consultation cost : "+co.getCost());
            System.out.println("Consultation Encrypt note : "+co.getEncNotes());
            System.out.println("-------------------------------------------------------\n");
        }
        if (consultation_list.size()==0){
            System.out.println("No Consultation yet");
        }
        System.out.println();
    }

    @Override
    public void generateReport() {
        // display time and date
        Date date = new Date();

        try
        {
            FileWriter myWriter = new FileWriter("Report/report_of_doctor.txt");
            myWriter.write("----------------------------------Doctor Details------------------------------------"+"\n");
            myWriter.write(String.format("Logs updated Date/Time : %tc", date )+"\n\n");

            myWriter.write("Total number of doctors in the centre : " + Doctor.Total_numbers_doctors+"\n");
            for (Doctor element:doctor_list){
                myWriter.write("-------------------------------------------------------"+"\n");
                myWriter.write("Doctor Center ID : " + element.getDoc_cent_number()+"\n");
                myWriter.write("Doctor name : Dr."+element.getName()+ " "+ element.getSurname()+"\n");
                myWriter.write("Doctor medical licence number : "+ element.getMedical_licence_number()+"\n");
                myWriter.write("Doctor Birthday : "+ element.getDate_birthday()+"\n");
                myWriter.write("Doctor mobile number : "+element.getMobile_number()+"\n");
                myWriter.write("Doctor type : "+ element.getDoc_type()+"\n");
                myWriter.write("-------------------------------------------------------\n"+"\n");
            }
            if (doctor_list.size()==0){
                myWriter.write("No doctors yet"+"\n");
            }
            myWriter.write("--------------------------------------------------------------------------------------"+"\n");
            myWriter.close();
            System.out.println("Successfully Created Report.");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println();
    }

    @Override
    public ArrayList<Doctor> returnDoctorArrayList() {
        return doctor_list;
    }

    @Override
    public ArrayList<Patient> returnPatientArrayList() {
        return patient_list;
    }

    @Override
    public ArrayList<Consultation> returnConsultationArrayList() {
        return consultation_list;
    }

    @Override
    public int return_memebrs_can_hold_center() {
        return doctors_can_hold_center;
    }

    public static <T> ArrayList<T> loadDateFromfiletoarray(ArrayList<T> listName,File filename,String storename) throws IOException {
        ObjectInputStream ois=null;
        Boolean checkFile=false;

        try {
            if (filename.isFile()){
                if ((filename).length()==0) {
                    System.out.println("No any old data in store "+storename);//Store meaning to file
                }else {
                    ois=new ObjectInputStream(new FileInputStream(filename));
                    listName= (ArrayList<T>) ois.readObject();
                    checkFile=true;
                }
            }else {
                System.out.println("Cant find store "+storename);
            }
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Error in "+storename);
        }finally {
            if (checkFile){
                ois.close();
            }
        }
        return listName;
    }

}
