package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	private Entorno entorno;
	private ArrayList<Gnomo> gnomos;
	private double tiempoGeneracion;
	private int intervaloGeneracion;
	private ArrayList<Tortuga> tortugas;
	private Random rand = new Random();
	private Pep pep;
	private Casa casa;
	private ArrayList<Isla> islas;
	private boolean direcBola;
	private ArrayList<BolaFuego> disparos;
	private int gnomosSalvados;
	private int gnomosMuertos;
	private int tiempoJuego;
	private int tiempoTranscurrido;
	private boolean juegoTerminado = false;
	private Escudo escudo;
	private int tortugasAsesinadas;
	private int nivel;
	private Roku roku;
	private Escudo escudo2;
	private boolean direcBola2;
	private ArrayList<BolaFuego> disparos2;
	
	

	public void dibujarFondo() {
		Image imagenFondo = Herramientas.cargarImagen("imagenes/fondo.jpg");
		entorno.dibujarImagen(imagenFondo, 400, 300, Math.toRadians(0), 0.7);
	}

	private boolean hayColision(double x1, double y1, double ancho1, double alto1, double x2, double y2, double ancho2,
			double alto2) {
		return x1 < x2 + ancho2 / 2 && x1 + ancho1 / 2 > x2 && y1 < y2 + alto2 / 2 && y1 + alto1 / 2 > y2;
	}

	public void terminarJuego() {
		juegoTerminado = true;
	}


	Juego() {

		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		this.gnomos = new ArrayList<>();
		this.tortugas = new ArrayList<Tortuga>();
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

		this.pep = new Pep(50, 460, 50, 30, 0, 3, 0, null);
		this.escudo=new Escudo(this.pep.getX(), this.pep.getY(), this.pep.getAlto(), this.pep.getAncho(), 3, null);
		this.roku = new Roku(750, 460, 50, 30, 0, 3, 0, null);
		this.escudo2=new Escudo(this.roku.getX(), this.roku.getY(), this.roku.getAlto(), this.roku.getAncho(), 3, null);
		this.disparos = new ArrayList<>();
		this.disparos2 = new ArrayList<>();

		this.entorno.iniciar();
		this.tiempoGeneracion = 0;
		this.intervaloGeneracion = 2;
	}

	public void tick() {

		dibujarFondo();

		
		if (pep == null && roku == null) {
			terminarJuego();
		}

		if (juegoTerminado) {
			dibujarMenuFinal();
			return;
		}

		entorno.cambiarFont("Arial", 18, Color.white);

		entorno.escribirTexto("Gnomos salvados: " + gnomosSalvados, 10, 25);
		entorno.escribirTexto("Gnomos muertos: " + gnomosMuertos, 630, 25);
		entorno.escribirTexto("Tortugas asesinadas: " + tortugasAsesinadas, 600, 50);
		entorno.escribirTexto("Nivel: " + nivel, 650, 75);
		entorno.escribirTexto("Tiempo de juego: " + tiempoJuego + " s", 10, 50);
		if(this.pep!=null) {
			entorno.escribirTexto("Vidas Pep: " + this.pep.getVida(), 10, 75);
			entorno.escribirTexto("Escudos Pep: " + this.escudo.getVida(), 10, 100);
		}
		if(this.roku!=null) {
			entorno.escribirTexto("Vidas Roku: " + this.roku.getVida(), 10, 125);
			entorno.escribirTexto("Escudos Roku: " + this.escudo2.getVida(), 10, 150);
		}

		this.casa.dibujar(entorno);

		for (Isla isla : this.islas) {
			isla.dibujar(entorno);
		}

		tiempoTranscurrido++;
		if (tiempoTranscurrido >= 60) {
			tiempoJuego++;
			tiempoTranscurrido = 0;
		}
		
		//NIVELES
		if(gnomosSalvados%10==0 && gnomosSalvados != nivel*10) {
			nivel++;
			try { Herramientas.play("sonidos/nivel2.wav"); } catch (Exception e) { e.printStackTrace();}
		}

		// PEP
		if (this.pep != null) {

			this.pep.dibujar(entorno);
			//ESCUDO
			this.escudo.setX(this.pep.getX());
			this.escudo.setY(this.pep.getY());
			this.escudo.setAlto(this.pep.getAlto());
			this.escudo.setAncho(this.pep.getAncho());
			if( entorno.estaPresionada('s') && this.escudo.getVida()>0){
				this.escudo.dibujar(entorno);
			}
			if(entorno.seLevanto('s') && this.escudo.getVida()>0) {
				this.escudo.setVida(this.escudo.getVida()-1);
				
			}

			// MOVILIDAD PEP
			if (entorno.estaPresionada('d') && this.pep.getX() + 10 < this.entorno.ancho()) {
				this.pep.moverDerecha();
				direcBola = true;
			}
			if (entorno.estaPresionada('a') && this.pep.getX() - 10 > 0) {
				this.pep.moverIzquierda();
				direcBola = false;
			}

			if (this.pep.pepSobreIsla(islas) == false && this.pep.dentroDelEntorno(entorno)) {
				this.pep.moverAbajo();
			}

			if (entorno.sePresiono('w') && this.pep.pepSobreIsla(islas)) {
				this.pep.iniciarSalto();
			}

			this.pep.actualizarSalto(islas);

			// muerte por limite
			if (!this.pep.dentroDelEntorno(entorno)) {
				try { Herramientas.play("sonidos/muerte.wav"); } catch (Exception e) { e.printStackTrace();}
				if (this.pep.getVida() > 0) {
					this.pep.setVida(this.pep.getVida() - 1);
					this.pep.setX(400);
					this.pep.setY(1);
				} else {
					this.pep = null;
					try { Herramientas.play("sonidos/perder2.wav"); } catch (Exception e) { e.printStackTrace();}
				}
			}

			// DISPARO PEP
			if (entorno.sePresiono('v') || entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
				disparos.add(new BolaFuego(this.pep.getX(), this.pep.getY() + this.pep.getAlto() / 4, 50, 30, null,
						direcBola));
				try { Herramientas.play("sonidos/disparo.wav"); } catch (Exception e) { e.printStackTrace();}
			}
			// BOLA DE FUEGO
			for (int i = 0; i < disparos.size(); i++) {
				BolaFuego bola = disparos.get(i);
				if (bola != null) {
					// MOVILIDAD BOLA
					if (bola.isDirec()) {
						bola.moverDerecha();
					}
					if (!bola.isDirec()) {
						bola.moverIzquierda();
					}
					bola.dibujar(entorno);
					if (!bola.dentroDelEntorno(entorno)) {
						bola = null;
						disparos.remove(i);
						i--;
					}
				}
			}

		}
		
		//ROKU
				
		if (this.roku != null) {

				this.roku.dibujar(entorno);
				//ESCUDO2
				this.escudo2.setX(this.roku.getX());
				this.escudo2.setY(this.roku.getY());
				this.escudo2.setAlto(this.roku.getAlto());
				this.escudo2.setAncho(this.roku.getAncho());
				if(entorno.estaPresionada(entorno.TECLA_ABAJO) && this.escudo2.getVida()>0){
					this.escudo2.dibujar(entorno);
				}
				if(entorno.seLevanto(entorno.TECLA_ABAJO) && this.escudo2.getVida()>0) {
					this.escudo2.setVida(this.escudo2.getVida()-1);
					
				}

				// MOVILIDAD ROKU
				if (entorno.estaPresionada(entorno.TECLA_DERECHA) && this.roku.getX() + 10 < this.entorno.ancho()) {
					this.roku.moverDerecha();
					direcBola2 = true;
				}
				if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && this.roku.getX() - 10 > 0) {
					this.roku.moverIzquierda();
					direcBola2 = false;
				}

				if (this.roku.rokuSobreIsla(islas) == false && this.roku.dentroDelEntorno(entorno)) {
					this.roku.moverAbajo();
				}

				if (entorno.sePresiono(entorno.TECLA_ARRIBA) && this.roku.rokuSobreIsla(islas)) {
					this.roku.iniciarSalto();
				}

				this.roku.actualizarSalto(islas);

				// muerte por limite
				if (!this.roku.dentroDelEntorno(entorno)) {
					try { Herramientas.play("sonidos/muerte.wav"); } catch (Exception e) { e.printStackTrace();}
					if (this.roku.getVida() > 0) {
						this.roku.setVida(this.roku.getVida() - 1);
						this.roku.setX(400);
						this.roku.setY(1);
					} else {
						this.roku = null;
						try { Herramientas.play("sonidos/perder.wav"); } catch (Exception e) { e.printStackTrace();}
					}
				}

				// DISPARO ROKU
				if (entorno.sePresiono('l') || entorno.sePresionoBoton(entorno.BOTON_DERECHO)) {
					disparos.add(new BolaFuego(this.roku.getX(), this.roku.getY() + this.roku.getAlto() / 4, 50, 30, null,
							direcBola2));
					try { Herramientas.play("sonidos/disparo.wav"); } catch (Exception e) { e.printStackTrace();}
				}
				// BOLA DE FUEGO 2
				for (int i = 0; i < disparos2.size(); i++) {
					BolaFuego bola = disparos2.get(i);
					if (bola != null) {
						// MOVILIDAD BOLA
						if (bola.isDirec()) {
							bola.moverDerecha();
						}
						if (!bola.isDirec()) {
							bola.moverIzquierda();
						}
						bola.dibujar(entorno);
						if (!bola.dentroDelEntorno(entorno)) {
							bola = null;
							disparos.remove(i);
							i--;
						}
					}
				}

			}


		// GNOMO

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

				// MOVILIDAD GNOMO
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
						gnomo = null;
						gnomos.remove(i);
						i--;
					}
				}
			}
		}



		// TORTUGA

		tiempoGeneracion += 1 / 60.0;

		if (tiempoGeneracion >= intervaloGeneracion) {
			if (tortugas.size() < nivel*2+2) {
				int x = rand.nextInt(entorno.getWidth());

				tortugas.add(new Tortuga(x, 0, 35, 50, 0, 0, 0, null, false, true, 2, 2));
			}
			
				



			tiempoGeneracion = 0;
		}

		for (int i = 0; i < tortugas.size(); i++) {
			Tortuga tortuga = tortugas.get(i);

			if (tortuga != null) {
				tortuga.dibujar(entorno);

				// MOVILIDAD TORTUGA
				if (!tortuga.estaAterrizado()) {
					tortuga.moverAbajo();
					tortuga.resetearAterrizado();
				}
				if (tortuga.tortugaSobreIsla(islas)
						&& !tortuga.tortugaSobreCasa(casa) /* && !tortuga.islaOcupada(islas, tortugas) */) {
					tortuga.setAterrizado(true);
					if (tortuga.isMovimientiIzquierda()) {
						tortuga.moverIzquierda();
					} else {
						tortuga.moverDerecha();
					}
					if (!tortuga.tortugaSobreIsla(islas)) { // si se va de limites de isla vuelve a entrar con direccion
															// opuesta
						tortuga.moverDerecha();
						tortuga.setMovimientoIzquierda(false);
						if (!tortuga.tortugaSobreIsla(islas)) {
							tortuga.moverIzquierda();
							tortuga.moverIzquierda();
							tortuga.setMovimientoIzquierda(true);
						}
					}

					if (!tortuga.dentroDelEntorno(entorno)) {
						tortuga = null;
						tortugas.remove(i);
						i--;
					}
				}

			}

		}
