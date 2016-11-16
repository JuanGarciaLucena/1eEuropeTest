package adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.myapplication.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import connections.ImageDownloader;
import objects.Media;

/**
 * Created by juanlucena on 14/08/15.
 */
public class MediaListAdapter extends BaseAdapter{

    private Activity activity;
    private List<Media> lMedia;

    public MediaListAdapter(Activity activity, List<Media> lMedia){
        this.activity = activity;
        this.lMedia = lMedia;
    }

    @Override
    public int getCount() {
        return lMedia.size();
    }

    @Override
    public Object getItem(int position) {
        return lMedia.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null){
            LayoutInflater inflator = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflator.inflate(R.layout.adapter_media_list_view, null);
        }

        TextView mediaName = (TextView)view.findViewById(R.id.mediaName);
        mediaName.setText(lMedia.get(position).getName());
        ImageView mediaThumbnail = (ImageView)view.findViewById(R.id.mediaThumbnail);

        if(lMedia.get(position).getObjectType().equals("Image")){

            try {
                ImageDownloader imageDownloader = new ImageDownloader(activity.getResources().getString(R.string.media_url) + lMedia.get(position).getPath());
                Bitmap bitmap = imageDownloader.execute().get();
                mediaThumbnail.setImageBitmap(bitmap);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        if(lMedia.get(position).getObjectType().equals("PDF")){
            mediaThumbnail.setImageResource(R.drawable.icon_pdf);
        }

        if(lMedia.get(position).getObjectType().equals("MOV")){
            mediaThumbnail.setImageResource(R.drawable.icon_mov);
        }

        return view;
    }

}
