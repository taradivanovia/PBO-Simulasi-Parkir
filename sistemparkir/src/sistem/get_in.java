package sistem;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class get_in 
{
	private static InetAddress host;
	private static final int PORT = 99;
	public static get_in tgl = new get_in();
	//public static final String ip = "get_in";
	
	private String getTanggal()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	public static void main(String[] args) 
	{
		try 
		{
			host = InetAddress.getByName("Taradiva-PC");
			//host = InetAddress.getLocalHost();
		}catch (UnknownHostException uhEx) 
		{
			System.out.println("Host ID tidak ditemukan!");
			System.exit(1);
		}
		accessServer();
	}

	public static void accessServer()
	{
		Socket link = null;
		//Step 1.
		try
		{
			link = new Socket(host,PORT);
			//Step 1.membuka input stream
			Scanner input = new Scanner(link.getInputStream());
			//Step 2.membuka output stream
			PrintWriter output = new PrintWriter(link.getOutputStream(),true);
			//Step 2.
			//Set up stream untuk masukan dari keyboard
			Scanner userEntry = new Scanner(System.in);
			String message = null, response;
			String n_pol, masuk, keluar;
			String admin, kapasitas;
		
			do
			{
				System.out.print("Ketik in(getin) & out (get out) : ");
					admin = userEntry.nextLine();
				
				System.out.print("Masukan No Polisi : ");
					n_pol = userEntry.nextLine();
				
				System.out.print("Tekan 'No' (untuk keluar) & 'Ya' (untuk lanjut) : ");
					message = userEntry.nextLine();
				
				output.println(n_pol);
				output.println(message);	
					
				
				if(admin.equalsIgnoreCase("in"))
				{	
					masuk = tgl.getTanggal();
					keluar = null;
					
					output.println(admin);
					output.println(n_pol);
					output.println(message);
					output.println(masuk);
					output.println(keluar);
					
					//masuk = tgl.getTanggal();
					//keluar = null;
					
					//output.println(message);
					// Step 3.
					
					//response = input.nextLine(); //Step 3.
					//System.out.println("\n"+new Date() +"SERVER> "+response);
					//System.out.println(response);
				}
				else if(admin.equalsIgnoreCase("out"))
				{
					keluar = tgl.getTanggal();
					
					output.println(admin);
					output.println(n_pol);
					output.println(message);
					output.println(keluar);
					
				}
				response = input.nextLine(); //Step 3.
					System.out.println(response);
				kapasitas = input.nextLine();
					System.out.println("sisa kapasitas parkir : ");
			}while (!message.equalsIgnoreCase("No"));
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
		finally
		{
			try
			{
				System.out.println(
						"\n* Closing connection... *");
				link.close();
				//Step 4.
			}
			catch(IOException ioEx)
			{
				System.out.println(
						"Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}