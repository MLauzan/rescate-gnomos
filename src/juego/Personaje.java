package juego;

import java.awt.Image;

public class Personaje {
	private double x;
	private double y;
	private double alto;
	private double ancho;
	private double velocidad;
	private double vida;
	private double daño;
	private Image imagen;

	public Personaje(double x, double y, double alto, double ancho, double velocidad, double vida, double daño, Image imagen) {
		this.setX(x);
		this.setY(y);
		this.setAlto(alto);
		this.setAncho(ancho);
		this.setVelocidad(velocidad);
		this.setVida(vida);
		this.setDaño(daño);
		this.setImagen(imagen);
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

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
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

	public double getDaño() {
		return daño;
	}

	public void setDaño(double daño) {
		this.daño = daño;
	}
}
