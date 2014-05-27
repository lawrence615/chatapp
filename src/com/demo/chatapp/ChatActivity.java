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
	boolean mRun = false;

	MessagesCustomAdapter customadapter = null;

	protected ChatClient chat;
	ArrayList<ChatMessage> conversation = new ArrayList<ChatMessage>();
	private TcpClient mTcpClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);

		new MyClientTask().execute("");

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
				// chat.sendMessageToServer(textSend.getText().toString());
				ChatMessage objCM = new ChatMessage(null, textSend.getText()
						.toString());
				if (mTcpClient != null) {
					mTcpClient.sendMessage(textSend.getText().toString());
				}
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
//		customadapter.notifyDataSetChanged();
		lv.setAdapter(customadapter);
	}

	public class MyClientTask extends AsyncTask<String, String, TcpClient> {

		@Override
		protected TcpClient doInBackground(String... message) {

			// we create a TCPClient object and
			mTcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
				@Override
				// here the messageReceived method is implemented
				public void messageReceived(String message) {
					// this method calls the onProgressUpdate
					publishProgress(message);
				}
			});
			mTcpClient.run();

			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
			ChatMessage objCM = new ChatMessage("in", values[0]);
			// in the arrayList we add the messaged received from server
			conversation.add(objCM);
			setMessageOnList();

		}

	}

	@Override
	protected void onPause() {
		super.onPause();

		// disconnect
		mTcpClient.stopClient();
		mTcpClient = null;

	}

}
