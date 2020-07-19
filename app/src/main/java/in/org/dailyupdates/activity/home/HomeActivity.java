package in.org.dailyupdates.activity.home;

import androidx.fragment.app.FragmentTransaction;
import in.org.dailyupdates.R;
import in.org.dailyupdates.activity.BaseActivity;
import in.org.dailyupdates.fragments.home.FragmentHome;
import in.org.dailyupdates.databinding.ActivityHomeBinding;

import android.os.Bundle;
import android.view.View;

public class HomeActivity extends BaseActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityHomeBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        setSupportActionBar(binding.layoutincluded.toolbar);
        loadFragmentHome();

    }

    private void loadFragmentHome() {
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_frame,new FragmentHome());
            fragmentTransaction.commitAllowingStateLoss();
    }


}
