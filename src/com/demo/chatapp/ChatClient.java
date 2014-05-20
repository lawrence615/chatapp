package com.demo.chatapp;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.util.Log;

public class ChatClient {
	private String response = "";
	protected Socket socket = null;
	
	public ChatClient(String dstAddress, int dstPort){
		try {
			this.socket  = new Socket(dstAddress, dstPort);
			socket.setKeepAlive(true);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isConnected() {

		try {
	

			System.out.println("connected");
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
					1024);
			byte[] buffer = new byte[1024];

			int bytesRead;
			InputStream inputStream = this.socket.getInputStream();

			/*
			 * notice: inputStream.read() will block if no data return
			 */
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, bytesRead);
				response += byteArrayOutputStream.toString("UTF-8");
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = "UnknownHostException: " + e.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = "IOException: " + e.toString();

		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return true;
	}

	public void sendMessageToServer(String message) {
//		System.out.println("my message "+message);
		
		try {
			String str = message;
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(this.socket.getOutputStream())), true);
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

}
