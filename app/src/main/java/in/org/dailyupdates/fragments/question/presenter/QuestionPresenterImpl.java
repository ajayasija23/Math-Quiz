package in.org.dailyupdates.fragments.question.presenter;

import java.util.List;

import in.org.dailyupdates.fragments.question.FragmentQuestion;
import in.org.dailyupdates.fragments.question.model.FragmentQuestionModel;
import in.org.dailyupdates.fragments.question.model.FragmentQuestionModelImpl;

public class QuestionPresenterImpl implements QuestionPresenter {

    private FragmentQuestion fragmentView;
    private FragmentQuestionModel fragmentQuestionModel;
    public QuestionPresenterImpl(FragmentQuestion fragmentQuestion) {
        this.fragmentView=fragmentQuestion; //initialise view
        this.fragmentQuestionModel=new FragmentQuestionModelImpl(this);

    }

    @Override
    public void presentQuestion()
    {
        fragmentQuestionModel.generateQuestion();
    }

    @Override
    public void HandleResponse(String ansEntered) {
        fragmentQuestionModel.validateAnswer(ansEntered);

    }

    @Override
    public void QuestionGenerated(String question, List<String> options) {
        fragmentView.questionGenerated(question, options);

    }

    @Override
    public void onCorrect() {
        fragmentView.onCorrect();
    }

    @Override
    public void onWrong() {
        fragmentView.onWrong();
    }
}
