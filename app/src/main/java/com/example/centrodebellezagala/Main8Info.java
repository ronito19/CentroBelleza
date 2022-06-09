package com.example.centrodebellezagala;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;




public class Main8Info extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8_info);
    }

    public void atras8(View view)
    {
        Intent intent = new Intent(this, Main4Menu.class);
        startActivity(intent);
    }


    public void salir8(View view)
    {
        Intent intent = new Intent(this, Main1Logueo.class);
        startActivity(intent);
        FirebaseAuth.getInstance().signOut();
    }

}
