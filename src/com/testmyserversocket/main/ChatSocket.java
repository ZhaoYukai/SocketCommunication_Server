package com.testmyserversocket.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/*
 * ����һ���µ��̣߳�ר�ŶԽ��յ����µ�socket���д����
 */
public class ChatSocket extends Thread {
	
	Socket socket;
	
	//���췽��
	public ChatSocket(Socket s){
		this.socket = s;
	}
	
	
	//�����������ز�������װ��OutPrint()�����У������������̨�������ݣ��Ͱ�Ҫ���͵����ݷŵ��β��У�ֱ�ӵ������OutPrint()����
	public void OutPrint(String out){
		try {
			//(1)����Ҫ�Ե�ǰ��socket�������ݵ������Ҳ���ǻ�ȡ�����������ͨ�������������ſ��������������
			OutputStream os = socket.getOutputStream();
			
			//(2)����OutputStream���write()��������out�н��յ����ַ����������Ļ�ϡ�ע��outҪ��getBytes()����һ���ַ���
			os.write((out+"\n").getBytes("UTF-8"));
						
		} 
		catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}//OutPrint()��������
	
	
	
	@Override
	public void run() {
		OutPrint("You have connected the server.");
		try {
			BufferedReader br = new BufferedReader( new InputStreamReader(socket.getInputStream() , "UTF-8") );
			String line = null;
			while( (line = br.readLine()) != null){ //������ܴ�br�ж�������
				ChatManager.getCm().publish(ChatSocket.this , line);
			}
			br.close(); //���˴����ж������ݵ����������ˣ��ر������
		} 
		catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}//run()��������

}
