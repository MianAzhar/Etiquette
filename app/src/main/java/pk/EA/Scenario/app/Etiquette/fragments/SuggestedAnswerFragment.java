package pk.EA.Scenario.app.Etiquette.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pk.EA.Scenario.app.Etiquette.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuggestedAnswerFragment extends android.support.v4.app.Fragment {


    public SuggestedAnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_suggested_answer, container, false);
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        TextView answer = (TextView)getActivity().findViewById(R.id.answer_text);

        answer.setMovementMethod(new ScrollingMovementMethod());
    }


}
