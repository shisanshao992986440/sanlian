package com.robot.sanlian.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadSocketServices implements Runnable {

	private Socket clientCocket = null;

	public ThreadSocketServices(Socket clientCocket) {
		this.clientCocket = clientCocket;

	}

	@Override
	public void run() {
		try {
			ServerSocket server = null;
			try {
				server = new ServerSocket(5200);
				System.out.println("server start is ok...");
				// 创建一个ServerSocket在端口5200监听客户请求
			} catch (Exception e) {
				System.out.println("can not listen to:" + e);
				// 出错，打印出错信息
			}
			try {
				clientCocket = server.accept();
				
				// 使用accept()阻塞等待客户请求，有客户
				// 请求到来则产生一个Socket对象，并继续执行
			} catch (Exception e) {
				System.out.println("Error." + e);
				// 出错，打印出错信息
			}
			System.out.println("客户端是否连接:"+clientCocket.isConnected());
			String line;
			BufferedReader in = new BufferedReader(new InputStreamReader(
					clientCocket.getInputStream()));
			// 由Socket对象得到输入流，并构造相应的BufferedReader对象
			PrintWriter writer = new PrintWriter(clientCocket.getOutputStream());
			// 由Socket对象得到输出流，并构造PrintWriter对象
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			// 由系统标准输入设备构造BufferedReader对象
			System.out.println("客户端地址:"+clientCocket.getLocalSocketAddress().toString());
			System.out.println("客户端ip:"+clientCocket.getInetAddress());
			System.out.println("Client-server:" + in.readLine());
			// 在标准输出上打印从客户端读入的字符串
			line = br.readLine();
			
			// 从标准输入读入一字符串
			while (!line.equals("end")) {
				// 如果该字符串为 "bye"，则停止循环
				writer.println(line);
				// 向客户端输出该字符串
				writer.flush();
				// 刷新输出流，使Client马上收到该字符串
				System.out.println("Server:" + line);
				// 在系统标准输出上打印读入的字符串
				System.out.println("Client:" + in.readLine());
				// 从Client读入一字符串，并打印到标准输出上
				line = br.readLine();
				// 从系统标准输入读入一字符串
			} // 继续循环
			writer.close(); // 关闭Socket输出流
			in.close(); // 关闭Socket输入流
			//clientCocket.close(); // 关闭Socket
			server.close(); // 关闭ServerSocket
		} catch (Exception e) {// 出错，打印出错信息
			System.out.println("Error." + e);
		}
	}

		public static void main(String[] args) {
			//启动socket服务端
//			ExecutorService pool = Executors.newFixedThreadPool(3);
			ThreadPoolExecutor pool=new ThreadPoolExecutor(5,10,3,TimeUnit.SECONDS,new ArrayBlockingQueue(1000));

			ServerSocket serverSocket = null;
			try {
				serverSocket = new ServerSocket(5200);
				while (true) {
					Socket clientSocket = serverSocket.accept();
					System.out.println("启动线程池.....");
					ThreadSocketServices thread = new ThreadSocketServices(
							clientSocket);
					System.out.println("线程要开始了....");
					pool.execute(thread);
					thread.sendMessage(clientSocket);
//					pool.shutdown();
				}
			} catch (IOException e) {
				e.printStackTrace();
				pool.shutdown();
			}
	}
		
		
		public void sendMessage(Socket socket){
			try{
				OutputStream out =socket.getOutputStream();//得到客户端的输出流，为了向客户端输出数据
				PrintWriter bufferedWriter=new PrintWriter(out,true);
				while (true) {
					 
					String lineout=null;
					bufferedWriter.println(new Date()+",Hello,I'm Server!");//向客户端输出数据
					if(lineout==null)
						break;
				}
			}catch(Exception e){
				e.printStackTrace();
			}

		}
}
