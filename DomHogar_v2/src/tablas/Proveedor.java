package tablas;

public class Proveedor {
	
	private String codProveedor, nombreProveedor, mail;

	public Proveedor(String codproveedor, String nombreProveedor, String mail) {
		super();
		this.codProveedor = codproveedor;
		this.nombreProveedor = nombreProveedor;
		this.mail = mail;
	}


	public String getCodproveedor() {
		return codProveedor;
	}

	public void setCodproveedor(String codproveedor) {
		this.codProveedor = codproveedor;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
