package com.example.deathcode.goalkicker;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView bookList;
    BookAdapter bookAdapter;
    List<BookModel> bookModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookList = findViewById(R.id.booklist);
        bookModels = new ArrayList<>();
        bookAdapter = new BookAdapter(bookModels, this);
        bookList.setLayoutManager(new GridLayoutManager(this, 3));
        bookList.setAdapter(bookAdapter);
        bookList.addItemDecoration(new ItemSpaceDecoration(20));
        bookList.setItemAnimator(new DefaultItemAnimator());
        new LoadBooks().execute("http://goalkicker.com");

        bookList.addOnItemTouchListener(new BookAdapter.RecylerTapListener(this, bookList, new BookAdapter.OnClick() {
            @Override
            public void onTap(View view, int position) {
                Toast.makeText(MainActivity.this, bookModels.get(position).getBookTitle(), Toast.LENGTH_LONG).show();
            }
        }));
    }

    class LoadBooks extends AsyncTask<String, Boolean, Boolean>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            String url = strings[0];
            try {
                Document doc = Jsoup.connect(url).get();
                Elements books = doc.getElementsByClass("bookContainer grow");
                for(Element book:books){
                    BookModel bookModel = new BookModel(book);
                    Log.e("DATA", book.text());
                    bookModels.add(bookModel);
                }
                return true;
            }catch (IOException ex){
                ex.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean && bookModels.size()>0){
                bookAdapter.notifyDataSetChanged();
            }
        }
    }
}
