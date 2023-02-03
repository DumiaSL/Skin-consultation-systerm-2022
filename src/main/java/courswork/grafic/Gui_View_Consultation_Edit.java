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


public class Gui_View_Consultation_Edit extends JFrame{
    ArrayList<Doctor> doctor_list=manager.returnDoctorArrayList();
    ArrayList<Patient> patient_list=manager.returnPatientArrayList();
    ArrayList<Consultation> consultation_list=manager.returnConsultationArrayList();
    private JFrame frame=new JFrame();
    private JPanel panel1 =new JPanel();
    private JPanel panel2=new JPanel();

    private JTextField pid=new JTextField();
    private JTextField date =new JTextField();
    private JTextField hour =new JTextField();
    private JTextField time =new JTextField();
    private JTextField note =new JTextField();

    JLabel main_lable=new JLabel("View Consultations");
    private JLabel pid_l=new JLabel("Enter patient ID");
    private JLabel pid_check_lable=new JLabel("Must Filled");
    private JLabel date_lable =new JLabel("Consultation Date");
    private JLabel date_check_lable =new JLabel("Must required");
    private JLabel time_lable =new JLabel("Consultation Time");
    private JLabel time_check_lable =new JLabel("Must required");
    private JLabel hour_lable =new JLabel("Consultation Hours");
    private JLabel hour_check_lable =new JLabel("Must required");
    private JLabel note_lable =new JLabel("Consultation note");

    private JLabel check_lable =new JLabel("Doctor has Consultation");

    private JButton back_but=new JButton("Back");
    private JButton get_detail=new JButton("Get details");
    private JButton edit_but=new JButton("Edit");
    private JButton cancel_but=new JButton("Cancel");

    private JButton next=new JButton("Next");

    private Font font2=new Font("Poppins",Font.BOLD,35);
    private Font font3=new Font("Poppins",Font.BOLD,16);
    private Font font4=new Font("Poppins",Font.BOLD,11);
    private Font font5=new Font("Poppins",Font.BOLD,14);
    private Font font6=new Font("Poppins",Font.BOLD,20);
    private Font font7=new Font("Poppins",Font.BOLD,28);
    private Font font8=new Font("Poppins",Font.BOLD,24);

    int count;
    ArrayList<Consultation> temp;

