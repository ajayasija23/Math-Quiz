package in.org.dailyupdates.fragments.contact;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import in.org.dailyupdates.R;
import in.org.dailyupdates.activity.fragments.BaseFragment;
import in.org.dailyupdates.databinding.FragmentContactBinding;

import static android.content.ContentValues.TAG;

public class FragmentContact extends BaseFragment implements View.OnClickListener {

    private FragmentContactBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= FragmentContactBinding.inflate(inflater,container,false);
        View view=binding.getRoot();
        binding.buttonMobNo.setOnClickListener(this);
        binding.buttonEmail.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Button button=(Button)v;
        switch (v.getId())
        {
            case R.id.button_email:
                Intent intent = new Intent (Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{button.getText().toString()});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                intent.setPackage("com.google.android.gm");
                if (intent.resolveActivity(getActivity().getPackageManager())!=null)
                    startActivity(intent);
                else
                    Toast.makeText(getActivity(),"Gmail App is not installed",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_mob_no:
                Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + button.getText()));
                startActivity(intent1);

                break;
        }

    }
}
