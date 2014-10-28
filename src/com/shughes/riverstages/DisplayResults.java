package com.shughes.riverstages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayResults extends Activity {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.display_results);
	
	    // TODO Auto-generated method stub
	    Intent intent=getIntent();
	    String table = intent.getStringExtra(MainActivity.EXTRA_TABLE);
	    TextView textView= (TextView)findViewById(R.id.display2);
	    textView.setText(table);
	}


}
