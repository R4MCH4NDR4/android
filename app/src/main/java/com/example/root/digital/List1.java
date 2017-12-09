package com.example.root.digital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class List1 extends AppCompatActivity implements View.OnClickListener{
    TextView cse,mech,it,ece,eee,civil,aero;
    String urll="http://192.168.0.8/list.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list1);
        cse=(TextView)findViewById(R.id.text1);
        cse.setOnClickListener(this);
        mech=(TextView)findViewById(R.id.text2);
        mech.setOnClickListener(this);
        it=(TextView)findViewById(R.id.text3);
        it.setOnClickListener(this);
        ece=(TextView)findViewById(R.id.text4);
        ece.setOnClickListener(this);
        eee=(TextView)findViewById(R.id.text5);
        eee.setOnClickListener(this);
        civil=(TextView)findViewById(R.id.text6);
        civil.setOnClickListener(this);
        aero=(TextView)findViewById(R.id.text7);
        aero.setOnClickListener(this);
        AndroidNetworking.initialize(getApplicationContext());


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text1 :
                AndroidNetworking.post(urll)
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("loggggg",response.toString());
                                try {
                                    JSONArray jsonArray=response.getJSONArray("result");
                                    for(int i=0;i<jsonArray.length();i++)
                                    {
                                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                                        String name=jsonObject.getString("name");
                                         String author=jsonObject.getString("author");
                                          String link=jsonObject.getString("linkk");
                                        String download="Download";
                                        Model model= new Model(name,author,link,download);
                                        List2 list2=new List2();
                                        list2.bookslist.add(model);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Intent intent=new Intent(List1.this,List2.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.d("loggg",anError.toString());

                            }
                        });
        }

    }
}
/*
 for (int i =0;i<routes.length();i++){
                                JSONObject object = routes.getJSONObject(i);
                                ListModel model = new ListModel();
                                Log.d("LOGGGG",object.getString("StartPoint")+object.getString("ViaPoint"));
                                model.setName(object.getString("StartPoint"));
                                model.setEmail(object.getString("ViaPoint"));
                                list.add(model);
 */