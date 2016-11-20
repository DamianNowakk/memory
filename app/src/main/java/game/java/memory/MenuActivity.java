package game.java.memory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    private Button start_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        OnClickButtonListener();
    }

    public void OnClickButtonListener()
    {
        start_btn = (Button)findViewById(R.id.btn_start);
        start_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("game.java.memory.GameActivity");
                        startActivity(intent);
                    }
                }
        );
    }
}
