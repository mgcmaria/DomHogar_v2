package tablas;

import java.sql.Date;

public class Factura {
	
	private String 	numFactura, dni_Cliente;
	private int importeFactura;
	private Date fecha;
	
	public Factura(String numFactura, String dni_Cliente, int importeFactura, Date fecha) {
		super();
		this.numFactura = numFactura;
		this.dni_Cliente = dni_Cliente;
		this.importeFactura = importeFactura;
		this.fecha = fecha;
	}

	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public String getDni_Cliente() {
		return dni_Cliente;
	}

	public void setDni_Cliente(String dni_Cliente) {
		this.dni_Cliente = dni_Cliente;
	}

	public int getImporteFactura() {
		return importeFactura;
	}

	public void setImporteFactura(int importeFactura) {
		this.importeFactura = importeFactura;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

}
