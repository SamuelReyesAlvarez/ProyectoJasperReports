package dam.samuel.vista;

import dam.samuel.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorPrincipal {

	private MainApp mainApp;
	private Stage dialogPrincipal;

	@FXML
	private Button btnNuevoJuego;
	@FXML
	private Button btnNuevoDesarrollador;

	/**
	 * Constructor est�ndar
	 */
	public ControladorPrincipal() {
	}

	/**
	 * Activa o desactiva las funciones de Administrador seg�n la autorizaci�n
	 * recibida
	 * 
	 * @param esAdmin indica si el usuario tiene autorizaci�n para realizar
	 *                funciones administrativas en la aplicaci�n
	 */
	public void controlarOpciones(boolean esAdmin) {
		if (!esAdmin) {
			btnNuevoJuego.setVisible(esAdmin);
			btnNuevoDesarrollador.setVisible(esAdmin);
		}
	}

	/**
	 * Recibe la clase principal de la aplicaci�n para gestionar la llamada a la
	 * siguiente ventana
	 * 
	 * @param mainApp es la clase principal de la aplicaci�n
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Recibe el marco de la ventana controlada por la clase
	 * 
	 * @param stage marco de la ventana Principal
	 */
	public void setDialog(Stage stage) {
		this.dialogPrincipal = stage;
	}

	/**
	 * Llama a la clase principal para cargar la ventana VerJuegos
	 */
	@FXML
	public void verJuegos() {
		mainApp.mostrarVerJuegos();
	}

	/**
	 * Llama a la clase principal para cargar la ventana VerDesarrolladores
	 */
	@FXML
	public void verDesarrolladores() {
		mainApp.mostrarVerDesarrolladores();
	}

	/**
	 * Llama a la clase principal para cargar la ventana RegistroJuego
	 */
	@FXML
	public void nuevoJuego() {
		mainApp.mostrarNuevoJuego();
	}

	/**
	 * Llama a la clase principal para cargar la ventana RegistroEmpresa
	 */
	@FXML
	public void nuevoDesarrollador() {
		mainApp.mostrarNuevoDesarrollador();
	}

	/**
	 * Cierra la ventana controlada por la clase y vuelve al Login
	 */
	@FXML
	public void volver() {
		dialogPrincipal.close();
	}

	/**
	 * Cierra la aplicaci�n por completo
	 */
	@FXML
	public void salir() {
		System.exit(0);
	}
}
