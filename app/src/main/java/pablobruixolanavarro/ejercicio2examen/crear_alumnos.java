package pablobruixolanavarro.ejercicio2examen;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import pablobruixolanavarro.ejercicio2examen.databinding.ActivityCrearAlumnosBinding;

public class crear_alumnos extends AppCompatActivity {

    private ActivityCrearAlumnosBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_alumnos);

        binding = ActivityCrearAlumnosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCrearAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.txtNombreAlumno.getText().toString().isEmpty() &&
                        !binding.txtCicloInteresado.getText().toString().isEmpty() &&
                        !binding.txtTelefono.toString().isEmpty()) {

                    Alumno alumno = new Alumno(binding.txtNombreAlumno.getText().toString(),
                            binding.txtCicloInteresado.getText().toString(),
                            Integer.parseInt(binding.txtTelefono.getText().toString())) ;

                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("alumno", alumno);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(crear_alumnos.this, "RELLENA LOS DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}