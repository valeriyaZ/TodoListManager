package todolist.huji.ac.il.todolistmanager;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.widget.TextView;

public class myCursorAdapter extends CursorAdapter {
    public LayoutInflater mInflater;

    public myCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.todo_list_item, parent, false);
        v.setId(cursor.getInt(cursor.getColumnIndex("_id")));
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        SimpleDateFormat frmt = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);


        TextView dueDate =(TextView)view.findViewById(R.id.txtTodoDueDate);
        TextView title = (TextView)view.findViewById(R.id.txtTodoTitle);

        title.setText(cursor.getString(cursor.getColumnIndex("title")));
        long dateLong = cursor.getLong(cursor.getColumnIndex("due"));
        dueDate.setText(frmt.format(new Date(dateLong)));


        if (new Date(dateLong).after(cal.getTime())) {
            dueDate.setTextColor(Color.BLACK);
            title.setTextColor(Color.BLACK);
        } else {
            dueDate.setTextColor(Color.RED);
            title.setTextColor(Color.RED);
        }
    }

}