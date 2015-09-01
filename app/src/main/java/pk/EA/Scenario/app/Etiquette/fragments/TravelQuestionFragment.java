package pk.EA.Scenario.app.Etiquette.fragments;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import pk.EA.Scenario.app.Etiquette.R;
import pk.EA.Scenario.app.Etiquette.utils.Etiquette;


/**
 * A simple {@link Fragment} subclass.
 */
public class TravelQuestionFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    Etiquette etiquette;

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

        Bundle data = getArguments();

        etiquette = (Etiquette)data.getSerializable("data");

        ImageView image = (ImageView)getActivity().findViewById(R.id.question_image);

        Picasso.with(getActivity()).load(etiquette.getUrl()).into(image);

        ImageView menu = (ImageView)getActivity().findViewById(R.id.drawMenu);
        menu.setOnClickListener(this);

        ImageView star1 = (ImageView)getActivity().findViewById(R.id.star1);
        ImageView star2 = (ImageView)getActivity().findViewById(R.id.star2);
        ImageView star3 = (ImageView)getActivity().findViewById(R.id.star3);
        ImageView star4 = (ImageView)getActivity().findViewById(R.id.star4);
        ImageView star5 = (ImageView)getActivity().findViewById(R.id.star5);
        
        int rate = Integer.parseInt(etiquette.getMeter());

        switch (rate)
        {
            case 1:
                star1.setImageResource(R.drawable.starfill);
                star2.setImageResource(R.drawable.star);
                star3.setImageResource(R.drawable.star);
                star4.setImageResource(R.drawable.star);
                star5.setImageResource(R.drawable.star);
                break;
            case 2:
                star1.setImageResource(R.drawable.starfill);
                star2.setImageResource(R.drawable.starfill);
                star3.setImageResource(R.drawable.star);
                star4.setImageResource(R.drawable.star);
                star5.setImageResource(R.drawable.star);
                break;
            case 3:
                star1.setImageResource(R.drawable.starfill);
                star2.setImageResource(R.drawable.starfill);
                star3.setImageResource(R.drawable.starfill);
                star4.setImageResource(R.drawable.star);
                star5.setImageResource(R.drawable.star);
                break;
            case 4:
                star1.setImageResource(R.drawable.starfill);
                star2.setImageResource(R.drawable.starfill);
                star3.setImageResource(R.drawable.starfill);
                star4.setImageResource(R.drawable.starfill);
                star5.setImageResource(R.drawable.star);
                break;
            case 5:
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
        
        TextView q1 = (TextView)getActivity().findViewById(R.id.option1);
        TextView q2 = (TextView)getActivity().findViewById(R.id.option2);
        TextView q3 = (TextView)getActivity().findViewById(R.id.option3);
        TextView q4 = (TextView)getActivity().findViewById(R.id.option4);

        TextView quest = (TextView)getActivity().findViewById(R.id.question_text);

        quest.setText(etiquette.getMinor_description());

        q1.setText(etiquette.getOpt1_text());
        q2.setText(etiquette.getOpt2_text());
        q3.setText(etiquette.getOpt3_text());
        q4.setText(etiquette.getOpt4_text());


        q1.setOnClickListener(this);
        q2.setOnClickListener(this);
        q3.setOnClickListener(this);
        q4.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        if(view.getId() == R.id.option1) {

            new WebAPI().execute("http://etiquetteapp.azurewebsites.net/update_result_etiquette", "1");
            /*
            CorrectAnswerFragment newFrag = new CorrectAnswerFragment();

            android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
            trans.addToBackStack(null);
            trans.replace(R.id.fragment_container, newFrag, "AnswerFragment").commit();
            */
        }
        else if(view.getId() == R.id.option2)
        {
            new WebAPI().execute("http://etiquetteapp.azurewebsites.net/update_result_etiquette", "2");

        }
        else if(view.getId() == R.id.option3)
        {
            new WebAPI().execute("http://etiquetteapp.azurewebsites.net/update_result_etiquette", "3");

        }
        else if(view.getId() == R.id.option4)
        {
            new WebAPI().execute("http://etiquetteapp.azurewebsites.net/update_result_etiquette", "4");

        }
        else if(view.getId() == R.id.drawMenu)
        {
            DrawerLayout d = (DrawerLayout)getActivity().findViewById(R.id.drawer);

            NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.navigation_view);
            d.openDrawer(navigationView);

        }

    }

    class WebAPI extends AsyncTask<String, Void, String> {

        private Exception exception;
        String optNo;

        ProgressDialog progressDialog = null;

        protected void onPreExecute() {
            if(progressDialog == null)
                progressDialog = ProgressDialog.show(getActivity(), getString(R.string.loading_text), getString(R.string.fetching_data_text), true);
        }

        protected String doInBackground(String... params) {

            String url = params[0];
            optNo = params[1];

            String result = "";


            HttpClient httpclient = new DefaultHttpClient();

            HttpPost request = new HttpPost(url);

            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair("language", "english"));
            parameters.add(new BasicNameValuePair("title", etiquette.getTitle()));
            parameters.add(new BasicNameValuePair("optionNo", optNo));
            try{
                request.setEntity(new UrlEncodedFormEntity(parameters));
            }
            catch (Exception ex){}

            HttpResponse response;

            try
            {
                response = httpclient.execute(request);

                result = EntityUtils.toString(response.getEntity());

            }
            catch(SocketTimeoutException ex)
            {
                //Toast.makeText(getActivity(), "Timeout Exception", Toast.LENGTH_LONG).show();
            }
            catch (ClientProtocolException e)
            {
                //Toast.makeText(getActivity(), "Client Protocol Ex", Toast.LENGTH_LONG).show();
            }
            catch (IOException e)
            {
                //Toast.makeText(getActivity(), "IO Exception", Toast.LENGTH_LONG).show();
            }
            catch(Exception ex)
            {
                //Toast.makeText(getActivity(), "Some Exception", Toast.LENGTH_LONG).show();
            }

            httpclient.getConnectionManager().shutdown();
            return result;
        }

        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            progressDialog = null;

            String message = "", status = "";
            JSONObject object = null;
            Bundle bundle = new Bundle();

            if(result.equals(""))
            {
                //Toast.makeText(getActivity(), getString(R.string.check_internet), Toast.LENGTH_LONG).show();
                return;
            }

            try {


                JSONObject obj = new JSONObject(result);

                status = obj.getString("status");

                if(!status.equals("success"))
                {
                    Toast.makeText(getActivity(), "Something wrong", Toast.LENGTH_SHORT).show();
                    return;
                }

                message = obj.getString("message");

                JSONObject data = obj.getJSONObject("data");

                etiquette.setOpt1_count(data.getInt("option_count_1"));
                etiquette.setOpt2_count(data.getInt("option_count_2"));
                etiquette.setOpt3_count(data.getInt("option_count_3"));
                etiquette.setOpt4_count(data.getInt("option_count_4"));

                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("data", etiquette);

                if(message.equals(optNo + " option is best" ))
                {
                    CorrectAnswerFragment newFrag = new CorrectAnswerFragment();

                    newFrag.setArguments(bundle1);

                    android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                    trans.addToBackStack(null);
                    trans.replace(R.id.fragment_container, newFrag, "AnswerFragment").commit();
                }
                else
                {
                    WrongAnswerFragment newFrag = new WrongAnswerFragment();

                    newFrag.setArguments(bundle1);

                    android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                    trans.addToBackStack(null);
                    trans.replace(R.id.fragment_container, newFrag, "AnswerFragment").commit();
                }


            }
            catch (Exception ex)
            {
                Toast.makeText(getActivity(), "Something wrong", Toast.LENGTH_SHORT).show();
            }


        }
    }


}
