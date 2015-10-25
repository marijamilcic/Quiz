package com.example.igrica;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class RezultatAktivnost extends Activity implements OnClickListener{

	TextView tv;
	Button btnRestart;
	
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rezultat);
		
		tv = (TextView)findViewById(R.id.textViewVasaOcena);
		btnRestart = (Button)findViewById(R.id.dugmeZapocniIgruPonovo);
		
		StringBuffer sb = new StringBuffer();
		sb.append("Vas rezultat je:" + AktivnostZapocni.ocena);
		tv.setText(sb);
		
		AktivnostZapocni.tacniOdg = 0;
		btnRestart.setOnClickListener(this);
	}
			
			@Override
			public void onClick(View v) {
			
			Intent in = new Intent(this, SledecaAktivnost.class)	;
			startActivity(in);
			
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
