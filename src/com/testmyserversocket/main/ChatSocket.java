package com.testmyserversocket.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/*
 * 这是一个新的线程，专门对接收到的新的socket进行处理的
 */
public class ChatSocket extends Thread {
	
	Socket socket;
	
	//构造方法
	public ChatSocket(Socket s){
		this.socket = s;
	}
	
	
	//把输出流的相关操作都封装到OutPrint()方法中，如果想往控制台发送数据，就把要发送的数据放到形参中，直接调用这个OutPrint()方法
	public void OutPrint(String out){
		try {
			//(1)首先要对当前的socket进行数据的输出，也就是获取它的输出流。通过这个输出流，才可以向外输出数据
			OutputStream os = socket.getOutputStream();
			
			//(2)调用OutputStream类的write()方法，把out中接收到的字符串输出到屏幕上。注意out要用getBytes()声明一下字符集
			os.write((out+"\n").getBytes("UTF-8"));
						
		} 
		catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}//OutPrint()方法结束
	
	
	
	@Override
	public void run() {
		OutPrint("You have connected the server.");
		try {
			BufferedReader br = new BufferedReader( new InputStreamReader(socket.getInputStream() , "UTF-8") );
			String line = null;
			while( (line = br.readLine()) != null){ //如果还能从br中读出数据
				ChatManager.getCm().publish(ChatSocket.this , line);
			}
			br.close(); //至此从流中读出数据的任务就完成了，关闭这个流
		} 
		catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}//run()方法结束

}
