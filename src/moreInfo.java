import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

//Clasa publica care asigura functionalitatea ferestrei care explica
//modul de functionare a testului grila
public class moreInfo extends JFrame {

    //Constructorul clasei moreInfo
    moreInfo(int time){

        //Setarea proprietatilor ferestrei de design
        setSize(800,500);
        setLocationRelativeTo(null);
        setResizable(false);

        //Crearea unui nou panou
        JPanel usagePanel=new JPanel();

        //Setarea properitatilor de design a panoului nou
        usagePanel.setSize(this.getSize().width,this.getSize().height);
        usagePanel.setBackground(new Color(226, 250, 253));
        usagePanel.setLayout(null);
        setContentPane(usagePanel);

        //Setarea unui label care explica cum functioneaza testul grila
        JLabel usageLabel = new JLabel("<html><center>Ai "+ time/60+" min "+time%60 +" secunde sa raspunzi la intrebarile incarcate din fisierul incarcat.\n"+
                "                              Se pot utiliza tool-urile de ajutor, cum ar fi '50:50' pentru a sterge doua posibile raspunsuri incorecte \n" +
                "                                    sau se poate utiliza hint-ul pentru a se da un indiciu legat de raspuns. La final, veti putea vizualiza raportul testului.</center></html>");

        //Setarea proprietatilor de design ale label-ului
        usageLabel.setFont(new Font("Verdana",Font.BOLD,10));
        usageLabel.setBounds(180,0,400,100);
        usageLabel.setForeground(Color.BLACK);

        //Crearea unui nou label care va actiona ca bordura ferestrei
        JLabel border = new JLabel();
        border.setBorder(new LineBorder(Color.BLACK,2,true));
        border.setBounds(10,5,750,400);


        ImageIcon thePic = new ImageIcon(getClass().getResource("PictureOfGame.jpg"));
        Image aux = thePic.getImage();
        Image aux2 = aux.getScaledInstance(500,400, Image.SCALE_SMOOTH);
        thePic = new ImageIcon(aux2);
        JLabel gamesDesign = new JLabel(thePic);
        gamesDesign.setHorizontalAlignment(JLabel.CENTER);

        //Setarea dimensiunilor label-ului cu poza
        gamesDesign.setBounds(120,90,500,300);

        //Adaugarea label-ului cu explicatia modului de functionare a testului in label-ul 'border'
        border.add(usageLabel);

        //Adaugarea label-ului cu poza in cadrul label-ului 'border'
        border.add(gamesDesign);

        //Adaugarea label-ului 'border' in panoul principal usagePanel cu toate componentele cuprinse in acesta
        usagePanel.add(border);

        //Setam panoul ca fiind vizibil
        setVisible(true);

        //Setarea componentelor butonului de 'redo'
        JButton redo=new JButton("Inapoi la meniul principal");
        redo.setBackground(new Color(255,255,255));
        redo.setBounds(550,420,200,30);
        add(redo);

        redo.addActionListener((ActionEvent e)->{
            setVisible(false);
        });
    }
}
