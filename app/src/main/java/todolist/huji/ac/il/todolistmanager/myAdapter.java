/**
 * Created by Zelikovich on 17-Mar-15.
 */
package todolist.huji.ac.il.todolistmanager;


import android.graphics.Color;

import android.widget.TextView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.view.View;
import android.content.Context;
import android.view.ViewGroup;


public class myAdapter<T> extends ArrayAdapter {
    public myAdapter(Context context , int resource, ArrayList<T> newTodoList) {
        super(context, resource , newTodoList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = super.getView(position, convertView, parent);
        TextView text = (TextView) view.findViewById(android.R.id.text1);
        if (position % 2 == 1) {
            text.setTextColor(Color.BLUE);
        } else {
            text.setTextColor(Color.RED);
        }
        return view;
    }
}
