package pk.EA.Scenario.app.Etiquette.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import pk.EA.Scenario.app.Etiquette.R;
import pk.EA.Scenario.app.Etiquette.adapters.CategoriesAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends android.support.v4.app.Fragment implements View.OnClickListener {


    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);

        ImageView menu = (ImageView)getActivity().findViewById(R.id.drawMenu);
        menu.setOnClickListener(this);

        ImageButton latest = (ImageButton) getActivity().findViewById(R.id.latestButton_categories);
        ImageButton popular = (ImageButton) getActivity().findViewById(R.id.popularButton_categories);

        latest.setOnClickListener(this);
        popular.setOnClickListener(this);

        GridView gridView = (GridView)getActivity().findViewById(R.id.gridView);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Category 1");
        arrayList.add("Category 2");
        arrayList.add("Category 3");
        arrayList.add("Category 4");
        arrayList.add("Category 5");
        //arrayList.add("Category 6");

        CategoriesAdapter adapter = new CategoriesAdapter(getActivity(), arrayList);

        gridView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.popularButton_categories)
        {
            PopularFragment newFrag = new PopularFragment();

            android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
            getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            trans.replace(R.id.fragment_container, newFrag, "PopularFragment").commit();
        }
        else if(view.getId() == R.id.latestButton_categories)
        {
            LatestFragment newFrag = new LatestFragment();

            android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
            getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            trans.replace(R.id.fragment_container, newFrag, "LatestFragment").commit();
        }
        else if(view.getId() == R.id.drawMenu)
        {

            DrawerLayout d = (DrawerLayout)getActivity().findViewById(R.id.drawer);

            NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.navigation_view);
            d.openDrawer(navigationView);

        }
    }


}
