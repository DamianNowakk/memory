package game.java.memory;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    public enum GameMoves { TEST, TEST2 }

    private ArrayList<Button> txt = new ArrayList<Button>();
    private TableLayout tl;
    private TextView tura;
    private TextView yourScore;
    private TextView oppScore;

    int id;
    int player;
    Thread game = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            id = b.getInt("gameId");
            player = b.getInt("player");
        }

        tura = (TextView)findViewById(R.id.tura);
        yourScore = (TextView)findViewById(R.id.your_score);
        oppScore = (TextView)findViewById(R.id.opp_score);

        setContentView(R.layout.activity_game);
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
                game();
            }
        };
        game.start();
    }

    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what== GameMoves.TEST.ordinal())
            {
                Toast.makeText(getBaseContext(), "start", Toast.LENGTH_SHORT).show();
            }
            if(msg.what== GameMoves.TEST2.ordinal())
            {
                Toast.makeText(getBaseContext(), "stop", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void setOnClick(final Button btn, final int x, final int y){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),x + " " + y, Toast.LENGTH_LONG).show();
            }
        });
    }

    private long countTimeElapsed(long startTime) {
        return System.currentTimeMillis() - startTime;
    }

    private void game()
    {
        while(true)
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
        Message msg = handler.obtainMessage();
        msg.what = GameMoves.TEST.ordinal();
        handler.sendMessage(msg);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        msg = handler.obtainMessage();
        msg.what = GameMoves.TEST2.ordinal();
        handler.sendMessage(msg);
    }

    private void makeMove()
    {

    }
}
