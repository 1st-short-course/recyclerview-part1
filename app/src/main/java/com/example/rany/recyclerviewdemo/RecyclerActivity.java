package com.example.rany.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.rany.recyclerviewdemo.adapter.ArticleAdapter;
import com.example.rany.recyclerviewdemo.callback.OnRecyClerItemClickListener;
import com.example.rany.recyclerviewdemo.model.Article;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity implements OnRecyClerItemClickListener{

    private RecyclerView rcArticle;
    private List<Article> articleList;
    private ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        rcArticle = findViewById(R.id.rcArticle);

        articleList = new ArrayList<>();
        for(int i = 1 ; i <= 50; i++){
            articleList.add(new Article(i, "Article Management "+i,
                    "Description of each Article Management"+i,
                    200, "https://www.fixpocket.com/public_assets/uploads/beats/1525674588536234-1QbGIX1496037475.jpg"));
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcArticle.setLayoutManager(manager);

        adapter = new ArticleAdapter(this);
        rcArticle.setAdapter(adapter);
        adapter.setArticles(articleList);

        adapter.onSetListener(this);

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(RecyclerActivity.this, Detail.class);
        Article article = adapter.getArticle(position);
        intent.putExtra("title", article.getTitle());
        startActivity(intent);
    }

    @Override
    public void onItemDelete(int position) {
        adapter.removeArticle(position);
    }
}
