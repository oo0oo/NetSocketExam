package com.hand;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class Client 		
{
	public static void main(String[] args) { 

		new netDown().start();

	} 
}

class netDown extends Thread{
	@Override
	public void run() {
		InputStream is = null;
		try {
			Socket cs = new Socket("localhost", 12345);
			is = cs.getInputStream();
			DataOutputStream fileOut = new DataOutputStream(
					new BufferedOutputStream(
							new BufferedOutputStream(
									new FileOutputStream("new_target.pdf"))));
			byte[] buf = new byte[1024];
			while(is.read(buf)!=-1){
				fileOut.write(buf);
				System.out.println("OK");
			}
									
			is.close();
			fileOut.close();
			cs.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}  
