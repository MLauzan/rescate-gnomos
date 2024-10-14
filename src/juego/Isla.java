package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Isla extends Estructura {

	public Isla(double x, double y, double alto, double ancho, Image Imagen) {
		super(x, y, alto, ancho, Imagen);
		// TODO Auto-generated constructor stub
	}
	
	public void dibujar(Entorno entorno) {
		Color miColor = Color.decode("#556B2F"); // Verde musgo oscuro
		entorno.dibujarRectangulo(this.getX(), this.getY(),this.getAncho(),this.getAlto(),0,miColor);
		Image imagenIsla = Herramientas.cargarImagen("imagenes/pasto.png");
		entorno.dibujarImagen(imagenIsla, this.getX(), this.getY()-10, Math.toRadians(0), 0.08);
	}

}
