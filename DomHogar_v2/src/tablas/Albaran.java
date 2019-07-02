package tablas;

import java.sql.Date;

public class Albaran {
	
	private String numAlbaran, codProveedor;
	private int importeAlbaran;
	private Date fecha;
	
	
	public Albaran(String numAlbaran, String codProveedor, int importeAlbaran, Date fecha) {
		super();
		this.numAlbaran = numAlbaran;
		this.codProveedor = codProveedor;
		this.importeAlbaran = importeAlbaran;
		this.fecha = fecha;
	}


	public String getNumAlbaran() {
		return numAlbaran;
	}


	public void setNumAlbaran(String numAlbaran) {
		this.numAlbaran = numAlbaran;
	}


	public String getCodProveedor() {
		return codProveedor;
	}


	public void setCodProveedor(String codProveedor) {
		this.codProveedor = codProveedor;
	}


	public int getImporteAlbaran() {
		return importeAlbaran;
	}


	public void setImporteAlbaran(int importeAlbaran) {
		this.importeAlbaran = importeAlbaran;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	

}
