package in.walkover.chawghadia;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import in.walkover.chawghdia.R;

public class DayView extends Activity {
    Button viewnight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper_day);
        viewnight = (Button) findViewById(R.id.static_viewbn);
        viewnight.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(DayView.this, NightView.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: {
                Intent i1 = new Intent(DayView.this, MainActivity.class);
                i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i1);
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                this.finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}


