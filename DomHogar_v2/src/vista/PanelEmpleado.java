package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controlador.AccesoDB;
import controlador.Eventos;

public class PanelEmpleado extends JPanel {
	
	private static final long serialVersionUID = 1L;

	//Atributos de RECURSOS / EMPLEADOS
	private Eventos controlador;//para que funcione la llamada a la ventana modal debemos pasarle el objeto Gestor Eventos a su constructor
	
	private JPanel panelEmpleado;
	private JPanel subPanelEmpInsertar;
	private JScrollPane barraEmpleados;
	private JTable tablaEmpleados;
	private JButton botonInsetEmpleado;
	private JButton botonActualizarEmpleado;
	private JButton botonBorrarEmpleado;
	private JButton botonExpEmplFichero;
	private JButton botonInsertFinal;
	private JTextField insertNomEmpl;
	private JTextField insertApelEmpl;
	private JTextField insertNIFEmp;
	private JTextField insertPhoneEmp;
	private JTextField insertEmailEmp;
	private JTextField insertUserEmp;
	private JTextField insertPassEmp;
	private JTextField insertPerfilEmp;
	private JLabel resulInsertEmp;
		
	public PanelEmpleado(Ventana ventana, Eventos controlador) {
		super(); //enviamos al constructor padre, los parámetros de este.
		this.controlador = controlador; //vinculamos el objeto controlador que hemos pasado por parámetro a su referencia
		
		setBackground(new Color(204,233,235));
		setSize(1000,700);
		setLayout(null);
		inicializarComponentes();
	}

