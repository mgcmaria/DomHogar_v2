package vista;

import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import controlador.AccesoDB;
import controlador.Eventos;
import metodos.ColorearFilas;
import tablas.Cliente;
import tablas.Producto;
import tablas.Proveedor;
import tablas.Servicio;

public class Ventana extends JFrame{
	
	Connection conexion = AccesoDB.conexion();	
	
	private static final long serialVersionUID = 1L;
	
	//DECLARACION DE COMPONENTES - ATRIBUTOS DE LA CLASE (PRIVADAS)	
	//Atributos de pantalla LOGIN y paneles de INICIO
	private JLabel etiquetaUser, etiquetaPass, etiquetaResulLogin, imagenInicio, imagenLogin, imageLogo;
	private JTextField cajaUser;
	private JPasswordField cajaPass;
	private JButton botonLogin, botonExit, botonPurchases, botonSales, botonSuppliers, botonCustomers, botonCRM, botonStock,
		botonHR, botonUser, botonLogout, botonExitInit;
	private JPanel panelIzq, panelDer;	
	
	//Atributos de RECURSOS / EMPLEADOS
	private JPanel panelEmpleado, subPanelEmpInsertar, subPanelBotonesEmp, subPanelEmpUpdate, subPanelEmpDelete, subPanelEmpExport;
	private JScrollPane barraEmpleados;
	private JTable tablaEmpleados;
	private JButton botonActualizarEmpleado, botonBorrarEmpleado, botonExpEmplFichero, botonInsertEmpFinal, botonUpdateEmpFinal, botonSearchEmp,
		botonDeleteEmpFinal, botonExportEmpFinal;
	private JLabel nuevoEmpleado, updateEmpleado, deleteEmpleado, labelPreguntaCambioEmp, labelNewDataEmp, exportEmpleado;
	private JTextField insertNomEmpl, insertApelEmpl, insertNIFEmp, insertPhoneEmp, insertEmailEmp, insertUserEmp, 
		insertPassEmp, insertPerfilEmp, insertNIFUpdateEmp, insertNewDataEmp, insertNIFDeleteEmp, insertUsuarioPCEmpleado;
	private JLabel resulInsertEmp, resultUpdateEmp, resulBusquedaEmp, resulDeleteEmp, resulExportEmp, insertRutaExportEmp;
	private JComboBox <String> comboUpdateEmp;
	
	//Atributos de COMPRAS
	private JPanel panelCompras, subPanelInsertCompras, subPanelBotonesCompras, subPanelComprasExport, subPanelDeliveryNoteCompras, subPanelComprasDelete;
	private JButton botonDeliveryNoteCompra, botonDeleteCompra, botonExportCompra, botonVerificarCompra, botonInsertCompraFinal,
		botonExportComFinal, botonCheckDeliNoteCom, botonCheckDeliNoteComDelete, botonDeleteLineaAlbaran, botonDeleteAlabaran;
	private JScrollPane barraCompras;
	private JTable tablaCompras;
	private JLabel JLabelNuevaCompra, JLresulComboProCompra, JLresulimporCompraPro, JLresulimporTotalPro,
		JLresulComboProveedorCompra, JLresulinsertComprafinal, exportCompras, insertRutaExportCompras, resulExportCom, JLDeliveryNoteCompras,
		JLslectorJTableCom, JLnumAlbaranCom, JLCustomerDomCom, JLDomohogarCom, JLDateCom, JLDateComRow, JLSuplierCom, JLSuplierComRow,
		JLSuplierCode, JLSuplierCodeRow, JLTotalAccountCom, JLTotalAccountComSuma, JLselecJTableComDelete, JLDeliveryNoteDeleteCom, JLnumAlbaranDeleteCom,
		JLselecJTableComDeleteLineAlbaran, JLselecJTableComDeleteAlbaran;
	private JTextField JTFnumAlbaran, JTFcantidadCompra, insertUsuarioPCCompras;
	private JComboBox <String> comboProductoCompras, comboProveedorCompras;
	
	//Atributos de VENTAS
	private JPanel panelVentas, subPanelInsertVentas, subPanelBotonesVentas, subPanelVentasExport, subPanelBills, subPanelVentasDelete;
	private JScrollPane barraVentas;
	private JTable tablaVentas;
	private JLabel JLabelNuevaVenta, JLresulComboSerVenta, JLresulimporVentaServ, JLresulimporTotalServ,
		JLresulComboClienteVentas, JLresulinsertVentafinal, exportVentas, insertRutaexportVentas, resulExportVen, JLslectorJTableBills,
		JLBills, JLnumBill, JLCustomer, JLCustomerRow, JLDate, JLDateRow, JLCustomerName, JLCustomerNameRow, JLTotalAmount, JLTotalAmountSum, 
		JLselecJTableVenDelete, JLBillDeleteVen, JLnumFacturaDeleteVen, JLselecJTableVenDeleteLineFactura, JLselecJTableVenDeleteFactura;
	private JTextField JTFnumFactura, JTFcantidadVenta, insertUsuarioPCVentas;
	private JComboBox<String> comboServicioVentas, comboClienteVentas;
	private JButton botonVerificarVenta, botonInsertVentaFinal, botonBillsVentas, botonDeleteVenta, botonExportVenta, botonExportVenFinal, 
		botonCheckBill, botonCheckBillVenDelete, botonDeleteLineaFactura, botonDeleteFactura;
	
	//Atributos de SERVICIOS --> CRM
	private JPanel panelCRM;
	private JScrollPane barraCRM;
	private JTable tablaCRM;
	
	//Atributos de PROVEEDORES
	private JPanel panelProveedores, subPanelInsProv, subPanelEditProv, subPanelElimProv, panelBotonesProv, subPanelProvExport ;
	private JTextField insertCodProv, insertNomProv, insertContProv, insertCODUpdateProv, insertNewDataProv, insertCODDeleteProv,
	insertUsuarioPCProveedor;
	private JScrollPane barraProveedores;
	private JTable tablaProveedores;
	private JButton botonInsertProveedor, botonActualizarProveedor, botonBorrarProveedor, botonInsertProvOk, 
	botonExportProveedor, botonUpdateEmpFinalPr, botonSearchProv, botonDeleteProvFinal, botonUpdateFinalPr,
	botonExportProvFinal;
	private JLabel nuevoProv, resulInsertProv, editProv, elimProv, labelPreguntaCambioProv, labelNewDataProv,
		resultUpdateProv, resulBusquedaProv, resulDeleteProv, exportProveedor, insertRutaExportProv, resulExportProv ;
	private JComboBox <String>comboUpdateProv;
	
	//Atributos de ALMACEN
	private JPanel panelAlmacen, subPanelAlmacenExport;
	private JScrollPane barraStock, barraStock2;
	private JButton botonExportStock, botonExportAlmFinal;
	private JTable tablaAlmacen, tablaAlmacen2;
	private JLabel detalleAlmacen, resumenAlmacen, exportResAlm, resulExportAlm, insertRutaExportAlm;
	private JTextField insertUsuarioPCAlmacen;
	
	//Atributos de CLIENTES
	private JPanel panelClientes, subPanelInsCliente, panelBotonesCliente, subPanelEditCliente, subPanelElimCliente;
	private JScrollPane barraClientes;
	private JTable tablaClientes;
	private JLabel nuevoCliente, resulInsertCliente, editCliente, labelPreguntaCambioCliente, labelNewDataCliente,
		resultUpdateCliente, elimCliente, resulBusquedaCli, resulDeleteCliente;
	private JTextField insertNIFCliente, insertNomCliente, insertTelCliente, insertNIFUpdateCliente, insertNIFDeleteCli,
		insertNewDataCliente, insertMailCliente;
	private JButton botonInsertClienteok, botonActualizarCliente, botonBorrarCliente, botonUpdateFinalCl,
		botonSearchCliente, botonDeleteClienteFinal, botonBills;
	private JComboBox<String> comboUpdateCliente;
	
	//Atributos de NOMINA
	private JPanel panelNomina;
	private JLabel JLUsuarioNomina, JLTextoConsulNom, JLDatosTrabajador, JLDatosEmpresa, JLDomHogarNom, JLCIFNom, 
		JLDomiEmpresaNom, JLDNIEmpNom, JLresultDNIEmpNomima, JLSSEmpNom, JLresulSSEmpNomina, JLCatEmpNom, JLRESULCatEmpNomina, 
		JLGrupoCotEmpNom, JLresulGrupoCotEmpNomina, JLresulIRPF, JLIRPF, JLDevengosTitulo, JLSalarioBase, JLresulSalarioBase, 
		JLHorasExtras, JLresulHorasExtras, JLDietas, JLresulDietas, JLTotaldevengado, JLresulTotalDevengado,
		JLRetencionesTitulo, JLFormacion, JLresulFormacion, JLDesempleo, JLresulDesempleo, JLConComunes, JLresulConComunes,
		JLTotalDeducir, JLresulTotalDeducir;
	private JComboBox<String> comboAnnoNomina, comboMesNomina;
	private JButton botonCheckNom;
	private JTextField tiraDatosTrabajador, tiraDatosEmpresa, tiraPeriodo;
	
	//COLORES
	Color color_blanco = Color.WHITE;
	Color color_azul = new Color(0,157,233);
	Color color_panel = new Color(202,233,255);
	
	//CONSTRUCTOR
	public Ventana() {
		setSize(400,520); //Tamano de la Ventana
		setLocationRelativeTo(null); //Eliminamos la autolocalizaciÃ³n
		setTitle("ERP DOMHOGAR"); //Titulo
		setLayout(null); // Lo colocamos nosotros
		setResizable(false); //Desactivamos botï¿½n maximizar
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes\\logo sin fondo.png")); //Imagen de la App
		setDefaultCloseOperation(EXIT_ON_CLOSE); //Para programa cuendo cerramos
		incializarComponentes(); //Metodo que inicializa los componentes
		setVisible(true); //Visible
	}

	//FUNCION QUE INICIALIZA LOS COMPONENTES
	private void incializarComponentes() {
		
		getContentPane().setBackground(new Color(255,255,255)); //Damos un color de fondo 
				
		//PANTALLA LOGIN
		
		Image imgLogin = new ImageIcon("imagenes\\logo1.PNG").getImage();
		imagenLogin = new JLabel(new ImageIcon(imgLogin.getScaledInstance(265, 200, Image.SCALE_SMOOTH)));
		//las coordenadas del final han de coincidir con las anteriores
		imagenLogin.setBounds(60, 10, 265, 200);
		add(imagenLogin);
				
		//Etiqueta Usuario
		etiquetaUser = new JLabel("user");//Creamos el componente
		etiquetaUser.setBounds(50,240,80,25);//Posicionamos
		etiquetaUser.setFont(new Font("Segoe UI",Font.PLAIN,25));//Damos formato al contenido
		etiquetaUser.setForeground(new Color(0,157,233));;//Color del texto
		add(etiquetaUser);//Anadimos
				
		//Etiqueta Password
		etiquetaPass = new JLabel("password");//Creamos el componente
		etiquetaPass.setBounds(50,290,120,25);
		etiquetaPass.setFont(new Font("Segoe UI",Font.PLAIN,25));//Damos formato al contenido
		etiquetaPass.setForeground(new Color(0,157,233));//Color del texto
		add(etiquetaPass);//Anadimos
				
		//Etiqueta Resultado del Login
		etiquetaResulLogin = new JLabel("");//Creamos el componente
		etiquetaResulLogin.setBounds(70,420,250,25);
		etiquetaResulLogin.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		etiquetaResulLogin.setForeground(Color.DARK_GRAY);//Color del texto
		add(etiquetaResulLogin);//Anadimos
				
		cajaUser = new JTextField();//Creamos el componente
		cajaUser.setBounds(180,240,150,30);//Posicionamos
		cajaUser.setBorder(null); //Eliminamos el borde
		cajaUser.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		cajaUser.setBackground(new Color(0,157,233)); //Color de fondo
		cajaUser.setForeground(new Color(255,255,255));//Color del texto
		cajaUser.setHorizontalAlignment(JTextField.CENTER); //Centramos en la caja
		add(cajaUser);//Anadimos
				
		cajaPass = new JPasswordField();//Creamos el componente
		cajaPass.setBounds(180,290,150,30);//Posicionamos
		cajaPass.setBorder(null); //Eliminamos el borde
		cajaPass.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		cajaPass.setBackground(color_azul); //Color de fondo
		cajaPass.setForeground(new Color(255,255,255));//Color del texto
		cajaPass.setHorizontalAlignment(JTextField.CENTER); //Centramos en la caja
		add(cajaPass);//Anadimos
				
		botonLogin = new JButton("");//Creamos el componente
		botonLogin.setBounds(50,360,136,42);
		botonLogin.setBorder(null); //Eliminamos el borde
		botonLogin.setIcon(new ImageIcon("imagenes/login.png"));
		botonLogin.setBackground(color_blanco);
		add(botonLogin);//Anadimos 
				
		botonExit = new JButton("");//Creamos el componente
		botonExit.setBounds(200,360,136,42);
		botonExit.setIcon(new ImageIcon("imagenes/exit_login.png"));
		botonExit.setBorder(new MatteBorder(null));
		botonExit.setBackground(color_blanco);
		add(botonExit);//Anadimos					
		
		//PAGINA PRINCIPAL
		
		Image img = new ImageIcon("img/imagen_inicio.jpg").getImage();		
		imagenInicio = new JLabel(new ImageIcon(img.getScaledInstance(750, 580, Image.SCALE_SMOOTH)));
		imagenInicio.setBounds(200, 40, 750, 580);
		add(imagenInicio);
		imagenInicio.setVisible(false);
		
		panelIzq = new JPanel();
		panelIzq.setBackground(color_blanco);
		panelIzq.setBounds(0, 0, 200, 700);
		panelIzq.setLayout(null);
		add(panelIzq);
		panelIzq.setVisible(false);
		
		panelDer = new JPanel();
		panelDer.setBackground(color_blanco);
		panelDer.setBounds(980, 0, 200, 700);
		panelDer.setLayout(null);
		add(panelDer);
		panelDer.setVisible(false);
		
		//Botones panel izquierdo
		
		Image imgBotonPurchases = new ImageIcon("img\\purchases.png").getImage();
		botonPurchases = new JButton(new ImageIcon(imgBotonPurchases.getScaledInstance(125,80, Image.SCALE_SMOOTH)));
		botonPurchases.setBounds(30,40,125,80);
		botonPurchases.setBackground(color_blanco);
		botonPurchases.setBorder(null);
		panelIzq.add(botonPurchases);
		
		Image imgBotonSales = new ImageIcon("img\\sales.png").getImage();
		botonSales = new JButton(new ImageIcon(imgBotonSales.getScaledInstance(125,80, Image.SCALE_SMOOTH)));
		botonSales.setBounds(30,140,125,80);
		botonSales.setBackground(color_blanco);
		botonSales.setBorder(null);
		panelIzq.add(botonSales);
		
		Image imgBotonSuppliers = new ImageIcon("img\\suppliers.png").getImage();
		botonSuppliers = new JButton(new ImageIcon(imgBotonSuppliers.getScaledInstance(125,80, Image.SCALE_SMOOTH)));
		botonSuppliers.setBounds(30,240,125,80);
		botonSuppliers.setBackground(color_blanco);
		botonSuppliers.setBorder(null);
		panelIzq.add(botonSuppliers);
		
		Image imgBotonCustomers = new ImageIcon("img\\customers.png").getImage();
		botonCustomers = new JButton(new ImageIcon(imgBotonCustomers.getScaledInstance(125,80, Image.SCALE_SMOOTH)));
		botonCustomers.setBounds(30,340,125,80);
		botonCustomers.setBackground(color_blanco);
		botonCustomers.setBorder(null);
		panelIzq.add(botonCustomers);
		
		Image imgBotonCRM = new ImageIcon("img\\crm.png").getImage();
		botonCRM = new JButton(new ImageIcon(imgBotonCRM.getScaledInstance(125,80, Image.SCALE_SMOOTH)));
		botonCRM.setBounds(30,440,125,80);
		botonCRM.setBackground(color_blanco);
		botonCRM.setBorder(null);
		panelIzq.add(botonCRM);
		
		Image imgBotonStock = new ImageIcon("img\\stock.png").getImage();
		botonStock = new JButton(new ImageIcon(imgBotonStock.getScaledInstance(125,80, Image.SCALE_SMOOTH)));
		botonStock.setBounds(30,540,125,80);
		botonStock.setBackground(color_blanco);
		botonStock.setBorder(null);
		panelIzq.add(botonStock);
		
		//Botones panel derecho
				
		Image imgLog = new ImageIcon("img\\logo sin fondo.PNG").getImage();
		imageLogo = new JLabel(new ImageIcon(imgLog.getScaledInstance(180, 130, Image.SCALE_SMOOTH)));
		//las coordenadas del final han de coincidir con las anteriores
		imageLogo.setBounds(5, 40, 180, 130);
		panelDer.add(imageLogo);
		
		Image imgBotonHR = new ImageIcon("img\\human resources.png").getImage();
		botonHR = new JButton(new ImageIcon(imgBotonHR.getScaledInstance(125,80, Image.SCALE_SMOOTH)));
		botonHR.setBounds(18,200,125,80);
		botonHR.setBackground(color_blanco);
		botonHR.setBorder(null);
		panelDer.add(botonHR);
		
		Image imgBotonUser = new ImageIcon("img\\boton_user.png").getImage();
		botonUser = new JButton(new ImageIcon(imgBotonUser.getScaledInstance(125,80, Image.SCALE_SMOOTH)));
		botonUser.setBounds(18,350,125,80);
		botonUser.setBackground(color_blanco);
		botonUser.setBorder(null);
		panelDer.add(botonUser);
		
		Image imgBotonLogout = new ImageIcon("img\\logout.png").getImage();
		botonLogout = new JButton(new ImageIcon(imgBotonLogout.getScaledInstance(125,80, Image.SCALE_SMOOTH)));
		botonLogout.setBounds(18,445,125,80);
		botonLogout.setBackground(color_blanco);
		botonLogout.setBorder(null);
		panelDer.add(botonLogout);
		
		Image imgBotonExit = new ImageIcon("img\\exit.png").getImage();
		botonExitInit = new JButton(new ImageIcon(imgBotonExit.getScaledInstance(125,80, Image.SCALE_SMOOTH)));
		botonExitInit.setBounds(18,540,125,80);
		botonExitInit.setBackground(color_blanco);
		botonExitInit.setBorder(null);
		panelDer.add(botonExitInit);
		
		panelEmpleado();
		panelCompras();
		panelVentas();
		panelProveedores();
		panelClientes();
		panelCRM();
		panelAlmacen();
		panelNomina();
		
	}