    public Gui_View_Consultation_Edit(){
        gui_Initialize();

        back_but.setSize(150,55);
        back_but.setLocation(55,655);
        back_but.setFont(font3);
        back_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Gui_Main A =new Gui_Main();
                A.tablePart_Panel();
            }
        });

        get_detail.setSize(150,55);
        get_detail.setLocation(350,655);
        get_detail.setFont(font3);
        get_detail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pid_check_lable.setSize(100,80);
                pid_check_lable.setLocation(380,130);
                pid_check_lable.setForeground(Color.red);
                checkId();
                panel2.setVisible(false);
                pid_l.setText("Enter patient ID");
                main_lable.setText("View Consultations");
                pid_l.setFont(font3);
                pid_l.setLocation(360,100);
                pid_l.setForeground(Color.black);
                next.setVisible(false);
                if (!(pid.getText().length()==0)&& (find_patientforbtn())){
                    count=0;
                    temp=new ArrayList<>();
                    for (Consultation co:consultation_list){
                        if (co.getPatient().getPatient_id().equals(pid.getText())){
                            temp.add(co);
                        }
                    }
                    if (!(temp.size()==0)){
                        panel2.setVisible(true);
                        next.setVisible(true);
                        pid_check_lable.setSize(200,80);
                        pid_check_lable.setLocation(350,130);
                        pid_check_lable.setText(temp.size()+"  Consultations found.");
                        pid_check_lable.setForeground(Color.blue);
                        pid_check_lable.setVisible(true);
                        date.setText(temp.get(count).getConsultation_date());
                        time.setText(temp.get(count).getConsultation_time());
                        hour.setText(temp.get(count).getApp_hours()+"");
                        note.setText(temp.get(count).getNotes());
                        main_lable.setText(temp.get(count).getPatient().getName()+" "+temp.get(count).getPatient().getSurname());
                        pid_l.setText("Dr."+temp.get(count).getDoctor().getName()+" "+temp.get(count).getDoctor().getSurname());
                        pid_l.setFont(font6);
                        pid_l.setForeground(Color.red);
                        pid_l.setLocation(330,100);
                        if (temp.size()==1){
                            next.setVisible(false);
                        }
                        count++;

                    }else {
                        pid_check_lable.setVisible(true);
                        pid_check_lable.setText("Results not found");
                    }
                }
            }
        });

        cancel_but.setSize(130,35);
        cancel_but.setLocation(55,320);
        cancel_but.setFont(font3);
        cancel_but.setVisible(false);
        cancel_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    date.setText(temp.get(count-1).getConsultation_date());
                    time.setText(temp.get(count-1).getConsultation_time());
                    hour.setText(temp.get(count-1).getApp_hours()+"");
                    note.setText(temp.get(count-1).getNotes());
                }catch (Exception ee){
                    System.out.println("Error occurred in save cancel button");
                }

                date.setEditable(false);
                time.setEditable(false);
                hour.setEditable(false);
                note.setEditable(false);
                edit_but.setText("Edit");
                get_detail.setVisible(true);
                pid.setEditable(true);
                cancel_but.setVisible(false);

                //Checking is there is next element and set next button visibility
                if (count==(temp.size())){
                    next.setVisible(false);
                }else {
                    next.setVisible(true);
                }
            }
        });

        edit_but.setSize(130,35);
        edit_but.setLocation(370,320);
        edit_but.setFont(font3);
        edit_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (edit_but.getText().equals("Edit")){
                    date.setEditable(true);
                    time.setEditable(true);
                    hour.setEditable(true);
                    note.setEditable(true);
                    edit_but.setText("Save");
                    next.setVisible(false);
                    get_detail.setVisible(false);
                    pid.setEditable(false);
                    cancel_but.setVisible(true);
                }else {
                    //Checking valid and set visibility on red error
                    checkDate();
                    checkhours();
                    checkTime();
                    check_doc_avi();

                    //Checking JTextField not empty and valid format date , valid format time also consultation doctor Availability
                    if (!(date.getText().length()==0)&&!(time.getText().length()==0)&&!(hour.getText().length()==0)&&checkhours()
                    && valid_day() && valid_Time() && check_doc_avi()){
                        //set editable access in JTextFields
                        date.setEditable(false);
                        time.setEditable(false);
                        hour.setEditable(false);
                        note.setEditable(false);
                        pid.setEditable(true);
                        cancel_but.setVisible(false);
                        //setting save button name back to Edit
                        edit_but.setText("Edit");
                        get_detail.setVisible(true);

                        try {
                            //updating list consultation object using new user entered data
                            temp.get(count-1).setConsultation_date(date.getText());
                            temp.get(count-1).setConsultation_time(time.getText());
                            temp.get(count-1).setApp_hours(Integer.parseInt(hour.getText()));
                            temp.get(count-1).setNotes(note.getText());
                        }catch (Exception ee){
                            System.out.println("Error occurred in saving  button");
                        }
                        //Updating files saving to consultation store
                        try {
                            manager.saveInFile("Consultation");
                        } catch (Exception ex) {
                            System.out.println("Error");
                        }
                        //Checking is there is next element and set next button visibility
                        if (count==(temp.size())){
                            next.setVisible(false);
                        }else {
                            next.setVisible(true);
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
                //setting next consultation data on Jtextfield
                date.setText(temp.get(count).getConsultation_date());
                time.setText(temp.get(count).getConsultation_time());
                hour.setText(temp.get(count).getApp_hours()+"");
                note.setText(temp.get(count).getNotes());
                pid_l.setText("Dr."+temp.get(count).getDoctor().getName()+" "+temp.get(count).getDoctor().getSurname());
                pid_l.setFont(font6);
                pid_l.setLocation(330,100);
                pid_l.setForeground(Color.red);
                //Checking is there is next element and set next button visibility
                if (count==(temp.size()-1)){
                    next.setVisible(false);
                }
                count++;

            }
        });

        //panel 2 adding
        panel2_add();
        //adding to frame
        frame.add(get_detail);
        frame.add(next);
        frame.add(back_but);
        frame.add(pid);
        frame.add(pid_l);
        frame.add(pid_check_lable);
        frame.add(main_lable);
        frame.add(panel2);
    }

    //checking is null or entered number is valid patient number, then set visible setting on red errors
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
            pid_check_lable.setText("Must required");
        }
    }

    //checking text field number is patient number.It is then return true or is not return false
    private Boolean find_patientforbtn(){
        Boolean btn_pai_flag=false;
        for (Patient patient:patient_list){
            if (patient.getPatient_id().equals(pid.getText())){
                btn_pai_flag=true;
                break;
            }
        }
        return btn_pai_flag;
    }

    //checking given date is in correct date format and current year.current year>.It is then return true or is not return false
    private   boolean valid_day(){
        DateTimeFormatter formater= DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            formater.parse(date.getText());
            //Checking future year
            if (Integer.parseInt(""+date.getText().charAt(0)+date.getText().charAt(1)
                    +date.getText().charAt(2)+date.getText().charAt(3)) >= Calendar.getInstance().get(Calendar.YEAR)){
                return true;
            }else {
                return false;
            }
        }catch (Exception er){
            return false;
        }
    }

    //checking date in correct format and not null, then set visible setting on red errors
    private void checkDate(){
        if (!(date.getText().length()==0)){
            date_check_lable.setVisible(false);
            if (valid_day()){
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

    //checking hour in integer format and not null, then set visible setting on red errors
    private boolean checkhours(){
        boolean x=true;
        if (!(hour.getText().length()==0)){
            hour_check_lable.setVisible(false);
            try {
                Integer.parseInt(hour.getText());
            }catch (Exception e){
                hour_check_lable.setText("Set Integer");
                hour_check_lable.setVisible(true);
                x=false;
            }
        }else {
            hour_check_lable.setVisible(true);
            x=false;
        }
        return x;
    }

    //checking given time is in correct time format and 8:00-20:00..It is then return true or is not return false
    private boolean valid_Time(){
        DateTimeFormatter formater= DateTimeFormatter.ofPattern("HH:mm");
        try {
            formater.parse(time.getText());
            //Checking future year
            if ((Integer.parseInt(""+time.getText().charAt(0)+time.getText().charAt(1))>= 9)&&
                    (Integer.parseInt(""+time.getText().charAt(0)+time.getText().charAt(1))< 20)){
                return true;
            }else {
                return false;
            }
        }catch (Exception er){
            return false;
        }
    }

    //checking time in correct format and not null, then set visible setting on red errors
    private void checkTime(){
        if (!(time.getText().length()==0)){
            time_check_lable.setVisible(false);
            if (valid_Time()){
                time_check_lable.setVisible(false);
            }else {
                time_check_lable.setVisible(true);
                time_check_lable.setText("Set time correct format");
            }
        }else {
            time_check_lable.setText("Must required");
            time_check_lable.setVisible(true);
        }
    }

    //checking that doctor can new date and time
    public Boolean check_doc_avi(){
        check_lable.setVisible(false);
        boolean can_flag=true;
        for (Consultation co:consultation_list){
            if (co.getDoctor().getDoc_cent_number()==temp.get(count-1).getDoctor().getDoc_cent_number()){//checking same doctor
                if (!(temp.get(count-1)==co)){//Ignore current consultation
                    //checking old con.. data with new data
                    if ((co.getConsultation_date().equals(date.getText()) && (co.getConsultation_time().equals(time.getText())))){
                        can_flag=false;
                        break;
                    }
                }
            }
        }
        if (!can_flag){
            check_lable.setVisible(true);
        }
        return can_flag;
    }

    //Gui start
    private void gui_Initialize(){
        frame.setVisible(true);
        frame.setTitle("Westminster Skin Consultation Manager");
        frame.setSize(900,800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        panel1.setSize(900,800);
        panel1.setBackground(Color.red);
        panel1.setLayout(null);
        panel1.setVisible(true);

        panel2.setSize(900,370);
        panel2.setBackground(Color.pink);
        panel2.setLayout(null);
        panel2.setVisible(false);
        panel2.setLocation(0,250);

        main_lable.setFont(font2);
        main_lable.setSize(700,80);
        main_lable.setLocation(260,25);

        pid_l.setFont(font3);
        pid_l.setSize(350,80);
        pid_l.setLocation(340,100);
        pid.setFont(font6);
        pid.setSize(340,38);
        pid.setLocation(260,190);
        pid.setBackground(Custerm_colour_form);
        pid.setHorizontalAlignment(JTextField.CENTER);
        pid_check_lable.setFont(font4);
        pid_check_lable.setVisible(false);

        date_lable.setFont(font3);
        date_lable.setSize(250,80);
        date_lable.setLocation(140,10);
        date.setFont(font6);
        date.setSize(340,38);
        date.setLocation(50,100);
        date.setEditable(false);
        date.setBackground(Custerm_colour_form);
        date.setHorizontalAlignment(JTextField.CENTER);
        date_check_lable.setFont(font4);
        date_check_lable.setSize(200,80);
        date_check_lable.setLocation(160,40);
        date_check_lable.setForeground(Color.RED);
        date_check_lable.setVisible(false);

        time_lable.setFont(font3);
        time_lable.setSize(250,80);
        time_lable.setLocation(570,10);
        time.setFont(font6);
        time.setSize(340,38);
        time.setLocation(480,100);
        time.setHorizontalAlignment(JTextField.CENTER);
        time.setBackground(Custerm_colour_form);
        time.setEditable(false);
        time_check_lable.setFont(font4);
        time_check_lable.setSize(200,80);
        time_check_lable.setLocation(570,40);
        time_check_lable.setForeground(Color.RED);
        time_check_lable.setVisible(false);

        hour_lable.setFont(font3);
        hour_lable.setSize(250,80);
        hour_lable.setLocation(130,160);
        hour.setFont(font6);
        hour.setSize(340,38);
        hour.setLocation(50,250);
        hour.setBackground(Custerm_colour_form);
        hour.setHorizontalAlignment(JTextField.CENTER);
        hour.setEditable(false);
        hour_check_lable.setFont(font4);
        hour_check_lable.setSize(200,80);
        hour_check_lable.setLocation(180,190);
        hour_check_lable.setForeground(Color.RED);
        hour_check_lable.setVisible(false);

        note_lable.setFont(font3);
        note_lable.setSize(250,80);
        note_lable.setLocation(570,160);
        note.setFont(font6);
        note.setSize(340,38);
        note.setLocation(480,250);
        note.setBackground(Custerm_colour_form);
        note.setHorizontalAlignment(JTextField.CENTER);
        note.setEditable(false);

        check_lable.setSize(200,80);
        check_lable.setLocation(350,10);
        check_lable.setForeground(Color.RED);
        check_lable.setVisible(false);
    }

    //Panel 2 adding
    private void panel2_add(){
        panel2.add(cancel_but);
        panel2.add(check_lable);
        panel2.add(edit_but);
        panel2.add(date);
        panel2.add(date_check_lable);
        panel2.add(date_lable);
        panel2.add(time);
        panel2.add(time_check_lable);
        panel2.add(time_lable);
        panel2.add(hour);
        panel2.add(hour_check_lable);
        panel2.add(hour_lable);
        panel2.add(note);
        panel2.add(note_lable);
    }
}
