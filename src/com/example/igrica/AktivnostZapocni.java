package com.example.igrica;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AktivnostZapocni extends Activity implements OnClickListener {
	
	TextView tv;
	Button btn;
	RadioGroup rg;
	RadioButton rb0, rb1, rb2;
	
	String pitanja [] = {"Gde se nalazi kip Slobode?", "Koje su tri osnovne boje?", "Kad je poceo Prvi svetski rat?", 
			"Kako se zove 4. padez?", "Ko je svetski teniser broj 1?", "Po kom kompozitoru je poznat Bec?", 
			"Koji je glavni grad Srbije?", "Koja je najduza reka koja protice kroz Srbiju?", 
			"Koja je najmnogoljudnija zemlja na svetu?", "Koje je najdublje jezero na svetu?" };
	String tacno [] = {"Nju Jork", "Zuta, crvena, plava", "1914.", "Akuzativ", "Novak Djokovic", 
			"Mocart", "Beograd", "Dunav", "Kina", "Bajkalsko jezero"};
	String opt[]= {"Nju Jork", "Pariz", "Milano", 
			"Bela, crna, crvena", "Zuta, crvena, plava", "Zelena, crvena, zuta",
			"1939.", "1889.", "1914.", 
			"Akuzativ", "Nominativ", "Lokativ", 
			"Rodzer Federer", "Novak Djokovic", "Rafael Nadal",
			"Bah", "Betoven", "Mocart",
			"Beograd", "Zagreb", "Sarajevo", 
			"Dunav", "Sava", "Velika Morava", 
			"Indija", "Kina", "Rusija",
			"Kaspijsko jezero", "Aralsko jezero", "Bajkalsko jezero"};
	
	int brPitanja = 0;
	public static int tacniOdg, ocena ;
	
	protected void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.activity_zapocni);
	       
	       tv=(TextView)findViewById(R.id.textViewTextPitanja);
	       btn= (Button)findViewById(R.id.dugmeSledecePitanje);
	       rg= (RadioGroup)findViewById(R.id.radioGroup1);
	       rb0= (RadioButton)findViewById(R.id.radio0);
	       rb1= (RadioButton)findViewById(R.id.radio1);
	       rb2= (RadioButton)findViewById(R.id.radio2);
	       
	       tv.setText(pitanja[brPitanja]);
	       rb0.setText(opt[0]);
	       rb1.setText(opt[1]);
	       rb2.setText(opt[2]);
	       
	
	       btn.setOnClickListener(this);
	}
			@Override
			public void onClick(View v) {
				RadioButton odg = (RadioButton)findViewById(rg.getCheckedRadioButtonId());
				String odgText= odg.getText().toString();
				
				if (odgText.equalsIgnoreCase(tacno[brPitanja])) {
					tacniOdg++;
				}
				brPitanja++;
				
				if(brPitanja<pitanja.length) {
					tv.setText(pitanja[brPitanja]);
					rb0.setText(opt[brPitanja*3]);
					rb1.setText(opt[(brPitanja*3)+1]);
					rb2.setText(opt[(brPitanja*3)+2]);
				} else {
					ocena = tacniOdg;
					Intent in = new Intent(this, RezultatAktivnost.class);
					startActivity(in);
				}
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
