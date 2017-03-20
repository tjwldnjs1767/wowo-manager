package wowo.wowo_manager.Push;

import android.app.Activity;
import android.content.Intent;
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
import wowo.wowo_manager.R;
import wowo.wowo_manager.Server.ServerRequest;


public class PushActivity extends Activity {

    String id;
    Button inputBssid, saveBtn;
    List<NameValuePair> params;
    String bssid, pushTitleText, pushBodyText;
    String[] bssidArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_push_context);
        Intent intent = getIntent();

        saveBtn = (Button) findViewById(R.id.saveBtn);
        inputBssid = (Button) findViewById(R.id.inputBssid);
        bssid = intent.getStringExtra("bssid");
        if (bssid != null) {
            inputBssid.setText(bssid);
        }
    }

    public void clickSaveBtn(View v) {
        EditText inputPushTitle = (EditText) findViewById(R.id.inputPushTitle);
        EditText inputPushBody = (EditText) findViewById(R.id.inputPushBody);
        pushTitleText = inputPushTitle.getText().toString();
        pushBodyText = inputPushBody.getText().toString();

        SaveValues saveValues = (SaveValues) getApplicationContext();
        id = saveValues.getId();
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", id));

        bssidArr = bssid.split(":");

        for (int i = 0; i < bssidArr.length; i++) {
            params.add(new BasicNameValuePair("bssid" + (i + 1), bssidArr[i]));
        }

        params.add(new BasicNameValuePair("push_title", pushTitleText));
        params.add(new BasicNameValuePair("push_body", pushBodyText));

        ServerRequest sr = new ServerRequest();
        JSONObject json = sr.getJSON("http://59.26.68.181:3001/push", params);

        if (json != null) {
            try {
                String jsonstr = json.getString("response");
                Toast.makeText(getApplication(), jsonstr, Toast.LENGTH_LONG).show();

                if (json.getBoolean("res")) {
                    Intent intent = new Intent(PushActivity.this, wowo.wowo_manager.List.List.class);
                    intent.putExtra("push_title", inputPushTitle.getText().toString());
                    intent.putExtra("push_body", inputPushBody.getText().toString());
                    startActivity(intent);
                    finish();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void clickBssidBtn(View v) {

        Intent intent = new Intent(PushActivity.this, wowo.wowo_manager.WifiList.WifiScan.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PushActivity.this, wowo.wowo_manager.List.List.class);
        startActivity(intent);
        finish();
    }
}