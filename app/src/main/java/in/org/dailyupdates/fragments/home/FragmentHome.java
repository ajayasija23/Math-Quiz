package in.org.dailyupdates.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import in.org.dailyupdates.R;
import in.org.dailyupdates.activity.fragments.BaseFragment;
import in.org.dailyupdates.fragments.contact.FragmentContact;
import in.org.dailyupdates.fragments.about.FragmentAbout;
import in.org.dailyupdates.activity.fragments.question.FragmentQuestion;
import in.org.dailyupdates.databinding.FragmentHomeBinding;

public class FragmentHome extends BaseFragment implements View.OnClickListener {
    private FragmentHomeBinding binding;
    private Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= FragmentHomeBinding.inflate(inflater,container,false);
        View view=binding.getRoot();
        binding.playQuiz.setOnClickListener(this);
        binding.About.setOnClickListener(this);
        binding.contact.setOnClickListener(this);
        return view;


    }

    private void loadFragment() {

        FragmentTransaction fragmentTransaction= getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.play_quiz:
                fragment=new FragmentQuestion();
                break;
            case R.id.About:
                fragment=new FragmentAbout();
                break;
            case R.id.contact:
                fragment=new FragmentContact();
                break;

        }
        loadFragment();

    }
}
