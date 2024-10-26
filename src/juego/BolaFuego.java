package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class BolaFuego {
	private double x;
	private double y;
	private double alto;
	private double ancho;
	private Image imagen;
	private boolean direc;
	
	
	public BolaFuego(double x, double y, double alto, double ancho, Image imagen, boolean direc) {
		super();
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.imagen = imagen;
		this.direc= direc;
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	public double getAlto() {
		return alto;
	}


	public void setAlto(double alto) {
		this.alto = alto;
	}


	public double getAncho() {
		return ancho;
	}


	public void setAncho(double ancho) {
		this.ancho = ancho;
	}


	public Image getImagen() {
		return imagen;
	}


	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}
	
	
	public boolean isDirec() {
		return direc;
	}


	public void setDirec(boolean direc) {
		this.direc = direc;
	}


	public void dibujar(Entorno entorno) {
		Image imagenBolaFuego = Herramientas.cargarImagen("imagenes/bolaFuego.jpg");
		entorno.dibujarImagen(imagenBolaFuego, this.getX(), this.getY(), Math.toRadians(0), 0.02);
	}
	
	public void moverDerecha() {
		this.x = this.x + 5;
	}

	public void moverIzquierda() {
		this.x = this.x - 5;
	}
	public boolean dentroDelEntorno(Entorno entorno) {
		return this.y <= entorno.alto() && this.x <= entorno.ancho();
	}


}
