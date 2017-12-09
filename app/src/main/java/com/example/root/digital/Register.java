package com.example.root.digital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {
    EditText editText1,editText2,editText3,editText4;
    Button button;
     String url="http://192.168.0.8/reg.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editText1=(EditText)findViewById(R.id.edit1);
        editText2=(EditText)findViewById(R.id.edit2);
        editText3=(EditText)findViewById(R.id.edit3);
        editText4=(EditText)findViewById(R.id.edit4);
        button=(Button)findViewById(R.id.button);
        AndroidNetworking.initialize(getApplicationContext());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editText1.getText().toString();
                String email=editText2.getText().toString();
                String pass=editText3.getText().toString();
                String cpass=editText4.getText().toString();
                if(name.equals("")||email.equals("")||pass.equals("")||cpass.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"please fill all the fields",Toast.LENGTH_LONG).show();
                }
                else  if (!(pass.equals(cpass)))
                {
                    Toast.makeText(getApplicationContext(),"password not matched",Toast.LENGTH_LONG).show();
                    editText3.setText(null);
                    editText4.setText(null);
                }
                else
                {
                    AndroidNetworking.post(url)
                            .addBodyParameter("name",name)
                            .addBodyParameter("email",email)
                            .addBodyParameter("password",pass)
                            .addBodyParameter("cpassword",cpass)
                            .setPriority(Priority.MEDIUM)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("loggg",response.toString());
                                    try {
                                        JSONArray jsonArray=response.getJSONArray("server");
                                        for(int i=0;i<=jsonArray.length();i++){
                                            JSONObject object=jsonArray.getJSONObject(i);
                                            Log.d("loggg",object.toString());
                                            String message=object.getString("message");
                                            String code=object.getString("code");
                                            if(code.equals("reg-fail")){
                                                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                                            }
                                            if(code.equals("sucess")){
                                                Toast.makeText(getApplicationContext(),"register sucessfully",Toast.LENGTH_LONG).show();
                                            }
                                            else {
                                                Toast.makeText(getApplicationContext(),"some error",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        //Intent intent=new Intent(Register.this,MainActivity.class);
                                        //artActivity(intent);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    Intent intent=new Intent(Register.this,MainActivity.class);
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
/*Log.i("LOGG","log"+response.toString());
        int j = response.length();
        JSONObject json;
        for(int i=0;i<=j;i++){
        try {
        json =response.getJSONObject(i);
        Log.i("LOGG","json"+json.toString());
        if (json.has("failed")){
        Toast.makeText(getApplicationContext(),"Invalid Details",Toast.LENGTH_SHORT).show();
        }else if(json.has("success")) {
        Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
        } else {
        Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_SHORT).show();
        }*/