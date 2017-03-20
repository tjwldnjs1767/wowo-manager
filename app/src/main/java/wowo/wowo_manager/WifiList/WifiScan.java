package wowo.wowo_manager.WifiList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import wowo.wowo_manager.Push.PushActivity;
import wowo.wowo_manager.R;

public class WifiScan extends ListActivity {

    private WifiManager wifiManager;
    private List<ScanResult> scanResult;
    private String bssid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifi_scan_list);

        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        ArrayList<String> list = refresh();

        final ListView lv = (ListView)findViewById(android.R.id.list);

        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
        lv.setOnItemClickListener(new ListViewItemClickListener());
    }


    private ArrayList<String> refresh() {
        scanResult = wifiManager.getScanResults();
        ArrayList<String> list = new ArrayList<>();

        for (ScanResult result : scanResult) {
            if(result.SSID.length() != 0)
                list.add(result.SSID);
        }
        //scanResult.clear();

        return list;
    }

    private class ListViewItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String ssid = ((TextView) view).getText().toString();

            Log.d ("scan", String.valueOf(scanResult.size()));
            for(ScanResult result : scanResult){
                if(result.SSID.equals(ssid)){
                    bssid = result.BSSID;
                }
            }

            Intent intent = new Intent(WifiScan.this, wowo.wowo_manager.Push.PushActivity.class);
            intent.putExtra("bssid",  bssid);
            startActivity(intent);
            finish();
        }
    }
}