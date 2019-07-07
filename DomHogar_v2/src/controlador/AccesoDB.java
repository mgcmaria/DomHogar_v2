package controlador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import tablas.Almacen;
import tablas.CRM;
import tablas.Cliente;
import tablas.Compras;
import tablas.Empleado;
import tablas.Nomina;
import tablas.Producto;
import tablas.Proveedor;
import tablas.Servicio;
import tablas.Ventas;

public class AccesoDB {
	
	public static Connection conexion() {
		
		// Paso 1: Cargar el driver
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection con = null;

		// Paso 2: Establecer conexion con la base de datos
		try {
			con = DriverManager.getConnection("jdbc:mysql://85.10.205.173:3306/tfg_domhogar",
					"tfg_domhogar", "tfg_domhogar");			
		} catch (SQLException e) {			
			e.getMessage();
			return null;
		}		
		return  con;
	}

	public static void cerrarConexion(Connection conexion) {
		
		try {
			conexion.close();
		} catch (SQLException e) {
			e.getMessage();
			return;
		}		
	}	
	
	//DATOS EMPLEADOS

	public static ArrayList<Empleado> datosEmpleado(Connection conexion ) {

		ArrayList<Empleado> lista_empleados = new ArrayList<Empleado>();
		
		Empleado empleado;
		
		try {			

			Statement sentencia = conexion.createStatement(); // Creamos sentencia con Statement
			// Consulta SQL con resulset
			ResultSet rs = sentencia.executeQuery("SELECT * FROM EMPLEADO");

			// Mientras haya registros anadimos al ArrayList
			while (rs.next()) { 
				
				String nombre = rs.getString("NOMBRE");
				String apellidos = rs.getString("APELLIDOS");
				String email = rs.getString("EMAIL");
				String nif = rs.getString("NIF_EMPLEADO");
				String usuario = rs.getString("USUARIO");
				String contrasena = rs.getString("CONTRASENA");
				String perfil = rs.getString("PERFIL");
				int telefono = rs.getInt("TELEFONO");
				
				empleado = new Empleado(nombre, apellidos, email, nif, usuario, contrasena, perfil, telefono);
				
				lista_empleados.add(empleado);				
			}
			
		} catch (SQLException e) {
			e.getMessage();
		}

		return lista_empleados;
	}

	public static String[][] obtenerMatrizEmpleados() {
		Connection conexion = conexion();				
		
		ArrayList<Empleado> listaEmpleados = AccesoDB.datosEmpleado(conexion);
		
		String matrizInfo[][] = new String[listaEmpleados.size()][5];
		
		for (int i = 0; i < listaEmpleados.size(); i++) {
			matrizInfo[i][0] = listaEmpleados.get(i).getNombre()+"";
			matrizInfo[i][1] = listaEmpleados.get(i).getApellidos()+"";
			matrizInfo[i][2] = listaEmpleados.get(i).getEmail()+"";
			matrizInfo[i][3] = listaEmpleados.get(i).getNif()+"";
			matrizInfo[i][4] = listaEmpleados.get(i).getTelefono()+"";
		}		
		return matrizInfo;
	}

