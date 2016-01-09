package forum.thread;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.tarucfyp.dietplan.R;

/**
 * Created by weiping-tan on 6/1/2016.
 */
public class Add_Thread extends Activity{
    private String categoryID;
    private EditText title , subject ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_thread);
        Intent intent = getIntent();
        categoryID = intent.getExtras().getString("id_category");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_compose, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_compose:
                title = (EditText) findViewById(R.id.add_thread_title);
                subject = (EditText) findViewById(R.id.add_thread_message);
                if(title == null)
                    Toast.makeText(Add_Thread.this, "Please enter the title ! ", Toast.LENGTH_SHORT).show();
                else if (subject == null)
                    Toast.makeText(Add_Thread.this, "Please enter the subject ! " , Toast.LENGTH_SHORT).show();
                else if (title == null & subject == null)
                    Toast.makeText(Add_Thread.this, "Please enter the title and subject ! " , Toast.LENGTH_SHORT).show();
                else
                {
                    Toast.makeText(Add_Thread.this, "Please enter the title and subject ! ", Toast.LENGTH_SHORT).show();
                }

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
