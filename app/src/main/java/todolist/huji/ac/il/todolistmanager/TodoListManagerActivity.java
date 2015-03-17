package todolist.huji.ac.il.todolistmanager;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;

import android.app.AlertDialog;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.view.View;
import android.content.DialogInterface;


public class TodoListManagerActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<String> listItems = new ArrayList<String>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);

        Button addBtn = (Button)findViewById(R.id.menuItemAdd);
        final EditText newTodo = (EditText)findViewById(R.id.edtNewItem);
        final ListView todoItems = (ListView)findViewById(R.id.lstTodoItems);
        final myAdapter<String> listAdapter = new myAdapter<String>(this,android.R.layout.simple_list_item_1, listItems);
        todoItems.setAdapter(listAdapter);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listItems.add(newTodo.getText().toString());
                listAdapter.notifyDataSetChanged();
                newTodo.setText("");
            }
        });
        todoItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {
                String itemValue = (String) todoItems.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(TodoListManagerActivity.this);
                builder.setTitle(itemValue);
                builder.setNegativeButton(" Delete Item ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listItems.remove(position);
                        listAdapter.notifyDataSetChanged();
                    }
                });
                builder.show();
            return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo_list_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
