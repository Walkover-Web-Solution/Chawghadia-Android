package in.walkover.chawghadia;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "chawghadia";
    private static final String TABLE_MYTABLE_1 = "mytable";
    private static final String COL_DAY = "day";
    private static final String COL_1 = "one";
    private static final String COL_2 = "two";
    private static final String COL_3 = "three";
    private static final String COL_4 = "four";
    private static final String COL_5 = "five";
    private static final String COL_6 = "six";
    private static final String COL_7 = "seven";
    private static final String COL_8 = "eight";
    private static final String COL_9 = "nine";
    private static final String COL_10 = "ten";
    private static final String COL_11 = "eleven";
    private static final String COL_12 = "twelve";
    private static final String COL_13 = "thirteen";
    private static final String COL_14 = "fourteen";
    private static final String COL_15 = "fifteen";
    private static final String COL_16 = "sixteen";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MYTABLE = " CREATE TABLE" + " " + TABLE_MYTABLE_1 + "(" + COL_DAY + " " + "TEXT," + COL_1 + " " + "TEXT,"
                + COL_2 + " " + "TEXT," + COL_3 + " " + "TEXT," + COL_4 +
                "  " + "TEXT," + " " + COL_5 + "  " + "TEXT," + " " + COL_6 +
                " " + "TEXT," + COL_7 + " " + "TEXT," + COL_8 + " " + "TEXT,"
                + COL_9 + " " + "TEXT," + COL_10 + " " + "TEXT," + COL_11 +
                " " + "TEXT," + COL_12 + " " + "TEXT," + COL_13 + " " + "TEXT,"
                + COL_14 + " " + "TEXT," + COL_15 + " " + "TEXT,"
                + COL_16 + " " + "TEXT" + ")";
        db.execSQL(CREATE_MYTABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST" + TABLE_MYTABLE_1);
        onCreate(db);
    }

    public void CreateDatabase(Context context) {

//		addData("day","one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen");
        addData("0", "Kaal", "Labh", "Udveg", "Shubh", "Udveg", "Char", "Labh", "Amrit", "Kaal", "Shubh", "Rog", "Udveg", "Shubh", "Amrit", "Char", "Rog");
        addData("1", "Udveg", "Shubh", "Amrit", "Char", "Amrit", "Kaal", "Shubh", "Rog", "Udveg", "Char", "Labh", "Amrit", "Char", "Rog", "Kaal", "Labh");
        addData("2", "Amrit", "Char", "Rog", "Kaal", "Rog", "Udveg", "Char", "Labh", "Amrit", "Kaal", "Shubh", "Rog", "Kaal", "Labh", "Udveg", "Shubh");
        addData("3", "Rog", "Kaal", "Labh", "Udveg", "Labh", "Amrit", "Kaal", "Shubh", "Rog", "Udveg", "Char", "Labh", "Udveg", "Shubh", "Amrit", "Char");
        addData("4", "Labh", "Udveg", "Shubh", "Amrit", "Shubh", "Rog", "Udveg", "Char", "Labh", "Amrit", "Kaal", "Shubh", "Amrit", "Char", "Rog", "Kaal");
        addData("5", "Shubh", "Amrit", "Char", "Rog", "Char", "Labh", "Amrit", "Kaal", "Shubh", "Rog", "Udveg", "Char", "Rog", "Kaal", "Labh", "Udveg");
        addData("6", "Char", "Rog", "Kaal", "Labh", "Kaal", "Shubh", "Rog", "Udveg", "Char", "Labh", "Amrit", "Kaal", "Labh", "Udveg", "Shubh", "Amrit");

        SharedPreferences preferences = context.getSharedPreferences(CommonFunction.PrefrenceFile, context.MODE_PRIVATE);
        preferences.edit().putString(CommonFunction.DataCheck, "true").commit();

    }


    void addData(String day, String one, String two, String three, String four, String five, String six, String seven, String eight, String nine, String ten, String eleven, String twelve, String thirteen, String fourteen, String fifteen, String sixteen) {

        SQLiteDatabase wdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_DAY, day);
        values.put(COL_1, one);
        values.put(COL_2, two);
        values.put(COL_3, three);
        values.put(COL_4, four);
        values.put(COL_5, five);
        values.put(COL_6, six);
        values.put(COL_7, seven);
        values.put(COL_8, eight);
        values.put(COL_9, nine);
        values.put(COL_10, ten);
        values.put(COL_11, eleven);
        values.put(COL_12, twelve);
        values.put(COL_13, thirteen);
        values.put(COL_14, fourteen);
        values.put(COL_15, fifteen);
        values.put(COL_16, sixteen);
        // Inserting Row
        long i = wdb.insert(TABLE_MYTABLE_1, null, values);


    }

    public String getSingleValue(String day, String col_name) {
        String value = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor s = null;
        s = db.rawQuery("select" + " " + col_name + " " + "from" + "  " + TABLE_MYTABLE_1 + " where day ='" + day + "'", null);

        if (s.getCount() > 0) {
            s.moveToFirst();
            value = s.getString(0);
        }
        return value;
    }

    public Cursor getAllValues(String day) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor s = db.rawQuery("select   * " + "  " + "from" + "  " + TABLE_MYTABLE_1 + " where day ='" + day + "'", null);
        return s;
    }

    public Cursor getAllValues() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor s = db.rawQuery("select   * " + "  " + "from" + "  " + TABLE_MYTABLE_1, null);
        return s;
    }
}














