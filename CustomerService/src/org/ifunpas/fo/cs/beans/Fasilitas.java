package org.ifunpas.fo.cs.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.ifunpas.fo.cs.util.DBConnection;

public class Fasilitas {

	private int idFasilitas;
	private String namaFasilitas;
	private String jenisFasilitas;
	private String keterangan;
	private String url;
	private ArrayList<Fasilitas> fas;

	public ArrayList<Fasilitas> getFas() {
		loadFasilitas();
		return fas;
	}

	public Fasilitas() {
	}

	public Fasilitas(int idFasilitas, String namaFasilitas,
			String jenisFasilitas, String keterangan, String url) {
		super();
		this.idFasilitas = idFasilitas;
		this.namaFasilitas = namaFasilitas;
		this.jenisFasilitas = jenisFasilitas;
		this.keterangan = keterangan;
		this.url = url;
	}

	public int getIdFasilitas() {
		return idFasilitas;
	}

	public void setIdFasilitas(int idFasilitas) {
		this.idFasilitas = idFasilitas;
	}

	public String getNamaFasilitas() {
		return namaFasilitas;
	}

	public void setNamaFasilitas(String namaFasilitas) {
		this.namaFasilitas = namaFasilitas;
	}

	public String getJenisFasilitas() {
		return jenisFasilitas;
	}

	public void setJenisFasilitas(String jenisFasilitas) {
		this.jenisFasilitas = jenisFasilitas;
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
	
	public void loadFasilitas() {
		try {
			DBConnection.getInstance().connect();
			Connection con = DBConnection.getInstance().getConnection();
			Statement stat = con.createStatement();
			ResultSet s = stat.executeQuery("exec dbo.viewFasilitas");
			System.out.println("s ="+ s);
			fas = new ArrayList<Fasilitas>();
			while (s.next()) {
				Fasilitas fs = new Fasilitas();
				fs.setIdFasilitas(s.getInt(1));
				fs.setNamaFasilitas(s.getString(2));
				fs.setJenisFasilitas(s.getString(3));
				fs.setKeterangan(s.getString(4));
				fs.setUrl(s.getString(5));
				fas.add(fs);
			}
			stat.close();
		} catch (SQLException ex) {
			System.out.println("ERROR KARENA : " + ex.getMessage());
		}
	}
}
