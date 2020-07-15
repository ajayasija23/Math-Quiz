package in.org.dailyupdates.fragments.question;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import in.org.dailyupdates.activity.fragments.BaseFragment;
import in.org.dailyupdates.databinding.FragmentQuestionBinding;
import in.org.dailyupdates.fragments.question.view.QuestionView;

public class FragmentQuestion extends BaseFragment implements QuestionView {
    private FragmentQuestionBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding= FragmentQuestionBinding.inflate(inflater,container,false);
        View view=binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCorrect() {

    }

    @Override
    public void onWrong() {

    }
}
