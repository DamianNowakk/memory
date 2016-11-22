package game.java.memory;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;


public class AsyncCaller2 extends AsyncTask<String, Void, JSONArray>
{

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //this method will be running on UI thread
    }
    @Override
    protected JSONArray doInBackground(String... params) {

        //this method will be running on background thread so don't update UI frome here
        //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here
        String requestedUrl = params[0];

        String url = "";
        for(int i = 0; i < params.length; i++)
        {
            url += params[i] + "/";
        }

        JSONParser jsonParser = new JSONParser();
        JSONArray json = jsonParser.getJSONFromUrl2(url, null);

        return json;
    }

    @Override
    protected void onPostExecute(JSONArray result) {
        super.onPostExecute(result);
        //this method will be running on UI thread
    }
}