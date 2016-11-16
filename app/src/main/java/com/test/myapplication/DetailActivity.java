package com.test.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import connections.ImageDownloader;


public class DetailActivity extends ActionBarActivity {

    private Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        b = getIntent().getExtras();

        TextView mediaNameTextView = (TextView)findViewById(R.id.mediaName);
        TextView mediaTypeTextView = (TextView)findViewById(R.id.mediaType);
        TextView mediaPathTextView = (TextView)findViewById(R.id.mediaPath);
        Button openFileButton = (Button)findViewById(R.id.openFileButton);
        ImageView detailImageView = (ImageView)findViewById(R.id.detailImageView);

        mediaNameTextView.setText(b.getString("NAME"));
        mediaTypeTextView.setText(b.getString("TYPE"));
        mediaPathTextView.setText(b.getString("PATH"));

        if(b.getString("TYPE").equals("Image")){
            openFileButton.setVisibility(Button.GONE);
            ImageDownloader imageDownloader = new ImageDownloader(getResources().getString(R.string.media_url) + b.getString("PATH"));
            try {
                Bitmap bitmap = imageDownloader.execute().get();
                detailImageView.setImageBitmap(bitmap);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        if(b.getString("TYPE").equals("PDF")) {
            detailImageView.setImageResource(R.drawable.icon_pdf);
        }

        if(b.getString("TYPE").equals("MOV")) {
            detailImageView.setImageResource(R.drawable.icon_mov);
            /*try {
                String link="http://s1133.photobucket.com/albums/m590/Anniebabycupcakez/?action=view&amp; current=1376992942447_242.mp4";
                //VideoView videoView = (VideoView) findViewById(R.id.videoView);
                MediaController mediaController = new MediaController(this);
                mediaController.setAnchorView(videoView);
                Uri video = Uri.parse(link);
                videoView.setMediaController(mediaController);
                videoView.setVideoURI(video);
                videoView.start();
            } catch (Exception e) {
                // TODO: handle exception
                Toast.makeText(this, "Error connecting", Toast.LENGTH_SHORT).show();
            }*/
        }
    }

    public void onOpeningFile(View view){

        if(b.getString("TYPE").equals("MOV")){
            String url = getResources().getString(R.string.media_url ) + b.getString("PATH");
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(url), "video/mp4");
            startActivity(intent);
        }

        if(b.getString("TYPE").equals("PDF")) {
            String url = getResources().getString(R.string.media_url) + b.getString("PATH");
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }
    }



}
