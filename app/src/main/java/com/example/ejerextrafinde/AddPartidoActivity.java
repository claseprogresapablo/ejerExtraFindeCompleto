package com.example.ejerextrafinde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ejerextrafinde.databinding.ActivityAddPartidoBinding;
import com.example.ejerextrafinde.modelos.Partido;

import java.lang.reflect.Array;

public class AddPartidoActivity extends AppCompatActivity {


    private ActivityAddPartidoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPartidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //RECUPERAR INTENT
        Intent intent = getIntent();
        //RECUPERAR BUNDLE
        Bundle bundle = intent.getExtras();

        String equipos[] = bundle.getStringArray("EQUIPOS");

        binding.localSpinnerAddPartido.setAdapter(new ArrayAdapter<String>(AddPartidoActivity.this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,equipos));
        binding.visitanteSpinnerAddPartido.setAdapter(new ArrayAdapter<String>(AddPartidoActivity.this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,equipos));


        binding.btnCancelarAddPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnAceptarAddPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Partido p = createPartido();
                //Toast.makeText(AddPartidoActivity.this, "HOOAAA", Toast.LENGTH_SHORT).show();

                if (p!=null) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PARTIDO",p);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK,intent);
                    finish();
                }


            }
        });


    }

    private Partido createPartido() {

        Partido p = new Partido(
                binding.localSpinnerAddPartido.getSelectedItem().toString(),
                binding.visitanteSpinnerAddPartido.getSelectedItem().toString(),
                binding.txtResumenAddPartido.getText().toString(),
                Integer.parseInt(binding.txtGolesLocalAddPartido.getText().toString()) ,
                Integer.parseInt(binding.txtGolesVisitanteAddPartido.getText().toString())


                );

        return p;

    }
}