package courswork.grafic;
/**
 *
 * @author Dumidu
 */

import courswork.Consultation;
import courswork.Doctor;
import courswork.Patient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static courswork.ConsoleApplication.manager;
import static courswork.ConsoleApplication.ran;


import static courswork.grafic.Gui_Main.Custerm_colour_form;

public class Gui_Book_Consultation extends JFrame{
    ArrayList<Doctor> doctor_list=manager.returnDoctorArrayList();
    ArrayList<Patient> patient_list=manager.returnPatientArrayList();
    ArrayList<Consultation> consultation_list=manager.returnConsultationArrayList();

    private JFrame frame =new JFrame();
    private JPanel panel1=new JPanel();
    private JPanel panel2=new JPanel();
    private JPanel panel3=new JPanel();

    private JPanel panel4=new JPanel();

    private ImageIcon bookingI=new ImageIcon("photos/selc.jpg");
    private JLabel bookingI_l=new JLabel(bookingI);


    // creating 9 buttons
    JButton btn1 = new JButton("not available");
    JButton btn2 = new JButton("not available");
    JButton btn3 = new JButton("not available");
    JButton btn4 = new JButton("not available");
    JButton btn5 = new JButton("not available");
    JButton btn6 = new JButton("not available");
    JButton btn7 = new JButton("not available");
    JButton btn8 = new JButton("not available");
    JButton btn9 = new JButton("not available");
    JButton btn10 = new JButton("not available");
    JButton btn11 = new JButton("not available");
    JButton btn12 = new JButton("not available");
    private JButton[] butarray={btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12};

    private JTextField cDate=new JTextField();
    private JTextField cTime=new JTextField();
    private JTextField cHours=new JTextField("1");
    private JTextField pid=new JTextField();
    private JTextArea note=new JTextArea();
    private JLabel slec_doc=new JLabel("Select a Doctor");
    private JLabel main_name=new JLabel("Booking Consultation");
    private JLabel cDate_l=new JLabel("Consultation Date");
    private JLabel ctime_l=new JLabel("Consultation Time");
    private JLabel chours_l=new JLabel("Consultation Hours");
    private JLabel pid_l=new JLabel("Patient ID");
    private JLabel note_l=new JLabel("Note");

    private JLabel cDate_for=new JLabel("[YYYY/MM/DD]");
    private JLabel cTime_for=new JLabel("[HH:MM]  8:00 - 20:00");

    private JLabel date_check_lable=new JLabel("Must required");
    private JLabel time_check_lable=new JLabel("Must required");
    private JLabel hours_check_lable=new JLabel("Must required");
    private JLabel pid_check_lable=new JLabel("Must required");
    private JLabel doc_check_lable=new JLabel("Must required");
    private JLabel labeldocname=new JLabel();
    private JLabel labelnewdocname=new JLabel();
    private JLabel labeldetail=new JLabel("has a consultation that time. You are allocated to");

    JButton backBtn_panel1 =new JButton("Back");
    JButton nextBtn_panel1 =new JButton("Checking Consultation Availability");

    JButton backBtn_panel3=new JButton("Back");
    JButton nextBtn_panel3=new JButton("Book Consultation");
    JButton new_patient=new JButton("New Patient");

    private Font font2=new Font("Poppins",Font.BOLD,35);

    private Font font3=new Font("Poppins",Font.BOLD,16);
    private Font font4=new Font("Poppins",Font.BOLD,11);
    private Font font5=new Font("Poppins",Font.BOLD,14);
    private Font font6=new Font("Poppins",Font.BOLD,20);
    private Font font7=new Font("Poppins",Font.BOLD,28);
    private Font font8=new Font("Poppins",Font.BOLD,24);

    private boolean btn_flag=false;

    Hashtable temp = new Hashtable();

    private String docdetail;
    private Boolean book_flag;
    private int docIndex;

    public Gui_Book_Consultation(){

        String temp1;
        for (Doctor docT:doctor_list){
            temp1="Dr."+docT.getName()+" "+docT.getSurname()+ " - ID "+docT.getDoc_cent_number();
            temp.put(docT.getDoc_cent_number(),temp1);
        }

        gui_Initialize();

        panel1Adding();
        panel2Adding();
        panel3Adding();
        frame.add(panel3);
        frame.add(panel1);

    }
    public Gui_Book_Consultation(String pid){
        this.pid.setText(pid);

        String temp1;
        for (Doctor docT:doctor_list){
            temp1="Dr."+docT.getName()+" "+docT.getSurname()+ " - ID "+docT.getDoc_cent_number();
            temp.put(docT.getDoc_cent_number(),temp1);
        }

        gui_Initialize();

        panel1Adding();
        panel2Adding();
        panel3Adding();
        frame.add(panel3);
        frame.add(panel1);
    }

