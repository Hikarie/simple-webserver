package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import http.HttpServer;

public class ServerFrame {
	
//	private HttpServer server;
//	private File directory;
	private int port;
	private String url;
	private Thread s;
	private static TextArea ta;
	
	
	private void init() {
		JFrame f1 = new JFrame("Web server");
		f1.setSize(550,500);
		f1.setResizable(false);
		f1.setLocation(300,200);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setLayout(new FlowLayout());

		TextField tf1 = new TextField(40);
		JButton btn1 = new JButton("浏览");
		TextField tf2 = new TextField(5);
		JButton btn2 = new JButton("启动");
		ta = new TextArea(25,65);
		f1.add(tf1);
		f1.add(btn1);
		f1.add(tf2);
		f1.add(btn2);
		f1.add(ta);	
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jfc.showDialog(new JLabel(), "选择主目录");
				url = jfc.getSelectedFile().getAbsolutePath();
				tf1.setText(url);
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				port = Integer.valueOf(tf2.getText());
				try {
					url = tf1.getText();
					if(btn2.getText().equals("启动")) {
					launch(url, port, ta);
					btn2.setText("关闭");
					}
					else {
						s.interrupt();
						btn2.setText("启动");
						DateFormat curFormat = new SimpleDateFormat("HH:mm:ss");
						ta.append(curFormat.format(new Date())+" The server has been closed\n");
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		f1.setVisible(true);
	}
	
	private void launch(String url, int port, TextArea ta) throws Exception {
		HttpServer server = new HttpServer(url, port, ta);
		s = new Thread(server);
		s.start();
	}
	
	public static void main(String[] args) {
		ServerFrame sf = new ServerFrame();
		sf.init();
	}
	
	public static void printLog(String text) {
		DateFormat curFormat = new SimpleDateFormat("HH:mm:ss");
		ta.append(curFormat.format(new Date())+" "+text+"\n");
	}

}