	public static int insertarEmpleado(ArrayList<Empleado> nuevoEmpleado, Connection conexion) {
		
		int afectados = 0;
		
		try {
			//Almacenamos en un String la Sentencia SQL
			String sql = "INSERT INTO EMPLEADO (NIF_EMPLEADO, NOMBRE, APELLIDOS, "
					+ "TELEFONO, EMAIL, USUARIO, CONTRASENA, PERFIL) " 
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
			String nif = null;
			String nombre = null;
			String apellidos = null;
			int telefono = 0;
			String email = null;
			String usuario = null;
			String pass = null ;
			String perfil = null;
			
			for (Empleado empleado : nuevoEmpleado) {
				nif = empleado.getNif();
				nombre = empleado.getNombre();
				apellidos = empleado.getApellidos();
				telefono = empleado.getTelefono();
				email = empleado.getEmail();
				usuario = empleado.getUsuario();
				pass = empleado.getContrasena();
				perfil = empleado.getPerfil();
			}		
			
			//Con PreparedStatement recogemos los valores introducidos			
			PreparedStatement sentencia;
			sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, nif);
			sentencia.setString(2, nombre);
			sentencia.setString(3, apellidos);
			sentencia.setInt(4, telefono);
			sentencia.setString(5, email);
			sentencia.setString(6, usuario);
			sentencia.setString(7, pass);
			sentencia.setString(8, perfil);
			
			afectados = sentencia.executeUpdate(); //Ejecutamos la inserciÃ¯Â¿Â½n

		} catch (SQLException e) {
			e.getMessage();
		}
		return afectados;		
	}

	//DATOS PROVEEDORES
	
	public static ArrayList<Proveedor> datosProveedor(Connection conexion ) {

		ArrayList<Proveedor> lista_proveedores = new ArrayList<Proveedor>();
		
		Proveedor proveedor;
		
		try {			

			Statement sentencia = conexion.createStatement(); // Creamos sentencia con Statement
			// Consulta SQL con resulset
			ResultSet rs = sentencia.executeQuery("SELECT * FROM PROVEEDOR");

			// Mientras haya registros anadimos al ArrayList
			while (rs.next()) { 
				
				String codProveedor = rs.getString("CODPROVEEDOR");
				String nombreProveedor = rs.getString("NOMBREPROVEEDOR");
				String mail = rs.getString("MAIL");
				
				proveedor = new Proveedor(codProveedor, nombreProveedor, mail);
				
				lista_proveedores.add(proveedor);				
			}
			
		} catch (SQLException e) {
			e.getMessage();
		}

		return lista_proveedores;
	}
	
	public static String[][] obtenerMatrizProveedores() {
		
		Connection conexion = AccesoDB.conexion();

		ArrayList<Proveedor> listaProveedores = AccesoDB.datosProveedor(conexion);

		String matrizInfoPr[][] = new String[listaProveedores.size()][3];

		for (int i = 0; i < listaProveedores.size(); i++) {
			matrizInfoPr[i][0] = listaProveedores.get(i).getCodproveedor() + "";
			matrizInfoPr[i][1] = listaProveedores.get(i).getNombreProveedor() + "";
			matrizInfoPr[i][2] = listaProveedores.get(i).getMail() + "";

		}
		return matrizInfoPr;
	}
	
	
	//DATOS CLIENTES
	public static ArrayList<Cliente> datosCliente(Connection conexion ) {

		ArrayList<Cliente> lista_clientes = new ArrayList<Cliente>();
		
		Cliente cliente;
		
		try {			

			Statement sentencia = conexion.createStatement(); // Creamos sentencia con Statement
			// Consulta SQL con resulset
			ResultSet rs = sentencia.executeQuery("SELECT * FROM CLIENTE");

			// Mientras haya registros anadimos al ArrayList
			while (rs.next()) { 
				
				String dni_Cliente = rs.getString("DNI_CLIENTE");
				String nombre = rs.getString("NOMBRE");
				int telefono = rs.getInt("TELEFONO");
				String email = rs.getString("EMAIL");
				
				cliente = new Cliente(dni_Cliente, nombre, email, telefono);
				
				lista_clientes.add(cliente);				
			}
			
		} catch (SQLException e) {
			e.getMessage();
		}

		return lista_clientes;
	}
	
	
	public static String[][] obtenerMatrizClientes() {
		Connection conexion = AccesoDB.conexion();

		ArrayList<Cliente> listaClientes = AccesoDB.datosCliente(conexion);

		String matrizInfoCliente[][] = new String[listaClientes.size()][4];

		for (int i = 0; i < listaClientes.size(); i++) {
			matrizInfoCliente[i][0] = listaClientes.get(i).getDni_Cliente() + "";
			matrizInfoCliente[i][1] = listaClientes.get(i).getNombre() + "";
			matrizInfoCliente[i][2] = listaClientes.get(i).getEmail() + "";
			matrizInfoCliente[i][3] = listaClientes.get(i).getTelefono() + "";

		}
		return matrizInfoCliente;
	}
	
	public static int insertarCliente(ArrayList<Cliente> nuevoCliente, Connection conexion) {

		int afectados = 0;

		try {
			// Almacenamos en un String la Sentencia SQL
			String sql = "INSERT INTO CLIENTE (DNI_CLIENTE, NOMBRE, EMAIL, TELEFONO) " + "VALUES (?, ?, ?, ?)";

			String dni = null;
			String nombre = null;
			String email = null;
			int telefono = 0;

			for (Cliente cliente : nuevoCliente) {
				dni = cliente.getDni_Cliente();
				nombre = cliente.getNombre();
				email = cliente.getEmail();
				telefono = cliente.getTelefono();
			}

			// Con PreparedStatement recogemos los valores introducidos
			PreparedStatement sentencia;
			sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, dni);
			sentencia.setString(2, nombre);
			sentencia.setString(3, email);
			sentencia.setInt(4, telefono);

			afectados = sentencia.executeUpdate(); // Ejecutamos la inserciÃ¯Â¿Â½n

		} catch (SQLException e) {
			e.getMessage();
		}
		return afectados;
	}
	
	public static int actualizarCliente(String dni, String campo, String nuevoDato, Connection conexion) {
		
		int afectados = 0;
		
		// Almacenamos en un String la Sentencia SQL
		String sql = "UPDATE CLIENTE SET " +campo+"= '"+nuevoDato+"' WHERE DNI_CLIENTE='"+dni+"';";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			afectados = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return afectados;
	}

	public static int borrarCliente(String dni, Connection conexion) {
	
		int afectados = 0;
		
		// Almacenamos en un String la Sentencia SQL
		String sql = "DELETE FROM CLIENTE WHERE DNI_CLIENTE='"+dni+"';";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			afectados = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return afectados;		
	}
	
	//DATOS COMPRAS	
	public static String[][] obtenerMatrizCompras() {
		
		DecimalFormat formatea = new DecimalFormat("###,###.##");// Declaramos el formato de los numeros
		
		Connection conexion = AccesoDB.conexion();

		ArrayList<Compras> listaCompras = AccesoDB.datosCompras(conexion);

		String matrizInfoCompras[][] = new String[listaCompras.size()][9];

		for (int i = 0; i < listaCompras.size(); i++) {
			matrizInfoCompras[i][0] = listaCompras.get(i).getNumAlbaran()+"";
			matrizInfoCompras[i][1] = listaCompras.get(i).getCodProducto()+"";
			matrizInfoCompras[i][2] = listaCompras.get(i).getNomProducto()+"";
			matrizInfoCompras[i][3] = formatea.format(listaCompras.get(i).getCantidad())+"";
			matrizInfoCompras[i][4] = formatea.format(listaCompras.get(i).getImporteCompraProducto())+" €";
			matrizInfoCompras[i][5] = formatea.format(listaCompras.get(i).getImporteTotal())+" €";
			matrizInfoCompras[i][6] = listaCompras.get(i).getCodProveedor()+"";
			matrizInfoCompras[i][7] = listaCompras.get(i).getNomProveedor()+"";
			matrizInfoCompras[i][8] = listaCompras.get(i).getFechaAlbaran()+"";
		}
		return matrizInfoCompras;		
	}	

	

	public static ArrayList<Compras> datosCompras(Connection conexion) {
		
		ArrayList<Compras> lista_compras = new ArrayList<Compras>();
		
		Compras compras;		
		
		try {			

			Statement sentencia = conexion.createStatement(); // Creamos sentencia con Statement
			// Consulta SQL con resulset
			ResultSet rs = sentencia.executeQuery("SELECT la.codproducto, pro.nombreProducto,pro.importeCompra, la.cantidad, "
					+ "la.numAlbaran, a.fecha, a.codProveedor, p.nombreProveedor "
					+ "FROM LINEA_ALBARAN la JOIN PRODUCTO pro on la.codproducto = pro.cod_Producto "
					+ "JOIN ALBARAN a on la.numAlbaran = a.numAlbaran JOIN PROVEEDOR p on a.codProveedor = p.codproveedor ORDER BY la.numAlbaran");			

			// Mientras haya registros anadimos al ArrayList
			while (rs.next()) { 
				
				String numAlbaran = rs.getString("numAlbaran");
				String codProducto = rs.getString("codproducto");
				String nomProducto = rs.getString("nombreProducto");
				int importeCompraPro = rs.getInt("importeCompra");
				int cantidad = rs.getInt("cantidad");
				int cantidadTotal = importeCompraPro*cantidad;
				String codProveedor = rs.getString("codProveedor");
				String nomProveedor = rs.getString("nombreProveedor");
				Date fecha = rs.getDate("fecha");
				
				
				compras = new Compras(numAlbaran,codProducto,nomProducto, cantidad, importeCompraPro, cantidadTotal, codProveedor,  nomProveedor, fecha);
				
				lista_compras.add(compras);
							
			}
			
		} catch (SQLException e) {
			e.getMessage();
		}		
		
		return lista_compras;
	}
	
	
	//DATOS Ventas
		public static String[][] obtenerMatrizVentas() {
			
			DecimalFormat formatea = new DecimalFormat("###,###.##");// Declaramos el formato de los numeros
			
			Connection conexion = AccesoDB.conexion();

			ArrayList<Ventas> listaVentas = AccesoDB.datosVentas(conexion);

			String matrizInfoVentas[][] = new String[listaVentas.size()][9];

			for (int i = 0; i < listaVentas.size(); i++) {
				matrizInfoVentas[i][0] = listaVentas.get(i).getNumFactura()+"";
				matrizInfoVentas[i][1] = listaVentas.get(i).getCodServicio()+"";
				matrizInfoVentas[i][2] = listaVentas.get(i).getNombreServicio()+"";
				matrizInfoVentas[i][3] = formatea.format(listaVentas.get(i).getUdsServicio())+"";
				matrizInfoVentas[i][4] = formatea.format(listaVentas.get(i).getImporteServicio())+" €";
				matrizInfoVentas[i][5] = formatea.format(listaVentas.get(i).getTotal())+" €";
				matrizInfoVentas[i][6] = listaVentas.get(i).getDni_Cliente()+"";
				matrizInfoVentas[i][7] = listaVentas.get(i).getNombre()+"";
				matrizInfoVentas[i][8] = listaVentas.get(i).getFecha()+"";
			}
			return matrizInfoVentas;		
		}	
		
		
		public static ArrayList<Ventas> datosVentas(Connection conexion) {
			
			ArrayList<Ventas> lista_ventas = new ArrayList<Ventas>();
			
			Ventas ventas;
			
			try {			

				Statement sentencia = conexion.createStatement(); // Creamos sentencia con Statement
				// Consulta SQL con resulset
				ResultSet rs = sentencia.executeQuery("SELECT lf.codServicio, s.nombreServicio, lf.udsServicio, s.importeServicio,"
						+ "f.numFactura, f.fecha, c.dni_Cliente, c.nombre "
						+ "FROM LINEA_FACTURA lf JOIN SERVICIO s on s.codServicio = lf.codServicio "
						+ "JOIN FACTURA f on f.numFactura = lf.numFactura JOIN CLIENTE c on c.dni_Cliente = f.dni_Cliente");			

				// Mientras haya registros anadimos al ArrayList
				while (rs.next()) { 
					
					String numFactura = rs.getString("numFactura");
					String codServicio = rs.getString("codServicio");
					String nombreServicio = rs.getString("nombreServicio");
					int udsServicio = rs.getInt("udsServicio");
					int importeServicio = rs.getInt("importeServicio");
					int Total = importeServicio*udsServicio;
					String dni_Cliente = rs.getString("dni_Cliente");
					String nombre = rs.getString("nombre");
					Date fecha = rs.getDate("fecha");
					
					ventas = new Ventas(numFactura,codServicio,nombreServicio, udsServicio, importeServicio, Total, 
							nombre, dni_Cliente, fecha);
					
					lista_ventas.add(ventas);
								
				}
				
			} catch (SQLException e) {
				e.getMessage();
			}		
			
			return lista_ventas;
		}
		
		public static ArrayList<Servicio> datosServicio(Connection conexion) {
			
			ArrayList<Servicio> lista_Servicios = new ArrayList<Servicio>();
			
			Servicio s;
			
			try {			

				Statement sentencia = conexion.createStatement(); // Creamos sentencia con Statement
				// Consulta SQL con resulset
				ResultSet rs = sentencia.executeQuery("SELECT * FROM SERVICIO");

				// Mientras haya registros anadimos al ArrayList
				while (rs.next()) { 
					
					String cod_Servicio = rs.getString("codServicio");
					String cod_Producto = rs.getString("codproducto");
					String nif_Empleado = rs.getString("nif_Empleado");
					String nombreServicio = rs.getString("nombreServicio");
					int importeVenta = rs.getInt("importeServicio");
					
					s = new Servicio(cod_Servicio, cod_Producto, nif_Empleado, nombreServicio, importeVenta);
					
					
					lista_Servicios.add(s);				
				}
				
			} catch (SQLException e) {
				e.getMessage();
			}

			return lista_Servicios;
			
		}
		

	public static int actualizarEmpleado(String nif, String campo, String nuevoDato, Connection conexion) {
		
		int afectados = 0;
		
		// Almacenamos en un String la Sentencia SQL
		String sql = "UPDATE EMPLEADO SET " +campo+"= '"+nuevoDato+"' WHERE NIF_EMPLEADO='"+nif+"';";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			afectados = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return afectados;
	}

	public static int borrarEmpleado(String nif, Connection conexion) {
	
		int afectados = 0;
		
		// Almacenamos en un String la Sentencia SQL
		String sql = "DELETE FROM EMPLEADO WHERE NIF_EMPLEADO='"+nif+"';";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			afectados = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return afectados;		
	}
	
	public static int insertarProveedor(ArrayList<Proveedor> nuevoProveedor, Connection conexion) {

		int afectados = 0;

		try {
			// Almacenamos en un String la Sentencia SQL
			String sql = "INSERT INTO PROVEEDOR (CODPROVEEDOR, NOMBREPROVEEDOR, MAIL) " + "VALUES (?, ?, ?)";

			String codigo = null;
			String nombre = null;
			String mail = null;

			for (Proveedor proveedor : nuevoProveedor) {
				codigo = proveedor.getCodproveedor();
				nombre = proveedor.getNombreProveedor();
				mail = proveedor.getMail();
			}

			// Con PreparedStatement recogemos los valores introducidos
			PreparedStatement sentencia;
			sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, codigo);
			sentencia.setString(2, nombre);
			sentencia.setString(3, mail);

			afectados = sentencia.executeUpdate(); // Ejecutamos la inserciÃ¯Â¿Â½n

		} catch (SQLException e) {
			e.getMessage();
		}
		return afectados;
	}
	
	
	public static int actualizarProveedor(String cod, String campo, String nuevoDato, Connection conexion) {
		
		int afectados = 0;
		
		// Almacenamos en un String la Sentencia SQL
		String sql = "UPDATE PROVEEDOR SET " +campo+"= '"+nuevoDato+"' WHERE CODPROVEEDOR='"+cod+"';";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			afectados = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return afectados;
	}

	public static int borrarProveedor(String cod, Connection conexion) {
	
		int afectados = 0;
		
		// Almacenamos en un String la Sentencia SQL
		String sql = "DELETE FROM PROVEEDOR WHERE CODPROVEEDOR='"+cod+"';";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			afectados = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return afectados;		
	}
	
