package courswork.grafic;
/**
 *
 * @author Dumidu
 */


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

public class Gui_Add_New_Patient extends JFrame {
    ArrayList<Patient> patient_list=manager.returnPatientArrayList();

    private final JFrame frame =new JFrame();
    private final JPanel panel1=new JPanel();
    private final JPanel panel2=new JPanel();
    private final JLabel main_name=new JLabel("Register");

    private final ImageIcon regit=new ImageIcon("photos/register.jpg");
    private final JLabel regit_l=new JLabel(regit);
    private final ImageIcon succ_i=new ImageIcon("photos/sucess.jpg");
    private final JLabel succ_l=new JLabel(succ_i);

    private final JLabel name_lable=new JLabel("First Name");
    private final JLabel surname_lable=new JLabel("Surname");
    private final JLabel date_lable=new JLabel("Date of Birthday");
    private final JLabel date_format=new JLabel("[YYYY/MM/DD]");
    private final JLabel mobile_lable=new JLabel("Mobile Number");
    private final JLabel id_lable=new JLabel("ID Number");

    private final JLabel name_check_lable=new JLabel("Must required");
    private final JLabel surname_check_lable=new JLabel("Must required");
    private final JLabel date_check_lable=new JLabel("Must required");
    private final JLabel mobile_check_lable=new JLabel("Must required");
    private final JLabel id_check_lable=new JLabel("Must required");

    private final JTextField name=new JTextField();
    private final JTextField surname=new JTextField();
    private final JTextField bdate=new JTextField();
    private final JTextField mobile=new JTextField();
    private final JTextField id=new JTextField();

    private final JButton reset_but =new JButton("Reset");
    private final JButton confirm_but =new JButton("Confirm");
    private final JButton book_but=new JButton("Book Consultation");
    private final JButton back_but=new JButton("Back");

