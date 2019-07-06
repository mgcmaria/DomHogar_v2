package tablas;

import java.sql.Date;

public class Ventas {
	
	private int importeServicio, udsServicio, Total, codLinea;
	private String numFactura, codServicio, nombreServicio, nombre, dni_Cliente;
	private Date fecha;
	
	public Ventas(String numFactura, String codServicio, String nombreServicio, int udsServicio, int importeServicio,int Total,
			String dni_Cliente, String nombre, Date fecha) {
		
		super();
		this.importeServicio = importeServicio;
		this.udsServicio = udsServicio;
		this.numFactura = numFactura;
		this.codServicio = codServicio;
		this.nombreServicio = nombreServicio;
		this.nombre = nombre;
		this.dni_Cliente = dni_Cliente;
		this.fecha = fecha;
		this.setTotal(Total);		
	}
	
	public Ventas(String numFactura, String codServicio, String nombreServicio, int udsServicio, int importeServicio,int Total,
			String dni_Cliente, String nombre, Date fecha, int codLinea) {
		
		super();
		this.importeServicio = importeServicio;
		this.udsServicio = udsServicio;
		this.numFactura = numFactura;
		this.codServicio = codServicio;
		this.nombreServicio = nombreServicio;
		this.nombre = nombre;
		this.dni_Cliente = dni_Cliente;
		this.fecha = fecha;
		this.setTotal(Total);
		this.codLinea = codLinea;
	}

	public int getCodLinea() {
		return codLinea;
	}

	public void setCodLinea(int codLinea) {
		this.codLinea = codLinea;
	}

	public int getImporteServicio() {
		return importeServicio;
	}

	public void setImporteServicio(int importeServicio) {
		this.importeServicio = importeServicio;
	}

	public int getUdsServicio() {
		return udsServicio;
	}

	public void setUdsServicio(int udsServicio) {
		this.udsServicio = udsServicio;
	}

	public int getTotal() {
		return Total;
	}

	public void setTotal(int total) {
		Total = total;
	}

	public String getNumFactura() {
		return numFactura;
	}

	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}

	public String getCodServicio() {
		return codServicio;
	}

	public void setCodServicio(String codServicio) {
		this.codServicio = codServicio;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni_Cliente() {
		return dni_Cliente;
	}

	public void setDni_Cliente(String dni_Cliente) {
		this.dni_Cliente = dni_Cliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	
}