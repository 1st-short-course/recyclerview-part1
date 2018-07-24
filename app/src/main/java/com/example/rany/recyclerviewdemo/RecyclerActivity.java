package com.example.rany.recyclerviewdemo;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rany.recyclerviewdemo.adapter.ArticleAdapter;
import com.example.rany.recyclerviewdemo.callback.OnRecyClerItemClickListener;
import com.example.rany.recyclerviewdemo.model.Article;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;

public class RecyclerActivity extends AppCompatActivity implements OnRecyClerItemClickListener{

    private static final String TAG = "RecyclerActivity";
    private RecyclerView rcArticle;
    private List<Article> articleList;
    private ArticleAdapter adapter;
    public static final int ADD_NEW_ARTICLE = 99;
    private EditText search;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        rcArticle = findViewById(R.id.rcArticle);
        search = findViewById(R.id.edtSearch);


        // *****************************************************
        // ---------- In case, when you click on close and it doesn't show all article
//        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//                return false;
//            }
//        });
        // *****************************************************

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                searchArticleTitle(search.getText().toString());
                return false;
            }
        });

        articleList = new ArrayList<>();
        for(int i = 1 ; i <= 50; i++){
            articleList.add(new Article(i, "Article Management "+i,
                    "Description of each Article Management"+i,
                    200, "https://www.fixpocket.com/public_assets/uploads/beats/1525674588536234-1QbGIX1496037475.jpg"));
        }

       final GridLayoutManager manager = new GridLayoutManager(this, 2);
       // final LinearLayoutManager manager = new LinearLayoutManager(this);

        rcArticle.setLayoutManager(manager);

        adapter = new ArticleAdapter(this);
        rcArticle.setAdapter(adapter);

        //================= Apply animation =================
        SlideInRightAnimationAdapter animation = new SlideInRightAnimationAdapter(adapter);
        animation.setDuration(1000);
        animation.setInterpolator(new LinearInterpolator());
        animation.setFirstOnly(false);
        rcArticle.setAdapter(animation);
        //======================================================
        adapter.setArticles(articleList);
        adapter.onSetListener(this);


//        rcArticle.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if(dy > 0){
//                    //rcArticle.setAdapter(new SlideInRightAnimationAdapter(adapter));
//                }
//                else{
//                    //rcArticle.setAdapter(new SlideInLeftAnimationAdapter(adapter));
//                }
//            }
//        });

    }

    private void searchArticleTitle(String title){
        // store matching article
        List<Article> articles = new ArrayList<>();
        adapter.clearData();
        for(Article article : articleList){
            if(article.getTitle().toLowerCase().contains(title.toLowerCase())){
                articles.add(article);
            }
        }
        adapter.setArticles(articles);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu, menu);
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.itm_search));
        SearchManager manager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itm_addNew:{
                Intent intent = new Intent(this, AddArticleActivity.class);
                startActivityForResult(intent, ADD_NEW_ARTICLE);
                break;
            }
            case R.id.itm_search:{
                searchArticle();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void searchArticle() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                List<Article> articles = new ArrayList<>();
                adapter.clearData();
                for(Article article : articleList){
                    if(article.getTitle().toLowerCase().contains(newText.toLowerCase())){
                        articles.add(article);
                    }
                }
                adapter.setArticles(articles);
                return false;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_NEW_ARTICLE && resultCode == RESULT_OK && data != null){
            Article article = data.getParcelableExtra("article");
            Log.e(TAG, "onActivityResult: "+ article.toString());

            articleList.add(0,article);
            this.adapter.addNewArticle(article);
            rcArticle.smoothScrollToPosition(0);

        }

    }
}
