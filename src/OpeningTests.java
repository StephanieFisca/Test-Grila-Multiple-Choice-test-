import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;

//Clasa care permite deschiderea testelor criptate
public class OpeningTests {

    InitialiseData[] data;
    static File inFileName;
    FileOutputStream fileOutputStream;

    //Constructorul clasei
    OpeningTests() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException {

        //Se alege un test
        JFileChooser chooser = new JFileChooser(".");
        chooser.showOpenDialog(null);
        inFileName = chooser.getSelectedFile();
        if(inFileName!=null){
        try{

            FileInputStream fileInputStream = new FileInputStream(inFileName);

            //Se creaza un nou fisier care v-a fi cel decriptat
            fileOutputStream = new FileOutputStream(inFileName+"_decrypted.txt");

            //Cream un panou pentru permiterea introducerii parolei de deschidere a fisierului
            JPanel jPanel = new JPanel();
            JLabel label = new JLabel("Enter the files password: ");
            JPasswordField jPasswordField = new JPasswordField(8);
            jPasswordField.setActionCommand("OK");
            jPanel.add(label);
            jPanel.add(jPasswordField);

            String[] options = new String[]{
                    "Ok"
            };
            do{

                JOptionPane dialog = new JOptionPane();
            int option = dialog.showOptionDialog(null,jPanel,"DECRYPTING FILE",JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);

            //Daca parola introdusa este corecta, se decripteaza fisierul
            if(option==0){
                char[] password  = jPasswordField.getPassword();
                String realPassword = new String(password);
                if(realPassword.equals("12345678"))
                StoreTest.decrypt(realPassword,fileInputStream,fileOutputStream);
                else{
                    JOptionPane.showMessageDialog(null,"The password is incorrect!","Error",JOptionPane.ERROR_MESSAGE);
                    jPasswordField.setText("");
                }
            }
            }while(!new String(jPasswordField.getPassword()).equals("12345678"));

        }catch (Throwable ex){
            ex.printStackTrace();
        }


        BufferedReader br=new BufferedReader(new FileReader(inFileName+"_decrypted.txt"));  //creates a buffering character input stream
        String line;
        int i=0;
        data = new InitialiseData[Integer.valueOf(br.readLine())];
        while((line=br.readLine())!=null)
        {
            String question,op1,op2,op3,op4,answer,clue;
            question=line;
            op1=br.readLine();
            op2=br.readLine();
            op3=br.readLine();
            op4=br.readLine();
            answer=br.readLine();
            clue=br.readLine();
            data[i++]= new InitialiseData(question,op1,op2,op3,op4,answer,clue);
        }
        br.close();    //closes the stream and release the resources
        new File(inFileName+"_decrypted.txt").delete();
        }
    }

    public InitialiseData[] getData(){
        return data;
    }

    public static File getFileName(){
        return inFileName;
    }
}
