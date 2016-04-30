package ua.kas.laba8;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server implements Runnable{

	static private ServerSocket server;
	static private Socket connection;
	static private ObjectOutputStream output;
	static private ObjectInputStream input1;
	static private ObjectInputStream input2;
	static private ObjectInputStream input3;
	static private ObjectInputStream input4;
	
	@Override
	public void run() {
		
		try {
			server = new ServerSocket(5678, 10);
			while (true) {
				connection = server.accept();
				output = new ObjectOutputStream(connection.getOutputStream());
				input1 = new ObjectInputStream(connection.getInputStream());
				input2 = new ObjectInputStream(connection.getInputStream());
				input3 = new ObjectInputStream(connection.getInputStream());
				input4 = new ObjectInputStream(connection.getInputStream());
				String y2 = (String)input1.readObject();
				String x2 = (String)input2.readObject();
				String y1 = (String)input3.readObject();
				String x1 = (String)input4.readObject();
					
				if(x1.contains(y2)){
				
					output.writeObject("Вы выбрали размерность: "+x1+"x"+y1+" and "+ x2+"x"+y2);	
				
					int[][] mA = new int[Integer.parseInt(y1)][Integer.parseInt(x1)];
					int[][] mB = new int[Integer.parseInt(y2)][Integer.parseInt(x2)];
					int[][] res = new int[Integer.parseInt(y1)][Integer.parseInt(x2)];
					
					//заполнение файла рандомными значения для матрицы
					
					Random random = new Random();
					
					for(int i=0; i<Integer.parseInt(y1); i++){
						for(int j=0; j<Integer.parseInt(x1); j++){
							mA[i][j] = random.nextInt(100);
						}
					}
					
					for(int i=0; i<Integer.parseInt(y2); i++){
						for(int j=0; j<Integer.parseInt(x2); j++){
							mB[i][j] = random.nextInt(100);
						}
					}
					
					//умножем матрицы
					
					int m = mA.length;    
					int n = mB[0].length; 
					int o = mB.length; 
					
					for (int i = 0; i < m; i++) {
						for (int j = 0; j < n; j++) {
							for (int k = 0; k < o; k++) {
								res[i][j] += mA[i][k] * mB[k][j];
							}
						}
					}
					System.out.println("Thread"+" finish!");
					
//					//создание и запись файла
//					
//					FileWriter fw;
//					try {
//						fw = new FileWriter(new File(result));
//						for (int i = 0; i < res.length; i++) {
//							for (int j = 0; j < res[0].length; j++) {
//								result = String.valueOf(res[i][j]) + "      ";
//								fw.append(result);
//							}
//							fw.append("\n" );
//						}
//						fw.flush();
//						fw.close();
//					} catch (IOException e) {}	
					
					
					//выводим в консоль

					for (int i = 0; i < res.length; i++) {
						for (int j = 0; j < res[0].length; j++) {
							System.out.format("%6d ", res[i][j]);
						}
						System.out.println();
					}

				}
				else{
					output.writeObject("Невозможность перемножения матриц вследствие несовпадения размерностей.");
				}
			}
		} catch (IOException e) {e.printStackTrace();
		} catch (HeadlessException e) {e.printStackTrace();
		} catch (ClassNotFoundException e) {e.printStackTrace();}
	}
	
	
}
