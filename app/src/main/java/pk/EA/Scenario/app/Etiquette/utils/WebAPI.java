package pk.EA.Scenario.app.Etiquette.utils;

/**
 * Created by Mian on 8/31/2015.
 */
//public class WebAPI extends AsyncTask<String, Void, String> {

    /*
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

        HttpPost request = new HttpPost(url);

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

        PopularFragment newFrag = new PopularFragment();

        android.support.v4.app.FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        trans.replace(R.id.fragment_container, newFrag, "PopularFragment").commit();
    }
    */
//}