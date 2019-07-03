package controlador;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import tablas.Cliente;
import tablas.Compras;
import tablas.Empleado;
import tablas.Producto;
import tablas.Proveedor;
import tablas.Servicio;
import tablas.Ventas;
import vista.Ventana;

public class Eventos implements ActionListener, MouseListener {
	
	//Variables que van a ser usuadas varias veces en el programa y que almacenan informaci�n
	Boolean ok_check = false;
	private Ventana ventana;
	Connection conexion;
	ArrayList<Compras> nuevaCompra = new ArrayList<Compras>();
	ArrayList<Ventas> nuevaVenta = new ArrayList<Ventas>();
	ArrayList<Empleado> lista_usuarios = new ArrayList<Empleado>();
	String usuario, contrasena, nifEmpleado;
	JScrollPane barraDeliNote  = new JScrollPane();
	JScrollPane barraDeliNoteDelete  = new JScrollPane();
	JTable tablaDeliNoteDeleteCom;
	JScrollPane barraBill  = new JScrollPane();
	JScrollPane barraBillDelete  = new JScrollPane();
	JTable tablaBillDeleteVen;

	public Eventos(Ventana ventana) {
		this.ventana = ventana;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Abrimos la conexion con la BBDD
		conexion = AccesoDB.conexion();				
		if (conexion == null)
		return;	
		
		//Evento LOGIN
		if (e.getSource() == ventana.getBotonLogin()) {			
			
			//Accedemos a la BBDD para recoger los datos del usuario y la contasena
			lista_usuarios = AccesoDB.datosEmpleado(conexion);
			
			//Recogemos los valores que introducimos en las cajas de texto
			usuario = ventana.getCajaUser().getText();
			
			// Para recoger el password se regoje en una secuencia de chars
			char contrasenaArray[] = ventana.getCajaPass().getPassword();			
			//Ahora lo pasamos a un String
			contrasena = new String (contrasenaArray);
			
			if(usuario.equals("") && contrasena.equals("")) {
				ventana.getEtiquetaResulLogin().setText("Please, enter user and password");
			} else if(usuario.equals("")) {
				ventana.getEtiquetaResulLogin().setText("Please, enter user");
			} else if (contrasena.equals("")) {
				ventana.getEtiquetaResulLogin().setText("Please, enter password");
			} else {
				for (Empleado lista : lista_usuarios) {
					
					if(lista.getUsuario().equalsIgnoreCase(usuario) && lista.getContrasena().equalsIgnoreCase(contrasena) && lista.getPerfil().equalsIgnoreCase("general")) {
						//Ocultamos componentes de la pantalla de Login
						ventana.getImagenLogin().setVisible(false);
						ventana.getEtiquetaUser().setVisible(false);
						ventana.getEtiquetaPass().setVisible(false);
						ventana.getCajaUser().setVisible(false);
						ventana.getCajaPass().setVisible(false);
						ventana.getEtiquetaResulLogin().setVisible(false);
						ventana.getBotonLogin().setVisible(false);
						ventana.getBotonExit().setVisible(false);
						//Mostramos los componentes de la aplicacion y redimensionamos la pantalla
						ventana.setSize(1180,700);
						ventana.setResizable(false);
						ventana.setLayout(null);
						ventana.setLocationRelativeTo(null);
						ventana.getImagenInicio().setVisible(true);
						ventana.getPanelIzq().setVisible(true);
						ventana.getPanelDer().setVisible(true); 
						ventana.getBotonHR().setVisible(false);
						return;
					} 
					
					else if(lista.getUsuario().equalsIgnoreCase(usuario) && lista.getContrasena().equalsIgnoreCase(contrasena) && lista.getPerfil().equalsIgnoreCase("administrador")) {
						//Ocultamos componentes de la pantalla de Login
						ventana.getImagenLogin().setVisible(false);
						ventana.getEtiquetaUser().setVisible(false);
						ventana.getEtiquetaPass().setVisible(false);
						ventana.getCajaUser().setVisible(false);
						ventana.getCajaPass().setVisible(false);
						ventana.getEtiquetaResulLogin().setVisible(false);
						ventana.getBotonLogin().setVisible(false);
						ventana.getBotonExit().setVisible(false);
						//Mostramos los componentes de la aplicacion y redimensionamos la pantalla
						ventana.setSize(1180,700);
						ventana.setResizable(false);
						ventana.setLayout(null);
						ventana.setLocationRelativeTo(null);
						ventana.getImagenInicio().setVisible(true);
						ventana.getPanelIzq().setVisible(true);
						ventana.getPanelDer().setVisible(true); 
						ventana.getBotonHR().setVisible(true);
						return;					
					} else {
						ventana.getEtiquetaResulLogin().setText("Incorrect user and/or password");
					}
				}
			}			
			//Ponemos foco en el usuario
			ventana.getCajaUser().requestFocus();
			return;			
		}	
		
		if (e.getSource() == ventana.getBotonExit()) {
			
			// Pasamos como argumento la conexion a cerrar.
			AccesoDB.cerrarConexion(conexion); 
			//Cerramos la aplicacion
			System.exit(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource()==ventana.getBotonExitInit()) {
			// Pasamos como argumento la conexion a cerrar.
			AccesoDB.cerrarConexion(conexion); 
			//Cerramos la aplicacion
			System.exit(0);
		}
		
		//BOTON LOGOUT
		else if(e.getSource()==ventana.getBotonLogout()) {
			
			ventana.getImagenInicio().setVisible(false);
			ventana.getPanelIzq().setVisible(false);
			ventana.getPanelDer().setVisible(false); 
			ventana.getBotonHR().setVisible(false);
			ventana.getPanelEmpleado().setVisible(false);
			ventana.getSubPanelEmpInsertar().setVisible(false);
			ventana.getSubPanelBotonesEmp().setVisible(false);
			ventana.getImagenInicio().setVisible(false);
			ventana.getPanelCompras().setVisible(false);
			ventana.getSubPanelDeliveryNoteCompras().setVisible(false);
			ventana.getSubPanelInsertCompras().setVisible(false);
			ventana.getSubPanelComprasDelete().setVisible(false);
			ventana.getSubPanelComprasExport().setVisible(false);
			ventana.getPanelVentas().setVisible(false);
			ventana.getSubPanelInsertVentas().setVisible(false);
			ventana.getSubPanelVentasExport().setVisible(false);
			ventana.getSubPanelBills().setVisible(false);
			ventana.getSubPanelVentasDelete().setVisible(false);
			ventana.getSubPanelBotonesVentas().setVisible(false);
			ventana.getPanelProveedores().setVisible(false);
			ventana.getPanelClientes().setVisible(false);
			ventana.getSubPanelInsCliente().setVisible(false);
			ventana.getPanelBotonesCliente().setVisible(false);
			ventana.getSubPanelEditCliente().setVisible(false);
			ventana.getSubPanelElimCliente().setVisible(false);
			ventana.getPanelCRM().setVisible(false);
			ventana.getPanelAlmacen().setVisible(false);
			ventana.getSubPanelAlmacenExport().setVisible(false);
			ventana.getSubPanelInsProv().setVisible(false);
			ventana.getSubPanelEditProv().setVisible(false);
			ventana.getSubPanelElimProv().setVisible(false);
			ventana.getPanelBotonesProv().setVisible(false);
			ventana.getSubPanelEmpDelete().setVisible(false);
			ventana.getSubPanelEmpExport().setVisible(false);
			ventana.getSubPanelEmpUpdate().setVisible(false);
			ventana.getPanelNomina().setVisible(false);
			
			ventana.setSize(400,520);
			ventana.setResizable(false);
			ventana.setLayout(null);
			ventana.setLocationRelativeTo(null);
			ventana.getImagenLogin().setVisible(true);
			ventana.getEtiquetaUser().setVisible(true);
			ventana.getEtiquetaPass().setVisible(true);
			ventana.getCajaUser().setVisible(true);
			ventana.getCajaPass().setVisible(true);
			ventana.getEtiquetaResulLogin().setVisible(true);
			ventana.getBotonLogin().setVisible(true);
			ventana.getBotonExit().setVisible(true);
			
			//Limpiamos todas la etiquetas rellenas
			ventana.getCajaUser().setText("");
			ventana.getCajaPass().setText("");
			ventana.getCajaUser().requestFocus();
			ventana.getEtiquetaResulLogin().setText("");

		}
		
		else if(e.getSource() == ventana.getBotonUser()) {
			
			//Mostramos el panel de N�nima
			ventana.getPanelNomina().setVisible(true);
			
			//Mostraremos en el panel el Usuario que ha accedido al sistema
			for (Empleado lista : lista_usuarios) {
				
				if(lista.getUsuario().equalsIgnoreCase(usuario) && lista.getContrasena().equalsIgnoreCase(contrasena)) {
					ventana.getJLUsuarioNomina().setText(lista.getNombre()+" "+ lista.getApellidos());
					//Recogemos el NIF del empleado para luego mostrar las n�minas
					nifEmpleado = lista.getNif();
				} 
			}
			
			//Ocultamos el resto de paneles
			ventana.getImagenInicio().setVisible(false);
			ventana.getPanelCompras().setVisible(false);
			ventana.getSubPanelDeliveryNoteCompras().setVisible(false);
			ventana.getSubPanelComprasDelete().setVisible(false);
			ventana.getSubPanelInsertCompras().setVisible(false);
			ventana.getSubPanelBotonesCompras().setVisible(false);
			ventana.getSubPanelComprasExport().setVisible(false);
			ventana.getPanelVentas().setVisible(false);
			ventana.getSubPanelInsertVentas().setVisible(false);
			ventana.getSubPanelBotonesVentas().setVisible(false);
			ventana.getSubPanelBills().setVisible(false);
			ventana.getSubPanelVentasDelete().setVisible(false);
			ventana.getSubPanelVentasExport().setVisible(false);
			ventana.getPanelProveedores().setVisible(false);
			ventana.getPanelClientes().setVisible(false);
			ventana.getSubPanelInsCliente().setVisible(false);
			ventana.getPanelBotonesCliente().setVisible(false);
			ventana.getSubPanelEditCliente().setVisible(false);
			ventana.getSubPanelElimCliente().setVisible(false);
			ventana.getPanelCRM().setVisible(false);
			ventana.getPanelAlmacen().setVisible(false);
			ventana.getSubPanelAlmacenExport().setVisible(false);
			ventana.getSubPanelInsProv().setVisible(false);
			ventana.getSubPanelEditProv().setVisible(false);
			ventana.getSubPanelElimProv().setVisible(false);
			ventana.getPanelBotonesProv().setVisible(false);
			ventana.getSubPanelEmpDelete().setVisible(false);
			ventana.getSubPanelEmpExport().setVisible(false);
			ventana.getBotonDeleteEmpFinal().setVisible(false);
			ventana.getSubPanelEmpUpdate().setVisible(false);
			
		}
		
		else if(e.getSource() == ventana.getBotonCheckNom()) {
			
			//Recogemos en variables el mes y anio de la nomina
			String anio = ventana.getComboAnnoNomina().getSelectedItem().toString();
			String mes = ventana.getComboMesNomina().getSelectedItem().toString();
				
			
		}
		
		else if (e.getSource()==ventana.getBotonHR()) {
			
			//Limpiamos todas la etiquetas rellenas
			ventana.getInsertNomEmpl().setText("");
			ventana.getInsertApelEmpl().setText("");
			ventana.getInsertNIFEmp().setText("");
			ventana.getInsertPhoneEmp().setText("");
			ventana.getInsertEmailEmp().setText("");
			ventana.getInsertUserEmp().setText("");
			ventana.getInsertPassEmp().setText("");
			ventana.getInsertPerfilEmp().setText("");
			ventana.getResulInsertEmp().setText("");
			
			
			//Mostramos los paneles de recursos
			ventana.getPanelEmpleado().setVisible(true);
			ventana.getSubPanelEmpInsertar().setVisible(true);
			ventana.getSubPanelBotonesEmp().setVisible(true);
			
			//Ocultamos el resto
			ventana.getImagenInicio().setVisible(false);
			ventana.getPanelCompras().setVisible(false);
			ventana.getSubPanelDeliveryNoteCompras().setVisible(false);
			ventana.getSubPanelComprasDelete().setVisible(false);
			ventana.getSubPanelInsertCompras().setVisible(false);
			ventana.getSubPanelBotonesCompras().setVisible(false);
			ventana.getSubPanelComprasExport().setVisible(false);
			ventana.getPanelVentas().setVisible(false);
			ventana.getSubPanelInsertVentas().setVisible(false);
			ventana.getSubPanelBotonesVentas().setVisible(false);
			ventana.getSubPanelBills().setVisible(false);
			ventana.getSubPanelVentasDelete().setVisible(false);
			ventana.getSubPanelVentasExport().setVisible(false);
			ventana.getPanelProveedores().setVisible(false);
			ventana.getPanelClientes().setVisible(false);
			ventana.getSubPanelInsCliente().setVisible(false);
			ventana.getPanelBotonesCliente().setVisible(false);
			ventana.getSubPanelEditCliente().setVisible(false);
			ventana.getSubPanelElimCliente().setVisible(false);
			ventana.getPanelCRM().setVisible(false);
			ventana.getPanelAlmacen().setVisible(false);
			ventana.getSubPanelAlmacenExport().setVisible(false);
			ventana.getSubPanelInsProv().setVisible(false);
			ventana.getSubPanelEditProv().setVisible(false);
			ventana.getSubPanelElimProv().setVisible(false);
			ventana.getPanelBotonesProv().setVisible(false);
			ventana.getSubPanelEmpDelete().setVisible(false);
			ventana.getSubPanelEmpExport().setVisible(false);
			ventana.getBotonDeleteEmpFinal().setVisible(false);
			ventana.getSubPanelEmpUpdate().setVisible(false);
			ventana.getPanelNomina().setVisible(false);
			
		}
		
		else if (e.getSource() == ventana.getBotonInsertEmpFinal()) {
			
			if(ventana.getInsertNomEmpl().getText().isEmpty() || ventana.getInsertApelEmpl().getText().isEmpty() ||
					ventana.getInsertNIFEmp().getText().isEmpty() || ventana.getInsertPhoneEmp().getText().isEmpty() ||
					ventana.getInsertEmailEmp().getText().isEmpty() || ventana.getInsertUserEmp().getText().isEmpty() ||
					ventana.getInsertPassEmp().getText().isEmpty() || ventana.getInsertPerfilEmp().getText().isEmpty())
			{
				ventana.getResulInsertEmp().setText("Please, complete all the fields");
				
			} else {
				//Limpiamos la etiqueta de resultado final
				ventana.getResulInsertEmp().setText("");
				
				ArrayList<Empleado> nuevoEmpleado = new ArrayList<Empleado>();
				
				//Recogemos los datos del nuevo empleado
				String nombre = ventana.getInsertNomEmpl().getText();
				String apellidos = ventana.getInsertApelEmpl().getText();
				String nif = ventana.getInsertNIFEmp().getText();
				int telefono = Integer.parseInt(ventana.getInsertPhoneEmp().getText());
				String email = ventana.getInsertPhoneEmp().getText();
				String user = ventana.getInsertUserEmp().getText();
				String pass = ventana.getInsertPassEmp().getText();
				String perfil = ventana.getInsertPassEmp().getText();
				
				Empleado emp = new Empleado(nombre, apellidos, email, nif, user, pass, perfil, telefono);
				
				nuevoEmpleado.add(emp);
				
				int afectados = AccesoDB.insertarEmpleado(nuevoEmpleado, conexion);
				
				if(afectados == 0) {
					ventana.getResulInsertEmp().setText("Error adding new employee");
				} else {
					ventana.getResulInsertEmp().setText("Employee added");
					
					refreshJTableEmpleados();
				
				}
			}			
		}
		
		else if(e.getSource() == ventana.getBotonActualizarEmpleado()) {
			
			//Limpiamos todas la etiquetas rellenas
			ventana.getInsertNIFUpdateEmp().setText("");
			ventana.getInsertNewDataEmp().setText("");
			ventana.getResultUpdateEmp().setText("");
			
			//Ocultamos los paneles de Insert, delete, export
			ventana.getSubPanelEmpInsertar().setVisible(false);
			ventana.getSubPanelEmpDelete().setVisible(false);
			ventana.getSubPanelEmpExport().setVisible(false);
			ventana.getBotonDeleteEmpFinal().setVisible(false);
			
			//Mostramos el panel de Update
			ventana.getSubPanelEmpUpdate().setVisible(true);
		}
		
		else if(e.getSource() == ventana.getBotonUpdateEmpFinal()) {
			
			if(ventana.getInsertNIFUpdateEmp().getText().isEmpty() || ventana.getInsertNewDataEmp().getText().isEmpty()) {
				ventana.getResultUpdateEmp().setText("Please, complete all the fields");
			} else {
				//Limpiamos la etiqueta de resultado final
				ventana.getResultUpdateEmp().setText("");
				
				//Recogemos los datos que queremos actualizar
				String nif = ventana.getInsertNIFUpdateEmp().getText();
				String campo = ventana.getComboUpdateEmp().getSelectedItem().toString();
				String nuevoDato = ventana.getInsertNewDataEmp().getText();
				
				int afectados = AccesoDB.actualizarEmpleado(nif,campo,nuevoDato, conexion);
				
				if(afectados == 0) {
					ventana.getResultUpdateEmp().setText("Error updating employee");
				} else {
					ventana.getResultUpdateEmp().setText("Employee updated");
					refreshJTableEmpleados();
				}
			}
		}
		
		else if(e.getSource() == ventana.getBotonBorrarEmpleado()) {
			
			//Limpiamos etiquetas rellenas
			ventana.getInsertNIFDeleteEmp().setText("");
			ventana.getResulBusquedaEmp().setText("");
			ventana.getResulDeleteEmp().setText("");
			
			//Ocultamos los paneles de insert y update empleado, boton delete y export
			ventana.getSubPanelEmpInsertar().setVisible(false);
			ventana.getSubPanelEmpUpdate().setVisible(false);
			ventana.getBotonDeleteEmpFinal().setVisible(false);
			ventana.getSubPanelEmpExport().setVisible(false);
			
			//Mostramos el panel de Borrar
			ventana.getSubPanelEmpDelete().setVisible(true);
		}
		
		else if(e.getSource() == ventana.getBotonSearchEmp()) {
			
			//Recogemos el nif para buscar el empleado
			String nif = ventana.getInsertNIFDeleteEmp().getText();
			
			ArrayList<Empleado> listaE = AccesoDB.datosEmpleado(conexion);
			
			for (Empleado empleado : listaE) {
				
				if(empleado.getNif().equalsIgnoreCase(nif)) {
					ventana.getResulBusquedaEmp().setText("The employee: | "+ empleado.getNombre()
					+" "+empleado.getApellidos()+" | , will be deleted.");
					ventana.getBotonDeleteEmpFinal().setVisible(true);
					return;
				} else {
					ventana.getResulBusquedaEmp().setText("Employee doesn't exists");
				}
			}
		}
		
		else if(e.getSource() == ventana.getBotonDeleteEmpFinal()) {
			
			//Recogemos el nif para buscar el empleado
			String nif = ventana.getInsertNIFDeleteEmp().getText();
			
			int afectados = AccesoDB.borrarEmpleado(nif, conexion);
			
			if(afectados == 0) {
				ventana.getResulDeleteEmp().setText("Error deleting employee");
			} else {
				ventana.getResulDeleteEmp().setText("Employee deleted");
				refreshJTableEmpleados();
			}
		}
		
		else if(e.getSource() == ventana.getBotonExpEmplFichero()) {
			
			//Limpiamos la etiqueta usuario
			ventana.getInsertUsuarioPCCompras().setText("");
			ventana.getResulExportEmp().setText("");
			
			//Ocultamos los paneles de insert, update empleado y boton delete
			ventana.getSubPanelEmpInsertar().setVisible(false);
			ventana.getSubPanelEmpUpdate().setVisible(false);
			ventana.getBotonDeleteEmpFinal().setVisible(false);
			ventana.getSubPanelEmpDelete().setVisible(false);
			
			//Mostramos el panel de Export
			ventana.getSubPanelEmpExport().setVisible(true);
			
		}
		
		else if(e.getSource() == ventana.getBotonExportEmpFinal()) {
			
			//Recojemos el usuario del PC
			String user = ventana.getInsertUsuarioPCEmpleado().getText();
						
			if(user.isEmpty()) {
				
				//Mostramos Dialog 
  				JOptionPane.showMessageDialog(new JFrame(), 
  						"Please, enter you user",
  						"Export Employees",
  						JOptionPane.ERROR_MESSAGE);
			} else {
				
				Boolean ok_fichero = AccesoDB.exportarFicheroEmpleados(user);
				
				if (ok_fichero == true) {
					
					ventana.getResulExportEmp().setText("File Created");
					
				} else {
					
					ventana.getResulExportEmp().setText("Error creating file");
				}
			}				
		}
		
		else if(e.getSource()==ventana.getBotonPurchases()) {
			
			//Limpiamos las etiquetas rellenas
			ventana.getJTFnumAlbaran().setText("");
			ventana.getComboProductoCompras().setSelectedIndex(0);
			ventana.getJLresulComboProCompra().setText("");
			ventana.getJTFcantidadCompra().setText("");
			ventana.getJLresulimporCompraPro().setText("");
			ventana.getJLresulimporTotalPro().setText("");
			ventana.getComboProveedorCompras().setSelectedIndex(0);
			ventana.getJLresulComboProveedorCompra().setText("");
			ventana.getJLresulinsertComprafinal().setText("");
			
			//Mostramos paneles de compras
			ventana.getPanelCompras().setVisible(true);
			ventana.getSubPanelInsertCompras().setVisible(true);
			ventana.getSubPanelBotonesCompras().setVisible(true);
			ventana.getSubPanelComprasExport().setVisible(false);
			
			//Ocultamos el resto
			ventana.getPanelEmpleado().setVisible(false);
			ventana.getSubPanelEmpInsertar().setVisible(false);
			ventana.getSubPanelEmpUpdate().setVisible(false);
			ventana.getSubPanelEmpDelete().setVisible(false);
			ventana.getSubPanelBotonesEmp().setVisible(false);
			ventana.getImagenInicio().setVisible(false);			
			ventana.getPanelVentas().setVisible(false);
			ventana.getSubPanelInsertVentas().setVisible(false);
			ventana.getSubPanelBotonesVentas().setVisible(false);
			ventana.getSubPanelBills().setVisible(false);
			ventana.getSubPanelVentasExport().setVisible(false);
			ventana.getSubPanelVentasDelete().setVisible(false);
			ventana.getPanelProveedores().setVisible(false);
			ventana.getPanelClientes().setVisible(false);
			ventana.getSubPanelInsCliente().setVisible(false);
			ventana.getPanelBotonesCliente().setVisible(false);
			ventana.getSubPanelEditCliente().setVisible(false);
			ventana.getSubPanelElimCliente().setVisible(false);
			ventana.getPanelCRM().setVisible(false);
			ventana.getPanelAlmacen().setVisible(false);
			ventana.getSubPanelAlmacenExport().setVisible(false);
			ventana.getSubPanelInsProv().setVisible(false);
			ventana.getSubPanelEditProv().setVisible(false);
			ventana.getSubPanelElimProv().setVisible(false);
			ventana.getPanelBotonesProv().setVisible(false);
			ventana.getSubPanelEmpExport().setVisible(false);			
			ventana.getSubPanelDeliveryNoteCompras().setVisible(false);
			ventana.getSubPanelComprasDelete().setVisible(false);
			ventana.getPanelNomina().setVisible(false);
		}
		
		else if(e.getSource() == ventana.getBotonVerificarCompra()) {	
			
			//Borramos el contenido del Array por si se hubiera quedado algún registro
			for (int i = 0; i < nuevaCompra.size(); i++) {
				nuevaCompra.remove(i);
			}
			
			//Recogemos en un Array los productos y Proveedores
			ArrayList<Producto> lista_productos = AccesoDB.datosProducto(conexion);
			ArrayList<Proveedor> lista_proveedores = AccesoDB.datosProveedor(conexion);
			
			//Recogemos en variables los datos introducidos por el usuario
			String codProducto = ventana.getComboProductoCompras().getSelectedItem().toString();
			String codproveedor = ventana.getComboProveedorCompras().getSelectedItem().toString();
			String numAlbaran = ventana.getJTFnumAlbaran().getText();
			String nomProducto = null;
			String nomProveedor = null;
			int cantidad = 0;
			int importeCompra = 0;
			int importeTotal = 0;
			
			Calendar hoy = new GregorianCalendar(); //obtiene la fecha actual
			//ahora accedemos a los anios, meses, dias:
			int anio = hoy.get(Calendar.YEAR);
			int mes = hoy.get(Calendar.MONTH)+1;
			int dia = hoy. get(Calendar.DAY_OF_MONTH)+1;
			String f = anio +"-" + mes +"-"+dia;
			
			Date fecha = Date.valueOf(f);
			
			try {
				cantidad = Integer.parseInt(ventana.getJTFcantidadCompra().getText());
				if(cantidad <= 0) {
					//Mostramos Dialog 
					JOptionPane.showMessageDialog(new JFrame(), 
							"Please, insert a correct number for quantity",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException g) {
				g.getMessage();
				//Mostramos Dialog 
				JOptionPane.showMessageDialog(new JFrame(), 
						"Please, insert quantity",
						"Error",
						JOptionPane.ERROR_MESSAGE);
			}			
			
			//Comprobamos los datos de producto
			for (Producto producto : lista_productos) {
				if(codProducto.equalsIgnoreCase(producto.getCod_Producto())) {
					importeCompra = producto.getImporteCompra();
					importeTotal = cantidad * producto.getImporteCompra();
					nomProducto = producto.getNombreProducto().toString();
					ventana.getJLresulComboProCompra().setText(nomProducto);
					ventana.getJLresulimporCompraPro().setText(Integer.toString(producto.getImporteCompra()).toString()+" � unity");
					ventana.getJLresulimporTotalPro().setText(Integer.toString(importeTotal).toString()+" � total amount");
				} 
			}
			
			//Comprobamos los datos de proveedor
			for (Proveedor proveedor : lista_proveedores) {
				if(codproveedor.equalsIgnoreCase(proveedor.getCodproveedor())) {	
					nomProveedor = proveedor.getNombreProveedor().toString();
					ventana.getJLresulComboProveedorCompra().setText(nomProveedor);
				} 
			}
			
			//Si algun componente de la los valores del Insert está vacío
			if(ventana.getJTFnumAlbaran().getText().isEmpty() || ventana.getComboProductoCompras().getSelectedItem().toString().contains("Product's code") ||
				    ventana.getComboProveedorCompras().getSelectedItem().toString().contains("Supplier's code"))
				{
				//Mostramos Dialog 
				JOptionPane.showMessageDialog(new JFrame(), 
						"Please, complete all the fields",
						"Check",
						JOptionPane.ERROR_MESSAGE);				
				} else {
					//Mostramos Dialog 
					JOptionPane.showMessageDialog(new JFrame(), 
							"You can insert a new Purchase",
							"Check",
							JOptionPane.INFORMATION_MESSAGE);	
					
					ok_check = true;					
													
					Compras c = new Compras(numAlbaran, codProducto, nomProducto, cantidad, importeCompra, importeTotal, codproveedor, nomProveedor, fecha);				
					nuevaCompra.add(c);
				}	
		}
		
		else if(e.getSource() == ventana.getBotonInsertCompraFinal()) {
			
			ArrayList<Compras> lista_compras = AccesoDB.datosCompras(conexion);
			String numAlbaran = ventana.getJTFnumAlbaran().getText();
			int coincide = 0; //Para controlar si encuentra el número de albarán en la lista de compras
			
			for (Compras compras : lista_compras) {
				if(compras.getNumAlbaran().equals(numAlbaran)) {
					coincide = 1;
					//return;
				}
			}
			
			if(ok_check == true && coincide == 1) {				
				
						//Mostramos Dialog 
						JOptionPane.showMessageDialog(new JFrame(), 
								"Delivery Note exists. New line will be inserted",
								"Check",
								JOptionPane.INFORMATION_MESSAGE);	
						
						int afectados = AccesoDB.insertarLineaAlbaran(nuevaCompra, conexion);
						
						if (afectados == 0) {
							ventana.getJLresulinsertComprafinal().setText("Error adding Delivery Note line");
						} else {
							ventana.getJLresulinsertComprafinal().setText("Delivery Note line added");
							
							refreshJTableCompras();
							
							for (int i = 0; i < nuevaCompra.size(); i++) {
								nuevaCompra.remove(i);
							}				
						}		
			}
			
			else if(ok_check == true && coincide == 0) {
				
				int afectados1 = AccesoDB.insertarCompra(nuevaCompra, conexion);
				int afectados2 = AccesoDB.insertarLineaAlbaran(nuevaCompra, conexion);

				if (afectados1 == 0 && afectados2 == 0) {
					ventana.getJLresulinsertComprafinal().setText("Error adding Delivery Note");
				} else if (afectados1 == 1 && afectados2 == 0) {
					ventana.getJLresulinsertComprafinal().setText("Error adding Delivery Note line");
				} else if (afectados1 == 0 && afectados2 == 1) {
					ventana.getJLresulinsertComprafinal().setText("Error adding Delivery Note");
				} else if (afectados1 == 1 && afectados2 == 1) {
					ventana.getJLresulinsertComprafinal().setText("Delivery Note added");
				}
				refreshJTableCompras();

				for (int i = 0; i < nuevaCompra.size(); i++) {
					nuevaCompra.remove(i);
				}
			}
			
			else if(ok_check == false) {
				//Mostramos Dialog 
				JOptionPane.showMessageDialog(new JFrame(), 
						"You must check the purchase's items first.",
						"Check",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(e.getSource() == ventana.getBotonDeliveryNoteCompra()) {
			
			//Limpiamos etiquetas
            ventana.getJLDeliveryNoteCompras().setVisible(false);
            ventana.getJLCustomerDomCom().setVisible(false);
            ventana.getJLDomohogarCom().setVisible(false);
            ventana.getJLDateCom().setVisible(false);
            ventana.getJLSuplierCode().setVisible(false);
            ventana.getJLSuplierCom().setVisible(false); 
            ventana.getJLTotalAccountCom().setVisible(false);
            ventana.getJLnumAlbaranCom().setText("");
            ventana.getJLDateComRow().setText("");
            ventana.getJLSuplierComRow().setText("");
            ventana.getJLSuplierCodeRow().setText("");
            ventana.getJLTotalAccountComSuma().setText("");
            barraDeliNote.setVisible(false);
			
			//Mostramos el subpanel de compras
			ventana.getSubPanelDeliveryNoteCompras().setVisible(true);
			ventana.getPanelCompras().setVisible(true);
			ventana.getSubPanelBotonesCompras().setVisible(true);
			
			//Ocultamos el resto			
			ventana.getSubPanelInsertCompras().setVisible(false);
			ventana.getSubPanelComprasExport().setVisible(false);
			ventana.getPanelEmpleado().setVisible(false);
			ventana.getSubPanelEmpInsertar().setVisible(false);
			ventana.getSubPanelEmpUpdate().setVisible(false);
			ventana.getSubPanelEmpDelete().setVisible(false);
			ventana.getSubPanelBotonesEmp().setVisible(false);
			ventana.getImagenInicio().setVisible(false);			
			ventana.getPanelVentas().setVisible(false);
			ventana.getSubPanelInsertVentas().setVisible(false);
			ventana.getSubPanelBotonesVentas().setVisible(false);
			ventana.getSubPanelBills().setVisible(false);
			ventana.getSubPanelVentasExport().setVisible(false);
			ventana.getSubPanelVentasDelete().setVisible(false);
			ventana.getPanelProveedores().setVisible(false);
			ventana.getPanelClientes().setVisible(false);
			ventana.getSubPanelInsCliente().setVisible(false);
			ventana.getPanelBotonesCliente().setVisible(false);
			ventana.getSubPanelEditCliente().setVisible(false);
			ventana.getSubPanelElimCliente().setVisible(false);
			ventana.getPanelCRM().setVisible(false);
			ventana.getPanelAlmacen().setVisible(false);
			ventana.getSubPanelAlmacenExport().setVisible(false);
			ventana.getSubPanelInsProv().setVisible(false);
			ventana.getSubPanelEditProv().setVisible(false);
			ventana.getSubPanelElimProv().setVisible(false);
			ventana.getPanelBotonesProv().setVisible(false);
			ventana.getSubPanelEmpExport().setVisible(false);
			ventana.getSubPanelComprasDelete().setVisible(false);
			ventana.getPanelNomina().setVisible(false);
			
		}
		
		else if(e.getSource() == ventana.getBotonCheckDeliNoteCom()) {
			
			//Almacenamos en una variable el número de la fila seleccionada
			int row = ventana.getTablaCompras().getSelectedRow();
			
			//Si el número de fila es dstinto a -1 es que se ha seleccionado una fila
            if (row != -1) {
            	
            	//Recogemos los valores de la fila seleccionada indicando los datos de la columna
                String numAlbaran = (String) ventana.getTablaCompras().getValueAt(row, 0);
                ventana.getJLnumAlbaranCom().setText(numAlbaran);
                
                String fecha = (String) ventana.getTablaCompras().getValueAt(row, 8);
                ventana.getJLDateComRow().setText(fecha);
                
                String sup = (String) ventana.getTablaCompras().getValueAt(row, 7);
                ventana.getJLSuplierComRow().setText(sup);
                
                String sup_code = (String) ventana.getTablaCompras().getValueAt(row, 6);
                ventana.getJLSuplierCodeRow().setText(sup_code);
                
                ArrayList<Compras> lista = AccesoDB.datosComprasDeliveryNote(numAlbaran, conexion);
                
                int suma = 0;
                
                for (Compras c : lista) {
					
                	suma = c.getImporteTotal() + suma;
				}
                
                ventana.getJLTotalAccountComSuma().setText(Integer.toString(suma) + " €");
                
                //Mostramos datos de las Labels que están ocultos en la ventana
                ventana.getJLDeliveryNoteCompras().setVisible(true);
                ventana.getJLCustomerDomCom().setVisible(true);
                ventana.getJLDomohogarCom().setVisible(true);
                ventana.getJLDateCom().setVisible(true);
                ventana.getJLSuplierCode().setVisible(true);
                ventana.getJLSuplierCom().setVisible(true); 
                ventana.getJLTotalAccountCom().setVisible(true);
                
                //Creamos la Tabla con la información               
                barraDeliNote.setVisible(true);
                barraDeliNote.setBounds(20, 120, 710, 100);
                ventana.getSubPanelDeliveryNoteCompras().add(barraDeliNote);
        		
        		String titulosDeliNoteCom[] = {"Product's Code", "Product's Name", "Price", "Quantity", "Total"};
        		String infoCompras[][] = AccesoDB.obtenerMatrizDeliveryNote(numAlbaran);
        		
        		JTable tablaDeliNoteCom = new JTable(infoCompras,titulosDeliNoteCom);
        		barraDeliNote.setViewportView(tablaDeliNoteCom);

            } else {

            	//Mostramos Dialog 
				JOptionPane.showMessageDialog(new JFrame(), 
						"You must select a row",
						"Delivery Note Detail",
						JOptionPane.ERROR_MESSAGE);
            }
			
		}
		
		else if(e.getSource() == ventana.getBotonDeleteCompra()) {
			
			//Limpiamos etiquetas
            ventana.getJLDeliveryNoteDeleteCom().setVisible(false);
            barraDeliNoteDelete.setVisible(false);
            ventana.getJLnumAlbaranDeleteCom().setText("");
            ventana.getBotonDeleteLineaAlbaran().setVisible(false);
            ventana.getBotonDeleteAlabaran().setVisible(false);
            ventana.getJLselecJTableComDeleteLineAlbaran().setVisible(false);
            ventana.getJLselecJTableComDeleteAlbaran().setVisible(false);            
			
			//Mostramos el subpanel de compras
			ventana.getSubPanelComprasDelete().setVisible(true);
			ventana.getPanelCompras().setVisible(true);
			ventana.getSubPanelBotonesCompras().setVisible(true);
			
			//Ocultamos el resto			
			ventana.getSubPanelInsertCompras().setVisible(false);
			ventana.getSubPanelComprasExport().setVisible(false);
			ventana.getSubPanelDeliveryNoteCompras().setVisible(false);
			ventana.getPanelEmpleado().setVisible(false);
			ventana.getSubPanelEmpInsertar().setVisible(false);
			ventana.getSubPanelEmpUpdate().setVisible(false);
			ventana.getSubPanelEmpDelete().setVisible(false);
			ventana.getSubPanelBotonesEmp().setVisible(false);
			ventana.getImagenInicio().setVisible(false);			
			ventana.getPanelVentas().setVisible(false);
			ventana.getSubPanelInsertVentas().setVisible(false);
			ventana.getSubPanelBotonesVentas().setVisible(false);
			ventana.getSubPanelBills().setVisible(false);
			ventana.getSubPanelVentasExport().setVisible(false);
			ventana.getSubPanelVentasDelete().setVisible(false);
			ventana.getPanelProveedores().setVisible(false);
			ventana.getPanelClientes().setVisible(false);
			ventana.getSubPanelInsCliente().setVisible(false);
			ventana.getPanelBotonesCliente().setVisible(false);
			ventana.getSubPanelEditCliente().setVisible(false);
			ventana.getSubPanelElimCliente().setVisible(false);
			ventana.getPanelCRM().setVisible(false);
			ventana.getPanelAlmacen().setVisible(false);
			ventana.getSubPanelAlmacenExport().setVisible(false);
			ventana.getSubPanelInsProv().setVisible(false);
			ventana.getSubPanelEditProv().setVisible(false);
			ventana.getSubPanelElimProv().setVisible(false);
			ventana.getPanelBotonesProv().setVisible(false);
			ventana.getSubPanelEmpExport().setVisible(false);	
			ventana.getPanelNomina().setVisible(false);
			
		}
		
		else if(e.getSource() == ventana.getBotonCheckDeliNoteComDelete()) {
			
			//Almacenamos en una variable el número de la fila seleccionada
			int rowDelete = ventana.getTablaCompras().getSelectedRow();
			
			//Si el número de fila es dstinto a -1 es que se ha seleccionado una fila
            if (rowDelete != -1) {
            	
            	//Recogemos los valores de la fila seleccionada indicando los datos de la columna
                String numAlbaranDelete = (String) ventana.getTablaCompras().getValueAt(rowDelete, 0);
                ventana.getJLnumAlbaranDeleteCom().setText(numAlbaranDelete);          
                
                //Mostramos datos de las Labels que están ocultos en la ventana
                ventana.getJLDeliveryNoteDeleteCom().setVisible(true);
                ventana.getBotonDeleteLineaAlbaran().setVisible(true);
                ventana.getBotonDeleteAlabaran().setVisible(true);
                ventana.getJLselecJTableComDeleteLineAlbaran().setVisible(true);
                ventana.getJLselecJTableComDeleteAlbaran().setVisible(true);
                
                //Creamos la Tabla con la información               
                barraDeliNoteDelete.setVisible(true);
                barraDeliNoteDelete.setBounds(20, 50, 710, 100);
                ventana.getSubPanelComprasDelete().add(barraDeliNoteDelete);
        		
        		String titulosDeliNoteCom[] = {"Product's Code", "Product's Name", "Purchase amount", "Quantity","Total Account", 
        				"Supplier's code", "Supplier", "Date"};
        		String infoCompras[][] = AccesoDB.obtenerMatrizDeliveryNoteDelete(numAlbaranDelete);
        		
        		tablaDeliNoteDeleteCom = new JTable(infoCompras,titulosDeliNoteCom);
        		barraDeliNoteDelete.setViewportView(tablaDeliNoteDeleteCom);
        		
            } else {

            	//Mostramos Dialog 
				JOptionPane.showMessageDialog(new JFrame(), 
						"You must select a row",
						"Delete Delivery Note",
						JOptionPane.ERROR_MESSAGE);
            }
		}
		
		else if(e.getSource() == ventana.getBotonDeleteLineaAlbaran()) {
			
			//Almacenamos en una variable el número de la fila seleccionada
			int rowDeleteLine = tablaDeliNoteDeleteCom.getSelectedRow();
			
			//Recogemos los valores de la fila seleccionada indicando los datos de la columna
            String numAlbaranDelete = ventana.getJLnumAlbaranDeleteCom().getText();
			
			//Si el número de fila es dstinto a -1 es que se ha seleccionado una fila
            if (rowDeleteLine != -1) {
            	
            	//Recogemos los valores de la fila seleccionada indicando los datos de la columna
                String numProductoDelete = (String) tablaDeliNoteDeleteCom.getValueAt(rowDeleteLine, 0);
                //Recogemos los valores de la fila seleccionada indicando los datos de la columna
                int cantidadDeleteCom = Integer.parseInt((String) tablaDeliNoteDeleteCom.getValueAt(rowDeleteLine, 3));
                
                int afect = AccesoDB.deleteLineaAlbaranCompras(numAlbaranDelete, numProductoDelete, cantidadDeleteCom, conexion);
                
                if(afect == 1) {
                	
                	//Mostramos Dialog 
    				JOptionPane.showMessageDialog(new JFrame(), 
    						"Delivery Note line deleted",
    						"Delete Delivery Note",
    						JOptionPane.INFORMATION_MESSAGE);
    				
    				refreshJTableDeleteCompras(numAlbaranDelete);
    				refreshJTableCompras();
                	
                } else {
                	
                	//Mostramos Dialog 
    				JOptionPane.showMessageDialog(new JFrame(), 
    						"ERROR to delete the Delivery Note Line",
    						"Delete Delivery Note",
    						JOptionPane.ERROR_MESSAGE);
                }
        		
            } else {

            	//Mostramos Dialog 
				JOptionPane.showMessageDialog(new JFrame(), 
						"You must select a row",
						"Delete Delivery Note",
						JOptionPane.ERROR_MESSAGE);
            }			
		}
		
		else if(e.getSource() == ventana.getBotonDeleteAlabaran()) {
			
			//Recogemos los valores de la fila seleccionada indicando los datos de la columna
            String numAlbaranDelete = ventana.getJLnumAlbaranDeleteCom().getText();
			
            int resp = JOptionPane.showConfirmDialog(null, 
            		"Do you want delete the Delivery Note "+ numAlbaranDelete + "?\n"
            				+"This operation can't be undone.", 
            		"Delete Delivery Note", 
            		JOptionPane.YES_NO_OPTION, 
            		JOptionPane.QUESTION_MESSAGE);
            
           if (JOptionPane.OK_OPTION == resp) {
        	   
        	   int afect = AccesoDB.deleteAlbaranCompras(numAlbaranDelete, conexion);
               int afect1 = AccesoDB.deleteLineaAlbaranCompras(numAlbaranDelete, conexion);
        	   
               if(afect == 1 && afect1 >= 1) {
               	
               	//Mostramos Dialog 
   				JOptionPane.showMessageDialog(new JFrame(), 
   						"Delivery Note DELETED from DataBase",
   						"Delete Delivery Note",
   						JOptionPane.INFORMATION_MESSAGE);
   				
   				refreshJTableDeleteCompras(numAlbaranDelete);
   				refreshJTableCompras();
               	
               } else {
               	
               	//Mostramos Dialog 
   				JOptionPane.showMessageDialog(new JFrame(), 
   						"ERROR to delete the Delivery Note",
   						"Delete Delivery Note",
   						JOptionPane.ERROR_MESSAGE);
               }
        	   
           } else {
        	   
              	//Mostramos Dialog 
  				JOptionPane.showMessageDialog(new JFrame(), 
  						"Abort Deleting ...",
  						"Delete Delivery Note",
  						JOptionPane.ERROR_MESSAGE);
           }

		}
		
		else if(e.getSource() == ventana.getBotonExportCompra()) {
			
			//Limpiamos la etiqueta usuario
			ventana.getInsertUsuarioPCCompras().setText("");	
			ventana.getResulExportCom().setText("");
			
			//Mostramos el panel de Export
			ventana.getSubPanelComprasExport().setVisible(true);
			
			//Ocultamos el resto de paneles de Compras
			ventana.getSubPanelInsertCompras().setVisible(false);
			ventana.getSubPanelDeliveryNoteCompras().setVisible(false);
			ventana.getSubPanelComprasDelete().setVisible(false);
			
		}
		
		else if(e.getSource() == ventana.getBotonExportComFinal()) {
			
			//Recojemos el usuario del PC
			String user = ventana.getInsertUsuarioPCCompras().getText();
						
			if(user.isEmpty()) {
				
				//Mostramos Dialog 
  				JOptionPane.showMessageDialog(new JFrame(), 
  						"Please, enter you user",
  						"Export Purchases",
  						JOptionPane.ERROR_MESSAGE);
			} else {
				
				Boolean ok_fichero = AccesoDB.exportarFicheroCompras(user);
				
				if (ok_fichero == true) {
					
					ventana.getResulExportCom().setText("File Created");
					
				} else {
					
					ventana.getResulExportCom().setText("Error creating file");
				}
			}			
		}
		
		else if(e.getSource()==ventana.getBotonSales()) {
			
			//Mostramos el panel de ventas
			ventana.getPanelVentas().setVisible(true);
			ventana.getSubPanelInsertVentas().setVisible(true);
			ventana.getSubPanelBotonesVentas().setVisible(true);
						
			//Ocultamos el resto
			ventana.getPanelEmpleado().setVisible(false);
			ventana.getSubPanelEmpInsertar().setVisible(false);
			ventana.getSubPanelEmpUpdate().setVisible(false);
			ventana.getSubPanelEmpDelete().setVisible(false);
			ventana.getSubPanelBotonesEmp().setVisible(false);
			ventana.getImagenInicio().setVisible(false);
			ventana.getPanelCompras().setVisible(false);	
			ventana.getSubPanelInsertCompras().setVisible(false);
			ventana.getSubPanelDeliveryNoteCompras().setVisible(false);
			ventana.getSubPanelComprasDelete().setVisible(false);
			ventana.getSubPanelBotonesCompras().setVisible(false);
			ventana.getPanelProveedores().setVisible(false);
			ventana.getPanelClientes().setVisible(false);
			ventana.getSubPanelInsCliente().setVisible(false);
			ventana.getPanelBotonesCliente().setVisible(false);
			ventana.getSubPanelEditCliente().setVisible(false);
			ventana.getSubPanelElimCliente().setVisible(false);
			ventana.getPanelCRM().setVisible(false);
			ventana.getPanelAlmacen().setVisible(false);
			ventana.getSubPanelAlmacenExport().setVisible(false);
			ventana.getSubPanelInsProv().setVisible(false);
			ventana.getSubPanelEditProv().setVisible(false);
			ventana.getSubPanelElimProv().setVisible(false);
			ventana.getPanelBotonesProv().setVisible(false);
			ventana.getSubPanelEmpExport().setVisible(false);
			ventana.getSubPanelComprasExport().setVisible(false);
			ventana.getSubPanelVentasExport().setVisible(false);
			ventana.getSubPanelBills().setVisible(false);
			ventana.getSubPanelVentasDelete().setVisible(false);
			ventana.getPanelNomina().setVisible(false);
			
		}
		
		//EN PRUEBAS
		else if(e.getSource() == ventana.getBotonVerificarVenta()) {			
			
			//Recogemos en un Array los servicios y clientes
			ArrayList<Servicio> lista_servicios = AccesoDB.datosServicio(conexion);
			ArrayList<Cliente> lista_clientes = AccesoDB.datosCliente(conexion);
			
			//Recogemos en variables los datos introducidos por el usuario
			String codServicio = ventana.getComboServicioVentas().getSelectedItem().toString();
			String dni_Cliente = ventana.getComboClienteVentas().getSelectedItem().toString();
			String numFactura = ventana.getJTFnumFactura().getText();
			String nombreServicio = null;
			String nombre = null;
			int cantidad = 0;
			int importeVentaServicio = 0;
			int cantidadTotal = 0;
			
			Calendar hoy = new GregorianCalendar(); //obtiene la fecha actual
			//ahora accedemos a los anios, meses, dias:
			int anio = hoy.get(Calendar.YEAR);
			int mes = hoy.get(Calendar.MONTH)+1;
			int dia = hoy. get(Calendar.DAY_OF_MONTH)+1;
			String f = anio +"-" + mes +"-"+dia;
			
			Date fecha = Date.valueOf(f);
			
			try {
				cantidad = Integer.parseInt(ventana.getJTFcantidadVenta().getText());
				if(cantidad <= 0) {
					//Mostramos Dialog 
					JOptionPane.showMessageDialog(new JFrame(), 
							"Please, insert a correct number for quantity",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException g) {
				g.getMessage();
				//Mostramos Dialog 
				JOptionPane.showMessageDialog(new JFrame(), 
						"Please, insert quantity",
						"Error",
						JOptionPane.ERROR_MESSAGE);
			}			
			
			//Comprobamos los datos de servicio
			for (Servicio servicio : lista_servicios) {
				if(codServicio.equalsIgnoreCase(servicio.getCodServicio())) {
					importeVentaServicio = servicio.getImporteServicio();
					cantidadTotal = cantidad * servicio.getImporteServicio();
					nombreServicio = servicio.getNombreServicio().toString();
					ventana.getJLresulComboSerVenta().setText(nombreServicio);
					ventana.getJLresulimporVentaServ().setText(Integer.toString(servicio.getImporteServicio()).toString()+" � unity");
					ventana.getJLresulimporTotalServ().setText(Integer.toString(cantidadTotal).toString()+" � total amount");
				} 
			}
			
			//Comprobamos los datos de clientes
			for (Cliente cliente : lista_clientes) {
				if(dni_Cliente.equalsIgnoreCase(cliente.getDni_Cliente())) {	
					nombre = cliente.getNombre().toString();
					ventana.getJLresulComboClienteVentas().setText(nombre);
				} 
			}
			
			//Si algun componente de la los valores del Insert est� vac�o
			if(ventana.getJTFnumFactura().getText().isEmpty() || ventana.getComboServicioVentas().getSelectedItem().toString().contains("Service code") ||
				    ventana.getComboClienteVentas().getSelectedItem().toString().contains("Customer's code"))
				{
				//Mostramos Dialog 
				JOptionPane.showMessageDialog(new JFrame(), 
						"Please, complete all the fields",
						"Check",
						JOptionPane.ERROR_MESSAGE);				
				} else {
					//Mostramos Dialog 
					JOptionPane.showMessageDialog(new JFrame(), 
							"You can insert a new Sale",
							"Check",
							JOptionPane.INFORMATION_MESSAGE);	
					
					ok_check =true;					
													
					Ventas v = new Ventas(numFactura, codServicio, nombreServicio, cantidad, importeVentaServicio, cantidadTotal, dni_Cliente, nombre, fecha);				
					nuevaVenta.add(v);
				}	
		}
		
		//EN PRUEBAS
		else if(e.getSource() == ventana.getBotonInsertVentaFinal()) {
			
			if(ok_check == true) {
				
				String numFactura = ventana.getJTFnumFactura().getText();
				
				for (Ventas ventas : nuevaVenta) {
					if(ventas.getNumFactura().contains(numFactura)) {
						//Mostramos Dialog 
						JOptionPane.showMessageDialog(new JFrame(), 
								"Delivery note exists. New line will be inserted",
								"Check",
								JOptionPane.INFORMATION_MESSAGE);	
						
						int afectados1 = AccesoDB.insertarLineaFactura(nuevaVenta, conexion);
						
						if (afectados1 == 0) {
							ventana.getJLresulinsertVentafinal().setText("Error adding Bill line");
						} else {
							ventana.getJLresulinsertVentafinal().setText("Bill line added");
							
							refreshJTableVentas();
						}		
					} else {
						int afectados = AccesoDB.insertarVenta(nuevaVenta, conexion);
						int afectados2 = AccesoDB.insertarLineaFactura(nuevaVenta, conexion);
						
						if (afectados == 0 || afectados2 == 0) {
							ventana.getJLresulinsertVentafinal().setText("Error adding Bill");
						} else {
							ventana.getJLresulinsertVentafinal().setText("Bill added");
							
							refreshJTableVentas();
						}		
					}
				}				
			} else {
				ventana.getJLresulinsertVentafinal().setText("Check all items");
			}
			
		}
		
		else if(e.getSource() == ventana.getBotonBillsVentas()) {
			
			//Limpiamos etiquetas
            ventana.getJLBills().setVisible(false);
            ventana.getJLCustomer().setVisible(false);
            ventana.getJLDate().setVisible(false);
            ventana.getJLCustomerName().setVisible(false);
            ventana.getJLTotalAmount().setVisible(false);
            ventana.getJLnumBill().setText("");
            ventana.getJLCustomerRow().setText("");
            ventana.getJLDateRow().setText("");
            ventana.getJLCustomerNameRow().setText("");
            ventana.getJLTotalAmountSum().setText("");
            barraBill.setVisible(false);
			
			//Mostramos el subpanel de compras
			ventana.getPanelVentas().setVisible(true);
			ventana.getSubPanelBills().setVisible(true);
			ventana.getSubPanelBotonesVentas().setVisible(true);
			
			//Ocultamos el resto
			ventana.getPanelCompras().setVisible(false);
			ventana.getSubPanelBotonesCompras().setVisible(false);
			ventana.getSubPanelInsertCompras().setVisible(false);
			ventana.getSubPanelComprasExport().setVisible(false);
			ventana.getSubPanelComprasDelete().setVisible(false);
			ventana.getSubPanelDeliveryNoteCompras().setVisible(false);
			ventana.getPanelEmpleado().setVisible(false);
			ventana.getSubPanelEmpInsertar().setVisible(false);
			ventana.getSubPanelEmpUpdate().setVisible(false);
			ventana.getSubPanelEmpDelete().setVisible(false);
			ventana.getSubPanelBotonesEmp().setVisible(false);
			ventana.getSubPanelEmpExport().setVisible(false);
			ventana.getImagenInicio().setVisible(false);			
			ventana.getSubPanelInsertVentas().setVisible(false);
			ventana.getSubPanelVentasExport().setVisible(false);
			ventana.getSubPanelVentasDelete().setVisible(false);
			ventana.getPanelProveedores().setVisible(false);
			ventana.getPanelClientes().setVisible(false);
			ventana.getSubPanelInsCliente().setVisible(false);
			ventana.getPanelBotonesCliente().setVisible(false);
			ventana.getSubPanelEditCliente().setVisible(false);
			ventana.getSubPanelElimCliente().setVisible(false);
			ventana.getPanelCRM().setVisible(false);
			ventana.getPanelAlmacen().setVisible(false);
			ventana.getSubPanelAlmacenExport().setVisible(false);
			ventana.getSubPanelInsProv().setVisible(false);
			ventana.getSubPanelEditProv().setVisible(false);
			ventana.getSubPanelElimProv().setVisible(false);
			ventana.getPanelBotonesProv().setVisible(false);	
			ventana.getPanelNomina().setVisible(false);
			//ventana.getSubPanelProvExport().setVisible(false);
			//ventana.getSubPanelClienteExport().setVisible(false);	
			
		}
		
		else if(e.getSource() == ventana.getBotonCheckBill()) {
			
			int row = ventana.getTablaVentas().getSelectedRow();

            if (row != -1) {
            	
            	//Mostramos datos de la fila seleccionada
                String numFactura = (String) ventana.getTablaVentas().getValueAt(row, 0);
                ventana.getJLnumBill().setText(numFactura);
                
                String fecha = (String) ventana.getTablaVentas().getValueAt(row, 8);
                ventana.getJLDateRow().setText(fecha);
                
                String cus = (String) ventana.getTablaVentas().getValueAt(row, 6);
                ventana.getJLCustomerRow().setText(cus);
                
                String cus_nif = (String) ventana.getTablaVentas().getValueAt(row, 7);
                ventana.getJLCustomerNameRow().setText(cus_nif);
                
                ArrayList<Ventas> lista_ventas = AccesoDB.datosVentasBill(numFactura, conexion);
                
                int suma = 0;
                
                for (Ventas v : lista_ventas) {					
                	suma = v.getTotal() + suma;
				}
                
                ventana.getJLTotalAmountSum().setText(Integer.toString(suma) + " �");
                
                //Mostramos datos de las Labels
                ventana.getJLBills().setVisible(true);
                ventana.getJLCustomer().setVisible(true);
                ventana.getJLDate().setVisible(true);
                ventana.getJLCustomerName().setVisible(true);
                ventana.getJLTotalAmount().setVisible(true);
                
                //Creamos la Tabla con la información
               
                barraBill.setVisible(true);
                barraBill.setBounds(20, 120, 710, 100);
                ventana.getSubPanelBills().add(barraBill);
        		
        		String titulosBillVen[] = {"Service Code", "Service Name", "Price", "Quantity", "Total"};
        		String infoVentas[][] = AccesoDB.obtenerMatrizBill(numFactura);
        		
        		JTable tablaBillVen = new JTable(infoVentas,titulosBillVen);
        		barraBill.setViewportView(tablaBillVen);

            } else {

            	//Mostramos Dialog 
				JOptionPane.showMessageDialog(new JFrame(), 
						"You must select a row",
						"Bill Detail",
						JOptionPane.ERROR_MESSAGE);
            }
		}
		
		else if(e.getSource() == ventana.getBotonDeleteVenta()) {

            ventana.getJLBillDeleteVen().setVisible(false);
            barraBillDelete.setVisible(false);
            ventana.getJLnumFacturaDeleteVen().setText("");
            ventana.getBotonDeleteLineaFactura().setVisible(false);
            ventana.getBotonDeleteFactura().setVisible(false);
            ventana.getJLselecJTableVenDeleteLineFactura().setVisible(false);
            ventana.getJLselecJTableVenDeleteFactura().setVisible(false);

			//Mostramos el subpanel de ventas
			ventana.getSubPanelVentasDelete().setVisible(true);
			ventana.getPanelVentas().setVisible(true);
			ventana.getSubPanelBotonesVentas().setVisible(true);

			//Ocultamos el resto			
			ventana.getSubPanelInsertCompras().setVisible(false);
			ventana.getSubPanelComprasExport().setVisible(false);
			ventana.getSubPanelDeliveryNoteCompras().setVisible(false);
			ventana.getPanelEmpleado().setVisible(false);
			ventana.getSubPanelEmpInsertar().setVisible(false);
			ventana.getSubPanelEmpUpdate().setVisible(false);
			ventana.getSubPanelEmpDelete().setVisible(false);
			ventana.getSubPanelBotonesEmp().setVisible(false);
			ventana.getImagenInicio().setVisible(false);			
			ventana.getSubPanelInsertVentas().setVisible(false);
			ventana.getSubPanelBills().setVisible(false);
			ventana.getSubPanelVentasExport().setVisible(false);
			ventana.getPanelProveedores().setVisible(false);
			ventana.getPanelClientes().setVisible(false);
			ventana.getSubPanelInsCliente().setVisible(false);
			ventana.getPanelBotonesCliente().setVisible(false);
			ventana.getSubPanelEditCliente().setVisible(false);
			ventana.getSubPanelElimCliente().setVisible(false);
			ventana.getPanelCRM().setVisible(false);
			ventana.getPanelAlmacen().setVisible(false);
			ventana.getSubPanelAlmacenExport().setVisible(false);
			ventana.getSubPanelInsProv().setVisible(false);
			ventana.getSubPanelEditProv().setVisible(false);
			ventana.getSubPanelElimProv().setVisible(false);
			ventana.getPanelBotonesProv().setVisible(false);
			ventana.getSubPanelEmpExport().setVisible(false);			
			ventana.getSubPanelComprasDelete().setVisible(false);
			ventana.getPanelCompras().setVisible(false);
			ventana.getSubPanelBotonesCompras().setVisible(false);
			ventana.getPanelNomina().setVisible(false);
		}
		
		else if(e.getSource() == ventana.getBotonCheckBillVenDelete()) {

			//Almacenamos en una variable el n�mero de la fila seleccionada
			int rowDelete = ventana.getTablaVentas().getSelectedRow();

			//Si el n�mero de fila es dstinto a -1 es que se ha seleccionado una fila
            if (rowDelete != -1) {

            	//Recogemos los valores de la fila seleccionada indicando los datos de la columna
                String numFacturaDelete = (String) ventana.getTablaVentas().getValueAt(rowDelete, 0);
                ventana.getJLnumFacturaDeleteVen().setText(numFacturaDelete);          

                //Mostramos datos de las Labels que est�n ocultos en la ventana
                ventana.getJLBillDeleteVen().setVisible(true);
                ventana.getBotonDeleteLineaFactura().setVisible(true);
                ventana.getBotonDeleteFactura().setVisible(true);
                ventana.getJLselecJTableVenDeleteLineFactura().setVisible(true);
                ventana.getJLselecJTableVenDeleteFactura().setVisible(true);

                //Creamos la Tabla con la informaci�n               
                barraBillDelete.setVisible(true);
                barraBillDelete.setBounds(20, 50, 710, 100);
                ventana.getSubPanelVentasDelete().add(barraBillDelete);

        		String titulosBillVen[] = {"Service Code", "Service Name", "Purchase amount", "Quantity","Total Amount", 
        				"Customer's DNI", "Customer", "Date"};
        		String infoVentas[][] = AccesoDB.obtenerMatrizBillDelete(numFacturaDelete);

        		tablaBillDeleteVen = new JTable(infoVentas,titulosBillVen);
        		barraBillDelete.setViewportView(tablaBillDeleteVen);

            } else {

            	//Mostramos Dialog 
				JOptionPane.showMessageDialog(new JFrame(), 
						"You must select a row",
						"Delete Bill",
						JOptionPane.ERROR_MESSAGE);
            }
		}
		
		else if(e.getSource() == ventana.getBotonDeleteLineaFactura()) {

			//Almacenamos en una variable el n�mero de la fila seleccionada
			int rowDeleteLine = tablaBillDeleteVen.getSelectedRow();

			//Recogemos los valores de la fila seleccionada indicando los datos de la columna
            String numFacturaDelete = ventana.getJLnumFacturaDeleteVen().getText();

			//Si el n�mero de fila es dstinto a -1 es que se ha seleccionado una fila
            if (rowDeleteLine != -1) {

            	//Recogemos los valores de la fila seleccionada indicando los datos de la columna
                String numServicioDelete = (String) tablaBillDeleteVen.getValueAt(rowDeleteLine, 0);
                //Recogemos los valores de la fila seleccionada indicando los datos de la columna
                int cantidadDeleteVen = Integer.parseInt((String) tablaBillDeleteVen.getValueAt(rowDeleteLine, 3));

                int afect = AccesoDB.deleteLineaFacturaVentas(numFacturaDelete, numServicioDelete, cantidadDeleteVen, conexion);

                if(afect == 1) {

                	//Mostramos Dialog 
    				JOptionPane.showMessageDialog(new JFrame(), 
    						"Bill's line deleted",
    						"Delete Bill",
    						JOptionPane.INFORMATION_MESSAGE);

    				refreshJTableDeleteVentas(numFacturaDelete);
    				refreshJTableVentas();

                } else {

                	//Mostramos Dialog 
    				JOptionPane.showMessageDialog(new JFrame(), 
    						"ERROR deleting Bill's line",
    						"Delete Bill",
    						JOptionPane.ERROR_MESSAGE);
                }

            } else {

            	//Mostramos Dialog 
				JOptionPane.showMessageDialog(new JFrame(), 
						"You must select a row",
						"Delete Bill",
						JOptionPane.ERROR_MESSAGE);
            }			
		}

		else if(e.getSource() == ventana.getBotonDeleteFactura()) {

			//Recogemos los valores de la fila seleccionada indicando los datos de la columna
            String numFacturaDelete = ventana.getJLnumFacturaDeleteVen().getText();

            int resp = JOptionPane.showConfirmDialog(null, 
            		"Do you want delete Bill "+ numFacturaDelete + "?\n"
            				+"This operation can't be undone.", 
            		"Delete Bill", 
            		JOptionPane.YES_NO_OPTION, 
            		JOptionPane.QUESTION_MESSAGE);

           if (JOptionPane.OK_OPTION == resp) {

        	   int afect = AccesoDB.deleteFacturaVentas(numFacturaDelete, conexion);
               int afect1 = AccesoDB.deleteLineaFacturaVentas(numFacturaDelete, conexion);

               if(afect == 1 && afect1 >= 1) {

               	//Mostramos Dialog 
   				JOptionPane.showMessageDialog(new JFrame(), 
   						"Bill DELETED from DataBase",
   						"Delete Bill",
   						JOptionPane.INFORMATION_MESSAGE);

   				refreshJTableDeleteVentas(numFacturaDelete);
   				refreshJTableVentas();

               } else {

               	//Mostramos Dialog 
   				JOptionPane.showMessageDialog(new JFrame(), 
   						"ERROR deleting Bill",
   						"Delete Bill",
   						JOptionPane.ERROR_MESSAGE);
               }

           } else {

              	//Mostramos Dialog 
  				JOptionPane.showMessageDialog(new JFrame(), 
  						"Abort Deleting ...",
  						"Delete Bill",
  						JOptionPane.ERROR_MESSAGE);
           }

		}
		
		else if(e.getSource() == ventana.getBotonExportVenta()) {
			
			//Limpiamos la etiqueta usuario
			ventana.getInsertUsuarioPCCompras().setText("");
			ventana.getResulExportVen().setText("");
			
			//Mostramos el subpanel de ventas
			
			ventana.getPanelVentas().setVisible(true);
			ventana.getSubPanelVentasExport().setVisible(true);
			ventana.getSubPanelBotonesVentas().setVisible(true);

			//Ocultamos el resto			
			ventana.getSubPanelInsertCompras().setVisible(false);
			ventana.getSubPanelComprasExport().setVisible(false);
			ventana.getSubPanelDeliveryNoteCompras().setVisible(false);
			ventana.getPanelEmpleado().setVisible(false);
			ventana.getSubPanelEmpInsertar().setVisible(false);
			ventana.getSubPanelEmpUpdate().setVisible(false);
			ventana.getSubPanelEmpDelete().setVisible(false);
			ventana.getSubPanelBotonesEmp().setVisible(false);
			ventana.getImagenInicio().setVisible(false);			
			ventana.getSubPanelInsertVentas().setVisible(false);
			ventana.getSubPanelBills().setVisible(false);
			ventana.getSubPanelVentasDelete().setVisible(false);
			ventana.getPanelProveedores().setVisible(false);
			ventana.getPanelClientes().setVisible(false);
			ventana.getSubPanelInsCliente().setVisible(false);
			ventana.getPanelBotonesCliente().setVisible(false);
			ventana.getSubPanelEditCliente().setVisible(false);
			ventana.getSubPanelElimCliente().setVisible(false);
			ventana.getPanelCRM().setVisible(false);
			ventana.getPanelAlmacen().setVisible(false);
			ventana.getSubPanelAlmacenExport().setVisible(false);
			ventana.getSubPanelInsProv().setVisible(false);
			ventana.getSubPanelEditProv().setVisible(false);
			ventana.getSubPanelElimProv().setVisible(false);
			ventana.getPanelBotonesProv().setVisible(false);
			ventana.getSubPanelEmpExport().setVisible(false);			
			ventana.getSubPanelComprasDelete().setVisible(false);
			ventana.getPanelCompras().setVisible(false);
			ventana.getSubPanelBotonesCompras().setVisible(false);
			ventana.getPanelNomina().setVisible(false);
			
		}
		
		else if(e.getSource() == ventana.getBotonExportVenFinal()) {
			
			//Recojemos el usuario del PC
			String user = ventana.getInsertUsuarioPCVentas().getText();
						
			if(user.isEmpty()) {
				
				//Mostramos Dialog 
  				JOptionPane.showMessageDialog(new JFrame(), 
  						"Please, enter you user",
  						"Export Sales",
  						JOptionPane.ERROR_MESSAGE);
			} else {
				
				Boolean ok_fichero = AccesoDB.exportarFicheroVentas(user);
				
				if (ok_fichero == true) {					
					ventana.getResulExportVen().setText("File Created");					
				} else {					
					ventana.getResulExportVen().setText("Error creating file");
				}
			}			
		}
		
		else if(e.getSource()==ventana.getBotonSuppliers()) {
			
			//Limpiamos todas la etiquetas rellenas
			ventana.getInsertCodProv().setText("");
			ventana.getInsertNomProv().setText("");
			ventana.getInsertContProv().setText("");
			ventana.getResulInsertProv().setText("");
			
			//Mostramos los paneles de Proveedores
			ventana.getPanelProveedores().setVisible(true);
			ventana.getSubPanelInsProv().setVisible(true);
			ventana.getPanelBotonesProv().setVisible(true);
			ventana.getSubPanelEditProv().setVisible(false);
			ventana.getSubPanelElimProv().setVisible(false);			
			
			//Ocultamos el resto
			ventana.getPanelEmpleado().setVisible(false);
			ventana.getSubPanelEmpInsertar().setVisible(false);
			ventana.getSubPanelEmpUpdate().setVisible(false);
			ventana.getSubPanelEmpDelete().setVisible(false);
			ventana.getSubPanelBotonesEmp().setVisible(false);
			ventana.getImagenInicio().setVisible(false);
			ventana.getPanelCompras().setVisible(false);
			ventana.getSubPanelInsertCompras().setVisible(false);
			ventana.getSubPanelBotonesCompras().setVisible(false);
			ventana.getSubPanelDeliveryNoteCompras().setVisible(false);
			ventana.getSubPanelComprasDelete().setVisible(false);
			ventana.getPanelVentas().setVisible(false);
			ventana.getSubPanelInsertVentas().setVisible(false);
			ventana.getSubPanelBotonesVentas().setVisible(false);
			ventana.getSubPanelBills().setVisible(false);
			ventana.getSubPanelVentasExport().setVisible(false);
			ventana.getSubPanelVentasDelete().setVisible(false);
			ventana.getPanelClientes().setVisible(false);
			ventana.getSubPanelInsCliente().setVisible(false);
			ventana.getPanelBotonesCliente().setVisible(false);
			ventana.getSubPanelEditCliente().setVisible(false);
			ventana.getSubPanelElimCliente().setVisible(false);
			ventana.getPanelCRM().setVisible(false);
			ventana.getPanelAlmacen().setVisible(false);
			ventana.getSubPanelAlmacenExport().setVisible(false);
			ventana.getSubPanelEmpExport().setVisible(false);
			ventana.getSubPanelComprasExport().setVisible(false);
			ventana.getPanelNomina().setVisible(false);
			
		}
		
		
		//BOTON INSERTAR PROVEEDOR 
		else if (e.getSource() == ventana.getBotonInsertProvOk()) {

			if(ventana.getInsertCodProv().getText().isEmpty() || ventana.getInsertNomProv().getText().isEmpty() ||
					ventana.getInsertContProv().getText().isEmpty())
			{
				ventana.getResulInsertProv().setForeground(Color.GRAY);
				ventana.getResulInsertProv().setText("Please, complete all the fields");
				
			} else {
				//Limpiamos la etiqueta de resultado final
				ventana.getResulInsertProv().setText("");				
				
				ArrayList<Proveedor> nuevoProveedor = new ArrayList<Proveedor>();
	
				// Recogemos los datos del nuevo empleado
				String codigo = ventana.getInsertCodProv().getText();
				String nombre = ventana.getInsertNomProv().getText();
				String mail = ventana.getInsertContProv().getText();
	
				Proveedor prov = new Proveedor(codigo, nombre, mail);
	
				nuevoProveedor.add(prov);
	
				int afectados = AccesoDB.insertarProveedor(nuevoProveedor, conexion);
	
				if (afectados == 0) {
					ventana.getResulInsertProv().setText("Error adding supplier");
				} else {
					ventana.getResulInsertProv().setText("Supplier added");
					refreshJTableProveedores();
				}
			}
			
		}
		
		else if(e.getSource() == ventana.getBotonActualizarProveedor()) {
			
			//Limpiamos todas la etiquetas rellenas
			ventana.getInsertCODUpdateProv().setText("");
			ventana.getInsertNewDataProv().setText("");
			ventana.getResultUpdateProv().setText("");
			
			//Ocultamos los paneles de Insert y delete
			ventana.getSubPanelInsProv().setVisible(false);
			ventana.getSubPanelElimProv().setVisible(false);
			
			//Mostramos el panel de Update
			ventana.getSubPanelEditProv().setVisible(true);
		}
		
		else if(e.getSource() == ventana.getBotonUpdateFinalPr()) {
			
			if(ventana.getInsertCODUpdateProv().getText().isEmpty() || ventana.getInsertNewDataProv().getText().isEmpty()) {
				ventana.getResultUpdateProv().setForeground(Color.RED);
				ventana.getResultUpdateProv().setText("Please, complete all the fields");
			} else {
				//Limpiamos la etiqueta de resultado final y devolvemos el color
				ventana.getResultUpdateProv().setText("");
				ventana.getResultUpdateProv().setForeground(new Color(0,157,233));
				
				//Recogemos los datos que queremos actualizar
				String cod = ventana.getInsertCODUpdateProv().getText();
				String campo = ventana.getComboUpdateProv().getSelectedItem().toString();
				String nuevoDato = ventana.getInsertNewDataProv().getText();
				
				int afectados = AccesoDB.actualizarProveedor(cod, campo, nuevoDato, conexion);
				
				if(afectados == 0) {
					ventana.getResultUpdateProv().setText("Error updating supplier");
				} else {
					ventana.getResultUpdateProv().setText("Supplier updated");
					refreshJTableProveedores();
				}
			}
		}
		
		else if(e.getSource() == ventana.getBotonBorrarProveedor()) {
			
			//Limpiamos etiquetas rellenas
			ventana.getInsertCODDeleteProv().setText("");
			ventana.getResulBusquedaProv().setText("");
			ventana.getResulDeleteProv().setText("");
			
			//Ocultamos los paneles de insert y update empleado as� como el bot�n delete
			ventana.getSubPanelInsProv().setVisible(false);
			ventana.getSubPanelEditProv().setVisible(false);
			ventana.getBotonDeleteProvFinal().setVisible(false);
			
			//Mostramos el panel de Update
			ventana.getSubPanelElimProv().setVisible(true);
		}
		
		else if(e.getSource() == ventana.getBotonSearchProv()) {
			
			//Recogemos el nif para buscar el empleado
			String cod = ventana.getInsertCODDeleteProv().getText();
			
			ArrayList<Proveedor> listaP = AccesoDB.datosProveedor(conexion);
			
			for (Proveedor proveedor : listaP) {
				
				if(proveedor.getCodproveedor().equalsIgnoreCase(cod)) {
					ventana.getResulBusquedaProv().setText("The supplier: | "+ proveedor.getNombreProveedor()
					+" "+" | , will be deleted.");
					ventana.getBotonDeleteProvFinal().setVisible(true);
					return;
				} else {
					ventana.getResulBusquedaProv().setText("Supplier doesn't exists");
				}
			}
		}
		
		else if(e.getSource() == ventana.getBotonDeleteProvFinal()) {
			
			//Recogemos el nif para buscar el empleado
			String cod = ventana.getInsertCODDeleteProv().getText();
			
			int afectados = AccesoDB.borrarProveedor(cod, conexion);
			
			if(afectados == 0) {
				ventana.getResulDeleteProv().setText("Error deleting supplier");
			} else {
				ventana.getResulDeleteProv().setText("Supplier deleted");
				refreshJTableProveedores();
			}
		}
		
		
		else if(e.getSource()==ventana.getBotonCustomers()) {
			
			//Mostramos panel de clientes
			ventana.getPanelClientes().setVisible(true);
			ventana.getSubPanelInsCliente().setVisible(true);
			ventana.getPanelBotonesCliente().setVisible(true);
			ventana.getSubPanelEditCliente().setVisible(false);
			ventana.getSubPanelElimCliente().setVisible(false);
			
			//Ocultamos el resto
			ventana.getPanelEmpleado().setVisible(false);
			ventana.getSubPanelEmpInsertar().setVisible(false);
			ventana.getSubPanelEmpUpdate().setVisible(false);
			ventana.getSubPanelEmpDelete().setVisible(false);
			ventana.getSubPanelBotonesEmp().setVisible(false);
			ventana.getImagenInicio().setVisible(false);
			ventana.getPanelCompras().setVisible(false);
			ventana.getSubPanelInsertCompras().setVisible(false);
			ventana.getSubPanelBotonesCompras().setVisible(false);
			ventana.getSubPanelDeliveryNoteCompras().setVisible(false);
			ventana.getSubPanelComprasDelete().setVisible(false);
			ventana.getPanelVentas().setVisible(false);
			ventana.getSubPanelInsertVentas().setVisible(false);
			ventana.getSubPanelBotonesVentas().setVisible(false);
			ventana.getSubPanelBills().setVisible(false);
			ventana.getSubPanelVentasExport().setVisible(false);
			ventana.getSubPanelVentasDelete().setVisible(false);
			ventana.getPanelProveedores().setVisible(false);			
			ventana.getPanelCRM().setVisible(false);
			ventana.getPanelAlmacen().setVisible(false);
			ventana.getSubPanelAlmacenExport().setVisible(false);
			ventana.getSubPanelInsProv().setVisible(false);
			ventana.getPanelBotonesProv().setVisible(false);
			ventana.getSubPanelEditProv().setVisible(false);
			ventana.getSubPanelElimProv().setVisible(false);
			ventana.getSubPanelEmpExport().setVisible(false);
			ventana.getSubPanelComprasExport().setVisible(false);	
			ventana.getPanelNomina().setVisible(false);
			
		}
		
		//BOT�N INSERTAR CLIENTE
		else if (e.getSource() == ventana.getBotonInsertClienteok()) {

			if(ventana.getInsertCodProv().getText().isEmpty() || ventana.getInsertNomProv().getText().isEmpty() ||
					ventana.getInsertContProv().getText().isEmpty())
			{
				ventana.getResulInsertCliente().setForeground(Color.GRAY);
				ventana.getResulInsertCliente().setText("Please, complete all the fields");
				
			} else {
				//Limpiamos la etiqueta de resultado final y devolvemos el color
				ventana.getResulInsertCliente().setText("");
				ventana.getResulInsertCliente().setForeground(new Color(0,157,233));
				
				
				ArrayList<Cliente> nuevoCliente = new ArrayList<Cliente>();

				// Recogemos los datos del nuevo empleado
				String dni = ventana.getInsertNIFCliente().getText();
				String nombre = ventana.getInsertNomCliente().getText();
				int telefono = Integer.parseInt(ventana.getInsertTelCliente().getText());
				String email = ventana.getInsertMailCliente().getText();

				Cliente cli = new Cliente(dni, nombre, email, telefono);

				nuevoCliente.add(cli);

				int afectados = AccesoDB.insertarCliente(nuevoCliente, conexion);

				if (afectados == 0) {
					ventana.getResulInsertCliente().setText("Error adding customer");
				} else {
					ventana.getResulInsertCliente().setText("Customer added");
					// AccesoDB.obtenerMatrizClientes();
					refreshJTableClientes();
				}
			}

		}		
		
		else if(e.getSource() == ventana.getBotonActualizarCliente()) {
			
			//Limpiamos todas la etiquetas rellenas
			ventana.getInsertNIFUpdateCliente().setText("");
			ventana.getInsertNewDataCliente().setText("");
			ventana.getResultUpdateCliente().setText("");
			
			//Ocultamos los paneles de Insert y delete
			ventana.getSubPanelInsCliente().setVisible(false);
			ventana.getSubPanelElimCliente().setVisible(false);
			
			//Mostramos el panel de Update
			ventana.getSubPanelEditCliente().setVisible(true);
		}
		
		else if(e.getSource() == ventana.getBotonUpdateFinalCl()) {
			
			if(ventana.getInsertNIFUpdateCliente().getText().isEmpty() || ventana.getInsertNewDataCliente().getText().isEmpty()) {
				ventana.getResultUpdateProv().setForeground(Color.RED);
				ventana.getResultUpdateCliente().setText("Please, complete all the fields");
			} else {
				//Limpiamos la etiqueta de resultado final y devolvemos el color
				ventana.getResultUpdateCliente().setText("");
				ventana.getResultUpdateCliente().setForeground(new Color(0,157,233));
				
				//Recogemos los datos que queremos actualizar
				String cod = ventana.getInsertNIFUpdateCliente().getText();
				String campo = ventana.getComboUpdateCliente().getSelectedItem().toString();
				String nuevoDato = ventana.getInsertNewDataCliente().getText();
				
				int afectados = AccesoDB.actualizarCliente(cod, campo, nuevoDato, conexion);
				
				if(afectados == 0) {
					ventana.getResultUpdateCliente().setText("Error updating customer");
				} else {
					ventana.getResultUpdateCliente().setText("Customer updated");
					refreshJTableClientes();
				}
			}
		}
		
		else if(e.getSource() == ventana.getBotonBorrarCliente()) {
			
			//Limpiamos etiquetas rellenas
			ventana.getInsertNIFDeleteCli().setText("");
			ventana.getResulBusquedaCli().setText("");
			ventana.getResulDeleteCliente().setText("");
			
			//Ocultamos los paneles de insert y update empleado as� como el bot�n delete
			ventana.getSubPanelInsCliente().setVisible(false);
			ventana.getSubPanelEditCliente().setVisible(false);
			ventana.getBotonDeleteClienteFinal().setVisible(false);
			
			//Mostramos el panel de Update
			ventana.getSubPanelElimCliente().setVisible(true);
		}
		
		else if(e.getSource() == ventana.getBotonSearchCliente()) {
			
			//Recogemos el nif para buscar el cliente
			String nif = ventana.getInsertNIFDeleteCli().getText();
			
			ArrayList<Cliente> listaCli = AccesoDB.datosCliente(conexion);
			
			for (Cliente cliente : listaCli) {
				
				if(cliente.getDni_Cliente().equalsIgnoreCase(nif)) {
					ventana.getResulBusquedaCli().setText("The customer: | "+ cliente.getNombre()
					+" "+" | , will be deleted.");
					ventana.getBotonDeleteClienteFinal().setVisible(true);
					return;
				} else {
					ventana.getResulBusquedaCli().setText("Customer doesn't exists");
				}
			}
		}
		
		else if(e.getSource() == ventana.getBotonDeleteClienteFinal()) {
			
			//Recogemos el nif para buscar el cliente
			String nif = ventana.getInsertNIFDeleteCli().getText();
			
			int afectados = AccesoDB.borrarCliente(nif, conexion);
			
			if(afectados == 0) {
				ventana.getResulDeleteCliente().setText("Error deleting customer");
			} else {
				ventana.getResulDeleteCliente().setText("Customer deleted");
				refreshJTableClientes();
			}
		}
		
		else if(e.getSource()==ventana.getBotonCRM()) {
			
			//Mostramos panel Servicios
			ventana.getPanelCRM().setVisible(true);
			
			//Ocultamos el resto
			ventana.getPanelEmpleado().setVisible(false);
			ventana.getSubPanelEmpInsertar().setVisible(false);
			ventana.getSubPanelEmpUpdate().setVisible(false);
			ventana.getSubPanelEmpDelete().setVisible(false);
			ventana.getSubPanelBotonesEmp().setVisible(false);
			ventana.getImagenInicio().setVisible(false);
			ventana.getPanelCompras().setVisible(false);
			ventana.getSubPanelInsertCompras().setVisible(false);
			ventana.getSubPanelDeliveryNoteCompras().setVisible(false);
			ventana.getSubPanelComprasDelete().setVisible(false);
			ventana.getSubPanelBotonesCompras().setVisible(false);
			ventana.getPanelVentas().setVisible(false);
			ventana.getSubPanelInsertVentas().setVisible(false);
			ventana.getSubPanelBotonesVentas().setVisible(false);
			ventana.getSubPanelBills().setVisible(false);
			ventana.getSubPanelVentasExport().setVisible(false);
			ventana.getSubPanelVentasDelete().setVisible(false);
			ventana.getPanelProveedores().setVisible(false);
			ventana.getPanelClientes().setVisible(false);
			ventana.getSubPanelInsCliente().setVisible(false);
			ventana.getPanelBotonesCliente().setVisible(false);
			ventana.getSubPanelEditCliente().setVisible(false);
			ventana.getSubPanelElimCliente().setVisible(false);
			ventana.getPanelAlmacen().setVisible(false);
			ventana.getSubPanelAlmacenExport().setVisible(false);
			ventana.getSubPanelInsProv().setVisible(false);
			ventana.getPanelBotonesProv().setVisible(false);
			ventana.getSubPanelEditProv().setVisible(false);
			ventana.getSubPanelElimProv().setVisible(false);
			ventana.getSubPanelEmpExport().setVisible(false);
			ventana.getSubPanelComprasExport().setVisible(false);			
			ventana.getPanelNomina().setVisible(false);
		}
		
		else if(e.getSource()==ventana.getBotonStock()) {
			
			//Mostramos panel de Stock
			ventana.getPanelAlmacen().setVisible(true);
			ventana.getSubPanelAlmacenExport().setVisible(false);
	
			
			//Ocultamos el resto
			ventana.getPanelEmpleado().setVisible(false);
			ventana.getSubPanelEmpInsertar().setVisible(false);
			ventana.getSubPanelEmpUpdate().setVisible(false);
			ventana.getSubPanelEmpDelete().setVisible(false);
			ventana.getSubPanelBotonesEmp().setVisible(false);
			ventana.getImagenInicio().setVisible(false);
			ventana.getPanelCompras().setVisible(false);
			ventana.getSubPanelInsertCompras().setVisible(false);
			ventana.getSubPanelBotonesCompras().setVisible(false);
			ventana.getSubPanelDeliveryNoteCompras().setVisible(false);
			ventana.getSubPanelComprasDelete().setVisible(false);
			ventana.getPanelVentas().setVisible(false);
			ventana.getSubPanelInsertVentas().setVisible(false);
			ventana.getSubPanelBotonesVentas().setVisible(false);
			ventana.getSubPanelBills().setVisible(false);
			ventana.getSubPanelVentasExport().setVisible(false);
			ventana.getSubPanelVentasDelete().setVisible(false);
			ventana.getPanelProveedores().setVisible(false);
			ventana.getPanelClientes().setVisible(false);
			ventana.getSubPanelInsCliente().setVisible(false);
			ventana.getPanelBotonesCliente().setVisible(false);
			ventana.getSubPanelEditCliente().setVisible(false);
			ventana.getSubPanelElimCliente().setVisible(false);
			ventana.getPanelCRM().setVisible(false);			
			ventana.getSubPanelInsProv().setVisible(false);
			ventana.getPanelBotonesProv().setVisible(false);
			ventana.getSubPanelEditProv().setVisible(false);
			ventana.getSubPanelElimProv().setVisible(false);
			ventana.getSubPanelEmpExport().setVisible(false);
			ventana.getSubPanelComprasExport().setVisible(false);
			ventana.getPanelNomina().setVisible(false);
			
		}	
		
		else if(e.getSource() == ventana.getBotonExportStock()) {
			
			//Limpiamos la etiqueta usuario
			ventana.getInsertUsuarioPCAlmacen().setText("");
			ventana.getResulExportAlm().setText("");
			
			//Ocultamos los paneles de insert, update empleado y boton delete
			ventana.getPanelAlmacen().setVisible(false);

			//Mostramos el panel de Export
			ventana.getSubPanelAlmacenExport().setVisible(true);
			
		}
		
		else if(e.getSource() == ventana.getBotonExportAlmFinal()) {
			
			//Recojemos el usuario del PC
			String user = ventana.getInsertUsuarioPCAlmacen().getText();
						
			if(user.isEmpty()) {
				
				//Mostramos Dialog 
  				JOptionPane.showMessageDialog(new JFrame(), 
  						"Please, enter you user",
  						"Export Stock",
  						JOptionPane.ERROR_MESSAGE);
			} else {
				
				Boolean ok_fichero = AccesoDB.exportarFicheroAlmacen(user);
				
				if (ok_fichero == true) {					
					ventana.getResulExportAlm().setText("File Created");					
				} else {					
					ventana.getResulExportAlm().setText("Error creating file");
				}
			}			
		}
		
		//Atras --> Si pulsamos en la imagen del Logo sale la imagen de Inicio
		else if(e.getSource()==ventana.getImageLogo()) {
			
			//Mostramos la imagen de Inicio
			ventana.getImagenInicio().setVisible(true);
			
			//Ocultamos el resto
			ventana.getPanelEmpleado().setVisible(false);
			ventana.getSubPanelEmpInsertar().setVisible(false);
			ventana.getSubPanelEmpUpdate().setVisible(false);
			ventana.getSubPanelEmpDelete().setVisible(false);
			ventana.getSubPanelBotonesEmp().setVisible(false);
			ventana.getPanelCompras().setVisible(false);
			ventana.getSubPanelInsertCompras().setVisible(false);
			ventana.getSubPanelDeliveryNoteCompras().setVisible(false);
			ventana.getSubPanelComprasDelete().setVisible(false);
			ventana.getPanelVentas().setVisible(false);
			ventana.getSubPanelInsertVentas().setVisible(false);
			ventana.getSubPanelBotonesVentas().setVisible(false);
			ventana.getSubPanelBills().setVisible(false);
			ventana.getSubPanelVentasExport().setVisible(false);
			ventana.getSubPanelVentasDelete().setVisible(false);
			ventana.getPanelProveedores().setVisible(false);
			ventana.getPanelClientes().setVisible(false);
			ventana.getSubPanelInsCliente().setVisible(false);
			ventana.getPanelBotonesCliente().setVisible(false);
			ventana.getSubPanelEditCliente().setVisible(false);
			ventana.getSubPanelElimCliente().setVisible(false);
			ventana.getPanelCRM().setVisible(false);
			ventana.getPanelAlmacen().setVisible(false);
			ventana.getSubPanelAlmacenExport().setVisible(false);
			ventana.getSubPanelInsProv().setVisible(false);
			ventana.getPanelBotonesProv().setVisible(false);
			ventana.getSubPanelEditProv().setVisible(false);
			ventana.getSubPanelElimProv().setVisible(false);
			ventana.getSubPanelEmpExport().setVisible(false);
			ventana.getSubPanelComprasExport().setVisible(false);
					
		}
		
	}

	private void refreshJTableDeleteVentas(String numFacturaDelete) {
		// TODO Auto-generated method stub
		
		String titulosBillVen[] = {"Service Code", "Service Name", "Purchase amount", "Quantity","Total Amount", 
				"Customer's DNI", "Customer", "Date"};
		String infoVentas[][] = AccesoDB.obtenerMatrizBillDelete(numFacturaDelete);

		TableModel modelo = new DefaultTableModel(infoVentas, titulosBillVen);

		tablaBillDeleteVen.setModel(modelo);

		((AbstractTableModel) modelo).fireTableDataChanged();
		
	}

	private void refreshJTableDeleteCompras(String numAlbaranDelete) {
		// TODO Auto-generated method stub

		String titulosDeliNoteCom[] = { "Product's Code", "Product's Name", "Purchase amount", "Quantity",
				"Total Account", "Supplier's code", "Supplier", "Date" };
		String infoCompras[][] = AccesoDB.obtenerMatrizDeliveryNoteDelete(numAlbaranDelete);

		TableModel modelo = new DefaultTableModel(infoCompras, titulosDeliNoteCom);

		tablaDeliNoteDeleteCom.setModel(modelo);

		((AbstractTableModel) modelo).fireTableDataChanged();

	}

	private void refreshJTableCompras() {
		String titulosCompras[] = { "Delivery Note's Code", "Product's Code", "Product's Name", "Quantity",
				"Purchase amount", "Total Account", "Supplier's code", "Supplier", "Date" };
		String infoCompras[][] = AccesoDB.obtenerMatrizCompras();

		TableModel modelo = new DefaultTableModel(infoCompras, titulosCompras);

		ventana.getTablaCompras().setModel(modelo);

		((AbstractTableModel) modelo).fireTableDataChanged();
	}

	private void refreshJTableEmpleados() {
		// ACTUALIZAR JTABLE EMPLEADOS

		String titulosEmpleados[] = { "Nombre", "Apellidos", "e-mail", "NIF", "Telefono" };
		String infoEmpleados[][] = AccesoDB.obtenerMatrizEmpleados();

		TableModel modelo = new DefaultTableModel(infoEmpleados, titulosEmpleados);

		ventana.getTablaEmpleados().setModel(modelo);

		((AbstractTableModel) modelo).fireTableDataChanged();
	}

	private void refreshJTableProveedores() {
		// ACTUALIZAR JTABLE PROVEEDORES

		String titulosProveedores[] = { "Codigo proveedor", "Nombre proveedor", "contacto" };
		String infoProveedores[][] = AccesoDB.obtenerMatrizProveedores();

		TableModel modelo = new DefaultTableModel(infoProveedores, titulosProveedores);

		ventana.getTablaProveedores().setModel(modelo);

		((AbstractTableModel) modelo).fireTableDataChanged();
	}

	private void refreshJTableClientes() {
		// ACTUALIZAR JTABLE CLIENTES

		String titulosClientes[] = { "DNI Cliente", "Nombre Cliente", "Email", "Tel�fono" };
		String infoClientes[][] = AccesoDB.obtenerMatrizClientes();

		TableModel modelo = new DefaultTableModel(infoClientes, titulosClientes);

		ventana.getTablaClientes().setModel(modelo);

		((AbstractTableModel) modelo).fireTableDataChanged();
	}

	private void refreshJTableVentas() {
		// ACTUALIZAR JTABLE VENTAS

		String titulosVentas[] = { "Bill's Code", "Service Code", "Service Name", "Quantity", "Sale amount", "Total",
				"Customer's DNI", "Customer", "Date" };
		String infoVentas[][] = AccesoDB.obtenerMatrizVentas();

		TableModel modelo = new DefaultTableModel(infoVentas, titulosVentas);

		ventana.getTablaVentas().setModel(modelo);

		((AbstractTableModel) modelo).fireTableDataChanged();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==ventana.getBotonLogin()) {			
			 ventana.getBotonLogin().setIcon(new ImageIcon("imagenes/login hover.png"));			 
		}
		else if (e.getSource()==ventana.getBotonExit()) {
			ventana.getBotonExit().setIcon(new ImageIcon("imagenes/exit_login_hover.png"));
		}		
		else if (e.getSource()==ventana.getBotonPurchases()) {
			ventana.getBotonPurchases().setIcon(new ImageIcon("img/purchases hover.png"));
		}		
		else if (e.getSource()==ventana.getBotonSales()) {
			ventana.getBotonSales().setIcon(new ImageIcon("img/sales hover.png"));
		}		
		else if (e.getSource()==ventana.getBotonSuppliers()) {
			ventana.getBotonSuppliers().setIcon(new ImageIcon("img/suppliers hover.png"));
		}		
		else if (e.getSource()==ventana.getBotonCustomers()) {
			ventana.getBotonCustomers().setIcon(new ImageIcon("img/customers hover.png"));
		}		
		else if (e.getSource()==ventana.getBotonCRM()) {
			ventana.getBotonCRM().setIcon(new ImageIcon("img/crm hover.png"));
		}		
		else if (e.getSource()==ventana.getBotonStock()) {
			ventana.getBotonStock().setIcon(new ImageIcon("img/stock hover.png"));
		}		
		else if (e.getSource()==ventana.getBotonHR()) {
			ventana.getBotonHR().setIcon(new ImageIcon("img/human resources hover.png"));
		}		
		else if (e.getSource()==ventana.getBotonUser()) {
			ventana.getBotonUser().setIcon(new ImageIcon("img/boton_user hover.png"));
		}		
		else if (e.getSource()==ventana.getBotonLogout()) {
			ventana.getBotonLogout().setIcon(new ImageIcon("img/logout hover.png"));
		}		
		else if (e.getSource()==ventana.getBotonExitInit()) {
			ventana.getBotonExitInit().setIcon(new ImageIcon("img/exit hover.png"));
		}	
		else if (e.getSource()==ventana.getBotonInsertEmpFinal()) {
			Image imgBotonInsertFinal = new ImageIcon("img\\insert hover.png").getImage();
			ventana.getBotonInsertEmpFinal().setIcon(new ImageIcon(imgBotonInsertFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonActualizarEmpleado()) {
			Image imgBotonUpdateEmpleado = new ImageIcon("img\\update employee hover.png").getImage();
			ventana.getBotonActualizarEmpleado().setIcon(new ImageIcon(imgBotonUpdateEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonBorrarEmpleado()) {
			Image imgBotonDeleteEmpleado = new ImageIcon("img\\delete employee hover.png").getImage();
			ventana.getBotonBorrarEmpleado().setIcon(new ImageIcon(imgBotonDeleteEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExpEmplFichero()) {
			Image imgBotonExportEmpleado = new ImageIcon("img\\export to file hover.png").getImage();
			ventana.getBotonExpEmplFichero().setIcon(new ImageIcon(imgBotonExportEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}	
		else if (e.getSource()==ventana.getBotonUpdateEmpFinal()) {
			Image imgBotonUpdateFinal = new ImageIcon("img\\update hover.png").getImage();
			ventana.getBotonUpdateEmpFinal().setIcon(new ImageIcon(imgBotonUpdateFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonSearchEmp()) {
			Image imgBotonSearchEmp = new ImageIcon("img\\search hover.png").getImage();
			ventana.getBotonSearchEmp().setIcon(new ImageIcon(imgBotonSearchEmp.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}	
		else if (e.getSource()==ventana.getBotonDeleteEmpFinal()) {
			Image imgBotonDeleteEmpFinal = new ImageIcon("img\\delete hover.png").getImage();
			ventana.getBotonDeleteEmpFinal().setIcon(new ImageIcon(imgBotonDeleteEmpFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportEmpFinal()) {
			Image imgBotonExportEmpleadoFinal = new ImageIcon("img\\export to file hover.png").getImage();
			ventana.getBotonExportEmpFinal().setIcon(new ImageIcon(imgBotonExportEmpleadoFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonInsertProvOk()) {
			Image imgBotonInsertProvOk = new ImageIcon("img\\insert hover.png").getImage();
			ventana.getBotonInsertProvOk().setIcon(new ImageIcon(imgBotonInsertProvOk.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonActualizarProveedor()) {
			Image imgBotonUpdateProveedor = new ImageIcon("img\\update supplier hover.png").getImage();
			ventana.getBotonActualizarProveedor().setIcon(new ImageIcon(imgBotonUpdateProveedor.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonBorrarProveedor()) {
			Image imgBotonBorrarProveedor = new ImageIcon("img\\delete supplier hover.png").getImage();
			ventana.getBotonBorrarProveedor().setIcon(new ImageIcon(imgBotonBorrarProveedor.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonDeliveryNotes()) {
			Image imgBotonDeliveryNotes = new ImageIcon("img\\delivery notes hover.png").getImage();
			ventana.getBotonDeliveryNotes().setIcon(new ImageIcon(imgBotonDeliveryNotes.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonUpdateFinalPr()) {
			Image imgBotonUpdateFinalPr = new ImageIcon("img\\update hover.png").getImage();
			ventana.getBotonUpdateFinalPr().setIcon(new ImageIcon(imgBotonUpdateFinalPr.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonSearchProv()) {
			Image imgBotonSearchProv = new ImageIcon("img\\search hover.png").getImage();
			ventana.getBotonSearchProv().setIcon(new ImageIcon(imgBotonSearchProv.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}	
		else if (e.getSource()==ventana.getBotonDeleteProvFinal()) {
			Image imgBotonDeleteProvFinal = new ImageIcon("img\\delete hover.png").getImage();
			ventana.getBotonDeleteProvFinal().setIcon(new ImageIcon(imgBotonDeleteProvFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonInsertClienteok()) {
			Image imgBotonInsertClienteok = new ImageIcon("img\\insert hover.png").getImage();
			ventana.getBotonInsertClienteok().setIcon(new ImageIcon(imgBotonInsertClienteok.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonActualizarCliente()) {
			Image imgBotonActualizarCliente = new ImageIcon("img\\update customer hover.png").getImage();
			ventana.getBotonActualizarCliente().setIcon(new ImageIcon(imgBotonActualizarCliente.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonBorrarCliente()) {
			Image imgBotonBorrarCliente = new ImageIcon("img\\delete customer hover.png").getImage();
			ventana.getBotonBorrarCliente().setIcon(new ImageIcon(imgBotonBorrarCliente.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonBills()) {
			Image imgBotonBills = new ImageIcon("img\\bills hover.png").getImage();
			ventana.getBotonBills().setIcon(new ImageIcon(imgBotonBills.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonUpdateFinalCl()) {
			Image imgBotonUpdateFinalCl = new ImageIcon("img\\update hover.png").getImage();
			ventana.getBotonUpdateFinalCl().setIcon(new ImageIcon(imgBotonUpdateFinalCl.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonSearchCliente()) {
			Image imgBotonSearchCliente = new ImageIcon("img\\search hover.png").getImage();
			ventana.getBotonSearchCliente().setIcon(new ImageIcon(imgBotonSearchCliente.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}	
		else if (e.getSource()==ventana.getBotonDeleteClienteFinal()) {
			Image imgBotonDeleteClienteFinal = new ImageIcon("img\\delete hover.png").getImage();
			ventana.getBotonDeleteClienteFinal().setIcon(new ImageIcon(imgBotonDeleteClienteFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonDeliveryNoteCompra()) {
			Image imgBotonActualCompra = new ImageIcon("img\\delivery notes hover.png").getImage();
			ventana.getBotonDeliveryNoteCompra().setIcon(new ImageIcon(imgBotonActualCompra.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonDeleteCompra()) {
			Image imgBotonDeleteCompra = new ImageIcon("img\\delete purchase hover.png").getImage();
			ventana.getBotonDeleteCompra().setIcon(new ImageIcon(imgBotonDeleteCompra.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportCompra()) {
			Image imgBotonExportCompra = new ImageIcon("img\\export to file hover.png").getImage();
			ventana.getBotonExportCompra().setIcon(new ImageIcon(imgBotonExportCompra.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportComFinal()) {
			Image imgBotonExportComFinal = new ImageIcon("img\\export to file hover.png").getImage();
			ventana.getBotonExportComFinal().setIcon(new ImageIcon(imgBotonExportComFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonDeleteVenta()) {
			Image imgBotonDeleteVenta = new ImageIcon("img\\delete sale hover.png").getImage();
			ventana.getBotonDeleteVenta().setIcon(new ImageIcon(imgBotonDeleteVenta.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportVenta()) {
			Image imgBotonExportVenta = new ImageIcon("img\\export to file hover.png").getImage();
			ventana.getBotonExportVenta().setIcon(new ImageIcon(imgBotonExportVenta.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportVenFinal()) {
			Image imgBotonExportVenFinal = new ImageIcon("img\\export to file hover.png").getImage();
			ventana.getBotonExportVenFinal().setIcon(new ImageIcon(imgBotonExportVenFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportStock()) {
			Image imgBotonExportStock = new ImageIcon("img\\export to file hover.png").getImage();
			ventana.getBotonExportStock().setIcon(new ImageIcon(imgBotonExportStock.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportAlmFinal()) {
			Image imgBotonExportAlmFinal = new ImageIcon("img\\export to file hover.png").getImage();
			ventana.getBotonExportAlmFinal().setIcon(new ImageIcon(imgBotonExportAlmFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonVerificarCompra()) {
			Image imgBotonVerificarCompra = new ImageIcon("img\\check hover.png").getImage();
			ventana.getBotonVerificarCompra().setIcon(new ImageIcon(imgBotonVerificarCompra.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonInsertCompraFinal()) {
			Image imgBotonInsertComprafinal = new ImageIcon("img\\purchases hover.png").getImage();
			ventana.getBotonInsertCompraFinal().setIcon(new ImageIcon(imgBotonInsertComprafinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonCheckDeliNoteCom()) {
			Image imgBotonCheckDeliNoteCom = new ImageIcon("img\\check hover.png").getImage();
			ventana.getBotonCheckDeliNoteCom().setIcon(new ImageIcon(imgBotonCheckDeliNoteCom.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonCheckDeliNoteComDelete()) {
			Image imgBotonCheckDeliNoteComDelete = new ImageIcon("img\\check hover.png").getImage();
			ventana.getBotonCheckDeliNoteComDelete().setIcon(new ImageIcon(imgBotonCheckDeliNoteComDelete.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}	
		else if(e.getSource()==ventana.getBotonDeleteAlabaran()) {
			Image imgBotonDeleteAlabaran = new ImageIcon("img\\delete purchase hover.png").getImage();
			ventana.getBotonDeleteAlabaran().setIcon(new ImageIcon(imgBotonDeleteAlabaran.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonBillsVentas()) {
			Image imgBotonBillsVentas = new ImageIcon("img\\bills hover.png").getImage();
			ventana.getBotonBillsVentas().setIcon(new ImageIcon(imgBotonBillsVentas.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonCheckBill()) {
			Image imgBotonCheckBill = new ImageIcon("img\\check hover.png").getImage();
			ventana.getBotonCheckBill().setIcon(new ImageIcon(imgBotonCheckBill.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonDeleteLineaAlbaran()) {
			Image imgBotonDeletLineAl= new ImageIcon("img\\delete line hover.png").getImage();
			ventana.getBotonDeleteLineaAlbaran().setIcon(new ImageIcon(imgBotonDeletLineAl.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonDeleteLineaFactura()) {
			Image imgBotonDeletLineAl= new ImageIcon("img\\delete line hover.png").getImage();
			ventana.getBotonDeleteLineaFactura().setIcon(new ImageIcon(imgBotonDeletLineAl.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonVerificarVenta()) {
			Image imgBotonVerifVenta = new ImageIcon("img\\check hover.png").getImage();
			ventana.getBotonVerificarVenta().setIcon(new ImageIcon(imgBotonVerifVenta.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonInsertVentaFinal()) {
			Image imgBotonInVentafinal = new ImageIcon("img\\sales hover.png").getImage();
			ventana.getBotonInsertVentaFinal().setIcon(new ImageIcon(imgBotonInVentafinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonCheckBillVenDelete()) {
			Image imgBotonVerifVenDelete = new ImageIcon("img\\check hover.png").getImage();
			ventana.getBotonCheckBillVenDelete().setIcon(new ImageIcon(imgBotonVerifVenDelete.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonDeleteFactura()) {
			Image imgBotonDeleteFact = new ImageIcon("img\\delete sale hover.png").getImage();
			ventana.getBotonDeleteFactura().setIcon(new ImageIcon(imgBotonDeleteFact.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==ventana.getBotonLogin()) {
			 ventana.getBotonLogin().setIcon(new ImageIcon("imagenes/login.png"));
		}
		else if (e.getSource()==ventana.getBotonExit()) {
			ventana.getBotonExit().setIcon(new ImageIcon("imagenes/exit_login.png"));
		}		
		else if (e.getSource()==ventana.getBotonPurchases()) {
			ventana.getBotonPurchases().setIcon(new ImageIcon("img/purchases.png"));
		}		
		else if (e.getSource()==ventana.getBotonSales()) {
			ventana.getBotonSales().setIcon(new ImageIcon("img/sales.png"));
		}		
		else if (e.getSource()==ventana.getBotonSuppliers()) {
			ventana.getBotonSuppliers().setIcon(new ImageIcon("img/suppliers.png"));
		}		
		else if (e.getSource()==ventana.getBotonCustomers()) {
			ventana.getBotonCustomers().setIcon(new ImageIcon("img/customers.png"));
		}		
		else if (e.getSource()==ventana.getBotonCRM()) {
			ventana.getBotonCRM().setIcon(new ImageIcon("img/crm.png"));
		}		
		else if (e.getSource()==ventana.getBotonStock()) {
			ventana.getBotonStock().setIcon(new ImageIcon("img/stock.png"));
		}		
		else if (e.getSource()==ventana.getBotonHR()) {
			ventana.getBotonHR().setIcon(new ImageIcon("img/human resources.png"));
		}
		else if (e.getSource()==ventana.getBotonUser()) {
			ventana.getBotonUser().setIcon(new ImageIcon("img/boton_user.png"));
		}		
		else if (e.getSource()==ventana.getBotonLogout()) {
			ventana.getBotonLogout().setIcon(new ImageIcon("img/logout.png"));
		}		
		else if (e.getSource()==ventana.getBotonExitInit()) {
			ventana.getBotonExitInit().setIcon(new ImageIcon("img/exit.png"));
		}		
		else if (e.getSource()==ventana.getBotonInsertEmpFinal()) {
			Image imgBotonInsertFinal = new ImageIcon("img\\insert.png").getImage();
			ventana.getBotonInsertEmpFinal().setIcon(new ImageIcon(imgBotonInsertFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonActualizarEmpleado()) {
			Image imgBotonUpdateEmpleado = new ImageIcon("img\\update employee.png").getImage();
			ventana.getBotonActualizarEmpleado().setIcon(new ImageIcon(imgBotonUpdateEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonBorrarEmpleado()) {
			Image imgBotonDeleteEmpleado = new ImageIcon("img\\delete employee.png").getImage();
			ventana.getBotonBorrarEmpleado().setIcon(new ImageIcon(imgBotonDeleteEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExpEmplFichero()) {
			Image imgBotonExportEmpleado = new ImageIcon("img\\export to file.png").getImage();
			ventana.getBotonExpEmplFichero().setIcon(new ImageIcon(imgBotonExportEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonUpdateEmpFinal()) {
			Image imgBotonUpdateFinal = new ImageIcon("img\\update.png").getImage();
			ventana.getBotonUpdateEmpFinal().setIcon(new ImageIcon(imgBotonUpdateFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonSearchEmp()) {
			Image imgBotonSearchEmp = new ImageIcon("img\\search.png").getImage();
			ventana.getBotonSearchEmp().setIcon(new ImageIcon(imgBotonSearchEmp.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonDeleteEmpFinal()) {
			Image imgBotonDeleteEmpFinal = new ImageIcon("img\\delete.png").getImage();
			ventana.getBotonDeleteEmpFinal().setIcon(new ImageIcon(imgBotonDeleteEmpFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportEmpFinal()) {
			Image imgBotonExportEmpleado = new ImageIcon("img\\export to file.png").getImage();
			ventana.getBotonExportEmpFinal().setIcon(new ImageIcon(imgBotonExportEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonActualizarProveedor()) {
			Image imgBotonUpdateProveedor = new ImageIcon("img\\update supplier.png").getImage();
			ventana.getBotonActualizarProveedor().setIcon(new ImageIcon(imgBotonUpdateProveedor.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonBorrarProveedor()) {
			Image imgBotonBorrarProveedor = new ImageIcon("img\\delete supplier.png").getImage();
			ventana.getBotonBorrarProveedor().setIcon(new ImageIcon(imgBotonBorrarProveedor.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonDeliveryNotes()) {
			Image imgBotonDeliveryNotes = new ImageIcon("img\\delivery notes.png").getImage();
			ventana.getBotonDeliveryNotes().setIcon(new ImageIcon(imgBotonDeliveryNotes.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}	
		else if (e.getSource()==ventana.getBotonInsertProvOk()) {
			Image imgBotonInsertProvOk = new ImageIcon("img\\insert.png").getImage();
			ventana.getBotonInsertProvOk().setIcon(new ImageIcon(imgBotonInsertProvOk.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonUpdateFinalPr()) {
			Image imgBotonUpdateFinalPr = new ImageIcon("img\\update.png").getImage();
			ventana.getBotonUpdateFinalPr().setIcon(new ImageIcon(imgBotonUpdateFinalPr.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonSearchProv()) {
			Image imgBotonSearchProv = new ImageIcon("img\\search.png").getImage();
			ventana.getBotonSearchProv().setIcon(new ImageIcon(imgBotonSearchProv.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonDeleteProvFinal()) {
			Image imgBotonDeleteProvFinal = new ImageIcon("img\\delete.png").getImage();
			ventana.getBotonDeleteProvFinal().setIcon(new ImageIcon(imgBotonDeleteProvFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonInsertClienteok()) {
			Image imgBotonInsertClienteok = new ImageIcon("img\\insert customer.png").getImage();
			ventana.getBotonInsertClienteok().setIcon(new ImageIcon(imgBotonInsertClienteok.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonActualizarCliente()) {
			Image imgBotonActualizarCliente = new ImageIcon("img\\update customer.png").getImage();
			ventana.getBotonActualizarCliente().setIcon(new ImageIcon(imgBotonActualizarCliente.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonBorrarCliente()) {
			Image imgBotonBorrarCliente = new ImageIcon("img\\delete customer.png").getImage();
			ventana.getBotonBorrarCliente().setIcon(new ImageIcon(imgBotonBorrarCliente.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonBills()) {
			Image imgBotonBills = new ImageIcon("img\\bills.png").getImage();
			ventana.getBotonBills().setIcon(new ImageIcon(imgBotonBills.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonUpdateFinalCl()) {
			Image imgBotonUpdateFinalCl = new ImageIcon("img\\update.png").getImage();
			ventana.getBotonUpdateFinalCl().setIcon(new ImageIcon(imgBotonUpdateFinalCl.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonSearchCliente()) {
			Image imgBotonSearchCliente = new ImageIcon("img\\search.png").getImage();
			ventana.getBotonSearchCliente().setIcon(new ImageIcon(imgBotonSearchCliente.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}	
		else if (e.getSource()==ventana.getBotonDeleteClienteFinal()) {
			Image imgBotonDeleteClienteFinal = new ImageIcon("img\\delete.png").getImage();
			ventana.getBotonDeleteClienteFinal().setIcon(new ImageIcon(imgBotonDeleteClienteFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonDeliveryNoteCompra()) {
			Image imgBotonActualCompra = new ImageIcon("img\\delivery notes.png").getImage();
			ventana.getBotonDeliveryNoteCompra().setIcon(new ImageIcon(imgBotonActualCompra.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonDeleteCompra()) {
			Image imgBotonDeleteCompra = new ImageIcon("img\\delete purchase.png").getImage();
			ventana.getBotonDeleteCompra().setIcon(new ImageIcon(imgBotonDeleteCompra.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportCompra()) {
			Image imgBotonExportCompra = new ImageIcon("img\\export to file.png").getImage();
			ventana.getBotonExportCompra().setIcon(new ImageIcon(imgBotonExportCompra.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportComFinal()) {
			Image imgBotonExportComFinal = new ImageIcon("img\\export to file.png").getImage();
			ventana.getBotonExportComFinal().setIcon(new ImageIcon(imgBotonExportComFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonDeleteVenta()) {
			Image imgBotonDeleteVenta = new ImageIcon("img\\delete sale.png").getImage();
			ventana.getBotonDeleteVenta().setIcon(new ImageIcon(imgBotonDeleteVenta.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportVenta()) {
			Image imgBotonExportVenta = new ImageIcon("img\\export to file.png").getImage();
			ventana.getBotonExportVenta().setIcon(new ImageIcon(imgBotonExportVenta.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportVenFinal()) {
			Image imgBotonExportVenFinal = new ImageIcon("img\\export to file.png").getImage();
			ventana.getBotonExportVenFinal().setIcon(new ImageIcon(imgBotonExportVenFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonExportStock()) {
			Image imgBotonExportStock = new ImageIcon("img\\export to file.png").getImage();
			ventana.getBotonExportStock().setIcon(new ImageIcon(imgBotonExportStock.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonExportAlmFinal()) {
			Image imgBotonExportAlmFinal = new ImageIcon("img\\export to file.png").getImage();
			ventana.getBotonExportAlmFinal().setIcon(new ImageIcon(imgBotonExportAlmFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonVerificarCompra()) {
			Image imgBotonVerificarCompra = new ImageIcon("img\\check.png").getImage();
			ventana.getBotonVerificarCompra().setIcon(new ImageIcon(imgBotonVerificarCompra.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonInsertCompraFinal()) {
			Image imgBotonInsertComprafinal = new ImageIcon("img\\purchases.png").getImage();
			ventana.getBotonInsertCompraFinal().setIcon(new ImageIcon(imgBotonInsertComprafinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonCheckDeliNoteCom()) {
			Image imgBotonCheckDeliNoteCom = new ImageIcon("img\\check.png").getImage();
			ventana.getBotonCheckDeliNoteCom().setIcon(new ImageIcon(imgBotonCheckDeliNoteCom.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonCheckDeliNoteComDelete()) {
			Image imgBotonCheckDeliNoteComDelete = new ImageIcon("img\\check.png").getImage();
			ventana.getBotonCheckDeliNoteComDelete().setIcon(new ImageIcon(imgBotonCheckDeliNoteComDelete.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonDeleteAlabaran()) {
			Image imgBotonDeleteAlabaran = new ImageIcon("img\\delete purchase.png").getImage();
			ventana.getBotonDeleteAlabaran().setIcon(new ImageIcon(imgBotonDeleteAlabaran.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonBillsVentas()) {
			Image imgBotonBillsVentas = new ImageIcon("img\\bills.png").getImage();
			ventana.getBotonBillsVentas().setIcon(new ImageIcon(imgBotonBillsVentas.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonCheckBill()) {
			Image imgBotonCheckBill = new ImageIcon("img\\check.png").getImage();
			ventana.getBotonCheckBill().setIcon(new ImageIcon(imgBotonCheckBill.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonDeleteLineaAlbaran()) {
			Image imgBotonDeletLineAl= new ImageIcon("img\\delete line.png").getImage();
			ventana.getBotonDeleteLineaAlbaran().setIcon(new ImageIcon(imgBotonDeletLineAl.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonDeleteLineaFactura()) {
			Image imgBotonDeletLineAl= new ImageIcon("img\\delete line.png").getImage();
			ventana.getBotonDeleteLineaFactura().setIcon(new ImageIcon(imgBotonDeletLineAl.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonVerificarVenta()) {
			Image imgBotonVerifVenta = new ImageIcon("img\\check.png").getImage();
			ventana.getBotonVerificarVenta().setIcon(new ImageIcon(imgBotonVerifVenta.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonInsertVentaFinal()) {
			Image imgBotonInVentafinal = new ImageIcon("img\\sales.png").getImage();
			ventana.getBotonInsertVentaFinal().setIcon(new ImageIcon(imgBotonInVentafinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonCheckBillVenDelete()) {
			Image imgBotonVerifVenDelete = new ImageIcon("img\\check.png").getImage();
			ventana.getBotonCheckBillVenDelete().setIcon(new ImageIcon(imgBotonVerifVenDelete.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonDeleteFactura()) {
			Image imgBotonDeleteFact = new ImageIcon("img\\delete sale.png").getImage();
			ventana.getBotonDeleteFactura().setIcon(new ImageIcon(imgBotonDeleteFact.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==ventana.getBotonLogin()) {			
			 ventana.getBotonLogin().setIcon(new ImageIcon("imagenes/login press.png"));	
			 ventana.getBotonLogin().setContentAreaFilled(false);			
		}
		else if (e.getSource()==ventana.getBotonExit()) {			
			ventana.getBotonExit().setIcon(new ImageIcon("imagenes/exit login press.png"));
			ventana.getBotonExit().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonPurchases()) {			
			ventana.getBotonPurchases().setIcon(new ImageIcon("img/purchases press.png"));
			ventana.getBotonPurchases().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonSales()) {
			ventana.getBotonSales().setIcon(new ImageIcon("img/sales press.png"));			
			ventana.getBotonSales().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonSuppliers()) {
			ventana.getBotonSuppliers().setIcon(new ImageIcon("img/suppliers press.png"));			
			ventana.getBotonSuppliers().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonCustomers()) {
			ventana.getBotonCustomers().setIcon(new ImageIcon("img/customers press.png"));			
			ventana.getBotonCustomers().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonCRM()) {
			ventana.getBotonCRM().setIcon(new ImageIcon("img/crm press.png"));			
			ventana.getBotonCRM().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonStock()) {
			ventana.getBotonStock().setIcon(new ImageIcon("img/stock press.png"));			
			ventana.getBotonStock().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonHR()) {
			ventana.getBotonHR().setIcon(new ImageIcon("img/human resources press.png"));			
			ventana.getBotonHR().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonUser()) {
			ventana.getBotonUser().setIcon(new ImageIcon("img/boton_user press.png"));			
			ventana.getBotonUser().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonLogout()) {
			ventana.getBotonLogout().setIcon(new ImageIcon("img/logout press.png"));			
			ventana.getBotonLogout().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonExitInit()) {
			ventana.getBotonExitInit().setIcon(new ImageIcon("img/exit press.png"));			
			ventana.getBotonExitInit().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonInsertEmpFinal()) {
			Image imgBotonInsertFinal = new ImageIcon("img\\insert press.png").getImage();
			ventana.getBotonInsertEmpFinal().setIcon(new ImageIcon(imgBotonInsertFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonInsertEmpFinal().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonActualizarEmpleado()) {
			Image imgBotonUpdateEmpleado = new ImageIcon("img\\update employee  press.png").getImage();
			ventana.getBotonActualizarEmpleado().setIcon(new ImageIcon(imgBotonUpdateEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonActualizarEmpleado().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonBorrarEmpleado()) {
			Image imgBotonDeleteEmpleado = new ImageIcon("img\\delete employee press.png").getImage();
			ventana.getBotonBorrarEmpleado().setIcon(new ImageIcon(imgBotonDeleteEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonBorrarEmpleado().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonExpEmplFichero()) {
			Image imgBotonExportEmpleado = new ImageIcon("img\\export to file press.png").getImage();
			ventana.getBotonExpEmplFichero().setIcon(new ImageIcon(imgBotonExportEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonExpEmplFichero().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonUpdateEmpFinal()) {
			Image imgBotonUpdateFinal = new ImageIcon("img\\update press.png").getImage();
			ventana.getBotonUpdateEmpFinal().setIcon(new ImageIcon(imgBotonUpdateFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonUpdateEmpFinal().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonSearchEmp()) {
			Image imgBotonSearchEmp = new ImageIcon("img\\search press.png").getImage();
			ventana.getBotonSearchEmp().setIcon(new ImageIcon(imgBotonSearchEmp.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonSearchEmp().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonDeleteEmpFinal()) {
			Image imgBotonDeleteEmpFinal = new ImageIcon("img\\delete press.png").getImage();
			ventana.getBotonDeleteEmpFinal().setIcon(new ImageIcon(imgBotonDeleteEmpFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonDeleteEmpFinal().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonExportEmpFinal()) {
			Image imgBotonExportEmpleado = new ImageIcon("img\\export to file press.png").getImage();
			ventana.getBotonExportEmpFinal().setIcon(new ImageIcon(imgBotonExportEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonExportEmpFinal().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonActualizarProveedor()) {
			Image imgBotonUpdateProveedor = new ImageIcon("img\\update supplier press.png").getImage();
			ventana.getBotonActualizarProveedor().setIcon(new ImageIcon(imgBotonUpdateProveedor.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonActualizarProveedor().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonBorrarProveedor()) {
			Image imgBotonBorrarProveedor = new ImageIcon("img\\delete supplier press.png").getImage();
			ventana.getBotonBorrarProveedor().setIcon(new ImageIcon(imgBotonBorrarProveedor.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonBorrarProveedor().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonDeliveryNotes()) {
			Image imgBotonDeliveryNotes = new ImageIcon("img\\delivery notes press.png").getImage();
			ventana.getBotonDeliveryNotes().setIcon(new ImageIcon(imgBotonDeliveryNotes.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonDeliveryNotes().setContentAreaFilled(false);
		}	
		else if (e.getSource()==ventana.getBotonInsertProvOk()) {
			Image imgBotonInsertProvOk = new ImageIcon("img\\insert press.png").getImage();
			ventana.getBotonInsertProvOk().setIcon(new ImageIcon(imgBotonInsertProvOk.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonInsertProvOk().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonUpdateFinalPr()) {
			Image imgBotonUpdateFinalPr = new ImageIcon("img\\update press.png").getImage();
			ventana.getBotonUpdateFinalPr().setIcon(new ImageIcon(imgBotonUpdateFinalPr.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonUpdateFinalPr().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonSearchProv()) {
			Image imgBotonSearchProv = new ImageIcon("img\\search press.png").getImage();
			ventana.getBotonSearchProv().setIcon(new ImageIcon(imgBotonSearchProv.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonSearchProv().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonDeleteProvFinal()) {
			Image imgBotonDeleteProvFinal = new ImageIcon("img\\delete press.png").getImage();
			ventana.getBotonDeleteProvFinal().setIcon(new ImageIcon(imgBotonDeleteProvFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonDeleteProvFinal().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonInsertClienteok()) {
			Image imgBotonInsertClienteok = new ImageIcon("img\\insert customer press.png").getImage();
			ventana.getBotonInsertClienteok().setIcon(new ImageIcon(imgBotonInsertClienteok.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonInsertClienteok().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonActualizarCliente()) {
			Image imgBotonActualizarCliente = new ImageIcon("img\\update customer press.png").getImage();
			ventana.getBotonActualizarCliente().setIcon(new ImageIcon(imgBotonActualizarCliente.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonActualizarCliente().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonBorrarCliente()) {
			Image imgBotonBorrarCliente = new ImageIcon("img\\delete customer press.png").getImage();
			ventana.getBotonBorrarCliente().setIcon(new ImageIcon(imgBotonBorrarCliente.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonBorrarCliente().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonBills()) {
			Image imgBotonBills = new ImageIcon("img\\bills press.png").getImage();
			ventana.getBotonBills().setIcon(new ImageIcon(imgBotonBills.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonBills().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonUpdateFinalCl()) {
			Image imgBotonUpdateFinalCl = new ImageIcon("img\\update press.png").getImage();
			ventana.getBotonUpdateFinalCl().setIcon(new ImageIcon(imgBotonUpdateFinalCl.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonUpdateFinalCl().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonSearchCliente()) {
			Image imgBotonSearchCliente = new ImageIcon("img\\search press.png").getImage();
			ventana.getBotonSearchCliente().setIcon(new ImageIcon(imgBotonSearchCliente.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonSearchCliente().setContentAreaFilled(false);
		}	
		else if (e.getSource()==ventana.getBotonDeleteClienteFinal()) {
			Image imgBotonDeleteClienteFinal = new ImageIcon("img\\delete press.png").getImage();
			ventana.getBotonDeleteClienteFinal().setIcon(new ImageIcon(imgBotonDeleteClienteFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonDeleteClienteFinal().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonVerificarCompra()) {
			Image imgBotonVerificarCompra = new ImageIcon("img\\check press.png").getImage();
			ventana.getBotonVerificarCompra().setIcon(new ImageIcon(imgBotonVerificarCompra.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonVerificarCompra().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonDeliveryNoteCompra()) {
			Image imgBotonActualCompra = new ImageIcon("img\\delivery notes press.png").getImage();
			ventana.getBotonDeliveryNoteCompra().setIcon(new ImageIcon(imgBotonActualCompra.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonDeliveryNoteCompra().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonDeleteCompra()) {
			Image imgBotonDeleteCompra = new ImageIcon("img\\delete purchase press.png").getImage();
			ventana.getBotonDeleteCompra().setIcon(new ImageIcon(imgBotonDeleteCompra.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonDeleteCompra().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonExportCompra()) {
			Image imgBotonExportCompra = new ImageIcon("img\\export to file press.png").getImage();
			ventana.getBotonExportCompra().setIcon(new ImageIcon(imgBotonExportCompra.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonExportCompra().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonExportComFinal()) {
			Image imgBotonExportComFinal = new ImageIcon("img\\export to file press.png").getImage();
			ventana.getBotonExportComFinal().setIcon(new ImageIcon(imgBotonExportComFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonExportComFinal().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonDeleteVenta()) {
			Image imgBotonDeleteVenta = new ImageIcon("img\\delete sale press.png").getImage();
			ventana.getBotonDeleteVenta().setIcon(new ImageIcon(imgBotonDeleteVenta.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonDeleteVenta().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonExportVenta()) {
			Image imgBotonExportVenta = new ImageIcon("img\\export to file press.png").getImage();
			ventana.getBotonExportVenta().setIcon(new ImageIcon(imgBotonExportVenta.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonExportVenta().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonExportVenFinal()) {
			Image imgBotonExportVenFinal = new ImageIcon("img\\export to file press.png").getImage();
			ventana.getBotonExportVenFinal().setIcon(new ImageIcon(imgBotonExportVenFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonExportVenFinal().setContentAreaFilled(false);
		}
		else if (e.getSource()==ventana.getBotonExportStock()) {
			Image imgBotonExportStock = new ImageIcon("img\\export to file press.png").getImage();
			ventana.getBotonExportStock().setIcon(new ImageIcon(imgBotonExportStock.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonExportStock().setContentAreaFilled(false);
		}		
		else if (e.getSource()==ventana.getBotonExportAlmFinal()) {
			Image imgBotonExportAlmFinal = new ImageIcon("img\\export to file press.png").getImage();
			ventana.getBotonExportAlmFinal().setIcon(new ImageIcon(imgBotonExportAlmFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonExportAlmFinal().setContentAreaFilled(false);
		}
		else if(e.getSource()==ventana.getBotonVerificarCompra()) {
			Image imgBotonVerificarCompra = new ImageIcon("img\\check press.png").getImage();
			ventana.getBotonVerificarCompra().setIcon(new ImageIcon(imgBotonVerificarCompra.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonVerificarCompra().setContentAreaFilled(false);
		}
		else if(e.getSource()==ventana.getBotonInsertCompraFinal()) {
			Image imgBotonInsertComprafinal = new ImageIcon("img\\purchases press.png").getImage();
			ventana.getBotonInsertCompraFinal().setIcon(new ImageIcon(imgBotonInsertComprafinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonInsertCompraFinal().setContentAreaFilled(false);
		}
		else if(e.getSource()==ventana.getBotonCheckDeliNoteCom()) {
			Image imgBotonCheckDeliNoteCom = new ImageIcon("img\\check press.png").getImage();
			ventana.getBotonCheckDeliNoteCom().setIcon(new ImageIcon(imgBotonCheckDeliNoteCom.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonCheckDeliNoteCom().setContentAreaFilled(false);
		}
		else if(e.getSource()==ventana.getBotonCheckDeliNoteComDelete()) {
			Image imgBotonCheckDeliNoteComDelete = new ImageIcon("img\\check press.png").getImage();
			ventana.getBotonCheckDeliNoteComDelete().setIcon(new ImageIcon(imgBotonCheckDeliNoteComDelete.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonCheckDeliNoteComDelete().setContentAreaFilled(false);
		}
		else if(e.getSource()==ventana.getBotonDeleteAlabaran()) {
			Image imgBotonDeleteAlabaran = new ImageIcon("img\\delete purchase press.png").getImage();
			ventana.getBotonDeleteAlabaran().setIcon(new ImageIcon(imgBotonDeleteAlabaran.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonDeleteAlabaran().setContentAreaFilled(false);
		}
		else if(e.getSource()==ventana.getBotonBillsVentas()) {
			Image imgBotonBillsVentas = new ImageIcon("img\\bills press.png").getImage();
			ventana.getBotonBillsVentas().setIcon(new ImageIcon(imgBotonBillsVentas.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
			ventana.getBotonBillsVentas().setContentAreaFilled(false);
		}
		else if(e.getSource()==ventana.getBotonCheckBill()) {
			Image imgBotonCheckBill = new ImageIcon("img\\check press.png").getImage();
			ventana.getBotonCheckBill().setIcon(new ImageIcon(imgBotonCheckBill.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonCheckBill().setContentAreaFilled(false);
		}
		else if(e.getSource()==ventana.getBotonDeleteLineaAlbaran()) {
			Image imgBotonDeletLineAl= new ImageIcon("img\\delete line press.png").getImage();
			ventana.getBotonDeleteLineaAlbaran().setIcon(new ImageIcon(imgBotonDeletLineAl.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonDeleteLineaAlbaran().setContentAreaFilled(false);
		}
		else if(e.getSource()==ventana.getBotonDeleteLineaFactura()) {
			Image imgBotonDeletLineAl= new ImageIcon("img\\delete line press.png").getImage();
			ventana.getBotonDeleteLineaFactura().setIcon(new ImageIcon(imgBotonDeletLineAl.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonDeleteLineaFactura().setContentAreaFilled(false);
		}
		else if(e.getSource()==ventana.getBotonVerificarVenta()) {
			Image imgBotonVerifVenta = new ImageIcon("img\\check press.png").getImage();
			ventana.getBotonVerificarVenta().setIcon(new ImageIcon(imgBotonVerifVenta.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonVerificarVenta().setContentAreaFilled(false);
		}
		else if(e.getSource()==ventana.getBotonInsertVentaFinal()) {
			Image imgBotonInVentafinal = new ImageIcon("img\\sales press.png").getImage();
			ventana.getBotonInsertVentaFinal().setIcon(new ImageIcon(imgBotonInVentafinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonInsertVentaFinal().setContentAreaFilled(false);
		}
		else if(e.getSource()==ventana.getBotonCheckBillVenDelete()) {
			Image imgBotonVerifVenDelete = new ImageIcon("img\\check press.png").getImage();
			ventana.getBotonCheckBillVenDelete().setIcon(new ImageIcon(imgBotonVerifVenDelete.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonCheckBillVenDelete().setContentAreaFilled(false);
		}
		else if(e.getSource()==ventana.getBotonDeleteFactura()) {
			Image imgBotonDeleteFact = new ImageIcon("img\\delete sale press.png").getImage();
			ventana.getBotonDeleteFactura().setIcon(new ImageIcon(imgBotonDeleteFact.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
			ventana.getBotonDeleteFactura().setContentAreaFilled(false);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource()==ventana.getBotonLogin()) {
			 ventana.getBotonLogin().setIcon(new ImageIcon("imagenes/login.png"));
		}
		else if (e.getSource()==ventana.getBotonExit()) {
			ventana.getBotonExit().setIcon(new ImageIcon("imagenes/exit_login.png"));
		}		
		else if (e.getSource()==ventana.getBotonPurchases()) {
			ventana.getBotonPurchases().setIcon(new ImageIcon("img/purchases.png"));
		}		
		else if (e.getSource()==ventana.getBotonSales()) {
			ventana.getBotonSales().setIcon(new ImageIcon("img/sales.png"));
		}		
		else if (e.getSource()==ventana.getBotonSuppliers()) {
			ventana.getBotonSuppliers().setIcon(new ImageIcon("img/suppliers.png"));
		}		
		else if (e.getSource()==ventana.getBotonCustomers()) {
			ventana.getBotonCustomers().setIcon(new ImageIcon("img/customers.png"));
		}		
		else if (e.getSource()==ventana.getBotonCRM()) {
			ventana.getBotonCRM().setIcon(new ImageIcon("img/crm.png"));
		}		
		else if (e.getSource()==ventana.getBotonStock()) {
			ventana.getBotonStock().setIcon(new ImageIcon("img/stock.png"));
		}		
		else if (e.getSource()==ventana.getBotonHR()) {
			ventana.getBotonHR().setIcon(new ImageIcon("img/human resources.png"));
		}		
		else if (e.getSource()==ventana.getBotonUser()) {
			ventana.getBotonUser().setIcon(new ImageIcon("img/boton_user.png"));
		}		
		else if (e.getSource()==ventana.getBotonLogout()) {
			ventana.getBotonLogout().setIcon(new ImageIcon("img/logout.png"));
		}		
		else if (e.getSource()==ventana.getBotonExitInit()) {
			ventana.getBotonExitInit().setIcon(new ImageIcon("img/exit.png"));
		}		
		else if (e.getSource()==ventana.getBotonInsertEmpFinal()) {
			Image imgBotonInsertFinal = new ImageIcon("img\\insert.png").getImage();
			ventana.getBotonInsertEmpFinal().setIcon(new ImageIcon(imgBotonInsertFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonActualizarEmpleado()) {
			Image imgBotonUpdateEmpleado = new ImageIcon("img\\update employee.png").getImage();
			ventana.getBotonActualizarEmpleado().setIcon(new ImageIcon(imgBotonUpdateEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonBorrarEmpleado()) {
			Image imgBotonDeleteEmpleado = new ImageIcon("img\\delete employee.png").getImage();
			ventana.getBotonBorrarEmpleado().setIcon(new ImageIcon(imgBotonDeleteEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExpEmplFichero()) {
			Image imgBotonExportEmpleado = new ImageIcon("img\\export to file.png").getImage();
			ventana.getBotonExpEmplFichero().setIcon(new ImageIcon(imgBotonExportEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonUpdateEmpFinal()) {
			Image imgBotonUpdateFinal = new ImageIcon("img\\update.png").getImage();
			ventana.getBotonUpdateEmpFinal().setIcon(new ImageIcon(imgBotonUpdateFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonSearchEmp()) {
			Image imgBotonSearchEmp = new ImageIcon("img\\search.png").getImage();
			ventana.getBotonSearchEmp().setIcon(new ImageIcon(imgBotonSearchEmp.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonDeleteEmpFinal()) {
			Image imgBotonDeleteEmpFinal = new ImageIcon("img\\delete.png").getImage();
			ventana.getBotonDeleteEmpFinal().setIcon(new ImageIcon(imgBotonDeleteEmpFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportEmpFinal()) {
			Image imgBotonExportEmpleado = new ImageIcon("img\\export to file.png").getImage();
			ventana.getBotonExportEmpFinal().setIcon(new ImageIcon(imgBotonExportEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonActualizarProveedor()) {
			Image imgBotonUpdateProveedor = new ImageIcon("img\\update supplier.png").getImage();
			ventana.getBotonActualizarProveedor().setIcon(new ImageIcon(imgBotonUpdateProveedor.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonBorrarProveedor()) {
			Image imgBotonBorrarProveedor = new ImageIcon("img\\delete supplier.png").getImage();
			ventana.getBotonBorrarProveedor().setIcon(new ImageIcon(imgBotonBorrarProveedor.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonDeliveryNotes()) {
			Image imgBotonDeliveryNotes = new ImageIcon("img\\delivery notes.png").getImage();
			ventana.getBotonDeliveryNotes().setIcon(new ImageIcon(imgBotonDeliveryNotes.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}	
		else if (e.getSource()==ventana.getBotonInsertProvOk()) {
			Image imgBotonInsertProvOk = new ImageIcon("img\\insert.png").getImage();
			ventana.getBotonInsertProvOk().setIcon(new ImageIcon(imgBotonInsertProvOk.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonUpdateFinalPr()) {
			Image imgBotonUpdateFinalPr = new ImageIcon("img\\update.png").getImage();
			ventana.getBotonUpdateFinalPr().setIcon(new ImageIcon(imgBotonUpdateFinalPr.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonSearchProv()) {
			Image imgBotonSearchProv = new ImageIcon("img\\search.png").getImage();
			ventana.getBotonSearchProv().setIcon(new ImageIcon(imgBotonSearchProv.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonDeleteProvFinal()) {
			Image imgBotonDeleteProvFinal = new ImageIcon("img\\delete.png").getImage();
			ventana.getBotonDeleteProvFinal().setIcon(new ImageIcon(imgBotonDeleteProvFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonInsertClienteok()) {
			Image imgBotonInsertClienteok = new ImageIcon("img\\insert customer.png").getImage();
			ventana.getBotonInsertClienteok().setIcon(new ImageIcon(imgBotonInsertClienteok.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonActualizarCliente()) {
			Image imgBotonActualizarCliente = new ImageIcon("img\\update customer.png").getImage();
			ventana.getBotonActualizarCliente().setIcon(new ImageIcon(imgBotonActualizarCliente.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonBorrarCliente()) {
			Image imgBotonBorrarCliente = new ImageIcon("img\\delete customer.png").getImage();
			ventana.getBotonBorrarCliente().setIcon(new ImageIcon(imgBotonBorrarCliente.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonBills()) {
			Image imgBotonBills = new ImageIcon("img\\bills.png").getImage();
			ventana.getBotonBills().setIcon(new ImageIcon(imgBotonBills.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonUpdateFinalCl()) {
			Image imgBotonUpdateFinalCl = new ImageIcon("img\\update.png").getImage();
			ventana.getBotonUpdateFinalCl().setIcon(new ImageIcon(imgBotonUpdateFinalCl.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonSearchCliente()) {
			Image imgBotonSearchCliente = new ImageIcon("img\\search.png").getImage();
			ventana.getBotonSearchCliente().setIcon(new ImageIcon(imgBotonSearchCliente.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}	
		else if (e.getSource()==ventana.getBotonDeleteClienteFinal()) {
			Image imgBotonDeleteClienteFinal = new ImageIcon("img\\delete.png").getImage();
			ventana.getBotonDeleteClienteFinal().setIcon(new ImageIcon(imgBotonDeleteClienteFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonDeliveryNoteCompra()) {
			Image imgBotonActualCompra = new ImageIcon("img\\delivery notes.png").getImage();
			ventana.getBotonDeliveryNoteCompra().setIcon(new ImageIcon(imgBotonActualCompra.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonDeleteCompra()) {
			Image imgBotonDeleteCompra = new ImageIcon("img\\delete purchase.png").getImage();
			ventana.getBotonDeleteCompra().setIcon(new ImageIcon(imgBotonDeleteCompra.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportCompra()) {
			Image imgBotonExportCompra = new ImageIcon("img\\export to file.png").getImage();
			ventana.getBotonExportCompra().setIcon(new ImageIcon(imgBotonExportCompra.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportComFinal()) {
			Image imgBotonExportComFinal = new ImageIcon("img\\export to file.png").getImage();
			ventana.getBotonExportComFinal().setIcon(new ImageIcon(imgBotonExportComFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonDeleteVenta()) {
			Image imgBotonDeleteVenta = new ImageIcon("img\\delete sale.png").getImage();
			ventana.getBotonDeleteVenta().setIcon(new ImageIcon(imgBotonDeleteVenta.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportVenta()) {
			Image imgBotonExportVenta = new ImageIcon("img\\export to file.png").getImage();
			ventana.getBotonExportVenta().setIcon(new ImageIcon(imgBotonExportVenta.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportVenFinal()) {
			Image imgBotonExportVenFinal = new ImageIcon("img\\export to file.png").getImage();
			ventana.getBotonExportVenFinal().setIcon(new ImageIcon(imgBotonExportVenFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if (e.getSource()==ventana.getBotonExportStock()) {
			Image imgBotonExportStock = new ImageIcon("img\\export to file.png").getImage();
			ventana.getBotonExportStock().setIcon(new ImageIcon(imgBotonExportStock.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}		
		else if (e.getSource()==ventana.getBotonExportAlmFinal()) {
			Image imgBotonExportAlmFinal = new ImageIcon("img\\export to file.png").getImage();
			ventana.getBotonExportAlmFinal().setIcon(new ImageIcon(imgBotonExportAlmFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonVerificarCompra()) {
			Image imgBotonVerificarCompra = new ImageIcon("img\\check.png").getImage();
			ventana.getBotonVerificarCompra().setIcon(new ImageIcon(imgBotonVerificarCompra.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonInsertCompraFinal()) {
			Image imgBotonInsertComprafinal = new ImageIcon("img\\purchases.png").getImage();
			ventana.getBotonInsertCompraFinal().setIcon(new ImageIcon(imgBotonInsertComprafinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonCheckDeliNoteCom()) {
			Image imgBotonCheckDeliNoteCom = new ImageIcon("img\\check.png").getImage();
			ventana.getBotonCheckDeliNoteCom().setIcon(new ImageIcon(imgBotonCheckDeliNoteCom.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonCheckDeliNoteComDelete()) {
			Image imgBotonCheckDeliNoteComDelete = new ImageIcon("img\\check.png").getImage();
			ventana.getBotonCheckDeliNoteComDelete().setIcon(new ImageIcon(imgBotonCheckDeliNoteComDelete.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonDeleteAlabaran()) {
			Image imgBotonDeleteAlabaran = new ImageIcon("img\\delete purchase.png").getImage();
			ventana.getBotonDeleteAlabaran().setIcon(new ImageIcon(imgBotonDeleteAlabaran.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}		
		else if(e.getSource()==ventana.getBotonBillsVentas()) {
			Image imgBotonBillsVentas = new ImageIcon("img\\bills.png").getImage();
			ventana.getBotonBillsVentas().setIcon(new ImageIcon(imgBotonBillsVentas.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonCheckBill()) {
			Image imgBotonCheckBill = new ImageIcon("img\\check.png").getImage();
			ventana.getBotonCheckBill().setIcon(new ImageIcon(imgBotonCheckBill.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonDeleteLineaAlbaran()) {
			Image imgBotonDeletLineAl= new ImageIcon("img\\delete line.png").getImage();
			ventana.getBotonDeleteLineaAlbaran().setIcon(new ImageIcon(imgBotonDeletLineAl.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonDeleteLineaFactura()) {
			Image imgBotonDeletLineAl= new ImageIcon("img\\delete line.png").getImage();
			ventana.getBotonDeleteLineaFactura().setIcon(new ImageIcon(imgBotonDeletLineAl.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonVerificarVenta()) {
			Image imgBotonVerifVenta = new ImageIcon("img\\check.png").getImage();
			ventana.getBotonVerificarVenta().setIcon(new ImageIcon(imgBotonVerifVenta.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonInsertVentaFinal()) {
			Image imgBotonInVentafinal = new ImageIcon("img\\sales.png").getImage();
			ventana.getBotonInsertVentaFinal().setIcon(new ImageIcon(imgBotonInVentafinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonCheckBillVenDelete()) {
			Image imgBotonVerifVenDelete = new ImageIcon("img\\check.png").getImage();
			ventana.getBotonCheckBillVenDelete().setIcon(new ImageIcon(imgBotonVerifVenDelete.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		else if(e.getSource()==ventana.getBotonDeleteFactura()) {
			Image imgBotonDeleteFact = new ImageIcon("img\\delete sale.png").getImage();
			ventana.getBotonDeleteFactura().setIcon(new ImageIcon(imgBotonDeleteFact.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		}
		
	}	
}