    private final Font font2=new Font("Poppins",Font.BOLD,35);
    private final Font font3=new Font("Poppins",Font.BOLD,16);
    private final Font font4=new Font("Poppins",Font.BOLD,11);
    private final Font font5=new Font("Poppins",Font.BOLD,20);
    public Gui_Add_New_Patient(){
        gui_Initialize();


        regit_l.setSize(240,300);
        regit_l.setLocation(80,200);
        succ_l.setSize(240,300);
        succ_l.setLocation(300,180);

        back_but.setSize(150,55);
        back_but.setLocation(85,655);
        back_but.setFont(font3);
        back_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Gui_Patient_Type();
            }
        });

        reset_but.setSize(150,55);
        reset_but.setLocation(340,655);
        reset_but.setFont(font3);
        reset_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                surname.setText("");
                bdate.setText("");
                mobile.setText("");
                id.setText("");
            }
        });

        confirm_but.setSize(200,55);
        confirm_but.setLocation(615,655);
        confirm_but.setFont(font3);
        confirm_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkName();
                checkSurName();
                checkDate();
                checkMobile();
                checkId();

                if (!(name.getText().length()==0) && (!(surname.getText().length()==0)) && (!(bdate.getText().length()==0))
                   && !(mobile.getText().length()==0) && !(id.getText().length()==0) && valid_Birthday() && valid_Mobile() && checkId()){

                    //create new patient
                    Patient patient=new Patient(name.getText(),surname.getText(),bdate.getText(),mobile.getText(),id.getText());
                    patient_list.add(patient);// add patient to list
                    panel1.setVisible(false);
                    //Saving data to file
                    try {
                        manager.saveInFile("Patient");
                    } catch (Exception ex) {
                        System.out.println("Error");
                    }

                    sucessPanel();
                }
            }
        });

        book_but.setSize(250,55);
        book_but.setLocation(320,550);
        book_but.setFont(font3);
        book_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Gui_Book_Consultation(id.getText());
            }
        });

        panel1Adding();
        panel2Adding();
        frame.add(panel2);
        frame.add(panel1);
    }
    //valid form date
    private   boolean valid_Birthday(){
        DateTimeFormatter formater= DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            formater.parse(bdate.getText());
            int user_year=Integer.parseInt(""+bdate.getText().charAt(0)+bdate.getText().charAt(1)+bdate.getText().charAt(2)+bdate.getText().charAt(3));
            int current_year=Calendar.getInstance().get(Calendar.YEAR);
            //Checking in (75-20) years birth year
            //Checking future year
            if ( ( user_year >= (current_year-100)) && user_year <= current_year){
                return true;
            }else {
                return false;
            }
        }catch (Exception er){
            return false;
        }
    }

    //validate mobile
    private boolean valid_Mobile(){
        try{
            Long.parseLong(mobile.getText());
            if (mobile.getText().length()==10){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            if (String.valueOf(mobile.getText().charAt(0)).equals("+")){
                if (mobile.getText().length()==12){
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
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

    //red error in gui for id
    private Boolean checkId(){
        Boolean checkflag=true;
        if (!(id.getText().length()==0)){
            id_check_lable.setVisible(false);
            for (Patient pa:patient_list){
                if (id.getText().equals(pa.getPatient_id())){
                    checkflag=false;
                    break;
                }
            }
        }else {
            id_check_lable.setVisible(true);
        }
        if (!(checkflag)){
            id_check_lable.setVisible(true);
            id_check_lable.setText("Already used");
        }else {
            id_check_lable.setText("Must required");
        }
        return checkflag;
    }

    //new panel for success page
    public void sucessPanel(){
        panel2.add(main_name);
        panel2.setVisible(true);
        main_name.setText("Success");
    }

    private void gui_Initialize(){
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

        panel2.setSize(900,800);
        panel2.setBackground(Color.white);
        panel2.setLayout(null);
        panel2.setVisible(false);

        main_name.setFont(font2);
        main_name.setSize(700,80);
        main_name.setLocation(360,25);

        name_lable.setFont(font3);
        name_lable.setSize(150,80);
        name_lable.setLocation(520,120);
        name.setFont(font5);
        name.setSize(340,38);
        name.setLocation(400,180);
        name.setBackground(Custerm_colour_form);
        name.setHorizontalAlignment(JTextField.CENTER);
        name_check_lable.setFont(font4);
        name_check_lable.setSize(100,80);
        name_check_lable.setLocation(670,120);
        name_check_lable.setForeground(Color.RED);
        name_check_lable.setVisible(false);

        surname_lable.setFont(font3);
        surname_lable.setSize(150,80);
        surname_lable.setLocation(530,210);
        surname.setFont(font5);
        surname.setSize(340,38);
        surname.setLocation(400,280);
        surname.setBackground(Custerm_colour_form);
        surname.setHorizontalAlignment(JTextField.CENTER);
        surname_check_lable.setFont(font4);
        surname_check_lable.setSize(100,80);
        surname_check_lable.setLocation(670,210);
        surname_check_lable.setForeground(Color.RED);
        surname_check_lable.setVisible(false);

        date_lable.setFont(font3);
        date_lable.setSize(200,80);
        date_lable.setLocation(505,310);
        date_format.setFont(font4);
        date_format.setSize(90,20);
        date_format.setLocation(760,390);
        bdate.setFont(font5);
        bdate.setSize(340,38);
        bdate.setLocation(400,380);
        bdate.setBackground(Custerm_colour_form);
        bdate.setHorizontalAlignment(JTextField.CENTER);
        date_check_lable.setFont(font4);
        date_check_lable.setSize(200,80);
        date_check_lable.setLocation(670,310);
        date_check_lable.setForeground(Color.RED);
        date_check_lable.setVisible(false);

        mobile_lable.setFont(font3);
        mobile_lable.setSize(150,80);
        mobile_lable.setLocation(510,410);
        mobile.setFont(font5);
        mobile.setSize(340,38);
        mobile.setLocation(400,480);
        mobile.setBackground(Custerm_colour_form);
        mobile.setHorizontalAlignment(JTextField.CENTER);
        mobile_check_lable.setFont(font4);
        mobile_check_lable.setSize(200,80);
        mobile_check_lable.setLocation(670,410);
        mobile_check_lable.setForeground(Color.RED);
        mobile_check_lable.setVisible(false);

        id_lable.setFont(font3);
        id_lable.setSize(150,80);
        id_lable.setLocation(527,510);
        id.setFont(font5);
        id.setSize(340,38);
        id.setLocation(400,580);
        id.setBackground(Custerm_colour_form);
        id.setHorizontalAlignment(JTextField.CENTER);
        id_check_lable.setFont(font4);
        id_check_lable.setSize(100,80);
        id_check_lable.setLocation(670,510);
        id_check_lable.setForeground(Color.RED);
        id_check_lable.setVisible(false);
    }

    private void panel1Adding(){
        panel1.add(main_name);
        panel1.add(name_lable);
        panel1.add(surname_lable);
        panel1.add(date_lable);
        panel1.add(date_format);
        panel1.add(mobile_lable);
        panel1.add(id_lable);
        panel1.add(name);
        panel1.add(surname);
        panel1.add(bdate);
        panel1.add(mobile);
        panel1.add(id);
        panel1.add(reset_but);
        panel1.add(confirm_but);
        panel1.add(name_check_lable);
        panel1.add(surname_check_lable);
        panel1.add(date_check_lable);
        panel1.add(mobile_check_lable);
        panel1.add(id_check_lable);
        panel1.add(regit_l);
        panel1.add(back_but);
    }
    private void panel2Adding(){
        panel2.add(succ_l);
        panel2.add(book_but);
    }

}