package wowo.wowo_manager.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import wowo.wowo_manager.R;
import wowo.wowo_manager.Server.ServerRequest;


public class RegisterActivity extends Activity {
    EditText id, password, workPlaceName, phoneNum;
    Button login, register;
    String idText, passwordText, workPlaceNameText, phoneNumText;
    List<NameValuePair> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        login = (Button) findViewById(R.id.loginbtn);
        register = (Button) findViewById(R.id.signUpbtn);
        id = (EditText) findViewById(R.id.id);
        password = (EditText) findViewById(R.id.password);
        workPlaceName = (EditText) findViewById(R.id.workPlaceName);
        phoneNum = (EditText) findViewById(R.id.phoneNum);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                idText = id.getText().toString();
                passwordText = password.getText().toString();
                workPlaceNameText = workPlaceName.getText().toString();
                phoneNumText = phoneNum.getText().toString();
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("id", idText));
                params.add(new BasicNameValuePair("password", passwordText));
                params.add(new BasicNameValuePair("work_place_name", workPlaceNameText));
                params.add(new BasicNameValuePair("phone_num", passwordText));

                ServerRequest sr = new ServerRequest();
                JSONObject json = sr.getJSON("http://59.26.68.181:3001/register", params);

                if (json != null) {
                    try {
                        String jsonstr = json.getString("response");
                        Toast.makeText(getApplication(), jsonstr, Toast.LENGTH_LONG).show();

                        if (json.getBoolean("res")) {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}