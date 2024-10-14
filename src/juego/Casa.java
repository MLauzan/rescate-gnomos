package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Casa extends Estructura {

	public Casa(double x, double y, double alto, double ancho, Image Imagen) {
		super(x, y, alto, ancho, Imagen);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public void dibujar(Entorno entorno) {
		Image imagenCasa = Herramientas.cargarImagen("imagenes/casa.png");
		entorno.dibujarImagen(imagenCasa, this.getX(), this.getY(), Math.toRadians(0), 0.06);
	}
}
