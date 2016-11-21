package game.java.memory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    public enum GameMoves { MOVE, STOPMOVE, CHANGETIME }

    private ArrayList<Button> txt = new ArrayList<Button>();
    private static TableLayout tl;
    private static TextView tura;
    private static TextView yourScore;
    private static TextView oppScore;
    private static TextView time;

    private boolean chanegePosition;
    private int x;
    private int y;

    boolean endGame = false;
    int id;
    int player;
    Thread game = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            id = b.getInt("gameId");
            player = b.getInt("player");
        }

        chanegePosition = false;
        x = -1;
        y = -1;

        tura = (TextView)findViewById(R.id.tura);
        yourScore = (TextView)findViewById(R.id.your_score);
        oppScore = (TextView)findViewById(R.id.opp_score);
        time = (TextView)findViewById(R.id.time);

        TableRow.LayoutParams lp = new TableRow.LayoutParams(1,android.widget.TableRow.LayoutParams.MATCH_PARENT,1f);
        Button btn;
        tl = (TableLayout) findViewById(R.id.map);
        for (Integer y = 0; y < 6; y++) {
            TableRow row = new TableRow(this);
            for (Integer x = 0; x< 6; x++) {
                btn = new Button(this);
                btn.setLayoutParams(lp);
                setOnClick(btn, x, y);
                row.addView(btn);
                txt.add(btn);
            }
            tl.addView(row);
        }
        tl.requestLayout();

        game = new Thread() {
            public void run() {
                game(this);
            }
        };
        game.start();
    }

    @Override
    public void onBackPressed() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                            exit();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
    }

    private void exit()
    {
        game.interrupt();
        this.finish();
    }

    final Handler handler = new Handler(){
        @Override
        public void handleMessage(final Message msg) {
            if(msg.what== GameMoves.MOVE.ordinal())
            {

            }
            if(msg.what== GameMoves.STOPMOVE.ordinal())
            {

            }
            if(msg.what== GameMoves.CHANGETIME.ordinal())
            {
                if(msg.arg1 == -1)
                    time.setText("X");
                else
                    time.setText(Integer.toString(msg.arg1));
            }
        }
    };

    private void sendHandler(GameMoves gameMoves, Object obj, int arg1, int arg2)
    {
        Message msg = handler.obtainMessage();
        msg.what = gameMoves.ordinal();
        msg.obj = obj;
        msg.arg1 = arg1;
        msg.arg2 = arg2;
        handler.sendMessage(msg);
    }

    private void setOnClick(final Button btn, final int x, final int y){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isChanegePosition()) {
                    setX(x);
                    setY(y);
                    Toast.makeText(getBaseContext(),x + " " + y, Toast.LENGTH_LONG).show();
                }
                setChanegePosition(false);
            }
        });
    }

    private void game(Thread thread)
    {
        while(!thread.isInterrupted())
        {
            if(true)//GetActivePlayer/{gameId} == player
            {
                showOppMove();
                makeMove();
            }
        }
    }

    private void showOppMove()
    {
        //GetNotShownMoves/{gameId}

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private long countTimeElapsed(long startTime) {
        Long a = System.currentTimeMillis() - startTime;
        return a/1000;
    }

    private void makeMove()
    {
        Long startTime = System.currentTimeMillis();
        setChanegePosition(true);
        while (countTimeElapsed(startTime) < 5)
        {
            if((5-countTimeElapsed(startTime)) > 4) sendHandler(GameMoves.CHANGETIME, null, 5, 0);
            else if((5-countTimeElapsed(startTime)) > 3) sendHandler(GameMoves.CHANGETIME, null, 4, 0);
            else if((5-countTimeElapsed(startTime)) > 2) sendHandler(GameMoves.CHANGETIME, null, 3, 0);
            else if((5-countTimeElapsed(startTime)) > 1) sendHandler(GameMoves.CHANGETIME, null, 2, 0);
            else if((5-countTimeElapsed(startTime)) > 0) sendHandler(GameMoves.CHANGETIME, null, 1, 0);
        }
        sendHandler(GameMoves.CHANGETIME, null, -1, 0);
        //wysylanie
        setChanegePosition(false);
        setX(-1);
        setY(-1);
    }

    public synchronized int getY() {
        return y;
    }

    public synchronized void setY(int y) {
        this.y = y;
    }

    public synchronized int getX() {
        return x;
    }

    public synchronized void setX(int x) {
        this.x = x;
    }

    public synchronized boolean isChanegePosition() {
        return chanegePosition;
    }

    public synchronized void setChanegePosition(boolean chanegePosition) {
        this.chanegePosition = chanegePosition;
    }
}
