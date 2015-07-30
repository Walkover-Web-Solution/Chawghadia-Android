package in.walkover.chawghadia;

import android.content.Context;
import android.database.Cursor;

import java.util.Date;


public class GetTextClass {
    Cursor c = null;

    public String getNextString(Context ctx, Date d, int col) {
        DatabaseHandler db = new DatabaseHandler(ctx);
        c = db.getAllValues();
        c.moveToPosition(d.getDay());
        String s = c.getString(col);
        return s;
    }

    public String getPreviousString(Context ctx, Date d, int col) {
        DatabaseHandler db = new DatabaseHandler(ctx);
        c = db.getAllValues();
        c.moveToPosition(d.getDay());
        String s = c.getString(col);
        return s;
    }

    public String getCurrentString(Context ctx, Date d, int col) {
        DatabaseHandler db = new DatabaseHandler(ctx);

        c = db.getAllValues();
        c.moveToPosition(d.getDay());
        String s = c.getString(col);
        return s;
    }
}
