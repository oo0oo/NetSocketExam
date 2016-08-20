package com.hand;
  
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
	@Override
	public void run() {
		Socket s = null;
		try {
			ServerSocket ss = new ServerSocket(12345);
			while(true){
				File fi = new File("target.pdf");
				s = ss.accept();
				DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(fi)));
				DataOutputStream ps = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));

				byte[] buf = new byte[1024];
				System.out.println("!");
				while(fis.read(buf)!=-1){
					ps.write(buf);
					System.out.println("!");
				}

				fis.close();
				ps.close();
				ss.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Server().start();
	}
}
