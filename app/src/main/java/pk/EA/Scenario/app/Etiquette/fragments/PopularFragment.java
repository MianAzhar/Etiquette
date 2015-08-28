package pk.EA.Scenario.app.Etiquette.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import pk.EA.Scenario.app.Etiquette.adapters.ListAdapter;
import pk.EA.Scenario.app.Etiquette.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends android.support.v4.app.Fragment implements View.OnClickListener {


    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false);
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        ImageView menu = (ImageView)getActivity().findViewById(R.id.drawMenu);
        menu.setOnClickListener(this);

        ImageButton latest = (ImageButton)getActivity().findViewById(R.id.latestButton_popular);
        ImageButton categories = (ImageButton)getActivity().findViewById(R.id.categoryButton_popular);

        latest.setOnClickListener(this);
        categories.setOnClickListener(this);

        ArrayList<String> texts = new ArrayList<String>();

        texts.add("This is sample text");
        texts.add("This is sample text");
        texts.add("This is sample text");
        texts.add("This is sample text");
        texts.add("This is sample text");

        int[] res = {R.drawable.picture, R.drawable.picture, R.drawable.picture, R.drawable.picture, R.drawable.picture};

        ListView list = (ListView) getActivity().findViewById(R.id.popularList);
        ListAdapter viewadapter = new ListAdapter(getActivity(), texts , res);
        list.setAdapter(viewadapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TravelQuestionFragment newFrag = new TravelQuestionFragment();

                android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                trans.addToBackStack(null);
                trans.replace(R.id.fragment_container, newFrag, "QuestionFragment").commit();
            }
        });
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.latestButton_popular)
        {
            LatestFragment newFrag = new LatestFragment();

            android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
            getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            trans.replace(R.id.fragment_container, newFrag, "LatestFragment").commit();
        }
        else if(view.getId() == R.id.categoryButton_popular)
        {
            CategoriesFragment newFrag = new CategoriesFragment();

            android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
            getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            trans.replace(R.id.fragment_container, newFrag, "CategoriesFragment").commit();
        }
        else if(view.getId() == R.id.drawMenu)
        {
            DrawerLayout d = (DrawerLayout)getActivity().findViewById(R.id.drawer);

            NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.navigation_view);
            d.openDrawer(navigationView);
        }
    }


}
