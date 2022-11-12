package com.example.ejerextrafinde.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerextrafinde.MainActivity;
import com.example.ejerextrafinde.R;
import com.example.ejerextrafinde.VerDatosPartidoActivity;
import com.example.ejerextrafinde.modelos.Partido;

import java.util.List;

public class PartidoAdapter extends RecyclerView.Adapter<PartidoAdapter.PartidoVH> {


    private List<Partido> objects;
    private int resource;
    private Context context;


    public PartidoAdapter(List<Partido> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public PartidoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View partidoView = LayoutInflater.from(context).inflate(resource, null);

        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        partidoView.setLayoutParams(lp);

        return new PartidoVH(partidoView);
    }

    @Override
    public void onBindViewHolder(@NonNull PartidoVH holder, int position) {

        Partido partido = objects.get(position);

        holder.txtlocal.setText(partido.getLocal());
        holder.txtvisitante.setText(partido.getVisitante());
        holder.txtGolesLocal.setText(""+partido.getGolesLocal());
        holder.txtGolesVisitante.setText(""+partido.getGolesVisitante());

        String equipos[] = {"España","Brasil","Portugal","Francia","Inglaterra","Argentina",
                "Estados Unidos","Alemania","Suezia","Chile","Mexico","Andorra",
                "Qatar","Uruguay","Japon","Marruecos","Costa Rica","Panama","Peru",
                "Polonia",};



        holder.btnGanador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verGanador(partido).show();
            }
        });

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objects.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, VerDatosPartidoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("PARTIDO", partido);
                bundle.putInt("POS", holder.getAdapterPosition());
                intent.putExtras(bundle);
                MainActivity.launcherEditPartido.launch(intent);


            }
        });
    }

    private AlertDialog verGanador(Partido p) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("GANADOR");

        String ganador = "";

        if (p.getGolesLocal() > p.getGolesVisitante()){
            ganador = p.getLocal();
        } else if (p.getGolesLocal() < p.getGolesVisitante()){
            ganador = p.getVisitante();
        } else if (p.getGolesLocal() == p.getGolesVisitante()){
            ganador = "EMAPATE";
        }

        builder.setMessage(ganador);

        return builder.create();

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class PartidoVH extends RecyclerView.ViewHolder {

        TextView txtlocal, txtvisitante, txtGolesLocal, txtGolesVisitante;
        Button btnGanador;
        ImageButton btnEliminar;

        public PartidoVH(@NonNull View itemView) {
            super(itemView);


            txtlocal = itemView.findViewById(R.id.txtLocalModelView);
            txtvisitante = itemView.findViewById(R.id.txtVisitanteModelView);
            btnGanador = itemView.findViewById(R.id.btnVerResultado);
            txtGolesLocal = itemView.findViewById(R.id.txtGolesLocalModelView);
            txtGolesVisitante = itemView.findViewById(R.id.txtGolesVisitanteModelView);
            btnEliminar = itemView.findViewById(R.id.btnEliminarModelView);

        }
    }
}
