package tablas;

public class Linea_Factura {
	
	private String 	codLinea, codServicio, numFactura;
	private int udsServicio;
	
	public Linea_Factura(String codLinea, String codServicio, String numFactura, int udsServicio) {
		super();
		this.codLinea = codLinea;
		this.codServicio = codServicio;
		this.numFactura = numFactura;
		this.udsServicio = udsServicio;
	}

	public String getCodLinea() {
		return codLinea;
	}

	public void setCodLinea(String codLinea) {
		this.codLinea = codLinea;
	}

	public String getCodServicio() {
		return codServicio;
	}

	public void setCodServicio(String codServicio) {
		this.codServicio = codServicio;
	}

	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public int getUdsServicio() {
		return udsServicio;
	}

	public void setUdsServicio(int udsServicio) {
		this.udsServicio = udsServicio;
	}

	

}
