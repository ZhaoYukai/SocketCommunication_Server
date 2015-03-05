package com.testmyserversocket.main;

import java.util.Vector;

/*
 * 由于在ServerListener线程中创建的很多个ChatSocket线程都是相互独立的
 * 如果让这些ChatSocket线程产生数据通讯，那么就让ChatManager统一管理它们
 * 由于一个聊天服务器只能有一个Manager，所以要把这个ChatManager类做一个单例化的处理
 */
public class ChatManager {
	
	//(1)单例化的第一步，就是让这个类的构造方法变成private类型的
	private ChatManager(){
		
	}

	//(2)单例化的第二步，创建一个private的、static的、final的   ChatManager对象
	private static final ChatManager cm = new ChatManager();

	//(3)单例化的第三步，创建一个static的，用于返回ChatManager对象的方法
	public static ChatManager getCm() {
		return cm;
	}
	
	
	Vector<ChatSocket> vector = new Vector<ChatSocket>();
	
	public void add(ChatSocket cs){
		vector.add(cs);
	}
	
	
	/*
	 * 某一个ChatSocket线程能够通过这个publish()方法向其他的所有客户端发送信息
	 * @param1  那么需要在形参列表中指明到底是哪个ChatSocket线程
	 * @param2  指明需要发送的信息
	 */
	public void publish(ChatSocket cs , String out){
		for(int i=0 ; i<vector.size() ; i++){
			ChatSocket testCs = vector.get(i);
			if( ! cs.equals(testCs)){ //如果正在遍历的那个线程不是形参列表中那个线程（因为形参列表中那个线程不需要再收到消息）
				testCs.OutPrint(out); //那么正在遍历的那个线程就打印出接收到的那个字符串
			}
		}
	}//publish()方法结束
	
	
	
	

}
