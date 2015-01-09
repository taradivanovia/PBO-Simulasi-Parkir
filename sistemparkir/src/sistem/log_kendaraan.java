package sistem;

import java.util.*;

class log_kendaraan 
{
	private String n_pol;
	private String masuk;
	private String keluar;
	private String cek = "tidak";
	private int kapasitas = 20;

	public int getKapasitas() {
		return kapasitas;
	}

	public void setKapasitas(int kapasitas) {
		this.kapasitas = kapasitas;
	}

	private Vector no_pol = new Vector();
	private Vector j_masuk = new Vector();
	private Vector j_keluar = new Vector();
	private int data_ke;
	
	public String getN_pol() 
	{
		return n_pol;
	}

	public void setN_pol(String n_pol) 
	{
		this.n_pol = n_pol;
	}

	public String getMasuk() 
	{
		return masuk;
	}

	public void setMasuk(String masuk) 
	{
		this.masuk = masuk;
		j_masuk.add(masuk);
	}
	
	public String getKeluar() 
	{
		return keluar;
	}

	public void setKeluar(String keluar) 
	{
		this.keluar = keluar;
		j_keluar.add(keluar);
	}
	
	public void setCek(String cek)
	{
		this.cek = cek;
	}
	public String getCek()
	{
		return cek;
	}

	public void nopol_in()
	{		
		for(int a = 0; a < no_pol.size(); a++)
		{
			if((no_pol.elementAt(a).toString()).equalsIgnoreCase(n_pol))
			{
				cek = "ada";
				break;
			}
			else
			{
				cek = "tidak";
			}
		}
		if(cek != "ada")
		{
			no_pol.add(getN_pol());
			cek = "tidak";
		}
		setCek(cek);
	}
	
	public void nopol_out()
	{		
		for(int b = 0; b < no_pol.size(); b++)
		{
			if((no_pol.elementAt(b).toString()).equalsIgnoreCase(n_pol))
			{
				cek = "ada";
				data_ke = b;
				j_keluar.insertElementAt(getKeluar(),b);
				break;
			}
			else
			{
				cek = "tidak";
			}
		}
		if(cek.equalsIgnoreCase("ada"))
		{
			setN_pol(no_pol.elementAt(data_ke).toString());
			setMasuk(j_masuk.elementAt(data_ke).toString());
			setKeluar(j_keluar.elementAt(data_ke).toString());
			
			//no_pol.removeElementAt(data_ke);
			//j_masuk.removeElementAt(data_ke);
			//j_keluar.removeElementAt(data_ke);
		}
		setCek(cek);
	}
	
	public void cekdata()
	{
		System.out.println("No Polisi" + "\t" + "Jam Masuk" + "\t\t" + "Jam Keluar");
		for(int x = 0; x < no_pol.size(); x++)
		{
			System.out.println(no_pol.elementAt(x) + "\t" + j_masuk.elementAt(x) + "\t" + j_keluar.elementAt(x));
		}
	}
	
	public void min_kapasitas()
	{
		kapasitas--;
		setKapasitas(kapasitas);
	}
	
	public void plus_kapasitas()
	{
		kapasitas++;
		setKapasitas(kapasitas);
	}
}