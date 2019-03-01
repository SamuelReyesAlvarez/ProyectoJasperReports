package dam.samuel;

import java.io.IOException;

import dam.samuel.modelo.HibernateUtil;
import dam.samuel.modelo.Juego;
import dam.samuel.vista.ControladorDetallesJuego;
import dam.samuel.vista.ControladorLogin;
import dam.samuel.vista.ControladorPrincipal;
import dam.samuel.vista.ControladorRegistroEmpresa;
import dam.samuel.vista.ControladorRegistroJuego;
import dam.samuel.vista.ControladorValorarJuego;
import dam.samuel.vista.ControladorVerDesarrolladores;
import dam.samuel.vista.ControladorVerJuegos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author Samuel Reyes Alvarez
 * 
 * @version 1.2.0 (27/02/2019)
 * 
 *          Esta aplicaci�n pretende ofrecer al usuario un sistema de consulta
 *          de datos sobre videojuegos y empresas que los desarrollan as� como
 *          la posibilidad de valorar positiva o negativamente el videojuego
 *          incluyendo un comentario personal. Incluye tambi�n un acceso para un
 *          Administrador el cual podr� ejercer las funciones de agregar nuevas
 *          empresas y videojuegos adem�s de modificar los datos de �stos y
 *          eliminar las valoraciones cuyo comentarios no sean correctos. Se
 *          pueden adem�s exportar datos de una pantalla a trav�s de informes o
 *          reportes
 *
 */
public class MainApp extends Application {

	private Stage stage;
	private Stage dialogPrincipal;
	private Stage dialogVerJuegos;
	private ControladorVerJuegos verJuegos;

	private boolean esAdministrador;