//		COLISION 1 PEP
		for (int j = 0; j < gnomos.size(); j++) {
			Gnomo gnomo = gnomos.get(j);
			for (int i = 0; i < tortugas.size(); i++) {
				Tortuga tortuga = tortugas.get(i);

				// rescate pep
				if (this.pep != null && this.pep.getY() > 300 && gnomo != null
						&& hayColision(this.pep.getX(), this.pep.getY(), this.pep.getAncho(), this.pep.getAlto(),
								gnomo.getX(), gnomo.getY(), gnomo.getAncho(), gnomo.getAlto())) {
					gnomosSalvados++;
					try { Herramientas.play("sonidos/salvado.wav"); } catch (Exception e) { e.printStackTrace();}
					gnomo = null;
					gnomos.remove(j);
					j--;
					if(this.escudo.getVida()<3) {
						this.escudo.setVida(this.escudo.getVida()+1);
					}
				}
				
				// rescate roku
				if (this.roku != null && this.roku.getY() > 300 && gnomo != null
						&& hayColision(this.roku.getX(), this.roku.getY(), this.roku.getAncho(), this.roku.getAlto(),
								gnomo.getX(), gnomo.getY(), gnomo.getAncho(), gnomo.getAlto())) {
					gnomosSalvados++;
					try { Herramientas.play("sonidos/salvado.wav"); } catch (Exception e) { e.printStackTrace();}
					gnomo = null;
					gnomos.remove(j);
					j--;
					if(this.escudo2.getVida()<3) {
						this.escudo2.setVida(this.escudo2.getVida()+1);
					}
				}

				// muerte pep
				if(this.pep!=null && tortuga!=null && !((entorno.estaPresionada('s') || entorno.estaPresionada(entorno.TECLA_ABAJO)) && this.escudo.getVida()>0) 
						&&  hayColision(this.pep.getX(), this.pep.getY(), this.pep.getAncho(), this.pep.getAlto(),
						tortuga.getX(), tortuga.getY(), tortuga.getAncho(), tortuga.getAlto())) {
					try { Herramientas.play("sonidos/muerte.wav"); } catch (Exception e) { e.printStackTrace();}
					if(this.pep.getVida()>0) {
						this.pep.setVida(this.pep.getVida()-1);
						this.pep.setX(400);
						this.pep.setY(1);
						}
					else{
						this.pep=null;
						try { Herramientas.play("sonidos/perder2.wav"); } catch (Exception e) { e.printStackTrace();}
					}
				}
				
				// muerte roku
				if(this.roku!=null && tortuga!=null && !((entorno.estaPresionada('s') || entorno.estaPresionada(entorno.TECLA_ABAJO)) && this.escudo2.getVida()>0) 
						&&  hayColision(this.roku.getX(), this.roku.getY(), this.roku.getAncho(), this.roku.getAlto(),
						tortuga.getX(), tortuga.getY(), tortuga.getAncho(), tortuga.getAlto())) {
					try { Herramientas.play("sonidos/muerte.wav"); } catch (Exception e) { e.printStackTrace();}
					if(this.roku.getVida()>0) {
						this.roku.setVida(this.roku.getVida()-1);
						this.roku.setX(400);
						this.roku.setY(1);
						}
					else{
						this.roku=null;
						try { Herramientas.play("sonidos/perder.wav"); } catch (Exception e) { e.printStackTrace();}
					}
				}

				// muerte gnomo
				if (gnomo != null && tortuga != null && hayColision(gnomo.getX(), gnomo.getY(), gnomo.getAncho(),
						gnomo.getAlto(), tortuga.getX(), tortuga.getY(), tortuga.getAncho(), tortuga.getAlto())) {
					gnomosMuertos++;
					gnomo = null;
					gnomos.remove(j);
					j--;
				}

			}
		}
		
		
		
		// COLISION 2
		for (int i = 0; i < tortugas.size(); i++) {
			Tortuga tortuga = tortugas.get(i);
			for (int w = 0; w < disparos.size(); w++) {
				BolaFuego bola = disparos.get(w);
				// muerte tortuga
				if (bola != null && tortuga != null && hayColision(bola.getX(), bola.getY(), bola.getAncho(),
						bola.getAlto(), tortuga.getX(), tortuga.getY(), tortuga.getAncho(), tortuga.getAlto())) {
					try { Herramientas.play("sonidos/asesinato2.wav"); } catch (Exception e) { e.printStackTrace();}
					tortugasAsesinadas++;
					tortuga = null;
					tortugas.remove(i);
					i--;
					bola = null;
					disparos.remove(w);
					w--;
				}
			}
			for (int j = 0; j < disparos2.size(); j++) {
				BolaFuego bola2 = disparos2.get(j);
				// muerte tortuga
				if (bola2 != null && tortuga != null && hayColision(bola2.getX(), bola2.getY(), bola2.getAncho(),
						bola2.getAlto(), tortuga.getX(), tortuga.getY(), tortuga.getAncho(), tortuga.getAlto())) {
					try { Herramientas.play("sonidos/asesinato2.wav"); } catch (Exception e) { e.printStackTrace();}
					tortugasAsesinadas++;
					tortuga = null;
					tortugas.remove(i);
					i--;
					bola2 = null;
					disparos.remove(j);
					j--;
				}
			}
		}
	}
	

	public void dibujarMenuFinal() {

		Color fondoTransparente = new Color(0, 0, 0, 150);

		entorno.dibujarRectangulo(400, 300, 800, 600, 0, fondoTransparente);

		entorno.cambiarFont("Arial", 36, Color.WHITE);
		entorno.escribirTexto("Juego Terminado", 280, 200);

		entorno.cambiarFont("Arial", 24, Color.WHITE);
		entorno.escribirTexto("Tiempo jugado: " + tiempoJuego + " segundos", 300, 250);
		entorno.escribirTexto("Gnomos salvados: " + gnomosSalvados, 300, 290);
		entorno.escribirTexto("Gnomos muertos: " + gnomosMuertos, 300, 330);
		entorno.escribirTexto("Tortugas asesinadas: " + tortugasAsesinadas, 300, 370);
		entorno.escribirTexto("Nivel: " + nivel, 300, 410);
		

		entorno.escribirTexto("Presioná 'R' para reiniciar o 'Q' para salir", 200, 450);

		if (entorno.sePresiono('r')) {
			reiniciarJuego();
		} else if (entorno.sePresiono('q')) {
			System.exit(0);
		}
	}

	public void reiniciarJuego() {
		juegoTerminado = false;
		gnomos.clear();
		tortugas.clear();
		disparos.clear();
		gnomosSalvados = 0;
		gnomosMuertos = 0;
		tortugasAsesinadas = 0;
		nivel = 0;
		pep = new Pep(50, 460, 50, 30, 0, 3, 0, null);
		roku = new Roku(750, 460, 50, 30, 0, 3, 0, null);
		
	}

	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
