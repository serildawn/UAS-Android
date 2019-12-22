package id.ac.polinema.dtsfit.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

import id.ac.polinema.dtsfit.Application;
import id.ac.polinema.dtsfit.Profile;
import id.ac.polinema.dtsfit.R;
import id.ac.polinema.dtsfit.adapters.CaloriesAdapter;
import id.ac.polinema.dtsfit.models.Calory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CaloryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CaloryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaloryFragment extends Fragment implements CaloriesAdapter.OnCaloryClickedListener {

    private TextView caloryText;
    private TextView bmrText;
    private RecyclerView caloriesView;
    private FloatingActionButton addButton;

    private CaloriesAdapter caloriesAdapter;

    private Profile profile;

    private OnFragmentInteractionListener mListener;

    public CaloryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CaloryFragment.
     */
    public static CaloryFragment newInstance() {
        CaloryFragment fragment = new CaloryFragment();
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
        View view = inflater.inflate(R.layout.fragment_calory, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        Context context = getActivity();

        caloryText = view.findViewById(R.id.tv_calory);
        bmrText = view.findViewById(R.id.tv_bmr);
        caloriesView = view.findViewById(R.id.rv_calories);
        addButton = view.findViewById(R.id.fab_add);

        bmrText.setText(String.format(Locale.ENGLISH, "Bmr %.2f cal", profile.getBmr()));

        // setup recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        caloriesAdapter = new CaloriesAdapter(context, this);
        caloriesView.setLayoutManager(layoutManager);
        caloriesView.setAdapter(caloriesAdapter);

        if (mListener != null) {
            mListener.onCaloryFragmentCreated(getView(), caloriesAdapter, caloryText);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onAddCaloryButtonClicked();
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_calory, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCaloryClicked(Calory calory) {
        if (mListener != null) {
            mListener.onCaloryClicked(calory);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onCaloryFragmentCreated(final View view, final CaloriesAdapter adapter, final TextView caloryText);
        void onAddCaloryButtonClicked();
        void onCaloryClicked(Calory calory);
    }
}
