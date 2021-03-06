package pk.EA.Scenario.app.Etiquette.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import pk.EA.Scenario.app.Etiquette.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AverageAnswerFragment extends android.support.v4.app.Fragment implements View.OnClickListener {


    public AverageAnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_average_answer, container, false);
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        ImageView menu = (ImageView)getActivity().findViewById(R.id.drawMenu);
        menu.setOnClickListener(this);

        TextView gotoAnswer = (TextView)getActivity().findViewById(R.id.goto_answer);

        gotoAnswer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.goto_answer){
            SuggestedAnswerFragment newFrag = new SuggestedAnswerFragment();

            android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
            trans.addToBackStack(null);
            trans.replace(R.id.fragment_container, newFrag, "SuggestedAnswerFragment").commit();
        }
        else if(view.getId() == R.id.drawMenu)
        {

            DrawerLayout d = (DrawerLayout)getActivity().findViewById(R.id.drawer);

            NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.navigation_view);
            d.openDrawer(navigationView);

        }
    }

}
