package courswork.grafic;

import courswork.Consultation;
import courswork.Doctor;
import courswork.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import static courswork.ConsoleApplication.manager;
import static courswork.grafic.Gui_Main.Custerm_colour_form;

public class Gui_Edit_Patient {

    ArrayList<Doctor> doctor_list=manager.returnDoctorArrayList();
    ArrayList<Patient> patient_list=manager.returnPatientArrayList();
    ArrayList<Consultation> consultation_list=manager.returnConsultationArrayList();

    JFrame frame =new JFrame();
    JPanel panel1=new JPanel();
    JPanel panel2=new JPanel();
    JPanel panel3=new JPanel();
    JLabel  main_name=new JLabel("Edit Patient Details");

    private JTextField pid=new JTextField();
    private JTextField name=new JTextField();
    private JTextField surname=new JTextField();
    private JTextField bdate=new JTextField();
    private JTextField mobile=new JTextField();

    private ImageIcon succ_i=new ImageIcon("photos/sucess.jpg");
    private JLabel succ_l=new JLabel(succ_i);

    private JLabel name_lable=new JLabel("First Name");
    private JLabel surname_lable=new JLabel("Surname");
    private JLabel date_lable=new JLabel("Date of Birthday");
    private JLabel mobile_lable=new JLabel("Mobile Number");

    private JLabel name_check_lable=new JLabel("Must required");
    private JLabel surname_check_lable=new JLabel("Must required");
    private JLabel date_check_lable=new JLabel("Must required");
    private JLabel mobile_check_lable=new JLabel("Must required");
    private JLabel pid_l=new JLabel("Enter patient ID");
    private JLabel pid_check_lable=new JLabel("Must Filled");

    private JButton back_but=new JButton("Back");
    private JButton get_detail=new JButton("Get details");
    private JButton next=new JButton("Submit");

    private Font font2=new Font("Poppins",Font.BOLD,35);

    private Font font3=new Font("Poppins",Font.BOLD,16);
    private Font font4=new Font("Poppins",Font.BOLD,11);
    private Font font6=new Font("Poppins",Font.BOLD,20);

