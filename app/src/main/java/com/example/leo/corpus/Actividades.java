package com.example.leo.corpus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;


public class Actividades extends AppCompatActivity  {

    CalendarView calendar;
    String[] values = new String[] { "No hay ningún evento este día"};
    String[] values25 = new String[] { "12:00 TARASCA GIGANTES Y CABEZUDOS.",
            "19:00 Concierto Sinfónico: Orquesta Escuela de la Joven Orquesta Sinfónica de Granada"
    };
    String[] values26 = new String[] { "10:15 PROCESION CORPUS."
    };
    String[] values29 = new String[] { "19:30  PROCESIÓN DE LA OCTAVA"
    };

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);

        lista = (ListView) findViewById(R.id.lista);



        calendar = (CalendarView) findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int mes = month + 1;
                String diaElegido = dayOfMonth + "/" + mes + "/" + year;

                if (diaElegido.equals("25/6/2016")){
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                            android.R.layout.simple_list_item_1, android.R.id.text1, values25);
                    // Assign adapter to ListView
                    lista.setAdapter(adapter);
                }else if(diaElegido.equals("26/6/2016")){
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                            android.R.layout.simple_list_item_1, android.R.id.text1, values26);
                    // Assign adapter to ListView
                    lista.setAdapter(adapter);
                }else if(diaElegido.equals("29/6/2016")){
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                            android.R.layout.simple_list_item_1, android.R.id.text1, values29);
                    // Assign adapter to ListView
                    lista.setAdapter(adapter);
                }
                else {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                            android.R.layout.simple_list_item_1, android.R.id.text1, values);
                    // Assign adapter to ListView
                    lista.setAdapter(adapter);
                }





            }
        });
    }


}
