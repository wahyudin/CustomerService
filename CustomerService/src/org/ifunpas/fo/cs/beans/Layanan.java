package org.ifunpas.fo.cs.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.ifunpas.fo.cs.util.DBConnection;

public class Layanan {

	private int idLayanan;
	private String namaLayanan;
	private String keterangan;
	private String url;
	private ArrayList<Layanan> la;

	public Layanan() {
	}

	public Layanan(int idLayanan, String namaLayanan, String keterangan,
			String url) {
		super();
		this.idLayanan = idLayanan;
		this.namaLayanan = namaLayanan;
		this.keterangan = keterangan;
		this.url = url;
	}

	public ArrayList<Layanan> getLa() {
		loadLayanan();
		return la;
	}

	public int getIdLayanan() {
		return idLayanan;
	}

	public void setIdLayanan(int idLayanan) {
		this.idLayanan = idLayanan;
	}

	public String getNamaLayanan() {
		return namaLayanan;
	}

	public void setNamaLayanan(String namaLayanan) {
		this.namaLayanan = namaLayanan;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void loadLayanan() {
		try {
			DBConnection.getInstance().connect();
			Connection con = DBConnection.getInstance().getConnection();
			Statement stat = con.createStatement();
			ResultSet s = stat.executeQuery("exec dbo.viewLayanan");
			System.out.println("s ="+ s);
			la = new ArrayList<Layanan>();
			while (s.next()) {
				Layanan l = new Layanan();
				l.setIdLayanan(s.getInt(1));
				l.setNamaLayanan(s.getString(2));
				l.setKeterangan(s.getString(3));
				l.setUrl(s.getString(4));
				la.add(l);
			}
			stat.close();
		} catch (SQLException ex) {
			System.out.println("ERROR KARENA : " + ex.getMessage());
		}
	}
}