public static Boolean exportarFicheroProveedores(String user) {
		
		File f = new File("C:\\Users\\"+user+"\\proveedores.csv");
		
		Connection conexion = AccesoDB.conexion();
		
		ArrayList<Proveedor> lista_proveedor = datosProveedor(conexion);
		
		try {
			FileWriter ficheroProveedores = new FileWriter(f);
			
			ficheroProveedores.write("Código Proveedor,Nombre Proveedor, Mail");
			ficheroProveedores.write("\n");
			
			for (Proveedor proveedor : lista_proveedor) {
				
				ficheroProveedores.write(proveedor.getCodproveedor());
				ficheroProveedores.write(",");
				ficheroProveedores.write(proveedor.getNombreProveedor());
				ficheroProveedores.write(",");
				ficheroProveedores.write(proveedor.getMail());
				ficheroProveedores.write("\n");
			
			}
			
			ficheroProveedores.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	

	public static Boolean exportarFicheroEmpleados(String user) {
		
		File f = new File("C:\\Users\\"+user+"\\empleados.csv");
		
		Connection conexion = AccesoDB.conexion();
		
		ArrayList<Empleado> lista_empleados = datosEmpleado(conexion);
		
		try {
			FileWriter ficheroEmp = new FileWriter(f);
			
			ficheroEmp.write("NIF,Nombre,Apellidos,Email,Telefono,Usuario,Contrasena,Perfil");
			ficheroEmp.write("\n");
			
			for (Empleado empleado : lista_empleados) {
				
				ficheroEmp.write(empleado.getNif());
				ficheroEmp.write(",");
				ficheroEmp.write(empleado.getNombre());
				ficheroEmp.write(",");
				ficheroEmp.write(empleado.getApellidos());
				ficheroEmp.write(",");
				ficheroEmp.write(empleado.getEmail());
				ficheroEmp.write(",");
				ficheroEmp.write(Integer.toString(empleado.getTelefono()));
				ficheroEmp.write(",");
				ficheroEmp.write(empleado.getUsuario());
				ficheroEmp.write(",");
				ficheroEmp.write(empleado.getContrasena());
				ficheroEmp.write(",");
				ficheroEmp.write(empleado.getPerfil());
				ficheroEmp.write("\n");
			
			}
			
			ficheroEmp.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
public static Boolean exportarFicheroCompras(String user) {
		
		File f = new File("C:\\Users\\"+user+"\\compras.csv");
		
		Connection conexion = AccesoDB.conexion();
		
		ArrayList<Compras> lista_compras = datosCompras(conexion);
		
		try {
			FileWriter ficheroCompras = new FileWriter(f);
			
			ficheroCompras.write("Numero Albaran,Nombre,Importe Producto,Cantidad,Codigo Proveedor" +
			"Codigo Producto,Nombre Producto,Fecha, Nombre Proveedor, Importe Total");
			ficheroCompras.write("\n");
			
			for (Compras compras : lista_compras) {
				
				ficheroCompras.write(compras.getNumAlbaran());
				ficheroCompras.write(",");
				ficheroCompras.write(Integer.toString(compras.getImporteCompraProducto()));
				ficheroCompras.write(",");
				ficheroCompras.write(Integer.toString(compras.getCantidad()));
				ficheroCompras.write(",");
				ficheroCompras.write(compras.getCodProveedor());
				ficheroCompras.write(",");
				ficheroCompras.write(compras.getCodProducto());
				ficheroCompras.write(",");
				ficheroCompras.write(compras.getNomProducto());
				ficheroCompras.write(",");
				DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
				ficheroCompras.write(fecha.format(compras.getFechaAlbaran()));
				ficheroCompras.write(",");
				ficheroCompras.write(compras.getNomProveedor());
				ficheroCompras.write(",");
				ficheroCompras.write(Integer.toString(compras.getImporteTotal()));
				ficheroCompras.write("\n");
			
			}
			
			ficheroCompras.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}

	public static Boolean exportarFicheroVentas(String user) {
		
		File f = new File("C:\\Users\\"+user+"\\ventas.csv");
		
		Connection conexion = AccesoDB.conexion();
		
		ArrayList<Ventas> lista_ventas = datosVentas(conexion);
		
		try {
			FileWriter ficheroVentas = new FileWriter(f);
			
			ficheroVentas.write("Numero Factura,Importe Venta Servicio,Cantidad Total,DNI Cliente,Codigo Servicio" +
			"Nombre Servicio,Fecha,Nombre, Importe Factura");
			ficheroVentas.write("\n");
			
			for (Ventas ventas : lista_ventas) {
				
				ficheroVentas.write(ventas.getNumFactura());
				ficheroVentas.write(",");
				ficheroVentas.write(Integer.toString(ventas.getImporteServicio()));
				ficheroVentas.write(",");
				ficheroVentas.write(Integer.toString(ventas.getUdsServicio()));
				ficheroVentas.write(",");
				ficheroVentas.write(ventas.getDni_Cliente());
				ficheroVentas.write(",");
				ficheroVentas.write(ventas.getCodServicio());
				ficheroVentas.write(",");
				ficheroVentas.write(ventas.getNombreServicio());
				ficheroVentas.write(",");
				DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
				ficheroVentas.write(fecha.format(ventas.getFecha()));
				ficheroVentas.write(",");
				ficheroVentas.write(ventas.getNombre());
				ficheroVentas.write(",");
				ficheroVentas.write(Integer.toString(ventas.getTotal()));
				ficheroVentas.write("\n");			
			}
			
			ficheroVentas.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
public static Boolean exportarFicheroAlmacen(String user) {
		
		File f = new File("C:\\Users\\"+user+"\\almacen.csv");
		
		Connection conexion = AccesoDB.conexion();
		
		ArrayList<Almacen> lista_almacen = datosAlmacen(conexion);
		
		try {
			FileWriter ficheroAlmacen = new FileWriter(f);
			
			ficheroAlmacen.write("Codigo Producto,Nombre Producto,Unidades Compradas,Unidades Vendidas,Total");
			ficheroAlmacen.write("\n");
			
			for (Almacen almacen : lista_almacen) {
				
				ficheroAlmacen.write(almacen.getCodProducto());
				ficheroAlmacen.write(",");
				ficheroAlmacen.write(almacen.getNomProducto());
				ficheroAlmacen.write(",");
				ficheroAlmacen.write(Integer.toString(almacen.getUdsCompradas()));
				ficheroAlmacen.write(",");
				ficheroAlmacen.write(Integer.toString(almacen.getUdsVendidas()));
				ficheroAlmacen.write(",");
				ficheroAlmacen.write(Integer.toString(almacen.getTotal()));
				ficheroAlmacen.write("\n");
			
			}
			
			ficheroAlmacen.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	

	public static Boolean exportarFicheroClientes(String user) {
	
	File f = new File("C:\\Users\\"+user+"\\clientes.csv");
	
	Connection conexion = AccesoDB.conexion();
	
	ArrayList<Cliente> lista_clientes = datosCliente(conexion);
	
	try {
		FileWriter ficheroClientes = new FileWriter(f);
		
		ficheroClientes.write("DNI Cliente,Nombre Cliente, Mail, Teléfono");
		ficheroClientes.write("\n");
		
		for (Cliente cliente : lista_clientes) {
			
			ficheroClientes.write(cliente.getDni_Cliente());
			ficheroClientes.write(",");
			ficheroClientes.write(cliente.getNombre());
			ficheroClientes.write(",");
			ficheroClientes.write(cliente.getEmail());
			ficheroClientes.write(",");
			ficheroClientes.write(Integer.toString(cliente.getTelefono()));
			ficheroClientes.write("\n");
		
		}
		
		ficheroClientes.close();
		
	} catch (IOException e) {
		e.printStackTrace();
		return false;
	}
	return true;
	
}

	public static ArrayList<Producto> datosProducto(Connection conexion) {
		
		ArrayList<Producto> lista_productos = new ArrayList<Producto>();
		
		Producto p;
		
		try {			

			Statement sentencia = conexion.createStatement(); // Creamos sentencia con Statement
			// Consulta SQL con resulset
			ResultSet rs = sentencia.executeQuery("SELECT * FROM PRODUCTO");

			// Mientras haya registros anadimos al ArrayList
			while (rs.next()) { 
				
				String cod_Producto = rs.getString("COD_PRODUCTO");
				String nombreProducto = rs.getString("NOMBREPRODUCTO");
				int stock = rs.getInt("STOCK");
				int importeCompra = rs.getInt("IMPORTECOMPRA");
				int importeVenta = rs.getInt("IMPORTEVENTA");
				
				p = new Producto(cod_Producto, nombreProducto, stock, importeCompra, importeVenta);
				
				lista_productos.add(p);				
			}
			
		} catch (SQLException e) {
			e.getMessage();
		}

		return lista_productos;
		
	}

	public static int insertarCompra(ArrayList<Compras> nuevaCompra, Connection conexion) {
		
		int afectados1 = 0;
		
		try {
			// Almacenamos en un String la Sentencia SQL
			String sql = "INSERT INTO ALBARAN (CODPROVEEDOR, FECHA, NUMALBARAN) " + "VALUES (?, ?, ?)";

			String codigoProveedor = null;
			Date fecha = null;
			String numAlbaran = null;

			for (Compras c : nuevaCompra) {
				codigoProveedor = c.getCodProveedor();
				fecha = c.getFechaAlbaran();
				numAlbaran = c.getNumAlbaran();
			}
	
			// Con PreparedStatement recogemos los valores introducidos
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, codigoProveedor);
			sentencia.setDate(2, fecha);
			sentencia.setString(3, numAlbaran);

			afectados1 = sentencia.executeUpdate(); // Ejecutamos la inserciÃ¯Â¿Â½n

		} catch (SQLException e) {
			e.getMessage();
		}
		return afectados1;
	}

	public static int insertarLineaAlbaran(ArrayList<Compras> nuevaCompra, Connection conexion) {
		
		int afectados = 0;
		
		try {
			// Almacenamos en un String la Sentencia SQL
			String sql = "INSERT INTO LINEA_ALBARAN (CANTIDAD, CODPRODUCTO, NUMALBARAN) " + "VALUES (?, ?, ?)";

			int cantidad = 0;
			String codProducto = null;
			String numAlbaran = null;

			for (Compras c : nuevaCompra) {
				cantidad = c.getCantidad();
				codProducto = c.getCodProducto();
				numAlbaran = c.getNumAlbaran();
			}

			// Con PreparedStatement recogemos los valores introducidos
			PreparedStatement sentencia;
			sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, cantidad);
			sentencia.setString(2, codProducto);
			sentencia.setString(3, numAlbaran);

			afectados = sentencia.executeUpdate(); // Ejecutamos la inserciÃ¯Â¿Â½n
			
		} catch (SQLException e) {
			e.getMessage();
		}
		return afectados;
	}

	
	public static int insertarVenta(ArrayList<Ventas> nuevaVenta, Connection conexion) {
		
		int afectados1 = 0;
		
		try {
			// Almacenamos en un String la Sentencia SQL
			String sql = "INSERT INTO FACTURA (NUMFACTURA, DNI_CLIENTE, FECHA) " + "VALUES (?, ?, ?)";

			String numFactura = null;
			String dniCliente = null;
			Date fecha = null;


			for (Ventas v : nuevaVenta) {
				numFactura = v.getNumFactura();	
				dniCliente = v.getDni_Cliente();
				fecha = v.getFecha();

			}
			
			System.out.println(numFactura);

			// Con PreparedStatement recogemos los valores introducidos
			PreparedStatement sentencia;
			sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, numFactura);
			sentencia.setString(2, dniCliente);
			sentencia.setDate(3, fecha);

			afectados1 = sentencia.executeUpdate(); // Ejecutamos la inserciÃ¯Â¿Â½n

		} catch (SQLException e) {
			e.getMessage();
		}
		System.out.println(afectados1);
		return afectados1;
	}

	public static int insertarLineaFactura(ArrayList<Ventas> nuevaVenta, Connection conexion) {
		
		int afectados = 0;
		
		try {
			// Almacenamos en un String la Sentencia SQL
			String sql = "INSERT INTO LINEA_FACTURA (CODSERVICIO, NUMFACTURA, UDSSERVICIO, IMPORTESERVICIO) " + "VALUES (?, ?, ?, ?)";

			String codServicio = null;
			String numFactura = null;
			int udServicio = 0;
			int importeServicio = 0;

			for (Ventas v : nuevaVenta) {

				codServicio = v.getCodServicio();
				numFactura = v.getNumFactura();
				udServicio = v.getUdsServicio();
				importeServicio = v.getImporteServicio();
			}

			// Con PreparedStatement recogemos los valores introducidos
			PreparedStatement sentencia;
			sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, codServicio);
			sentencia.setString(2, numFactura);
			sentencia.setInt(3, udServicio);
			sentencia.setInt(4, importeServicio);

			afectados = sentencia.executeUpdate(); // Ejecutamos la inserciÃ¯Â¿Â½n

		} catch (SQLException e) {
			e.getMessage();
		}
		return afectados;
	}
	
	//STOCK
	public static String[][] obtenerMatrizStock() {
		
		Connection conexion = AccesoDB.conexion();
		
		ArrayList<Almacen> listaAlmacen = AccesoDB.datosAlmacen(conexion);

		String matrizInfoAlmacen[][] = new String[listaAlmacen.size()][5];

		for (int i = 0; i < listaAlmacen.size(); i++) {
			matrizInfoAlmacen[i][0] = listaAlmacen.get(i).getCodProducto()+"";
			matrizInfoAlmacen[i][1] = listaAlmacen.get(i).getNomProducto()+"";
			matrizInfoAlmacen[i][2] = listaAlmacen.get(i).getUdsCompradas()+"";
			matrizInfoAlmacen[i][3] = listaAlmacen.get(i).getUdsVendidas()+"";
			matrizInfoAlmacen[i][4] = listaAlmacen.get(i).getTotal()+"";
		}
		return matrizInfoAlmacen;		
	}	
	
	public static ArrayList<Almacen> datosAlmacen(Connection conexion) {
		
		
		ArrayList<Almacen> lista_Almacen = new ArrayList<Almacen>();
		
		Almacen almacen;
		
		try {			

			Statement sentencia = conexion.createStatement(); 
			ResultSet rs = sentencia.executeQuery("SELECT p.cod_Producto, p.nombreProducto, la.cantidad, lf.udsServicio "
					+ "from LINEA_FACTURA lf , PRODUCTO p join LINEA_ALBARAN la on p.cod_Producto = la.codproducto");		

			while (rs.next()) { 
				
				String codProducto = rs.getString("cod_Producto");
				String nomProducto = rs.getString("nombreProducto");
				int udsCompradas = rs.getInt("cantidad");
				int udsVendidas = rs.getInt("udsServicio");
				int total = udsCompradas-udsVendidas;
				
				almacen = new Almacen(codProducto,nomProducto, udsCompradas, udsVendidas, total);

				lista_Almacen.add(almacen);
							
			}
			
		} catch (SQLException e) {
			e.getMessage();
		}		
		
		return lista_Almacen;
	}
	
	public static ArrayList<Almacen> datosAlmacenResumen(Connection conexion) {
		
		
		ArrayList<Almacen> lista_Almacen = new ArrayList<Almacen>();
		
		Almacen almacen;
		
		try {			

			Statement sentencia = conexion.createStatement(); 
			ResultSet rs = sentencia.executeQuery("SELECT p.cod_Producto, p.nombreProducto, sum(la.cantidad) as sum_cant, "
					+ "sum(lf.uds_Producto) as sum_uds from PRODUCTO p join LINEA_ALBARAN la on p.cod_Producto = la.codproducto "
					+ "join LINEA_FACTURA lf on lf.cod_Producto = la.codproducto group by cod_Producto");		

			while (rs.next()) { 
				
				String codProducto = rs.getString("cod_Producto");
				String nomProducto = rs.getString("nombreProducto");
				int udsCompradas = rs.getInt("sum_cant");
				int udsVendidas = rs.getInt("sum_uds");
				int total = udsCompradas-udsVendidas;
				
				almacen = new Almacen(codProducto,nomProducto, udsCompradas, udsVendidas, total);

				lista_Almacen.add(almacen);
							
			}
			
		} catch (SQLException e) {
			e.getMessage();
		}		
		
		return lista_Almacen;
	}

	public static String[][] obtenerMatrizDeliveryNote(String numAlbaran) {
		
		DecimalFormat formatea = new DecimalFormat("###,###.##");// Declaramos el formato de los numeros
		
		Connection conexion = AccesoDB.conexion();

		ArrayList<Compras> listaCompras = AccesoDB.datosComprasDeliveryNote(numAlbaran, conexion);

		String matrizInfoCompras[][] = new String[listaCompras.size()][5];

		for (int i = 0; i < listaCompras.size(); i++) {
			matrizInfoCompras[i][0] = listaCompras.get(i).getCodProducto()+"";
			matrizInfoCompras[i][1] = listaCompras.get(i).getNomProducto()+"";			
			matrizInfoCompras[i][2] = formatea.format(listaCompras.get(i).getImporteCompraProducto())+" €";			
			matrizInfoCompras[i][3] = formatea.format(listaCompras.get(i).getCantidad())+"";
			matrizInfoCompras[i][4] = formatea.format(listaCompras.get(i).getImporteTotal())+" €";
		}
		return matrizInfoCompras;		
		
	}

	static ArrayList<Compras> datosComprasDeliveryNote(String numAlbaran, Connection conexion) {
		
		ArrayList<Compras> lista_compras = new ArrayList<Compras>();
		
		Compras compras;
			
		try {			

			Statement sentencia = conexion.createStatement(); // Creamos sentencia con Statement
			// Consulta SQL con resulset
			ResultSet rs = sentencia.executeQuery("SELECT la.codproducto, la.codlinea, pro.nombreProducto,pro.importeCompra, la.cantidad, "
					+ "la.numAlbaran, a.fecha, a.codProveedor, p.nombreProveedor \r\n" + 
					"FROM LINEA_ALBARAN la \r\n" + 
					"JOIN PRODUCTO pro on la.codproducto = pro.cod_Producto \r\n" + 
					"JOIN ALBARAN a on la.numAlbaran = a.numAlbaran \r\n" + 
					"JOIN PROVEEDOR p on a.codProveedor = p.codproveedor \r\n" + 
					"WHERE la.numAlbaran = '"+numAlbaran+"';");			

			// Mientras haya registros anadimos al ArrayList
			while (rs.next()) { 
				
				String numAlbaran1 = rs.getString("numAlbaran");
				String codProducto = rs.getString("codproducto");
				String nomProducto = rs.getString("nombreProducto");
				int importeCompraPro = rs.getInt("importeCompra");
				int cantidad = rs.getInt("cantidad");
				int cantidadTotal = importeCompraPro*cantidad;
				String codProveedor = rs.getString("codProveedor");
				String nomProveedor = rs.getString("nombreProveedor");
				Date fecha = rs.getDate("fecha");
				int codLinea = rs.getInt("codlinea");
				
				compras = new Compras(numAlbaran1,codProducto,nomProducto,cantidad,importeCompraPro,cantidadTotal,codProveedor,
						nomProveedor, fecha,codLinea);
				
				lista_compras.add(compras);
							
			}
			
		} catch (SQLException e) {
			e.getMessage();
		}		
		
		return lista_compras;
	}

	public static String[][] obtenerMatrizDeliveryNoteDelete(String numAlbaranDelete) {
		
		DecimalFormat formatea = new DecimalFormat("###,###.##");// Declaramos el formato de los numeros
		
		Connection conexion = conexion();

		ArrayList<Compras> listaCompras = AccesoDB.datosComprasDeliveryNote(numAlbaranDelete, conexion);

		//String titulosDeliNoteCom[] = {"Product's Code", "Product's Name", "Quantity", "Purchase amount", "Total Account", 
				//"Supplier's code", "Supplier", "Date"};
		
		String matrizInfoCompras[][] = new String[listaCompras.size()][9];

		for (int i = 0; i < listaCompras.size(); i++) {
			matrizInfoCompras[i][0] = listaCompras.get(i).getCodProducto()+"";
			matrizInfoCompras[i][1] = listaCompras.get(i).getNomProducto()+"";			
			matrizInfoCompras[i][2] = formatea.format(listaCompras.get(i).getImporteCompraProducto())+" €";			
			matrizInfoCompras[i][3] = formatea.format(listaCompras.get(i).getCantidad())+"";
			matrizInfoCompras[i][4] = formatea.format(listaCompras.get(i).getImporteTotal())+" €";
			matrizInfoCompras[i][5] = listaCompras.get(i).getCodProveedor()+"";
			matrizInfoCompras[i][6] = listaCompras.get(i).getNomProveedor()+"";
			matrizInfoCompras[i][7] = listaCompras.get(i).getFechaAlbaran()+"";
			matrizInfoCompras[i][8] = listaCompras.get(i).getCodLineaF()+"";
		}
		return matrizInfoCompras;
		
	}

	public static int deleteAlbaranCompras(String numAlbaranDelete, Connection conexion) {
		
		int afectados = 0;
		
		// Almacenamos en un String la Sentencia SQL
		String sql = "DELETE FROM ALBARAN WHERE numAlbaran = '"+ numAlbaranDelete +"'";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			afectados = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return afectados;		
		
	}

	public static int deleteLineaAlbaranCompras(String numAlbaranDelete, Connection conexion) {
		
		int afectados = 0;
		
		// Almacenamos en un String la Sentencia SQL
		String sql = "DELETE FROM LINEA_ALBARAN WHERE numAlbaran = '"+ numAlbaranDelete +"'";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			afectados = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return afectados;		
	}
	
	
	public static int deleteLineaAlbaranCompras(int codLinea, Connection conexion) {
		
		int afectados = 0;
		
		// Almacenamos en un String la Sentencia SQL
		String sql = "DELETE FROM LINEA_ALBARAN WHERE codlinea =" +codLinea;
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			afectados = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return afectados;		
	}

	public static String[][] obtenerMatrizCRM() {
		
		Connection conexion = AccesoDB.conexion();

		ArrayList<CRM> listaCRM = AccesoDB.datosCRM(conexion);

		String matrizInfoCRM[][] = new String[listaCRM.size()][9];

		for (int i = 0; i < listaCRM.size(); i++) {
			matrizInfoCRM[i][0] = listaCRM.get(i).getId()+"";
			matrizInfoCRM[i][1] = listaCRM.get(i).getNombre()+"";
			matrizInfoCRM[i][2] = listaCRM.get(i).getEmail()+"";
			matrizInfoCRM[i][3] = listaCRM.get(i).getTelefono()+"";
			matrizInfoCRM[i][4] = listaCRM.get(i).getDecoWifi_pack()+"";
			matrizInfoCRM[i][5] = listaCRM.get(i).getWifi_Signal_Expansion()+"";
			matrizInfoCRM[i][6] = listaCRM.get(i).getSmart_Plugs_Pack()+"";
			matrizInfoCRM[i][7] = listaCRM.get(i).getSmart_Bulbs_Pack()+"";
			matrizInfoCRM[i][8] = listaCRM.get(i).getWifi_Surveillance_Cameras()+"";
		}
		return matrizInfoCRM;		
	}

	static ArrayList<CRM> datosCRM(Connection conexion) {
		
		ArrayList<CRM> lista_CRM = new ArrayList<CRM>();
		
		CRM c;
		
		try {			

			Statement sentencia = conexion.createStatement(); // Creamos sentencia con Statement
			// Consulta SQL con resulset
			ResultSet rs = sentencia.executeQuery("SELECT * FROM CRM");			

			// Mientras haya registros anadimos al ArrayList
			while (rs.next()) { 
				
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				int telefono = rs.getInt("telefono");
				int DecoWifi_pack = rs.getInt("DecoWifi_pack");
				int Wifi_Signal_Expansion  = rs.getInt("Wifi_Signal_Expansion");
				int Smart_Plugs_Pack = rs.getInt("Smart_Plugs_Pack");
				int Smart_Bulbs_Pack  = rs.getInt("Smart_Bulbs_Pack");
				int Wifi_Surveillance_Cameras = rs.getInt("Wifi_Surveillance_Cameras");
				String observaciones = rs.getString("obs");
				String fecha = rs.getString("fecha");
				
				c = new CRM(id, nombre,email,telefono,DecoWifi_pack, Wifi_Signal_Expansion, Smart_Plugs_Pack, Smart_Bulbs_Pack,  Wifi_Surveillance_Cameras,
						observaciones, fecha);
				
				lista_CRM.add(c);							
			}
			
		} catch (SQLException e) {
			e.getMessage();
		}		
		
		return lista_CRM;
	}

	public static ArrayList<Ventas> datosVentasBill(String numFactura, Connection conexion) {
		
		ArrayList<Ventas> lista_ventas = new ArrayList<Ventas>();
		
		Ventas ventas;
			
		try {			

			Statement sentencia = conexion.createStatement(); // Creamos sentencia con Statement
			// Consulta SQL con resulset
			ResultSet rs = sentencia.executeQuery("SELECT lf.codServicio, lf.codLinea, s.nombreServicio, s.importeServicio, lf.udsServicio, "
					+ "lf.numFactura,f.fecha,f.dni_Cliente,c.nombre \r\n" + 
					"FROM LINEA_FACTURA lf \r\n" + 
					"JOIN SERVICIO s on lf.codServicio = s.codServicio \r\n" + 
					"JOIN FACTURA f on lf.numFactura = f.numFactura \r\n" + 
					"JOIN CLIENTE c on f.dni_Cliente = c.dni_Cliente \r\n" + 
					"WHERE lf.numFactura = '"+numFactura+"';");			

			// Mientras haya registros anadimos al ArrayList
			while (rs.next()) { 
				
				String numFactura2 = rs.getString("numFactura");
				String codServicio = rs.getString("codServicio");
				String nomServicio = rs.getString("nombreServicio");
				int udsServicio = rs.getInt("udsServicio");
				int importeVentaSer = rs.getInt("importeServicio");
				int Total = importeVentaSer*udsServicio;
				String dniCliente = rs.getString("dni_Cliente");
				String nomCliente = rs.getString("nombre");
				Date fecha = rs.getDate("fecha");
				int codLinea = rs.getInt("codLinea");
				
				ventas = new Ventas(numFactura2,codServicio,nomServicio, udsServicio, importeVentaSer, Total, 
						dniCliente,  nomCliente, fecha, codLinea);
				
				lista_ventas.add(ventas);							
			}
			
		} catch (SQLException e) {
			e.getMessage();
		}		
		
		return lista_ventas;
	}

	public static String[][] obtenerMatrizBill(String numFactura) {
		
		DecimalFormat formatea = new DecimalFormat("###,###.##");// Declaramos el formato de los numeros
		
		Connection conexion = conexion();

		ArrayList<Ventas> listaVentas = datosVentasBill(numFactura, conexion);

		String matrizInfoVentas[][] = new String[listaVentas.size()][5];

		for (int i = 0; i < listaVentas.size(); i++) {
			matrizInfoVentas[i][0] = listaVentas.get(i).getCodServicio()+"";
			matrizInfoVentas[i][1] = listaVentas.get(i).getNombreServicio()+"";			
			matrizInfoVentas[i][2] = formatea.format(listaVentas.get(i).getImporteServicio())+" €";			
			matrizInfoVentas[i][3] = formatea.format(listaVentas.get(i).getUdsServicio())+"";
			matrizInfoVentas[i][4] = formatea.format(listaVentas.get(i).getTotal())+" €";
		}
		return matrizInfoVentas;	
	}

	public static String[][] obtenerMatrizBillDelete(String numFacturaDelete) {
		
		DecimalFormat formatea = new DecimalFormat("###,###.##");// Declaramos el formato de los numeros

		Connection conexion = AccesoDB.conexion();

		ArrayList<Ventas> listaVentas = AccesoDB.datosVentasBill(numFacturaDelete, conexion);

		String matrizInfoVentas[][] = new String[listaVentas.size()][9];

		for (int i = 0; i < listaVentas.size(); i++) {
			matrizInfoVentas[i][0] = listaVentas.get(i).getCodServicio()+"";
			matrizInfoVentas[i][1] = listaVentas.get(i).getNombreServicio()+"";			
			matrizInfoVentas[i][2] = formatea.format(listaVentas.get(i).getImporteServicio())+" €";			
			matrizInfoVentas[i][3] = formatea.format(listaVentas.get(i).getUdsServicio())+"";
			matrizInfoVentas[i][4] = formatea.format(listaVentas.get(i).getTotal())+" €";
			matrizInfoVentas[i][5] = listaVentas.get(i).getDni_Cliente()+"";
			matrizInfoVentas[i][6] = listaVentas.get(i).getNombre()+"";
			matrizInfoVentas[i][7] = listaVentas.get(i).getFecha()+"";
			matrizInfoVentas[i][8] = listaVentas.get(i).getCodLinea()+"";
		}
		return matrizInfoVentas;
	}	

	public static int deleteFacturaVentas(String numFacturaDelete, Connection conexion) {
		
		int afectados = 0;

		// Almacenamos en un String la Sentencia SQL
		String sql = "DELETE FROM FACTURA WHERE numFactura = '"+ numFacturaDelete +"'";

		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			afectados = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Afectados Factura:" + afectados);
		return afectados;	
	}

	public static int deleteLineaFacturaVentas(int codLinea, Connection conexion) {

		int afectados = 0;

		// Almacenamos en un String la Sentencia SQL
		String sql = "DELETE FROM LINEA_FACTURA WHERE codLinea =" +codLinea;

		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			afectados = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Afectados lineaFactura:" + afectados);

		return afectados;
	}
	 

	public static ArrayList<Nomina> datosNomina(String nifEmpleado2, String anio2, String mes2, Connection conexion) {
		
		ArrayList<Nomina> lista_NOM = new ArrayList<Nomina>();
		
		Nomina n;
			
		try {			

			Statement sentencia = conexion.createStatement(); // Creamos sentencia con Statement
			// Consulta SQL con resulset
			ResultSet rs = sentencia.executeQuery("SELECT n.codNomina, n.nif_Empleado, e.SS, e.perfil, e.gruCotizacion, "
					+ "n.mes, n.anio, n.salarioBase, n.horasExtra, n.dietas "
					+ "FROM NOMINA n "
					+ "JOIN EMPLEADO e ON n.nif_Empleado=e.NIF_Empleado "
					+ "WHERE n.nif_Empleado='"+nifEmpleado2+"' AND n.mes='"+mes2+"' AND n.anio="+anio2);			

			// Mientras haya registros anadimos al ArrayList
			while (rs.next()) { 
				
				String codNomina = rs.getString("codNomina");
				String nifEmpleado = rs.getString("nif_Empleado");
				String ss = rs.getString("SS");
				String categoria = rs.getString("perfil");
				String grupoCotizacion = rs.getString("gruCotizacion");
				String mes = rs.getString("mes");
				String anio = rs.getString("anio");
				int salarioBase = rs.getInt("salarioBase");
				int horasExtra = rs.getInt("horasExtra");
				int dietas = rs.getInt("dietas");
				
				n = new Nomina(codNomina, nifEmpleado, ss, categoria, grupoCotizacion, mes, anio, salarioBase, horasExtra, dietas);
				
				lista_NOM.add(n);
			}
			
		} catch (SQLException e) {
			e.getMessage();
		}
		return lista_NOM;		
	}

	public static int updateCRM(CRM c, Connection conexion) {
		
		int afect = 0;
		
		// Almacenamos en un String la Sentencia SQL
		String sql = "UPDATE CRM SET DecoWifi_pack="+c.getDecoWifi_pack()+", Wifi_Signal_Expansion="+c.Wifi_Signal_Expansion+
				", Smart_Plugs_Pack="+c.getSmart_Plugs_Pack()+", Smart_Bulbs_Pack="+c.getSmart_Bulbs_Pack()+
				", Wifi_Surveillance_Cameras="+c.getWifi_Surveillance_Cameras()+", obs='"+c.getObservaciones()+
				"', fecha='"+c.getFecha()+"' WHERE id="+c.getId()+";";
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			afect = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return afect;		
	}

	public static int deleteLineaFacturaVentas(String numFacturaDelete, Connection conexion) {
		
		int afectados = 0;

		// Almacenamos en un String la Sentencia SQL
		String sql = "DELETE FROM LINEA_FACTURA WHERE numFactura ='" +numFacturaDelete+"';";

		try {
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			afectados = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return afectados;
	}
}
