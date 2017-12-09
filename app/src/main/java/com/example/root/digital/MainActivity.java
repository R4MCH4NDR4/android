package com.example.root.digital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  {
    EditText editText,editText1;
    TextView textView;
    Button button;
    String url="http://192.168.0.8/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.edit1);
        editText1=(EditText)findViewById(R.id.edit2);
        textView=(TextView)findViewById(R.id.textView2);
        button=(Button)findViewById(R.id.button);
        AndroidNetworking.initialize(getApplicationContext());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1,s2;
                s1=editText.getText().toString();
                s2=editText1.getText().toString();
                if(s1.equals("")||s2.equals("")){
                    Toast.makeText(getApplicationContext(),"fill all filelds",Toast.LENGTH_LONG).show();
                }
                else {
                    AndroidNetworking.post(url)
                            .addBodyParameter("email",s1)
                            .addBodyParameter("password",s2)
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("logdgsgj",response.toString());
                                    try {
                                        JSONArray json=response.getJSONArray("server");
                                        for(int i=0;i<=json.length();i++)
                                        {
                                            JSONObject object=json.getJSONObject(i);
                                            String code=object.getString("code");
                                            String message=object.getString("message");
                                            if(code.equals("login_true")){
                                                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                                            }
                                            if(message.equals("login_fail")){
                                                Toast.makeText(getApplicationContext(),"invalid user name or pass",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    Intent intent=new Intent(MainActivity.this,List1.class);
                                    startActivity(intent);

                                }

                                @Override
                                public void onError(ANError anError) {

                                }
                            });
                }
            }
        });
    }
}
