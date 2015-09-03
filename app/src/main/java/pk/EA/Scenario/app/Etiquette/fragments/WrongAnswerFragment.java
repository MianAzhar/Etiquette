package pk.EA.Scenario.app.Etiquette.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import pk.EA.Scenario.app.Etiquette.R;
import pk.EA.Scenario.app.Etiquette.activities.MainActivity;
import pk.EA.Scenario.app.Etiquette.utils.Etiquette;

/**
 * A simple {@link Fragment} subclass.
 */
public class WrongAnswerFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    Etiquette etiquette;

    public WrongAnswerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wrong_answer, container, false);
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        //Bundle data = getArguments();

        //etiquette = (Etiquette)data.getSerializable("data");

        etiquette = MainActivity.etiquetteObj;

        ProgressBar p1 = (ProgressBar)getActivity().findViewById(R.id.wrongProgress1);
        ProgressBar p2 = (ProgressBar)getActivity().findViewById(R.id.wrongProgress2);
        ProgressBar p3 = (ProgressBar)getActivity().findViewById(R.id.wrongProgress3);
        ProgressBar p4 = (ProgressBar)getActivity().findViewById(R.id.wrongProgress4);

        p1.setProgress(etiquette.getOpt1_count());
        p2.setProgress(etiquette.getOpt2_count());
        p3.setProgress(etiquette.getOpt3_count());
        p4.setProgress(etiquette.getOpt4_count());

        TextView t1 = (TextView)getActivity().findViewById(R.id.wrongCount1);
        TextView t2 = (TextView)getActivity().findViewById(R.id.wrongCount2);
        TextView t3 = (TextView)getActivity().findViewById(R.id.wrongCount3);
        TextView t4 = (TextView)getActivity().findViewById(R.id.wrongCount4);

        t1.setText(Integer.toString(etiquette.getOpt1_count()) + "%");
        t2.setText(Integer.toString(etiquette.getOpt2_count()) + "%");
        t3.setText(Integer.toString(etiquette.getOpt3_count()) + "%");
        t4.setText(Integer.toString(etiquette.getOpt4_count()) + "%");


        ImageView menu = (ImageView)getActivity().findViewById(R.id.drawMenu);
        menu.setOnClickListener(this);

        TextView gotoAnswer = (TextView)getActivity().findViewById(R.id.goto_answer);

        gotoAnswer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.goto_answer){
            SuggestedAnswerFragment newFrag = new SuggestedAnswerFragment();

            Bundle bundle = new Bundle();
            bundle.putSerializable("data", etiquette);

            //newFrag.setArguments(bundle);

            android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
            //trans.addToBackStack(null);
            getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            trans.replace(R.id.fragment_container, newFrag, "SuggestedAnswerFragmentWrong").commit();
        }
        else if(view.getId() == R.id.drawMenu)
        {

            DrawerLayout d = (DrawerLayout)getActivity().findViewById(R.id.drawer);

            NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.navigation_view);
            d.openDrawer(navigationView);

        }
    }


}
