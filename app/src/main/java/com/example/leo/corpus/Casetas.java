package com.example.leo.corpus;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class Casetas extends Activity {

    private CasetasDbAdapter dbHelper;
    private SimpleCursorAdapter dataAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casetas);

        dbHelper = new CasetasDbAdapter(this);
        dbHelper.open();


        displayListView();



    }

    private void displayListView() {


        Cursor cursor = dbHelper.recorrerCasetas();


        String[] columns = new String[] {
                CasetasDbAdapter.KEY_NAME,
                CasetasDbAdapter.KEY_CALLE

        };


        int[] to = new int[] {
                R.id.name,
                R.id.calle,

        };


        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.caseta_info,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setItemsCanFocus(false);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {

                Cursor cursor = (Cursor) listView.getItemAtPosition(position);


                String id_caseta = cursor.getString(cursor.getColumnIndexOrThrow("_id"));

                //Toast.makeText(getApplicationContext(),id_caseta, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), Casetas2.class);
                intent.putExtra("id", id_caseta);
                startActivity(intent);


            }
        });

        EditText myFilter = (EditText) findViewById(R.id.myFilter);
        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
            }
        });

        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return dbHelper.recorrerCasetasPorNombre(constraint.toString());
            }
        });

    }
}
