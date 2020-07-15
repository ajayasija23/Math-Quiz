package in.org.dailyupdates.fragments.question.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FragmentQuestionModelImpl implements FragmentQuestionModel {
    private int type;
    private String question;
    private int answer;
    private List<String> options=new ArrayList<String>();
    @Override
    public void generateQuestion() {
        int num1=(int)(Math.random()*100);
        int num2=(int)(Math.random()*100);
        type= (int)(Math.random()*6);
        switch (type)
        {
            case 0:
                question=num1+" + "+num2+" = ?";
                answer= num1+num2;
                break;
            case 1:
                question=num1+" - "+num2+" = ?";
                answer= num1-num2;
                break;
            case 2:
                question=num1+" * "+num2+" = ?";
                answer= num1*num2;
                break;
            case 3:
                if(num2>num1)
                {
                    int temp=num1;
                    num1=num2;
                    num2=temp;
                }
                if(num2==0)
                {
                    num2=(int)(Math.random()*100);
                }
                question=num1+" / "+num2+" = ?";
                answer=num1-num2;
                break;
            case 4:
                question="H.C.F of "+num1+" , "+num2+"=?";
                answer=getHcf(num1,num2);
                break;
            case 5:
                question="L.C.M of "+num1+" , "+num2+"=?";
                answer=getLcm(num1,num2);
                break;
        }
        createOptions();


    }

    private void createOptions() {

        int option1=(int)(Math.random()*100);
        int option2=(int)(Math.random()*100);
        int option3=(int)(Math.random()*100);

        //adding options to option list
        options.add(option1+"");
        options.add(option2+"");
        options.add(option3+"");
        options.add(answer+"");

        Collections.shuffle(options);

    }

    private int getLcm(int num1, int num2) {
        if (num1 == 0 || num2 == 0) {
            return 0;
        }
        int absNumber1 = Math.abs(num1);
        int absNumber2 = Math.abs(num2);
        int absHigherNumber = Math.max(absNumber1, absNumber2);
        int absLowerNumber = Math.min(absNumber1, absNumber2);
        int lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;

    }

    private int getHcf(int num1, int num2) {
        int lcm=getLcm(num1,num2);

        return (num1*num2)/lcm;
    }

    @Override
    public void validateAnswer(String enteredAns)
    {
        if(Integer.parseInt(enteredAns)==answer)
        {

        }
        else
        {

        }

    }
}
