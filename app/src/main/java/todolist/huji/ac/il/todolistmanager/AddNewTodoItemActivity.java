package todolist.huji.ac.il.todolistmanager;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.view.View;
import java.util.Calendar;


import java.util.Date;

public class AddNewTodoItemActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_todo_item);
        final EditText newTodo = (EditText)findViewById(R.id.edtNewItem);
        final Button btnOK = (Button)findViewById(R.id.btnOK);
        final Button btnCancel = (Button)findViewById(R.id.btnCancel);
        final DatePicker datePkr = (DatePicker)findViewById(R.id.datePicker);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                String newItem = newTodo.getText().toString();
                int day = datePkr.getDayOfMonth();
                int month = datePkr.getMonth();
                int year = datePkr.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month,day);
                result.putExtra("title", newItem);
                result.putExtra("dueDate",calendar.getTime());
                setResult(RESULT_OK , result);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}
