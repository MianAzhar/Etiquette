package pk.EA.Scenario.app.Etiquette.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import pk.EA.Scenario.app.Etiquette.ListAdapter;
import pk.EA.Scenario.app.Etiquette.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LatestFragment extends android.support.v4.app.Fragment implements View.OnClickListener {


    public LatestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_latest, container, false);
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        ImageButton latest = (ImageButton)getActivity().findViewById(R.id.popularButton_latest);
        ImageButton categories = (ImageButton)getActivity().findViewById(R.id.categoryButton_latest);

        latest.setOnClickListener(this);
        categories.setOnClickListener(this);

        ArrayList<String> texts = new ArrayList<String>();

        texts.add("This is sample text");
        texts.add("This is sample text");
        texts.add("This is sample text");
        texts.add("This is sample text");
        texts.add("This is sample text");

        int[] res = {R.drawable.toiletpic, R.drawable.toiletpic, R.drawable.toiletpic, R.drawable.toiletpic, R.drawable.toiletpic};

        ListView list = (ListView) getActivity().findViewById(R.id.latestList);
        ListAdapter viewadapter = new ListAdapter(getActivity(), texts , res);
        list.setAdapter(viewadapter);
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.popularButton_latest)
        {
            PopularFragment newFrag = new PopularFragment();

            android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
            getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            trans.replace(R.id.fragment_container, newFrag, "PopularFragment").commit();
        }
        else if(view.getId() == R.id.categoryButton_latest)
        {
            CategoriesFragment newFrag = new CategoriesFragment();

            android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
            getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            trans.replace(R.id.fragment_container, newFrag, "CategoriesFragment").commit();
        }
    }


}