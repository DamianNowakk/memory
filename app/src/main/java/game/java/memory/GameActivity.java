package game.java.memory;

import android.os.Handler;
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

    ArrayList<Button> txt = new ArrayList<Button>();
    TableLayout tl;
    TextView tura;
    TextView yourScore;
    TextView oppScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    }

    private void setOnClick(final Button btn, final int x, final int y){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),x + " " + y, Toast.LENGTH_LONG).show();
            }
        });
    }
}
