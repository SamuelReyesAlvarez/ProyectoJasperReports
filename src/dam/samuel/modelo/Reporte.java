package dam.samuel.modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class Reporte {

	private static final String RUTA_JASPER = "reportes/InformeGeneralJuegos.jasper";

	private JFrame ventana;

	/**
	 * Metodo encargado de crear el reporte a partir de los datos recibidos desde
	 * hibernate y lo muestra en una ventana temporal hasta que el usuario decide
	 * guardarlo o descartarlo
	 * 
	 * @param contenido Lista recibida desde el Query de hibernate con los datos a
	 *                  mostrar en el reporte
	 * @throws JRException lanzado si el reporte no se puede crear por cualquier
	 *                     motivo relacionado con JasperReport
	 */
	public void generarReporte(List<Juego> contenido) throws JRException {

		// Parametros principales para personalizar el reporte
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("autor", "Samuel Reyes Alvarez");
		parametros.put("titulo", "Informe General de Juegos");

		// Busca el fichero con el modelo del reporte y lo rellena con los datos
		// recibidos por parametro
		JasperPrint jasper = JasperFillManager.fillReport(RUTA_JASPER, parametros,
				new JRBeanCollectionDataSource(contenido));

		// Muestra el reporte en la ventana
		JRViewer viewer = new JRViewer(jasper);

		// Carga la ventana que contiene el reporte y la muestra
		ventana = new JFrame();
		viewer.setOpaque(true);
		viewer.setVisible(true);
		ventana.add(viewer);
		ventana.setSize(700, 500);
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
