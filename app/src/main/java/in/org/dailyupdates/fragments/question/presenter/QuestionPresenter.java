package in.org.dailyupdates.fragments.question.presenter;

import java.util.List;

public interface QuestionPresenter {
    void presentQuestion();
    void HandleResponse(String ansEntered);
    void QuestionGenerated(String question, List<String> options);
    void onCorrect();
    void onWrong();
}
