package tablas;

public class Producto {
	
	private String 	cod_Producto, nombreProducto;
	private int stock, importeCompra, importeVenta;
	
	public Producto(String cod_Producto, String nombreProducto, int stock, int importeCompra, int importeVenta) {
		super();
		this.cod_Producto = cod_Producto;
		this.nombreProducto = nombreProducto;
		this.stock = stock;
		this.importeCompra = importeCompra;
		this.importeVenta = importeVenta;
	}

	public String getCod_Producto() {
		return cod_Producto;
	}

	public void setCod_Producto(String cod_Producto) {
		this.cod_Producto = cod_Producto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getImporteCompra() {
		return importeCompra;
	}

	public void setImporteCompra(int importeCompra) {
		this.importeCompra = importeCompra;
	}

	public int getImporteVenta() {
		return importeVenta;
	}

	public void setImporteVenta(int importeVenta) {
		this.importeVenta = importeVenta;
	}
	
	

}
