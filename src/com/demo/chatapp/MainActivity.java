package com.demo.chatapp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private ProgressDialog pDialog;
	
	PrintWriter output;

	TextView textResponse;
	EditText editTextAddress, editTextPort;
	Button buttonConnect, buttonClear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editTextAddress = (EditText) findViewById(R.id.address);
		editTextPort = (EditText) findViewById(R.id.port);
		buttonConnect = (Button) findViewById(R.id.connect);
		buttonClear = (Button) findViewById(R.id.clear);
		textResponse = (TextView) findViewById(R.id.response);

		buttonClear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				textResponse.setText("");

			}
		});

		OnClickListener buttonConnectOnClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				MyClientTask myClientTask = new MyClientTask(editTextAddress
						.getText().toString(), Integer.parseInt(editTextPort
						.getText().toString()));
				myClientTask.execute();

			}
		};

		buttonConnect.setOnClickListener(buttonConnectOnClickListener);

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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	public class MyClientTask extends AsyncTask<Void, Void, Void> {

		String dstAddress;
		int dstPort;
	

		MyClientTask(String addr, int port) {
			dstAddress = addr;
			dstPort = port;
		}

		// @Override
		// protected void onPreExecute() {
		// super.onPreExecute();
		// // Showing progress dialog
		// pDialog = new ProgressDialog(MainActivity.this);
		// pDialog.setMessage("Connecting...");
		// pDialog.setCancelable(false);
		// pDialog.show();
		//
		// }

		@Override
		protected Void doInBackground(Void... arg0) {

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
//			textResponse.setText(response);


		}

	}

}
