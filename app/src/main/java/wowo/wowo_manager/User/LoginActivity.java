package wowo.wowo_manager.User;

/**
 * Created by dsm037 on 2016-11-11.
 */

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import wowo.wowo_manager.Data.SaveValues;
import wowo.wowo_manager.Push.PushActivity;
import wowo.wowo_manager.R;
import wowo.wowo_manager.Server.ServerRequest;


public class LoginActivity extends Activity {
    EditText id, password;
    Button login, register;
    String idText, passwordText;
    List<NameValuePair> params;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login = (Button) findViewById(R.id.loginbtn);
        id = (EditText) findViewById(R.id.id);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginbtn);
        register = (Button) findViewById(R.id.register);

        pref = getSharedPreferences("AppPref", MODE_PRIVATE);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent regactivity = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(regactivity);
                finish();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                idText = id.getText().toString();
                passwordText = password.getText().toString();
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("id", idText));
                params.add(new BasicNameValuePair("password", passwordText));
                ServerRequest sr = new ServerRequest();
                JSONObject json = sr.getJSON("http://59.26.68.181:3001/login", params);

                if (json != null) {
                    try {
                        String jsonstr = json.getString("response");
                        Toast.makeText(getApplication(), jsonstr, Toast.LENGTH_LONG).show();

                        if (json.getBoolean("res")) {
                            // TODO: 2016-11-24 application
                            SaveValues saveValues = (SaveValues) getApplicationContext();
                            saveValues.setId(idText);
                            Intent profactivity = new Intent(LoginActivity.this, wowo.wowo_manager.Push.PushActivity.class);
                            startActivity(profactivity);
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}