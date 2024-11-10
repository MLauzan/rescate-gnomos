package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Escudo {
	private double x;
	private double y;
	private double alto;
	private double ancho;
	private double vida;
	private Image imagen;
	
	
	public Escudo(double x, double y, double alto, double ancho, double vida, Image imagen) {
		super();
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.vida = vida;
		this.imagen = imagen;
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


	public double getVida() {
		return vida;
	}


	public void setVida(double vida) {
		this.vida = vida;
	}


	public Image getImagen() {
		return imagen;
	}


	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}
	
	public void dibujar(Entorno entorno) {
		Image imagenEscudo = Herramientas.cargarImagen("imagenes/escudo.png");
		entorno.dibujarImagen(imagenEscudo, this.getX(), this.getY(), Math.toRadians(0), 0.07);
	}
	
	
	

}
