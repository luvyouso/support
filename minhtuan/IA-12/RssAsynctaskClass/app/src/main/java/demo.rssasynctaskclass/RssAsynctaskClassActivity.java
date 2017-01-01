package demo.rssasynctaskclass;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class RssAsynctaskClassActivity extends ListActivity {

    private List<String> headlines;
    private List<String> links;
    private ArrayAdapter adapter;

    private class VerySlowTask extends AsyncTask<String, String, Void> {

        private final ProgressDialog dialog = new ProgressDialog(RssAsynctaskClassActivity.this);
        String waitMsg = "Wait\nSome SLOW job is being done... ";

        protected void onPreExecute(){
            this.dialog.setMessage(waitMsg);
            this.dialog.setCancelable(false);
            this.dialog.show();
        }

        protected Void doInBackground(final String... args){
            try {
                URL url = new URL("http://feeds.pcworld.com/pcworld/latestnews");

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();

                // We will get the XML from an input stream
                xpp.setInput(getInputStream(url), "UTF_8");

                /* We will parse the XML content looking for the "<title>" tag which appears inside the "<item>" tag.
                 * However, we should take in consideration that the rss feed name also is enclosed in a "<title>" tag.
                 * As we know, every feed begins with these lines: "<channel><title>Feed_Name</title>...."
                 * so we should skip the "<title>" tag which is a child of "<channel>" tag,
                 * and take in consideration only "<title>" tag which is a child of "<item>"
                 *
                 * In order to achieve this, we will make use of a boolean variable.
                */
                boolean insideItem = false;

                // Returns the type of current event: START_TAG, END_TAG, etc..
                int eventType = xpp.getEventType();
                String HeadLines = "";
                String Links = "";
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {

                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                        } else if (xpp.getName().equalsIgnoreCase("title")) {
                            if (insideItem) {
                                HeadLines = xpp.nextText().toString();//extract the headline
                            }
                        } else if (xpp.getName().equalsIgnoreCase("link")) {
                            if (insideItem) {
                                Links = xpp.nextText().toString();//extract the link of article
                            }
                        }
                    }else if(eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
                        insideItem=false;
                        Thread.sleep(1000);
                        publishProgress(HeadLines, Links);
                    }

                    eventType = xpp.next(); //move to next element
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        // periodic updates - it is OK to change UI
        @Override
        protected void onProgressUpdate(String... value){
            super.onProgressUpdate(value);
            adapter.notifyDataSetChanged();
            headlines.add(value[0]);
            links.add(value[1]);
        }

        // can use UI thread here
        protected void onPostExecute(final Void unused){
            if(this.dialog.isShowing())
                this.dialog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing instance variables
        headlines = new ArrayList();
        links = new ArrayList();

        // Binding data
        adapter = new ArrayAdapter(RssAsynctaskClassActivity.this, android.R.layout.simple_list_item_1, headlines);
        setListAdapter(adapter);

        new VerySlowTask().execute("RSS");
    }

    public InputStream getInputStream(URL url) {
        InputStream is = null;
        try {
            is = url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        } finally {
            return is;
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Uri uri = Uri.parse(links.get(position));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
