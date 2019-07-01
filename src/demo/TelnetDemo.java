package demo;

import java.io.*;
import java.net.Socket;

public class TelnetDemo {
	public static void main(String[] args) {
		try {
			if(args.length<2) {
				System.out.println("Usage: telnet ip port"); 
				return;
			}
			int nPort=Integer.valueOf(args[1]);
			Socket socket = new Socket(args[0],nPort);	// 打开一个套接字
			try {
				BufferedReader inStream=new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
				while (true) {
					String line = inStream.readLine();//.();//.nextLine(); 
					System.out.println(line);
				}
			}
			finally	{
				socket.close();
			}
			}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
