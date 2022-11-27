import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

//Clasa care poarta rolul de a prezenta informatiile creator-ului de test
public class CreatorsDetails extends JFrame {

    //Constructorul CreatorsDetails
    CreatorsDetails(){

        //Setarea componentelor de design al ferestrei
        setSize(800,500);
        setResizable(false);
        setLocationRelativeTo(null);

        //Crearea unui nou panou
        JPanel usagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Setarea componentelor de design al panoului nou
        usagePanel.setSize(this.getSize().width,this.getSize().height);
        usagePanel.setBackground(new Color(199, 255, 216));
        usagePanel.setLayout(null);
        setLocationRelativeTo(usagePanel);
        setContentPane(usagePanel);

        //Crearea unui nou label care va marca bordura panoului
        JLabel border = new JLabel();
        border.setBorder(new LineBorder(Color.BLACK,2,true));
        border.setBounds(30,30,720,350);
        border.setHorizontalAlignment(JLabel.CENTER);
        //Adaugam bordura cuprinsa in label pe panoul principal
        usagePanel.add(border);

        //Adaugarea unei poze cu creatorul testului grila (Fisca Stephanie)
        ImageIcon thePic = new ImageIcon(getClass().getResource("selfie2022.jpg"));
        Image aux = thePic.getImage();
        Image aux2 = aux.getScaledInstance(450,300, Image.SCALE_SMOOTH);
        thePic = new ImageIcon(aux2);
        JLabel selfie = new JLabel(thePic);
        selfie.setHorizontalAlignment(JLabel.CENTER);

        //Adaugam un label cu numele creatorului
        JLabel name = new JLabel("<html><center>Stephanie Fisca <center></html>");

        //Adaugam un nou label care pentru copyright claim pe aplicatie
        JLabel copyright = new JLabel ("<html><center>Copyright © 2022 Stephanie Fisca, Inc. All rights reserved" + ".<center></html>");

        JLabel aboutMe = new JLabel("<html><center> Ma numesc Fisca Stephanie, am 19 ani impliniti si lucrez la a deveni un programator " +
                "inca de cand aveam 14 ani. Am realizat acest program care permite rularea unui test grila cu ajutorul limbajului Java, utilizand swing pentru crearea " +
                "interfetei grafice. Sunt pasionata de limbajul Python, limbaj in care am debutat in lumea programarii, incercand usor si diverse alte limbaje pentru a descoperi" +
                " limbajul in care as vrea sa profesez.");

        //Adaugam un nou label care sa cuprinda detaliile de contact
        JLabel contact = new JLabel ("<html>"
                + "<center>Nr. Telefon: 0741254240</center>"+
                "<center>Facebook:</center> <a href='' style='color:blue;'> https://www.facebook.com/stephanie.fisca</a><br/>" +
                "Email : stephaniefisca02@gmail.com<br/>"+
                "GitHub  : <a href=''>https://github.com/StephanieFisca</a>"
                + "</html>");

        aboutMe.setBounds(430,20,250,180);
        aboutMe.setForeground(Color.black);
        border.add(aboutMe);

        //Adaugam componentele de design ale label-urilor si le adaugam pe panoul principal
        copyright.setBounds(450,310,200,30);
        copyright.setForeground(Color.black);
        border.add(copyright);

        contact.setBounds(430,190,250,150);
        contact.setForeground(Color.black);
        border.add(contact);

        name.setBounds(430,190,250,150);
        name.setFont(new Font("Verdana", Font.BOLD, 10));
        name.setForeground(Color.black);
        selfie.setBounds(10,10,370,330);
        selfie.add(name);

        JSeparator sep = new JSeparator();
        JSeparator sep2 = new JSeparator();

        sep.setBounds(400,220,300,5);
        sep2.setBounds(420,310,250,5);

        border.add(sep);
        border.add(sep2);
        border.add(selfie);
        setVisible(true);

        //Setarea componentelor butonului de 'redo'
        JButton redo=new JButton("Meniul Principal");
        redo.setBackground(new Color(255,255,255));
        redo.setBounds(600,400,150,30);
        add(redo);

        redo.addActionListener((ActionEvent e)->{
            setVisible(false);
        });
    }
}
