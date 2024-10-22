package juego;

import java.awt.Image;
import java.util.ArrayList;
import entorno.Entorno;
import entorno.Herramientas;

public class Gnomo {
	private double x;
	private double y;
	private double alto;
	private double ancho;
	private double velocidad;
	private double vida;
	private double daño;
	private Image imagen;
	private boolean aterrizado;
	private boolean movimientoDerecha;

	public Gnomo(double x, double y, double alto, double ancho, double velocidad, double vida, double daño,
			Image imagen, boolean aterrizado) {
		this.setX(x);
		this.setY(y);
		this.setAlto(alto);
		this.setAncho(ancho);
		this.setVelocidad(velocidad);
		this.setVida(vida);
		this.setDaño(daño);
		this.setImagen(imagen);
		this.aterrizado = false;
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

	public void dibujar(Entorno entorno) {
		Image imagenGnomo = Herramientas.cargarImagen("imagenes/gnomo.png");
		entorno.dibujarImagen(imagenGnomo, this.getX(), this.getY(), Math.toRadians(0), 0.05);
	}

	public void moverDerecha() {
		this.x = this.x + 0.7;
	}

	public void moverIzquierda() {
		this.x = this.x - 0.7;
	}

	public void moverAbajo() {
		this.y = this.y + 2;
	}

	public void setAterrizado(boolean aterrizado) {
		this.aterrizado = aterrizado;
	}

	public boolean estaAterrizado() {
		return aterrizado;
	}

	public void resetearAterrizado() {
		this.aterrizado = false;
	}

	public boolean isMovimientoDerecha() {
		return movimientoDerecha;
	}

	public void elegirDireccion() {
		double numeroRandom = Math.random();
		this.movimientoDerecha = numeroRandom >= 0.5;
	}

	public boolean dentroDelEntorno(Entorno entorno) {
		return this.y <= entorno.alto() && this.x <= entorno.ancho();
	}

	public boolean gnomoSobreIsla(ArrayList<Isla> islas) {
		for (Isla isla : islas) {
			if (this.x >= isla.getX() - isla.getAncho() / 2 && this.x <= isla.getX() + isla.getAncho() / 2) {
				if (this.y + this.alto / 2 >= isla.getY() - isla.getAlto() / 2
						&& this.y + this.alto / 2 <= isla.getY() + isla.getAlto() / 2) {
					return true;
				}
			}
		}
		return false;
	}
}
