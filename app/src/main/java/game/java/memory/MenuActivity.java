package game.java.memory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;
import java.util.concurrent.ExecutionException;

import game.java.memory.containers.GetActivePlayer;
import game.java.memory.containers.GetGame;
import game.java.memory.containers.GetGameScore;
import game.java.memory.containers.GetNoShownMoves;
import game.java.memory.containers.MakeMove;

public class MenuActivity extends AppCompatActivity {
    private Button start_btn;
    private EditText editLogin;
    private Activity activity = this;
    GetGame getGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        OnClickButtonListener();
        OnClickEditTextListener();
//        try {
//            GetGame getGame = WebAPI.getGame(1);
//            GetGameScore getGameScore = WebAPI.getGameScore(1);
//            MakeMove makeMove = WebAPI.makeMove(1,1,1,1,1,1);
//            WebAPI.makeMove(1,1,1,1,2,1);
//            WebAPI.makeMove(1,1,1,1,2,3);
//            GetActivePlayer getActivePlayer = WebAPI.getActivePlayer(1);
//            List<GetNoShownMoves> getNoShownMoves = WebAPI.getNotShownMoves(1);
//            TextView textView = (TextView)findViewById(R.id.textView2);
//            textView.append(getGame.toString() + "\n");
//            textView.append(getGameScore.toString() + "\n");
//            textView.append(makeMove.toString() + "\n");
//            textView.append(getActivePlayer.toString() + "\n");
//            for(GetNoShownMoves getNoShownMoves1 : getNoShownMoves) {
//                textView.append(getNoShownMoves1.toString() + "; \n");
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }

    public void OnClickButtonListener()
    {
        start_btn = (Button)findViewById(R.id.btn_start);
        start_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getGame = null;
                        String login = editLogin.getText().toString();
                        if (login.trim().length() > 0) {
                            try {
                                getGame = WebAPI.getGame(Integer.valueOf(login));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            new AsyncWaitForPlayer().execute();
                        }
                        else
                        {
                            TextView textView = (TextView)findViewById(R.id.textView2);
                            textView.setText("Please type login....");
                        }
                    }
                }
        );
    }

    public class AsyncWaitForPlayer extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //this method will be running on UI thread
            TextView textView = (TextView)findViewById(R.id.textView3);
            textView.setText("Waiting for opponent...");
        }

        @Override
        protected Void doInBackground(Void... params) {
            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here
            GetActivePlayer getActivePlayer = null;

            while(true){
                try {
                    JSONParser jsonParser = new JSONParser();
                    JSONObject json = jsonParser.getJSONFromUrl(WebAPI.HOME + "GetActivePlayer/" + getGame.gameId, null);
                    getActivePlayer = new GetActivePlayer(json.getInt(GetActivePlayer.PLAYERID));
                    Log.d("activeplayerID ",getActivePlayer.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(getActivePlayer.playerID != 3)
                    break;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            TextView textView = (TextView)findViewById(R.id.textView3);
            textView.setText("Player found !");
            //this method will be running on UI thread
            Intent intent = new Intent("game.java.memory.GameActivity");
            Bundle b = new Bundle();
            b.putInt("gameId", getGame.gameId);
            b.putInt("player", getGame.playerNumber);
            intent.putExtras(b);
            startActivity(intent);
        }
    }

    public void OnClickEditTextListener()
    {
        editLogin = (EditText) findViewById(R.id.id);
    }
}
