package tablas;

public class Linea_Albaran {
	
	private String codlinea, codproducto, numAlbaran;
	private int cantidad;
	
	public Linea_Albaran(String codlinea, String codproducto, String numAlbaran, int cantidad) {
		super();
		this.codlinea = codlinea;
		this.codproducto = codproducto;
		this.numAlbaran = numAlbaran;
		this.cantidad = cantidad;
	}

	public String getCodlinea() {
		return codlinea;
	}

	public void setCodlinea(String codlinea) {
		this.codlinea = codlinea;
	}

	public String getCodproducto() {
		return codproducto;
	}

	public void setCodproducto(String codproducto) {
		this.codproducto = codproducto;
	}

	public String getNumAlbaran() {
		return numAlbaran;
	}

	public void setNumAlbaran(String numAlbaran) {
		this.numAlbaran = numAlbaran;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