    private Patient setP;
    public Gui_Edit_Patient(){


        frame.setVisible(true);
        frame.setTitle("Westminster Skin Consultation Manager");
        frame.setSize(900,800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        panel1.setSize(900,800);
        panel1.setBackground(Color.white);
        panel1.setLayout(null);
        panel1.setVisible(true);

        panel2.setSize(900,370);
        panel2.setBackground(Color.pink);
        panel2.setLayout(null);
        panel2.setVisible(false);
        panel2.setLocation(0,250);

        panel3.setSize(900,370);
        panel3.setBackground(Color.white);
        panel3.setLayout(null);
        panel3.setVisible(false);
        panel3.setLocation(0,250);

        main_name.setFont(font2);
        main_name.setSize(700,80);
        main_name.setLocation(270,25);

        succ_l.setSize(240,300);
        succ_l.setLocation(300,0);

        pid_l.setFont(font3);
        pid_l.setSize(150,80);
        pid_l.setLocation(360,100);
        pid.setFont(font6);
        pid.setSize(340,38);
        pid.setLocation(260,190);
        pid.setBackground(Custerm_colour_form);
        pid.setHorizontalAlignment(JTextField.CENTER);
        pid_check_lable.setFont(font4);
        pid_check_lable.setSize(100,80);
        pid_check_lable.setLocation(380,130);
        pid_check_lable.setForeground(Color.RED);
        pid_check_lable.setVisible(false);

        name_lable.setFont(font3);
        name_lable.setSize(150,80);
        name_lable.setLocation(160,10);
        name.setFont(font6);
        name.setSize(340,38);
        name.setLocation(50,100);
        name.setBackground(Custerm_colour_form);
        name.setHorizontalAlignment(JTextField.CENTER);
        name_check_lable.setFont(font4);
        name_check_lable.setSize(100,80);
        name_check_lable.setLocation(160,40);
        name_check_lable.setForeground(Color.RED);
        name_check_lable.setVisible(false);

        surname_lable.setFont(font3);
        surname_lable.setSize(150,80);
        surname_lable.setLocation(600,10);
        surname.setFont(font6);
        surname.setSize(340,38);
        surname.setLocation(480,100);
        surname.setHorizontalAlignment(JTextField.CENTER);
        surname.setBackground(Custerm_colour_form);
        surname_check_lable.setFont(font4);
        surname_check_lable.setSize(100,80);
        surname_check_lable.setLocation(600,40);
        surname_check_lable.setForeground(Color.RED);
        surname_check_lable.setVisible(false);

        date_lable.setFont(font3);
        date_lable.setSize(200,80);
        date_lable.setLocation(140,160);
        bdate.setFont(font6);
        bdate.setSize(340,38);
        bdate.setLocation(50,250);
        bdate.setBackground(Custerm_colour_form);
        bdate.setHorizontalAlignment(JTextField.CENTER);
        date_check_lable.setFont(font4);
        date_check_lable.setSize(200,80);
        date_check_lable.setLocation(160,190);
        date_check_lable.setForeground(Color.RED);
        date_check_lable.setVisible(false);

        mobile_lable.setFont(font3);
        mobile_lable.setSize(150,80);
        mobile_lable.setLocation(590,160);
        mobile.setFont(font6);
        mobile.setSize(340,38);
        mobile.setLocation(480,250);
        mobile.setBackground(Custerm_colour_form);
        mobile.setHorizontalAlignment(JTextField.CENTER);
        mobile_check_lable.setFont(font4);
        mobile_check_lable.setSize(200,80);
        mobile_check_lable.setLocation(600,190);
        mobile_check_lable.setForeground(Color.RED);
        mobile_check_lable.setVisible(false);

        back_but.setSize(150,55);
        back_but.setLocation(55,655);
        back_but.setFont(font3);
        back_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Gui_Patient_Type();
            }
        });

        get_detail.setSize(150,55);
        get_detail.setLocation(350,655);
        get_detail.setFont(font3);
        get_detail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkId();
                if (!(pid.getText().length()==0)&& (find_patientforbtn())){
                    panel2.setVisible(true);
                    next.setVisible(true);
                    for (Patient ele:patient_list){
                        if (pid.getText().equals(ele.getPatient_id())){
                            name.setText(ele.getName());
                            surname.setText(ele.getSurname());
                            bdate.setText(ele.getDate_birthday());
                            mobile.setText(ele.getMobile_number());
                            setP=ele;
                            break;
                        }
                    }
                }
            }
        });

        next.setSize(150,55);
        next.setLocation(650,655);
        next.setFont(font3);
        next.setVisible(false);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkName();
                checkSurName();
                checkDate();
                checkMobile();
                checkId();

                if (!(name.getText().length()==0) && (!(surname.getText().length()==0)) && (!(bdate.getText().length()==0))
                        && !(mobile.getText().length()==0) && !(pid.getText().length()==0) && valid_Birthday() && valid_Mobile() && (find_patientforbtn())){
                    setP.setName(name.getText());
                    setP.setSurname(surname.getText());
                    setP.setDate_birthday(bdate.getText());
                    setP.setMobile_number(mobile.getText());

                    //also updateing Consultation patient details
                    for (Consultation ele:consultation_list){
                        if (ele.getPatient().getPatient_id().equals(pid.getText())){
                            ele.getPatient().setName(name.getText());
                            ele.getPatient().setSurname(surname.getText());
                            ele.getPatient().setDate_birthday(bdate.getText());
                            ele.getPatient().setMobile_number(mobile.getText());
                        }
                    }
                    //Update saved files
                    try {
                        manager.saveInFile("Patient");
                    } catch (Exception ex) {
                        System.out.println("Error");
                    }

                    try {
                        manager.saveInFile("Consultation");
                    } catch (Exception ex) {
                        System.out.println("Error");
                    }

                    panel2.setVisible(false);
                    panel3.setVisible(true);
                    get_detail.setVisible(false);
                    next.setVisible(false);
                    pid.setVisible(false);
                    pid_l.setVisible(false);
                }
            }
        });

        panel3.add(succ_l);
        panel2.add(date_check_lable);
        panel2.add(date_lable);
        panel2.add(bdate);
        panel2.add(mobile);
        panel2.add(mobile_check_lable);
        panel2.add(mobile_lable);
        panel2.add(name);
        panel2.add(name_lable);
        panel2.add(name_check_lable);
        panel2.add(surname);
        panel2.add(surname_lable);
        panel2.add(surname_check_lable);
        panel1.add(panel2);
        panel1.add(get_detail);
        panel1.add(pid);
        panel1.add(pid_l);
        panel1.add(pid_check_lable);
        panel1.add(next);
        panel1.add(back_but);
        panel1.add(main_name);
        frame.add(panel3);
        frame.add(panel1);
    }

    private void checkId(){
        if (!(pid.getText().length()==0)){
            pid_check_lable.setVisible(false);
            if (find_patientforbtn()){
                pid_check_lable.setVisible(false);
            }else{
                pid_check_lable.setVisible(true);
                pid_check_lable.setText("Cant find patient");
            }
        }else {
            pid_check_lable.setVisible(true);
        }
    }

    private Boolean find_patientforbtn(){
        Boolean btn_pai_flag=false;
        for (Patient patient:patient_list){
            if (patient.getPatient_id().equals(pid.getText())){
                btn_pai_flag=true;
                break;
            }
        }
//        System.out.println(btn_pai_flag);
        return btn_pai_flag;
    }

    private   boolean valid_Birthday(){
        DateTimeFormatter formater= DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            formater.parse(bdate.getText());
            int user_year=Integer.parseInt(""+bdate.getText().charAt(0)+bdate.getText().charAt(1)+bdate.getText().charAt(2)+bdate.getText().charAt(3));
            int current_year= Calendar.getInstance().get(Calendar.YEAR);
            //Checking in 100 years old birth year
            //Checking future year
            if ( ( user_year >= (current_year-100)) && user_year <= current_year){
                return true;
            }else {
//                System.out.println("Cant use future year");
                return false;
            }
        }catch (Exception er){
            System.out.println("Invalid date type");
            return false;
        }
    }

    private boolean valid_Mobile(){
        if ((mobile.getText().length()==10)|(mobile.getText().length()==12)){
            return true;
        }else {
//            System.out.println("use 10 digits or +94 number");
            return false;
        }
    }
    //red error in gui for name
    private void checkName(){
        if (!(name.getText().length()==0)){
            name_check_lable.setVisible(false);

        }else {
            name_check_lable.setVisible(true);
        }
    }
    //red error in gui for surname
    private void checkSurName(){
        if (!(surname.getText().length()==0)){
            surname_check_lable.setVisible(false);

        }else {
            surname_check_lable.setVisible(true);
        }
    }
    //red error in gui for date
    private void checkDate(){
        if (!(bdate.getText().length()==0)){
            date_check_lable.setVisible(false);
            if (valid_Birthday()){
                date_check_lable.setVisible(false);
            }else {
                date_check_lable.setVisible(true);
                date_check_lable.setText("Set date correct format");
            }

        }else {
            date_check_lable.setText("Must required");
            date_check_lable.setVisible(true);
        }
    }
    //red error in gui for mobile number
    private void checkMobile(){
        if (!(mobile.getText().length()==0)){
            mobile_check_lable.setVisible(false);
            if (valid_Mobile()){
                mobile_check_lable.setVisible(false);
            }else {
                mobile_check_lable.setVisible(true);
                mobile_check_lable.setText("Use 10 digits or +94 number");
            }

        }else {
            mobile_check_lable.setText("Must required");
            mobile_check_lable.setVisible(true);
        }
    }
}
