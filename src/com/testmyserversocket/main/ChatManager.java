package com.testmyserversocket.main;

import java.util.Vector;

/*
 * ������ServerListener�߳��д����ĺܶ��ChatSocket�̶߳����໥������
 * �������ЩChatSocket�̲߳�������ͨѶ����ô����ChatManagerͳһ��������
 * ����һ�����������ֻ����һ��Manager������Ҫ�����ChatManager����һ���������Ĵ���
 */
public class ChatManager {
	
	//(1)�������ĵ�һ���������������Ĺ��췽�����private���͵�
	private ChatManager(){
		
	}

	//(2)�������ĵڶ���������һ��private�ġ�static�ġ�final��   ChatManager����
	private static final ChatManager cm = new ChatManager();

	//(3)�������ĵ�����������һ��static�ģ����ڷ���ChatManager����ķ���
	public static ChatManager getCm() {
		return cm;
	}
	
	
	Vector<ChatSocket> vector = new Vector<ChatSocket>();
	
	public void add(ChatSocket cs){
		vector.add(cs);
	}
	
	
	/*
	 * ĳһ��ChatSocket�߳��ܹ�ͨ�����publish()���������������пͻ��˷�����Ϣ
	 * @param1  ��ô��Ҫ���β��б���ָ���������ĸ�ChatSocket�߳�
	 * @param2  ָ����Ҫ���͵���Ϣ
	 */
	public void publish(ChatSocket cs , String out){
		for(int i=0 ; i<vector.size() ; i++){
			ChatSocket testCs = vector.get(i);
			if( ! cs.equals(testCs)){ //������ڱ������Ǹ��̲߳����β��б����Ǹ��̣߳���Ϊ�β��б����Ǹ��̲߳���Ҫ���յ���Ϣ��
				testCs.OutPrint(out); //��ô���ڱ������Ǹ��߳̾ʹ�ӡ�����յ����Ǹ��ַ���
			}
		}
	}//publish()��������
	
	
	
	

}
