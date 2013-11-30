package org.ifunpas.fo.cs.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import org.ifunpas.fo.cs.util.DBConnection;


public class Barang {
	private int idBarang;
	private String namaBarang;
	private String jenisBarang;
	private int hargaBeli;
	private int hargaJual;
	private int stok;
	private String tgl_masuk;
	private int discount;
	private String url;
	
	private ArrayList<Barang> barangs;
	
	

	public ArrayList<Barang> getBarangs() {
		setBarangTerbaru();
		return barangs;
	}

	public Barang() {
	}

	public Barang(String namaBarang, String jenisBarang,
			int hargaBeli, int hargaJual, int stok, String tgl_masuk,
			int discount) {
		this.namaBarang = namaBarang;
		this.jenisBarang = jenisBarang;
		this.hargaBeli = hargaBeli;
		this.hargaJual = hargaJual;
		this.stok = stok;
		this.tgl_masuk = tgl_masuk;
		this.discount = discount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIdBarang() {
		return idBarang;
	}

	public void setIdBarang(int idBarang) {
		this.idBarang = idBarang;
	}

	public String getNamaBarang() {
		return namaBarang;
	}

	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}

	public String getJenisBarang() {
		return jenisBarang;
	}

	public void setJenisBarang(String jenisBarang) {
		this.jenisBarang = jenisBarang;
	}

	public int getHargaBeli() {
		return hargaBeli;
	}

	public void setHargaBeli(int hargaBeli) {
		this.hargaBeli = hargaBeli;
	}

	public int getHargaJual() {
		return hargaJual;
	}

	public void setHargaJual(int hargaJual) {
		this.hargaJual = hargaJual;
	}

	public int getStok() {
		return stok;
	}

	public void setStok(int stok) {
		this.stok = stok;
	}

	public String getTgl_masuk() {
		return tgl_masuk;
	}

	public void setTgl_masuk(String tgl_masuk) {
		this.tgl_masuk = tgl_masuk;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public void setBarangTerbaru() {
		try {
			DBConnection.getInstance().connect();
			Connection con = DBConnection.getInstance().getConnection();
			Statement stat = con.createStatement();
			ResultSet s = stat.executeQuery("exec dbo.viewModelBaru");
			System.out.println("s ="+ s);
			barangs = new ArrayList<Barang>();
			while (s.next()) {
				Barang b = new Barang();
				b.setIdBarang(s.getInt(1));
				System.out.println(b.getIdBarang());
				b.setNamaBarang(s.getString(2));
				b.setJenisBarang(s.getString(3));
				b.setHargaBeli(s.getInt(4));
				b.setHargaJual(s.getInt(5));
				b.setStok(s.getInt(6));
				b.setTgl_masuk(s.getString(7));
				b.setDiscount(s.getInt(8));
				b.setUrl(s.getString(9));
				barangs.add(b);
			}
			stat.close();
		} catch (SQLException ex) {
			System.out.println("ERROR KARENA : " + ex.getMessage());
		}
	}
}
