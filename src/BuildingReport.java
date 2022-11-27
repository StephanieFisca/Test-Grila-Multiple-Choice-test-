import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//Class that inserts the correct answers into the report and adds a delay to the test after the user has pressed a button
public class BuildingReport {

    Timer t = null;

    BuildingReport(JButton a, Integer score,String theAnswer,JButton a1,JButton a2, JButton a3,JButton a4){
        if (a.getText().equals(theAnswer)){
            TestsGUI.score++;
            a.setBackground(Color.green);
        }
        else
            a.setBackground(Color.red);


        t = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.stop();
                TestsGUI.next=true;
            }
        });
        t.start();
        try {
            if(theAnswer.equals(a1.getText())){
                MainTest.report.append("op1\n");
                MainTest.report.flush();
            }
            else if (theAnswer.equals(a2.getText())){
                MainTest.report.append("op2\n");
                MainTest.report.flush();
            }
            else if (theAnswer.equals(a3.getText())){
                MainTest.report.append("op3\n");
                MainTest.report.flush();
            }
            else if(theAnswer.equals(a4.getText())){
                MainTest.report.append("op4\n");
                MainTest.report.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
