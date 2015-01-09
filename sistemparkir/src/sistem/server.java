package sistem;

import java.io.*;
import java.net.*;
import java.util.*;

public class server
{
	//public static Vector no_pol = new Vector();
	//public static Vector masuk = new Vector();
	//public static Vector keluar = new Vector();
	
	private static ServerSocket serverSocket;
	private static final int PORT = 99;
	public static void main(String[] args)throws IOException
	{
		try
		{
			serverSocket = new ServerSocket(PORT);
			System.out.println("Server Parkir Pada PORT:" + PORT);
		}
		catch (IOException ioEx)
		{
			System.out.println("\nTidak Dapat Membuka Port!");
			System.exit(1);
		}
		do
		{
			//Tunggu Koneksi
			System.out.println("Server Siap...");
			Socket client = serverSocket.accept();
			
			System.out.println("\nClient Baru ..masuk..dengan IP: "+client.getInetAddress().toString()+"\n");
			//Create a thread to handle communication with
			//this client and pass the constructor for this
			//thread a reference to the relevant socket...
			ClientHandler handler =	new ClientHandler(client);
			handler.start();//As usual, method calls run .
		}while (true);
	}
}

class ClientHandler extends Thread
{
	private Socket client;
	private Scanner input;
	private PrintWriter output;
	
	log_kendaraan log = new log_kendaraan();
	
	public ClientHandler(Socket socket)
	{
		//Set up reference to associated socket...
		client = socket;
		try
		{
			input = new Scanner(client.getInputStream());
			output = new PrintWriter(client.getOutputStream(),true);
			//output.println("Selamat Datang Di Server Parkir . . .");
		}
		catch(IOException ioEx){
			ioEx.printStackTrace();
		}
	}
	public void run()
	{
		String no_polisi,masuk,keluar,message,admin;
		int data_ke = 0;
		int kapasitas;
		
		do
		{
			admin = input.nextLine();
			no_polisi = input.nextLine();
			message = input.nextLine();
			if(log.getKapasitas() > 0)
			{
				if(admin.equalsIgnoreCase("in"))
				{
					masuk = input.nextLine();
					keluar = input.nextLine();
					
					log.setN_pol(no_polisi);
					log.setMasuk(masuk);
					log.setKeluar(keluar);
					
					log.nopol_in();
					
					if(log.getCek().equalsIgnoreCase("tidak"))
					{				
						System.out.println("No Polisi : " + log.getN_pol());
						System.out.println("Jam Masuk : " + log.getMasuk());
						System.out.println("Jam Keluar : " + log.getKeluar());
						System.out.println("Welcome");
						output.println("Silahkan Masuk");
						System.out.println();
						log.min_kapasitas();
					}
					else
					{
						System.out.println("salh pintu");
						output.println("salah pintu boz !!!");
						System.out.println();
					}
				}
				else if(admin.equalsIgnoreCase("out"))
				{
					keluar = input.nextLine();
					
					log.setN_pol(no_polisi);
					log.setKeluar(keluar);
					
					log.nopol_out();
					
					if(log.getCek().equalsIgnoreCase("ada"))
					{				
						System.out.println("No Polisi : " + log.getN_pol());
						System.out.println("Jam Masuk : " + log.getMasuk());
						System.out.println("Jam Keluar : " + log.getKeluar());
						
						output.println("Silahkan Keluar");
						
						System.out.println();
						
						log.plus_kapasitas();
					}
					else
					{
						System.out.println("maling");
						output.println("Apakah Anda Pencuri ???");
						System.out.println();
					}
				}
			}
			else
			{
				output.println("Maaf Parkir Telah Penuh");
			}
			

			log.cekdata();
			
			System.out.println();
			
			//System.out.println("cek : " + log.getCek());
			//System.out.println("admin : " + admin);
			//System.out.println("Jam Keluar : " + log.getKeluar());
			
			//message = input.nextLine();
			//System.out.println("message : " + message);
			
			System.out.println();

			output.println(log.getKapasitas());
		}while (!message.equalsIgnoreCase("No"));
		try
		{
			if (client!=null)
			{
				System.out.println(
						"Menutup koneksi...sukses..");
				client.close();
			}
		}
		catch(IOException ioEx)
		{
			System.out.println("Gagal menutup koneksi!");
		}
	}
}