	private void inicializarComponentes() {

		// PANEL EMPLEADO
		
		  panelEmpleado = new JPanel(); 
		  panelEmpleado.setBackground(new Color(204, 233, 235));
		  panelEmpleado.setBounds(240, 20, 800, 350);
		  panelEmpleado.setLayout(null); 
		  add(panelEmpleado);
		 
		
		// Construimos la tabla empleados

		barraEmpleados = new JScrollPane();
		barraEmpleados.setBounds(40, 80, 680, 300);
		panelEmpleado.add(barraEmpleados);
		
		String titulosEmpleados[] = {"Nombre", "Apellidos", "e-mail", "NIF", "Telefono"};
		String infoEmpleados[][] = AccesoDB.obtenerMatrizEmpleados();
		
		tablaEmpleados = new JTable();		
		
	    DefaultTableModel modelo = new DefaultTableModel(infoEmpleados, titulosEmpleados);
	    tablaEmpleados.setModel(modelo);
	    
	    //¿¿¿Cómo refrescar la Tabla?????????
	    
	    modelo.fireTableDataChanged();
	    //modelo.addTableModelListener(getTablaEmpleados());
	    
	    tablaEmpleados.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaEmpleados.getColumnModel().getColumn(1).setPreferredWidth(115);
		tablaEmpleados.getColumnModel().getColumn(2).setPreferredWidth(140);
		barraEmpleados.setViewportView(tablaEmpleados);
			
		//Botones panel EMPLEADOS
		
		botonInsetEmpleado = new JButton("NEW EMPLOYEE");//Creamos el componente
		botonInsetEmpleado.setBounds(20,20,110,42);
		botonInsetEmpleado.setBorder(null); //Eliminamos el borde
		//Falta incluir la imagen del botï¿½n
		//botonInsetEmpleado.setIcon(new ImageIcon("imagenes/login.png"));
		botonInsetEmpleado.setBackground(Color.BLUE);
		panelEmpleado.add(botonInsetEmpleado);//Anadimos 
		
		botonActualizarEmpleado = new JButton("UPDATE EMPLOYEE");//Creamos el componente
		botonActualizarEmpleado.setBounds(150,20,130,42);
		botonActualizarEmpleado.setBorder(null); //Eliminamos el borde
		//Falta incluir la imagen del botï¿½n
		//botonInsetEmpleado.setIcon(new ImageIcon("imagenes/login.png"));
		botonActualizarEmpleado.setBackground(Color.BLUE);
		panelEmpleado.add(botonActualizarEmpleado);//Anadimos 
		
		botonBorrarEmpleado = new JButton("DELETE EMPLOYEE");//Creamos el componente
		botonBorrarEmpleado.setBounds(300,20,130,42);
		botonBorrarEmpleado.setBorder(null); //Eliminamos el borde
		//Falta incluir la imagen del botï¿½n
		//botonInsetEmpleado.setIcon(new ImageIcon("imagenes/login.png"));
		botonBorrarEmpleado.setBackground(Color.BLUE);
		panelEmpleado.add(botonBorrarEmpleado);//Anadimos 
		
		botonExpEmplFichero = new JButton("EXPORT FILE EMPLOYEE");//Creamos el componente
		botonExpEmplFichero.setBounds(450,20,160,42);
		botonExpEmplFichero.setBorder(null); //Eliminamos el borde
		//Falta incluir la imagen del botï¿½n
		//botonInsetEmpleado.setIcon(new ImageIcon("imagenes/login.png"));
		botonExpEmplFichero.setBackground(Color.green);
		panelEmpleado.add(botonExpEmplFichero);//Anadimos 
		
		//SUBPANEL INSERTAR
		
		subPanelEmpInsertar = new JPanel();
		subPanelEmpInsertar.setBounds(240, 400, 800, 350);
		subPanelEmpInsertar.setBackground(new Color(204,233,235));
		subPanelEmpInsertar.setLayout(null);
		add(subPanelEmpInsertar);
				
		insertNomEmpl = new JTextField();//Creamos el componente
		TextPrompt placeholder = new TextPrompt("Nombre Empleado", insertNomEmpl);
	    placeholder.changeAlpha(0.75f);
	    placeholder.changeStyle(Font.ITALIC);
		insertNomEmpl.setBounds(20,20,200,30);//Posicionamos
		insertNomEmpl.setBorder(null); //Eliminamos el borde
		insertNomEmpl.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		insertNomEmpl.setBackground(new Color(0,157,233)); //Color de fondo
		insertNomEmpl.setForeground(new Color(255,255,255));//Color del texto
		subPanelEmpInsertar.add(insertNomEmpl);//Anadimos
		
		insertApelEmpl = new JTextField();//Creamos el componente
		TextPrompt placeholder1 = new TextPrompt("Apellidos Empleado", insertApelEmpl);
	    placeholder1.changeAlpha(0.75f);
	    placeholder1.changeStyle(Font.ITALIC);
	    insertApelEmpl.setBounds(250,20,430,30);//Posicionamos
	    insertApelEmpl.setBorder(null); //Eliminamos el borde
	    insertApelEmpl.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertApelEmpl.setBackground(new Color(0,157,233)); //Color de fondo
	    insertApelEmpl.setForeground(new Color(255,255,255));//Color del texto
		subPanelEmpInsertar.add(insertApelEmpl);//Anadimos
		
		insertNIFEmp = new JTextField();//Creamos el componente
		TextPrompt placeholder2 = new TextPrompt("NIF", insertNIFEmp);
	    placeholder2.changeAlpha(0.75f);
	    placeholder2.changeStyle(Font.ITALIC);
	    insertNIFEmp.setBounds(20,70,120,30);//Posicionamos
	    insertNIFEmp.setBorder(null); //Eliminamos el borde
	    insertNIFEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertNIFEmp.setBackground(new Color(0,157,233)); //Color de fondo
	    insertNIFEmp.setForeground(new Color(255,255,255));//Color del texto
		subPanelEmpInsertar.add(insertNIFEmp);//Anadimos
		
		insertPhoneEmp = new JTextField();//Creamos el componente
		TextPrompt placeholder3 = new TextPrompt("Telefono", insertPhoneEmp);
	    placeholder3.changeAlpha(0.75f);
	    placeholder3.changeStyle(Font.ITALIC);
	    insertPhoneEmp.setBounds(170,70,120,30);//Posicionamos
	    insertPhoneEmp.setBorder(null); //Eliminamos el borde
	    insertPhoneEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertPhoneEmp.setBackground(new Color(0,157,233)); //Color de fondo
	    insertPhoneEmp.setForeground(new Color(255,255,255));//Color del texto
		subPanelEmpInsertar.add(insertPhoneEmp);//Anadimos
		
		insertEmailEmp = new JTextField();//Creamos el componente
		TextPrompt placeholder4 = new TextPrompt("e-Mail", insertEmailEmp);
	    placeholder4.changeAlpha(0.75f);
	    placeholder4.changeStyle(Font.ITALIC);
	    insertEmailEmp.setBounds(320,70,360,30);//Posicionamos
	    insertEmailEmp.setBorder(null); //Eliminamos el borde
	    insertEmailEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertEmailEmp.setBackground(new Color(0,157,233)); //Color de fondo
	    insertEmailEmp.setForeground(new Color(255,255,255));//Color del texto
		subPanelEmpInsertar.add(insertEmailEmp);//Anadimos
		
		insertUserEmp = new JTextField();//Creamos el componente
		TextPrompt placeholder5 = new TextPrompt("User", insertUserEmp);
	    placeholder5.changeAlpha(0.75f);
	    placeholder5.changeStyle(Font.ITALIC);
	    insertUserEmp.setBounds(20,120,200,30);//Posicionamos
	    insertUserEmp.setBorder(null); //Eliminamos el borde
	    insertUserEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertUserEmp.setBackground(new Color(0,157,233)); //Color de fondo
	    insertUserEmp.setForeground(new Color(255,255,255));//Color del texto
		subPanelEmpInsertar.add(insertUserEmp);//Anadimos
		
		insertPassEmp = new JTextField();//Creamos el componente
		TextPrompt placeholder6 = new TextPrompt("Password", insertPassEmp);
	    placeholder6.changeAlpha(0.75f);
	    placeholder6.changeStyle(Font.ITALIC);
	    insertPassEmp.setBounds(250,120,200,30);//Posicionamos
	    insertPassEmp.setBorder(null); //Eliminamos el borde
	    insertPassEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertPassEmp.setBackground(new Color(0,157,233)); //Color de fondo
	    insertPassEmp.setForeground(new Color(255,255,255));//Color del texto
		subPanelEmpInsertar.add(insertPassEmp);//Anadimos	
		
		insertPerfilEmp = new JTextField();//Creamos el componente
		TextPrompt placeholder7 = new TextPrompt("Perfil", insertPerfilEmp);
	    placeholder7.changeAlpha(0.75f);
	    placeholder7.changeStyle(Font.ITALIC);
	    insertPerfilEmp.setBounds(480,120,200,30);//Posicionamos
	    insertPerfilEmp.setBorder(null); //Eliminamos el borde
	    insertPerfilEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
	    insertPerfilEmp.setBackground(new Color(0,157,233)); //Color de fondo
	    insertPerfilEmp.setForeground(new Color(255,255,255));//Color del texto
		subPanelEmpInsertar.add(insertPerfilEmp);//Anadimos	
		
		botonInsertFinal = new JButton("INSERT");//Creamos el componente
		botonInsertFinal.setBounds(20,170,110,42);
		botonInsertFinal.setBorder(null); //Eliminamos el borde
		//Falta incluir la imagen del botï¿½n
		//botonInsetEmpleado.setIcon(new ImageIcon("imagenes/login.png"));
		botonInsertFinal.setBackground(Color.BLUE);
		subPanelEmpInsertar.add(botonInsertFinal);//Anadimos 
		
		resulInsertEmp = new JLabel();//Creamos el componente
		resulInsertEmp.setBounds(20,222,500,30);//Posicionamos
		resulInsertEmp.setBorder(null); //Eliminamos el borde
		resulInsertEmp.setFont(new Font("Segoe UI",Font.BOLD,16));//Damos formato al contenido
		resulInsertEmp.setForeground(new Color(255,255,255));//Color del texto
		subPanelEmpInsertar.add(resulInsertEmp);//Anadimos	

	}
	
