package com.example.root.digital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class List2 extends AppCompatActivity {
    public static  List<Model> bookslist =new ArrayList<>();
   // public static String namee,author,link;
    RecyclerView recyclerView;
    Myadapter myadapter;
    private  RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        myadapter=new Myadapter(this,bookslist);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myadapter);

    }

   /* public void data() {
        Model model=new Model(namee,author,link);
        bookslist.add(model);
    }*/


}
/*
public class MainActivity extends AppCompatActivity {
    List<Movie> movieList=new ArrayList<>();
    RecyclerView recyclerView;
    Myadapter myadapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        myadapter=new Myadapter(this,movieList);
       // mLayoutManager=new LinearLayoutManager(this);
        mLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(myadapter);
        prepareMovieData();
 */