package com.jesusmanzano.spinnercontemas;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
private EditText user, Contraseña;
    TextView textViewDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.Spinnertemas);
        textViewDescripcion = (TextView) findViewById(R.id.themeDescription);

        final String[] Temas = {
                "Selecciona un Tema", "Tema1", "Tema2", "Tema3"
        };

        // Configurar el adaptador para el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Temas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    int imageId = getResources().getIdentifier(Temas[position].toLowerCase(), "drawable", getPackageName());

                    Toast.makeText(parent.getContext(), "Seleccionaste el tema: " + Temas[position], Toast.LENGTH_SHORT).show();


                    switch (position) {
                        case 1:
                            setTheme(R.style.Tema1);
                            break;
                        case 2: // Venus
                            setTheme(R.style.Tema2);
                            break;
                        case 3:
                            setTheme(R.style.Tema3);
                    }

                    // Reiniciar la actividad para aplicar el tema
                    finish();
                    startActivity(getIntent());
                } else {

                    textViewDescripcion.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nada aquí
            }
        });
    }
}

