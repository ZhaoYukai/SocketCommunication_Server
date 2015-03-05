package com.testmyserversocket.main;

/*
 * telnet localhost 12345 开启模拟第一个客户端
 * 如果再新打开一个控制台窗口输入命令，就模拟了又一个新开启的客户端
 */

public class MyServerSocket {

	public static void main(String[] args) {
		
		//调用ServerListener线程
		new ServerListener().start();

	}//main()方法结束

}
