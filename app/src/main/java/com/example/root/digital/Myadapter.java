package com.example.root.digital;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by root on 24/8/17.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {
    List<Model> bookslist;
    Context context;
    public Myadapter(Context context,List<Model> bookslist){
        this.context=context;
        this.bookslist=bookslist;
    }

    @Override
    public Myadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Myadapter.ViewHolder holder, int position) {
        final Model model=bookslist.get(position);
        holder.name.setText(model.getName());
        holder.author.setText(model.getAuthor());
        holder.download.setText(model.getDownload());
        final String string=model.getLink();
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(string));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,author,download;
        public ViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.text1);
            author=(TextView)itemView.findViewById(R.id.text2);
            download=(TextView)itemView.findViewById(R.id.text3);
        }
    }
}
/*
public  class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder>{
    List<Movie> movieList;
    Context context;
   // MainActivity mainActivity=new MainActivity();
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView genere,author,singer;
        public ViewHolder(View itemView) {
            super(itemView);
            genere=(TextView)itemView.findViewById(R.id.text1);
            author=(TextView)itemView.findViewById(R.id.text2);
            singer=(TextView)itemView.findViewById(R.id.text3);
        }

        @Override
        public void onClick(View view) {
            Log.d( "onClick " ,view.toString() );
        }
    }

    public Myadapter(Context context,List<Movie> movieList) {
        this.movieList = movieList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Movie movie=movieList.get(position);
        holder.singer.setText(movie.getSinger());
        holder.author.setText(movie.getAuthor());
        holder.genere.setText(movie.getGener());
        holder.genere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"item clicked at"+position,Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }



 */