package tablas;

public class Nomina {
	
	private String codNomina, nif_Empleado, ss, categoria, grupoCotizacion, mes, anio;
	private int salarioBase, horasExtra, dietas;	

	public Nomina(String codNomina, String nif_Empleado, String ss, String categoria, String grupoCotizacion,
			String mes, String anio, int salarioBase, int horasExtra, int dietas) {
		super();
		this.codNomina = codNomina;
		this.nif_Empleado = nif_Empleado;
		this.ss = ss;
		this.categoria = categoria;
		this.grupoCotizacion = grupoCotizacion;
		this.mes = mes;
		this.anio = anio;
		this.salarioBase = salarioBase;
		this.horasExtra = horasExtra;
		this.dietas = dietas;
	}
	
	public String getCodNomina() {
		return codNomina;
	}
	public void setCodNomina(String codNomina) {
		this.codNomina = codNomina;
	}
	public String getNif_Empleado() {
		return nif_Empleado;
	}
	public void setNif_Empleado(String nif_Empleado) {
		this.nif_Empleado = nif_Empleado;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public int getSalarioBase() {
		return salarioBase;
	}
	public void setSalarioBase(int salarioBase) {
		this.salarioBase = salarioBase;
	}
	public int getHorasExtra() {
		return horasExtra;
	}
	public void setHorasExtra(int horasExtra) {
		this.horasExtra = horasExtra;
	}
	public int getDietas() {
		return dietas;
	}
	public void setDietas(int dietas) {
		this.dietas = dietas;
	}

	public String getSs() {
		return ss;
	}
	public void setSs(String ss) {
		this.ss = ss;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getGrupoCotizacion() {
		return grupoCotizacion;
	}
	public void setGrupoCotizacion(String grupoCotizacion) {
		this.grupoCotizacion = grupoCotizacion;
	}
}
