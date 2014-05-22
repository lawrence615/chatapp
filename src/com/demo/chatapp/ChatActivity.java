package com.demo.chatapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class ChatActivity extends ActionBarActivity {

	String dstAddress = "192.168.0.102";
	int dstPort = 5000;
	ImageButton btnSend;
	EditText textSend;

	protected ChatClient chat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);

		MyClientTask myClientTask = new MyClientTask(dstAddress, dstPort);
		myClientTask.execute();

		btnSend = (ImageButton) findViewById(R.id.btn_send);
		textSend = (EditText) findViewById(R.id.text_send);

		OnClickListener buttonSendOnClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				chat.sendMessageToServer(textSend.getText().toString());
			}
		};

		btnSend.setOnClickListener(buttonSendOnClickListener);

	}

	public class MyClientTask extends AsyncTask<Void, Void, Void> {

		String dstAddress;
		int dstPort;

		MyClientTask(String addr, int port) {
			dstAddress = addr;
			dstPort = port;
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			chat = new ChatClient(dstAddress, dstPort);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// textResponse.setText(response);

		}

	}

}
