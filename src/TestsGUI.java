import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class TestsGUI extends JPanel {

    //Doua label-uri, una care v-a afisa intrebarile testului grila,
    //cealalta indicand indiciul
    JLabel Q,hintLabel;

    //Cele 4 butoane care vor fi utilizate pentru selectarea raspunsului dorit
    JButton a1,a2,a3,a4;

    //Siruri de caractere care vor marca raspunsul corect al intrebarii,
    //cat si indiciul
    String theAnswer,theClue,theQuestion;

    //Variabila booleana utilizata pentru accesarea intrebarii urmatoare
    static boolean next = false;

    //Variabila care v-a memora numarul de raspunsuri corecte
    static int score = 0;

    //Un label care v-a marca cat timp este stat pe fiecare intrabare
    static JLabel timer = new JLabel("00:00:000");

    //Variabila care v-a numara raspunsurile
    static counter count = new counter();

    Timer t = null;

    TestsGUI(InitialiseData obj, JFrame window){

        //Initializarea componentelor interfetei grafice in constructor
        Q=new JLabel((obj.question));
        hintLabel=new JLabel("Indiciu");
        a1=new JButton(obj.op1);
        a2=new JButton(obj.op2);
        a3=new JButton(obj.op3);
        a4=new JButton(obj.op4);
        theQuestion=obj.question;
        theAnswer=obj.answer;
        theClue=obj.clue;

        //Crearea unui panou nou
        JPanel fereastra = new JPanel();
        fereastra.setLayout(null);
        fereastra.setBorder(BorderFactory.createLineBorder(new Color(255, 231, 189)));
        fereastra.setBackground(new Color(255, 231, 189));
        window.setContentPane(fereastra);
        setLayout(null);
        setBackground(new Color(255, 178, 46));
        setBounds(90,170,600,200);
        setBorder(BorderFactory.createLineBorder(Color.black));
        fereastra.add(this);

        add(Q);add(a1);add(a2);add(a3);add(a4);

        Q.setBounds(110,8,400,50);
        Q.setBackground(new Color(255, 231, 189));
        Q.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        Q.setHorizontalAlignment(JLabel.CENTER);


        a1.setBounds(90,70,200,40);
        a1.setBackground(new Color(255, 231, 189));
        a1.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

        a2.setBounds(90,140,200,40);
        a2.setBackground(new Color(255, 231, 189));
        a2.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

        a3.setBounds(330,70,200,40);
        a3.setBackground(new Color(255, 231, 189));
        a3.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

        a4.setBounds(330,140,200,40);
        a4.setBackground(new Color(255, 231, 189));
        a4.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

        timer.setBounds(250,400,300,50);
        timer.setFont(new Font("Verdana",Font.BOLD,40));
        timer.setHorizontalAlignment(JLabel.CENTER);
        timer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        timer.setForeground(Color.black);
        fereastra.add(timer);

        //Adaugarea unei poze cu creatorul testului grila (Fisca Stephanie)
        ImageIcon thePic = new ImageIcon(getClass().getResource("5050.png"));
        Image aux = thePic.getImage();
        Image aux2 = aux.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        thePic = new ImageIcon(aux2);
        JButton half = new JButton(thePic);

        //Adaugarea unei poze cu creatorul testului grila (Fisca Stephanie)
        ImageIcon thePic2 = new ImageIcon(getClass().getResource("clue.png"));
        Image aux3 = thePic2.getImage();
        Image aux4 = aux3.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        thePic2 = new ImageIcon(aux4);
        JButton hintBTN = new JButton(thePic2);


        fereastra.add(half);
        fereastra.add(hintBTN);
        fereastra.add(hintLabel);
        hintBTN.setBounds(200,50,80,80);
        hintBTN.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        half.setBounds(500,50,80,80);
        half.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        hintLabel.setBounds(290,50,200,80);
        hintLabel.setHorizontalAlignment(JLabel.CENTER);
        hintLabel.setForeground(Color.BLACK);
        hintLabel.setBorder(new LineBorder(new Color(255, 178, 46),2,true));
        hintLabel.setFont(new Font(Font.SERIF,Font.ITALIC,15));

        //La apasarea butonului de hint, se va afisa indiciul in labelul corespunzator
        hintBTN.addActionListener((ActionEvent e) -> {
            hintLabel.setText(theClue);
        });

        //La apasarea butonului de 50/50 , doua butoane se vor colora rosu
        half.addActionListener((ActionEvent e) -> {
            if (!a1.getText().equals(theAnswer)) {
                a1.setEnabled(false);
                a1.setBackground(Color.red);
            }else if (!a2.getText().equals(theAnswer)) {
                a2.setEnabled(false);
                a2.setBackground(Color.red);
            }
            if (!a3.getText().equals(theAnswer)) {
                a3.setEnabled(false);
                a3.setBackground(Color.red);
            }else if (!a4.getText().equals(theAnswer)) {
                a4.setEnabled(false);
                a4.setBackground(Color.red);
            }
        });

        window.setVisible(true);

    }

    void getAnswer(int time) throws InterruptedException, IOException {
        MainTest.report.append(theQuestion);
        MainTest.report.flush();
        a1.addActionListener((ActionEvent e)->{
            try {
                MainTest.report.append(",x,,,,");
                MainTest.report.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            new BuildingReport(a1,score,theAnswer,a1,a2,a3,a4);
        });

        a2.addActionListener((ActionEvent e)->{
            try {
                MainTest.report.append(",,x,,,");
                MainTest.report.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            new BuildingReport(a2,score,theAnswer,a1,a2,a3,a4);
        });

        a3.addActionListener((ActionEvent e)->{
            try {
                MainTest.report.append(",,,x,,");
                MainTest.report.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            new BuildingReport(a3,score,theAnswer,a1,a2,a3,a4);
        });
        a4.addActionListener((ActionEvent e)->{
            try {
                MainTest.report.append(",,,x,,");
                MainTest.report.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            new BuildingReport(a4,score,theAnswer,a1,a2,a3,a4);
        });

        while(next==false){
            timer.setText(String.format("%02d",count.M)+" : "+String.format("%02d",count.S)+" : "+String.format("%03d",count.Ms));
            count.Ms++;
            Thread.sleep(1);
            if(count.Ms==999){
                count.S++;
                count.Ms=0;
            }

            if(count.S==59){
                count.M++;
                count.S=0;
            }

            if((count.S + count.M*60 )>(time-3)){
                timer.setForeground(Color.red);
                if((count.S+count.M*60)==time){
                    return;
                }
            }
        }
        next=false;
    }

    int getScore(){
        return score;
    }

    counter getTime(){
        return count;
    }

    void Reset(){
        count.M=0;
        count.Ms=0;
        count.S=0;
        score=0;
    }

}
