package com.demo.chatapp;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class ChatActivity extends ActionBarActivity {

	String dstAddress = "192.168.23.47";
	int dstPort = 5000;
	ImageButton btnSend;
	EditText textSend;
	ListView lv;

	MessagesCustomAdapter customadapter = null;

	protected ChatClient chat;
	ArrayList<ChatMessage> conversation = new ArrayList<ChatMessage>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);

		MyClientTask myClientTask = new MyClientTask(dstAddress, dstPort);
		myClientTask.execute();

		btnSend = (ImageButton) findViewById(R.id.btn_send);
		btnSend.setEnabled(false);
		textSend = (EditText) findViewById(R.id.text_send);

		TextWatcher editTextOnTypeListener = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				btnSend.setEnabled(false);
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.length() > 0) {
					btnSend.setEnabled(true);
				}

			}
		};

		OnClickListener buttonSendOnClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				chat.sendMessageToServer(textSend.getText().toString());
				ChatMessage objCM = new ChatMessage(null, textSend.getText()
						.toString());
				conversation.add(objCM);
				setMessageOnList();
				textSend.setText("");
			}
		};

		textSend.addTextChangedListener(editTextOnTypeListener);
		btnSend.setOnClickListener(buttonSendOnClickListener);

	}

	public void setMessageOnList() {
		lv = (ListView) findViewById(R.id.message_container);
		customadapter = new MessagesCustomAdapter(getApplicationContext(),
				R.layout.list_item_message, conversation);

		lv.setAdapter(customadapter);
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
