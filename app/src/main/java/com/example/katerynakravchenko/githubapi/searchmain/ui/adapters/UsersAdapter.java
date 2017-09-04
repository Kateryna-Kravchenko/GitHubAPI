package com.example.katerynakravchenko.githubapi.searchmain.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katerynakravchenko.githubapi.R;
import com.example.katerynakravchenko.githubapi.entities.User;
import com.example.katerynakravchenko.githubapi.libs.base.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by katerynakravchenko on 31.07.17.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    private List<User> userList;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;

    public UsersAdapter(List<User> userList, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        this.userList = userList;
        this.imageLoader = imageLoader;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User currentUser = userList.get(position);
        imageLoader.load(holder.img_user, currentUser.getAvatarUrl());

        holder.tv_user_name.setText(currentUser.getLogin());
        holder.tv_user_description.setText(currentUser.getScore().toString());

        holder.setOnItemClickListener(currentUser, onItemClickListener);
    }

    public void setUsers(List<User> users) {
        this.userList = users;

        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.img_user)
        CircleImageView img_user;

        @Bind(R.id.tv_user_name)
        TextView tv_user_name;

        @Bind(R.id.tv_user_description)
        TextView tv_user_description;


        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }

        public void setOnItemClickListener(final User currentUser, final OnItemClickListener onItemClickListener) {

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(currentUser);
                }
            });


        }
    }
}
