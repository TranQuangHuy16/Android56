package com.example.android56_day5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    private ArrayList<User> mListData = new ArrayList<>();

    public UserAdapter(ArrayList<User> mListData) {
        this.mListData = mListData;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_user, parent, false);
        } else {
            view = convertView;
        }

        ImageView imgAvatar = view.findViewById(R.id.imgAvatar);
        TextView tvUserName = view.findViewById(R.id.tvUserName);
        TextView tvPhoneNumber = view.findViewById(R.id.tvPhoneNumber);

        User user = mListData.get(position);
        String userName = user.getUserName();
        String phoneNumber = user.getPhoneNumber();
        String avatarUrl = user.getAvatar();

        Glide.with(parent.getContext())
                .load(avatarUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(imgAvatar);
        tvUserName.setText(userName);
        tvPhoneNumber.setText(phoneNumber);

        return view;
    }
}
