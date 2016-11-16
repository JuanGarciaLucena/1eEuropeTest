package connections;

import android.app.Activity;
import android.os.AsyncTask;

import com.test.myapplication.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import adapters.MediaListAdapter;
import objects.Media;
import test.XMLParser;

/**
 * Created by juanlucena on 14/08/15.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class GetXML extends AsyncTask<String, Void, List<Media>>{


    private List<Media> mediaList = new ArrayList<Media>();
    private Activity activity;

    public GetXML(Activity activity){
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Media> doInBackground(String... params) {

        URL url;
        try {
            url = new URL(activity.getResources().getString(R.string.main_url));
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("Media");

            if (nodeList != null && nodeList.getLength() > 0){
                for(int i = 0; i < nodeList.getLength(); i++){

                    Node nNode = nodeList.item(i);
                    System.out.println("\nCurrent Element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;

                        String mName = eElement.getElementsByTagName("Name").item(0).getTextContent();
                        String mObjectType = eElement.getElementsByTagName("ObjectType").item(0).getTextContent();
                        String mPath = eElement.getElementsByTagName("Path").item(0).getTextContent();

                        Media m = new Media(mName, mObjectType, mPath);
                        mediaList.add(m);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mediaList;
    }

    @Override
    protected void onPostExecute(List<Media> res) {



    }
}

