package com.example.diego.class9v2;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2 extends ListActivity {
    TextView text;
    private ProgressDialog pDialog;
    private static String url = "159.203.230.134/slots.json";

    // JSON Node names
    private static final String TAG_ZONE = "Zona";
    private static final String TAG_LUGARES = "Lugares";
    private static final String TAG_DISPONIBLES = "Disponibles";

    // contacts JSONArray
    JSONArray zonesa = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> zonelist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
      /*  text = (TextView) findViewById(R.id.textView);
        Intent i = getIntent();
        text.setText(i.getStringExtra("Input")); */

        zonelist = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();
    }

    public void onclick(View v) {
        Button button = (Button) v;
        new GetContacts().execute();

    }

    /**
     * Async task class to get json by making HTTP call
     * */

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity2.this);
            pDialog.setMessage("Loading, please wait (or get a better internet connection)...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            JSONParser sh = new JSONParser();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, JSONParser.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    zonesa = jsonObj.getJSONArray(TAG_ZONE);

                    // looping through All Contacts
                    for (int i = 0; i < zonesa.length(); i++) {
                        JSONObject c = zonesa.getJSONObject(i);

                        String zones = c.getString(TAG_ZONE);
                        String lugares = c.getString(TAG_LUGARES);
                        String disponibles = c.getString(TAG_DISPONIBLES);

                        // tmp hashmap for single contact
                        HashMap<String, String> contact = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        contact.put(TAG_ZONE, zones);
                        contact.put(TAG_LUGARES, lugares);
                        contact.put(TAG_DISPONIBLES, disponibles);

                        // adding contact to contact list
                        zonelist.add(contact);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity2.this, zonelist,
                    R.layout.list_item, new String[] { TAG_LUGARES, TAG_DISPONIBLES,}, new int[] { R.id.lugares,R.id.disponibles});

            setListAdapter(adapter);
        }



    }
}
