import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class StoreTest {


    //metoda care crypteaza toate byte-urile fisierului
    public static void doCopy(InputStream is,OutputStream os)throws IOException{
        byte[] bytes = new byte[64];
        int numBytes;

        while((numBytes= is.read(bytes))!=-1){
            os.write(bytes,0,numBytes);
        }

        //curatarea stream-ului
        os.flush();
        os.close();
        is.close();
    }

    public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable{

        //Cream un 'data encryption standard' key prin utilizarea obiectului DESKeySpec. Acesta foloseste un
        //'cipher' pentru cryptarea cheii date
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());

        //obtinem specificatiile unei chei din fabrica 'DES'
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");

        //Obtinem cheia cryptata din fabra de chei pentru cheia noastra
        SecretKey destinationKey = secretKeyFactory.generateSecret(desKeySpec);

        //Cream un obiect de tip Cipher pentru 'DES'
        Cipher cipher = Cipher.getInstance("DES");

        //Daca se doreste cryptare
        if(mode == Cipher.ENCRYPT_MODE){
            //Initializam cipher-ul pentru cryptare cu cheia generata
            cipher.init(Cipher.ENCRYPT_MODE,destinationKey);

            //Citim fisierul care a fost procesat de cipher prin utilizarea clasei cipherinputstreak
            CipherInputStream cipherInputStream = new CipherInputStream(is,cipher);
            doCopy(cipherInputStream,os);
        }
        //Daca se doreste decryptarea
        else if (mode==Cipher.DECRYPT_MODE){
            //Initializam cipher-ul pentru decryptare cu cheia generata
            cipher.init(Cipher.DECRYPT_MODE,destinationKey);

            //Cream un obiect cipheroutputstream care creaza un fisier gol care poate fi procesat de cipher
            CipherOutputStream cipherOutputStream = new CipherOutputStream(os,cipher);

            //copiem datele din fisierul necryptat in cipheroutputstream, fisierul criptat de catre cipher
            doCopy(is,cipherOutputStream);
        }
    }

    public static void encrypt(String key, InputStream is, OutputStream os)throws Throwable{
        //Apelam optiunea de cryptae
        encryptOrDecrypt(key,Cipher.ENCRYPT_MODE,is,os);
    }

    public static void decrypt(String key, InputStream is, OutputStream os)throws Throwable{
        //Apelam optiunea de decryptare
        encryptOrDecrypt(key,Cipher.DECRYPT_MODE,is,os);
    }

    //Constructorul clasei StoreTest
    StoreTest(Integer n, ArrayList<InitialiseData> x,String fileName) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

        //Fisierul este creat in folder-ul Tests
        File myObj = new File("./Tests/"+fileName);
        myObj.createNewFile();

        //Creaza un obiect pentru scrierea in fisierul 'myObj'
        FileWriter myWriter = new FileWriter(myObj);

        //Scriem numarul de intrebari
        myWriter.write(n.toString());

        //Scriem toate datele in fisier
        for (InitialiseData s: x) {
            myWriter.write("\n");
            myWriter.write(s.question);
            myWriter.write("\n");
            myWriter.write(s.op1);
            myWriter.write("\n");
            myWriter.write(s.op2);
            myWriter.write("\n");
            myWriter.write(s.op3);
            myWriter.write("\n");
            myWriter.write(s.op4);
            myWriter.write("\n");
            myWriter.write(s.answer);
            myWriter.write("\n");
            myWriter.write(s.clue);
        }
        myWriter.close();

        try{
            //key - cheia pentru cryptarea fisierului
            String key = "12345678";

            //Utilizam obiectul FileInputStream pentru a putea citi fisierul
            FileInputStream fileInputStream = new FileInputStream(myObj);

            //Utilizam obiectul FileOutputStream pentru a putea crea un nou fisier cryptat
            FileOutputStream fileOutputStream = new FileOutputStream("Tests/"+myObj.getName()+"_Encrypted.txt");

            //Se crypteaza fisierul 'fileInputStream' cu cheia 'key' si se salveaza in 'fileOutputStream'
            encrypt(key,fileInputStream,fileOutputStream);
        }catch (Throwable ex){
            ex.printStackTrace();
        }

        myObj.delete();

    }


}
