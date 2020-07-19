package in.org.dailyupdates.fragments.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import in.org.dailyupdates.activity.fragments.BaseFragment;
import in.org.dailyupdates.databinding.FragmentAboutBinding;
import in.org.dailyupdates.databinding.FragmentQuestionBinding;

public class FragmentAbout extends BaseFragment {

    private FragmentAboutBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= FragmentAboutBinding.inflate(inflater,container,false);
        View view=binding.getRoot();
        return view;
    }
}
