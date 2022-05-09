package com.example.bookup.ui.settings;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.bookup.R;
import com.example.bookup.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class SettingsFragment extends Fragment {

    private Button signOut;
    private TextView userEmail;
    private SettingsViewModel viewModel;
    private int item = 0;
    private TextView languageField;
    private ImageView imageLanguage;
    private ImageView userPicture;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        languageField = root.findViewById(R.id.languageField);
        imageLanguage = root.findViewById(R.id.image_for_language);
        loadLocal();


        LinearLayout linearLayoutLang = root.findViewById(R.id.language);
        linearLayoutLang.setOnClickListener(v -> showChangeLanguageDialog());

        signOut = root.findViewById(R.id.sign_out_button);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.usersRepository.signOut();
                Intent intent = new Intent(getContext(), SignInActivity.class);
                startActivity(intent);
            }
        });

        userEmail = root.findViewById(R.id.Email_to_display);
        String yey = getResources().getString(R.string.Yupee);
        userEmail.setText(yey + "\t" + viewModel.getCurrentUser().getValue().getEmail());
        userPicture = root.findViewById(R.id.userPicture);
        if (viewModel.getCurrentUser().getValue().getPhotoUrl()!= null)
        {
            Glide.with(this).load(viewModel.getCurrentUser().getValue().getPhotoUrl()).into(userPicture);
        }
        else
        {
            Glide.with(this).load(R.drawable.ic_user).into(userPicture);
        }

        return root;
    }

    public void showChangeLanguageDialog() {
        String[] languages = {"English", "Română", "Русский "};

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        mBuilder.setTitle("Choose language");
        mBuilder.setSingleChoiceItems(languages, item, (dialog, which) -> {
            switch (which) {
                case 0:
                    setLocal("en");
                    item = 0;
                    languageField.setText(languages[item]);
                    imageLanguage.setImageResource(R.drawable.usa_flag);
                    reset();
                    break;
                case 1:
                    setLocal("ro");
                    item = 1;
                    languageField.setText(languages[item]);
                    imageLanguage.setImageResource(R.drawable.romania_flag);
                    reset();
                    break;
                case 2:
                    setLocal("kv");
                    item = 2;
                    languageField.setText(languages[item]);
                    imageLanguage.setImageResource(R.drawable.russia_flag);
                    reset();
                    break;
            }

            dialog.dismiss();
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocal(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration conf = new Configuration();
        conf.locale = locale;
        getContext().getResources().updateConfiguration(conf, getContext().getResources().getDisplayMetrics());

        SharedPreferences prefs = getContext().getSharedPreferences("Settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocal() {
        SharedPreferences prefs = getContext().getSharedPreferences("Settings", MODE_PRIVATE);
        String lang = prefs.getString("My_Lang", "");
        setLocal(lang);
        String[] languages = {"English", "Română", "Русский "};

        switch (lang) {
            case "en":
                item = 0;
                languageField.setText(languages[item]);
                imageLanguage.setImageResource(R.drawable.usa_flag);
                break;
            case "ro":
                item = 1;
                languageField.setText(languages[item]);
                imageLanguage.setImageResource(R.drawable.romania_flag);
                break;
            case "kv":
                item = 2;
                languageField.setText(languages[item]);
                imageLanguage.setImageResource(R.drawable.russia_flag);
                break;
            default:
                item = 0;
                languageField.setText("");
                break;
        }
    }

    public void reset() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            ft.setReorderingAllowed(false);
        }
        ft.detach(this).attach(this).commit();
    }
}