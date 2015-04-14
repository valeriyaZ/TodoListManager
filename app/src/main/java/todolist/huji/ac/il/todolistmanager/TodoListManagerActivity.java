package todolist.huji.ac.il.todolistmanager;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Context;
import android.net.Uri;

import android.widget.ListView;


import android.app.AlertDialog;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.view.View;
import android.content.DialogInterface;
import java.util.Date;



public class TodoListManagerActivity extends ActionBarActivity {

    public ArrayList<NewTodoItem> listItems = new ArrayList<>();
    public myAdapter<NewTodoItem> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);
        final ListView todoItems = (ListView)findViewById(R.id.lstTodoItems);
        listAdapter = new myAdapter<>(this,android.R.layout.simple_list_item_1, listItems);

        todoItems.setAdapter(listAdapter);

        todoItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {
                final String itemTitle = listItems.get(position).getTitle();
                AlertDialog.Builder builder = new AlertDialog.Builder(TodoListManagerActivity.this);
                builder.setTitle(itemTitle);
                builder.setNegativeButton(" Delete Item ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listItems.remove(position);
                        listAdapter.notifyDataSetChanged();
                    }
                });
                String[] curItems = new String[1];
                curItems[0] = itemTitle;
                builder.setItems(curItems , new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        if (itemTitle.toLowerCase().startsWith("call ")){
                            String number = itemTitle.substring(itemTitle.toLowerCase().indexOf("call ") + 5);
                            Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                            startActivity(dial);
                        }
                    }
                });
                builder.show();
            return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_todo_list_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuItemAdd) {
            Intent intent = new Intent(this , AddNewTodoItemActivity.class);
            startActivityForResult(intent, 0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int reqCode, int resCode, Intent data){
        if(reqCode == 0 && resCode == RESULT_OK){
            Date curData = (Date)data.getSerializableExtra("dueDate");
            String curString = data.getStringExtra("title");
            NewTodoItem curItem = new NewTodoItem(curString,curData);
            listItems.add(curItem);
            listAdapter.notifyDataSetChanged();
        }
    }

}
