package org.ifunpas.fo.cs.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ifunpas.fo.cs.util.DBConnection;

public class PesanPelanggan {
	private int idPesan;
	private String pesan;
	private String namaPelanggan;
	private String email;
	private String jenisPesan;
	private ArrayList<PesanPelanggan> pp;

	public PesanPelanggan() {
	}

	public PesanPelanggan(int idPesan, String pesan, String namaPelanggan,
			String email, String jenisPesan) {
		this.idPesan = idPesan;
		this.pesan = pesan;
		this.namaPelanggan = namaPelanggan;
		this.email = email;
		this.jenisPesan = jenisPesan;
	}

	public ArrayList<PesanPelanggan> getPp() {
		loadPesanPelanggan();
		return pp;
	}

	public int getIdPesan() {
		return idPesan;
	}

	public void setIdPesan(int idPesan) {
		this.idPesan = idPesan;
	}

	public String getPesan() {
		return pesan;
	}

	public void setPesan(String pesan) {
		this.pesan = pesan;
	}

	public String getNamaPelanggan() {
		return namaPelanggan;
	}

	public void setNamaPelanggan(String namaPelanggan) {
		this.namaPelanggan = namaPelanggan;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJenisPesan() {
		return jenisPesan;
	}

	public void setJenisPesan(String jenisPesan) {
		this.jenisPesan = jenisPesan;
	}

	public void insertPesanPelanggan() {
		try {
			DBConnection.getInstance().connect();
			Connection con = DBConnection.getInstance().getConnection();
			Statement stat = con.createStatement();
			int status = stat.executeUpdate("exec dbo.insertPesanPelanggan '"
					+ getPesan() + "','" + getNamaPelanggan() + "','"
					+ getEmail() + "','" + getJenisPesan() + "'");
			stat.close();
			clear();
			if (status == 1) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Berhasil!", "Data Telah Disimpan"));
			}
		} catch (SQLException ex) {
			System.out.println("ERROR KARENA : " + ex.getMessage());
		}
	}

	public void clear() {
		namaPelanggan = "";
		pesan = "";
		jenisPesan = "";
		email = "";
	}

	public void loadPesanPelanggan() {
		try {
			DBConnection.getInstance().connect();
			Connection con = DBConnection.getInstance().getConnection();
			Statement stat = con.createStatement();
			ResultSet s = stat.executeQuery("exec dbo.loadPesanPelanggan");
			System.out.println("s =" + s);
			pp = new ArrayList<PesanPelanggan>();
			while (s.next()) {
				PesanPelanggan p = new PesanPelanggan();
				p.setIdPesan(s.getInt(1));
				p.setPesan(s.getString(2));
				p.setNamaPelanggan(s.getString(3));
				p.setEmail(s.getString(4));
				p.setJenisPesan(s.getString(5));
				pp.add(p);
			}
			stat.close();
		} catch (SQLException ex) {
			System.out.println("ERROR KARENA : " + ex.getMessage());
		}
	}
}
