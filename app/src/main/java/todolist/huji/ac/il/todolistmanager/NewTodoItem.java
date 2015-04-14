package todolist.huji.ac.il.todolistmanager;

import java.util.Date;

public class NewTodoItem {
    public String title;
    public Date date;

    public NewTodoItem(String curString, Date curData) {
        this.title = curString;
        this.date  = curData;
    }


    public String getTitle(){
        return this.title;
    }

    public Date getDate(){
        return this.date;
    }

}
