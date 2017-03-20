package wowo.wowo_manager.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import wowo.wowo_manager.R;

public class ListViewAdapter extends BaseAdapter {
    private Context mContext = null;
    public ArrayList<ListData> mListData = new ArrayList<ListData>();

    public ListViewAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(String pushTitle, String pushBody) {
        ListData addInfo = new ListData();
        addInfo.mTitle = pushTitle;
        addInfo.mBody = pushBody;

        mListData.add(addInfo);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listitem, null);

            holder.pushTitle = (TextView) convertView.findViewById(R.id.mPushTitle);
            holder.pushBody = (TextView) convertView.findViewById(R.id.mPushBody);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ListData mData = mListData.get(position);

        holder.pushTitle.setText(mData.mTitle);
        holder.pushBody.setText(mData.mBody);
        return convertView;
    }
}