	private void panelNomina() {
		// TODO Auto-generated method stub
		
		panelNomina = new JPanel();
		panelNomina.setBackground(color_panel);
		panelNomina.setBounds(200, 40, 750, 580);
		panelNomina.setLayout(null);
		add(panelNomina);
		panelNomina.setVisible(false);
		
		JLUsuarioNomina = new JLabel();
		JLUsuarioNomina.setBounds(20, 0, 710, 45);
		JLUsuarioNomina.setBorder(null);
		JLUsuarioNomina.setFont(new Font("Segoe UI",Font.BOLD,36));//Damos formato al contenido
		JLUsuarioNomina.setForeground(color_azul);//Color del texto
		JLUsuarioNomina.setHorizontalAlignment(JLabel.CENTER);
		JLUsuarioNomina.setVerticalAlignment(JLabel.CENTER);
		panelNomina.add(JLUsuarioNomina);
		
		JLTextoConsulNom = new JLabel("Wellcome to Nominas consult. Select Year and Month to inquiry.");
		JLTextoConsulNom.setBounds(20, 45, 710, 30);
		JLTextoConsulNom.setBorder(null);
		JLTextoConsulNom.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLTextoConsulNom.setForeground(color_azul);//Color del texto
		panelNomina.add(JLTextoConsulNom);
		
		comboAnnoNomina = new JComboBox<String>();
		comboAnnoNomina.addItem("Year"); 	
		comboAnnoNomina.addItem("2019"); 
	    comboAnnoNomina.setSelectedIndex(0); // Foco en el item 0
	    comboAnnoNomina.setBounds(20, 80, 220, 30);
	    panelNomina.add(comboAnnoNomina);		
	    
	    comboMesNomina = new JComboBox<String>();
	    comboMesNomina.addItem("Month");
	    comboMesNomina.addItem("Enero");
	    comboMesNomina.addItem("Febrero");
	    comboMesNomina.addItem("Marzo");
	    comboMesNomina.addItem("Abril");
	    comboMesNomina.addItem("Mayo");
	    comboMesNomina.addItem("Junio");	    
	    comboMesNomina.setSelectedIndex(0); // Foco en el item 0
	    comboMesNomina.setBounds(250, 80, 220, 30);
	    panelNomina.add(comboMesNomina);  
	    
	    Image checkNomina = new ImageIcon("img\\check.png").getImage();
		botonCheckNom = new JButton(new ImageIcon(checkNomina.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonCheckNom.setBounds(520, 80, 110, 42);
		botonCheckNom.setBorder(null); // Eliminamos el borde
		botonCheckNom.setBackground(color_panel);
		panelNomina.add(botonCheckNom);// Anadimos
		
		//******************//TRABAJADOR
		
		JLDatosTrabajador = new JLabel("Datos Trabajador");
		JLDatosTrabajador.setBounds(20, 130, 300, 30);
		JLDatosTrabajador.setBorder(null);
		JLDatosTrabajador.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLDatosTrabajador.setForeground(Color.gray);//Color del texto
		panelNomina.add(JLDatosTrabajador);
		
		tiraDatosTrabajador = new JTextField();
		tiraDatosTrabajador.setBounds(20, 160, 300, 5);
		tiraDatosTrabajador.setBackground(Color.gray);
		panelNomina.add(tiraDatosTrabajador);
		
		JLDNIEmpNom = new JLabel("DNI: ");
		JLDNIEmpNom.setBounds(20, 170, 100, 25);
		JLDNIEmpNom.setBorder(null);
		JLDNIEmpNom.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLDNIEmpNom.setForeground(Color.gray);//Color del texto
		panelNomina.add(JLDNIEmpNom); 	
		
		JLresultDNIEmpNomima = new JLabel("123456789X");
		JLresultDNIEmpNomima.setBounds(130, 170, 200, 25);
		JLresultDNIEmpNomima.setBorder(null);
		JLresultDNIEmpNomima.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLresultDNIEmpNomima.setForeground(color_azul);//Color del texto
		panelNomina.add(JLresultDNIEmpNomima); 	
		
		JLSSEmpNom = new JLabel("SS: ");
		JLSSEmpNom.setBounds(20, 195, 100, 25);
		JLSSEmpNom.setBorder(null);
		JLSSEmpNom.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLSSEmpNom.setForeground(Color.gray);//Color del texto
		panelNomina.add(JLSSEmpNom); 	
		
		JLresulSSEmpNomina = new JLabel("28euqijdl98349183921");
		JLresulSSEmpNomina.setBounds(130, 195, 200, 25);
		JLresulSSEmpNomina.setBorder(null);
		JLresulSSEmpNomina.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLresulSSEmpNomina.setForeground(color_azul);//Color del texto
		panelNomina.add(JLresulSSEmpNomina); 	
		
		JLCatEmpNom = new JLabel("Categoría: ");
		JLCatEmpNom.setBounds(20, 220, 100, 25);
		JLCatEmpNom.setBorder(null);
		JLCatEmpNom.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLCatEmpNom.setForeground(Color.gray);//Color del texto
		panelNomina.add(JLCatEmpNom);
		
		JLRESULCatEmpNomina = new JLabel("ejemplo");
		JLRESULCatEmpNomina.setBounds(130, 220, 100, 25);
		JLRESULCatEmpNomina.setBorder(null);
		JLRESULCatEmpNomina.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLRESULCatEmpNomina.setForeground(color_azul);//Color del texto
		panelNomina.add(JLRESULCatEmpNomina);
		
		JLGrupoCotEmpNom = new JLabel("Grupo Cot: ");
		JLGrupoCotEmpNom.setBounds(20, 245, 100, 25);
		JLGrupoCotEmpNom.setBorder(null);
		JLGrupoCotEmpNom.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLGrupoCotEmpNom.setForeground(Color.gray);//Color del texto
		panelNomina.add(JLGrupoCotEmpNom);
		
		JLresulGrupoCotEmpNomina = new JLabel("ejemplo");
		JLresulGrupoCotEmpNomina.setBounds(130, 245, 100, 25);
		JLresulGrupoCotEmpNomina.setBorder(null);
		JLresulGrupoCotEmpNomina.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLresulGrupoCotEmpNomina.setForeground(Color.gray);//Color del texto
		panelNomina.add(JLresulGrupoCotEmpNomina);
		
		//******************//EMPRESA
		
		JLDatosEmpresa = new JLabel("Datos Empresa");
		JLDatosEmpresa.setBounds(350, 130, 300, 30);
		JLDatosEmpresa.setBorder(null);
		JLDatosEmpresa.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLDatosEmpresa.setForeground(Color.gray);//Color del texto
		panelNomina.add(JLDatosEmpresa);
		
		tiraDatosEmpresa = new JTextField();
		tiraDatosEmpresa.setBounds(350, 160, 320, 5);
		tiraDatosEmpresa.setBackground(Color.gray);
		panelNomina.add(tiraDatosEmpresa);
		
		JLDomHogarNom = new JLabel("DomHogar, S.L.");
		JLDomHogarNom.setBounds(350, 170, 300, 25);
		JLDomHogarNom.setBorder(null);
		JLDomHogarNom.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLDomHogarNom.setForeground(Color.gray);//Color del texto
		panelNomina.add(JLDomHogarNom);
		
		JLCIFNom = new JLabel("CIF: B25675368, SL");
		JLCIFNom.setBounds(350, 195, 300, 25);
		JLCIFNom.setBorder(null);
		JLCIFNom.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLCIFNom.setForeground(Color.gray);//Color del texto
		panelNomina.add(JLCIFNom);
		
		JLDomiEmpresaNom = new JLabel("Pol.Industrial El Pez, S/N, 28600, Madrid");
		JLDomiEmpresaNom.setBounds(350, 220, 300, 50);
		JLDomiEmpresaNom.setBorder(null);
		JLDomiEmpresaNom.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLDomiEmpresaNom.setForeground(Color.gray);//Color del texto
		panelNomina.add(JLDomiEmpresaNom);
		
		//******************//LIQUIDACION
		
		tiraPeriodo = new JTextField();
		tiraPeriodo.setBounds(20, 270, 650, 5);
		tiraPeriodo.setBackground(Color.gray);
		panelNomina.add(tiraPeriodo);
		
		//******************//DEVENGOS
		
		JLDevengosTitulo = new JLabel("DEVENGOS");
		JLDevengosTitulo.setBounds(20, 280, 100, 25);
		JLDevengosTitulo.setBorder(null);
		JLDevengosTitulo.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLDevengosTitulo.setBackground(Color.black);
		JLDevengosTitulo.setForeground(Color.DARK_GRAY);//Color del texto
		panelNomina.add(JLDevengosTitulo);
		
		JLSalarioBase = new JLabel("Salario Base: ");
		JLSalarioBase.setBounds(40, 305, 100, 25);
		JLSalarioBase.setBorder(null);
		JLSalarioBase.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLSalarioBase.setBackground(Color.black);
		JLSalarioBase.setForeground(Color.GRAY);//Color del texto
		panelNomina.add(JLSalarioBase);
		
		JLresulSalarioBase = new JLabel("1.100 €");
		JLresulSalarioBase.setBounds(330, 305, 100, 25);
		JLresulSalarioBase.setBorder(null);
		JLresulSalarioBase.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido	
		JLresulSalarioBase.setForeground(color_azul);//Color del texto
		panelNomina.add(JLresulSalarioBase);
		
		JLHorasExtras = new JLabel("Horas Extras: ");
		JLHorasExtras.setBounds(40, 330, 100, 25);
		JLHorasExtras.setBorder(null);
		JLHorasExtras.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLHorasExtras.setForeground(Color.GRAY);//Color del texto
		panelNomina.add(JLHorasExtras);
		
		JLresulHorasExtras = new JLabel("8");
		JLresulHorasExtras.setBounds(330, 330, 100, 25);
		JLresulHorasExtras.setBorder(null);
		JLresulHorasExtras.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLresulHorasExtras.setForeground(color_azul);//Color del texto
		panelNomina.add(JLresulHorasExtras);
		
		JLDietas = new JLabel("Dietas: ");
		JLDietas.setBounds(40, 355, 100, 25);
		JLDietas.setBorder(null);
		JLDietas.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLDietas.setForeground(Color.GRAY);//Color del texto
		panelNomina.add(JLDietas);
		
		JLresulDietas = new JLabel("10");
		JLresulDietas.setBounds(330, 355, 100, 25);
		JLresulDietas.setBorder(null);
		JLresulDietas.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLresulDietas.setForeground(color_azul);//Color del texto
		panelNomina.add(JLresulDietas);
		
		JLTotaldevengado = new JLabel("Total Devengado: ");
		JLTotaldevengado.setBounds(150, 380, 180, 25);
		JLTotaldevengado.setBorder(null);
		JLTotaldevengado.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLTotaldevengado.setForeground(Color.DARK_GRAY);//Color del texto
		panelNomina.add(JLTotaldevengado);
		
		JLresulTotalDevengado = new JLabel("1400 €");
		JLresulTotalDevengado.setBounds(330, 380, 100, 25);
		JLresulTotalDevengado.setBorder(null);
		JLresulTotalDevengado.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLresulTotalDevengado.setForeground(color_azul);//Color del texto
		panelNomina.add(JLresulTotalDevengado);		
		
		//******************//RETENCION
		
		JLRetencionesTitulo = new JLabel("RETENCIONES");
		JLRetencionesTitulo.setBounds(20, 405, 100, 25);
		JLRetencionesTitulo.setBorder(null);
		JLRetencionesTitulo.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLRetencionesTitulo.setForeground(Color.DARK_GRAY);//Color del texto
		panelNomina.add(JLRetencionesTitulo);		
		
		JLConComunes = new JLabel("Con.Comunes - 4,70%: ");
		JLConComunes.setBounds(40, 430, 200, 25);
		JLConComunes.setBorder(null);
		JLConComunes.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLConComunes.setForeground(Color.GRAY);//Color del texto
		panelNomina.add(JLConComunes);
		
		JLresulConComunes = new JLabel("55 €");
		JLresulConComunes.setBounds(330, 430, 100, 25);
		JLresulConComunes.setBorder(null);
		JLresulConComunes.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLresulConComunes.setForeground(color_azul);//Color del texto
		panelNomina.add(JLresulConComunes);	
		
		JLDesempleo= new JLabel("Desempleo - 1,55%: ");
		JLDesempleo.setBounds(40, 455, 150, 25);
		JLDesempleo.setBorder(null);
		JLDesempleo.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLDesempleo.setForeground(Color.GRAY);//Color del texto
		panelNomina.add(JLDesempleo);
		
		JLresulDesempleo = new JLabel("2,25 €");
		JLresulDesempleo.setBounds(330, 455, 100, 25);
		JLresulDesempleo.setBorder(null);
		JLresulDesempleo.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLresulDesempleo.setForeground(color_azul);//Color del texto
		panelNomina.add(JLresulDesempleo);
		
		JLFormacion= new JLabel("Formación - 0,10%: ");
		JLFormacion.setBounds(40, 480, 150, 25);
		JLFormacion.setBorder(null);
		JLFormacion.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLFormacion.setForeground(Color.GRAY);//Color del texto
		panelNomina.add(JLFormacion);
		
		JLresulFormacion = new JLabel("1,10 €");
		JLresulFormacion.setBounds(330, 480, 100, 25);
		JLresulFormacion.setBorder(null);
		JLresulFormacion.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLresulFormacion.setForeground(color_azul);//Color del texto
		panelNomina.add(JLresulFormacion);
		
		JLIRPF= new JLabel("I.R.P.F. - 11%: ");
		JLIRPF.setBounds(40, 505, 150, 25);
		JLIRPF.setBorder(null);
		JLIRPF.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLIRPF.setForeground(Color.GRAY);//Color del texto
		panelNomina.add(JLIRPF);
		
		JLresulIRPF = new JLabel("250 €");
		JLresulIRPF.setBounds(330, 505, 100, 25);
		JLresulIRPF.setBorder(null);
		JLresulIRPF.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLresulIRPF.setForeground(color_azul);//Color del texto
		panelNomina.add(JLresulIRPF);
		
		JLTotalDeducir = new JLabel("Total a Deducir: ");
		JLTotalDeducir.setBounds(150, 530, 180, 25);
		JLTotalDeducir.setBorder(null);
		JLTotalDeducir.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLTotalDeducir.setForeground(Color.DARK_GRAY);//Color del texto
		panelNomina.add(JLTotalDeducir);
		
		JLresulTotalDeducir = new JLabel("439 €");
		JLresulTotalDeducir.setBounds(330, 530, 100, 25);
		JLresulTotalDeducir.setBorder(null);
		JLresulTotalDeducir.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		JLresulTotalDeducir.setForeground(color_azul);//Color del texto
		panelNomina.add(JLresulTotalDeducir);	
		
	}

	// PANEL COMPRAS
	private void panelCompras() {
		
		panelCompras = new JPanel();
		panelCompras.setBackground(color_panel);
		panelCompras.setBounds(200, 40, 750, 230);
		panelCompras.setLayout(null);
		add(panelCompras);
		panelCompras.setVisible(false);		
		
		barraCompras = new JScrollPane();
		barraCompras.setBounds(20, 20, 710, 190);
		panelCompras.add(barraCompras);
		
		String titulosCompras[] = {"Delivery Note's Code", "Product's Code", "Product's Name", "Quantity", "Purchase amount", "Total Account", 
				"Supplier's code", "Supplier", "Date"};
		String infoCompras[][] = AccesoDB.obtenerMatrizCompras();
		
		tablaCompras = new JTable(infoCompras,titulosCompras);
		barraCompras.setViewportView(tablaCompras);
		
		//SUBPANEL INSERTAR COMPRAS
		
		subPanelInsertCompras = new JPanel();
		subPanelInsertCompras.setBounds(200, 270, 750, 268);
		subPanelInsertCompras.setBackground(color_panel);
		subPanelInsertCompras.setLayout(null);
		add(subPanelInsertCompras);
		subPanelInsertCompras.setVisible(false);	
		
		JLabelNuevaCompra = new JLabel("new purchase");
		JLabelNuevaCompra.setBounds(20, 0, 710, 60);
		JLabelNuevaCompra.setBorder(null);
		JLabelNuevaCompra.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		JLabelNuevaCompra.setForeground(color_azul);//Color del texto
		JLabelNuevaCompra.setHorizontalAlignment(JLabel.CENTER);
		JLabelNuevaCompra.setVerticalAlignment(JLabel.CENTER);
		subPanelInsertCompras.add(JLabelNuevaCompra);
		
		JTFnumAlbaran = new JTextField();//Creamos el componente
		TextPrompt placeholdernumal = new TextPrompt("Delivery Note's Code", JTFnumAlbaran);
		placeholdernumal.changeAlpha(0.75f);
		placeholdernumal.changeStyle(Font.ITALIC);
	    JTFnumAlbaran.setBounds(20,70,150,30);//Posicionamos		
	    JTFnumAlbaran.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    JTFnumAlbaran.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    JTFnumAlbaran.setBackground(Color.WHITE); //Color de fondo
	    JTFnumAlbaran.setForeground(color_azul);//Color del texto
	    subPanelInsertCompras.add(JTFnumAlbaran);//Anadimos
		
	    //Combo producto
	    comboProductoCompras = new JComboBox<String>();
	    comboProductoCompras.addItem("Product's code");	    
	    //Obtenemos los codigos de producto de la BBDD de la Tabla Compras   	    
	    ArrayList<Producto> lista_productos = AccesoDB.datosProducto(conexion);	    
	    for (Producto p : lista_productos) {
	    	comboProductoCompras.addItem(p.getCod_Producto());
		}	    
	    comboProductoCompras.setSelectedIndex(0); // Foco en el item 0
	    comboProductoCompras.setBounds(20, 170, 150, 30);
		subPanelInsertCompras.add(comboProductoCompras);			
		
		JLresulComboProCompra = new JLabel();//Creamos el componente
		JLresulComboProCompra.setBounds(200,170,220,30);//Posicionamos
		JLresulComboProCompra.setBorder(null); //Eliminamos el borde
		JLresulComboProCompra.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		JLresulComboProCompra.setForeground(color_azul);//Color del texto
		subPanelInsertCompras.add(JLresulComboProCompra);//Anadimos	
		
		JTFcantidadCompra = new JTextField();//Creamos el componente
		TextPrompt placeholdercancom = new TextPrompt("Quantity", JTFcantidadCompra);
		placeholdercancom.changeAlpha(0.75f);
		placeholdercancom.changeStyle(Font.ITALIC);
		JTFcantidadCompra.setBounds(20,120,150,30);//Posicionamos		
		JTFcantidadCompra.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
		JTFcantidadCompra.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		JTFcantidadCompra.setBackground(Color.WHITE); //Color de fondo
		JTFcantidadCompra.setForeground(color_azul);//Color del texto
	    subPanelInsertCompras.add(JTFcantidadCompra);//Anadimos
	    
	    JLresulimporCompraPro = new JLabel("");//Creamos el componente
	    JLresulimporCompraPro.setBounds(200,120,220,30);//Posicionamos
	    JLresulimporCompraPro.setBorder(null); //Eliminamos el borde
	    JLresulimporCompraPro.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    JLresulimporCompraPro.setForeground(color_azul);//Color del texto
		subPanelInsertCompras.add(JLresulimporCompraPro);//Anadimos	
		
		JLresulimporTotalPro = new JLabel("");// Creamos el componente
		JLresulimporTotalPro.setBounds(450, 120, 280, 30);// Posicionamos
		JLresulimporTotalPro.setBorder(null); // Eliminamos el borde
		JLresulimporTotalPro.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		JLresulimporTotalPro.setForeground(color_azul);// Color del texto
		subPanelInsertCompras.add(JLresulimporTotalPro);// Anadimos
		
		//Combo compras
		comboProveedorCompras = new JComboBox<String>();
		comboProveedorCompras.addItem("Supplier's code");
	    //Obtenemos los codigos de producto de la BBDD de la Tabla Compras	   		    
	    ArrayList<Proveedor> lista_proveedor = AccesoDB.datosProveedor(conexion);	    
	    for (Proveedor p : lista_proveedor) {
	    	comboProveedorCompras.addItem(p.getCodproveedor());
		}	    
	    comboProveedorCompras.setSelectedIndex(0); // Foco en el item 0
	    comboProveedorCompras.setBounds(200, 70, 220, 30);
		subPanelInsertCompras.add(comboProveedorCompras);
		
		JLresulComboProveedorCompra = new JLabel("");//Creamos el componente
		JLresulComboProveedorCompra.setBounds(450,70,280,30);//Posicionamos
		JLresulComboProveedorCompra.setBorder(null); //Eliminamos el borde
		JLresulComboProveedorCompra.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		JLresulComboProveedorCompra.setForeground(color_azul);//Color del texto
		subPanelInsertCompras.add(JLresulComboProveedorCompra);//Anadimos	
		
		Image imgBotonVerificarCompra = new ImageIcon("img\\check.png").getImage();
		botonVerificarCompra = new JButton(new ImageIcon(imgBotonVerificarCompra.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonVerificarCompra.setBounds(600, 170, 110, 42);
		botonVerificarCompra.setBorder(null); // Eliminamos el borde
		botonVerificarCompra.setBackground(color_panel);
		subPanelInsertCompras.add(botonVerificarCompra);// Anadimos
		
		Image imgBotonCompra = new ImageIcon("img\\purchases.png").getImage();
		botonInsertCompraFinal = new JButton(new ImageIcon(imgBotonCompra.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonInsertCompraFinal.setBounds(20, 215, 110, 42);
		botonInsertCompraFinal.setBorder(null); // Eliminamos el borde
		botonInsertCompraFinal.setBackground(color_panel);
		subPanelInsertCompras.add(botonInsertCompraFinal);// Anadimos
		
		JLresulinsertComprafinal = new JLabel();//Creamos el componente
		JLresulinsertComprafinal.setBounds(150, 215, 500, 50);//Posicionamos
		JLresulinsertComprafinal.setBorder(null); //Eliminamos el borde
		JLresulinsertComprafinal.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		JLresulinsertComprafinal.setForeground(Color.gray);//Color del text
		JLresulinsertComprafinal.setVerticalAlignment(JLabel.CENTER);
		subPanelInsertCompras.add(JLresulinsertComprafinal);//Anadimos
		
		//SUBPANEL DELIVERY NOTE COMPRAS
		
		subPanelDeliveryNoteCompras = new JPanel();
		subPanelDeliveryNoteCompras.setBounds(200, 270, 750, 268);
		subPanelDeliveryNoteCompras.setBackground(color_panel);
		subPanelDeliveryNoteCompras.setLayout(null);
		add(subPanelDeliveryNoteCompras);
		subPanelDeliveryNoteCompras.setVisible(false);
		
		JLslectorJTableCom = new JLabel("(* Select a row to show detail)");// Creamos el componente
		JLslectorJTableCom.setBounds(140, 0, 300, 30);// Posicionamos
		JLslectorJTableCom.setBorder(null); // Eliminamos el borde
		JLslectorJTableCom.setFont(new Font("Segoe UI", Font.BOLD, 14));// Damos formato al contenido
		JLslectorJTableCom.setForeground(color_azul);// Color del texto
		JLslectorJTableCom.setVerticalAlignment(JLabel.CENTER);
		subPanelDeliveryNoteCompras.add(JLslectorJTableCom);// Anadimos
		
		Image checkDeliveryNoteCompra = new ImageIcon("img\\check.png").getImage();
		botonCheckDeliNoteCom = new JButton(new ImageIcon(checkDeliveryNoteCompra.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonCheckDeliNoteCom.setBounds(20, 0, 110, 42);
		botonCheckDeliNoteCom.setBorder(null); // Eliminamos el borde
		botonCheckDeliNoteCom.setBackground(color_panel);
		subPanelDeliveryNoteCompras.add(botonCheckDeliNoteCom);// Anadimos
		
		JLDeliveryNoteCompras = new JLabel("Delivery note #: ");// Creamos el componente
		JLDeliveryNoteCompras.setBounds(20, 50, 160, 30);// Posicionamos
		JLDeliveryNoteCompras.setBorder(null);
		JLDeliveryNoteCompras.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
		JLDeliveryNoteCompras.setForeground(color_azul);//Color del texto
		subPanelDeliveryNoteCompras.add(JLDeliveryNoteCompras);// Anadimos		
		JLDeliveryNoteCompras.setVisible(false);
		
		JLnumAlbaranCom = new JLabel();// Creamos el componente
		JLnumAlbaranCom.setBounds(190, 50, 100, 30);// Posicionamos
		JLnumAlbaranCom.setBorder(null); // Eliminamos el borde
		JLnumAlbaranCom.setFont(new Font("Segoe UI", Font.BOLD, 18));// Damos formato al contenido
		JLnumAlbaranCom.setForeground(Color.darkGray);// Color del texto
		subPanelDeliveryNoteCompras.add(JLnumAlbaranCom);// Anadimos	
		
		JLCustomerDomCom = new JLabel("CUSTOMER:");// Creamos el componente
		JLCustomerDomCom.setBounds(20, 80, 120, 30);// Posicionamos
		JLCustomerDomCom.setBorder(null); // Eliminamos el borde
		JLCustomerDomCom.setFont(new Font("Segoe UI", Font.BOLD, 18));// Damos formato al contenido
		JLCustomerDomCom.setForeground(color_azul);// Color del texto
		subPanelDeliveryNoteCompras.add(JLCustomerDomCom);// Anadimos
		JLCustomerDomCom.setVisible(false);
		
		JLDomohogarCom = new JLabel("DomHogar, S.L.");// Creamos el componente
		JLDomohogarCom.setBounds(140, 80, 150, 30);// Posicionamos
		JLDomohogarCom.setBorder(null); // Eliminamos el borde
		JLDomohogarCom.setFont(new Font("Segoe UI", Font.BOLD, 18));// Damos formato al contenido
		JLDomohogarCom.setForeground(Color.darkGray);// Color del texto
		subPanelDeliveryNoteCompras.add(JLDomohogarCom);// Anadimos
		JLDomohogarCom.setVisible(false);
		
		JLDateCom = new JLabel("DATE: ");// Creamos el componente
		JLDateCom.setBounds(300, 50, 70, 30);// Posicionamos
		JLDateCom.setBorder(null); // Eliminamos el borde
		JLDateCom.setFont(new Font("Segoe UI", Font.BOLD, 20));// Damos formato al contenido
		JLDateCom.setForeground(color_azul);// Color del texto
		subPanelDeliveryNoteCompras.add(JLDateCom);// Anadimos
		JLDateCom.setVisible(false);
		
		JLDateComRow = new JLabel();// Creamos el componente
		JLDateComRow.setBounds(370, 50, 200, 30);// Posicionamos
		JLDateComRow.setBorder(null); // Eliminamos el borde
		JLDateComRow.setFont(new Font("Segoe UI", Font.BOLD, 18));// Damos formato al contenido
		JLDateComRow.setForeground(Color.darkGray);// Color del texto
		subPanelDeliveryNoteCompras.add(JLDateComRow);// Anadimos
		
		JLSuplierCom = new JLabel("SUPPLIER: ");// Creamos el componente
		JLSuplierCom.setBounds(300, 80, 100, 30);// Posicionamos
		JLSuplierCom.setBorder(null); // Eliminamos el borde
		JLSuplierCom.setFont(new Font("Segoe UI", Font.BOLD, 18));// Damos formato al contenido
		JLSuplierCom.setForeground(color_azul);// Color del texto
		subPanelDeliveryNoteCompras.add(JLSuplierCom);// Anadimos
		JLSuplierCom.setVisible(false);
		
		JLSuplierComRow = new JLabel();// Creamos el componente
		JLSuplierComRow.setBounds(400, 80, 200, 30);// Posicionamos
		JLSuplierComRow.setBorder(null); // Eliminamos el borde
		JLSuplierComRow.setFont(new Font("Segoe UI", Font.BOLD, 18));// Damos formato al contenido
		JLSuplierComRow.setForeground(Color.darkGray);// Color del texto
		subPanelDeliveryNoteCompras.add(JLSuplierComRow);// Anadimos
		
		JLSuplierCode = new JLabel("CODE: ");// Creamos el componente
		JLSuplierCode.setBounds(610, 80, 70, 30);// Posicionamos
		JLSuplierCode.setBorder(null); // Eliminamos el borde
		JLSuplierCode.setFont(new Font("Segoe UI", Font.BOLD, 18));// Damos formato al contenido
		JLSuplierCode.setForeground(color_azul);// Color del texto
		subPanelDeliveryNoteCompras.add(JLSuplierCode);// Anadimos
		JLSuplierCode.setVisible(false);
		
		JLSuplierCodeRow = new JLabel();// Creamos el componente
		JLSuplierCodeRow.setBounds(680, 80, 50, 30);// Posicionamos
		JLSuplierCodeRow.setBorder(null); // Eliminamos el borde
		JLSuplierCodeRow.setFont(new Font("Segoe UI", Font.BOLD, 18));// Damos formato al contenido
		JLSuplierCodeRow.setForeground(Color.darkGray);// Color del texto
		subPanelDeliveryNoteCompras.add(JLSuplierCodeRow);// Anadimos
		
		JLTotalAccountCom = new JLabel("Total Account: ");// Creamos el componente
		JLTotalAccountCom.setBounds(400, 230, 180, 30);// Posicionamos
		JLTotalAccountCom.setBorder(null); // Eliminamos el borde
		JLTotalAccountCom.setFont(new Font("Segoe UI", Font.BOLD, 20));// Damos formato al contenido
		JLTotalAccountCom.setForeground(color_azul);// Color del texto
		subPanelDeliveryNoteCompras.add(JLTotalAccountCom);// Anadimos
		JLTotalAccountCom.setVisible(false);
		
		JLTotalAccountComSuma = new JLabel();// Creamos el componente
		JLTotalAccountComSuma.setBounds(580, 230, 120, 30);// Posicionamos
		JLTotalAccountComSuma.setBorder(null); // Eliminamos el borde
		JLTotalAccountComSuma.setFont(new Font("Segoe UI", Font.BOLD, 20));// Damos formato al contenido
		JLTotalAccountComSuma.setForeground(Color.darkGray);// Color del texto
		subPanelDeliveryNoteCompras.add(JLTotalAccountComSuma);// Anadimos
		
		//SUBPANEL DELETE COMPRAS
		
		subPanelComprasDelete = new JPanel();
		subPanelComprasDelete.setBounds(200, 270, 750, 268);
		subPanelComprasDelete.setBackground(color_panel);
		subPanelComprasDelete.setLayout(null);
		add(subPanelComprasDelete);
		subPanelComprasDelete.setVisible(false);
		
		JLselecJTableComDelete = new JLabel("(* Select a row to check purchase until delete)");// Creamos el componente
		JLselecJTableComDelete.setBounds(140, 0, 450, 30);// Posicionamos
		JLselecJTableComDelete.setBorder(null); // Eliminamos el borde
		JLselecJTableComDelete.setFont(new Font("Segoe UI", Font.BOLD, 14));// Damos formato al contenido
		JLselecJTableComDelete.setForeground(color_azul);// Color del texto
		JLselecJTableComDelete.setVerticalAlignment(JLabel.CENTER);
		subPanelComprasDelete.add(JLselecJTableComDelete);// Anadimos
		
		Image checkDeleteCompra = new ImageIcon("img\\check.png").getImage();
		botonCheckDeliNoteComDelete = new JButton(new ImageIcon(checkDeleteCompra.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonCheckDeliNoteComDelete.setBounds(20, 0, 110, 42);
		botonCheckDeliNoteComDelete.setBorder(null); // Eliminamos el borde
		botonCheckDeliNoteComDelete.setBackground(color_panel);
		subPanelComprasDelete.add(botonCheckDeliNoteComDelete);// Anadimos
		
		JLDeliveryNoteDeleteCom = new JLabel("Delivery note #: ");// Creamos el componente
		JLDeliveryNoteDeleteCom.setBounds(500, 0, 150, 30);// Posicionamos
		JLDeliveryNoteDeleteCom.setBorder(null);
		JLDeliveryNoteDeleteCom.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
		JLDeliveryNoteDeleteCom.setForeground(color_azul);//Color del texto
		subPanelComprasDelete.add(JLDeliveryNoteDeleteCom);// Anadimos		
		JLDeliveryNoteDeleteCom.setVisible(false);
		
		JLnumAlbaranDeleteCom = new JLabel();// Creamos el componente
		JLnumAlbaranDeleteCom.setBounds(660, 0, 80, 30);// Posicionamos
		JLnumAlbaranDeleteCom.setBorder(null); // Eliminamos el borde
		JLnumAlbaranDeleteCom.setFont(new Font("Segoe UI", Font.BOLD, 18));// Damos formato al contenido
		JLnumAlbaranDeleteCom.setForeground(Color.darkGray);// Color del texto
		subPanelComprasDelete.add(JLnumAlbaranDeleteCom);// Anadimos	
		
		Image deleteLineAl = new ImageIcon("img\\delete line.png").getImage();
		botonDeleteLineaAlbaran = new JButton(new ImageIcon(deleteLineAl.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonDeleteLineaAlbaran.setBounds(20, 160, 110, 42);
		botonDeleteLineaAlbaran.setBorder(null); // Eliminamos el borde
		botonDeleteLineaAlbaran.setBackground(color_panel);
		subPanelComprasDelete.add(botonDeleteLineaAlbaran);// Anadimos
		botonDeleteLineaAlbaran.setVisible(false);
		
		JLselecJTableComDeleteLineAlbaran = new JLabel("(* Select a row to delete a line of Delivery Note)");// Creamos el componente
		JLselecJTableComDeleteLineAlbaran.setBounds(140, 160, 450, 42);// Posicionamos
		JLselecJTableComDeleteLineAlbaran.setBorder(null); // Eliminamos el borde
		JLselecJTableComDeleteLineAlbaran.setFont(new Font("Segoe UI", Font.BOLD, 14));// Damos formato al contenido
		JLselecJTableComDeleteLineAlbaran.setForeground(color_azul);// Color del texto
		JLselecJTableComDeleteLineAlbaran.setVerticalAlignment(JLabel.CENTER);
		subPanelComprasDelete.add(JLselecJTableComDeleteLineAlbaran);// Anadimos
		JLselecJTableComDeleteLineAlbaran.setVisible(false);
		
		Image DeleteCompra = new ImageIcon("img\\delete purchase.png").getImage();
		botonDeleteAlabaran = new JButton(new ImageIcon(DeleteCompra.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonDeleteAlabaran.setBounds(20, 210, 110, 42);
		botonDeleteAlabaran.setBorder(null); // Eliminamos el borde
		botonDeleteAlabaran.setBackground(color_panel);
		subPanelComprasDelete.add(botonDeleteAlabaran);//Anadimos
		botonDeleteAlabaran.setVisible(false);
		
		JLselecJTableComDeleteAlbaran = new JLabel("(* Press to delete Delivery Note)");// Creamos el componente
		JLselecJTableComDeleteAlbaran.setBounds(140, 210, 450, 42);// Posicionamos
		JLselecJTableComDeleteAlbaran.setBorder(null); // Eliminamos el borde
		JLselecJTableComDeleteAlbaran.setFont(new Font("Segoe UI", Font.BOLD, 14));// Damos formato al contenido
		JLselecJTableComDeleteAlbaran.setForeground(color_azul);// Color del texto
		JLselecJTableComDeleteAlbaran.setVerticalAlignment(JLabel.CENTER);
		subPanelComprasDelete.add(JLselecJTableComDeleteAlbaran);// Anadimos
		JLselecJTableComDeleteAlbaran.setVisible(false);
		
		//SUBPANEL EXPORT COMPRAS
		
		subPanelComprasExport = new JPanel();
		subPanelComprasExport.setBounds(200, 270, 750, 268);
		subPanelComprasExport.setBackground(color_panel);
		subPanelComprasExport.setLayout(null);
		add(subPanelComprasExport);
		subPanelComprasExport.setVisible(false);
		
		exportCompras = new JLabel("export purchases to file");
		exportCompras.setBounds(20, 0, 710, 60);
		exportCompras.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		exportCompras.setForeground(color_azul);//Color del texto
		exportCompras.setHorizontalAlignment(JLabel.CENTER);
		exportCompras.setVerticalAlignment(JLabel.CENTER);
		subPanelComprasExport.add(exportCompras);//Anadimos al panel
				
		insertUsuarioPCCompras = new JTextField();//Creamos el componente
		TextPrompt placeholder12 = new TextPrompt("Insert user PC. Ej. ejemplo", insertUsuarioPCCompras);
		placeholder12.changeAlpha(0.75f);
		placeholder12.changeStyle(Font.ITALIC);
	    insertUsuarioPCCompras.setBounds(20,70,200,30);//Posicionamos		
	    insertUsuarioPCCompras.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertUsuarioPCCompras.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
	    insertUsuarioPCCompras.setBackground(Color.WHITE); //Color de fondo
	    insertUsuarioPCCompras.setForeground(color_azul);//Color del texto
	    subPanelComprasExport.add(insertUsuarioPCCompras);//Anadimos	
		
		insertRutaExportCompras = new JLabel("You'll find the file in C:\\Users\\youruser. Name of file: compras.csv ");//Creamos el componente
	    insertRutaExportCompras.setBounds(20,120,670,30);//Posicionamos		
	    insertRutaExportCompras.setBorder(null); //Eliminamos el borde
	    insertRutaExportCompras.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
	    insertRutaExportCompras.setForeground(color_azul);//Color del texto
	    subPanelComprasExport.add(insertRutaExportCompras);//Anadimos
			    
	    Image imgBotonExportComFinal = new ImageIcon("img\\export to file.png").getImage();
		botonExportComFinal = new JButton(new ImageIcon(imgBotonExportComFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		botonExportComFinal.setBounds(20,180,160,42);
		botonExportComFinal.setBackground(new Color(186,236,247));
		botonExportComFinal.setBorder(null); //Eliminamos el borde
		subPanelComprasExport.add(botonExportComFinal);//Anadimos 
				
	    resulExportCom = new JLabel();//Creamos el componente
	    resulExportCom.setBounds(200,180,500,30);//Posicionamos
	    resulExportCom.setBorder(null); //Eliminamos el borde
	    resulExportCom.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    resulExportCom.setForeground(Color.GRAY);//Color del texto
	    subPanelComprasExport.add(resulExportCom);//Anadimos	
	    
		//SUBPANEL BOTONES COMPRAS

		subPanelBotonesCompras = new JPanel();
		subPanelBotonesCompras.setBounds(200, 538, 750, 82);
		subPanelBotonesCompras.setBackground(color_panel);
		subPanelBotonesCompras.setLayout(null);
		add(subPanelBotonesCompras);
		subPanelBotonesCompras.setVisible(false);	
		
		Image imgBotonUpdateCompras = new ImageIcon("img\\delivery notes.png").getImage();
		botonDeliveryNoteCompra = new JButton(new ImageIcon(imgBotonUpdateCompras.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		botonDeliveryNoteCompra.setBounds((int) 67.5, 20, 160, 42);
		botonDeliveryNoteCompra.setBorder(null); // Eliminamos el borde
		botonDeliveryNoteCompra.setBackground(color_panel);
		subPanelBotonesCompras.add(botonDeliveryNoteCompra);// Anadimos

		Image imgBotonDeleteCompras = new ImageIcon("img\\delete purchase.png").getImage();
		botonDeleteCompra = new JButton(new ImageIcon(imgBotonDeleteCompras.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		botonDeleteCompra.setBounds(295, 20, 160, 42);
		botonDeleteCompra.setBorder(null); // Eliminamos el borde
		botonDeleteCompra.setBackground(color_panel);
		subPanelBotonesCompras.add(botonDeleteCompra);// Anadimos

		Image imgBotonExportCompras = new ImageIcon("img\\export to file.png").getImage();
		botonExportCompra = new JButton(new ImageIcon(imgBotonExportCompras.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		botonExportCompra.setBounds((int) 522.5, 20, 160, 42);
		botonExportCompra.setBorder(null); // Eliminamos el borde
		botonExportCompra.setBackground(color_panel);
		subPanelBotonesCompras.add(botonExportCompra);// Anadimos		
	} 

	//PANEL VENTAS
	private void panelVentas() {
		
		panelVentas = new JPanel();
		panelVentas.setBackground(color_panel);
		panelVentas.setBounds(200, 40, 750, 230);
		panelVentas.setLayout(null);
		add(panelVentas);
		panelVentas.setVisible(false);		
		
		barraVentas = new JScrollPane();
		barraVentas.setBounds(20, 20, 710, 190);
		panelVentas.add(barraVentas);
		
		String titulosVentas[] = {"Bill's Code", "Service Code", "Service Name", "Quantity", "Sale amount", "Total", 
				"Customer's dni", "Customer's Name", "Date"};
		String[][] infoVentas = AccesoDB.obtenerMatrizVentas();
		
		tablaVentas = new JTable(infoVentas,titulosVentas);
		barraVentas.setViewportView(tablaVentas);
		
		//SUBPANEL INSERTAR VENTAS
		
		subPanelInsertVentas = new JPanel();
		subPanelInsertVentas.setBounds(200, 270, 750, 268);
		subPanelInsertVentas.setBackground(color_panel);
		subPanelInsertVentas.setLayout(null);
		add(subPanelInsertVentas);
		subPanelInsertVentas.setVisible(false);	
		
		JLabelNuevaVenta = new JLabel("new sale");
		JLabelNuevaVenta.setBounds(20, 0, 710, 60);
		JLabelNuevaVenta.setBorder(null);
		JLabelNuevaVenta.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		JLabelNuevaVenta.setForeground(color_azul);//Color del texto
		JLabelNuevaVenta.setHorizontalAlignment(JLabel.CENTER);
		JLabelNuevaVenta.setVerticalAlignment(JLabel.CENTER);
		subPanelInsertVentas.add(JLabelNuevaVenta);
		
		JTFnumFactura = new JTextField();//Creamos el componente
		TextPrompt placeholdernumal = new TextPrompt("Bill's Code", JTFnumFactura);
		placeholdernumal.changeAlpha(0.75f);
		placeholdernumal.changeStyle(Font.ITALIC);
	    JTFnumFactura.setBounds(20,70,150,30);//Posicionamos		
	    JTFnumFactura.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    JTFnumFactura.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    JTFnumFactura.setBackground(Color.WHITE); //Color de fondo
	    JTFnumFactura.setForeground(color_azul);//Color del texto
	    subPanelInsertVentas.add(JTFnumFactura);//Anadimos
		
	    comboServicioVentas = new JComboBox<String>();
	    comboServicioVentas.addItem("Service code");	   

	    //Obtenemos los codigos de servicio de la BBDD de la Tabla Ventas	   		    
	    ArrayList<Servicio> lista_servicios = AccesoDB.datosServicio(conexion);
	    
	    for (Servicio s : lista_servicios) {
	    	comboServicioVentas.addItem(s.getCodServicio());
		}
	    
	    comboServicioVentas.setSelectedIndex(0); // Foco en el item 0
	    comboServicioVentas.setBounds(200, 70, 220, 30);
		subPanelInsertVentas.add(comboServicioVentas);
		
		JLresulComboSerVenta = new JLabel();//Creamos el componente
		JLresulComboSerVenta.setBounds(450,70,280,30);//Posicionamos
		JLresulComboSerVenta.setBorder(null); //Eliminamos el borde
		JLresulComboSerVenta.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		JLresulComboSerVenta.setForeground(color_azul);//Color del texto
		subPanelInsertVentas.add(JLresulComboSerVenta);//Anadimos	
		
		JTFcantidadVenta = new JTextField();//Creamos el componente
		TextPrompt placeholdercancom = new TextPrompt("Quantity", JTFcantidadVenta);
		placeholdercancom.changeAlpha(0.75f);
		placeholdercancom.changeStyle(Font.ITALIC);
		JTFcantidadVenta.setBounds(20,120,150,30);//Posicionamos		
		JTFcantidadVenta.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
		JTFcantidadVenta.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		JTFcantidadVenta.setBackground(Color.WHITE); //Color de fondo
		JTFcantidadVenta.setForeground(color_azul);//Color del texto
	    subPanelInsertVentas.add(JTFcantidadVenta);//Anadimos
	    
	    JLresulimporVentaServ = new JLabel("");//Creamos el componente
	    JLresulimporVentaServ.setBounds(200,120,220,30);//Posicionamos
	    JLresulimporVentaServ.setBorder(null); //Eliminamos el borde
	    JLresulimporVentaServ.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    JLresulimporVentaServ.setForeground(color_azul);//Color del texto
		subPanelInsertVentas.add(JLresulimporVentaServ);//Anadimos	
		
		JLresulimporTotalServ = new JLabel("");// Creamos el componente
		JLresulimporTotalServ.setBounds(450, 120, 280, 30);// Posicionamos
		JLresulimporTotalServ.setBorder(null); // Eliminamos el borde
		JLresulimporTotalServ.setFont(new Font("Segoe UI", Font.BOLD, 16));// Damos formato al contenido
		JLresulimporTotalServ.setForeground(color_azul);// Color del texto
		subPanelInsertVentas.add(JLresulimporTotalServ);// Anadimos
		
		comboClienteVentas = new JComboBox<String>();
		comboClienteVentas.addItem("Customer's code");	    
	    		
		//Obtenemos los codigos de producto de la BBDD de la Tabla Compras	   		    
	    ArrayList<Cliente> lista_cliente = AccesoDB.datosCliente(conexion);
	    
	    for (Cliente c : lista_cliente) {
	    	comboClienteVentas.addItem(c.getDni_Cliente());
		}
	    
	    comboClienteVentas.setSelectedIndex(0); // Foco en el item 0
	    comboClienteVentas.setBounds(20, 170, 150, 30);
		subPanelInsertVentas.add(comboClienteVentas);
		
		JLresulComboClienteVentas = new JLabel("");//Creamos el componente
		JLresulComboClienteVentas.setBounds(200,170,220,30);//Posicionamos
		JLresulComboClienteVentas.setBorder(null); //Eliminamos el borde
		JLresulComboClienteVentas.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		JLresulComboClienteVentas.setForeground(color_azul);//Color del texto
		subPanelInsertVentas.add(JLresulComboClienteVentas);//Anadimos	
		
		Image imgBotonVerificarVenta = new ImageIcon("img\\check.png").getImage();
		botonVerificarVenta = new JButton(new ImageIcon(imgBotonVerificarVenta.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonVerificarVenta.setBounds(600, 170, 110, 42);
		botonVerificarVenta.setBorder(null); // Eliminamos el borde
		botonVerificarVenta.setBackground(color_panel);
		subPanelInsertVentas.add(botonVerificarVenta);// Anadimos
		
		Image imgBotonVentas = new ImageIcon("img\\sales.png").getImage();
		botonInsertVentaFinal = new JButton(new ImageIcon(imgBotonVentas.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonInsertVentaFinal.setBounds(20, 215, 110, 42);
		botonInsertVentaFinal.setBorder(null); // Eliminamos el borde
		botonInsertVentaFinal.setBackground(color_panel);
		subPanelInsertVentas.add(botonInsertVentaFinal);// Anadimos
		
		JLresulinsertVentafinal = new JLabel();//Creamos el componente
		JLresulinsertVentafinal.setBounds(150, 215, 500, 50);//Posicionamos
		JLresulinsertVentafinal.setBorder(null); //Eliminamos el borde
		JLresulinsertVentafinal.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		JLresulinsertVentafinal.setForeground(Color.gray);//Color del text
		JLresulinsertVentafinal.setVerticalAlignment(JLabel.CENTER);
		subPanelInsertVentas.add(JLresulinsertVentafinal);//Anadimos
		
		//SUBPANEL BILLS VENTAS
		
		subPanelBills = new JPanel();
		subPanelBills.setBounds(200, 270, 750, 268);
		subPanelBills.setBackground(color_panel);
		subPanelBills.setLayout(null);
		add(subPanelBills);
		subPanelBills.setVisible(false);

		JLslectorJTableBills = new JLabel("(* Select a row to show detail)");// Creamos el componente
		JLslectorJTableBills.setBounds(140, 0, 300, 30);// Posicionamos
		JLslectorJTableBills.setBorder(null); // Eliminamos el borde
		JLslectorJTableBills.setFont(new Font("Segoe UI", Font.BOLD, 14));// Damos formato al contenido
		JLslectorJTableBills.setForeground(color_azul);// Color del texto
		JLslectorJTableBills.setVerticalAlignment(JLabel.CENTER);
		subPanelBills.add(JLslectorJTableBills);// Anadimos

		Image checkBill = new ImageIcon("img\\check.png").getImage();
		botonCheckBill = new JButton(new ImageIcon(checkBill.getScaledInstance(110, 42, Image.SCALE_SMOOTH)));
		botonCheckBill.setBounds(20, 0, 110, 42);
		botonCheckBill.setBorder(null); // Eliminamos el borde
		botonCheckBill.setBackground(color_panel);
		subPanelBills.add(botonCheckBill);// Anadimos

		JLBills = new JLabel("BILL: ");// Creamos el componente
		JLBills.setBounds(20, 50, 160, 30);// Posicionamos
		JLBills.setBorder(null);
		JLBills.setFont(new Font("Segoe UI", Font.BOLD, 20));// Damos formato al contenido
		JLBills.setForeground(color_azul);// Color del texto
		subPanelBills.add(JLBills);// Anadimos
		JLBills.setVisible(false);

		JLnumBill = new JLabel();// Creamos el componente
		JLnumBill.setBounds(150, 50, 170, 30);// Posicionamos
		JLnumBill.setBorder(null); // Eliminamos el borde
		JLnumBill.setFont(new Font("Segoe UI", Font.BOLD, 20));// Damos formato al contenido
		JLnumBill.setForeground(Color.darkGray);// Color del texto
		subPanelBills.add(JLnumBill);// Anadimos

		JLCustomer = new JLabel("CUSTOMER:");// Creamos el componente
		JLCustomer.setBounds(20, 80, 120, 30);// Posicionamos
		JLCustomer.setBorder(null); // Eliminamos el borde
		JLCustomer.setFont(new Font("Segoe UI", Font.BOLD, 20));// Damos formato al contenido
		JLCustomer.setForeground(color_azul);// Color del texto
		subPanelBills.add(JLCustomer);// Anadimos
		JLCustomer.setVisible(false);

		JLCustomerRow = new JLabel();// Creamos el componente
		JLCustomerRow.setBounds(140, 80, 150, 30);// Posicionamos
		JLCustomerRow.setBorder(null); // Eliminamos el borde
		JLCustomerRow.setFont(new Font("Segoe UI", Font.BOLD, 20));// Damos formato al contenido
		JLCustomerRow.setForeground(Color.darkGray);// Color del texto
		subPanelBills.add(JLCustomerRow);// Anadimos

		JLDate = new JLabel("DATE: ");// Creamos el componente
		JLDate.setBounds(320, 50, 70, 30);// Posicionamos
		JLDate.setBorder(null); // Eliminamos el borde
		JLDate.setFont(new Font("Segoe UI", Font.BOLD, 20));// Damos formato al contenido
		JLDate.setForeground(color_azul);// Color del texto
		subPanelBills.add(JLDate);// Anadimos
		JLDate.setVisible(false);

		JLDateRow = new JLabel();// Creamos el componente
		JLDateRow.setBounds(390, 50, 200, 30);// Posicionamos
		JLDateRow.setBorder(null); // Eliminamos el borde
		JLDateRow.setFont(new Font("Segoe UI", Font.BOLD, 20));// Damos formato al contenido
		JLDateRow.setForeground(Color.darkGray);// Color del texto
		subPanelBills.add(JLDateRow);// Anadimos

		JLCustomerName = new JLabel("NIF: ");// Creamos el componente
		JLCustomerName.setBounds(320, 80, 100, 30);// Posicionamos
		JLCustomerName.setBorder(null); // Eliminamos el borde
		JLCustomerName.setFont(new Font("Segoe UI", Font.BOLD, 20));// Damos formato al contenido
		JLCustomerName.setForeground(color_azul);// Color del texto
		subPanelBills.add(JLCustomerName);// Anadimos
		JLCustomerName.setVisible(false);

		JLCustomerNameRow = new JLabel();// Creamos el componente
		JLCustomerNameRow.setBounds(390, 80, 200, 30);// Posicionamos
		JLCustomerNameRow.setBorder(null); // Eliminamos el borde
		JLCustomerNameRow.setFont(new Font("Segoe UI", Font.BOLD, 20));// Damos formato al contenido
		JLCustomerNameRow.setForeground(Color.darkGray);// Color del texto
		subPanelBills.add(JLCustomerNameRow);// Anadimos

		JLTotalAmount = new JLabel("Total Amount: ");// Creamos el componente
		JLTotalAmount.setBounds(400, 230, 180, 30);// Posicionamos
		JLTotalAmount.setBorder(null); // Eliminamos el borde
		JLTotalAmount.setFont(new Font("Segoe UI", Font.BOLD, 20));// Damos formato al contenido
		JLTotalAmount.setForeground(color_azul);// Color del texto
		subPanelBills.add(JLTotalAmount);// Anadimos
		JLTotalAmount.setVisible(false);

		JLTotalAmountSum = new JLabel();// Creamos el componente
		JLTotalAmountSum.setBounds(580, 230, 120, 30);// Posicionamos
		JLTotalAmountSum.setBorder(null); // Eliminamos el borde
		JLTotalAmountSum.setFont(new Font("Segoe UI", Font.BOLD, 20));// Damos formato al contenido
		JLTotalAmountSum.setForeground(Color.darkGray);// Color del texto
		subPanelBills.add(JLTotalAmountSum);// Anadimos
		
		//SUBPANEL DELETE VENTAS
		
		subPanelVentasDelete = new JPanel();
		subPanelVentasDelete.setBounds(200, 270, 750, 268);
		subPanelVentasDelete.setBackground(color_panel);
		subPanelVentasDelete.setLayout(null);
		add(subPanelVentasDelete);
		subPanelVentasDelete.setVisible(false);

		JLselecJTableVenDelete = new JLabel("(* Select a row to check sale before delete)");// Creamos el componente
		JLselecJTableVenDelete.setBounds(140, 0, 450, 30);// Posicionamos
		JLselecJTableVenDelete.setBorder(null); // Eliminamos el borde
		JLselecJTableVenDelete.setFont(new Font("Segoe UI", Font.BOLD, 14));// Damos formato al contenido
		JLselecJTableVenDelete.setForeground(color_azul);// Color del texto
		JLselecJTableVenDelete.setVerticalAlignment(JLabel.CENTER);
		subPanelVentasDelete.add(JLselecJTableVenDelete);// Anadimos

		Image checkDeleteVenta = new ImageIcon("img\\check.png").getImage();
		botonCheckBillVenDelete = new JButton(new ImageIcon(checkDeleteVenta.getScaledInstance(110, 42, Image.SCALE_SMOOTH)));
		botonCheckBillVenDelete.setBounds(20, 0, 110, 42);
		botonCheckBillVenDelete.setBorder(null); // Eliminamos el borde
		botonCheckBillVenDelete.setBackground(color_panel);
		subPanelVentasDelete.add(botonCheckBillVenDelete);// Anadimos

		JLBillDeleteVen = new JLabel("Bill #: ");// Creamos el componente
		JLBillDeleteVen.setBounds(500, 0, 150, 30);// Posicionamos
		JLBillDeleteVen.setBorder(null);
		JLBillDeleteVen.setFont(new Font("Segoe UI", Font.BOLD, 18));// Damos formato al contenido
		JLBillDeleteVen.setForeground(color_azul);// Color del texto
		subPanelVentasDelete.add(JLBillDeleteVen);// Anadimos
		JLBillDeleteVen.setVisible(false);

		JLnumFacturaDeleteVen = new JLabel();// Creamos el componente
		JLnumFacturaDeleteVen.setBounds(660, 0, 80, 30);// Posicionamos
		JLnumFacturaDeleteVen.setBorder(null); // Eliminamos el borde
		JLnumFacturaDeleteVen.setFont(new Font("Segoe UI", Font.BOLD, 18));// Damos formato al contenido
		JLnumFacturaDeleteVen.setForeground(Color.darkGray);// Color del texto
		subPanelVentasDelete.add(JLnumFacturaDeleteVen);// Anadimos

		Image checkDeleteLineaVenta = new ImageIcon("img\\delete line.png").getImage();
		botonDeleteLineaFactura = new JButton(new ImageIcon(checkDeleteLineaVenta.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonDeleteLineaFactura.setBounds(20, 160, 110, 42);
		botonDeleteLineaFactura.setBorder(null); // Eliminamos el borde
		botonDeleteLineaFactura.setBackground(color_panel);
		subPanelVentasDelete.add(botonDeleteLineaFactura);// Anadimos
		botonDeleteLineaFactura.setVisible(false);

		JLselecJTableVenDeleteLineFactura = new JLabel("(* Select a row to delete a Bill's line)");// Creamos el componente
		JLselecJTableVenDeleteLineFactura.setBounds(140, 160, 450, 42);// Posicionamos
		JLselecJTableVenDeleteLineFactura.setBorder(null); // Eliminamos el borde
		JLselecJTableVenDeleteLineFactura.setFont(new Font("Segoe UI", Font.BOLD, 14));// Damos formato al contenido
		JLselecJTableVenDeleteLineFactura.setForeground(color_azul);// Color del texto
		JLselecJTableVenDeleteLineFactura.setVerticalAlignment(JLabel.CENTER);
		subPanelVentasDelete.add(JLselecJTableVenDeleteLineFactura);// Anadimos
		JLselecJTableVenDeleteLineFactura.setVisible(false);

		Image DeleteVenta = new ImageIcon("img\\delete sale.png").getImage();
		botonDeleteFactura = new JButton(new ImageIcon(DeleteVenta.getScaledInstance(110, 42, Image.SCALE_SMOOTH)));
		botonDeleteFactura.setBounds(20, 210, 110, 42);
		botonDeleteFactura.setBorder(null); // Eliminamos el borde
		botonDeleteFactura.setBackground(color_panel);
		subPanelVentasDelete.add(botonDeleteFactura);// Anadimos
		botonDeleteFactura.setVisible(false);

		JLselecJTableVenDeleteFactura = new JLabel("(* Press to delete Bill)");// Creamos el componente
		JLselecJTableVenDeleteFactura.setBounds(140, 210, 450, 42);// Posicionamos
		JLselecJTableVenDeleteFactura.setBorder(null); // Eliminamos el borde
		JLselecJTableVenDeleteFactura.setFont(new Font("Segoe UI", Font.BOLD, 14));// Damos formato al contenido
		JLselecJTableVenDeleteFactura.setForeground(color_azul);// Color del texto
		JLselecJTableVenDeleteFactura.setVerticalAlignment(JLabel.CENTER);
		subPanelVentasDelete.add(JLselecJTableVenDeleteFactura);// Anadimos
		JLselecJTableVenDeleteFactura.setVisible(false);
		
		//SUBPANEL EXPORT VENTAS
		
		subPanelVentasExport = new JPanel();
		subPanelVentasExport.setBounds(200, 270, 750, 268);
		subPanelVentasExport.setBackground(color_panel);
		subPanelVentasExport.setLayout(null);
		add(subPanelVentasExport);
		subPanelVentasExport.setVisible(false);
		
		exportVentas = new JLabel("export sales to file");
		exportVentas.setBounds(20, 0, 710, 60);
		exportVentas.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		exportVentas.setForeground(color_azul);//Color del texto
		exportVentas.setHorizontalAlignment(JLabel.CENTER);
		exportVentas.setVerticalAlignment(JLabel.CENTER);
		subPanelVentasExport.add(exportVentas);//Anadimos al panel
		
		insertUsuarioPCVentas = new JTextField();//Creamos el componente
		TextPrompt placeholder12 = new TextPrompt("Insert user PC. Ej. ejemplo", insertUsuarioPCVentas);
		placeholder12.changeAlpha(0.75f);
		placeholder12.changeStyle(Font.ITALIC);
		insertUsuarioPCVentas.setBounds(20,70,200,30);//Posicionamos		
		insertUsuarioPCVentas.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
		insertUsuarioPCVentas.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		insertUsuarioPCVentas.setBackground(Color.WHITE); //Color de fondo
		insertUsuarioPCVentas.setForeground(color_azul);//Color del texto
	    subPanelVentasExport.add(insertUsuarioPCVentas);//Anadimos	
		
		insertRutaexportVentas = new JLabel("You'll find the file in C:\\Users\\youruser. Name of file: ventas.csv ");//Creamos el componente
	    insertRutaexportVentas.setBounds(20,120,670,30);//Posicionamos		
	    insertRutaexportVentas.setBorder(null); //Eliminamos el borde
	    insertRutaexportVentas.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
	    insertRutaexportVentas.setForeground(color_azul);//Color del texto
	    subPanelVentasExport.add(insertRutaexportVentas);//Anadimos
			    
	    Image imgBotonExportVentFinal = new ImageIcon("img\\export to file.png").getImage();
		botonExportVenFinal = new JButton(new ImageIcon(imgBotonExportVentFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		botonExportVenFinal .setBounds(20,180,160,42);
		botonExportVenFinal .setBackground(new Color(186,236,247));
		botonExportVenFinal .setBorder(null); //Eliminamos el borde
		subPanelVentasExport.add(botonExportVenFinal );//Anadimos 
				
	    resulExportVen = new JLabel();//Creamos el componente
	    resulExportVen.setBounds(200,180,500,30);//Posicionamos
	    resulExportVen.setBorder(null); //Eliminamos el borde
	    resulExportVen.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    resulExportVen.setForeground(Color.GRAY);//Color del texto
	    subPanelVentasExport.add(resulExportVen);//Anadimos	
		
		//SUBPANEL BOTONES VENTAS

		subPanelBotonesVentas = new JPanel();
		subPanelBotonesVentas.setBounds(200, 538, 750, 82);
		subPanelBotonesVentas.setBackground(color_panel);
		subPanelBotonesVentas.setLayout(null);
		add(subPanelBotonesVentas);
		subPanelBotonesVentas.setVisible(false);	
		
		Image imgBotonUpdateVentas = new ImageIcon("img\\bills.png").getImage();
		botonBillsVentas = new JButton(new ImageIcon(imgBotonUpdateVentas.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		botonBillsVentas.setBounds((int) 67.5, 20, 160, 42);
		botonBillsVentas.setBorder(null); // Eliminamos el borde
		botonBillsVentas.setBackground(color_panel);
		subPanelBotonesVentas.add(botonBillsVentas);// Anadimos
		
		Image imgBotonDeleteVentas = new ImageIcon("img\\delete sale.png").getImage();
		botonDeleteVenta = new JButton(new ImageIcon(imgBotonDeleteVentas.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		botonDeleteVenta.setBounds(295, 20, 160, 42);
		botonDeleteVenta.setBorder(null); // Eliminamos el borde
		botonDeleteVenta.setBackground(color_panel);
		subPanelBotonesVentas.add(botonDeleteVenta);// Anadimos

		Image imgBotonExportVentas = new ImageIcon("img\\export to file.png").getImage();
		botonExportVenta = new JButton(new ImageIcon(imgBotonExportVentas.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		botonExportVenta.setBounds((int) 522.5, 20, 160, 42);
		botonExportVenta.setBorder(null); // Eliminamos el borde
		botonExportVenta.setBackground(color_panel);
		subPanelBotonesVentas.add(botonExportVenta);// Anadimos
		
	}

	//PANEL PROVEEDORES
	private void panelProveedores() {
		panelProveedores = new JPanel();
		panelProveedores.setBackground(color_panel);
		panelProveedores.setBounds(200, 40, 750, 230);
		panelProveedores.setLayout(null);
		add(panelProveedores);
		panelProveedores.setVisible(false);
		
		barraProveedores = new JScrollPane();
		barraProveedores.setBounds(20, 20, 710, 190);
		panelProveedores.add(barraProveedores);
		
		String titulosProveedores[] = {"Codigo proveedor", "Nombre proveedor", "contacto"};
		String infoProveedores[][] = AccesoDB.obtenerMatrizProveedores();
		
		tablaProveedores = new JTable(infoProveedores,titulosProveedores);
		tablaProveedores.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaProveedores.getColumnModel().getColumn(1).setPreferredWidth(115);
		tablaProveedores.getColumnModel().getColumn(2).setPreferredWidth(140);
		barraProveedores.setViewportView(tablaProveedores);
		
		//SUBPANEL INSERTAR PROVEEDOR
		
		subPanelInsProv = new JPanel();
		subPanelInsProv.setBounds(200, 270, 750, 268);
		subPanelInsProv.setBackground(color_panel);
		subPanelInsProv.setLayout(null);
		add(subPanelInsProv);
		subPanelInsProv.setVisible(false);	
		
		nuevoProv = new JLabel("new supplier");
		nuevoProv.setBounds(20, 0, 710, 60);
		nuevoProv.setBorder(null);
		nuevoProv.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		nuevoProv.setForeground(color_azul);//Color del texto
		nuevoProv.setHorizontalAlignment(JLabel.CENTER);
		nuevoProv.setVerticalAlignment(JLabel.CENTER);
		subPanelInsProv.add(nuevoProv);
		
		//caja codigo nuevo proveedor
		insertCodProv = new JTextField();//Creamos el componente
		TextPrompt placeholder = new TextPrompt("supplier's code", insertCodProv);
	    placeholder.changeAlpha(0.75f);
	    placeholder.changeStyle(Font.ITALIC);
	    insertCodProv.setBounds(40,90,200,30);//Posicionamos
	    insertCodProv.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertCodProv.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertCodProv.setBackground(Color.WHITE); //Color de fondo
	    insertCodProv.setForeground(color_azul);//Color del texto
	    subPanelInsProv.add(insertCodProv);//Anadimos
	    
	    //caja nombre nuevo proveedor
	    insertNomProv = new JTextField();//Creamos el componente
		TextPrompt placeholder1 = new TextPrompt("supplier's name", insertNomProv);
	    placeholder1.changeAlpha(0.75f);
	    placeholder1.changeStyle(Font.ITALIC);
	    insertNomProv.setBounds(250,90,430,30);//Posicionamos
	    insertNomProv.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertNomProv.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertNomProv.setBackground(Color.WHITE); //Color de fondo
	    insertNomProv.setForeground(color_azul);//Color del texto
	    subPanelInsProv.add(insertNomProv);//Anadimos
		
	    //caja mail nuevo proveedor
		insertContProv = new JTextField();//Creamos el componente
		TextPrompt placeholder2 = new TextPrompt("contact", insertContProv);
	    placeholder2.changeAlpha(0.75f);
	    placeholder2.changeStyle(Font.ITALIC);
	    insertContProv.setBounds(40,150,400,30);//Posicionamos
	    insertContProv.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertContProv.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertContProv.setBackground(Color.WHITE); //Color de fondo
	    insertContProv.setForeground(color_azul);//Color del texto
	    subPanelInsProv.add(insertContProv);//Anadimos
	    
	    //boton insertar
	    Image imgBotonInsertProvOk = new ImageIcon("img\\insert.png").getImage();
		botonInsertProvOk = new JButton(new ImageIcon(imgBotonInsertProvOk.getScaledInstance(110,42, Image.SCALE_SMOOTH)));//Creamos el componente
		botonInsertProvOk.setBounds(550,140,110,42);
		botonInsertProvOk.setBorder(null); //Eliminamos el borde
		botonInsertProvOk.setBackground(new Color(186,236,247));
		subPanelInsProv.add(botonInsertProvOk);//Anadimos 
		
		//resultado insercion
		resulInsertProv = new JLabel("");//Creamos el componente
		resulInsertProv.setBounds(100,200,500,30);//Posicionamos
		resulInsertProv.setBorder(null); //Eliminamos el borde
		resulInsertProv.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		resulInsertProv.setForeground(new Color(000,000,000));//Color del texto
		subPanelInsProv.add(resulInsertProv);//Anadimos	
		
		//SUBPANEL EDITAR PROVEEDOR
		
		subPanelEditProv = new JPanel();
		subPanelEditProv.setBounds(200, 270, 750, 268);
		subPanelEditProv.setBackground(color_panel);
		subPanelEditProv.setLayout(null);
		add(subPanelEditProv);
		subPanelEditProv.setVisible(false);	
		
		editProv = new JLabel("update supplier");
		editProv.setBounds(20, 0, 710, 60);
		editProv.setBorder(null);
		editProv.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		editProv.setForeground(color_azul);//Color del texto
		editProv.setHorizontalAlignment(JLabel.CENTER);
		editProv.setVerticalAlignment(JLabel.CENTER);
		subPanelEditProv.add(editProv);
		
		//caja cod proveedor a editar
		insertCODUpdateProv = new JTextField();//Creamos el componente
		TextPrompt placeholder8 = new TextPrompt("Insert supplier's code", insertCODUpdateProv);
	    placeholder8.changeAlpha(0.75f);
	    placeholder8.changeStyle(Font.ITALIC);
	    insertCODUpdateProv.setBounds(20,70,200,30);//Posicionamos		
	    insertCODUpdateProv.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertCODUpdateProv.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
	    insertCODUpdateProv.setBackground(Color.WHITE); //Color de fondo
	    insertCODUpdateProv.setForeground(color_azul);//Color del texto
	    subPanelEditProv.add(insertCODUpdateProv);//Anadimos
	    
	    labelPreguntaCambioProv = new JLabel("What do you want to change?");
	    labelPreguntaCambioProv.setBounds(20, 120, 300, 30);
	    labelPreguntaCambioProv.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
	    labelPreguntaCambioProv.setForeground(color_azul);//Color del texto
	    subPanelEditProv.add(labelPreguntaCambioProv);//Anadimos
	    
	    //combo para elegir que campo editar
	    comboUpdateProv = new JComboBox<String>();
	    comboUpdateProv.addItem("codproveedor");
	    comboUpdateProv.addItem("nombreProveedor");
	    comboUpdateProv.addItem("mail");
	    comboUpdateProv.setBounds(340, 120, 300, 30);
	    subPanelEditProv.add(comboUpdateProv);

		labelNewDataProv = new JLabel("Insert new data");
		labelNewDataProv.setBounds(20, 170, 300, 30);
		labelNewDataProv.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
		labelNewDataProv.setForeground(color_azul);//Color del texto
		subPanelEditProv.add(labelNewDataProv);//Anadimos
		
		//nueva info
		insertNewDataProv = new JTextField();//Creamos el componente
		TextPrompt placeholder9 = new TextPrompt("Insert new data", insertNewDataProv);
	    placeholder9.changeAlpha(0.75f);
	    placeholder9.changeStyle(Font.ITALIC);
	    insertNewDataProv.setBounds(340,170,300,30);//Posicionamos		
	    insertNewDataProv.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertNewDataProv.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
	    insertNewDataProv.setBackground(Color.WHITE); //Color de fondo
	    insertNewDataProv.setForeground(color_azul);//Color del texto
	    subPanelEditProv.add(insertNewDataProv);//Anadimos
	    
	    //boton actualizar
	    Image imgBotonUpdateFinalPr = new ImageIcon("img\\update.png").getImage();
		botonUpdateFinalPr = new JButton(new ImageIcon(imgBotonUpdateFinalPr.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonUpdateFinalPr.setBounds(20,220,110,42);
		botonUpdateFinalPr.setBackground(new Color(186,236,247));
		botonUpdateFinalPr.setBorder(null); //Eliminamos el borde
		subPanelEditProv.add(botonUpdateFinalPr);//Anadimos 
		
		//resultado de la actualizacion
		resultUpdateProv = new JLabel();//Creamos el componente
		resultUpdateProv.setBounds(150,222,500,30);//Posicionamos
		resultUpdateProv.setBorder(null); //Eliminamos el borde
		resultUpdateProv.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		resultUpdateProv.setForeground(color_azul);//Color del texto
		subPanelEditProv.add(resultUpdateProv);//Anadimos

		
		//SUBPANEL ELIMINAR PROVEEDOR
		
		subPanelElimProv = new JPanel();
		subPanelElimProv.setBounds(200, 270, 750, 268);
		subPanelElimProv.setBackground(color_panel);
		subPanelElimProv.setLayout(null);
		add(subPanelElimProv);
		subPanelElimProv.setVisible(false);	
		
		elimProv = new JLabel("delete supplier");
		elimProv.setBounds(20, 0, 710, 60);
		elimProv.setBorder(null);
		elimProv.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		elimProv.setForeground(color_azul);//Color del texto
		elimProv.setHorizontalAlignment(JLabel.CENTER);
		elimProv.setVerticalAlignment(JLabel.CENTER);
		subPanelElimProv.add(elimProv);
		
		//cod proveedor a eliminar
		insertCODDeleteProv = new JTextField();//Creamos el componente
		TextPrompt placeholder11 = new TextPrompt("Insert supplier's code", insertCODDeleteProv);
	    placeholder11.changeAlpha(0.75f);
	    placeholder11.changeStyle(Font.ITALIC);
	    insertCODDeleteProv.setBounds(20,70,200,30);//Posicionamos		
	    insertCODDeleteProv.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertCODDeleteProv.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
	    insertCODDeleteProv.setBackground(Color.WHITE); //Color de fondo
	    insertCODDeleteProv.setForeground(color_azul);//Color del texto
	    subPanelElimProv.add(insertCODDeleteProv);//Anadimos
		
	    //boton busqueda
	    Image imgBotonSearchProv = new ImageIcon("img\\search.png").getImage();
		botonSearchProv = new JButton(new ImageIcon(imgBotonSearchProv.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonSearchProv.setBounds(20,130,110,42);
		botonSearchProv.setBackground(new Color(186,236,247));
		botonSearchProv.setBorder(null); //Eliminamos el borde
		subPanelElimProv.add(botonSearchProv);//Anadimos 
		
		//resultado busqueda
		resulBusquedaProv = new JLabel();//Creamos el componente
		resulBusquedaProv.setBounds(150,130,500,30);//Posicionamos
		resulBusquedaProv.setBorder(null); //Eliminamos el borde
		resulBusquedaProv.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		resulBusquedaProv.setForeground(color_azul);//Color del texto
		subPanelElimProv.add(resulBusquedaProv);//Anadimos
		
		//boton borrar el proveedor seleccionado
		Image imgBotonDeleteProvFinal = new ImageIcon("img\\delete.png").getImage();
		botonDeleteProvFinal = new JButton(new ImageIcon(imgBotonDeleteProvFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonDeleteProvFinal.setBounds(20,190,110,42);
		botonDeleteProvFinal.setBackground(new Color(186,236,247));
		botonDeleteProvFinal.setBorder(null); //Eliminamos el borde
		subPanelElimProv.add(botonDeleteProvFinal);//Anadimos 
		botonDeleteProvFinal.setVisible(false);
		
		//resultado del borrado
		resulDeleteProv = new JLabel();//Creamos el componente
		resulDeleteProv.setBounds(150,190,500,30);//Posicionamos
		resulDeleteProv.setBorder(null); //Eliminamos el borde
		resulDeleteProv.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		resulDeleteProv.setForeground(color_azul);//Color del texto
		subPanelEmpDelete.add(resulDeleteProv);//Anadimos	
		
		//exportar proveedores
		subPanelProvExport = new JPanel();
		subPanelProvExport.setBounds(200, 270, 750, 268);
		subPanelProvExport.setBackground(color_panel);
		subPanelProvExport.setLayout(null);
		add(subPanelProvExport);
		subPanelProvExport.setVisible(false);
		
		exportProveedor = new JLabel("export suppliers to file");
		exportProveedor.setBounds(20, 0, 710, 60);
		exportProveedor.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		exportProveedor.setForeground(color_azul);//Color del texto
		exportProveedor.setHorizontalAlignment(JLabel.CENTER);
		exportProveedor.setVerticalAlignment(JLabel.CENTER);
		subPanelProvExport.add(exportProveedor);//Anadimos al panel
		
		insertUsuarioPCProveedor = new JTextField();//Creamos el componente
		TextPrompt placeholder12 = new TextPrompt("Insert user PC. Ej. ejemplo", insertUsuarioPCProveedor);
		placeholder12.changeAlpha(0.75f);
		placeholder12.changeStyle(Font.ITALIC);
	    insertUsuarioPCProveedor.setBounds(20,70,200,30);//Posicionamos		
	    insertUsuarioPCProveedor.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertUsuarioPCProveedor.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
	    insertUsuarioPCProveedor.setBackground(Color.WHITE); //Color de fondo
	    insertUsuarioPCProveedor.setForeground(color_azul);//Color del texto
	    subPanelProvExport.add(insertUsuarioPCProveedor);//Anadimos	
		
		insertRutaExportProv = new JLabel("You'll find the file in C:\\Users\\youruser. Name of file: proveedores.csv ");//Creamos el componente
	    insertRutaExportProv.setBounds(20,120,670,30);//Posicionamos		
	    insertRutaExportProv.setBorder(null); //Eliminamos el borde
	    insertRutaExportProv.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
	    insertRutaExportProv.setForeground(color_azul);//Color del texto
	    subPanelProvExport.add(insertRutaExportProv);//Anadimos
	    
	    Image imgBotonExportProvFinal = new ImageIcon("img\\export to file.png").getImage();
		botonExportProvFinal = new JButton(new ImageIcon(imgBotonExportProvFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		botonExportProvFinal.setBounds(20,180,160,42);
		botonExportProvFinal.setBackground(new Color(186,236,247));
		botonExportProvFinal.setBorder(null); //Eliminamos el borde
		subPanelProvExport.add(botonExportProvFinal);//Anadimos 
				
	    resulExportProv = new JLabel();//Creamos el componente
	    resulExportProv.setBounds(200,180,500,30);//Posicionamos
	    resulExportProv.setBorder(null); //Eliminamos el borde
	    resulExportProv.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    resulExportProv.setForeground(Color.GRAY);//Color del texto
	    subPanelProvExport.add(resulExportProv);//Anadimos	
	    
		//Panel botones PROVEEDORES
	    
		panelBotonesProv = new JPanel();
		panelBotonesProv.setBounds(200, 538, 750, 82);
		panelBotonesProv.setBackground(color_panel);
		panelBotonesProv.setLayout(null);
		add(panelBotonesProv);
		panelBotonesProv.setVisible(false);		
	    
		Image imgbotonActualizarProveedor = new ImageIcon("img\\update supplier.png").getImage();
		botonActualizarProveedor = new JButton(new ImageIcon(imgbotonActualizarProveedor.getScaledInstance(160,42, Image.SCALE_SMOOTH)));//Creamos el componente
		botonActualizarProveedor.setBounds((int) 67.5, 20, 160, 42);
		botonActualizarProveedor.setBorder(null); //Eliminamos el borde
		botonActualizarProveedor.setBackground(color_panel);
		panelBotonesProv.add(botonActualizarProveedor);//Anadimos 
		
		Image imgbotonBorrarProveedor = new ImageIcon("img\\delete supplier.png").getImage();
		botonBorrarProveedor = new JButton(new ImageIcon(imgbotonBorrarProveedor.getScaledInstance(160,42, Image.SCALE_SMOOTH)));//Creamos el componente
		botonBorrarProveedor.setBounds(295, 20, 160, 42);
		botonBorrarProveedor.setBorder(null); //Eliminamos el borde
		botonBorrarProveedor.setBackground(color_panel);
		panelBotonesProv.add(botonBorrarProveedor);//Anadimos 
		
		Image imgbotonExportProveedor = new ImageIcon("img\\export to file.png").getImage();
		botonExportProveedor = new JButton(new ImageIcon(imgbotonExportProveedor.getScaledInstance(160,42, Image.SCALE_SMOOTH)));//Creamos el componente
		botonExportProveedor.setBounds((int) 522.5, 20, 160, 42);
		botonExportProveedor.setBorder(null); //Eliminamos el borde
		botonExportProveedor.setBackground(color_panel);
		panelBotonesProv.add(botonExportProveedor);//Anadimos 

	}
	
	//PANEL CLIENTES
	private void panelClientes() {
		panelClientes = new JPanel();
		panelClientes.setBackground(color_panel);
		panelClientes.setBounds(200, 40, 750, 230);
		panelClientes.setLayout(null);
		add(panelClientes);
		panelClientes.setVisible(false);
		
		barraClientes = new JScrollPane();
		barraClientes.setBounds(20, 20, 710, 190);
		panelClientes.add(barraClientes);
		
		String titulosClientes[] = {"DNI Cliente", "Nombre Cliente", "Email", "Telï¿½fono"};
		String infoClientes[][] = AccesoDB.obtenerMatrizClientes();
		
		tablaClientes = new JTable(infoClientes,titulosClientes);
		tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaClientes.getColumnModel().getColumn(1).setPreferredWidth(115);
		tablaClientes.getColumnModel().getColumn(2).setPreferredWidth(140);
		barraClientes.setViewportView(tablaClientes);
		
		//SUBPANEL INSERTAR CLIENTE
		
		subPanelInsCliente = new JPanel();
		subPanelInsCliente.setBounds(200, 270, 750, 268);
		subPanelInsCliente.setBackground(color_panel);
		subPanelInsCliente.setLayout(null);
		add(subPanelInsCliente);
		subPanelInsCliente.setVisible(false);	
				
		nuevoCliente = new JLabel("new customer");
		nuevoCliente.setBounds(20, 0, 710, 60);
		nuevoCliente.setBorder(null);
		nuevoCliente.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		nuevoCliente.setForeground(color_azul);//Color del texto
		nuevoCliente.setHorizontalAlignment(JLabel.CENTER);
		nuevoCliente.setVerticalAlignment(JLabel.CENTER);
		subPanelInsCliente.add(nuevoCliente);
		
		//caja codigo nuevo cliente
		insertNIFCliente = new JTextField();//Creamos el componente
		TextPrompt placeholder = new TextPrompt("customer's code", insertNIFCliente);
	    placeholder.changeAlpha(0.75f);
	    placeholder.changeStyle(Font.ITALIC);
	    insertNIFCliente.setBounds(40,90,200,30);//Posicionamos
	    insertNIFCliente.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertNIFCliente.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertNIFCliente.setBackground(Color.WHITE); //Color de fondo
	    insertNIFCliente.setForeground(color_azul);//Color del texto
	    subPanelInsCliente.add(insertNIFCliente);//Anadimos
				
		  //caja nombre nuevo cliente
	    insertNomCliente = new JTextField();//Creamos el componente
		TextPrompt placeholder1 = new TextPrompt("customer's name", insertNomCliente);
	    placeholder1.changeAlpha(0.75f);
	    placeholder1.changeStyle(Font.ITALIC);
	    insertNomCliente.setBounds(250,90,430,30);//Posicionamos
	    insertNomCliente.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertNomCliente.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertNomCliente.setBackground(Color.WHITE); //Color de fondo
	    insertNomCliente.setForeground(color_azul);//Color del texto
	    subPanelInsCliente.add(insertNomCliente);//Anadimos
				
			//caja telefono nuevo cliente
	    insertTelCliente = new JTextField();//Creamos el componente
		TextPrompt placeholder2 = new TextPrompt("email", insertTelCliente);
	    placeholder2.changeAlpha(0.75f);
	    placeholder2.changeStyle(Font.ITALIC);
	    insertTelCliente.setBounds(40,130,400,30);//Posicionamos
	    insertTelCliente.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertTelCliente.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertTelCliente.setBackground(Color.WHITE); //Color de fondo
	    insertTelCliente.setForeground(color_azul);//Color del texto
	    subPanelInsCliente.add(insertTelCliente);//Anadimos
	    
	    //insertMailCliente
	    insertMailCliente = new JTextField();//Creamos el componente
		TextPrompt placeholder3 = new TextPrompt("phone", insertMailCliente);
	    placeholder3.changeAlpha(0.75f);
	    placeholder3.changeStyle(Font.ITALIC);
	    insertMailCliente.setBounds(40,170,120,30);//Posicionamos
	    insertMailCliente.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertMailCliente.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertMailCliente.setBackground(Color.WHITE); //Color de fondo
	    insertMailCliente.setForeground(color_azul);//Color del texto
	    subPanelInsCliente.add(insertMailCliente);//Anadimos
		
		//boton insertar
	    Image imgBotonInsertClienteOk = new ImageIcon("img\\insert.png").getImage();
		botonInsertClienteok = new JButton(new ImageIcon(imgBotonInsertClienteOk.getScaledInstance(110,42, Image.SCALE_SMOOTH)));//Creamos el componente
		botonInsertClienteok.setBounds(550,140,110,42);
		botonInsertClienteok.setBorder(null); //Eliminamos el borde
		botonInsertClienteok.setBackground(new Color(186,236,247));
		subPanelInsCliente.add(botonInsertClienteok);//Anadimos 
				
		//resultado insercion
		resulInsertCliente = new JLabel("");//Creamos el componente
		resulInsertCliente.setBounds(100,200,500,30);//Posicionamos
		resulInsertCliente.setBorder(null); //Eliminamos el borde
		resulInsertCliente.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		resulInsertCliente.setForeground(new Color(000,000,000));//Color del texto
		subPanelInsCliente.add(resulInsertCliente);//Anadimos			
		
		//SUBPANEL EDITAR CLIENTE
		
		subPanelEditCliente = new JPanel();
		subPanelEditCliente.setBounds(200, 270, 750, 268);
		subPanelEditCliente.setBackground(color_panel);
		subPanelEditCliente.setLayout(null);
		add(subPanelEditCliente);
		subPanelEditCliente.setVisible(false);	
		
		editCliente = new JLabel("update supplier");
		editCliente.setBounds(20, 0, 710, 60);
		editCliente.setBorder(null);
		editCliente.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		editCliente.setForeground(color_azul);//Color del texto
		editCliente.setHorizontalAlignment(JLabel.CENTER);
		editCliente.setVerticalAlignment(JLabel.CENTER);
		subPanelEditCliente.add(editCliente);
		
		//caja nif cliente editar
		insertNIFUpdateCliente = new JTextField();//Creamos el componente
		TextPrompt placeholder8 = new TextPrompt("Insert customer's NIF", insertNIFUpdateCliente);
	    placeholder8.changeAlpha(0.75f);
	    placeholder8.changeStyle(Font.ITALIC);
	    insertNIFUpdateCliente.setBounds(20,70,200,30);//Posicionamos		
	    insertNIFUpdateCliente.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertNIFUpdateCliente.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
	    insertNIFUpdateCliente.setBackground(Color.WHITE); //Color de fondo
	    insertNIFUpdateCliente.setForeground(color_azul);//Color del texto
	    subPanelEditCliente.add(insertNIFUpdateCliente);//Anadimos
	    
	    labelPreguntaCambioCliente = new JLabel("What do you want to change?");
	    labelPreguntaCambioCliente.setBounds(20, 120, 300, 30);
	    labelPreguntaCambioCliente.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
	    labelPreguntaCambioCliente.setForeground(color_azul);//Color del texto
	    subPanelEditCliente.add(labelPreguntaCambioCliente);//Anadimos
		
		//combo para elegir que campo editar
	    comboUpdateCliente = new JComboBox<String>();
	    comboUpdateCliente.addItem("dni_Cliente");
	    comboUpdateCliente.addItem("nombre");
		comboUpdateCliente.addItem("telefono");
	    comboUpdateCliente.addItem("email");
	    comboUpdateCliente.setBounds(340, 120, 300, 30);
	    subPanelEditCliente.add(comboUpdateCliente);

		labelNewDataCliente = new JLabel("Insert new data");
		labelNewDataCliente.setBounds(20, 170, 300, 30);
		labelNewDataCliente.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
		labelNewDataCliente.setForeground(color_azul);//Color del texto
		subPanelEditCliente.add(labelNewDataCliente);//Anadimos
		
		//nueva info
		insertNewDataCliente = new JTextField();//Creamos el componente
		TextPrompt placeholder9 = new TextPrompt("Insert new data", insertNewDataCliente);
	    placeholder9.changeAlpha(0.75f);
	    placeholder9.changeStyle(Font.ITALIC);
	    insertNewDataCliente.setBounds(340,170,300,30);//Posicionamos		
	    insertNewDataCliente.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertNewDataCliente.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
	    insertNewDataCliente.setBackground(Color.WHITE); //Color de fondo
	    insertNewDataCliente.setForeground(color_azul);//Color del texto
	    subPanelEditCliente.add(insertNewDataCliente);//Anadimos
		
		//boton actualizar
	    Image imgBotonUpdateFinalCl = new ImageIcon("img\\update.png").getImage();
		botonUpdateFinalCl = new JButton(new ImageIcon(imgBotonUpdateFinalCl.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonUpdateFinalCl.setBounds(20,220,110,42);
		botonUpdateFinalCl.setBackground(new Color(186,236,247));
		botonUpdateFinalCl.setBorder(null); //Eliminamos el borde
		subPanelEditCliente.add(botonUpdateFinalCl);//Anadimos 
		
		//resultado de la actualizacion
		resultUpdateCliente = new JLabel();//Creamos el componente
		resultUpdateCliente.setBounds(150,222,500,30);//Posicionamos
		resultUpdateCliente.setBorder(null); //Eliminamos el borde
		resultUpdateCliente.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		resultUpdateCliente.setForeground(color_azul);//Color del texto
		subPanelEditCliente.add(resultUpdateCliente);//Anadimos
		
		//SUBPANEL ELIMINAR CLIENTE
		
		subPanelElimCliente = new JPanel();
		subPanelElimCliente.setBounds(200, 270, 750, 268);
		subPanelElimCliente.setBackground(color_panel);
		subPanelElimCliente.setLayout(null);
		add(subPanelElimCliente);
		subPanelElimCliente.setVisible(false);	
		
		elimCliente = new JLabel("delete customer");
		elimCliente.setBounds(20, 0, 710, 60);
		elimCliente.setBorder(null);
		elimCliente.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		elimCliente.setForeground(color_azul);//Color del texto
		elimCliente.setHorizontalAlignment(JLabel.CENTER);
		elimCliente.setVerticalAlignment(JLabel.CENTER);
		subPanelElimCliente.add(elimCliente);
		
		//nif cliente a eliminar
		insertNIFDeleteCli = new JTextField();//Creamos el componente
		TextPrompt placeholder11 = new TextPrompt("Insert customer's NIF", insertNIFDeleteCli);
	    placeholder11.changeAlpha(0.75f);
	    placeholder11.changeStyle(Font.ITALIC);
	    insertNIFDeleteCli.setBounds(20,70,200,30);//Posicionamos		
	    insertNIFDeleteCli.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertNIFDeleteCli.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
	    insertNIFDeleteCli.setBackground(Color.WHITE); //Color de fondo
	    insertNIFDeleteCli.setForeground(color_azul);//Color del texto
	    subPanelElimCliente.add(insertNIFDeleteCli);//Anadimos
		
		//boton busqueda
	    Image imgBotonSearchCli = new ImageIcon("img\\search.png").getImage();
		botonSearchCliente = new JButton(new ImageIcon(imgBotonSearchCli.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonSearchCliente.setBounds(20,130,110,42);
		botonSearchCliente.setBackground(new Color(186,236,247));
		botonSearchCliente.setBorder(null); //Eliminamos el borde
		subPanelElimCliente.add(botonSearchCliente);//Anadimos 
		
		//resultado busqueda
		resulBusquedaCli = new JLabel();//Creamos el componente
		resulBusquedaCli.setBounds(150,130,500,30);//Posicionamos
		resulBusquedaCli.setBorder(null); //Eliminamos el borde
		resulBusquedaCli.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		resulBusquedaCli.setForeground(color_azul);//Color del texto
		subPanelElimCliente.add(resulBusquedaCli);//Anadimos
		
		//boton borrar el cliente seleccionado
		Image imgBotonDeleteClienteFinal = new ImageIcon("img\\delete.png").getImage();
		botonDeleteClienteFinal = new JButton(new ImageIcon(imgBotonDeleteClienteFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonDeleteClienteFinal.setBounds(20,190,110,42);
		botonDeleteClienteFinal.setBackground(new Color(186,236,247));
		botonDeleteClienteFinal.setBorder(null); //Eliminamos el borde
		subPanelElimCliente.add(botonDeleteClienteFinal);//Anadimos 
		botonDeleteClienteFinal.setVisible(false);
		
		//resultado del borrado
		resulDeleteCliente = new JLabel();//Creamos el componente
		resulDeleteCliente.setBounds(150,190,500,30);//Posicionamos
		resulDeleteCliente.setBorder(null); //Eliminamos el borde
		resulDeleteCliente.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		resulDeleteCliente.setForeground(color_azul);//Color del texto
		subPanelElimCliente.add(resulDeleteCliente);//Anadimos	
		
		//Panel botones CLIENTES
	    
		panelBotonesCliente = new JPanel();
		panelBotonesCliente.setBounds(200, 538, 750, 82);
		panelBotonesCliente.setBackground(color_panel);
		panelBotonesCliente.setLayout(null);
		add(panelBotonesCliente);
		panelBotonesCliente.setVisible(false);		
	    
		//cambiar imagen boton
		Image imgbotonActualizarCliente = new ImageIcon("img\\update customer.png").getImage();
		botonActualizarCliente = new JButton(new ImageIcon(imgbotonActualizarCliente.getScaledInstance(160,42, Image.SCALE_SMOOTH)));//Creamos el componente
		botonActualizarCliente.setBounds((int) 67.5, 20, 160, 42);
		botonActualizarCliente.setBorder(null); //Eliminamos el borde
		botonActualizarCliente.setBackground(color_panel);
		panelBotonesCliente.add(botonActualizarCliente);//Anadimos 
		
		//cambiar imagen boton
		Image imgbotonBorrarCliente = new ImageIcon("img\\delete customer.png").getImage();
		botonBorrarCliente = new JButton(new ImageIcon(imgbotonBorrarCliente.getScaledInstance(160,42, Image.SCALE_SMOOTH)));//Creamos el componente
		botonBorrarCliente.setBounds(295, 20, 160, 42);
		botonBorrarCliente.setBorder(null); //Eliminamos el borde
		botonBorrarCliente.setBackground(color_panel);
		panelBotonesCliente.add(botonBorrarCliente);//Anadimos 
		
		//cambiar imagen boton
		Image imgbotonBills = new ImageIcon("img\\bills.png").getImage();
		botonBills = new JButton(new ImageIcon(imgbotonBills.getScaledInstance(160,42, Image.SCALE_SMOOTH)));//Creamos el componente
		botonBills.setBounds((int) 522.5, 20, 160, 42);
		botonBills.setBorder(null); //Eliminamos el borde
		botonBills.setBackground(color_panel);
		panelBotonesCliente.add(botonBills);//Anadimos 

	}
	
	//PANEL SERVICIOS
	private void panelCRM() {
		
		panelCRM = new JPanel();
		panelCRM.setBackground(color_panel);
		panelCRM.setBounds(200, 40, 750, 580);
		panelCRM.setLayout(null);
		add(panelCRM);
		panelCRM.setVisible(false);
		
		barraCRM = new JScrollPane();
		barraCRM.setBounds(20, 20, 710, 190);
		panelCRM.add(barraCRM);
		
		String titulosClientes[] = {"Name", "Email", "Phone Number", "Deco Wifi Pack", "wifi Signal Expansion", 
				"Smart plugs Pack", "Smart_Bulbs_Pack", "wifi Surveillance Cameras"};
		String infoClientes[][] = AccesoDB.obtenerMatrizCRM();
		
		tablaCRM = new JTable(infoClientes,titulosClientes);
		barraCRM.setViewportView(tablaCRM);	
				
		ColorearFilas colorear = new ColorearFilas();
		tablaCRM.setDefaultRenderer (Object.class, colorear);
					
	}

	//PANEL ALMACEN
	private void panelAlmacen() {
		panelAlmacen = new JPanel();
		panelAlmacen.setBackground(color_panel);
		panelAlmacen.setBounds(200, 40, 750, 580);
		panelAlmacen.setLayout(null);
		add(panelAlmacen);
		panelAlmacen.setVisible(false);
		
		detalleAlmacen = new JLabel("detalle almacï¿½n");
		detalleAlmacen.setBounds(20, 0, 710, 60);
		detalleAlmacen.setFont(new Font("Segoe UI",Font.BOLD,30));//Damos formato al contenido
		detalleAlmacen.setForeground(color_azul);//Color del texto
		detalleAlmacen.setHorizontalAlignment(JLabel.CENTER);
		detalleAlmacen.setVerticalAlignment(JLabel.CENTER);
		panelAlmacen.add(detalleAlmacen);//Anadimos
		
		barraStock = new JScrollPane();
		barraStock.setBounds(20, 55, 710, 190);
		panelAlmacen.add(barraStock);
		
		String titulosAlmacen[] = {"Cï¿½digo Producto", "Nombre Producto", "Unidades compradas", "Unidades Vendidas", "Total"};
		String infoAlmacen[][] = AccesoDB.obtenerMatrizStock();
		
		tablaAlmacen = new JTable(infoAlmacen,titulosAlmacen);
		tablaAlmacen.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaAlmacen.getColumnModel().getColumn(1).setPreferredWidth(115);
		tablaAlmacen.getColumnModel().getColumn(2).setPreferredWidth(140);
		barraStock.setViewportView(tablaAlmacen);

		Image imgbotonExportStock = new ImageIcon("img\\export to file.png").getImage();
		botonExportStock = new JButton(new ImageIcon(imgbotonExportStock.getScaledInstance(160,42, Image.SCALE_SMOOTH)));//Creamos el componente
		botonExportStock.setBounds(295, 255, 160, 42);
		botonExportStock.setBorder(null); //Eliminamos el borde
		botonExportStock.setBackground(color_panel);
		panelAlmacen.add(botonExportStock);//Anadimos 
		
		resumenAlmacen = new JLabel("resumen almacï¿½n");
		resumenAlmacen.setBounds(20, 310, 710, 60);
		resumenAlmacen.setFont(new Font("Segoe UI",Font.BOLD,30));//Damos formato al contenido
		resumenAlmacen.setForeground(color_azul);//Color del texto
		resumenAlmacen.setHorizontalAlignment(JLabel.CENTER);
		resumenAlmacen.setVerticalAlignment(JLabel.CENTER);
		panelAlmacen.add(resumenAlmacen);//Anadimos
		
		barraStock2 = new JScrollPane();
		barraStock2.setBounds(20, 365, 710, 150);
		panelAlmacen.add(barraStock2);
		
		String titulosAlmacen2[] = {"Cï¿½digo Producto", "Nombre Producto", "Unidades compradas", "Unidades Vendidas", "Total"};
		String infoAlmacen2[][] = AccesoDB.obtenerMatrizStockResumen();
		
		tablaAlmacen2 = new JTable(infoAlmacen2,titulosAlmacen2);
		tablaAlmacen2.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaAlmacen2.getColumnModel().getColumn(1).setPreferredWidth(115);
		tablaAlmacen2.getColumnModel().getColumn(2).setPreferredWidth(140);
		barraStock2.setViewportView(tablaAlmacen2);
		
		subPanelAlmacenExport = new JPanel();
		subPanelAlmacenExport.setBounds(200, 40, 750, 580);
		subPanelAlmacenExport.setBackground(color_panel);
		subPanelAlmacenExport.setLayout(null);
		add(subPanelAlmacenExport);
		subPanelAlmacenExport.setVisible(false);
		
		exportResAlm = new JLabel("export stock resume to file");
		exportResAlm.setBounds(20, 0, 710, 60);
		exportResAlm.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		exportResAlm.setForeground(color_azul);//Color del texto
		exportResAlm.setHorizontalAlignment(JLabel.CENTER);
		exportResAlm.setVerticalAlignment(JLabel.CENTER);
		subPanelAlmacenExport.add(exportResAlm);//Anadimos al panel
		
		insertUsuarioPCAlmacen = new JTextField();//Creamos el componente
		TextPrompt placeholder12 = new TextPrompt("Insert user PC. Ej. ejemplo", insertUsuarioPCAlmacen);
		placeholder12.changeAlpha(0.75f);
		placeholder12.changeStyle(Font.ITALIC);
		insertUsuarioPCAlmacen.setBounds(20,70,200,30);//Posicionamos		
		insertUsuarioPCAlmacen.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
		insertUsuarioPCAlmacen.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		insertUsuarioPCAlmacen.setBackground(Color.WHITE); //Color de fondo
		insertUsuarioPCAlmacen.setForeground(color_azul);//Color del texto
	    subPanelAlmacenExport.add(insertUsuarioPCAlmacen);//Anadimos	
		
		insertRutaExportAlm = new JLabel("You'll find the file in C:\\Users\\youruser. Name of file: almacen.csv ");//Creamos el componente
	    insertRutaExportAlm.setBounds(20,120,670,30);//Posicionamos		
	    insertRutaExportAlm.setBorder(null); //Eliminamos el borde
	    insertRutaExportAlm.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
	    insertRutaExportAlm.setForeground(color_azul);//Color del texto
	    subPanelAlmacenExport.add(insertRutaExportAlm);//Anadimos
	    
	    Image imgBotonExportAlmFinal = new ImageIcon("img\\export to file.png").getImage();
		botonExportAlmFinal = new JButton(new ImageIcon(imgBotonExportAlmFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		botonExportAlmFinal.setBounds(20,180,160,42);
		botonExportAlmFinal.setBackground(new Color(186,236,247));
		botonExportAlmFinal.setBorder(null); //Eliminamos el borde
		subPanelAlmacenExport.add(botonExportAlmFinal);//Anadimos 
				
	    resulExportAlm = new JLabel();//Creamos el componente
	    resulExportAlm.setBounds(200,180,500,30);//Posicionamos
	    resulExportAlm.setBorder(null); //Eliminamos el borde
	    resulExportAlm.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    resulExportAlm.setForeground(Color.GRAY);//Color del texto
	    subPanelAlmacenExport.add( resulExportAlm);//Anadimos			

	}

	//PANEL EMPLEADO
	private void panelEmpleado() {
		
		//PANEL TABLA EMPLEADO
		
		panelEmpleado = new JPanel();
		panelEmpleado.setBackground(color_panel);
		panelEmpleado.setBounds(200, 40, 750, 230);
		panelEmpleado.setLayout(null);
		add(panelEmpleado);
		panelEmpleado.setVisible(false);		
		
		//Construimos la tabla empleados
		
		barraEmpleados = new JScrollPane();
		barraEmpleados.setBounds(20, 20, 710, 190);
		panelEmpleado.add(barraEmpleados);
		
		String titulosEmpleados[] = {"Nombre", "Apellidos", "e-mail", "NIF", "Telefono"};
		String infoEmpleados[][] = AccesoDB.obtenerMatrizEmpleados();
		
		tablaEmpleados = new JTable(infoEmpleados,titulosEmpleados);
		tablaEmpleados.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaEmpleados.getColumnModel().getColumn(1).setPreferredWidth(115);
		tablaEmpleados.getColumnModel().getColumn(2).setPreferredWidth(140);
		barraEmpleados.setViewportView(tablaEmpleados);
			
		//SUBPANEL INSERTAR EMPLEADO - Este sub-panel va cambiando en funciï¿½n de los botones que se vayan pulsando
		
		subPanelEmpInsertar = new JPanel();
		subPanelEmpInsertar.setBounds(200, 270, 750, 268);
		subPanelEmpInsertar.setBackground(color_panel);
		subPanelEmpInsertar.setLayout(null);
		add(subPanelEmpInsertar);
		subPanelEmpInsertar.setVisible(false);	
		
		//CAMPOS DEL SUBPANEL INSERTAR EMPLEADO
		
		nuevoEmpleado = new JLabel("new employee");
		nuevoEmpleado.setBounds(20, 0, 710, 60);
		nuevoEmpleado.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		nuevoEmpleado.setForeground(color_azul);//Color del texto
		nuevoEmpleado.setHorizontalAlignment(JLabel.CENTER);
		nuevoEmpleado.setVerticalAlignment(JLabel.CENTER);
		subPanelEmpInsertar.add(nuevoEmpleado);//Anadimos
		
		insertNomEmpl = new JTextField();//Creamos el componente
		TextPrompt placeholder = new TextPrompt("Employee's name", insertNomEmpl);
	    placeholder.changeAlpha(0.75f);
	    placeholder.changeStyle(Font.ITALIC);
		insertNomEmpl.setBounds(20,70,200,30);//Posicionamos		
		insertNomEmpl.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
		insertNomEmpl.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		insertNomEmpl.setBackground(Color.WHITE); //Color de fondo
		insertNomEmpl.setForeground(color_azul);//Color del texto
		subPanelEmpInsertar.add(insertNomEmpl);//Anadimos
		
		insertApelEmpl = new JTextField();//Creamos el componente
		TextPrompt placeholder1 = new TextPrompt("Employee's last name", insertApelEmpl);
	    placeholder1.changeAlpha(0.75f);
	    placeholder1.changeStyle(Font.ITALIC);
	    insertApelEmpl.setBounds(250,70,480,30);//Posicionamos
	    insertApelEmpl.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertApelEmpl.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertApelEmpl.setBackground(Color.WHITE); //Color de fondo
	    insertApelEmpl.setForeground(color_azul);//Color del texto
		subPanelEmpInsertar.add(insertApelEmpl);//Anadimos
		
		insertNIFEmp = new JTextField();//Creamos el componente
		TextPrompt placeholder2 = new TextPrompt("NIF", insertNIFEmp);
	    placeholder2.changeAlpha(0.75f);
	    placeholder2.changeStyle(Font.ITALIC);
	    insertNIFEmp.setBounds(20,120,120,30);//Posicionamos
	    insertNIFEmp.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertNIFEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertNIFEmp.setBackground(Color.WHITE); //Color de fondo
	    insertNIFEmp.setForeground(color_azul);//Color del texto
		subPanelEmpInsertar.add(insertNIFEmp);//Anadimos
		
		insertPhoneEmp = new JTextField();//Creamos el componente
		TextPrompt placeholder3 = new TextPrompt("Phone", insertPhoneEmp);
	    placeholder3.changeAlpha(0.75f);
	    placeholder3.changeStyle(Font.ITALIC);
	    insertPhoneEmp.setBounds(170,120,120,30);//Posicionamos
	    insertPhoneEmp.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertPhoneEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertPhoneEmp.setBackground(Color.WHITE); //Color de fondo
	    insertPhoneEmp.setForeground(color_azul);//Color del texto
		subPanelEmpInsertar.add(insertPhoneEmp);//Anadimos
		
		insertEmailEmp = new JTextField();//Creamos el componente
		TextPrompt placeholder4 = new TextPrompt("e-Mail", insertEmailEmp);
	    placeholder4.changeAlpha(0.75f);
	    placeholder4.changeStyle(Font.ITALIC);
	    insertEmailEmp.setBounds(320,120,410,30);//Posicionamos
	    insertEmailEmp.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertEmailEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertEmailEmp.setBackground(Color.WHITE); //Color de fondo
	    insertEmailEmp.setForeground(color_azul);//Color del texto
		subPanelEmpInsertar.add(insertEmailEmp);//Anadimos
		
		insertUserEmp = new JTextField();//Creamos el componente
		TextPrompt placeholder5 = new TextPrompt("User", insertUserEmp);
	    placeholder5.changeAlpha(0.75f);
	    placeholder5.changeStyle(Font.ITALIC);
	    insertUserEmp.setBounds(20,170,200,30);//Posicionamos
	    insertUserEmp.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertUserEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertUserEmp.setBackground(Color.WHITE); //Color de fondo
	    insertUserEmp.setForeground(color_azul);//Color del texto
		subPanelEmpInsertar.add(insertUserEmp);//Anadimos
		
		insertPassEmp = new JTextField();//Creamos el componente
		TextPrompt placeholder6 = new TextPrompt("Password", insertPassEmp);
	    placeholder6.changeAlpha(0.75f);
	    placeholder6.changeStyle(Font.ITALIC);
	    insertPassEmp.setBounds(250,170,200,30);//Posicionamos
	    insertPassEmp.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertPassEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertPassEmp.setBackground(Color.WHITE); //Color de fondo
	    insertPassEmp.setForeground(color_azul);//Color del texto
		subPanelEmpInsertar.add(insertPassEmp);//Anadimos	
		
		insertPerfilEmp = new JTextField();//Creamos el componente
		TextPrompt placeholder7 = new TextPrompt("Profile (general/administrador)", insertPerfilEmp);
	    placeholder7.changeAlpha(0.75f);
	    placeholder7.changeStyle(Font.ITALIC);
	    insertPerfilEmp.setBounds(480,170,250,30);//Posicionamos
	    insertPerfilEmp.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertPerfilEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertPerfilEmp.setBackground(Color.WHITE); //Color de fondo
	    insertPerfilEmp.setForeground(color_azul);//Color del texto
		subPanelEmpInsertar.add(insertPerfilEmp);//Anadimos	
		
		Image imgBotonInsertFinal = new ImageIcon("img\\insert.png").getImage();
		botonInsertEmpFinal = new JButton(new ImageIcon(imgBotonInsertFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonInsertEmpFinal.setBounds(20,220,110,42);
		botonInsertEmpFinal.setBackground(new Color(186,236,247));
		botonInsertEmpFinal.setBorder(null); //Eliminamos el borde
		subPanelEmpInsertar.add(botonInsertEmpFinal);//Anadimos 
		
		resulInsertEmp = new JLabel();//Creamos el componente
		resulInsertEmp.setBounds(150,222,500,30);//Posicionamos
		resulInsertEmp.setBorder(null); //Eliminamos el borde
		resulInsertEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		resulInsertEmp.setForeground(Color.gray);//Color del texto
		subPanelEmpInsertar.add(resulInsertEmp);//Anadimos	
		
		//SUBPANEL BOTONES EMPLEADOS

		subPanelBotonesEmp = new JPanel();
		subPanelBotonesEmp.setBounds(200, 538, 750, 82);
		subPanelBotonesEmp.setBackground(color_panel);
		subPanelBotonesEmp.setLayout(null);
		add(subPanelBotonesEmp);
		subPanelBotonesEmp.setVisible(false);	
		
		Image imgBotonUpdateEmpleado = new ImageIcon("img\\update employee.png").getImage();
		botonActualizarEmpleado = new JButton(new ImageIcon(imgBotonUpdateEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		botonActualizarEmpleado.setBounds((int) 67.5, 20, 160, 42);
		botonActualizarEmpleado.setBorder(null); // Eliminamos el borde
		botonActualizarEmpleado.setBackground(color_panel);
		subPanelBotonesEmp.add(botonActualizarEmpleado);// Anadimos

		Image imgBotonDeleteEmpleado = new ImageIcon("img\\delete employee.png").getImage();
		botonBorrarEmpleado = new JButton(new ImageIcon(imgBotonDeleteEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		botonBorrarEmpleado.setBounds(295, 20, 160, 42);
		botonBorrarEmpleado.setBorder(null); // Eliminamos el borde
		botonBorrarEmpleado.setBackground(color_panel);
		subPanelBotonesEmp.add(botonBorrarEmpleado);// Anadimos

		Image imgBotonExportEmpleado = new ImageIcon("img\\export to file.png").getImage();
		botonExpEmplFichero = new JButton(new ImageIcon(imgBotonExportEmpleado.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		botonExpEmplFichero.setBounds((int) 522.5, 20, 160, 42);
		botonExpEmplFichero.setBorder(null); // Eliminamos el borde
		botonExpEmplFichero.setBackground(color_panel);
		subPanelBotonesEmp.add(botonExpEmplFichero);// Anadimos
		
		//SUBPANEL UPDATE EMPLEADO
		
		subPanelEmpUpdate = new JPanel();
		subPanelEmpUpdate.setBounds(200, 270, 750, 268);
		subPanelEmpUpdate.setBackground(color_panel);
		subPanelEmpUpdate.setLayout(null);
		add(subPanelEmpUpdate);
		subPanelEmpUpdate.setVisible(false);
		
		updateEmpleado = new JLabel("update employee");
		updateEmpleado.setBounds(20, 0, 710, 60);
		updateEmpleado.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		updateEmpleado.setForeground(color_azul);//Color del texto
		updateEmpleado.setHorizontalAlignment(JLabel.CENTER);
		updateEmpleado.setVerticalAlignment(JLabel.CENTER);
		subPanelEmpUpdate.add(updateEmpleado);//Anadimos
		
		insertNIFUpdateEmp = new JTextField();//Creamos el componente
		TextPrompt placeholder8 = new TextPrompt("Insert employee's NIF", insertNIFUpdateEmp);
	    placeholder8.changeAlpha(0.75f);
	    placeholder8.changeStyle(Font.ITALIC);
	    insertNIFUpdateEmp.setBounds(20,70,200,30);//Posicionamos		
	    insertNIFUpdateEmp.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertNIFUpdateEmp.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
	    insertNIFUpdateEmp.setBackground(Color.WHITE); //Color de fondo
	    insertNIFUpdateEmp.setForeground(color_azul);//Color del texto
	    subPanelEmpUpdate.add(insertNIFUpdateEmp);//Anadimos
	    
	    labelPreguntaCambioEmp = new JLabel("What do you want to change?");
	    labelPreguntaCambioEmp.setBounds(20, 120, 300, 30);
	    labelPreguntaCambioEmp.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
	    labelPreguntaCambioEmp.setForeground(color_azul);//Color del texto
		subPanelEmpUpdate.add(labelPreguntaCambioEmp);//Anadimos
		
		comboUpdateEmp = new JComboBox<String>();
		comboUpdateEmp.addItem("nombre");
		comboUpdateEmp.addItem("apellidos");
		comboUpdateEmp.addItem("email");
		comboUpdateEmp.addItem("NIF_Empleado");
		comboUpdateEmp.addItem("telefono");
		comboUpdateEmp.addItem("usuario");
		comboUpdateEmp.addItem("contrasena");
		comboUpdateEmp.addItem("perfil");
		comboUpdateEmp.setBounds(340, 120, 300, 30);
		subPanelEmpUpdate.add(comboUpdateEmp);
		
		labelNewDataEmp = new JLabel("Insert new data");
		labelNewDataEmp.setBounds(20, 170, 300, 30);
		labelNewDataEmp.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
		labelNewDataEmp.setForeground(color_azul);//Color del texto
		subPanelEmpUpdate.add(labelNewDataEmp);//Anadimos
		
		insertNewDataEmp = new JTextField();//Creamos el componente
		TextPrompt placeholder9 = new TextPrompt("Insert new data", insertNewDataEmp);
	    placeholder9.changeAlpha(0.75f);
	    placeholder9.changeStyle(Font.ITALIC);
	    insertNewDataEmp.setBounds(340,170,300,30);//Posicionamos		
	    insertNewDataEmp.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertNewDataEmp.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
	    insertNewDataEmp.setBackground(Color.WHITE); //Color de fondo
	    insertNewDataEmp.setForeground(color_azul);//Color del texto
	    subPanelEmpUpdate.add(insertNewDataEmp);//Anadimos
	    
	    Image imgBotonUpdateFinal = new ImageIcon("img\\update.png").getImage();
		botonUpdateEmpFinal = new JButton(new ImageIcon(imgBotonUpdateFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonUpdateEmpFinal.setBounds(20,220,110,42);
		botonUpdateEmpFinal.setBackground(new Color(186,236,247));
		botonUpdateEmpFinal.setBorder(null); //Eliminamos el borde
		subPanelEmpUpdate.add(botonUpdateEmpFinal);//Anadimos 
		
		resultUpdateEmp = new JLabel();//Creamos el componente
		resultUpdateEmp.setBounds(150,222,500,30);//Posicionamos
		resultUpdateEmp.setBorder(null); //Eliminamos el borde
		resultUpdateEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		resultUpdateEmp.setForeground(Color.gray);//Color del texto
		subPanelEmpUpdate.add(resultUpdateEmp);//Anadimos	
		
		//SUBPANEL DELETE EMPLEADO
		
		subPanelEmpDelete = new JPanel();
		subPanelEmpDelete.setBounds(200, 270, 750, 268);
		subPanelEmpDelete.setBackground(color_panel);
		subPanelEmpDelete.setLayout(null);
		add(subPanelEmpDelete);
		subPanelEmpDelete.setVisible(false);
		
		deleteEmpleado = new JLabel("delete employee");
		deleteEmpleado.setBounds(20, 0, 710, 60);
		deleteEmpleado.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		deleteEmpleado.setForeground(color_azul);//Color del texto
		deleteEmpleado.setHorizontalAlignment(JLabel.CENTER);
		deleteEmpleado.setVerticalAlignment(JLabel.CENTER);
		subPanelEmpDelete.add(deleteEmpleado);//Anadimos al panel
		
		insertNIFDeleteEmp = new JTextField();//Creamos el componente
		TextPrompt placeholder11 = new TextPrompt("Insert employee's NIF", insertNIFDeleteEmp);
	    placeholder11.changeAlpha(0.75f);
	    placeholder11.changeStyle(Font.ITALIC);
	    insertNIFDeleteEmp.setBounds(20,70,200,30);//Posicionamos		
	    insertNIFDeleteEmp.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
	    insertNIFDeleteEmp.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
	    insertNIFDeleteEmp.setBackground(Color.WHITE); //Color de fondo
	    insertNIFDeleteEmp.setForeground(color_azul);//Color del texto
		subPanelEmpDelete.add(insertNIFDeleteEmp);//Anadimos	

	    Image imgBotonSearchEmp = new ImageIcon("img\\search.png").getImage();
		botonSearchEmp = new JButton(new ImageIcon(imgBotonSearchEmp.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonSearchEmp.setBounds(20,130,110,42);
		botonSearchEmp.setBackground(new Color(186,236,247));
		botonSearchEmp.setBorder(null); //Eliminamos el borde
		subPanelEmpDelete.add(botonSearchEmp);//Anadimos 
		
		resulBusquedaEmp = new JLabel();//Creamos el componente
		resulBusquedaEmp.setBounds(150,130,500,30);//Posicionamos
		resulBusquedaEmp.setBorder(null); //Eliminamos el borde
		resulBusquedaEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		resulBusquedaEmp.setForeground(Color.gray);//Color del texto
		subPanelEmpDelete.add(resulBusquedaEmp);//Anadimos
		
		Image imgBotonDeleteEmpFinal = new ImageIcon("img\\delete.png").getImage();
		botonDeleteEmpFinal = new JButton(new ImageIcon(imgBotonDeleteEmpFinal.getScaledInstance(110,42, Image.SCALE_SMOOTH)));
		botonDeleteEmpFinal.setBounds(20,190,110,42);
		botonDeleteEmpFinal.setBackground(new Color(186,236,247));
		botonDeleteEmpFinal.setBorder(null); //Eliminamos el borde
		subPanelEmpDelete.add(botonDeleteEmpFinal);//Anadimos 
		botonDeleteEmpFinal.setVisible(false);
		
		resulDeleteEmp = new JLabel();//Creamos el componente
		resulDeleteEmp.setBounds(150,190,500,30);//Posicionamos
		resulDeleteEmp.setBorder(null); //Eliminamos el borde
		resulDeleteEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		resulDeleteEmp.setForeground(Color.gray);//Color del texto
		subPanelEmpDelete.add(resulDeleteEmp);//Anadimos
		
		//SUBPANEL EXPORT EMPLEADO
		
		subPanelEmpExport = new JPanel();
		subPanelEmpExport.setBounds(200, 270, 750, 268);
		subPanelEmpExport.setBackground(color_panel);
		subPanelEmpExport.setLayout(null);
		add(subPanelEmpExport);
		subPanelEmpExport.setVisible(false);
		
		exportEmpleado = new JLabel("export employee's to file");
		exportEmpleado.setBounds(20, 0, 710, 60);
		exportEmpleado.setFont(new Font("Segoe UI",Font.BOLD,40));//Damos formato al contenido
		exportEmpleado.setForeground(color_azul);//Color del texto
		exportEmpleado.setHorizontalAlignment(JLabel.CENTER);
		exportEmpleado.setVerticalAlignment(JLabel.CENTER);
		subPanelEmpExport.add(exportEmpleado);//Anadimos al panel
		
		insertUsuarioPCEmpleado = new JTextField();//Creamos el componente
		TextPrompt placeholder12 = new TextPrompt("Insert user PC. Ej. ejemplo", insertUsuarioPCEmpleado);
		placeholder12.changeAlpha(0.75f);
		placeholder12.changeStyle(Font.ITALIC);
		insertUsuarioPCEmpleado.setBounds(20,70,200,30);//Posicionamos		
		insertUsuarioPCEmpleado.setBorder(BorderFactory.createLineBorder(color_azul, 2)); //Eliminamos el borde
		insertUsuarioPCEmpleado.setFont(new Font("Segoe UI",Font.BOLD,14));//Damos formato al contenido
		insertUsuarioPCEmpleado.setBackground(Color.WHITE); //Color de fondo
		insertUsuarioPCEmpleado.setForeground(color_azul);//Color del texto
	    subPanelEmpExport.add(insertUsuarioPCEmpleado);//Anadimos	
		
		insertRutaExportEmp = new JLabel("You'll find the file in C:\\Users\\youruser. Name of file: empleados.csv ");//Creamos el componente
	    insertRutaExportEmp.setBounds(20,120,670,30);//Posicionamos		
	    insertRutaExportEmp.setBorder(null); //Eliminamos el borde
	    insertRutaExportEmp.setFont(new Font("Segoe UI",Font.BOLD,18));//Damos formato al contenido
	    insertRutaExportEmp.setForeground(color_azul);//Color del texto
	    subPanelEmpExport.add(insertRutaExportEmp);//Anadimos
	    
	    Image imgBotonExportEmpFinal = new ImageIcon("img\\export to file.png").getImage();
		botonExportEmpFinal = new JButton(new ImageIcon(imgBotonExportEmpFinal.getScaledInstance(160,42, Image.SCALE_SMOOTH)));
		botonExportEmpFinal.setBounds(20,180,160,42);
		botonExportEmpFinal.setBackground(new Color(186,236,247));
		botonExportEmpFinal.setBorder(null); //Eliminamos el borde
		subPanelEmpExport.add(botonExportEmpFinal);//Anadimos 
				
	    resulExportEmp = new JLabel();//Creamos el componente
	    resulExportEmp.setBounds(200,180,500,30);//Posicionamos
	    resulExportEmp.setBorder(null); //Eliminamos el borde
	    resulExportEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    resulExportEmp.setForeground(Color.GRAY);//Color del texto
	    subPanelEmpExport.add(resulExportEmp);//Anadimos	
	}

	//METODO PARA PONER A LA ESCUCHA LOS EVENTOS	
	public void Eventos (Eventos manejador) {
		
		botonLogin.addActionListener(manejador);
		botonExit.addActionListener(manejador);
		botonLogin.addMouseListener(manejador);
		botonExit.addMouseListener(manejador);
		botonSales.addMouseListener(manejador);
		botonPurchases.addMouseListener(manejador);
		botonSuppliers.addMouseListener(manejador);
		botonCustomers.addMouseListener(manejador);
		botonCRM.addMouseListener(manejador);
		botonStock.addMouseListener(manejador);
		botonHR.addMouseListener(manejador);
		botonUser.addMouseListener(manejador);
		botonLogout.addMouseListener(manejador);
		botonExitInit.addMouseListener(manejador);	
		imageLogo.addMouseListener(manejador);
		
		botonActualizarEmpleado.addMouseListener(manejador);
		botonBorrarEmpleado.addMouseListener(manejador);
		botonExpEmplFichero.addMouseListener(manejador);
		botonUpdateEmpFinal.addMouseListener(manejador);
		botonSearchEmp.addMouseListener(manejador);
		botonDeleteEmpFinal.addMouseListener(manejador);
		botonInsertEmpFinal.addMouseListener(manejador);
		botonExportEmpFinal.addMouseListener(manejador);
		
		botonActualizarProveedor.addMouseListener(manejador);
		botonBorrarProveedor.addMouseListener(manejador);
		botonExportProveedor.addMouseListener(manejador);
		botonUpdateFinalPr.addMouseListener(manejador);
		botonSearchProv.addMouseListener(manejador);
		botonDeleteProvFinal.addMouseListener(manejador);	
		botonInsertProvOk.addMouseListener(manejador);
		botonExportProvFinal.addMouseListener(manejador);
		
		botonInsertClienteok.addMouseListener(manejador);
		botonActualizarCliente.addMouseListener(manejador);
		botonBorrarCliente.addMouseListener(manejador);
		botonUpdateFinalCl.addMouseListener(manejador);
		botonSearchCliente.addMouseListener(manejador);
		botonDeleteClienteFinal.addMouseListener(manejador);		
		botonBills.addMouseListener(manejador);	
		
		botonVerificarCompra.addMouseListener(manejador);
		botonInsertCompraFinal.addMouseListener(manejador);
		botonDeliveryNoteCompra.addMouseListener(manejador);
		botonDeleteCompra.addMouseListener(manejador);
		botonExportCompra.addMouseListener(manejador);
		botonExportComFinal.addMouseListener(manejador);
		botonCheckDeliNoteCom.addMouseListener(manejador);
		botonCheckDeliNoteComDelete.addMouseListener(manejador);
		botonDeleteAlabaran.addMouseListener(manejador);
		botonDeleteLineaAlbaran.addMouseListener(manejador);
		
		botonVerificarVenta.addMouseListener(manejador);
		botonInsertVentaFinal.addMouseListener(manejador);
		botonBillsVentas.addMouseListener(manejador);
		botonCheckBill.addMouseListener(manejador);
		botonDeleteVenta.addMouseListener(manejador);
		botonCheckBillVenDelete.addMouseListener(manejador);
		botonDeleteLineaFactura.addMouseListener(manejador);
		botonDeleteFactura.addMouseListener(manejador);	
		botonExportVenta.addMouseListener(manejador);
		botonExportVenFinal.addMouseListener(manejador);
		
		botonExportStock.addMouseListener(manejador);
		botonExportAlmFinal.addMouseListener(manejador);
		
		botonCheckNom.addMouseListener(manejador);
		
	}

	//GETTER Y SETTER
	public JLabel getEtiquetaUser() {
		return etiquetaUser;
	}
	public void setEtiquetaUser(JLabel etiquetaUser) {
		this.etiquetaUser = etiquetaUser;
	}
	public JLabel getEtiquetaPass() {
		return etiquetaPass;
	}
	public void setEtiquetaPass(JLabel etiquetaPass) {
		this.etiquetaPass = etiquetaPass;
	}
	public JTextField getCajaUser() {
		return cajaUser;
	}
	public void setCajaUser(JTextField cajaUser) {
		this.cajaUser = cajaUser;
	}
	public JPasswordField getCajaPass() {
		return cajaPass;
	}
	public void setCajaPass(JPasswordField cajaPass) {
		this.cajaPass = cajaPass;
	}
	public JButton getBotonLogin() {
		return botonLogin;
	}
	public void setBotonLogin(JButton botonLogin) {
		this.botonLogin = botonLogin;
	}
	public JLabel getEtiquetaResulLogin() {
		return etiquetaResulLogin;
	}
	public void setEtiquetaResulLogin(JLabel etiquetaResulLogin) {
		this.etiquetaResulLogin = etiquetaResulLogin;
	}
	public JButton getBotonExit() {
		return botonExit;
	}
	public void setBotonExit(JButton botonExit) {
		this.botonExit = botonExit;
	}
	public JLabel getImagenInicio() {
		return imagenInicio;
	}
	public void setImagenInicio(JLabel imagenInicio) {
		this.imagenInicio = imagenInicio;
	}
	public JLabel getImagenLogin() {
		return imagenLogin;
	}
	public void setImagenLogin(JLabel imagenLogin) {
		this.imagenLogin = imagenLogin;
	}
	public JButton getBotonPurchases() {
		return botonPurchases;
	}
	public void setBotonPurchases(JButton botonPurchases) {
		this.botonPurchases = botonPurchases;
	}
	public JPanel getPanelIzq() {
		return panelIzq;
	}
	public void setPanelIzq(JPanel panelIzq) {
		this.panelIzq = panelIzq;
	}
	public JPanel getPanelDer() {
		return panelDer;
	}
	public JScrollPane getBarraEmpleados() {
		return barraEmpleados;
	}
	public void setBarraEmpleados(JScrollPane barraEmpleados) {
		this.barraEmpleados = barraEmpleados;
	}
	public void setPanelDer(JPanel panelDer) {
		this.panelDer = panelDer;
	}		
	public JLabel getImageLogo() {
		return imageLogo;
	}
	public void setImageLogo(JLabel imageLogo) {
		this.imageLogo = imageLogo;
	}
	public JButton getBotonSales() {
		return botonSales;
	}
	public void setBotonSales(JButton botonSales) {
		this.botonSales = botonSales;
	}
	public JButton getBotonSuppliers() {
		return botonSuppliers;
	}
	public void setBotonSuppliers(JButton botonSuppliers) {
		this.botonSuppliers = botonSuppliers;
	}
	public JLabel getDeleteEmpleado() {
		return deleteEmpleado;
	}

	public void setDeleteEmpleado(JLabel deleteEmpleado) {
		this.deleteEmpleado = deleteEmpleado;
	}

	public JTextField getInsertCODDeleteProv() {
		return insertCODDeleteProv;
	}

	public void setInsertCODDeleteProv(JTextField insertCODDeleteProv) {
		this.insertCODDeleteProv = insertCODDeleteProv;
	}

	public JButton getBotonSearchProv() {
		return botonSearchProv;
	}

	public void setBotonSearchProv(JButton botonSearchProv) {
		this.botonSearchProv = botonSearchProv;
	}

	public JButton getBotonUpdateFinalPr() {
		return botonUpdateFinalPr;
	}

	public void setBotonUpdateFinalPr(JButton botonUpdateFinalPr) {
		this.botonUpdateFinalPr = botonUpdateFinalPr;
	}

	public JButton getBotonDeleteProvFinal() {
		return botonDeleteProvFinal;
	}

	public void setBotonDeleteProvFinal(JButton botonDeleteProvFinal) {
		this.botonDeleteProvFinal = botonDeleteProvFinal;
	}

	public JLabel getResulBusquedaProv() {
		return resulBusquedaProv;
	}

	public void setResulBusquedaProv(JLabel resulBusquedaProv) {
		this.resulBusquedaProv = resulBusquedaProv;
	}

	public JLabel getResulDeleteProv() {
		return resulDeleteProv;
	}

	public void setResulDeleteProv(JLabel resulDeleteProv) {
		this.resulDeleteProv = resulDeleteProv;
	}

	public JButton getBotonCustomers() {
		return botonCustomers;
	}
	public void setBotonCustomers(JButton botonCustomers) {
		this.botonCustomers = botonCustomers;
	}

	public JButton getBotonStock() {
		return botonStock;
	}
	public void setBotonStock(JButton botonStock) {
		this.botonStock = botonStock;
	}
	public JButton getBotonHR() {
		return botonHR;
	}
	public void setBotonHR(JButton botonHR) {
		this.botonHR = botonHR;
	}
	public JButton getBotonUser() {
		return botonUser;
	}
	public void setBotonUser(JButton botonUser) {
		this.botonUser = botonUser;
	}
	public JButton getBotonLogout() {
		return botonLogout;
	}
	public void setBotonLogout(JButton botonLogout) {
		this.botonLogout = botonLogout;
	}
	public JButton getBotonExitInit() {
		return botonExitInit;
	}
	public void setBotonExitInit(JButton botonExitInit) {
		this.botonExitInit = botonExitInit;
	}
	public JTable getTablaEmpleados() {
		return tablaEmpleados;
	}
	public void setTablaEmpleados(JTable tablaEmpleados) {
		this.tablaEmpleados = tablaEmpleados;
	}
	public JButton getBotonActualizarEmpleado() {
		return botonActualizarEmpleado;
	}
	public void setBotonActualizarEmpleado(JButton botonActualizarEmpleado) {
		this.botonActualizarEmpleado = botonActualizarEmpleado;
	}
	public JButton getBotonBorrarEmpleado() {
		return botonBorrarEmpleado;
	}
	public void setBotonBorrarEmpleado(JButton botonBorrarEmpleado) {
		this.botonBorrarEmpleado = botonBorrarEmpleado;
	}
	public JButton getBotonExpEmplFichero() {
		return botonExpEmplFichero;
	}
	public void setBotonExpEmplFichero(JButton botonExpEmplFichero) {
		this.botonExpEmplFichero = botonExpEmplFichero;
	}
	public JPanel getPanelEmpleado() {
		return panelEmpleado;
	}
	public void setPanelEmpleado(JPanel panelEmpleado) {
		this.panelEmpleado = panelEmpleado;
	}
	public JPanel getSubPanelEmpInsertar() {
		return subPanelEmpInsertar;
	}
	public void setSubPanelEmpInsertar(JPanel subPanelEmpInsertar) {
		this.subPanelEmpInsertar = subPanelEmpInsertar;
	}
	public JPanel getSubPanelBotonesEmp() {
		return subPanelBotonesEmp;
	}
	public void setSubPanelBotonesEmp(JPanel subPanelBotonesEmp) {
		this.subPanelBotonesEmp = subPanelBotonesEmp;
	}
	public JTextField getInsertNomEmpl() {
		return insertNomEmpl;
	}
	public void setInsertNomEmpl(JTextField insertNomEmpl) {
		this.insertNomEmpl = insertNomEmpl;
	}
	public JTextField getInsertApelEmpl() {
		return insertApelEmpl;
	}
	public void setInsertApelEmpl(JTextField insertApelEmpl) {
		this.insertApelEmpl = insertApelEmpl;
	}
	public JTextField getInsertNIFEmp() {
		return insertNIFEmp;
	}
	public void setInsertNIFEmp(JTextField insertNIFEmp) {
		this.insertNIFEmp = insertNIFEmp;
	}
	public JTextField getInsertPhoneEmp() {
		return insertPhoneEmp;
	}
	public void setInsertPhoneEmp(JTextField insertPhoneEmp) {
		this.insertPhoneEmp = insertPhoneEmp;
	}
	public JTextField getInsertEmailEmp() {
		return insertEmailEmp;
	}
	public void setInsertEmailEmp(JTextField insertEmailEmp) {
		this.insertEmailEmp = insertEmailEmp;
	}
	public JTextField getInsertUserEmp() {
		return insertUserEmp;
	}
	public void setInsertUserEmp(JTextField insertUserEmp) {
		this.insertUserEmp = insertUserEmp;
	}
	public JTextField getInsertPassEmp() {
		return insertPassEmp;
	}
	public void setInsertPassEmp(JTextField insertPassEmp) {
		this.insertPassEmp = insertPassEmp;
	}
	public JButton getBotonInsertEmpFinal() {
		return botonInsertEmpFinal;
	}
	public void setBotonInsertEmpFinal(JButton botonInsertFinal) {
		this.botonInsertEmpFinal = botonInsertFinal;
	}
	public JLabel getResulInsertEmp() {
		return resulInsertEmp;
	}
	public void setResulInsertEmp(JLabel resulInsertEmp) {
		this.resulInsertEmp = resulInsertEmp;
	}
	public JTextField getInsertPerfilEmp() {
		return insertPerfilEmp;
	}
	public void setInsertPerfilEmp(JTextField insertPerfilEmp) {
		this.insertPerfilEmp = insertPerfilEmp;
	}	
	public JLabel getResultUpdateEmp() {
		return resultUpdateEmp;
	}
	public void setResultUpdateEmp(JLabel resultUpdateEmp) {
		this.resultUpdateEmp = resultUpdateEmp;
	}
	public JPanel getPanelCompras() {
		return panelCompras;
	}
	public void setPanelCompras(JPanel panelCompras) {
		this.panelCompras = panelCompras;
	}	
	public JScrollPane getBarraCompras() {
		return barraCompras;
	}
	public void setBarraCompras(JScrollPane barraCompras) {
		this.barraCompras = barraCompras;
	}
	public JPanel getPanelVentas() {
		return panelVentas;
	}
	public void setPanelVentas(JPanel panelVentas) {
		this.panelVentas = panelVentas;
	}	
	public JPanel getPanelProveedores() {
		return panelProveedores;
	}
	public void setPanelProveedores(JPanel panelProveedores) {
		this.panelProveedores = panelProveedores;
	}
	public JPanel getPanelClientes() {
		return panelClientes;
	}
	public void setPanelClientes(JPanel panelClientes) {
		this.panelClientes = panelClientes;
	}
	public JPanel getPanelAlmacen() {
		return panelAlmacen;
	}
	public void setPanelAlmacen(JPanel panelAlmacen) {
		this.panelAlmacen = panelAlmacen;
	}
	public JPanel getSubPanelEmpUpdate() {
		return subPanelEmpUpdate;
	}
	public void setSubPanelEmpUpdate(JPanel subPanelEmpUpdate) {
		this.subPanelEmpUpdate = subPanelEmpUpdate;
	}
	public JLabel getNuevoEmpleado() {
		return nuevoEmpleado;
	}
	public void setNuevoEmpleado(JLabel nuevoEmpleado) {
		this.nuevoEmpleado = nuevoEmpleado;
	}
	public JButton getBotonUpdateEmpFinal() {
		return botonUpdateEmpFinal;
	}
	public void setBotonUpdateEmpFinal(JButton botonUpdateFinal) {
		this.botonUpdateEmpFinal = botonUpdateFinal;
	}
	public JLabel getUpdateEmpleado() {
		return updateEmpleado;
	}
	public void setUpdateEmpleado(JLabel updateEmpleado) {
		this.updateEmpleado = updateEmpleado;
	}
	public JTextField getInsertNIFUpdateEmp() {
		return insertNIFUpdateEmp;
	}
	public void setInsertNIFUpdateEmp(JTextField insertNIFUpdateEmp) {
		this.insertNIFUpdateEmp = insertNIFUpdateEmp;
	}
	public JTextField getInsertNewDataEmp() {
		return insertNewDataEmp;
	}
	public void setInsertNewDataEmp(JTextField insertNewDataEmp) {
		this.insertNewDataEmp = insertNewDataEmp;
	}
	public JComboBox<String> getComboUpdateEmp() {
		return comboUpdateEmp;
	}
	public void setComboUpdateEmp(JComboBox<String> comboUpdateEmp) {
		this.comboUpdateEmp = comboUpdateEmp;
	}
	public JPanel getSubPanelInsProv() {
		return subPanelInsProv;
	}
	public void setSubPanelInsProv(JPanel subPanelInsProv) {
		this.subPanelInsProv = subPanelInsProv;
	}
	public JPanel getSubPanelEditProv() {
		return subPanelEditProv;
	}
	public void setSubPanelEditProv(JPanel subPanelEditProv) {
		this.subPanelEditProv = subPanelEditProv;
	}
	public JPanel getSubPanelElimProv() {
		return subPanelElimProv;
	}
	public void setSubPanelElimProv(JPanel subPanelElimProv) {
		this.subPanelElimProv = subPanelElimProv;
	}
	public JPanel getPanelBotonesProv() {
		return panelBotonesProv;
	}
	public void setPanelBotonesProv(JPanel panelBotonesProv) {
		this.panelBotonesProv = panelBotonesProv;
	}
	public JTextField getInsertCODUpdateProv() {
		return insertCODUpdateProv;
	}
	public void setInsertCODUpdateProv(JTextField insertCODUpdateProv) {
		this.insertCODUpdateProv = insertCODUpdateProv;
	}
	public JTextField getInsertNewDataProv() {
		return insertNewDataProv;
	}
	public void setInsertNewDataProv(JTextField insertNewDataProv) {
		this.insertNewDataProv = insertNewDataProv;
	}
	public JButton getBotonUpdateEmpFinalPr() {
		return botonUpdateEmpFinalPr;
	}
	public void setBotonUpdateEmpFinalPr(JButton botonUpdateEmpFinalPr) {
		this.botonUpdateEmpFinalPr = botonUpdateEmpFinalPr;
	}
	public JLabel getResultUpdateProv() {
		return resultUpdateProv;
	}
	public void setResultUpdateProv(JLabel resultUpdateProv) {
		this.resultUpdateProv = resultUpdateProv;
	}
	public JTextField getInsertCodProv() {
		return insertCodProv;
	}
	public void setInsertCodProv(JTextField insertCodProv) {
		this.insertCodProv = insertCodProv;
	}
	public JTextField getInsertNomProv() {
		return insertNomProv;
	}
	public void setInsertNomProv(JTextField insertNomProv) {
		this.insertNomProv = insertNomProv;
	}
	public JTextField getInsertContProv() {
		return insertContProv;
	}
	public void setInsertContProv(JTextField insertContProv) {
		this.insertContProv = insertContProv;
	}
	public JScrollPane getBarraProveedores() {
		return barraProveedores;
	}
	public void setBarraProveedores(JScrollPane barraProveedores) {
		this.barraProveedores = barraProveedores;
	}
	public JTable getTablaProveedores() {
		return tablaProveedores;
	}
	public void setTablaProveedores(JTable tablaProveedores) {
		this.tablaProveedores = tablaProveedores;
	}
	public JButton getBotonInsertProveedor() {
		return botonInsertProveedor;
	}
	public void setBotonInsertProveedor(JButton botonInsertProveedor) {
		this.botonInsertProveedor = botonInsertProveedor;
	}
	public JButton getBotonActualizarProveedor() {
		return botonActualizarProveedor;
	}
	public void setBotonActualizarProveedor(JButton botonActualizarProveedor) {
		this.botonActualizarProveedor = botonActualizarProveedor;
	}
	public JButton getBotonBorrarProveedor() {
		return botonBorrarProveedor;
	}
	public void setBotonBorrarProveedor(JButton botonBorrarProveedor) {
		this.botonBorrarProveedor = botonBorrarProveedor;
	}
	public JButton getBotonInsertProvOk() {
		return botonInsertProvOk;
	}
	public void setBotonInsertProvOk(JButton botonInsertProvOk) {
		this.botonInsertProvOk = botonInsertProvOk;
	}
	public JLabel getNuevoProv() {
		return nuevoProv;
	}
	public void setNuevoProv(JLabel nuevoProv) {
		this.nuevoProv = nuevoProv;
	}
	public JLabel getResulInsertProv() {
		return resulInsertProv;
	}
	public void setResulInsertProv(JLabel resulInsertProv) {
		this.resulInsertProv = resulInsertProv;
	}
	public JLabel getEditProv() {
		return editProv;
	}
	public void setEditProv(JLabel editProv) {
		this.editProv = editProv;
	}
	public JComboBox<String> getComboUpdateProv() {
		return comboUpdateProv;
	}

	public void setComboUpdateProv(JComboBox<String> comboUpdateProv) {
		this.comboUpdateProv = comboUpdateProv;
	}

	public JLabel getElimProv() {
		return elimProv;
	}
	
	public void setElimProv(JLabel elimProv) {
		this.elimProv = elimProv;
	}
	public JPanel getSubPanelEmpDelete() {
		return subPanelEmpDelete;
	}
	public void setSubPanelEmpDelete(JPanel subPanelEmpDelete) {
		this.subPanelEmpDelete = subPanelEmpDelete;
	}
	public JButton getBotonSearchEmp() {
		return botonSearchEmp;
	}
	public void setBotonSearchEmp(JButton botonSearchEmp) {
		this.botonSearchEmp = botonSearchEmp;
	}
	public JTextField getInsertNIFDeleteEmp() {
		return insertNIFDeleteEmp;
	}
	public void setInsertNIFDeleteEmp(JTextField insertNIFDeleteEmp) {
		this.insertNIFDeleteEmp = insertNIFDeleteEmp;
	}
	public JLabel getResulBusquedaEmp() {
		return resulBusquedaEmp;
	}
	public void setResulBusquedaEmp(JLabel resulBusquedaEmp) {
		this.resulBusquedaEmp = resulBusquedaEmp;
	}
	public JButton getBotonDeleteEmpFinal() {
		return botonDeleteEmpFinal;
	}
	public void setBotonDeleteEmpFinal(JButton botonDeleteEmpFinal) {
		this.botonDeleteEmpFinal = botonDeleteEmpFinal;
	}
	public JLabel getResulDeleteEmp() {
		return resulDeleteEmp;
	}
	public void setResulDeleteEmp(JLabel resulDeleteEmp) {
		this.resulDeleteEmp = resulDeleteEmp;
	}
	public JPanel getSubPanelEmpExport() {
		return subPanelEmpExport;
	}
	public void setSubPanelEmpExport(JPanel subPanelEmpExport) {
		this.subPanelEmpExport = subPanelEmpExport;
	}
	public JButton getBotonExportEmpFinal() {
		return botonExportEmpFinal;
	}
	public void setBotonExportEmpFinal(JButton botonExportEmpFinal) {
		this.botonExportEmpFinal = botonExportEmpFinal;
	}
	public JLabel getExportEmpleado() {
		return exportEmpleado;
	}
	public void setExportEmpleado(JLabel exportEmpleado) {
		this.exportEmpleado = exportEmpleado;
	}
	public JLabel getInsertRutaExportEmp() {
		return insertRutaExportEmp;
	}
	public void setInsertRutaExportEmp(JLabel insertRutaExportEmp) {
		this.insertRutaExportEmp = insertRutaExportEmp;
	}
	public JLabel getResulExportEmp() {
		return resulExportEmp;
	}
	public void setResulExportEmp(JLabel resulExportEmp) {
		this.resulExportEmp = resulExportEmp;
	}
	public JPanel getSubPanelInsCliente() {
		return subPanelInsCliente;
	}
	public void setSubPanelInsCliente(JPanel subPanelInsCliente) {
		this.subPanelInsCliente = subPanelInsCliente;
	}
	public JPanel getPanelBotonesCliente() {
		return panelBotonesCliente;
	}
	public void setPanelBotonesCliente(JPanel panelBotonesCliente) {
		this.panelBotonesCliente = panelBotonesCliente;
	}
	public JPanel getSubPanelEditCliente() {
		return subPanelEditCliente;
	}
	public void setSubPanelEditCliente(JPanel subPanelEditCliente) {
		this.subPanelEditCliente = subPanelEditCliente;
	}
	public JPanel getSubPanelElimCliente() {
		return subPanelElimCliente;
	}
	public void setSubPanelElimCliente(JPanel subPanelElimCliente) {
		this.subPanelElimCliente = subPanelElimCliente;
	}
	public JScrollPane getBarraClientes() {
		return barraClientes;
	}
	public void setBarraClientes(JScrollPane barraClientes) {
		this.barraClientes = barraClientes;
	}
	public JTable getTablaClientes() {
		return tablaClientes;
	}
	public void setTablaClientes(JTable tablaClientes) {
		this.tablaClientes = tablaClientes;
	}
	public JLabel getNuevoCliente() {
		return nuevoCliente;
	}
	public void setNuevoCliente(JLabel nuevoCliente) {
		this.nuevoCliente = nuevoCliente;
	}
	public JLabel getResulInsertCliente() {
		return resulInsertCliente;
	}
	public void setResulInsertCliente(JLabel resulInsertCliente) {
		this.resulInsertCliente = resulInsertCliente;
	}
	public JLabel getEditCliente() {
		return editCliente;
	}
	public void setEditCliente(JLabel editCliente) {
		this.editCliente = editCliente;
	}
	public JLabel getResultUpdateCliente() {
		return resultUpdateCliente;
	}
	public void setResultUpdateCliente(JLabel resultUpdateCliente) {
		this.resultUpdateCliente = resultUpdateCliente;
	}
	public JLabel getElimCliente() {
		return elimCliente;
	}
	public void setElimCliente(JLabel elimCliente) {
		this.elimCliente = elimCliente;
	}
	public JLabel getResulBusquedaCli() {
		return resulBusquedaCli;
	}
	public void setResulBusquedaCli(JLabel resulBusquedaCli) {
		this.resulBusquedaCli = resulBusquedaCli;
	}
	public JLabel getResulDeleteCliente() {
		return resulDeleteCliente;
	}
	public void setResulDeleteCliente(JLabel resulDeleteCliente) {
		this.resulDeleteCliente = resulDeleteCliente;
	}
	public JTextField getInsertNIFCliente() {
		return insertNIFCliente;
	}
	public void setInsertNIFCliente(JTextField insertNIFCliente) {
		this.insertNIFCliente = insertNIFCliente;
	}
	public JTextField getInsertNomCliente() {
		return insertNomCliente;
	}
	public void setInsertNomCliente(JTextField insertNomCliente) {
		this.insertNomCliente = insertNomCliente;
	}
	public JTextField getInsertTelCliente() {
		return insertTelCliente;
	}
	public void setInsertTelCliente(JTextField insertTelCliente) {
		this.insertTelCliente = insertTelCliente;
	}
	public JTextField getInsertNIFUpdateCliente() {
		return insertNIFUpdateCliente;
	}
	public void setInsertNIFUpdateCliente(JTextField insertNIFUpdateCliente) {
		this.insertNIFUpdateCliente = insertNIFUpdateCliente;
	}
	public JTextField getInsertNIFDeleteCli() {
		return insertNIFDeleteCli;
	}
	public void setInsertNIFDeleteCli(JTextField insertNIFDeleteCli) {
		this.insertNIFDeleteCli = insertNIFDeleteCli;
	}
	public JTextField getInsertNewDataCliente() {
		return insertNewDataCliente;
	}
	public void setInsertNewDataCliente(JTextField insertNewDataCliente) {
		this.insertNewDataCliente = insertNewDataCliente;
	}
	public JButton getBotonInsertClienteok() {
		return botonInsertClienteok;
	}
	public void setBotonInsertClienteok(JButton botonInsertClienteok) {
		this.botonInsertClienteok = botonInsertClienteok;
	}
	public JButton getBotonActualizarCliente() {
		return botonActualizarCliente;
	}
	public void setBotonActualizarCliente(JButton botonActualizarCliente) {
		this.botonActualizarCliente = botonActualizarCliente;
	}
	public JButton getBotonBorrarCliente() {
		return botonBorrarCliente;
	}
	public void setBotonBorrarCliente(JButton botonBorrarCliente) {
		this.botonBorrarCliente = botonBorrarCliente;
	}
	public JButton getBotonUpdateFinalCl() {
		return botonUpdateFinalCl;
	}
	public void setBotonUpdateFinalCl(JButton botonUpdateFinalCl) {
		this.botonUpdateFinalCl = botonUpdateFinalCl;
	}
	public JButton getBotonSearchCliente() {
		return botonSearchCliente;
	}
	public void setBotonSearchCliente(JButton botonSearchCliente) {
		this.botonSearchCliente = botonSearchCliente;
	}
	public JButton getBotonDeleteClienteFinal() {
		return botonDeleteClienteFinal;
	}
	public void setBotonDeleteClienteFinal(JButton botonDeleteClienteFinal) {
		this.botonDeleteClienteFinal = botonDeleteClienteFinal;
	}
	public JComboBox<String> getComboUpdateCliente() {
		return comboUpdateCliente;
	}
	public void setComboUpdateCliente(JComboBox<String> comboUpdateCliente) {
		this.comboUpdateCliente = comboUpdateCliente;
	}
	public JTextField getInsertMailCliente() {
		return insertMailCliente;
	}
	public void setInsertMailCliente(JTextField insertMailCliente) {
		this.insertMailCliente = insertMailCliente;
	}

	public JButton getBotonBills() {
		return botonBills;
	}

	public void setBotonBills(JButton botonBills) {
		this.botonBills = botonBills;
	}

	public JTextField getInsertUsuarioPCCompras() {
		return insertUsuarioPCCompras;
	}

	public void setInsertUsuarioPCCompras(JTextField insertUsuarioPC) {
		this.insertUsuarioPCCompras = insertUsuarioPC;
	}

	public JPanel getSubPanelInsertCompras() {
		return subPanelInsertCompras;
	}

	public void setSubPanelInsertCompras(JPanel subPanelInsertCompras) {
		this.subPanelInsertCompras = subPanelInsertCompras;
	}

	public JPanel getSubPanelBotonesCompras() {
		return subPanelBotonesCompras;
	}

	public void setSubPanelBotonesCompras(JPanel subPanelBotonesCompras) {
		this.subPanelBotonesCompras = subPanelBotonesCompras;
	}

	public JButton getBotonDeliveryNoteCompra() {
		return botonDeliveryNoteCompra;
	}

	public void setBotonDeliveryNoteCompra(JButton botonActualCompra) {
		this.botonDeliveryNoteCompra = botonActualCompra;
	}

	public JButton getBotonDeleteCompra() {
		return botonDeleteCompra;
	}

	public void setBotonDeleteCompra(JButton botonDeleteCompra) {
		this.botonDeleteCompra = botonDeleteCompra;
	}

	public JButton getBotonExportCompra() {
		return botonExportCompra;
	}

	public void setBotonExportCompra(JButton botonExportCompra) {
		this.botonExportCompra = botonExportCompra;
	}

	public JLabel getJLresulComboProCompra() {
		return JLresulComboProCompra;
	}

	public void setJLresulComboProCompra(JLabel jLresulComboProCompra) {
		JLresulComboProCompra = jLresulComboProCompra;
	}

	public JTextField getJTFnumAlbaran() {
		return JTFnumAlbaran;
	}

	public void setJTFnumAlbaran(JTextField jTFnumAlbaran) {
		JTFnumAlbaran = jTFnumAlbaran;
	}

	public JComboBox<String> getComboProductoCompras() {
		return comboProductoCompras;
	}

	public void setComboProductoCompras(JComboBox<String> comboProductoCompras) {
		this.comboProductoCompras = comboProductoCompras;
	}

	public JButton getBotonVerificarCompra() {
		return botonVerificarCompra;
	}

	public void setBotonVerificarCompra(JButton botonVerificarCompra) {
		this.botonVerificarCompra = botonVerificarCompra;
	}

	public JButton getBotonInsertCompraFinal() {
		return botonInsertCompraFinal;
	}

	public void setBotonInsertCompraFinal(JButton botonCompraFinal) {
		this.botonInsertCompraFinal = botonCompraFinal;
	}

	public JTable getTablaCompras() {
		return tablaCompras;
	}

	public void setTablaCompras(JTable tablaCompras) {
		this.tablaCompras = tablaCompras;
	}

	public JLabel getJLresulimporCompraPro() {
		return JLresulimporCompraPro;
	}

	public void setJLresulimporCompraPro(JLabel jLresulimporCompraPro) {
		JLresulimporCompraPro = jLresulimporCompraPro;
	}

	public JLabel getJLresulimporTotalPro() {
		return JLresulimporTotalPro;
	}

	public void setJLresulimporTotalPro(JLabel jLresulimporTotalPro) {
		JLresulimporTotalPro = jLresulimporTotalPro;
	}

	public JLabel getJLresulComboProveedorCompra() {
		return JLresulComboProveedorCompra;
	}

	public void setJLresulComboProveedorCompra(JLabel jLresulComboProveedorCompra) {
		JLresulComboProveedorCompra = jLresulComboProveedorCompra;
	}

	public JTextField getJTFcantidadCompra() {
		return JTFcantidadCompra;
	}

	public void setJTFcantidadCompra(JTextField jTFcantidadCompra) {
		JTFcantidadCompra = jTFcantidadCompra;
	}

	public JComboBox<String> getComboProveedorCompras() {
		return comboProveedorCompras;
	}

	public void setComboProveedorCompras(JComboBox<String> comboProveedorCompras) {
		this.comboProveedorCompras = comboProveedorCompras;
	}

	public JLabel getJLresulinsertComprafinal() {
		return JLresulinsertComprafinal;
	}

	public void setJLresulinsertComprafinal(JLabel jLresulinsertComprafinal) {
		JLresulinsertComprafinal = jLresulinsertComprafinal;
	}

	public JLabel getLabelNewDataEmp() {
		return labelNewDataEmp;
	}

	public void setLabelNewDataEmp(JLabel labelNewDataEmp) {
		this.labelNewDataEmp = labelNewDataEmp;
	}

	public JPanel getSubPanelInsertVentas() {
		return subPanelInsertVentas;
	}

	public void setSubPanelInsertVentas(JPanel subPanelInsertVentas) {
		this.subPanelInsertVentas = subPanelInsertVentas;
	}

	public JPanel getSubPanelBotonesVentas() {
		return subPanelBotonesVentas;
	}

	public void setSubPanelBotonesVentas(JPanel subPanelBotonesVentas) {
		this.subPanelBotonesVentas = subPanelBotonesVentas;
	}

	public JScrollPane getBarraVentas() {
		return barraVentas;
	}

	public void setBarraVentas(JScrollPane barraVentas) {
		this.barraVentas = barraVentas;
	}

	public JTable getTablaVentas() {
		return tablaVentas;
	}

	public void setTablaVentas(JTable tablaVentas) {
		this.tablaVentas = tablaVentas;
	}

	public JLabel getJLresulComboSerVenta() {
		return JLresulComboSerVenta;
	}

	public void setJLresulComboSerVenta(JLabel jLresulComboSerVenta) {
		JLresulComboSerVenta = jLresulComboSerVenta;
	}

	public JLabel getJLresulimporVentaServ() {
		return JLresulimporVentaServ;
	}

	public void setJLresulimporVentaServ(JLabel jLresulimporVentaServ) {
		JLresulimporVentaServ = jLresulimporVentaServ;
	}

	public JLabel getJLresulimporTotalServ() {
		return JLresulimporTotalServ;
	}

	public void setJLresulimporTotalServ(JLabel jLresulimporTotalServ) {
		JLresulimporTotalServ = jLresulimporTotalServ;
	}

	public JLabel getJLresulComboClienteVentas() {
		return JLresulComboClienteVentas;
	}

	public void setJLresulComboClienteVentas(JLabel jLresulComboClienteVentas) {
		JLresulComboClienteVentas = jLresulComboClienteVentas;
	}

	public JLabel getJLresulinsertVentafinal() {
		return JLresulinsertVentafinal;
	}

	public void setJLresulinsertVentafinal(JLabel jLresulinsertVentafinal) {
		JLresulinsertVentafinal = jLresulinsertVentafinal;
	}

	public JTextField getJTFnumFactura() {
		return JTFnumFactura;
	}

	public void setJTFnumFactura(JTextField jTFnumFactura) {
		JTFnumFactura = jTFnumFactura;
	}

	public JTextField getJTFcantidadVenta() {
		return JTFcantidadVenta;
	}

	public void setJTFcantidadVenta(JTextField jTFcantidadVenta) {
		JTFcantidadVenta = jTFcantidadVenta;
	}

	public JComboBox<String> getComboServicioVentas() {
		return comboServicioVentas;
	}

	public void setComboServicioVentas(JComboBox<String> comboServicioVentas) {
		this.comboServicioVentas = comboServicioVentas;
	}

	public JComboBox<String> getComboClienteVentas() {
		return comboClienteVentas;
	}

	public void setComboClienteVentas(JComboBox<String> comboClienteVentas) {
		this.comboClienteVentas = comboClienteVentas;
	}

	public JButton getBotonVerificarVenta() {
		return botonVerificarVenta;
	}

	public void setBotonVerificarVenta(JButton botonVerificarVenta) {
		this.botonVerificarVenta = botonVerificarVenta;
	}

	public JButton getBotonInsertVentaFinal() {
		return botonInsertVentaFinal;
	}

	public void setBotonInsertVentaFinal(JButton botonInsertVentaFinal) {
		this.botonInsertVentaFinal = botonInsertVentaFinal;
	}
	public JButton getBotonDeleteVenta() {
		return botonDeleteVenta;
	}

	public void setBotonDeleteVenta(JButton botonDeleteVenta) {
		this.botonDeleteVenta = botonDeleteVenta;
	}

	public JButton getBotonExportVenta() {
		return botonExportVenta;
	}

	public void setBotonExportVenta(JButton botonExportVenta) {
		this.botonExportVenta = botonExportVenta;
	}

	public JLabel getLabelNewDataProv() {
		return labelNewDataProv;
	}

	public void setLabelNewDataProv(JLabel labelNewDataProv) {
		this.labelNewDataProv = labelNewDataProv;
	}

	public JLabel getLabelNewDataCliente() {
		return labelNewDataCliente;
	}

	public void setLabelNewDataCliente(JLabel labelNewDataCliente) {
		this.labelNewDataCliente = labelNewDataCliente;
	}

	public JPanel getSubPanelComprasExport() {
		return subPanelComprasExport;
	}

	public void setSubPanelComprasExport(JPanel subPanelComprasExport) {
		this.subPanelComprasExport = subPanelComprasExport;
	}

	public JButton getBotonExportComFinal() {
		return botonExportComFinal;
	}

	public void setBotonExportComFinal(JButton botonExportComFinal) {
		this.botonExportComFinal = botonExportComFinal;
	}

	public JLabel getExportCompras() {
		return exportCompras;
	}

	public void setExportCompras(JLabel exportCompras) {
		this.exportCompras = exportCompras;
	}

	public JLabel getInsertRutaExportCompras() {
		return insertRutaExportCompras;
	}

	public void setInsertRutaExportCompras(JLabel insertRutaExportCompras) {
		this.insertRutaExportCompras = insertRutaExportCompras;
	}

	public JLabel getResulExportCom() {
		return resulExportCom;
	}

	public void setResulExportCom(JLabel resulExportCom) {
		this.resulExportCom = resulExportCom;
	}

	public JButton getBotonCRM() {
		return botonCRM;
	}

	public void setBotonCRM(JButton botonCRM) {
		this.botonCRM = botonCRM;
	}

	public JPanel getPanelCRM() {
		return panelCRM;
	}

	public void setPanelCRM(JPanel panelCRM) {
		this.panelCRM = panelCRM;
	}

	public JPanel getSubPanelVentasExport() {
		return subPanelVentasExport;
	}

	public void setSubPanelVentasExport(JPanel subPanelVentasExport) {
		this.subPanelVentasExport = subPanelVentasExport;
	}

	public JLabel getResulExportVen() {
		return resulExportVen;
	}

	public void setResulExportVen(JLabel resulExportVen) {
		
		
		this.resulExportVen = resulExportVen;
	}

	public JButton getBotonExportVenFinal() {
		return botonExportVenFinal;
	}

	public void setBotonExportVenFinal(JButton botonExportVenFinal) {
		this.botonExportVenFinal = botonExportVenFinal;
	}
	
	public JScrollPane getBarraStock() {
		return barraStock;
	}

	public void setBarraStock(JScrollPane barraStock) {
		this.barraStock = barraStock;
	}

	public JButton getBotonExportStock() {
		return botonExportStock;
	}

	public void setBotonExportStock(JButton botonExportStock) {
		this.botonExportStock = botonExportStock;
	}

	public JTable getTablaAlmacen() {
		return tablaAlmacen;
	}

	public void setTablaAlmacen(JTable tablaAlmacen) {
		this.tablaAlmacen = tablaAlmacen;
	}

	public JPanel getSubPanelAlmacenExport() {
		return subPanelAlmacenExport;
	}

	public void setSubPanelAlmacenExport(JPanel subPanelAlmacenExport) {
		this.subPanelAlmacenExport = subPanelAlmacenExport;
	}

	public JScrollPane getBarraStock2() {
		return barraStock2;
	}

	public void setBarraStock2(JScrollPane barraStock2) {
		this.barraStock2 = barraStock2;
	}

	public JButton getBotonExportAlmFinal() {
		return botonExportAlmFinal;
	}

	public void setBotonExportAlmFinal(JButton botonExportAlmFinal) {
		this.botonExportAlmFinal = botonExportAlmFinal;
	}

	public JTable getTablaAlmacen2() {
		return tablaAlmacen2;
	}

	public void setTablaAlmacen2(JTable tablaAlmacen2) {
		this.tablaAlmacen2 = tablaAlmacen2;
	}

	public JLabel getDetalleAlmacen() {
		return detalleAlmacen;
	}

	public void setDetalleAlmacen(JLabel detalleAlmacen) {
		this.detalleAlmacen = detalleAlmacen;
	}

	public JLabel getResumenAlmacen() {
		return resumenAlmacen;
	}

	public void setResumenAlmacen(JLabel resumenAlmacen) {
		this.resumenAlmacen = resumenAlmacen;
	}

	public JLabel getResulExportAlm() {
		return resulExportAlm;
	}

	public void setResulExportAlm(JLabel resulExportAlm) {
		this.resulExportAlm = resulExportAlm;
	}

	public JPanel getSubPanelDeliveryNoteCompras() {
		return subPanelDeliveryNoteCompras;
	}

	public void setSubPanelDeliveryNoteCompras(JPanel subPanelDeliveryNoteCompras) {
		this.subPanelDeliveryNoteCompras = subPanelDeliveryNoteCompras;
	}

	public JButton getBotonCheckDeliNoteCom() {
		return botonCheckDeliNoteCom;
	}

	public void setBotonCheckDeliNoteCom(JButton botonCheckDeliNoteCom) {
		this.botonCheckDeliNoteCom = botonCheckDeliNoteCom;
	}

	public JLabel getJLnumAlbaranCom() {
		return JLnumAlbaranCom;
	}

	public void setJLnumAlbaranCom(JLabel jLnumAlbaranCom) {
		JLnumAlbaranCom = jLnumAlbaranCom;
	}

	public JLabel getJLCustomerDomCom() {
		return JLCustomerDomCom;
	}

	public void setJLCustomerDomCom(JLabel jLCustomerDomCom) {
		JLCustomerDomCom = jLCustomerDomCom;
	}

	public JLabel getJLDomohogarCom() {
		return JLDomohogarCom;
	}

	public void setJLDomohogarCom(JLabel jLDomohogarCom) {
		JLDomohogarCom = jLDomohogarCom;
	}

	public JLabel getJLDateCom() {
		return JLDateCom;
	}

	public void setJLDateCom(JLabel jLDateCom) {
		JLDateCom = jLDateCom;
	}

	public JLabel getJLDateComRow() {
		return JLDateComRow;
	}

	public void setJLDateComRow(JLabel jLDateComRow) {
		JLDateComRow = jLDateComRow;
	}

	public JLabel getJLDeliveryNoteCompras() {
		return JLDeliveryNoteCompras;
	}

	public void setJLDeliveryNoteCompras(JLabel jLDeliveryNoteCompras) {
		JLDeliveryNoteCompras = jLDeliveryNoteCompras;
	}

	public JLabel getJLSuplierCom() {
		return JLSuplierCom;
	}

	public void setJLSuplierCom(JLabel jLSuplierCom) {
		JLSuplierCom = jLSuplierCom;
	}

	public JLabel getJLSuplierComRow() {
		return JLSuplierComRow;
	}

	public void setJLSuplierComRow(JLabel jLSuplierComRow) {
		JLSuplierComRow = jLSuplierComRow;
	}

	public JLabel getJLSuplierCode() {
		return JLSuplierCode;
	}

	public void setJLSuplierCode(JLabel jLSuplierCode) {
		JLSuplierCode = jLSuplierCode;
	}

	public JLabel getJLSuplierCodeRow() {
		return JLSuplierCodeRow;
	}

	public void setJLSuplierCodeRow(JLabel jLSuplierCodeRow) {
		JLSuplierCodeRow = jLSuplierCodeRow;
	}

	public JLabel getJLTotalAccountCom() {
		return JLTotalAccountCom;
	}

	public void setJLTotalAccountCom(JLabel jLTotalAccountCom) {
		JLTotalAccountCom = jLTotalAccountCom;
	}

	public JLabel getJLTotalAccountComSuma() {
		return JLTotalAccountComSuma;
	}

	public void setJLTotalAccountComSuma(JLabel jLTotalAccountComSuma) {
		JLTotalAccountComSuma = jLTotalAccountComSuma;
	}

	public JButton getBotonCheckDeliNoteComDelete() {
		return botonCheckDeliNoteComDelete;
	}

	public void setBotonCheckDeliNoteComDelete(JButton botonCheckDeliNoteComDelete) {
		this.botonCheckDeliNoteComDelete = botonCheckDeliNoteComDelete;
	}

	public JPanel getSubPanelComprasDelete() {
		return subPanelComprasDelete;
	}

	public void setSubPanelComprasDelete(JPanel subPanelComprasDelete) {
		this.subPanelComprasDelete = subPanelComprasDelete;
	}

	public JLabel getJLDeliveryNoteDeleteCom() {
		return JLDeliveryNoteDeleteCom;
	}

	public void setJLDeliveryNoteDeleteCom(JLabel jLDeliveryNoteDeleteCom) {
		JLDeliveryNoteDeleteCom = jLDeliveryNoteDeleteCom;
	}

	public JLabel getJLnumAlbaranDeleteCom() {
		return JLnumAlbaranDeleteCom;
	}

	public void setJLnumAlbaranDeleteCom(JLabel jLnumAlbaranDeleteCom) {
		JLnumAlbaranDeleteCom = jLnumAlbaranDeleteCom;
	}

	public JButton getBotonDeleteLineaAlbaran() {
		return botonDeleteLineaAlbaran;
	}

	public void setBotonDeleteLineaAlbaran(JButton botonDeleteLineaAlbaran) {
		this.botonDeleteLineaAlbaran = botonDeleteLineaAlbaran;
	}

	public JButton getBotonDeleteAlabaran() {
		return botonDeleteAlabaran;
	}

	public void setBotonDeleteAlabaran(JButton botonDeleteAlabaran) {
		this.botonDeleteAlabaran = botonDeleteAlabaran;
	}

	public JLabel getJLselecJTableComDeleteLineAlbaran() {
		return JLselecJTableComDeleteLineAlbaran;
	}

	public void setJLselecJTableComDeleteLineAlbaran(JLabel jLselecJTableComDeleteLineAlbaran) {
		JLselecJTableComDeleteLineAlbaran = jLselecJTableComDeleteLineAlbaran;
	}

	public JLabel getJLselecJTableComDeleteAlbaran() {
		return JLselecJTableComDeleteAlbaran;
	}

	public void setJLselecJTableComDeleteAlbaran(JLabel jLselecJTableComDeleteAlbaran) {
		JLselecJTableComDeleteAlbaran = jLselecJTableComDeleteAlbaran;
	}

	public JTextField getInsertUsuarioPCEmpleado() {
		return insertUsuarioPCEmpleado;
	}

	public void setInsertUsuarioPCEmpleado(JTextField insertUsuarioPCEmpleado) {
		this.insertUsuarioPCEmpleado = insertUsuarioPCEmpleado;
	}

	public JLabel getInsertRutaexportVentas() {
		return insertRutaexportVentas;
	}

	public void setInsertRutaexportVentas(JLabel insertRutaexportVentas) {
		this.insertRutaexportVentas = insertRutaexportVentas;
	}

	public JTextField getInsertUsuarioPCVentas() {
		return insertUsuarioPCVentas;
	}

	public void setInsertUsuarioPCVentas(JTextField insertUsuarioPCVentas) {
		this.insertUsuarioPCVentas = insertUsuarioPCVentas;
	}

	public JLabel getInsertRutaExportAlm() {
		return insertRutaExportAlm;
	}

	public void setInsertRutaExportAlm(JLabel insertRutaExportAlm) {
		this.insertRutaExportAlm = insertRutaExportAlm;
	}

	public JTextField getInsertUsuarioPCAlmacen() {
		return insertUsuarioPCAlmacen;
	}

	public void setInsertUsuarioPCAlmacen(JTextField insertUsuarioPCAlmacen) {
		this.insertUsuarioPCAlmacen = insertUsuarioPCAlmacen;
	}

	public JLabel getLabelPreguntaCambioEmp() {
		return labelPreguntaCambioEmp;
	}

	public void setLabelPreguntaCambioEmp(JLabel labelPreguntaCambioEmp) {
		this.labelPreguntaCambioEmp = labelPreguntaCambioEmp;
	}

	public JLabel getJLabelNuevaCompra() {
		return JLabelNuevaCompra;
	}

	public void setJLabelNuevaCompra(JLabel jLabelNuevaCompra) {
		JLabelNuevaCompra = jLabelNuevaCompra;
	}

	public JLabel getJLslectorJTableCom() {
		return JLslectorJTableCom;
	}

	public void setJLslectorJTableCom(JLabel jLslectorJTableCom) {
		JLslectorJTableCom = jLslectorJTableCom;
	}

	public JLabel getJLselecJTableComDelete() {
		return JLselecJTableComDelete;
	}

	public void setJLselecJTableComDelete(JLabel jLselecJTableComDelete) {
		JLselecJTableComDelete = jLselecJTableComDelete;
	}

	public JPanel getSubPanelBills() {
		return subPanelBills;
	}

	public void setSubPanelBills(JPanel subPanelBills) {
		this.subPanelBills = subPanelBills;
	}

	public JPanel getSubPanelVentasDelete() {
		return subPanelVentasDelete;
	}

	public void setSubPanelVentasDelete(JPanel subPanelVentasDelete) {
		this.subPanelVentasDelete = subPanelVentasDelete;
	}

	public JLabel getJLabelNuevaVenta() {
		return JLabelNuevaVenta;
	}

	public void setJLabelNuevaVenta(JLabel jLabelNuevaVenta) {
		JLabelNuevaVenta = jLabelNuevaVenta;
	}

	public JLabel getExportVentas() {
		return exportVentas;
	}

	public void setExportVentas(JLabel exportVentas) {
		this.exportVentas = exportVentas;
	}

	public JLabel getJLslectorJTableBills() {
		return JLslectorJTableBills;
	}

	public void setJLslectorJTableBills(JLabel jLslectorJTableBills) {
		JLslectorJTableBills = jLslectorJTableBills;
	}

	public JLabel getJLBills() {
		return JLBills;
	}

	public void setJLBills(JLabel jLBills) {
		JLBills = jLBills;
	}

	public JLabel getJLnumBill() {
		return JLnumBill;
	}

	public void setJLnumBill(JLabel jLnumBill) {
		JLnumBill = jLnumBill;
	}

	public JLabel getJLCustomer() {
		return JLCustomer;
	}

	public void setJLCustomer(JLabel jLCustomer) {
		JLCustomer = jLCustomer;
	}

	public JLabel getJLCustomerRow() {
		return JLCustomerRow;
	}

	public void setJLCustomerRow(JLabel jLCustomerRow) {
		JLCustomerRow = jLCustomerRow;
	}

	public JLabel getJLDate() {
		return JLDate;
	}

	public void setJLDate(JLabel jLDate) {
		JLDate = jLDate;
	}

	public JLabel getJLDateRow() {
		return JLDateRow;
	}

	public void setJLDateRow(JLabel jLDateRow) {
		JLDateRow = jLDateRow;
	}

	public JLabel getJLCustomerName() {
		return JLCustomerName;
	}

	public void setJLCustomerName(JLabel jLCustomerName) {
		JLCustomerName = jLCustomerName;
	}

	public JLabel getJLCustomerNameRow() {
		return JLCustomerNameRow;
	}

	public void setJLCustomerNameRow(JLabel jLCustomerNameRow) {
		JLCustomerNameRow = jLCustomerNameRow;
	}

	public JLabel getJLTotalAmount() {
		return JLTotalAmount;
	}

	public void setJLTotalAmount(JLabel jLTotalAmount) {
		JLTotalAmount = jLTotalAmount;
	}

	public JLabel getJLTotalAmountSum() {
		return JLTotalAmountSum;
	}

	public void setJLTotalAmountSum(JLabel jLTotalAmountSum) {
		JLTotalAmountSum = jLTotalAmountSum;
	}

	public JButton getBotonCheckBill() {
		return botonCheckBill;
	}

	public void setBotonCheckBill(JButton botonCheckBill) {
		this.botonCheckBill = botonCheckBill;
	}

	public JScrollPane getBarraCRM() {
		return barraCRM;
	}

	public void setBarraCRM(JScrollPane barraCRM) {
		this.barraCRM = barraCRM;
	}

	public JLabel getLabelPreguntaCambioProv() {
		return labelPreguntaCambioProv;
	}

	public void setLabelPreguntaCambioProv(JLabel labelPreguntaCambioProv) {
		this.labelPreguntaCambioProv = labelPreguntaCambioProv;
	}

	public JLabel getExportResAlm() {
		return exportResAlm;
	}

	public void setExportResAlm(JLabel exportResAlm) {
		this.exportResAlm = exportResAlm;
	}

	public JLabel getLabelPreguntaCambioCliente() {
		return labelPreguntaCambioCliente;
	}

	public void setLabelPreguntaCambioCliente(JLabel labelPreguntaCambioCliente) {
		this.labelPreguntaCambioCliente = labelPreguntaCambioCliente;
	}

	public JButton getBotonBillsVentas() {
		return botonBillsVentas;
	}

	public void setBotonBillsVentas(JButton botonBillsVentas) {
		this.botonBillsVentas = botonBillsVentas;
	}

	public JLabel getJLselecJTableVenDelete() {
		return JLselecJTableVenDelete;
	}

	public void setJLselecJTableVenDelete(JLabel jLselecJTableVenDelete) {
		JLselecJTableVenDelete = jLselecJTableVenDelete;
	}

	public JLabel getJLBillDeleteVen() {
		return JLBillDeleteVen;
	}

	public void setJLBillDeleteVen(JLabel jLBillDeleteVen) {
		JLBillDeleteVen = jLBillDeleteVen;
	}

	public JLabel getJLnumFacturaDeleteVen() {
		return JLnumFacturaDeleteVen;
	}

	public void setJLnumFacturaDeleteVen(JLabel jLnumFacturaDeleteVen) {
		JLnumFacturaDeleteVen = jLnumFacturaDeleteVen;
	}

	public JLabel getJLselecJTableVenDeleteLineFactura() {
		return JLselecJTableVenDeleteLineFactura;
	}

	public void setJLselecJTableVenDeleteLineFactura(JLabel jLselecJTableVenDeleteLineFactura) {
		JLselecJTableVenDeleteLineFactura = jLselecJTableVenDeleteLineFactura;
	}

	public JLabel getJLselecJTableVenDeleteFactura() {
		return JLselecJTableVenDeleteFactura;
	}

	public void setJLselecJTableVenDeleteFactura(JLabel jLselecJTableVenDeleteFactura) {
		JLselecJTableVenDeleteFactura = jLselecJTableVenDeleteFactura;
	}

	public JButton getBotonCheckBillVenDelete() {
		return botonCheckBillVenDelete;
	}

	public void setBotonCheckBillVenDelete(JButton botonCheckBillVenDelete) {
		this.botonCheckBillVenDelete = botonCheckBillVenDelete;
	}

	public JButton getBotonDeleteLineaFactura() {
		return botonDeleteLineaFactura;
	}

	public void setBotonDeleteLineaFactura(JButton botonDeleteLineaFactura) {
		this.botonDeleteLineaFactura = botonDeleteLineaFactura;
	}

	public JButton getBotonDeleteFactura() {
		return botonDeleteFactura;
	}

	public void setBotonDeleteFactura(JButton botonDeleteFactura) {
		this.botonDeleteFactura = botonDeleteFactura;
	}

	public JTable getTablaCRM() {
		return tablaCRM;
	}

	public void setTablaCRM(JTable tablaCRM) {
		this.tablaCRM = tablaCRM;
	}

	public JPanel getPanelNomina() {
		return panelNomina;
	}

	public void setPanelNomina(JPanel panelNomina) {
		this.panelNomina = panelNomina;
	}

	public JLabel getJLUsuarioNomina() {
		return JLUsuarioNomina;
	}

	public void setJLUsuarioNomina(JLabel jLUsuarioNomina) {
		JLUsuarioNomina = jLUsuarioNomina;
	}

	public JComboBox<String> getComboAnnoNomina() {
		return comboAnnoNomina;
	}

	public void setComboAnnoNomina(JComboBox<String> comboAnnoNomina) {
		this.comboAnnoNomina = comboAnnoNomina;
	}

	public JComboBox<String> getComboMesNomina() {
		return comboMesNomina;
	}

	public void setComboMesNomina(JComboBox<String> comboMesNomina) {
		this.comboMesNomina = comboMesNomina;
	}

	public JButton getBotonCheckNom() {
		return botonCheckNom;
	}

	public void setBotonCheckNom(JButton botonCheckNom) {
		this.botonCheckNom = botonCheckNom;
	}

	public JPanel getSubPanelProvExport() {
		return subPanelProvExport;
	}

	public void setSubPanelProvExport(JPanel subPanelProvExport) {
		this.subPanelProvExport = subPanelProvExport;
	}

	public JTextField getInsertUsuarioPCProveedor() {
		return insertUsuarioPCProveedor;
	}

	public void setInsertUsuarioPCProveedor(JTextField insertUsuarioPCProveedor) {
		this.insertUsuarioPCProveedor = insertUsuarioPCProveedor;
	}

	public JButton getBotonExportProveedor() {
		return botonExportProveedor;
	}

	public void setBotonExportProveedor(JButton botonExportProveedor) {
		this.botonExportProveedor = botonExportProveedor;
	}

	public JButton getBotonExportProvFinal() {
		return botonExportProvFinal;
	}

	public void setBotonExportProvFinal(JButton botonExportProvFinal) {
		this.botonExportProvFinal = botonExportProvFinal;
	}

	public JLabel getExportProveedor() {
		return exportProveedor;
	}

	public void setExportProveedor(JLabel exportProveedor) {
		this.exportProveedor = exportProveedor;
	}

	public JLabel getInsertRutaExportProv() {
		return insertRutaExportProv;
	}

	public void setInsertRutaExportProv(JLabel insertRutaExportProv) {
		this.insertRutaExportProv = insertRutaExportProv;
	}

	public JLabel getResulExportProv() {
		return resulExportProv;
	}

	public void setResulExportProv(JLabel resulExportProv) {
		this.resulExportProv = resulExportProv;
	}
}
