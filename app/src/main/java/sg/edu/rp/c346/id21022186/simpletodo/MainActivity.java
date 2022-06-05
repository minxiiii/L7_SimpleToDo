package sg.edu.rp.c346.id21022186.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    Button btnClear;
    Button btnDel;
    EditText Task;
    ListView tvDisplay;
    Spinner spinner;
    ArrayList<String> textArray;
    ArrayAdapter<String> aaTips;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd =findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClean);
        btnDel = findViewById(R.id.btnDel);
        tvDisplay = findViewById(R.id.tvDisplay);
        Task = findViewById(R.id.Tasks);
        spinner = findViewById(R.id.spinner);

        textArray = new ArrayList<>();

        aaTips = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,  textArray);
        tvDisplay.setAdapter(aaTips);


       btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task  = Task.getText().toString();

                textArray.add(task);
                aaTips.notifyDataSetChanged();

           }
      });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textArray.clear();
                aaTips.notifyDataSetChanged();


            }
       });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textArray.remove(Task.getText().toString());
                aaTips.notifyDataSetChanged();


            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item = spinner.getSelectedItem().toString();
                int position = spinner.getSelectedItemPosition();

                switch (position) {
                    case 0:
                        String task = Task.getText().toString();

                        textArray.add(task);
                        aaTips.notifyDataSetChanged();
                        btnDel.setEnabled(false);
                        btnAdd.setEnabled(true);

                        break;

                    case 1:
                        String taskk = Task.getText().toString();

                        if (textArray.isEmpty()) {
                            Task.setHint("You don't have any task to remove");

                        } else {
                            Task.setHint("Type in the index of the task to be removed");
                            int value = Integer.parseInt(taskk);
                            if (value >= textArray.size() || value < 0) {
                                Task.setText("Wrong number index");
                            }


                        Toast.makeText(MainActivity.this, taskk, Toast.LENGTH_LONG).show();

                        textArray.remove(Task.getText().toString());
                        aaTips.notifyDataSetChanged();
                        btnDel.setEnabled(true);
                        btnAdd.setEnabled(false);
                }

                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}