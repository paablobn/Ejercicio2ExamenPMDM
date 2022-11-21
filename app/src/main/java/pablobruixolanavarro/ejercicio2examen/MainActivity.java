package pablobruixolanavarro.ejercicio2examen;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import pablobruixolanavarro.ejercicio2examen.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Alumno> alumnosList;

    private AlumnoAdapter adapter;

    private RecyclerView.LayoutManager layoutManager;


    private ActivityResultLauncher<Intent> crearAlumnoLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        alumnosList = new ArrayList<>();
        inicializarLaunchers();

        adapter = new AlumnoAdapter(alumnosList, R.layout.card_alumnos, this);
        layoutManager = new GridLayoutManager(this, 1);
        binding.contentMain.contenedor.setAdapter(adapter);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, crear_alumnos.class);
                crearAlumnoLauncher.launch(intent);
            }
        });
    }

    private void inicializarLaunchers() {
        crearAlumnoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null && result.getData().getExtras() != null) {
                                Alumno alumno = (Alumno) result.getData().getExtras().getSerializable("alumno");
                                alumnosList.add(0, alumno);
                                adapter.notifyItemInserted(0);
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "CANCELADO", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );
    }


}