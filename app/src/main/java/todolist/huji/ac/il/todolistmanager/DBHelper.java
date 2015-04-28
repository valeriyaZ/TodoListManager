package todolist.huji.ac.il.todolistmanager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper  {

    public static final String TABLE_NAME = "todo_db";

    public DBHelper(Context context) {
        super(context, "todo_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "+ TABLE_NAME+" (" +
                " _id integer primary key autoincrement, " +
                " title string, " +
                " due long );"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
      //TODO
    }




}
