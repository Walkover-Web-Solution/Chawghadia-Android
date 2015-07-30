package in.walkover.chawghadia;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import in.walkover.chawghdia.R;

public class NightView extends Activity {
    Button viewday;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK: {
                Intent i1 = new Intent(NightView.this, MainActivity.class);
                i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i1);
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                finish();
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(in.walkover.chawghdia.R.layout.flipper_night);

        viewday = (Button) findViewById(in.walkover.chawghdia.R.id.static_viewbd);
        viewday.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(NightView.this, DayView.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                overridePendingTransition(in.walkover.chawghdia.R.anim.enter_anim, in.walkover.chawghdia.R.anim.exit_anim);
            }
        });
    }
}
