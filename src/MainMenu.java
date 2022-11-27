import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


//Clasa publica principala pentru generarea panoului principal de meniu
public class MainMenu extends JPanel {

    //Butoane ale panoului principal, care permit accesarea functionalitatii jocului
    //cat si a informatiilor detalie in legatura cu acest joc
    JButton start,howToUse,info,exit,upload;
    JPanel createATest,fileName;

    //Variabila booleana care asigura faptul ca nu va porni testul grila
    //pana la apasarea butonului de start
    static boolean initialiseGame = false;


    //Constructor al clasei MainMenu, care seteaza proprietatile ferestrei
    //si a tuturor componentelor cuprinse in aceasta
    MainMenu(JFrame fereastra) {
        //Setarea dimensiunilor ferestrei si a design-ului
        setSize(fereastra.getSize().width, fereastra.getSize().height);
        setLayout(null);
        setBackground(new Color(253, 237, 253));
        fereastra.setContentPane(this);


        JLabel test = new JLabel("Test Grila");
        test.setBounds(360, 10, 300, 50);
        test.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
        add(test);

        JLabel creator = new JLabel("Realizat de: Fisca Stephanie");
        creator.setBounds(570, 420, 300, 50);
        creator.setFont(new Font(Font.SERIF, Font.ITALIC, 15));
        add(creator);

        JSeparator sep = new JSeparator();
        sep.setBounds(50, 80, 680, 5);
        add(sep);

        //Cream un nou buton care va permite completarea testului grila
        start = new JButton("Creaza un test");
        //Setam componentele de aspect ale acestuia si il adaugam pe fereastra
        start.setBounds(50, 130, 200, 50);
        start.setFont(new Font(Font.SERIF, Font.ITALIC, 15));
        start.setBackground(new Color(253, 196, 253));
        add(start);

        //Cream un nou buton care va permite incarcarea unui fisier folosit ca si test
        upload = new JButton("Incarca un test");
        upload.setBounds(50, 220, 200, 50);
        upload.setFont(new Font(Font.SERIF, Font.ITALIC, 15));
        upload.setBackground(new Color(253, 196, 253));
        add(upload);

        //Cream un nou buton care va deschide o noua fereastra cu si modul de functionare
        //a testului grila
        howToUse = new JButton("Reguli");
        //Setam componentele de aspect ale acestuia si il adaugam pe fereastra
        howToUse.setBackground(new Color(253, 196, 253));
        howToUse.setBounds(50, 310, 200, 50);
        howToUse.setFont(new Font(Font.SERIF, Font.ITALIC, 15));
        add(howToUse);

        //Cream un nou buton care va deschide o nous fereastra cu informatii detaliate
        //despre testul grila
        info = new JButton("Informatii despre creator");
        //Setam componentele de aspect ale acestuia si il adaugam pe fereastra
        info.setBackground(new Color(253, 196, 253));
        info.setBounds(50, 400, 200, 50);
        info.setFont(new Font(Font.SERIF, Font.ITALIC, 15));
        add(info);

        //Cream un nou buton care va permite inchiderea testului grila
        exit = new JButton("Iesire");
        //Setam componentele de aspect ale acestuia si il adaugam pe fereastra
        exit.setBackground(new Color(253, 196, 253));
        exit.setBounds(300, 400, 200, 50);
        exit.setFont(new Font(Font.SERIF, Font.ITALIC, 15));
        add(exit);

        createATest = new JPanel();
        new CreateATest(fereastra,createATest);

        fereastra.setVisible(true);

    }

    //Adaugarea posibilitatea de a asculta pentru un eveniment pe butoanele noastre
    void eventListenerOnButtons(int time){

        //Butonul de pornire a testului
        start.addActionListener((ActionEvent e)->{
            createATest.setVisible(true);
        });

        upload.addActionListener((ActionEvent e)->{
            initialiseGame=true;
            setVisible(false);
        });

        //Butonul care afiseaza instructiunile de utilizare
        howToUse.addActionListener((ActionEvent e)->{
            new moreInfo(time);
        });

        //Butonul care afiseaza mai multe informatii despre test
        info.addActionListener((ActionEvent e)->{
            new CreatorsDetails();
        });

        //Butonul care inchide aplicatia
        exit.addActionListener((ActionEvent e)->{
            System.exit(0);
        });


        while(!initialiseGame){
            try{
                Thread.sleep(0);
            }catch (InterruptedException ex){}
        }
    }

}
