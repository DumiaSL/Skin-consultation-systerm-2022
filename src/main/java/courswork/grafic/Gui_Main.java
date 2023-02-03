package courswork.grafic;

/**
 *
 * @author Dumidu
 */

import courswork.Doctor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static courswork.ConsoleApplication.consolePart;
import static courswork.ConsoleApplication.manager;

public class Gui_Main extends JFrame{
    public static String admin_password="20200515";//In Patient  part doctor table back button password
    ArrayList<Doctor> doctor_list=manager.returnDoctorArrayList();// Access list array
    private final JFrame frame=new JFrame();
    private final JPanel first_panel=new JPanel();
    private final JPanel docTable_panel=new JPanel();

    private final ImageIcon wes_im=new ImageIcon("photos/westminster-logo.jpg");
    private final ImageIcon iit_im=new ImageIcon("photos/iit.png");
    private final ImageIcon skin_im=new ImageIcon("photos/skin care photo.jpg");
    private final JLabel wes=new JLabel(wes_im);
    private final JLabel iit=new JLabel(iit_im);
    private final JLabel skin=new JLabel(skin_im);
    private  JTable jt;

    private final JLabel  main_name=new JLabel("Westminster Skin Consultation \nCenter");
    private final JButton doctorPartButton =new JButton("Doctor Part");
    private final JButton patientPartButton =new JButton("Patient Part");

    private final  Font font2=new Font("Poppins",Font.BOLD,35);
    private final Font font3=new Font("Poppins",Font.BOLD,16);
    private final Font font4=new Font("Poppins",Font.BOLD,15);
    private final Font font6=new Font("Poppins",Font.BOLD,15);
    private final Font font5=new Font("Poppins",Font.BOLD,17);

    public static Color Custerm_colour_form=new Color(218,221,227);

    public Gui_Main(){
        frame.setVisible(true);
        frame.setTitle("Westminster Skin Consultation Manager");
        frame.setSize(900,800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        first_panel.setSize(900,800);
        first_panel.setBackground(Color.white);
        first_panel.setLayout(null);
        first_panel.setVisible(true);

        docTable_panel.setSize(900,800);
        docTable_panel.setBackground(Color.white);
        docTable_panel.setLayout(null);
        docTable_panel.setVisible(false);

        main_name.setFont(font2);
        main_name.setSize(700,80);
        main_name.setLocation(100,290);

        iit.setSize(200,250);
        iit.setLocation(600,30);

        wes.setSize(250,90);
        wes.setLocation(70,130);

        skin.setSize(900,320);
        skin.setLocation(120,530);

        doctorPartButton.setSize(150,55);
        doctorPartButton.setLocation(200,415);
        doctorPartButton.setFont(font3);
        doctorPartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                try {
                    consolePart();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        patientPartButton.setSize(150,55);
        patientPartButton.setLocation(500,415);
        patientPartButton.setFont(font3);
        patientPartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePart_Panel();
            }
        });

