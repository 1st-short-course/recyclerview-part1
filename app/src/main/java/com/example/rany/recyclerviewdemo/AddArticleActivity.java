package com.example.rany.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rany.recyclerviewdemo.model.Article;

import java.util.ArrayList;
import java.util.List;

public class AddArticleActivity extends AppCompatActivity {

    private Button btnAdd;
    private EditText title, des, view, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        initView();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get value from Edittext
                String art_title = title.getText().toString();
                String art_des = des.getText().toString();
                int art_view = Integer.parseInt(view.getText().toString());
                String art_image = image.getText().toString();
                // generate id for article
                int id = (int) (System.currentTimeMillis()/1000);

//                ArrayList<Article> articleList = new ArrayList<>();
//                articleList.add(new Article(id, art_title, art_des, art_view, art_image));

                Article article = new Article(id, art_title, art_des, art_view, art_image);


                Intent intent = new Intent();
                intent.putExtra("article", article);
//                intent.putParcelableArrayListExtra("article", articleList);
                setResult(RESULT_OK, intent);

                finish();
            }
        });

    }

    private void initView() {
        btnAdd = findViewById(R.id.btnAdd);
        title = findViewById(R.id.edtTitle);
        des = findViewById(R.id.edtDescription);
        view = findViewById(R.id.edtView);
        image = findViewById(R.id.edtImage);
    }
}
