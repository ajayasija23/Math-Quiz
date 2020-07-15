package in.org.dailyupdates.activity.splash;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import in.org.dailyupdates.R;
import in.org.dailyupdates.BaseActivity;
import in.org.dailyupdates.activity.home.HomeActivity;
import in.org.dailyupdates.databinding.ActivitySplashBinding;

public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding binding;
    private Animation topAnim,middleAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySplashBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        middleAnim= AnimationUtils.loadAnimation(this,R.anim.middle_anim);

        topAnim.setDuration(2000);
        middleAnim.setDuration(2000);
        binding.imageViewSplash.setAnimation(middleAnim);
        binding.textTitle.setAnimation(topAnim);
               navigateToHomeScreen();
    }

    private void navigateToHomeScreen() {
        {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));

                    finish();
                }
            }, 5000);

        }
    }
}