        first_panel.add(doctorPartButton);
        first_panel.add(patientPartButton);
        first_panel.add(wes);
        first_panel.add(main_name);
        first_panel.add(skin);
        first_panel.add(iit);
        frame.add(first_panel);
        frame.add(docTable_panel);
    }

    public void tablePart_Panel(){
        first_panel.setVisible(false);
        docTable_panel.setVisible(true);

        final JButton but3=new JButton("Back");
        final JButton but5=new JButton("View Consultation");
        final JButton but4=new JButton("Book Consultation");
        final JButton sort_btn=new JButton("Sort the list alphabetically based on name");
        final JLabel table_name=new JLabel("List of Doctors");
        final JLabel table_name_h1=new JLabel("  Doctor Name  ");
        final JLabel table_name_h2=new JLabel("  Medical licence  ");
        final JLabel table_name_h3=new JLabel("Doctor Type");
        final JLabel table_name_h4=new JLabel("Center Doc ID");
        final JPasswordField back_pass=new JPasswordField();
        final JLabel back_pass_check=new JLabel("Wrong Password.Contract Administration");

        docTablecreate(doctor_list);

        ArrayList<Doctor> templist_1= (ArrayList<Doctor>) doctor_list.clone();
        ArrayList<Doctor> templist_2= (ArrayList<Doctor>) doctor_list.clone();
        // sort doctor array using first name
        templist_1.sort(Comparator.comparing(Doctor::getName));
        // sort doctor array using surname name
        Collections.sort(templist_2);

        table_name.setFont(font2);
        table_name.setSize(700,80);
        table_name.setLocation(310,10);

        table_name_h1.setFont(font5);
        table_name_h1.setSize(200,20);
        table_name_h1.setLocation(50,130);
        table_name_h2.setFont(font5);
        table_name_h2.setSize(200,20);
        table_name_h2.setLocation(420,130);
        table_name_h3.setFont(font5);
        table_name_h3.setSize(200,20);
        table_name_h3.setLocation(645,130);
        table_name_h4.setFont(font5);
        table_name_h4.setSize(200,20);
        table_name_h4.setLocation(240,130);

        back_pass.setSize(170,40);
        back_pass.setLocation(50,570);
        back_pass.setFont(font4);
        back_pass.setVisible(false);
        back_pass.setHorizontalAlignment(JTextField.CENTER);

        back_pass_check.setSize(250,20);
        back_pass_check.setLocation(20,620);
        back_pass_check.setForeground(Color.red);
        back_pass_check.setVisible(false);

        but3.setSize(180,55);
        but3.setLocation(50,500);
        but3.setFont(font3);
        but3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (but3.getText().equals("Back")){
                    but3.setText("Admin Password");
                    back_pass.setVisible(true);
                    back_pass.setVisible(true);
                }else {
                    if (back_pass.getText().equals(admin_password)){
                        frame.setVisible(false);
                        try {
                            new Gui_Main();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }else {
                        back_pass_check.setVisible(true);
                    }
                }
            }
        });

        sort_btn.setSize(350,30);
        sort_btn.setLocation(260,80);
        sort_btn.setFont(new Font("Poppins",Font.BOLD,12));
        sort_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sort_btn.getText().equals("Sort the list alphabetically based on name")) {
                    docTablecreate(templist_1);
                    sort_btn.setText("Sort the list alphabetically based on surname");
                }else if (sort_btn.getText().equals("Sort the list alphabetically based on surname")){
                    sort_btn.setText("Return List");
                    docTablecreate(templist_2);
                }else {
                    sort_btn.setText("Sort the list alphabetically based on name");
                    docTablecreate(doctor_list);
                }
            }
        });

        but4.setSize(250,55);
        but4.setLocation(600,500);
        but4.setFont(font3);
        but4.addActionListener(new ActionListener() {
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

        but5.setSize(250,55);
        but5.setLocation(300,500);
        but5.setFont(font3);
        but5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                try {
                    new Gui_View_Consultation_Edit();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        skin.setLocation(20,580);
        docTable_panel.add(sort_btn);
        docTable_panel.add(but3);
        docTable_panel.add(but4);
        docTable_panel.add(but5);
        docTable_panel.add(back_pass_check);
        docTable_panel.add(back_pass);

        docTable_panel.add(table_name);
        docTable_panel.add(table_name_h1);
        docTable_panel.add(table_name_h2);
        docTable_panel.add(table_name_h3);
        docTable_panel.add(table_name_h4);
        docTable_panel.add(skin);
    }

    private void docTablecreate(ArrayList<Doctor> sorted_doctor_list) {
        String[][] doc_data=new String[sorted_doctor_list.size()][4];
        String[] columnNames ={"Doctor Name","Center Doc ID","Medical licence","Doctor Type"};

        //adding doctor details to nested array
        for (int count=0;count<sorted_doctor_list.size();count++){
            doc_data[count][0]="Dr."+sorted_doctor_list.get(count).getName()+" "+sorted_doctor_list.get(count).getSurname();
            doc_data[count][1]=""+sorted_doctor_list.get(count).getDoc_cent_number();
            doc_data[count][2]=sorted_doctor_list.get(count).getMedical_licence_number();
            doc_data[count][3]=sorted_doctor_list.get(count).getDoc_type();
        }

        jt=new JTable(doc_data,columnNames);
        jt.setSize(810,sorted_doctor_list.size()*33);
        jt.setLocation(50,170);
        jt.setRowHeight(30);
        jt.setShowHorizontalLines(true);
        jt.setShowVerticalLines(false);
        jt.setFont(font6);
        docTable_panel.add(jt);
    }

}