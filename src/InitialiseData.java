public class InitialiseData {

    //Variabilele necesare pentru stocarea datelor ale intrebarilor testului grila
    String question, op1,op2,op3,op4,answer,clue;

    @Override
    public String toString() {
        return "InitialiseData{" +
                "question='" + question + '\'' +
                ", op1='" + op1 + '\'' +
                ", op2='" + op2 + '\'' +
                ", op3='" + op3 + '\'' +
                ", op4='" + op4 + '\'' +
                ", answer='" + answer + '\'' +
                ", clue='" + clue + '\'' +
                '}';
    }

    //Constructorul clasei InitialiseData care initializeaza toate campurile clasei
    InitialiseData(String question,String op1,String op2, String op3, String op4, String answer, String clue){
        this.question=question;
        this.op1=op1;
        this.op2=op2;
        this.op3=op3;
        this.op4=op4;
        this.answer=answer;
        this.clue=clue;
    }
}
