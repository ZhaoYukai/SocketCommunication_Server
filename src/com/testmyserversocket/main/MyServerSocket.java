package com.testmyserversocket.main;

/*
 * telnet localhost 12345 ����ģ���һ���ͻ���
 * ������´�һ������̨�������������ģ������һ���¿����Ŀͻ���
 */

public class MyServerSocket {

	public static void main(String[] args) {
		
		//����ServerListener�߳�
		new ServerListener().start();

	}//main()��������

}
