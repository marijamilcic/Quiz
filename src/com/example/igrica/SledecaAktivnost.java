package com.example.igrica;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SledecaAktivnost extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sledecaa);
	
	TextView text = (TextView) findViewById(R.id.textViewSrecanRad);
	
	Button button= (Button) findViewById(R.id.dugmeZapocni);
	button.setOnClickListener(this);
	}
		@Override
		public void onClick(View v) {
			
			Intent act = new Intent(this, AktivnostZapocni.class);
			startActivity(act);
			
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

	@Override
    protected void onStart() {
        super.onStart();
        // Kreirana aktivnost postaje vidljiva.
        
	}
	 @Override
	    protected void onResume() {
	        super.onResume();
	        // Aktivnost je postala vidljiva (sada je u stanju "resumed").
	    }
	    @Override
	    protected void onPause() {
	        super.onPause();
	        // Druga aktivnost uzima fokus (ova aktivnost je sada "paused").
	    }
	    @Override
	    protected void onStop() {
	        super.onStop();
	        // Aktivnost više nije vidljiva (sada je u stanju "stopped").
	    }
	    @Override
	    protected void onDestroy() {
	        super.onDestroy();
	        // Aktivnost se uništava.
	    }
	    
	    
		

}
