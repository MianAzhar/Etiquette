package pk.EA.Scenario.app.Etiquette.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pk.EA.Scenario.app.Etiquette.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TravelQuestionFragment extends android.support.v4.app.Fragment implements View.OnClickListener {


    public TravelQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_travel_question, container, false);
    }

    @Override
    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        TextView q1 = (TextView)getActivity().findViewById(R.id.option1);
        TextView q2 = (TextView)getActivity().findViewById(R.id.option2);
        TextView q3 = (TextView)getActivity().findViewById(R.id.option3);
        TextView q4 = (TextView)getActivity().findViewById(R.id.option4);

        q1.setOnClickListener(this);
        q2.setOnClickListener(this);
        q3.setOnClickListener(this);
        q4.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        if(view.getId() == R.id.option1) {
            CorrectAnswerFragment newFrag = new CorrectAnswerFragment();

            android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
            trans.addToBackStack(null);
            trans.replace(R.id.fragment_container, newFrag, "AnswerFragment").commit();
        }
        else if(view.getId() == R.id.option2)
        {
            WrongAnswerFragment newFrag = new WrongAnswerFragment();

            android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
            trans.addToBackStack(null);
            trans.replace(R.id.fragment_container, newFrag, "AnswerFragment").commit();
        }
        else if(view.getId() == R.id.option3)
        {
            AverageAnswerFragment newFrag = new AverageAnswerFragment();

            android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
            trans.addToBackStack(null);
            trans.replace(R.id.fragment_container, newFrag, "AnswerFragment").commit();
        }

    }


}
