import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Clasa care v-a fi utilizata pentru afisarea timpului petrecut la fiecare intrebare
public class counter{
    //M-minute
    //S - secunde
    //Ms - milisecunde
    int M,S,Ms;

    //Constructor care initializeaza toate cele 3 variabile cu valoarea de start 0
    counter(){
        M=0;
        S=0;
        Ms=0;
    }
//Functie care reseteaza valorile variabilelor, cat si text-ul afisat in labelul specific
    //timpului ramas
    void reset(JButton btn, JLabel l){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                M=0;
                S=0;
                Ms=0;
                l.setText("00 : 00 : 000");
            }
        });
    }
}