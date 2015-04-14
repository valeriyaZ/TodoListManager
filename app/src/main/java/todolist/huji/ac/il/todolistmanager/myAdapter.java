
package todolist.huji.ac.il.todolistmanager;


import android.graphics.Color;
import java.util.Date;
import java.util.Calendar;

import android.view.LayoutInflater;
import android.widget.TextView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.view.View;
import android.content.Context;
import android.view.ViewGroup;
import java.text.SimpleDateFormat;


public class myAdapter<T> extends ArrayAdapter {
    ArrayList<NewTodoItem> curList;
    public myAdapter(Context context , int resource, ArrayList<NewTodoItem> newTodoList) {
        super(context, resource , newTodoList);
        this.curList = newTodoList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.todo_list_item, null);
        }
        SimpleDateFormat frmt = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);


        TextView dueDate =(TextView)convertView.findViewById(R.id.txtTodoDueDate);
        TextView title = (TextView)convertView.findViewById(R.id.txtTodoTitle);

        NewTodoItem item = curList.get(position);
        title.setText(item.getTitle());

        dueDate.setText(frmt.format(item.getDate()));

        if (item.getDate().after(cal.getTime())) {
            dueDate.setTextColor(Color.BLACK);
            title.setTextColor(Color.BLACK);
        } else {
            dueDate.setTextColor(Color.RED);
           title.setTextColor(Color.RED);
        }
        return convertView;
    }
}
