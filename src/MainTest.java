import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//Clasa principala
public class MainTest {
    public static FileWriter report;
    public static String reportName;

    //Functia main
    public static void main(String[] args) throws InterruptedException, IOException, NoSuchPaddingException, NoSuchAlgorithmException {

        //Setarea ferestrei principale si aspectele acestuia
        JFrame fereastra = new JFrame("Test Grila");
        fereastra.setSize(800,500);
        fereastra.setLocationRelativeTo(null);
        fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fereastra.setResizable(false);

        while (true){
            int nbr=0, score=0,m=0,s=0,time=30;

            //Deschiderea meniului principal
            MainMenu welcome = new MainMenu(fereastra);
            welcome.eventListenerOnButtons(time);
            //Alegem un test
            InitialiseData[] qObj=new OpeningTests().getData();

            if(qObj!=null) {
                try {
                    //Cream un raport unde numele acestuia este compus din numele testului + data si ora actuala
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
                    File file = new File("Reports/" + OpeningTests.inFileName.getName() + "-" + formatter.format(calendar.getTimeInMillis()).toString() + ".csv");

                    //Se creaza fisierul raport
                    file.createNewFile();
                    reportName = file.getName();
                    //Se adauga pe prima linie urmatoarele date de baza
                    report = new FileWriter(file);
                    report.append("Cerinta intrebarii, optiunea 1, optiunea 2, optiunea 3,optiunea 4, Raspuns Corect\n");
                    //Dam un flush la stream (curatam stream-ul)
                    report.flush();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }


                //Cat timp nu s-a ajuns la sfarsitul testului si inca a ramas timp
                while (nbr != qObj.length && s < time) {
                    //Rulam test-ul
                    TestsGUI quiz = new TestsGUI(qObj[nbr], fereastra);
                    quiz.getAnswer(time);
                    m = quiz.getTime().M;
                    s = quiz.getTime().S;
                    score = quiz.getScore();
                    //Se va reseta testul daca s-a ramas fara timp sau s-au prcurs toate intrebarile
                    if (nbr == qObj.length - 1 || (s == time))
                        quiz.Reset();
                    nbr++;
                }
                //Se afiseaza score-ul final
                int nbrQ = qObj.length;
                ScorePanel scorePanel = new ScorePanel(fereastra, score, nbrQ);
                scorePanel.choose();
            }
        }
    }
}
