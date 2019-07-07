package tablas;

import java.sql.Date;

public class Compras {
	
	private int importeCompraProducto,cantidad, importeTotal, codLineaF;
	private String numAlbaran, codProducto, nomProducto, nomProveedor, codProveedor;
	private Date fechaAlbaran;
	
	
	public Compras(String numAlbaran, String codProducto, String nomProducto, int cantidad, int importeCompraProducto,  int importeTotal, 
			String codProveedor, String nomProveedor, Date fechaAlbaran) {
		super();
		this.numAlbaran = numAlbaran;
		this.importeCompraProducto = importeCompraProducto;
		this.cantidad = cantidad;
		this.codProveedor = codProveedor;
		this.codProducto = codProducto;
		this.nomProducto = nomProducto;
		this.nomProveedor = nomProveedor;
		this.fechaAlbaran = fechaAlbaran;
		this.setImporteTotal(importeTotal);
	}
	
	public Compras(String numAlbaran, String codProducto, String nomProducto, int cantidad, int importeCompraProducto,  int importeTotal, 
			String codProveedor, String nomProveedor, Date fechaAlbaran, int codLineaF) {
		super();
		this.numAlbaran = numAlbaran;
		this.importeCompraProducto = importeCompraProducto;
		this.cantidad = cantidad;
		this.codProveedor = codProveedor;
		this.codProducto = codProducto;
		this.nomProducto = nomProducto;
		this.nomProveedor = nomProveedor;
		this.fechaAlbaran = fechaAlbaran;
		this.setImporteTotal(importeTotal);
		this.codLineaF = codLineaF;
	}

	public String getNumAlbaran() {
		return numAlbaran;
	}
	public void setNumAlbaran(String numAlbaran) {
		this.numAlbaran = numAlbaran;
	}
	public int getImporteCompraProducto() {
		return importeCompraProducto;
	}
	public void setImporteCompraProducto(int importeCompraProducto) {
		this.importeCompraProducto = importeCompraProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getCodProveedor() {
		return codProveedor;
	}
	public void setCodProveedor(String codProveedor) {
		this.codProveedor = codProveedor;
	}
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getNomProducto() {
		return nomProducto;
	}
	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
	}
	public String getNomProveedor() {
		return nomProveedor;
	}
	public void setNomProveedor(String nomProveedor) {
		this.nomProveedor = nomProveedor;
	}
	public Date getFechaAlbaran() {
		return fechaAlbaran;
	}
	public void setFechaAlbaran(Date fechaAlbaran) {
		this.fechaAlbaran = fechaAlbaran;
	}
	public int getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(int importeTotal) {
		this.importeTotal = importeTotal;
	}

	public int getCodLineaF() {
		return codLineaF;
	}

	public void setCodLineaF(int codLineaF) {
		this.codLineaF = codLineaF;
	}
}