    private void gui_Initialize() {
        frame.setVisible(true);
        frame.setTitle("Westminster Skin Consultation Manager");
        frame.setSize(900, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        panel1.setSize(900, 800);
        panel1.setBackground(Color.white);
        panel1.setLayout(null);
        panel1.setVisible(true);

        panel2.setSize(830, 170);
        panel2.setBackground(Color.white);
        panel2.setLayout(new GridLayout(4,3,10,10));
        panel2.setVisible(true);
        panel2.setLocation(25,200);

        panel3.setSize(900, 800);
        panel3.setBackground(Color.white);
        panel3.setLayout(null);
        panel3.setVisible(false);

        panel4.setSize(900, 300);
        panel4.setBackground(Color.pink);
        panel4.setLayout(null);
        panel4.setVisible(false);
        panel4.setLocation(0,100);

        main_name.setFont(font2);
        main_name.setSize(700,80);
        main_name.setLocation(230,20);

        bookingI_l.setSize(350,200);
        bookingI_l.setLocation(40,390);

        slec_doc.setFont(font3);
        slec_doc.setSize(200,20);
        slec_doc.setLocation(360,150);

        labeldocname.setFont(font7);
        labeldocname.setSize(500,60);

        labelnewdocname.setFont(font7);
        labelnewdocname.setSize(400,60);
        labelnewdocname.setLocation(300,200);
        labelnewdocname.setVisible(false);

        labeldetail.setFont(font8);
        labeldetail.setSize(600,60);

        create_btn();

        doc_check_lable.setFont(font4);
        doc_check_lable.setSize(150,80);
        doc_check_lable.setLocation(385,140);
        doc_check_lable.setForeground(Color.RED);
        doc_check_lable.setVisible(false);

        cDate_l.setFont(font3);
        cDate_l.setSize(200,80);
        cDate_l.setLocation(420,390);
        cDate.setFont(font6);
        cDate.setSize(200,38);
        cDate.setLocation(620,410);
        cDate.setBackground(Custerm_colour_form);
        cDate.setHorizontalAlignment(JTextField.CENTER);
        date_check_lable.setFont(font4);
        date_check_lable.setSize(150,80);
        date_check_lable.setLocation(450,410);
        date_check_lable.setForeground(Color.RED);
        date_check_lable.setVisible(false);
        cDate_for.setFont(font4);
        cDate_for.setSize(90,20);
        cDate_for.setLocation(445,460);

        ctime_l.setFont(font3);
        ctime_l.setSize(200,80);
        ctime_l.setLocation(420,470);
        cTime.setFont(font6);
        cTime.setSize(200,38);
        cTime.setLocation(620,490);
        cTime.setHorizontalAlignment(JTextField.CENTER);
        cTime.setBackground(Custerm_colour_form);
        time_check_lable.setFont(font4);
        time_check_lable.setSize(150,80);
        time_check_lable.setLocation(450,490);
        time_check_lable.setForeground(Color.RED);
        time_check_lable.setVisible(false);
        cTime_for.setFont(font4);
        cTime_for.setSize(150,20);
        cTime_for.setLocation(445,540);

        chours_l.setFont(font3);
        chours_l.setSize(200,80);
        chours_l.setLocation(420,550);
        cHours.setFont(font6);
        cHours.setSize(200,38);
        cHours.setLocation(620,570);
        cHours.setHorizontalAlignment(JTextField.CENTER);
        cHours.setBackground(Custerm_colour_form);
        hours_check_lable.setFont(font4);
        hours_check_lable.setSize(150,80);
        hours_check_lable.setLocation(450,570);
        hours_check_lable.setForeground(Color.RED);
        hours_check_lable.setVisible(false);

        pid_l.setFont(font3);
        pid_l.setSize(200,80);
        pid_l.setLocation(70,425);
        pid.setFont(font6);
        pid.setSize(180,38);
        pid.setLocation(170,445);
        pid.setBackground(Custerm_colour_form);
        pid.setHorizontalAlignment(JTextField.CENTER);
        pid_check_lable.setFont(font4);
        pid_check_lable.setSize(100,80);
        pid_check_lable.setLocation(70,452);
        pid_check_lable.setForeground(Color.RED);
        pid_check_lable.setVisible(false);

        note_l.setFont(font3);
        note_l.setSize(200,130);
        note_l.setLocation(470,400);
        note.setFont(font6);
        note.setSize(250,130);
        note.setLocation(540,450);
        note.setBackground(Custerm_colour_form);

        new_patient.setSize(130,40);
        new_patient.setLocation(40,500);
        new_patient.setFont(font4);
        new_patient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Gui_Add_New_Patient();
            }
        });


        backBtn_panel1.setSize(150,55);
        backBtn_panel1.setLocation(150,650);
        backBtn_panel1.setFont(font3);
        backBtn_panel1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                try {
                    new Gui_Patient_Type();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        backBtn_panel3.setSize(150,55);
        backBtn_panel3.setLocation(150,650);
        backBtn_panel3.setFont(font3);
        backBtn_panel3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    panel3.setVisible(false);
                    panel1.setVisible(true);
                    panel1.add(main_name);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        nextBtn_panel3.setSize(200,55);
        nextBtn_panel3.setLocation(550,650);
        nextBtn_panel3.setFont(font3);
        nextBtn_panel3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    checkId();
                    if (!(pid.getText().length()==0)&& (find_patientforbtn())){
                        Consultation consultation;
                        if (book_flag){
                             consultation=new Consultation(cDate.getText(),cTime.getText(), Integer.parseInt(cHours.getText())
                                    ,note.getText(),doctor_list.get(docIndex), find_patient());
                        }else {
                             consultation=new Consultation(cDate.getText(),cTime.getText(), Integer.parseInt(cHours.getText())
                                    ,note.getText(),find_doc(), find_patient());
                        }

                        //create new consultation
                        consultation_list.add(consultation);// add consultation to list
                        //Saving data to file
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
                        frame.setVisible(false);
                        new Gui_Book_Confirm(consultation);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        nextBtn_panel1.setSize(350,55);
        nextBtn_panel1.setLocation(460,650);
        nextBtn_panel1.setFont(font3);
        nextBtn_panel1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkDate();
                checkHours();
                checkTime();
                check_docflag();

                if (btn_flag && (valid_day()) && (checkHours()) && (valid_Time() )){
                    panel4.setVisible(true);

                    Doctor doc=find_doc();
                    book_flag=false;
                    book_flag=candoc(doc);

                    if (book_flag){
                        int count=0;
                        while (true){
                            docIndex=ran.nextInt(0,(doctor_list.size()));
                            if ((!(doctor_list.get(docIndex).getDoc_cent_number()==doc.getDoc_cent_number()))&&(!candoc((doctor_list.get(docIndex))))){
                                break;
                            }
                            count++;
//                            if (count==500){
//                                break;
//                            }
                        }

                        labeldocname.setText("Dr."+doc.getName()+" "+ doc.getSurname());
                        labeldetail.setText("has a consultation this time. You are allocated");
                        labelnewdocname.setText("Dr."+doctor_list.get(docIndex).getName()+" "+ doctor_list.get(docIndex).getSurname());
                        labeldetail.setLocation(180,120);
                        labeldocname.setLocation(300,40);
                        labelnewdocname.setVisible(true);
                        panel4.add(labelnewdocname);

                    }else {
                        labelnewdocname.setVisible(false);
                        labeldocname.setText("Dr."+doc.getName()+" "+ doc.getSurname());
                        labeldetail.setText("for your time");
                        labeldocname.setLocation(300,80);
                        labeldetail.setLocation(360,160);
                    }
                    panel4.add(labeldetail);
                    panel4.add(labeldocname);

                    panel1.setVisible(false);
                    panel3.setVisible(true);
                    panel3.add(main_name);
                }
            }
        });
    }

    private void panel1Adding(){
        panel1.add(main_name);
        panel1.add(bookingI_l);
        panel1.add(slec_doc);
        panel1.add(doc_check_lable);
        panel1.add(cDate);
        panel1.add(cTime);
        panel1.add(cHours);
        panel1.add(cDate_l);
        panel1.add(ctime_l);
        panel1.add(chours_l);
        panel1.add(cDate_for);
        panel1.add(cTime_for);
        panel1.add(date_check_lable);
        panel1.add(time_check_lable);
        panel1.add(hours_check_lable);
        panel1.add(backBtn_panel1);
        panel1.add(nextBtn_panel1);
        panel1.add(panel2);
    }
    private void panel2Adding(){
        panel2.add(btn1);
        panel2.add(btn2);
        panel2.add(btn3);
        panel2.add(btn4);
        panel2.add(btn5);
        panel2.add(btn6);
        panel2.add(btn7);
        panel2.add(btn8);
        panel2.add(btn9);
        panel2.add(btn10);
        panel2.add(btn11);
        panel2.add(btn12);
    }

    private void panel3Adding(){
        panel3.add(pid);
        panel3.add(pid_l);
        panel3.add(pid_check_lable);
        panel3.add(panel4);
        panel3.add(backBtn_panel3);
        panel3.add(nextBtn_panel3);
        panel3.add(note);
        panel3.add(note_l);
        panel3.add(new_patient);
    }

    //checking doctor booking
    private boolean candoc(Doctor doc){
        Boolean flag=false;
        for (Consultation element:consultation_list){
            if (element.getDoctor().getDoc_cent_number()==(doc.getDoc_cent_number()) && (element.getConsultation_date().equals(cDate.getText()))
                    && (element.getConsultation_time().equalsIgnoreCase(cTime.getText()))){
                flag= true;
                break;
            }else {
                flag= false;
            }
        }
        return flag;
    }
    public void create_btn(){
        int count=0;

        Enumeration elemnts = temp.elements();

        // adding to array for combobox
        while (elemnts.hasMoreElements()) {

            butarray[count].setText((String) elemnts.nextElement());
            butarray[count].setFont(font5);
            butarray[count].setBackground(Color.pink);
            int finalCount = count;
            butarray[count].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    check_docCan(butarray[finalCount]);
                }
            });
            count++;
        }

        while (count<12){
            butarray[count].setFont(font4);
            butarray[count].setBackground(Color.pink);
            int finalCount1 = count;
            butarray[count].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btn_flag=false;
                    docdetail=null;
                    for (JButton bun:butarray){
                        if (!(bun==butarray[finalCount1])){
                            bun.setBackground(Color.pink);
                        }
                    }
                }
            });
            count++;
        }
    }

    private void check_docCan(JButton btn){
        btn_flag=true;
        docdetail=btn.getText();
        btn.setBackground(Color.cyan);
        for (JButton bun:butarray){
            if (!(bun==btn)){
                bun.setBackground(Color.pink);
            }
        }
    }

    private Doctor find_doc(){
        Set set=temp.entrySet();
        Iterator iterator=set.iterator();

        //Loop Hashtable elements using iterator.
        int key = 0;
        while(iterator.hasNext()){

            Map.Entry mapEntry=(Map.Entry)iterator.next();
            if (mapEntry.getValue().equals(docdetail)){
                key= (int) mapEntry.getKey();
                break;
            }
        }
        Doctor doc = null;
        for (Doctor doctor:doctor_list){
            if (doctor.getDoc_cent_number()==key){
                doc=doctor;
                break;
            }
        }
        return doc;
    }

    private Patient find_patient(){
        Patient pai = null;
        for (Patient patient:patient_list){
            if (patient.getPatient_id().equals(pid.getText())){
                pai=patient;
                break;
            }
        }
        return pai;
    }

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

    private void check_docflag(){
        if (!btn_flag){
            doc_check_lable.setVisible(true);
        }else {
            doc_check_lable.setVisible(false);
        }
    }
    //valid form date
    private   boolean valid_day(){
        if (!(cDate.getText().length()==0)){
            DateTimeFormatter formater= DateTimeFormatter.ofPattern("yyyy/MM/dd");
            try {
                formater.parse(cDate.getText());
                //Checking future year
                int user_year=Integer.parseInt(""+cDate.getText().charAt(0)+cDate.getText().charAt(1)+cDate.getText().charAt(2)+cDate.getText().charAt(3));
                int current_year=Calendar.getInstance().get(Calendar.YEAR);

                if ( user_year >= current_year){
                    return true;
                }else {
                    date_check_lable.setVisible(true);
                    date_check_lable.setText("Set date correct format");
                    return false;
                }
            }catch (Exception er){
                return false;
            }
        }else {
            return false;
        }
    }

    //valid form time
    private boolean valid_Time(){
        if (!(cTime.getText().length()==0)){
            DateTimeFormatter formater= DateTimeFormatter.ofPattern("HH:mm");
            try {
                formater.parse(cTime.getText());
                //Checking future year
                if ((Integer.parseInt(""+cTime.getText().charAt(0)+cTime.getText().charAt(1))>= 9)&&
                        (Integer.parseInt(""+cTime.getText().charAt(0)+cTime.getText().charAt(1))< 20)){
                    return true;
                }else {
                    return false;
                }
            }catch (Exception er){
                return false;
            }
        }else {
            return false;
        }
    }

    private void checkDate(){
        if (!(cDate.getText().length()==0)){
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

    private void checkTime(){
        if (!(cTime.getText().length()==0)){
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

    //red error in gui for hours
    private boolean checkHours(){
        if (!(cHours.getText().length()==0)){
            hours_check_lable.setVisible(false);
            try {
                Integer.parseInt(cHours.getText());
                return true;
            }catch (Exception e){
                hours_check_lable.setText("Set Integer");
                hours_check_lable.setVisible(true);
                return false;
            }
        }else {
            hours_check_lable.setVisible(true);
            hours_check_lable.setText("Must required");
            return false;
        }
    }
}