	/**
	 * Este m�todo es ejecutado desde el Main para abrir la ventana principal de la
	 * aplicaci�n que en nuestro caso es el Login para el Administrador, los usuario
	 * pueden entrar dejando los campos en blanco
	 * 
	 * @param primaryStage es la ventana padre de la aplicaci�n, la cual si se
	 *                     cierra dara por finalizada la ejecuci�n del programa
	 */
	@Override
	public void start(Stage primaryStage) {

		try {
			stage = primaryStage;
			stage.setTitle("Inicio de sesion");

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/Login.fxml"));

			VBox anchor = (VBox) loader.load();
			Scene scene = new Scene(anchor);
			stage.setScene(scene);
			stage.setResizable(false);

			ControladorLogin login = loader.getController();
			login.setStage(this);

			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este m�todo se encarga de preparar y mostrar la ventana que contendr� un men�
	 * principal compuesto por botones, los cuales dar�n acceso a las ventanas de
	 * consulta de informaci�n y registro de nuevos Juegos y Empresa
	 * 
	 * @param esAdmin permite habilitar o deshabilitar las opciones de administrador
	 *                seg�n los datos de acceso desde el Login
	 */
	public void mostrarPrincipal(boolean esAdmin) {
		esAdministrador = esAdmin;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/Principal.fxml"));

			dialogPrincipal = new Stage();
			dialogPrincipal.setTitle("Valorator");
			dialogPrincipal.initOwner(stage);
			dialogPrincipal.initModality(Modality.WINDOW_MODAL);

			BorderPane border = (BorderPane) loader.load();
			Scene scene = new Scene(border);
			dialogPrincipal.setScene(scene);
			dialogPrincipal.setResizable(false);

			ControladorPrincipal principal = loader.getController();
			principal.setMainApp(this);
			principal.setDialog(dialogPrincipal);
			principal.controlarOpciones(esAdministrador);

			stage.hide();
			configurarSesion();
			dialogPrincipal.showAndWait();
			cerrarSesion();
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga y muestra la ventana que permite visualizar en una tabla los juegos
	 * registrados y sus datos principales. Posee la opci�n de visualizar seg�n el
	 * estilo del juego
	 */
	public void mostrarVerJuegos() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/VerJuegos.fxml"));

			dialogVerJuegos = new Stage();
			dialogVerJuegos.setTitle("Valorator");
			dialogVerJuegos.initOwner(dialogPrincipal);
			dialogVerJuegos.initModality(Modality.WINDOW_MODAL);

			BorderPane border = (BorderPane) loader.load();
			Scene scene = new Scene(border);
			dialogVerJuegos.setScene(scene);
			dialogVerJuegos.setResizable(false);

			verJuegos = loader.getController();
			verJuegos.setMainApp(this);
			verJuegos.setDialog(dialogVerJuegos);

			dialogVerJuegos.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga y muestra todas las empresas registradas en la aplicaci�n y, cuando se
	 * selecciona una, permite ver los juegos que ha desarrollado
	 */
	public void mostrarVerDesarrolladores() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/VerDesarrolladores.fxml"));

			Stage dialogVerDesarrolladores = new Stage();
			dialogVerDesarrolladores.setTitle("Valorator");
			dialogVerDesarrolladores.initOwner(dialogPrincipal);
			dialogVerDesarrolladores.initModality(Modality.WINDOW_MODAL);

			BorderPane border = (BorderPane) loader.load();
			Scene scene = new Scene(border);
			dialogVerDesarrolladores.setScene(scene);
			dialogVerDesarrolladores.setResizable(false);

			ControladorVerDesarrolladores verDesarrolladores = loader.getController();
			verDesarrolladores.setDialog(dialogVerDesarrolladores);

			dialogVerDesarrolladores.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga y muestra una ventana disponible para el administrador d�nde podr�
	 * registrar nuevos juegos tras cumplimentar un formulario con los datos de �ste
	 */
	public void mostrarNuevoJuego() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/RegistroJuego.fxml"));

			Stage dialogRegistroJuego = new Stage();
			dialogRegistroJuego.setTitle("Valorator");
			dialogRegistroJuego.initOwner(dialogPrincipal);
			dialogRegistroJuego.initModality(Modality.WINDOW_MODAL);

			BorderPane border = (BorderPane) loader.load();
			Scene scene = new Scene(border);
			dialogRegistroJuego.setScene(scene);
			dialogRegistroJuego.setResizable(false);

			ControladorRegistroJuego registroJuego = loader.getController();
			registroJuego.setDialog(dialogRegistroJuego);

			dialogRegistroJuego.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga y muestra la ventana para el administrador en la que podr� registrar
	 * nuevas empresas escribiendo el nombre de ella
	 */
	public void mostrarNuevoDesarrollador() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/RegistroEmpresa.fxml"));

			Stage dialogRegistroEmpresa = new Stage();
			dialogRegistroEmpresa.setTitle("Valorator");
			dialogRegistroEmpresa.initOwner(dialogPrincipal);
			dialogRegistroEmpresa.initModality(Modality.WINDOW_MODAL);

			BorderPane border = (BorderPane) loader.load();
			Scene scene = new Scene(border);
			dialogRegistroEmpresa.setScene(scene);
			dialogRegistroEmpresa.setResizable(false);

			ControladorRegistroEmpresa registroEmpresa = loader.getController();
			registroEmpresa.setDialog(dialogRegistroEmpresa);

			dialogRegistroEmpresa.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga y muestra una ventana en la que el usuario podr� valorar positiva o
	 * negativamente un juego seleccionado en la ventana VerJuegos y escribir un
	 * comentario sobre dicho juego
	 * 
	 * @param juego selecionado en la tabla de la ventana VerJuegos
	 */
	public void mostrarValorarJuego(Juego juego) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/ValorarJuego.fxml"));

			Stage dialogValorarJuego = new Stage();
			dialogValorarJuego.setTitle("Valorator");
			dialogValorarJuego.initOwner(dialogVerJuegos);
			dialogValorarJuego.initModality(Modality.WINDOW_MODAL);

			BorderPane border = (BorderPane) loader.load();
			Scene scene = new Scene(border);
			dialogValorarJuego.setScene(scene);
			dialogValorarJuego.setResizable(false);

			ControladorValorarJuego valorarJuego = loader.getController();
			valorarJuego.setJuego(juego);
			valorarJuego.setDialog(dialogValorarJuego);
			valorarJuego.mostrarDatosJuego();

			dialogValorarJuego.showAndWait();
			verJuegos.cargarPorEstilo();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga y muestra en una ventana todos los datos de un juego y una tabla con
	 * todas las valoraciones hechas por los usuarios. El administrador podr� adem�s
	 * borrar comentarios que no cumplan con un c�digo �tico y modificar los datos
	 * del juego
	 * 
	 * @param juego seleccionado en la tabla de la ventana VerJuegos
	 */
	public void mostrarDetallesJuego(Juego juego) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/DetallesJuego.fxml"));

			Stage dialogDetallesJuego = new Stage();
			dialogDetallesJuego.setTitle("Valorator");
			dialogDetallesJuego.initOwner(dialogVerJuegos);
			dialogDetallesJuego.initModality(Modality.WINDOW_MODAL);

			BorderPane border = (BorderPane) loader.load();
			Scene scene = new Scene(border);
			dialogDetallesJuego.setScene(scene);
			dialogDetallesJuego.setResizable(false);

			ControladorDetallesJuego detallesJuego = loader.getController();
			detallesJuego.setJuego(juego);
			detallesJuego.setDialog(dialogDetallesJuego);
			detallesJuego.controlarOpciones(esAdministrador);
			detallesJuego.cargarDetallesJuego();

			dialogDetallesJuego.showAndWait();
			verJuegos.cargarPorEstilo();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo que se ejecuta al iniciar la aplicaci�n y que lanza el m�todo que
	 * muestra la primera ventana
	 * 
	 * @param args son los par�metros que se pueden recibir al ejecutar la
	 *             aplicaci�n
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Cierra la sesi�n de hibernate que fue abierta para ejecutar la persistencia
	 * de datos con mysql
	 */
	public void cerrarSesion() {
		HibernateUtil.closeSessionFactory();
	}

	/**
	 * Abre una nueva sesi�n de hibernate con mysql para gestionar la persistencia
	 * de los datos generados y modificados en la aplicaci�n
	 */
	public void configurarSesion() {
		HibernateUtil.buildSessionFactory();
		HibernateUtil.openSessionAndBindToThread();
	}
}
