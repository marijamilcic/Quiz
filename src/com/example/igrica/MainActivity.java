package com.example.igrica;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Intent in = new Intent (getApplicationContext(), SledecaAktivnost.class);
				startActivity(in);
			}
		}) ;
		
		t.start();
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
