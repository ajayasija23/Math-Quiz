package in.org.dailyupdates.fragments.question;

import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import in.org.dailyupdates.R;
import in.org.dailyupdates.activity.fragments.BaseFragment;
import in.org.dailyupdates.databinding.FragmentQuestionBinding;
import in.org.dailyupdates.fragments.question.presenter.QuestionPresenter;
import in.org.dailyupdates.fragments.question.presenter.QuestionPresenterImpl;
import in.org.dailyupdates.fragments.question.view.QuestionView;
import in.org.dailyupdates.fragments.score.FragmentScore;
import in.org.dailyupdates.utils.Constant;

import static android.content.ContentValues.TAG;

public class FragmentQuestion extends BaseFragment implements QuestionView, View.OnClickListener {
    private FragmentQuestionBinding binding;
    private QuestionPresenter presenter;
    private Button button;
    private Animation anim;
    private int total=0,correct=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding= FragmentQuestionBinding.inflate(inflater,container,false);
        View view=binding.getRoot();
        anim=AnimationUtils.loadAnimation(getActivity(),R.anim.middle_anim);

        presenter=new QuestionPresenterImpl(this);
        presenter.presentQuestion();



        //ads
        MobileAds.initialize(getActivity(),getActivity().getString(R.string.adUnitQuestionPage));

        AdRequest adRequest=new AdRequest.Builder().build();
        binding.adViewQuestion.loadAd(adRequest);

        binding.btnFinish.setOnClickListener(this);

        binding.optionOne.setOnClickListener(this);
        binding.optionTwo.setOnClickListener(this);
        binding.optionThree.setOnClickListener(this);
        binding.optionFour.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCorrect() {
        total++;
        correct++;
        binding.tvScore.setText("Score: "+correct+"/"+total);
        button.setBackgroundResource(R.drawable.correct_ans);
        MediaPlayer mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.correct);
        mp.start();
        button.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },2000);
    }

    @Override
    public void onWrong() {
        total++;
        binding.tvScore.setText("Score: "+correct+"/"+total);
        button.setBackgroundResource(R.drawable.wrong_ans);
        button.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY,HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
        MediaPlayer mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.wrong);
        mp.start();
        button.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },2000);

    }

    @Override
    public void questionGenerated(String question, List<String> options) {
        anim.setDuration(2000);
        
        binding.textViewQuestion.setText(question);

        binding.optionOne.setText(options.get(0));
        binding.optionTwo.setText(options.get(1));
        binding.optionThree.setText(options.get(2));
        binding.optionFour.setText(options.get(3));
    }

    @Override
    public void onClick(View v)
    {


        button= (Button)v;
        if(button.getId()==R.id.btnFinish)
        {

            Constant.TOTAL_SCORE=correct+"/"+total;
            getActivity().setTitle("Score");

            FragmentTransaction fragmentTransaction= getParentFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_frame,new FragmentScore());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commitAllowingStateLoss();
        }
        else {

            presenter.HandleResponse(button.getText().toString());

            button.postDelayed(new Runnable() {
                @Override
                public void run() {
                    binding.optionOne.setBackgroundResource(R.drawable.button_bg);
                    binding.optionTwo.setBackgroundResource(R.drawable.button_bg);
                    binding.optionThree.setBackgroundResource(R.drawable.button_bg);
                    binding.optionFour.setBackgroundResource(R.drawable.button_bg);
                    presenter.presentQuestion();
                }
            }, 1000);

        }

    }

}
