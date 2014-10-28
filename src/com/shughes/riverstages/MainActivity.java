package com.shughes.riverstages;


import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
public class MainActivity extends Activity {
	public final static String EXTRA_TABLE = "com.shughes.riverstages.TABLE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
		String[] riverCities = new String[] {"Greenville", "Vicksburg",
    			"Memphis", "Natchez", "Cape Girardeau", "Helena"};   
    ListView listView = (ListView)findViewById(R.id.mainListView1);
    ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, riverCities);
    listView.setAdapter(adapter);
    
    listView.setOnItemClickListener(new OnItemClickListener() {
    	
    	@Override
    	public void onItemClick(AdapterView<?> l, View v, int position, long id)
    	{//this will need to be changed to a method for calling search
    	//of selected city
    		String s = (String) l.getItemAtPosition(position);
    		Toast.makeText(MainActivity.this, "Getting data for " + s, Toast.LENGTH_SHORT).show();
    		Document doc;
    		String table = null;
    		//Must figure out asynctask to stop runtime error
    		//Shows body of text between tags <pre>
    		try {
    			//Get web site
    			doc = Jsoup.connect("http://www.srh.noaa.gov/lmrfc/?n=lmrfc-mississippiandohioriverforecast").userAgent("Mozilla").get();
    			table = doc.select("pre").text();
    				
 
    		} catch (IOException e) {
                e.printStackTrace();
    			}finally{}
    		//Sends info to DisplayActivity
    		Intent intent = new Intent(MainActivity.this, DisplayResults.class);
    		  //Create the bundle
    		  Bundle bundle = new Bundle();
    		  //Add your data to bundle
    		  bundle.putString(table, table);  
    		  //Add the bundle to the intent
    		  intent.putExtra(EXTRA_TABLE,table);
    		startActivity(intent);
    	}
    	
    });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	}
	


