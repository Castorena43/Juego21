package com.example.juego21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MostrarDatos extends AppCompatActivity {

    String url = "http://ramiro174.com/api/obtener/numero";
    private VolleyS volley;
    protected RequestQueue fRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_persona);
        volley = VolleyS.getInstance(this.getApplicationContext());
        fRequestQueue = volley.getRequestQueue();
        obtenerDatos();
    }


    private void obtenerDatos(){
        JsonObjectRequest request =  new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{

                   ArrayList<Persona> ListaPersona;
                    Type talp = new TypeToken<ArrayList<Persona>>(){}.getType();
                    String p = response.getString("resultados");
                    ListaPersona = new Gson().fromJson(p,talp);
                    AdaptadorPersona ap = new AdaptadorPersona(ListaPersona);
                    RecyclerView mylista = findViewById(R.id.recyclepersona);
                    mylista.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    mylista.setAdapter(ap);


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

}
