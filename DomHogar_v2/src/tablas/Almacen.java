package tablas;

public class Almacen {
	
	String codProducto, nomProducto;
	int udsCompradas, udsVendidas, total;
	
	public Almacen(String codProducto, String nomProducto, int udsCompradas, int udsVendidas, int total) {
		super();
		this.codProducto = codProducto;
		this.nomProducto = nomProducto;
		this.udsCompradas = udsCompradas;
		this.udsVendidas = udsVendidas;
		this.total = total;
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

	public int getUdsCompradas() {
		return udsCompradas;
	}

	public void setUdsCompradas(int udsCompradas) {
		this.udsCompradas = udsCompradas;
	}

	public int getUdsVendidas() {
		return udsVendidas;
	}

	public void setUdsVendidas(int udsVendidas) {
		this.udsVendidas = udsVendidas;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
