package com.testmyserversocket.main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

/*
 * 这是一种比较方便的开启子线程的方法，通过extends一个Thread类，就完成了一个子线程的创建
 * 然后在这个类中重写run()方法，里面的内容就是子线程中要执行的东西
 */
public class ServerListener extends Thread {
	
	@Override
	public void run() {
		
		try {
			//(1)创建一个ServerSocket对象。构造方法里传入的int型数据就是当前ServerSocket监听的端口，它的值可以是1~65535
			ServerSocket serverSocket = new ServerSocket(12345);
			
			/*
			 * 对于accept()方法，每当有一个客户端连接到ServerSocket，那么accept()方法都会返回一个Socket对象
			 * 如果有多个客户端连接当前的服务器，也就是连接当前的ServerSocket的话，那么就会有多个Socket对象出现
			 * 因此在这里需要创建一个while循环，来循环的监听来自客户端的连接
			 */
			
			while(true){
				//(2)调用accept()方法监听客户端的连接。
				//不过accept()方法是一个阻塞的方法，它会阻塞当前的线程。一旦accept()方法调用成功，会返回一个Socket对象，这就表示连接成功了
				//显然，accept()方法的调用发生在子线程中更为合理一些
				Socket socket = serverSocket.accept();
				
				//(3)既然已经跟客户端连接上了，就弹出一个消息框作为提示
				JOptionPane.showMessageDialog(null , "有客户端链接到了本机的12345端口");
				
				//(4)由于每个socket又要与独立的客户端进行通信，所以还要把这个socket传递给另起一个新的线程（也就是ChatSocket线程）
				ChatSocket cs = new ChatSocket(socket);
				cs.start();
				
				//(5)这样一个ChatSocket线程就创建完了，把它放进ChatManager类的集合中方便以后管理
				ChatManager.getCm().add(cs);
				
			}//while结束
			

			
		}//try结束
		catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}//run()方法结束

}
