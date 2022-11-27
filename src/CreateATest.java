import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

//Clasa care creaza un panel cu campurile necesare pentru a putea crea un test
public class CreateATest {

    //Constructorul clasei CreateATest
    CreateATest(JFrame fereastra, JPanel createATest){

        //Adaugarea componentelor JPanel-ului
        //Set visibile este fals deoarece panou-ul va deveni vizibil numai la apasarea butonului pentru a crea un test
        createATest.setVisible(false);
        createATest.setLayout(null);
        createATest.setBorder(LineBorder.createBlackLineBorder());
        createATest.setBounds(300, 130, 430, 250);
        createATest.setBackground(new Color(253, 196, 253));

        //Panou care v-a aparea la salvarea fisierului
        JPanel fileName = new JPanel();

        //Array list-ul in care vor fi stocate datele introduse de utilizator
        ArrayList<InitialiseData> theData = new ArrayList<>();

        JLabel registerTitle = new JLabel("Construieste testul grila");
        registerTitle.setBounds(150, 5, 200, 20);
        createATest.add(registerTitle);

        JLabel nrOfQuestion = new JLabel("Numar de intrebari:");
        nrOfQuestion.setBounds(10, 30, 200, 20);
        createATest.add(nrOfQuestion);

        //Adaugarea unui combo box pentru a permite selectarea dintr-un drop down a unei valori
        JComboBox<String> nrQ = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        nrQ.setBounds(150, 30, 50, 20);
        createATest.add(nrQ);

        JLabel questionNr = new JLabel("Intrebarea " + (theData.size() + 1) + ":");
        questionNr.setBounds(20, 50, 200, 20);
        createATest.add(questionNr);

        JLabel intrebare = new JLabel("Intrebarea nr. " + (theData.size() + 1) + ":");
        intrebare.setBounds(10, 70, 200, 20);
        JTextArea questionss = new JTextArea();
        questionss.setBounds(150, 70, 200, 20);
        questionss.setBorder(LineBorder.createBlackLineBorder());
        createATest.add(intrebare);
        createATest.add(questionss);

        JLabel answerNr1 = new JLabel("Posibil raspuns nr. " + (1) + ":");
        answerNr1.setBounds(10, 90, 200, 20);
        JTextArea answers = new JTextArea();
        answers.setBounds(150, 90, 200, 20);
        answers.setBorder(LineBorder.createBlackLineBorder());
        createATest.add(answers);
        createATest.add(answerNr1);

        JLabel answerNr2 = new JLabel("Posibil raspuns nr. " + (2) + ":");
        answerNr2.setBounds(10, 110, 200, 20);
        JTextArea answers2 = new JTextArea();
        answers2.setBounds(150, 110, 200, 20);
        answers2.setBorder(LineBorder.createBlackLineBorder());
        createATest.add(answers2);
        createATest.add(answerNr2);

        JLabel answerNr3 = new JLabel("Posibil raspuns nr. " + (3) + ":");
        answerNr3.setBounds(10, 130, 200, 20);
        JTextArea answers3 = new JTextArea();
        answers3.setBounds(150, 130, 200, 20);
        answers3.setBorder(LineBorder.createBlackLineBorder());
        createATest.add(answers3);
        createATest.add(answerNr3);

        JLabel answerNr4 = new JLabel("Posibil raspuns nr. " + (4) + ":");
        answerNr4.setBounds(10, 150, 200, 20);
        JTextArea answers4 = new JTextArea();
        answers4.setBounds(150, 150, 200, 20);
        answers4.setBorder(LineBorder.createBlackLineBorder());
        createATest.add(answers4);
        createATest.add(answerNr4);

        JLabel Raspuns = new JLabel("Raspuns corect:");
        Raspuns.setBounds(10, 170, 200, 20);
        JTextArea rasp = new JTextArea();
        rasp.setBounds(150, 170, 200, 20);
        rasp.setBorder(LineBorder.createBlackLineBorder());
        createATest.add(Raspuns);
        createATest.add(rasp);

        JLabel hints = new JLabel("Indiciu:");
        hints.setBounds(10, 190, 200, 20);
        JTextArea clue = new JTextArea();
        clue.setBounds(150, 190, 200, 20);
        clue.setBorder(LineBorder.createBlackLineBorder());
        createATest.add(hints);
        createATest.add(clue);

        JButton adaugaDate = new JButton("Inregistreaza Datele");
        adaugaDate.setBounds(120, 210, 250, 20);
        createATest.add(adaugaDate);

        //La fiecare apasare a butonului de "Adauga Date", acestea vor fi adaugate in array list in cazul in care
        //valorile introduse sunt valide
        adaugaDate.addActionListener((ActionEvent e) -> {

            //In cazul in care raspunsul corect nu exista in posibilele raspunsuri introduse
            if(!rasp.getText().toString().equals(answers.getText().toString())
                    && !rasp.getText().toString().equals(answers2.getText().toString())
                    && !rasp.getText().toString().equals(answers3.getText().toString())
                    && !rasp.getText().toString().equals(answers4.getText().toString())) {
                JOptionPane.showMessageDialog(null, "Raspunsul corect introdus nu coincide cu unul din posibilele variante de raspuns ", "Eroare!", JOptionPane.INFORMATION_MESSAGE);
            }
            //Cazul in care nu s-au completat toate campurile
            else if (rasp.getText().toString().equals("") || answers.getText().toString().equals("")
                        || answers2.getText().toString().equals("")
                        || answers3.getText().toString().equals("")
                        || answers4.getText().toString().equals("")
                        || clue.getText().toString().equals("")){
                JOptionPane.showMessageDialog(null, "Completati toate campurile!", "Eroare!", JOptionPane.INFORMATION_MESSAGE);
            }

            else{
                //Adaugarea datelor in array list
                theData.add(new InitialiseData(questionss.getText().toString(), answers.getText().toString(), answers2.getText().toString(), answers3.getText().toString(), answers4.getText().toString(), rasp.getText().toString(), clue.getText().toString()));

                //resetarea campurilor
                questionss.setText("");
                answers.setText("");
                answers2.setText("");
                answers3.setText("");
                answers4.setText("");
                rasp.setText("");
                clue.setText("");
                questionNr.setText("Intrebarea "+(theData.size()+1));
                intrebare.setText("Intrebarea nr. "+(theData.size()+1)+":");

                //Daca s-au introdus toate datele pentru numarul de intrebari specificate
                if (Integer.valueOf(nrQ.getItemAt(nrQ.getSelectedIndex())) == theData.size()) {

                    //Acest panou v-a deveni invizibil
                    createATest.setVisible(false);

                    //Adaugam pe frame panel-ul raspunzator la preluarea de nume a fisierului de la utilizator
                    fileName.setLayout(null);
                    fileName.setBorder(LineBorder.createBlackLineBorder());
                    fileName.setBounds(300, 130, 430, 250);
                    fileName.setBackground(new Color(253, 196, 253));
                    fileName.setVisible(true);

                    JLabel theName = new JLabel("Numele Fisierului:");
                    theName.setBounds(10, 100, 200, 20);
                    JTextArea name = new JTextArea();
                    name.setBounds(150, 100, 200, 20);
                    name.setBorder(LineBorder.createBlackLineBorder());

                    JButton save = new JButton("Salvare");
                    save.setBounds(150,130,200,20);
                    fileName.add(save);
                    fileName.add(theName);
                    fileName.add(name);

                    //Salvarea fisierului in folder-ul Tests
                    save.addActionListener((ActionEvent a)->{
                        if(name.getText().toString().equals("")){
                            JOptionPane.showMessageDialog(null, "Nu ati numit testul!", "Eroare!", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                        try {
                            new StoreTest(Integer.valueOf(nrQ.getItemAt(nrQ.getSelectedIndex())),theData,name.getText().toString());
                            fileName.setVisible(false);
                        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException ex) {
                            ex.printStackTrace();
                        }
                    });

                }
            }
        });

        //Facem fereastra vizibila
        fereastra.add(createATest);
        fereastra.add(fileName);
    }
}
