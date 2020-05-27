package com.example.dialogfragmentrecycler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity  implements  MutiChoiceDialog.OnSureListener{

    private Button button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }

    private void showDialog()
    {
        // FragmentManager fragmentManager =  getSupportFragmentManager();
        MutiChoiceDialog mutiChoiceDialog = new MutiChoiceDialog();
        mutiChoiceDialog.setOnSureListener(this);
        mutiChoiceDialog.show(getSupportFragmentManager(),"");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onSureClick(List<Integer> list) {
        StringBuffer selectPosition = new StringBuffer();
        for(Integer postion :list)
        {
            selectPosition.append(postion+",");
        }

        Toast.makeText(this,selectPosition.toString(),Toast.LENGTH_LONG).show();
    }
}
