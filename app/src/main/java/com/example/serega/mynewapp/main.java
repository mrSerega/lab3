package com.example.serega.mynewapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class main extends AppCompatActivity {

    private EditText _login, _pass;
    private Switch _remember;
    private Button _log;
    public static SharedPreferences store;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _login = (EditText) findViewById(R.id._login);
        _pass = (EditText) findViewById(R.id._pass);
        _remember = (Switch) findViewById(R.id._sw_remember_me);
        _log = (Button) findViewById(R.id._btn_login);
         store = getPreferences(MODE_PRIVATE);
          final SharedPreferences.Editor editor = store.edit();
          _login.setText(store.getString("_r_login","Login"));

        _log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login, pass, true_pass, data;
                login = _login.getText().toString();
                pass = _pass.getText().toString();
                true_pass = store.getString(login, "");
                data=login+"_data";
                if(true_pass.length()<1){
                    Toast.makeText(main.this, "user not found", Toast.LENGTH_SHORT).show();
                }else {
                    if (pass.equals(true_pass)) {
                        Toast.makeText(main.this, "login is successful", Toast.LENGTH_SHORT).show();
                        if(_remember.isChecked()){
                            editor.putString("_r_login",login);
                            editor.commit();
                        }else{
                            editor.putString("_r_login","Login");
                            editor.commit();
                        }
                    } else {
                        Toast.makeText(main.this, "login fail, try again", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_register) {
            Intent intent = new Intent(this, RegActivity.class);
            startActivity(intent);
            return true;
        }

        if(id == R.id.action_login){
            Intent intent = new Intent(this, main.class);
            startActivity(intent);
            return true;
        }

        if(id == R.id.action_exit){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
