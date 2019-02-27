package dam.samuel.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
@Entity
@Table(name = "valoracion")
public class Valoracion implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Dato autogenerado que identifica de forma �nica al objeto entre los de su
	 * clase
	 */
	@Id
	@Column(name = "idValoracion")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idValoracion;

	/**
	 * Recomendaci�n del usuario que realiz� la valoraci�n
	 */
	@Column(name = "voto")
	@Type(type = "boolean")
	@NotNull
	private boolean positivo;

	/**
	 * Breve opini�n del usuario sobre el juego
	 */
	@Column(name = "comentario")
	@NotNull
	@Size(min = 5, max = 250)
	private String comentario;

	/**
	 * Identificador del juego al que hace referencia
	 */
	@ManyToOne
	@JoinColumn(name = "idJuego")
	@NotNull
	private Juego juego;

	/**
	 * Texto de la recomendaci�n del usuario
	 */
	@Transient
	private String tipoVoto;

	/**
	 * Constructor principal de la clase
	 * 
	 * @param voto       recomendaci�n del usuario
	 * @param comentario opini�n del usuario
	 */
	public Valoracion(boolean voto, String comentario) {
		this.positivo = voto;
		this.comentario = comentario;
	}

	/**
	 * Constructor est�ndar
	 */
	public Valoracion() {
		super();
	}

	/**
	 * Devuelve el identificador de la valoraci�n
	 * 
	 * @return idValoracion
	 */
	public int getIdValoracion() {
		return idValoracion;
	}

	/**
	 * Establece el identificador de la valoraci�n
	 * 
	 * @param idValoracion es el identificador del objeto
	 */
	public void setIdValoracion(int idValoracion) {
		this.idValoracion = idValoracion;
	}

	/**
	 * Devuelve la recomendaci�n del usuario
	 * 
	 * @return positivo
	 */
	public boolean isPositivo() {
		return positivo;
	}

	/**
	 * Establece la recomendaci�n hecha por el usuario
	 * 
	 * @param voto recomendaci�n del usuario
	 */
	public void setPositivo(boolean voto) {
		this.positivo = voto;
	}

	/**
	 * Devuelve la opini�n del usuario
	 * 
	 * @return comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * Establece la opini�n hecha por el usuario
	 * 
	 * @param comentario es la opini�n del usuario
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * Devuelve el juego al que hace referencia la valoraci�n
	 * 
	 * @return juego
	 */
	public Juego getJuego() {
		return juego;
	}

	/**
	 * Establece el juego al que hace referencia la valoraci�n
	 * 
	 * @param juego que el usuario ha valorado
	 */
	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	/**
	 * Genera un texto para las tablas con la recomendaci�n del usuario
	 * 
	 * @return tipoVoto
	 */
	public String getTipoVoto() {
		if (positivo) {
			tipoVoto = "positivo";
		} else {
			tipoVoto = "negativo";
		}

		return tipoVoto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idValoracion;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Valoracion other = (Valoracion) obj;
		if (idValoracion != other.idValoracion)
			return false;
		return true;
	}

	/**
	 * Muestra la informaci�n principal de la valoraci�n
	 */
	@Override
	public String toString() {
		return "Valoracion [idValoracion=" + idValoracion + ", positivo=" + positivo + ", comentario=" + comentario
				+ "]";
	}
}
