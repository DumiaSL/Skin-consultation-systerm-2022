package courswork.grafic;

/**
 *
 * @author Dumidu
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Gui_Patient_Type extends JFrame{
    private final JFrame frame =new JFrame();
    private final JPanel panel1=new JPanel();

    private final ImageIcon asis=new ImageIcon("photos/assistent.jpg");
    private final JLabel asis_l=new JLabel(asis);

    private final ImageIcon bar=new ImageIcon("photos/Picture1.png");
    private final JLabel bars=new JLabel(bar);
    private final JLabel  main_name=new JLabel("Patient Type");
    private final JButton but1=new JButton("New Patient ");
    private final JButton but2=new JButton("Existing Patient");
    private final JButton but3=new JButton("Edit Patient");
    private final  JButton back_but=new JButton("Back");

    private final Font font2=new Font("Poppins",Font.BOLD,35);
    private final  Font font3=new Font("Poppins",Font.BOLD,16);

    public Gui_Patient_Type(){
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

        asis_l.setSize(430,500);
        asis_l.setLocation(220,400);

        bars.setSize(430,150);
        bars.setLocation(210,110);

        main_name.setFont(font2);
        main_name.setSize(700,80);
        main_name.setLocation(320,25);

        back_but.setSize(150,55);
        back_but.setLocation(55,655);
        back_but.setFont(font3);
        back_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Gui_Main A=new Gui_Main();
                A.tablePart_Panel();
            }
        });

        but1.setSize(200,55);
        but1.setLocation(70,250);
        but1.setFont(font3);
        but1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                try {
                    new Gui_Add_New_Patient();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        but2.setSize(200,55);
        but2.setLocation(330,250);
        but2.setFont(font3);
        but2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                try {
                    new Gui_Book_Consultation();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        but3.setSize(200,55);
        but3.setLocation(590,250);
        but3.setFont(font3);
        but3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                try {
                    new Gui_Edit_Patient();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        panel1.add(but1);
        panel1.add(but2);
        panel1.add(but3);
        panel1.add(main_name);
        panel1.add(asis_l);
        panel1.add(bars);
        panel1.add(back_but);
        frame.add(panel1);
    }
}
