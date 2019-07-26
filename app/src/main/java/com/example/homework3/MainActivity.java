package com.example.homework3;

import android.content.Context;
import android.content.Intent;
import android.net.http.HttpResponseCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.* ;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.net.Proxy.Type.HTTP;


public class MainActivity extends AppCompatActivity {

    private ArrayList<DATA_LINE> datas = new ArrayList<DATA_LINE>() ;
    private String load_php = "http://10.0.2.2/android_use/load_icon_partice.php" ;
    private String database_return ;
    public String tem1 , tem2 ;

    private ListView listview ;
    private LayoutInflater inflater ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread(runnable) ;
        thread.start() ;

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            HttpClient httpClient = new DefaultHttpClient() ;
            HttpPost httpPost = new HttpPost(load_php);
            HttpResponse httpResponse;

            try {
                httpResponse = httpClient.execute(httpPost) ;
                HttpEntity resEntity = httpResponse.getEntity();
                database_return = EntityUtils.toString(resEntity);
            } catch (Exception e) {
                Log.e("ERROR : ", e.toString());
            }

            try {
                JSONArray jary = new JSONArray(database_return);
                for (int i = 0; i < jary.length(); i++) {
                    JSONObject jobt = jary.getJSONObject(i);
                    tem1 = jobt.getString("url") ;
                    tem2 = jobt.getString("name") ;
                    DATA_LINE data_line = new DATA_LINE(tem1,tem2) ;
                    datas.add(data_line) ;
                }
            }catch ( JSONException e) {
                e.printStackTrace() ;
            }

            runOnUiThread(uithread) ;
        }
    } ;

    private Runnable uithread = new Runnable() {
        public void run() {
            listview = findViewById(R.id.listview) ;
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
            listview_adapter adapter = new listview_adapter(MainActivity.this,datas,inflater) ;
            listview.setAdapter(adapter) ;
            listview.setOnItemClickListener(listviewOnClick);
        }
    } ;

    private AdapterView.OnItemClickListener listviewOnClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this,"amber"+(position+1),Toast.LENGTH_SHORT).show() ;
        }
    } ;

}
