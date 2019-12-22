package id.ac.polinema.dtsfit.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import id.ac.polinema.dtsfit.Application;
import id.ac.polinema.dtsfit.Profile;
import id.ac.polinema.dtsfit.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private TextInputEditText nameText;
    private RadioGroup genderGroup;
    private AppCompatRadioButton maleRadio;
    private AppCompatRadioButton femaleRadio;
    private TextInputEditText heightText;
    private TextInputEditText weightText;
    private TextInputEditText ageText;
    private AppCompatSpinner activitiesSpinner;
    private AppCompatButton saveButton;

    private Profile profile;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfileFragment.
     */
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        profile = Application.provideProfile();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initComponents(view);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initComponents(final View view) {
        nameText = view.findViewById(R.id.input_name);
        genderGroup = view.findViewById(R.id.group_gender);
        maleRadio = view.findViewById(R.id.radio_male);
        femaleRadio = view.findViewById(R.id.radio_female);
        heightText = view.findViewById(R.id.input_height);
        weightText = view.findViewById(R.id.input_weight);
        ageText = view.findViewById(R.id.input_age);
        activitiesSpinner = view.findViewById(R.id.spinner_activities);
        saveButton = view.findViewById(R.id.button_save);

        nameText.setText(profile.getName());
        if (profile.getGender() != null) {
            RadioButton genderRadio = ("Male".equals(profile.getGender())) ? maleRadio : femaleRadio;
            genderRadio.setChecked(true);
        }
        heightText.setText(String.valueOf(profile.getHeight()));
        weightText.setText(String.valueOf(profile.getWeight()));
        ageText.setText(String.valueOf(profile.getAge()));
        activitiesSpinner.setSelection(profile.getActivity());
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                Log.i("ProfileFragment", String.valueOf(genderGroup.getCheckedRadioButtonId()));
                RadioButton selectedRadioButton = view.findViewById(genderGroup.getCheckedRadioButtonId());
                String gender = selectedRadioButton.getText().toString();
                int height = Integer.parseInt(heightText.getText().toString());
                int weight = Integer.parseInt(weightText.getText().toString());
                int age = Integer.parseInt(ageText.getText().toString());
                int activity = activitiesSpinner.getSelectedItemPosition();

                float bmr = calculateBmr(gender, height, weight, age, activity);
                profile.setName(name);
                profile.setGender(gender);
                profile.setHeight(height);
                profile.setWeight(weight);
                profile.setAge(age);
                profile.setActivity(activity);
                profile.setBmr(bmr);

                Snackbar.make(v, "Save Profile Successfull", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private float calculateBmr(String gender, float height, float weight, int age, int activity) {
        float result = (gender.equals("Male"))
                ? 66 + (13.7f + weight) + (5f * height) - (6.8f * age)
                : 655 + (9.6f * weight) + (1.8f * height) - (4.7f * age);
        if (activity == 0) {
            result *= 1.2f;
        } else if (activity == 1) {
            result *= 1.375f;
        } else if (activity == 2) {
            result *= 1.55f;
        } else if (activity == 3) {
            result *= 1.755f;
        } else {
            result *= 1.9f;
        }

        return result;
    }
}
