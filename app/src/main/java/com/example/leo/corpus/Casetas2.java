package com.example.leo.corpus;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Casetas2 extends AppCompatActivity implements View.OnClickListener{
    private CasetasDbAdapter dbHelper;
    private TextView tv_name;
    private TextView tv_calle;
    private TextView tv_descripcion;

    private CheckBox check_favorito;
    private ImageButton compartir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casetas2);
        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_calle = (TextView)findViewById(R.id.tv_calle);
        tv_descripcion = (TextView)findViewById(R.id.tv_descripcion);

        check_favorito=(CheckBox) findViewById(R.id.favorito);
        compartir = (ImageButton)findViewById(R.id.compartir);

        compartir.setOnClickListener(this);

        final String id_caseta = getIntent().getStringExtra("id");

        check_favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (check_favorito.isChecked()) {
                    //Toast.makeText(getApplicationContext(), "CheckBox marcado" + id_caseta, Toast.LENGTH_LONG).show();
                    dbHelper = new CasetasDbAdapter(getApplicationContext());
                    dbHelper.open();
                    dbHelper.anadirFavorito(id_caseta);
                }
                else {
                    //Toast.makeText(getApplicationContext(), "CheckBox no marcado" + id_caseta, Toast.LENGTH_LONG).show();
                    dbHelper = new CasetasDbAdapter(getApplicationContext());
                    dbHelper.open();
                    dbHelper.quitarFavorito(id_caseta);

                }

            }
        });



        dbHelper = new CasetasDbAdapter(this);
        dbHelper.open();

        Cursor cursor = dbHelper.getTodo(id_caseta);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    String name = cursor.getString(0);
                    String calle = cursor.getString(1);
                    String descripcion = cursor.getString(2);
                    String favorito = cursor.getString(3);
                    tv_name.setText(name);
                    tv_calle.setText(calle);
                    tv_descripcion.setText(descripcion);

                    if (favorito.equals("si")){
                        check_favorito.setChecked(true);


                    }
                    else {
                        check_favorito.setChecked(false);
                    }
                } while (cursor.moveToNext());
            }
        }



        cursor.close();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.compartir:

                String url_aires_de_fiesta = "https://goo.gl/maps/nAbksKGPimo";

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Estoy en la caseta " + tv_name.getText() + ";) \n "+ url_aires_de_fiesta );
                sendIntent.setType("text/plain");

                startActivity(sendIntent);



                break;

        }
    }
}

