package in.walkover.chawghadia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class ReceiverClass extends BroadcastReceiver {
    private final String BOOT_ACTION = "android.intent.action.BOOT_COMPLETED";
    Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        mContext = context;
        String action = intent.getAction();

        if (action.equalsIgnoreCase(BOOT_ACTION)) {
            startmyService();
        }
    }

    private void startmyService() {
        //here, you will start your service
        Intent mServiceIntent = new Intent();
        mServiceIntent.setAction("ServiceForUpdation");
        mContext.startService(mServiceIntent);

    }
}
