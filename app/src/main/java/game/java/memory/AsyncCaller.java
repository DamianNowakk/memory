package game.java.memory;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

public class AsyncCaller extends AsyncTask<String, Void, JSONObject>
{

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //this method will be running on UI thread
    }
    @Override
    protected JSONObject doInBackground(String... params) {

        //this method will be running on background thread so don't update UI frome here
        //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here
        String requestedUrl = params[0];

        String url = "";
        for(int i = 0; i < params.length; i++)
        {
            url += params[i] + "/";
        }

        JSONParser jsonParser = new JSONParser();
        JSONObject json = jsonParser.getJSONFromUrl(url, null);

        return json;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);
        //this method will be running on UI thread
    }
}