package com.zeynepmervekoyuncu.instagramclone.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.zeynepmervekoyuncu.instagramclone.databinding.RecyclerRowBinding;
import com.zeynepmervekoyuncu.instagramclone.model.Post;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private final ArrayList<Post> postArrayList;

    public PostAdapter(ArrayList<Post> postArrayList) {
        this.postArrayList = postArrayList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater ile RecyclerRowBinding bağlanıyor
        RecyclerRowBinding binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PostHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Post post = postArrayList.get(position);
        holder.binding.recyclerViewUserEmailText.setText(post.email);
        holder.binding.recyclerViewCommentText.setText(post.comment);
        Picasso.get().load(post.downloadUrl).into(holder.binding.recyclerViewImageView);
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    // ViewHolder sınıfı
    static class PostHolder extends RecyclerView.ViewHolder {
        private final RecyclerRowBinding binding;

        public PostHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
