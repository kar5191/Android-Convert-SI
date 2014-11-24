package com.example.convert;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {

	static final int check=1111;
	static final int check2=1;
	Button temp;
	Button dist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temp = (Button)findViewById(R.id.temp);
		temp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
				startActivityForResult(i, check);
				
			}
		});
		
		dist= (Button)findViewById(R.id.dist);
		dist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent j = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				j.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
				startActivityForResult(j, check2);
			}
		});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if(requestCode == check && resultCode == RESULT_OK){
			ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			boolean check = false;
			Double fahrenheit=0.0;
			Double celsius=0.0;
			String s=new String("hello");
			 boolean isNumber = false;
			   try
			    {
			    	fahrenheit=Double.parseDouble(results.get(0));
			    	celsius=(fahrenheit-32)*5/9;
			    	celsius=Math.floor(celsius*100)/100;
			        isNumber = true;
			        s=Double.toString(celsius);
			    } catch (NumberFormatException e)
			    {
			        isNumber = false;
			        s="sorry try again and make sure you say a number";
			    }
			   
			   if(isNumber)
			    	Toast.makeText(getApplicationContext(),"F: " +fahrenheit +" C: " +s, Toast.LENGTH_LONG).show();
			    else
			    	Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
		}
		
		else if(requestCode == check2 && resultCode == RESULT_OK){
			ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			boolean check = false;
			double miles=0.0;
			double km=0.0;
			String s2=new String();
			 boolean isNumber = false;
			    try
			    {
			    	miles=Double.parseDouble(results.get(0));
			    	km=miles*1.60934;
			        isNumber = true;
			        s2=Double.toString(km);
			    } catch (NumberFormatException e)
			    {
			        isNumber = false;
			        s2="sorry try again and make sure you say a number";
			    }
			   
			    if(isNumber)
			    	Toast.makeText(getApplicationContext(), "Miles :" +miles +" Km: " +s2, Toast.LENGTH_LONG).show();
			    else
			    	Toast.makeText(getApplicationContext(), s2, Toast.LENGTH_LONG).show();
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
}
