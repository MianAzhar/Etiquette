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
/*
        ArrayList<String> texts = new ArrayList<String>();

        objects = new ArrayList<Etiquette>();
        Bundle data = getArguments();
        String str;

        try {
            str = data.getString("data");

            JSONObject obj = new JSONObject(str);

            JSONArray jsonArray = obj.getJSONArray("data");

            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject temp = jsonArray.getJSONObject(i);
                Etiquette et = new Etiquette();
                texts.add(temp.getString("minor_description"));

                et.setDescription(temp.getString("description"));
                et.setId(temp.getInt("etiquetteId"));
                et.setMeter(temp.getString("meter"));
                et.setMinor_description(temp.getString("minor_description"));
                et.setOpt1(temp.getString("option1"));
                et.setOpt2(temp.getString("option2"));
                et.setOpt3(temp.getString("option3"));
                et.setOpt4(temp.getString("option4"));
                et.setUrl(temp.getString("image_video"));
                et.setTitle(temp.getString("title"));
                et.setType(temp.getString("type"));

                objects.add(et);
            }

        }
        catch (Exception ex)
        {

        }

        /*
        texts.add("This is sample text");
        texts.add("This is sample text");
        texts.add("This is sample text");
        texts.add("This is sample text");
        texts.add("This is sample text");

        int[] res = {R.drawable.picture, R.drawable.picture, R.drawable.picture, R.drawable.picture, R.drawable.picture};

        ListView list = (ListView) getActivity().findViewById(R.id.popularList);
        ListAdapter viewadapter = new ListAdapter(getActivity(), texts , objects);
        list.setAdapter(viewadapter);
*/
        ListView list = (ListView) getActivity().findViewById(R.id.popularList);
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

    public class WebAPI extends AsyncTask<String, Void, String> {

        private Exception exception;

        ProgressDialog progressDialog = null;

        protected void onPreExecute() {
            if(progressDialog == null)
                progressDialog = ProgressDialog.show(getActivity(), getString(R.string.loading_text), getString(R.string.logging_in_text), true);
        }

        protected String doInBackground(String... params) {



            String url = params[0];

            String result = "";


            HttpClient httpclient = new DefaultHttpClient();

            HttpPost request = new HttpPost("http://etiquetteapp.azurewebsites.net/getAllEtiquettes");

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
                    texts.add(temp.getString("minor_description"));

                    et.setDescription(temp.getString("description"));
                    et.setId(temp.getInt("etiquetteId"));
                    et.setMeter(temp.getString("meter"));
                    et.setMinor_description(temp.getString("minor_description"));
                    et.setOpt1(temp.getString("option1"));
                    et.setOpt2(temp.getString("option2"));
                    et.setOpt3(temp.getString("option3"));
                    et.setOpt4(temp.getString("option4"));
                    et.setUrl(temp.getString("image_video"));
                    et.setTitle(temp.getString("title"));
                    et.setType(temp.getString("type"));

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
