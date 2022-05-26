package com.example.centrodebellezagala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainBienvenida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bienvenida);
    }


    public void ir_al_logueo(View view)
    {
        Intent intent = new Intent(this, MainRegistro.class);
        startActivity(intent);
    }
}