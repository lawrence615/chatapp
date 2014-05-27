package com.demo.chatapp;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;;

public class MessagesCustomAdapter extends ArrayAdapter<ChatMessage> {
	private ArrayList<ChatMessage> ctmsg;
	Context mContext;
	
	

	public MessagesCustomAdapter(Context context, int resource,
			ArrayList<ChatMessage> ctmgs) {
		super(context, resource, ctmgs);
		this.mContext = context;
		this.ctmsg = new ArrayList<ChatMessage>();
		this.ctmsg.addAll(ctmgs);

	}

	private class ViewHolder {
		TextView txtSender;
		TextView txtMessage;
	}

	public View getView(int pos, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		Log.v("Convert view", String.valueOf(pos));
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.list_item_message, null);

			holder = new ViewHolder();

//			holder.txtSender = (TextView) convertView.findViewById(R.id.sender);
			holder.txtMessage = (TextView) convertView
					.findViewById(R.id.message);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		ChatMessage chatMessage = ctmsg.get(pos);
//		System.out.println("<<my message>>" + chatMessage.getMessage());
//		holder.txtSender.setText(chatMessage.getSender());
		holder.txtMessage.setText(chatMessage.getMessage());
		LayoutParams lp = (LayoutParams) holder.txtMessage.getLayoutParams();
		
		if(chatMessage.getSender() == "in"){
			holder.txtMessage.setBackgroundResource(R.drawable.speech_bubble_orange);
			lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT); 
			holder.txtMessage.setLayoutParams(lp);
		}

		return convertView;
	}


}
