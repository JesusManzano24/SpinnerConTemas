package com.jesusmanzano.spinnercontemas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

    private static final String PREF_THEME = "pref_theme";
    private Spinner spinner;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Verificar si es la primera vez que se inicia la app
        if (!prefs.contains(PREF_THEME)) {
            prefs.edit().putInt(PREF_THEME, R.style.AppTheme).apply();
        }

        int themeId = prefs.getInt(PREF_THEME, R.style.AppTheme);
        setTheme(themeId);

        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.Spinnertemas);

        final String[] Temas = {
                "Selecciona un tema", "Tema1", "Tema2", "Tema3"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Temas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int newThemeId;
                switch (position) {
                    case 0:
                        newThemeId = R.style.AppTheme;
                        break;
                    case 1:
                        newThemeId = R.style.Tema1;
                        break;
                    case 2:
                        newThemeId = R.style.Tema2;
                        break;
                        case 3:
                            newThemeId = R.style.Tema3;
                        break;
                    default:
                        return;
                }

                int currentThemeId = prefs.getInt(PREF_THEME, R.style.AppTheme);
                if (currentThemeId != newThemeId) {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt(PREF_THEME, newThemeId);
                    editor.apply();

                    recreate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nada aquí
            }
        });
    }
}
