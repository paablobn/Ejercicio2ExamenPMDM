package pablobruixolanavarro.ejercicio2examen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.AlumnoVH> {

    private List<Alumno> objects;
    private int resource;
    private Context context;

    public AlumnoAdapter(List<Alumno> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public AlumnoAdapter.AlumnoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View alumnoView = LayoutInflater.from(context).inflate(resource, null);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        alumnoView.setLayoutParams(layoutParams);
        return new AlumnoVH(alumnoView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoAdapter.AlumnoVH holder, int position) {
        Alumno alumno = objects.get(position);

        holder.lbNombreAlumno.setText(alumno.getNombre());
        holder.lbCiclo.setText(alumno.getCiclo());
        holder.lbTelefono.setText(String.valueOf(alumno.getTelefono()));

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarAlumno("¿ESTÁS SEGURO QUE QUIERES ELIMINAR A "+alumno.getNombre()+ " ?",alumno).show();
            }
        });
    }

    public AlertDialog eliminarAlumno(String texto, Alumno alumno){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);

        builder.setTitle(texto);
        builder.setCancelable(false);
        builder.setNegativeButton("NO",null);
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                objects.remove(alumno);
                notifyDataSetChanged();
            }
        });
        return builder.create();
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class AlumnoVH extends RecyclerView.ViewHolder {
        TextView lbNombreAlumno;
        TextView lbCiclo;
        TextView lbTelefono;
        ImageButton btnEliminar;

        public AlumnoVH(@NonNull View itemView) {
            super(itemView);
            lbNombreAlumno = itemView.findViewById(R.id.lbNombreAlumnoViewModel);
            lbCiclo = itemView.findViewById(R.id.lbCicloViewModel);
            lbTelefono = itemView.findViewById(R.id.lbTelefonoViewModel);
            btnEliminar = itemView.findViewById(R.id.btnEliminarAlumnoViewModel);
        }
    }
}
