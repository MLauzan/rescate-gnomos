package juego;

import java.awt.Image;
import java.util.ArrayList;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	private Entorno entorno;
	private ArrayList<Gnomo> gnomos;
	private double tiempoGeneracion;
	private int intervaloGeneracion;
	private Tortuga tortuga;
	private Pep pep;
	private Casa casa;
	private ArrayList<Isla> islas;
	private boolean direcBola;
	private ArrayList<BolaFuego> disparos;

	public void dibujarFondo() {
		Image imagenFondo = Herramientas.cargarImagen("imagenes/fondo.jpg");
		entorno.dibujarImagen(imagenFondo, 400, 300, Math.toRadians(0), 0.7);
	}

	Juego() {
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		this.gnomos = new ArrayList<>();
		this.casa = new Casa(400, 60, 50, 50, null);
		this.islas = new ArrayList<>();

		islas.add(new Isla(400, 100, 30, 110, null));
		islas.add(new Isla(300, 200, 30, 110, null));
		islas.add(new Isla(500, 200, 30, 110, null));
		islas.add(new Isla(210, 300, 30, 110, null));
		islas.add(new Isla(400, 300, 30, 110, null));
		islas.add(new Isla(590, 300, 30, 110, null));
		islas.add(new Isla(137.5, 400, 30, 110, null));
		islas.add(new Isla(305, 400, 30, 110, null));
		islas.add(new Isla(490, 400, 30, 110, null));
		islas.add(new Isla(662.5, 400, 30, 110, null));
		islas.add(new Isla(50, 500, 30, 110, null));
		islas.add(new Isla(225, 500, 30, 110, null));
		islas.add(new Isla(400, 500, 30, 110, null));
		islas.add(new Isla(575, 500, 30, 110, null));
		islas.add(new Isla(750, 500, 30, 110, null));

		this.pep = new Pep(50, 460, 50, 30, 0, 0, 0, null);
		this.tortuga = new Tortuga(pep.getX() + 100, pep.getY(), pep.getAlto(), pep.getAncho() + 20, 0, 0, 0, null);
		this.disparos = new ArrayList<>();

		this.entorno.iniciar();
		this.tiempoGeneracion = 0;
		this.intervaloGeneracion = 2;
	}

	public void tick() {

		dibujarFondo();

		this.casa.dibujar(entorno);

		for (Isla isla : this.islas) {
			isla.dibujar(entorno);
		}

		this.pep.dibujar(entorno);

		if ((entorno.estaPresionada(entorno.TECLA_DERECHA) || entorno.estaPresionada('d'))
				&& (this.pep.pepSobreIsla(islas) || this.pep.getX() + 10 < this.entorno.ancho())) {
			this.pep.moverDerecha();
			direcBola=true;
		}
		if ((entorno.estaPresionada(entorno.TECLA_IZQUIERDA) || entorno.estaPresionada('a'))
				&& (this.pep.pepSobreIsla(islas) || this.pep.getX() -10 > 0)) {
			this.pep.moverIzquierda();
			direcBola=false;
		}

		if (this.pep.pepSobreIsla(islas) == false && this.pep.dentroDelEntorno(entorno)) {
			this.pep.moverAbajo();
		}

		if ((entorno.estaPresionada(entorno.TECLA_ARRIBA) || entorno.estaPresionada('w'))
				&& this.pep.pepSobreIsla(islas)) {
			this.pep.iniciarSalto();
		}
		
		if(entorno.estaPresionada('c') || entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
			disparos.add(new BolaFuego(this.pep.getX(), this.pep.getY()+this.pep.getAlto()/2, 50, 30, null, direcBola));
		}
		for (int i = 0; i < disparos.size(); i++) {
			BolaFuego bola = disparos.get(i);
			if(bola.isDirec()) {
				bola.moverDerecha();
			}
			if(!bola.isDirec()) {
				bola.moverIzquierda();
			}
			bola.dibujar(entorno);
			if(!bola.dentroDelEntorno(entorno)) {
				disparos.remove(i);
				i--;
			}
		}

		this.pep.actualizarSalto(islas);

		this.tortuga.dibujar(entorno);

		tiempoGeneracion += 1.0 / 60;

		if (tiempoGeneracion >= intervaloGeneracion) {
			if (gnomos.size() < 4) {
				gnomos.add(new Gnomo(400, 60, 55, 95, 0, 0, 0, null, false));
			}
			tiempoGeneracion = 0;
		}

		if (gnomos.size() < 2) {
			gnomos.add(new Gnomo(400, 60, 55, 95, 0, 0, 0, null, false));
		}

		for (int i = 0; i < gnomos.size(); i++) {
			Gnomo gnomo = gnomos.get(i);
			if (gnomo != null) {
				gnomo.dibujar(entorno);

				if (gnomo.gnomoSobreIsla(islas)) {
					if (!gnomo.estaAterrizado()) {
						gnomo.elegirDireccion();
						gnomo.setAterrizado(true);
					}
					if (gnomo.isMovimientoDerecha()) {
						gnomo.moverDerecha();
					} else {
						gnomo.moverIzquierda();
					}
				} else {
					gnomo.moverAbajo();
					gnomo.resetearAterrizado();

					if (!gnomo.dentroDelEntorno(entorno)) {
						gnomos.remove(i);
						i--;
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
