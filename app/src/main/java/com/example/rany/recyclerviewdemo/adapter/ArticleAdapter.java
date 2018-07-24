package com.example.rany.recyclerviewdemo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.example.rany.recyclerviewdemo.R;
import com.example.rany.recyclerviewdemo.callback.OnRecyClerItemClickListener;
import com.example.rany.recyclerviewdemo.model.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends
        RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private Context context;
    private List<Article> articleList;
    private OnRecyClerItemClickListener listener;

    public ArticleAdapter(Context context) {
        this.context = context;
        articleList = new ArrayList<>();
    }

    public void setArticles(List<Article> articles){
        this.articleList.addAll(articles);
        notifyDataSetChanged();
    }

    public void clearData(){
        this.articleList.clear();
        notifyDataSetChanged();
    }

    public void addNewArticle(Article article){
        this.articleList.add(0,article);
        notifyDataSetChanged();
    }

    public Article getArticle(int position){
        return this.articleList.get(position);
    }

    public void removeArticle(int position){
        this.articleList.remove(position);
        notifyItemChanged(position);
    }

    public void onSetListener(OnRecyClerItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.article_item, parent, false
        );
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, final int position) {
        final Article article = articleList.get(position);
        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        holder.viewCount.setText(String.valueOf(article.getViewCount()));
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground);
        Glide.with(context)
                .load(article.getImage())
                .apply(options)
                .into(holder.imvArticle);

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder{

        private ImageView imvArticle, icDetail, icDelete;
        private TextView title, description, viewCount;

        public ArticleViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvArtTitle);
            description = itemView.findViewById(R.id.tvArtDes);
            viewCount = itemView.findViewById(R.id.tvArtView);
            imvArticle = itemView.findViewById(R.id.imvArticle);
            icDetail = itemView.findViewById(R.id.icDetail);
            icDelete = itemView.findViewById(R.id.icDelete);

            icDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
            icDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemDelete(getAdapterPosition());
                }
            });

        }
    }

}
