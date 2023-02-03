package courswork.grafic;
/**
 *
 * @author Dumidu
 */

import javax.swing.*;
import java.awt.*;

public class Gui_Splash_Screen extends JFrame{
    JFrame frame;

    private JLabel text=new JLabel("Westminster Skin Consultation Center");
    private JProgressBar progressBar=new JProgressBar();
    private JLabel message=new JLabel();

    private ImageIcon Im=new ImageIcon("photos/sload.png");
    private JLabel image_l=new JLabel(Im);
    public Gui_Splash_Screen()
    {
        createGUI();
        addText();
        addMessage();
        image_l.setSize(600,400);
        image_l.setLocation(0,0);
        frame.add(image_l);
        addProgressBar();

        runningPBar();
        new Gui_Main();
    }
    private void createGUI(){
        frame=new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.white);
        frame.setVisible(true);
    }

    private void addText()
    {
        text.setFont(new Font("Poppins",Font.BOLD,28));
        text.setBounds(27,50,600,40);
        text.setForeground(Color.red);
        frame.add(text);
    }
    private void addMessage()
    {
        message.setBounds(250,350,200,40);
        message.setForeground(Color.black);
        message.setFont(new Font("Poppins",Font.BOLD,17));
        frame.add(message);
    }
    private void addProgressBar(){
        progressBar.setBounds(100,280,400,30);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.black);
        progressBar.setValue(0);
        frame.add(progressBar);
    }
    private void runningPBar(){
        int i=0;

        while( i<=100)
        {
            try{
                Thread.sleep(20);
                progressBar.setValue(i);
                message.setText("LOADING "+i+"%");
                i++;
                if(i==100)
                    frame.dispose();
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }
}
