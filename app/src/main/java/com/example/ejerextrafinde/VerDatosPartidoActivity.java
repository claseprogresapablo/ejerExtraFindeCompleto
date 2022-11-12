package com.example.ejerextrafinde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ejerextrafinde.databinding.ActivityAddPartidoBinding;
import com.example.ejerextrafinde.databinding.ActivityVerDatosPartidoBinding;
import com.example.ejerextrafinde.modelos.Partido;

public class VerDatosPartidoActivity extends AppCompatActivity {

    private ActivityVerDatosPartidoBinding binding;
    private int pos;

    private String equipos[] = {"España","Brasil","Portugal","Francia","Inglaterra","Argentina",
            "Estados Unidos","Alemania","Suezia","Chile","Mexico","Andorra",
            "Qatar","Uruguay","Japon","Marruecos","Costa Rica","Panama","Peru",
            "Polonia",};

    private int posSpinnerLocal;
    private int posSpinnerVisitante;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //2-Construye el binding
        binding = ActivityVerDatosPartidoBinding.inflate(getLayoutInflater());
        //3- Asocia el binding a la activity
        setContentView(binding.getRoot());


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle!=null){
            Partido p = (Partido) bundle.getSerializable("PARTIDO");
            pos = bundle.getInt("POS");
            inicializarVistas(p);
        }



        binding.btnCancelarVerDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnAceptarVerDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Partido p = crearPartido();

                if (p!=null){

                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PARTIDO",p);
                    bundle.putInt("POS",pos);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK,intent);
                    finish();


                }
            }
        });
    }

    private Partido crearPartido() {

        Partido p = new Partido(

                binding.spinnerEquipoLocalVerDatos.getSelectedItem().toString(),
                binding.spinnerEquipoVisitanteVerDatos.getSelectedItem().toString(),
                binding.txtResumenVerDatos.getText().toString(),
                Integer.parseInt(binding.txtGolesLocalVerDatos.getText().toString()) ,
                Integer.parseInt(binding.txtGolesVisitanteVerDatos.getText().toString())


        );

        return p;
    }

    private void inicializarVistas(Partido p) {

        binding.spinnerEquipoLocalVerDatos.setAdapter(new ArrayAdapter<String>(VerDatosPartidoActivity.this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,equipos));
        binding.spinnerEquipoVisitanteVerDatos.setAdapter(new ArrayAdapter<String>(VerDatosPartidoActivity.this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,equipos));


        for (int i = 0; i < equipos.length; i++) {

            if (p.getLocal().equalsIgnoreCase(equipos[i])){

                posSpinnerLocal = i;

            }


        }

        for (int i = 0; i < equipos.length; i++) {

            if (p.getVisitante().equalsIgnoreCase(equipos[i])){

                posSpinnerVisitante = i;

            }


        }

        binding.spinnerEquipoLocalVerDatos.setSelection(posSpinnerLocal);
        binding.spinnerEquipoVisitanteVerDatos.setSelection(posSpinnerVisitante);
        binding.txtGolesVisitanteVerDatos.setText(String.valueOf(p.getGolesVisitante()));
        binding.txtGolesLocalVerDatos.setText(String.valueOf(p.getGolesLocal()));
        binding.txtResumenVerDatos.setText(p.getResumen());

    }
}