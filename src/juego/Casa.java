package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Casa {
	private double x;
	private double y;
	private double alto;
	private double ancho;
	private Image imagen;

	public Casa(double x, double y, double alto, double ancho, Image Imagen) {
		this.setX(x);
		this.setY(y);
		this.setAlto(alto);
		this.setAncho(ancho);
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

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}

	public void dibujar(Entorno entorno) {
		Image imagenCasa = Herramientas.cargarImagen("imagenes/casa.png");
		entorno.dibujarImagen(imagenCasa, this.getX(), this.getY(), Math.toRadians(0), 0.06);
	}
}
