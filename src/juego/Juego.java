package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	private Entorno entorno;

	Juego() {
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		this.entorno.iniciar();
	}

	public void tick() {
		dibujarImagen();
	}

	public void dibujarImagen() {
		Image imagen = Herramientas.cargarImagen("imagenes/casa.jpg");
		entorno.dibujarImagen(imagen, 100, 100, Math.toRadians(0), 0.5);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
