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
        if(!(etiquette.getDescription().equals("null") || etiquette.getDescription().equals("")))
            answer.setText(etiquette.getDescription());
        else
            answer.setText("No Description");

        ImageView star1 = (ImageView)getActivity().findViewById(R.id.star1);
        ImageView star2 = (ImageView)getActivity().findViewById(R.id.star2);
        ImageView star3 = (ImageView)getActivity().findViewById(R.id.star3);
        ImageView star4 = (ImageView)getActivity().findViewById(R.id.star4);
        ImageView star5 = (ImageView)getActivity().findViewById(R.id.star5);

        int rate = Integer.parseInt(etiquette.getMeter());

        switch (rate)
        {
            case -2:
                star1.setImageResource(R.drawable.starfill);
                star2.setImageResource(R.drawable.star);
                star3.setImageResource(R.drawable.star);
                star4.setImageResource(R.drawable.star);
                star5.setImageResource(R.drawable.star);
                break;
            case -1:
                star1.setImageResource(R.drawable.starfill);
                star2.setImageResource(R.drawable.starfill);
                star3.setImageResource(R.drawable.star);
                star4.setImageResource(R.drawable.star);
                star5.setImageResource(R.drawable.star);
                break;
            case 0:
                star1.setImageResource(R.drawable.starfill);
                star2.setImageResource(R.drawable.starfill);
                star3.setImageResource(R.drawable.starfill);
                star4.setImageResource(R.drawable.star);
                star5.setImageResource(R.drawable.star);
                break;
            case 1:
                star1.setImageResource(R.drawable.starfill);
                star2.setImageResource(R.drawable.starfill);
                star3.setImageResource(R.drawable.starfill);
                star4.setImageResource(R.drawable.starfill);
                star5.setImageResource(R.drawable.star);
                break;
            case 2:
                star1.setImageResource(R.drawable.starfill);
                star2.setImageResource(R.drawable.starfill);
                star3.setImageResource(R.drawable.starfill);
                star4.setImageResource(R.drawable.starfill);
                star5.setImageResource(R.drawable.starfill);
                break;
            default:
                star1.setImageResource(R.drawable.star);
                star2.setImageResource(R.drawable.star);
                star3.setImageResource(R.drawable.star);
                star4.setImageResource(R.drawable.star);
                star5.setImageResource(R.drawable.star);
                break;
        }

        TextView quest = (TextView)getActivity().findViewById(R.id.question_text);

        quest.setText(etiquette.getTitle());

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
