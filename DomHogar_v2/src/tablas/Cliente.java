package tablas;

public class Cliente {
	
	private String dni_Cliente, nombre, email;
	private int telefono;
	
	
	public Cliente(String dni_Cliente, String nombre, String email, int telefono) {
		super();
		this.dni_Cliente = dni_Cliente;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
	}


	public String getDni_Cliente() {
		return dni_Cliente;
	}


	public void setDni_Cliente(String dni_Cliente) {
		this.dni_Cliente = dni_Cliente;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
}
