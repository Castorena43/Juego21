package com.example.juego21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String url = "http://ramiro174.com/api/numero";

    String urle= "http://ramiro174.com/api/borrar/numero";
    Integer suma = 0;

    private VolleyS volley;
    protected RequestQueue fRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volley = VolleyS.getInstance(this.getApplicationContext());
        fRequestQueue = volley.getRequestQueue();

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn1:
                obtenerNumero();
                break;
            case R.id.btn2:
                enviarNumero();
                break;
            case R.id.btn3:
                Intent i = new Intent(getApplicationContext(),MostrarDatos.class);
                startActivity(i);
                break;
            case R.id.btn4:
                EliminarDatos();
                break;

        }
        //obtenerNumero();
    }

    private void EliminarDatos(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urle, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this, "Se borro correctamente", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponse: ", error.toString());
            }
        });
        fRequestQueue.add(request);
    }

    private void obtenerNumero(){
        JsonObjectRequest request =  new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TextView text = findViewById(R.id.textview1);
                try{
                    Integer num = response.getInt("numero");
                    suma += num;
                    Toast.makeText(MainActivity.this, suma.toString(), Toast.LENGTH_SHORT).show();

                    if(suma > 21){
                        Toast.makeText(MainActivity.this, "Perdiste!", Toast.LENGTH_SHORT).show();
                        text.setText("0");
                        suma = 0;
                        return;
                    }

                    text.setText(suma.toString());

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponse: ", error.toString());
            }
        });

        fRequestQueue.add(request);
    }
    private void enviarNumero(){
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("nombre", "Hector");
            jsonParams.put("numero", suma);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObjectRequest request =  new JsonObjectRequest(Request.Method.POST, "http://ramiro174.com/api/enviar/numero", jsonParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //JSONObject Obj = response;
                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                //return;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponse: ", error.toString());
            }
        });

        fRequestQueue.add(request);
    }
}
