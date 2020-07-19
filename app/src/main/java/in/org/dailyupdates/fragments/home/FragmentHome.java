package in.org.dailyupdates.fragments.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import in.org.dailyupdates.R;
import in.org.dailyupdates.activity.fragments.BaseFragment;
import in.org.dailyupdates.fragments.contact.FragmentContact;
import in.org.dailyupdates.fragments.about.FragmentAbout;
import in.org.dailyupdates.fragments.question.FragmentQuestion;
import in.org.dailyupdates.databinding.FragmentHomeBinding;

public class FragmentHome extends BaseFragment implements View.OnClickListener {
    private FragmentHomeBinding binding;
    private Fragment fragment;
    private String title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= FragmentHomeBinding.inflate(inflater,container,false);
        View view=binding.getRoot();

        //ads
        MobileAds.initialize(getActivity(),getActivity().getString(R.string.adUnitHomePage));

        AdRequest adRequest=new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);

        binding.playQuiz.setOnClickListener(this);
        binding.About.setOnClickListener(this);
        binding.contact.setOnClickListener(this);
        binding.share.setOnClickListener(this);
        return view;


    }

    private void loadFragment() {

        getActivity().setTitle(title);

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
                title="Play Quiz";
                fragment=new FragmentQuestion();
                loadFragment();
                break;
            case R.id.About:
                title="About";
                fragment=new FragmentAbout();
                loadFragment();
                break;
            case R.id.contact:
                title="Contact Us";
                fragment=new FragmentContact();
                loadFragment();
                break;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                String url="http://play.google.com/store/apps/details?id="+getActivity().getPackageName();
                sendIntent.putExtra(Intent.EXTRA_TEXT, url);
                sendIntent.setType("text/html");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                break;

        }

    }
}
