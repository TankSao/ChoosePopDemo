package com.example.administrator.choosepopdemo.Utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.choosepopdemo.MainActivity;
import com.example.administrator.choosepopdemo.R;

import java.util.ArrayList;
import java.util.List;

public class MyListViewAdapter extends BaseAdapter {
    private Context mContext;
    private String flag;
    private List<String> mList;
    public MyListViewAdapter(Context mContext, ArrayList<String> mList, String flag) {
        this.flag = flag;
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        //科目选择
        if(flag.equals("km")||flag.equals("nj")){
            return  mList == null?0:mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        //课程大纲 年级  出版社
         if (flag.equals("nj") || flag.equals("km")) {
                return mList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            //年级  出版社 科目
            if (flag.equals("nj")||flag.equals("km")) {
                convertView = View.inflate(mContext, R.layout.tv_item, null);
            }
            viewHolder = new ViewHolder(convertView);
            assert convertView != null;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        if(flag.equals("km")||flag.equals("nj")){
            viewHolder.name.setText(mList.get(position));
        }
        return convertView;
    }

    private class ViewHolder {
        TextView name;
        ViewHolder(View view) {
            if (flag.equals("nj")||flag.equals("km")) {
                name = view.findViewById(R.id.text_tv);
            }
        }
    }
}