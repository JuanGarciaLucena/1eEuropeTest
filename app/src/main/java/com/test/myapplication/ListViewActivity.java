package com.test.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import adapters.MediaListAdapter;
import connections.GetXML;
import objects.Media;


public class ListViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        GetXML getXML = new GetXML(ListViewActivity.this);

        try{
            List<Media> lMedia = getXML.execute().get();
            ListView mediaList = (ListView)findViewById(R.id.mediaList);
            MediaListAdapter mediaListAdapter = new MediaListAdapter(ListViewActivity.this, lMedia);
            mediaList.setAdapter(mediaListAdapter);

            mediaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Object o = parent.getAdapter().getItem(position);
                    Media m = (Media) o;

                    String name = m.getName();
                    String mediaType = m.getObjectType();
                    String mediaPath = m.getPath();

                    Bundle bundle = new Bundle();
                    bundle.putString("NAME", name);
                    bundle.putString("TYPE", mediaType);
                    bundle.putString("PATH", mediaPath);

                    Intent i = new Intent();
                    i.setClass(ListViewActivity.this, DetailActivity.class);
                    i.putExtras(bundle);
                    startActivity(i);



                }
            });

        }catch(Exception e){
            e.printStackTrace();
        }




    }


}
