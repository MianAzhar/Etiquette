package pk.EA.Scenario.app.Etiquette.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import pk.EA.Scenario.app.Etiquette.R;
import pk.EA.Scenario.app.Etiquette.utils.Etiquette;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuggestedAnswerFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    Etiquette etiquette;

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

        Bundle data = getArguments();

        etiquette = (Etiquette)data.getSerializable("data");

        ImageView answerImage = (ImageView)getActivity().findViewById(R.id.suggestedAnswer_image);

        Picasso.with(getActivity()).load(etiquette.getUrl()).into(answerImage);

        ImageView menu = (ImageView)getActivity().findViewById(R.id.drawMenu);
        menu.setOnClickListener(this);

        TextView answer = (TextView)getActivity().findViewById(R.id.answer_text);

        answer.setMovementMethod(new ScrollingMovementMethod());
        answer.setText(etiquette.getDescription());
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.drawMenu)
        {

            DrawerLayout d = (DrawerLayout)getActivity().findViewById(R.id.drawer);

            NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.navigation_view);
            d.openDrawer(navigationView);

        }
    }

}
