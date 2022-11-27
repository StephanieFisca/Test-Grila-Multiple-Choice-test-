import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class ScorePanel extends JPanel {
    //Butoane care permit reluarea testului grila sau inchiderea aplicatie
    JButton redo, exit,report;

    //Un label utilizat pentru afisarea punctajului obtinut
    JLabel scoreLabel;

    //Variabila de tip bool care permite reluarea jocului
    static boolean again = false;

    //Constructorul clasei ScorePanel
    ScorePanel(JFrame fereastra, int punctaj, int nrOfQuestions) throws IOException {
        //Setarea dimensiunilor si componentelor ferestrei
        setSize(fereastra.getSize().width,fereastra.getSize().height);
        setLayout(null);
        setBackground(new Color(253, 196, 253));
        fereastra.setContentPane(this);


        //Setarea componentelor butonului de 'exit'
        exit = new JButton("Exit");
        exit.setBackground(Color.white);
        exit.setBounds(450,350,200,50);
        add(exit);

        //Vizualizarea raportului
        report = new JButton("Report");
        report.setBackground(Color.white);
        report.setBounds(150,350,200,50);
        add(report);

        //Setarea componentelor label-ului care afiseaza punctajul obtinut
        scoreLabel = new JLabel("You got: "+punctaj+"/"+nrOfQuestions);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setFont(new Font("Verdana",Font.BOLD,40));
        scoreLabel.setForeground(Color.black);
        scoreLabel.setBorder(new LineBorder(Color.black,2,true));
        scoreLabel.setBounds(200,100,400,100);
        add(scoreLabel);

        MainTest.report.append("\n\nPunctaj obtinut:,"+punctaj+" din "+nrOfQuestions+" intrebari!");
        MainTest.report.flush();
        MainTest.report.close();
        //Facem noua fereastra vizibila
        fereastra.setVisible(true);
    }

    //Functie care gestioneaza diferitele cazuri atunci cand apasam pe butoane
    void choose(){

        //In cazul in care se doreste inchiderea programului,
        //acesta se v-a inchide
        exit.addActionListener((ActionEvent e)->{
            System.exit(0);
        });

        report.addActionListener((ActionEvent e)->{
            try {
                Desktop.getDesktop().open(new File("Reports/"+MainTest.reportName));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        //Cat timp nu s-a apasat pe vreun buton
        while(!again){
            try{
                Thread.sleep(0);
            }catch (InterruptedException ex){}
        }

        //Nu se doreste reinceperea testului
        again=false;
    }
}
