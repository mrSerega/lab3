package com.example.serega.mynewapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegActivity extends AppCompatActivity {

    private EditText _new_login, _new_first_pass, _new_second_pass;
    private Button _reg;
    private SharedPreferences store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        _reg = (Button) findViewById(R.id._btn_register);
        _new_login = (EditText) findViewById(R.id._new_login);
        _new_first_pass = (EditText) findViewById(R.id._pass);
        _new_second_pass = (EditText) findViewById(R.id._pass_second);

        _reg.setOnClickListener(new View.OnClickListener(){
                                   @Override
                               public void onClick(View v){
                                       store = main.store;
                                       boolean tag1,tag2,tag3;
                                       tag1=true;
                                       tag2=true;
                                       tag3=true;

                                       String login_str = _new_login.getText().toString();
                                       if(login_str.length()<1){
                                           Toast.makeText(RegActivity.this,"so short login", Toast.LENGTH_SHORT).show();
                                           tag1=false;
                                       }
                                       String res=store.getString(login_str, "");
                                       if(res.length()>0){
                                           Toast.makeText(RegActivity.this,"this login already exists", Toast.LENGTH_SHORT).show();
                                           tag2=false;
                                       }
                                       String fpass = _new_first_pass.getText().toString();
                                       if(fpass.length()<1){
                                           Toast.makeText(RegActivity.this, "too short pass", Toast.LENGTH_SHORT).show();
                                       }
                                       String spass = _new_second_pass.getText().toString();
                                       if(!fpass.equals(spass)){
                                           Toast.makeText(RegActivity.this,"Passwords do not match", Toast.LENGTH_SHORT).show();
                                           tag3=false;
                                       }
                                       if(tag1&&tag2&&tag3){
                                           SharedPreferences.Editor editor = store.edit();
                                            editor.putString(login_str,fpass); //лишь одно значение
                                            editor.commit();//коммит, на всякий записать в onDestroy()
                                           Toast.makeText(RegActivity.this,"register is successful", Toast.LENGTH_SHORT).show();
                                       }
                                    }
                                }

        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

    /*
        if (id == R.id.action_register) {
            Intent intent = new Intent(this, RegActivity.class);
            startActivity(intent);
            return true;
        }
        */

        if(id == R.id.action_login){
            Intent intent = new Intent(this, main.class);
            startActivity(intent);
            return true;
        }

        if(id == R.id.action_exit){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