	// METODO PARA PONER A LA ESCUCHA LOS EVENTOS
		public void Eventos(Eventos manejador) {			
			botonInsetEmpleado.addMouseListener(manejador);
			botonInsertFinal.addMouseListener(manejador);		
		}

		public Eventos getControlador() {
			return controlador;
		}

		public void setControlador(Eventos controlador) {
			this.controlador = controlador;
		}

	
	  public JPanel getPanelEmpleado() { return panelEmpleado; }
	  
	  public void setPanelEmpleado(JPanel panelEmpleado) { this.panelEmpleado =
	  panelEmpleado; }
	 

		public JPanel getSubPanelEmpInsertar() {
			return subPanelEmpInsertar;
		}

		public void setSubPanelEmpInsertar(JPanel subPanelEmpInsertar) {
			this.subPanelEmpInsertar = subPanelEmpInsertar;
		}

		public JScrollPane getBarraEmpleados() {
			return barraEmpleados;
		}

		public void setBarraEmpleados(JScrollPane barraEmpleados) {
			this.barraEmpleados = barraEmpleados;
		}

		public JTable getTablaEmpleados() {
			return tablaEmpleados;
		}

		public void setTablaEmpleados(JTable tablaEmpleados) {
			this.tablaEmpleados = tablaEmpleados;
		}

		public JButton getBotonInsetEmpleado() {
			return botonInsetEmpleado;
		}

		public void setBotonInsetEmpleado(JButton botonInsetEmpleado) {
			this.botonInsetEmpleado = botonInsetEmpleado;
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

		public JButton getBotonInsertFinal() {
			return botonInsertFinal;
		}

		public void setBotonInsertFinal(JButton botonInsertFinal) {
			this.botonInsertFinal = botonInsertFinal;
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

		public JTextField getInsertPerfilEmp() {
			return insertPerfilEmp;
		}

		public void setInsertPerfilEmp(JTextField insertPerfilEmp) {
			this.insertPerfilEmp = insertPerfilEmp;
		}

		public JLabel getResulInsertEmp() {
			return resulInsertEmp;
		}

		public void setResulInsertEmp(JLabel resulInsertEmp) {
			this.resulInsertEmp = resulInsertEmp;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
}

				