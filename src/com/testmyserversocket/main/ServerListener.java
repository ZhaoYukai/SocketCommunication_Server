package com.testmyserversocket.main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

/*
 * ����һ�ֱȽϷ���Ŀ������̵߳ķ�����ͨ��extendsһ��Thread�࣬�������һ�����̵߳Ĵ���
 * Ȼ�������������дrun()��������������ݾ������߳���Ҫִ�еĶ���
 */
public class ServerListener extends Thread {
	
	@Override
	public void run() {
		
		try {
			//(1)����һ��ServerSocket���󡣹��췽���ﴫ���int�����ݾ��ǵ�ǰServerSocket�����Ķ˿ڣ�����ֵ������1~65535
			ServerSocket serverSocket = new ServerSocket(12345);
			
			/*
			 * ����accept()������ÿ����һ���ͻ������ӵ�ServerSocket����ôaccept()�������᷵��һ��Socket����
			 * ����ж���ͻ������ӵ�ǰ�ķ�������Ҳ�������ӵ�ǰ��ServerSocket�Ļ�����ô�ͻ��ж��Socket�������
			 * �����������Ҫ����һ��whileѭ������ѭ���ļ������Կͻ��˵�����
			 */
			
			while(true){
				//(2)����accept()���������ͻ��˵����ӡ�
				//����accept()������һ�������ķ���������������ǰ���̡߳�һ��accept()�������óɹ����᷵��һ��Socket������ͱ�ʾ���ӳɹ���
				//��Ȼ��accept()�����ĵ��÷��������߳��и�Ϊ����һЩ
				Socket socket = serverSocket.accept();
				
				//(3)��Ȼ�Ѿ����ͻ����������ˣ��͵���һ����Ϣ����Ϊ��ʾ
				JOptionPane.showMessageDialog(null , "�пͻ������ӵ��˱�����12345�˿�");
				
				//(4)����ÿ��socket��Ҫ������Ŀͻ��˽���ͨ�ţ����Ի�Ҫ�����socket���ݸ�����һ���µ��̣߳�Ҳ����ChatSocket�̣߳�
				ChatSocket cs = new ChatSocket(socket);
				cs.start();
				
				//(5)����һ��ChatSocket�߳̾ʹ������ˣ������Ž�ChatManager��ļ����з����Ժ����
				ChatManager.getCm().add(cs);
				
			}//while����
			

			
		}//try����
		catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}//run()��������

}
