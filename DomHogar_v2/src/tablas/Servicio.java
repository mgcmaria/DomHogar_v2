package tablas;

public class Servicio {
	
	private String codServicio, codproducto, nif_Empleado, nombreServicio;
	private int importeServicio;
	
	public Servicio(String codServicio, String codproducto, String nif_Empleado, String nombreServicio,
			int importeServicio) {
		super();
		this.codServicio = codServicio;
		this.codproducto = codproducto;
		this.nif_Empleado = nif_Empleado;
		this.nombreServicio = nombreServicio;
		this.importeServicio = importeServicio;
	}

	public String getCodServicio() {
		return codServicio;
	}

	public void setCodServicio(String codServicio) {
		this.codServicio = codServicio;
	}

	public String getCodproducto() {
		return codproducto;
	}

	public void setCodproducto(String codproducto) {
		this.codproducto = codproducto;
	}

	public String getNif_Empleado() {
		return nif_Empleado;
	}

	public void setNif_Empleado(String nif_Empleado) {
		this.nif_Empleado = nif_Empleado;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public int getImporteServicio() {
		return importeServicio;
	}

	public void setImporteServicio(int importeServicio) {
		this.importeServicio = importeServicio;
	}
	
	

}
