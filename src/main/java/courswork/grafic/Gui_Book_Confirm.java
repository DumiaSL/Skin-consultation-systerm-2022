package courswork.grafic;

/**
 *
 * @author Dumidu
 */

import courswork.Consultation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui_Book_Confirm extends JFrame{

    private JFrame frame=new JFrame();
    private JPanel panel1=new JPanel();

    private JLabel main_name=new JLabel("Success");

    private ImageIcon succ_i=new ImageIcon("photos/sucess.jpg");
    private JLabel succ_l=new JLabel(succ_i);
    private JLabel doc_name=new JLabel();
    private JLabel cost=new JLabel();
    private JLabel date=new JLabel();
    private JLabel time=new JLabel();

    private JButton finish_but=new JButton("Done");
    private Font font2=new Font("Poppins",Font.BOLD,35);
    private Font font3=new Font("Poppins",Font.BOLD,16);
    private Font font4=new Font("Poppins",Font.BOLD,11);
    private Font font5=new Font("Poppins",Font.BOLD,23);

    public Gui_Book_Confirm(Consultation consultation){

        frame.setVisible(true);
        frame.setTitle("Westminster Skin Consultation Manager");
        frame.setSize(900,800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        main_name.setFont(font2);
        main_name.setSize(700,80);
        main_name.setLocation(340,25);

        panel1.setSize(900,800);
        panel1.setBackground(Color.white);
        panel1.setLayout(null);
        panel1.setVisible(true);

        succ_l.setSize(240,300);
        succ_l.setLocation(290,120);

        doc_name.setFont(font5);
        doc_name.setSize(700,80);
        doc_name.setLocation(290,400);
        doc_name.setText("Dr."+consultation.getDoctor().getName()+" "+consultation.getDoctor().getSurname());

        date.setFont(font5);
        date.setSize(700,80);
        date.setLocation(300,460);
        date.setText(consultation.getConsultation_date());

        time.setFont(font5);
        time.setSize(700,80);
        time.setLocation(480,460);
        time.setText(consultation.getConsultation_time());

        cost.setFont(font5);
        cost.setSize(700,80);
        cost.setLocation(400,520);
        cost.setText("£"+consultation.getCost());


        finish_but.setSize(250,55);
        finish_but.setLocation(310,630);
        finish_but.setFont(font3);
        finish_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new Gui_Main().tablePart_Panel();
            }
        });

        panel1.add(doc_name);
        panel1.add(date);
        panel1.add(cost);
        panel1.add(time);
        panel1.add(finish_but);
        panel1.add(succ_l);
        panel1.add(main_name);
        frame.add(panel1);
    }
}
