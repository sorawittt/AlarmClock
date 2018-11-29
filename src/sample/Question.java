package sample;

import java.util.Random;

public class Question {
    private int ans;
    private int a, b, c;
    private String opera1, opera2;

    public Question() {
        setQuestion();
    }

    public void setQuestion() {
        Random randomOpe = new Random();
        Random n = new Random();
        a = n.nextInt(10) + 1;
        b = n.nextInt(10) + 1;
        c = n.nextInt(10) + 1;

        switch (randomOpe.nextInt(9)) {
            case 0: ans = a + b + c;
                opera1 = "+";
                opera2 = "+";
                break;
            case 1: ans = a + b - c;
                opera1 = "+";
                opera2 = "-";
                break;
            case 2: ans = a + b * c;
                opera1 = "+";
                opera2 = "x";
                break;
            case 3: ans = a - b + c;
                opera1 = "-";
                opera2 = "+";
                break;
            case 4: ans = a - b - c;
                opera1 = "-";
                opera2 = "-";
                break;
            case 5: ans = a - b * c;
                opera1 = "-";
                opera2 = "x";
                break;
            case 6: ans = a * b + c;
                opera1 = "x";
                opera2 = "+";
                break;
            case 7: ans = a * b - c;
                opera1 = "x";
                opera2 = "-";
                break;
            case 8: ans = a * b * c;
                opera1 = "x";
                opera2 = "x";
                break;
        }
    }

    public int getAns() {
        return ans;
    }

    public String getQuestion() {
        return a + " " + opera1 + " " + b + " " + opera2 + " " + c + " = " + " ?";
    }

}
