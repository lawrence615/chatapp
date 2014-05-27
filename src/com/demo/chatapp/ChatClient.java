package com.demo.chatapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.util.Log;

public class ChatClient {
	private String response = "";
	protected Socket socket = null;

	public ChatClient(String dstAddress, int dstPort) {
		try {
			this.socket = new Socket(dstAddress, dstPort);
			socket.setKeepAlive(true);
//			getMessageFromServer();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isConnected() {

		if(!this.socket.isConnected()){
			return false;
		}

		return true;
	}

	/*
	 * @desc  sends message to the server
	 * @param message - string. The message typed by the user
	 */
	public void sendMessageToServer(String message) {
		// System.out.println("my message "+message);


			try {
				String str = message;
				/*
				 * @desc writing to the socket the message received
				 */
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(this.socket.getOutputStream())),
						true);
				out.println(str);
				Log.d("Client", "Client sent message");
			} catch (UnknownHostException e) {
				System.out.println("Error1");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error2");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Error3");
				e.printStackTrace();
			}

	}
	
	public String getMessageFromServer(){
		try {
			InputStreamReader isr = new InputStreamReader(this.socket.getInputStream());
			BufferedReader br  = new BufferedReader(isr);
			response = br.readLine();
//			System.out.println(response);
			isr.close();
//			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response);

			return response;
		
	}
	
	

}
