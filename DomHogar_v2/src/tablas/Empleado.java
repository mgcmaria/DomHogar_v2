package tablas;

public class Empleado {
	
	private String nombre, apellidos, email, nif, usuario, contrasena, perfil, ss, gruCotizacion;
	private int telefono;
	
	public Empleado(String nombre, String apellidos, String email, String nif, String usuario, String contrasena,
			String perfil, int telefono) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.nif = nif;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.perfil = perfil;
		this.telefono = telefono;
	}
	
	public Empleado(String nombre, String apellidos, String email, String nif, String usuario, String contrasena,
			String perfil, int telefono, String ss, String gruCotizacion) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.nif = nif;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.perfil = perfil;
		this.telefono = telefono;
		this.ss = ss;
		this.gruCotizacion = gruCotizacion;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}	
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

}
