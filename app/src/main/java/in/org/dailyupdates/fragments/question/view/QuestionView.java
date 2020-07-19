package in.org.dailyupdates.fragments.question.view;

import java.util.List;

public interface QuestionView {
     void onCorrect();
     void onWrong();
     void questionGenerated(String question, List<String> options);
}
