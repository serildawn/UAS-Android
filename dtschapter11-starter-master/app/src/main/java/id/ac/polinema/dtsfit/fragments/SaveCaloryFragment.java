package id.ac.polinema.dtsfit.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;

import id.ac.polinema.dtsfit.Constant;
import id.ac.polinema.dtsfit.R;
import id.ac.polinema.dtsfit.models.Calory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SaveCaloryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SaveCaloryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SaveCaloryFragment extends Fragment {

    private Calory calory;

    private TextInputEditText foodText;
    private TextInputEditText caloryText;

    private OnFragmentInteractionListener mListener;

    public SaveCaloryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SaveCaloryFragment.
     */
    public static SaveCaloryFragment newInstance(Calory calory) {
        SaveCaloryFragment fragment = new SaveCaloryFragment();
        Bundle bundle = new Bundle();
        // TODO: pass object calory dengan menggunakan bundle.putParcelable
        bundle.putParcelable(Constant.ARG_CALORY, calory);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_save_calory, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        foodText = view.findViewById(R.id.input_food);
        caloryText = view.findViewById(R.id.input_calory);
        Bundle bundle = getArguments();
        if (bundle != null) {
            calory = bundle.getParcelable(Constant.ARG_CALORY);
            // TODO: set informasi calory pada layar tampilan
            foodText.setText(calory.getFood());
            caloryText.setText(String.valueOf(calory.getCalory()));
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_save, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            if (mListener != null) {
                if (calory == null) {
                    calory = new Calory();
                }
                // TODO: Ambil nilai calory yang didapatkan dari tampilan


                mListener.onSaveMenuClicked(getView(), calory);
            }
        }
        return super.onOptionsItemSelected(item);
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
        void onSaveMenuClicked(View view, Calory calory);
    }
}