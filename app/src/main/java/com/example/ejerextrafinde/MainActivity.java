package com.example.ejerextrafinde;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.ejerextrafinde.adapter.PartidoAdapter;
import com.example.ejerextrafinde.modelos.Partido;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import com.example.ejerextrafinde.databinding.ActivityMainBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Partido> partidosList;
    private ActivityResultLauncher<Intent> launcherPartido;
    private PartidoAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static ActivityResultLauncher<Intent> launcherEditPartido;


    private String equipos[] = {"España","Brasil","Portugal","Francia","Inglaterra","Argentina",
                                "Estados Unidos","Alemania","Suezia","Chile","Mexico","Andorra",
                                "Qatar","Uruguay","Japon","Marruecos","Costa Rica","Panama","Peru",
                                "Polonia",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        partidosList = new ArrayList<>();
        inicializaLaunchers();

        int columnas;
        //HORIZONTAL -> 2
        //VERTICAL -> 1
        //DESDE LAS CONFIGURACIONES DE LA ACTIVIDAD -> orientation // PORTRAIT(V) / LANDSCAPE(H)
        columnas = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? 1 : 2;
        adapter = new PartidoAdapter(partidosList,R.layout.partido_model_view,this);
        binding.contentMain.contenedor.setAdapter(adapter);
        layoutManager = new GridLayoutManager(MainActivity.this, columnas);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);


        
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,AddPartidoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArray("EQUIPOS",equipos);
                intent.putExtras(bundle);
                launcherPartido.launch(intent);

            }
        });
    }

    private void inicializaLaunchers() {

        launcherPartido = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {


                if (result.getResultCode() == RESULT_OK){
                    if (result.getData()!= null){
                        Bundle bundle = result.getData().getExtras();
                        Partido p = (Partido) bundle.getSerializable("PARTIDO");
                        partidosList.add(p);
                        //Toast.makeText(MainActivity.this, ""+p.toString(), Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

        launcherEditPartido = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                if (result.getResultCode() == RESULT_OK){
                    if (result.getData()!= null){
                        Bundle bundle = result.getData().getExtras();
                        Partido p = (Partido) bundle.getSerializable("PARTIDO");
                        int pos = bundle.getInt("POS");
                        partidosList.remove(pos);
                        partidosList.add(pos,p);
                        adapter.notifyItemChanged(pos);

                    }
                }
            }
        });

    }


    /**
     * Se dispara ANTES de que se elimine la actividad
     * @param outState -> guardo los datos
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("LISTA",partidosList);
    }

    /**
     * Se dispara despues de crear la actividad nueva
     * @param savedInstanceState -> recupero los datos
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList<Partido> tem = (ArrayList<Partido>) savedInstanceState.getSerializable("LISTA");
        partidosList.addAll(tem);
        adapter.notifyItemRangeInserted(0,partidosList.size());
    }

}