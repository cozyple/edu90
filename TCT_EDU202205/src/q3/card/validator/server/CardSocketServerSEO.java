package q3.card.validator.server;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class CardSocketServerSEO implements Runnable { // Runnable Interface 구현

	private ServerSocket svrSocket;
    private boolean isStop;
	
	
	//파일명(String),파일크기(int),파일데이터(NByte)
	public void run() {
		
		svrSocket = null;
		try {
			svrSocket = new ServerSocket(55566);

			if(!isStop) {
				Socket socket = null;
				
				try {
					socket =  svrSocket.accept();
					
		
						DataInputStream is = new DataInputStream(socket.getInputStream());
						
						byte[] buffer = new byte[2056];
						int len;
						
						while(true) {
							String filename = is.readUTF();
							int fileSize = is.readInt();
						
							while(fileSize > 0) {
								len = is.read(buffer, 0, Math.min(buffer.length, fileSize));
								fileSize -= len;
								writeFile(filename, buffer, 0, len);
							}
						}
				}catch (SocketException e) {

				}catch (EOFException e) {
					
				}finally {
					if(socket != null) socket.close();
				}
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void writeFile(String fileName, byte[] buffer, int offset, int length) throws IOException {
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream("../SERVER/" + fileName);
			fos.write(buffer, offset, length);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("Server received");
			if(fos != null) fos.close();	
		}
	}

	public synchronized void stop() {
		isStop = true;
		
		if(svrSocket !=null )
			try {
				svrSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
