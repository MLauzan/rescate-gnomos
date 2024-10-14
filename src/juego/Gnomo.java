package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Gnomo extends Personaje {

	public Gnomo(double x, double y, double alto, double ancho, double velocidad, double vida, double daño, Image imagen) {
		super(x, y, alto, ancho, velocidad, vida, daño, imagen);
		// TODO Auto-generated constructor stub
	}
	
	public void dibujar(Entorno entorno) {
		Image imagenGnomo = Herramientas.cargarImagen("imagenes/gnomo.png");
		entorno.dibujarImagen(imagenGnomo, this.getX(), this.getY(), Math.toRadians(0), 0.05);
	}
		
}
