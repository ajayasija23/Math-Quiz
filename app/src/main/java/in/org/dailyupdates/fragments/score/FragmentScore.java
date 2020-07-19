package in.org.dailyupdates.fragments.score;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import in.org.dailyupdates.R;
import in.org.dailyupdates.activity.fragments.BaseFragment;
import in.org.dailyupdates.databinding.FragmentQuestionBinding;
import in.org.dailyupdates.databinding.FragmentScoreBinding;
import in.org.dailyupdates.fragments.home.FragmentHome;
import in.org.dailyupdates.utils.Constant;

import static android.content.ContentValues.TAG;

public class FragmentScore extends BaseFragment implements RewardedVideoAdListener, View.OnClickListener {
    private FragmentScoreBinding binding;
    private RewardedVideoAd mRewardedVideoAd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= FragmentScoreBinding.inflate(inflater,container,false);
        View view=binding.getRoot();
        MobileAds.initialize(getActivity(), "ca-app-pub-8127434234485566/4036437103");
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getActivity());
        mRewardedVideoAd.loadAd("ca-app-pub-8127434234485566/4036437103",
                new AdRequest.Builder().build());
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        MobileAds.initialize(getActivity(),getActivity().getString(R.string.adUnitHomePage));

        AdRequest adRequest=new AdRequest.Builder().build();
        binding.adViewBottom.loadAd(adRequest);

        binding.textviewTotalScore.setText(Constant.TOTAL_SCORE);
        binding.btnHome.setOnClickListener(this);
        binding.btnFeedback.setOnClickListener(this);
        return view;
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Log.i("loaded", "Rewarded: onRewardedVideoAdLoaded");
        try {
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Log.i("unable to load", "Rewarded: onRewardedVideoAdFailedToLoad: " + i);

    }

    @Override
    public void onRewardedVideoCompleted() {

    }

    @Override
    public void onClick(View v) {
        Button button=(Button)v;
        switch (button.getId())
        {
            case R.id.btnHome:
                getActivity().setTitle("Score");

                FragmentTransaction fragmentTransaction= getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_frame,new FragmentHome());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commitAllowingStateLoss();
                break;
            case R.id.btnFeedback:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getActivity().getPackageName())));
                } catch (android.content.ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
                }
                break;
        }

    }
}
