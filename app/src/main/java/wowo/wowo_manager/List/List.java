package wowo.wowo_manager.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import wowo.wowo_manager.R;


public class List extends Activity {

    private ListView mListView = null;
    private ListViewAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

/*        Intent intent = getIntent(); // TODO: 2016-12-14 서버에서 가져오는 것으로 바꿔야함
        String pushTitle = intent.getStringExtra("push_title");
        String pushBody = intent.getStringExtra("push_body");

        mListView = (ListView) findViewById(R.id.mList);
        mAdapter = new ListViewAdapter(this);
        mListView.setAdapter(mAdapter);

        mAdapter.addItem(pushTitle, pushBody);*/
    }


    public void ClickAddBtn() {
        Intent intent = new Intent(List.this, wowo.wowo_manager.Push.PushActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}