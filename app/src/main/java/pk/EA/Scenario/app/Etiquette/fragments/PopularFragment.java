package pk.EA.Scenario.app.Etiquette.fragments;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
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

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import pk.EA.Scenario.app.Etiquette.R;
import pk.EA.Scenario.app.Etiquette.activities.MainActivity;
import pk.EA.Scenario.app.Etiquette.adapters.ListAdapter;
import pk.EA.Scenario.app.Etiquette.utils.Etiquette;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    ArrayList<Etiquette> objects;

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

        new WebAPI().execute("http://etiquetteapp.azurewebsites.net/getAllEtiquettes");

        ImageView menu = (ImageView)getActivity().findViewById(R.id.drawMenu);
        menu.setOnClickListener(this);

        ImageButton latest = (ImageButton)getActivity().findViewById(R.id.latestButton_popular);
        ImageButton categories = (ImageButton)getActivity().findViewById(R.id.categoryButton_popular);

        latest.setOnClickListener(this);
        categories.setOnClickListener(this);

        ListView list = (ListView) getActivity().findViewById(R.id.popularList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Etiquette obj = objects.get(position);

                MainActivity.etiquetteObj = obj;
                //Bundle question = new Bundle();

                //question.putSerializable("data", obj);

                if(obj.getOpt1_text().equals("null"))
                {
                    SuggestedAnswerFragment newFrag = new SuggestedAnswerFragment();

                    //newFrag.setArguments(question);

                    android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                    getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    //trans.addToBackStack("PopularFragment");
                    trans.replace(R.id.fragment_container, newFrag, "SuggestedAnswerFragment").commit();
                }
                else {
                    TravelQuestionFragment newFrag = new TravelQuestionFragment();

                    //newFrag.setArguments(question);

                    android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
                    getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    //trans.addToBackStack("PopularFragment");
                    trans.replace(R.id.fragment_container, newFrag, "QuestionFragment").commit();
                }
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

    class WebAPI extends AsyncTask<String, Void, String> {

        private Exception exception;

        ProgressDialog progressDialog = null;

        protected void onPreExecute() {
            if(progressDialog == null)
                progressDialog = ProgressDialog.show(getActivity(), getString(R.string.loading_text), getString(R.string.fetching_data_text), true);
        }

        protected String doInBackground(String... params) {

            String url = params[0];

            String result = "";


            HttpClient httpclient = new DefaultHttpClient();

            HttpPost request = new HttpPost(url);

            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair("language", "english"));
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

                bundle.putString("data", result);
            }
            catch (Exception ex){

            }

            ArrayList<String> texts = new ArrayList<String>();

            objects = new ArrayList<Etiquette>();


            try {


                JSONObject obj = new JSONObject(result);

                JSONArray jsonArray = obj.getJSONArray("data");

                for(int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject temp = jsonArray.getJSONObject(i);
                    Etiquette et = new Etiquette();
                    texts.add(temp.getString("Title"));

                    et.setDescription(temp.getString("Description"));
                    et.setId(temp.getInt("Etiquette_Id"));
                    et.setMeter(temp.getString("Meter"));
                    et.setMinor_description(temp.getString("Minor_Description"));
                    et.setOpt1_text(temp.getString("Option_Text_1"));
                    et.setOpt2_text(temp.getString("Option_Text_2"));
                    et.setOpt3_text(temp.getString("Option_Text_3"));
                    et.setOpt4_text(temp.getString("Option_Text_4"));
                    et.setUrl(temp.getString("Image_Video"));
                    et.setTitle(temp.getString("Title"));
                    et.setType(temp.getString("Type"));

                    objects.add(et);
                }

            }
            catch (Exception ex)
            {

            }

            ListView list = (ListView) getActivity().findViewById(R.id.popularList);
            ListAdapter viewadapter = new ListAdapter(getActivity(), texts , objects);
            list.setAdapter(viewadapter);

        }
    }

}
