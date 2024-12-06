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
	private Islas islas;
	private boolean direcBola;
	private ArrayList<BolaFuego> disparos;
	private int gnomosSalvados;
	private int gnomosMuertos;
	private int tiempoJuego;
	private int tiempoTranscurrido;
	private int tiempoUltimaMuerte;
	private int tiempoUltimoSalvado;
	private boolean juegoTerminado = false;
	private boolean juegoGanado;
	private Escudo escudo;
	private int tortugasAsesinadas;
	private int nivel;
	private Roku roku;
	private Escudo escudo2;
	private boolean direcBola2;
	private ArrayList<BolaFuego> disparos2;
	private int finalizar;
	private int pepSalvados;
	private int rokuSalvados;
	private int pepAsesinatos;
	private int rokuAsesinatos;
	private int ultimo;
	private int balasPep;
	private int balasRoku;
	private double tiempoRecarga;
	private double tiempoRecarga2;
	

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
	public void dibujarBalas(double x,double y) {
		Image imagenBalas = Herramientas.cargarImagen("imagenes/bolaFuego.jpg");
		entorno.dibujarImagen(imagenBalas, x, y, Math.toRadians(0), 0.02);
	}
	

	Juego() {

		this.entorno = new Entorno(this, "Aldeco-Lauzan-Toledo-tp-p1", 800, 600);
		this.gnomos = new ArrayList<>();
		this.tortugas = new ArrayList<Tortuga>();
		this.casa = new Casa(400, 60, 50, 50, null);
		this.islas = new Islas();

		this.pep = new Pep(50, 460, 50, 30, 0, 3, 0, null);
		this.escudo = new Escudo(this.pep.getX(), this.pep.getY(), this.pep.getAlto(), this.pep.getAncho(), 3, null);
		this.roku = new Roku(750, 460, 50, 30, 0, 3, 0, null);
		this.escudo2 = new Escudo(this.roku.getX(), this.roku.getY(), this.roku.getAlto(), this.roku.getAncho(), 3,
				null);
		this.disparos = new ArrayList<>();
		this.disparos2 = new ArrayList<>();
		finalizar = 2;
		ultimo=finalizar-1;
		balasPep=3;
		balasRoku=3;
		this.tiempoRecarga=0;
		this.tiempoRecarga2=0;

		this.entorno.iniciar();
		this.tiempoGeneracion = 0;
		this.intervaloGeneracion = 2;
	}

	public void tick() {

		dibujarFondo();

		if (pep == null && roku == null) {
			juegoGanado = false;
			terminarJuego();
		}

		if (nivel == finalizar) {
			juegoGanado = true;
			terminarJuego();
		}
		if ((nivel == ultimo) && (pep == null || roku == null) ) {
			juegoGanado = true;
			terminarJuego();
		}

		if (juegoTerminado) {
			if (juegoGanado) {
				dibujarMenuFinal("Ganaste");
			} else {
				dibujarMenuFinal("Perdiste");
			}
			return;
		}

		entorno.cambiarFont("Arial", 18, Color.BLACK, 1);
		entorno.escribirTexto("Tortugas asesinadas: " + tortugasAsesinadas, 575, 25);
		entorno.cambiarFont("DialogInput", 26, Color.BLACK, 1);

		if (tortugasAsesinadas == 1 && (tiempoJuego - tiempoUltimaMuerte) < 3) {
			entorno.escribirTexto("¡TORTUGA A LA PARRILLA!", 240, 140);
		} else if (tortugasAsesinadas == 5 && (tiempoJuego - tiempoUltimaMuerte) < 3) {
			entorno.escribirTexto("¡SICARIO!", 320, 140);
		} else if (tortugasAsesinadas == 10 && (tiempoJuego - tiempoUltimaMuerte) < 3) {
			entorno.escribirTexto("¡EL TERROR DE LAS TORTUGAS!", 205, 140);
		} else if (tortugasAsesinadas == 20 && (tiempoJuego - tiempoUltimaMuerte) < 3) {
			entorno.escribirTexto("¡FURIA DESATADA!", 265, 140);
		}

		entorno.cambiarFont("Arial", 18, Color.CYAN, 1);
		entorno.escribirTexto("Gnomos salvados: " + gnomosSalvados, 10, 25);
		entorno.cambiarFont("DialogInput", 26, Color.CYAN, 1);

		if (gnomosSalvados == 1 && (tiempoJuego - tiempoUltimoSalvado) < 3) {
			entorno.escribirTexto("¡GNOMO A SALVO!", 520, 170);
		} else if (gnomosSalvados == 5 && (tiempoJuego - tiempoUltimoSalvado) < 3) {
			entorno.escribirTexto("¡HÉROE DE GNOMOS!", 500, 170);
		} else if (gnomosSalvados == 15 && (tiempoJuego - tiempoUltimoSalvado) < 3) {
			entorno.escribirTexto("¡NIÑERA PROFESIONAL!", 460, 170);
		} else if (gnomosSalvados == 25 && (tiempoJuego - tiempoUltimoSalvado) < 3) {
			entorno.escribirTexto("¡EL SALVADOR!", 540, 170);
		} else if (gnomosSalvados == nivel * 10 && (tiempoJuego - tiempoUltimoSalvado) < 3 && nivel != 0) {
			entorno.escribirTexto("¡SUBES DE NIVEL!", 540, 170);
		}

		entorno.cambiarFont("Arial", 18, Color.white);
		entorno.escribirTexto("Tiempo de juego: " + tiempoJuego + " s", 10, 50);
		
		Color rosa = new Color(255, 192, 203);
		entorno.cambiarFont("Arial", 18, rosa);
		entorno.escribirTexto("Gnomos muertos: " + gnomosMuertos, 625, 50);

		if (this.pep != null) {
			entorno.cambiarFont("DialogInput", 26, Color.RED, 1);
			entorno.escribirTexto("PEP" , 10, 75);
			entorno.escribirTexto("Vidas:" + this.pep.getVida(), 10, 100);
			entorno.escribirTexto("Escudos:" + this.escudo.getVida(), 10, 125);
		}
		if (this.roku != null) {
			Color verdeMusgo = new Color(85, 107, 47);
			entorno.cambiarFont("DialogInput", 26, verdeMusgo, 1);
			entorno.escribirTexto("ROKU" , 690, 75);
			entorno.escribirTexto("Vidas:" + this.roku.getVida(), 675, 100);
			entorno.escribirTexto("Escudos:" + this.escudo2.getVida(), 643, 125);
		}
		
		if(ultimo==nivel) {
			entorno.cambiarFont("DialogInput", 26, Color.YELLOW, 1);
			entorno.escribirTexto("FINAL 1 VS 1", 300, 20);

		}
		if(nivel!=ultimo) {
			entorno.cambiarFont("DialogInput", 26, Color.YELLOW, 1);
			entorno.escribirTexto("NIVEL:" + nivel, 350, 20);
		}

		this.casa.dibujar(entorno);

		for (Isla isla : this.islas.getIslas()) {
			isla.dibujar(entorno);
		}

		tiempoTranscurrido++;
		if (tiempoTranscurrido >= 60) {
			tiempoJuego++;
			tiempoTranscurrido = 0;
		}

		// NIVELES
		if (gnomosSalvados % 10 == 0 && gnomosSalvados != nivel * 10) {
			nivel++;
			if (nivel != finalizar) {
				try {
					Herramientas.play("sonidos/nivel2.wav");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// PEP
		if (this.pep != null) {

			this.pep.dibujar(entorno);
			// ESCUDO
			this.escudo.setX(this.pep.getX());
			this.escudo.setY(this.pep.getY());
			this.escudo.setAlto(this.pep.getAlto());
			this.escudo.setAncho(this.pep.getAncho());
			if (entorno.estaPresionada('s') && this.escudo.getVida() > 0) {
				this.escudo.dibujar(entorno);
			}
			if (entorno.seLevanto('s') && this.escudo.getVida() > 0) {
				this.escudo.setVida(this.escudo.getVida() - 1);

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

			if (this.pep.pepSobreIsla(this.islas.getIslas()) == false && this.pep.dentroDelEntorno(entorno)) {
				this.pep.moverAbajo();
			}

			if (entorno.sePresiono('w') && this.pep.pepSobreIsla(this.islas.getIslas())) {
				this.pep.iniciarSalto();
			}

			this.pep.actualizarSalto(this.islas.getIslas());

			// muerte por limite
			if (!this.pep.dentroDelEntorno(entorno)) {
				try {
					Herramientas.play("sonidos/muerte.wav");
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (this.pep.getVida() > 1) {
					this.pep.setVida(this.pep.getVida() - 1);
					this.pep.setX(400);
					this.pep.setY(1);
				} else {
					this.pep = null;
					try {
						Herramientas.play("sonidos/perder2.wav");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			// DISPARO PEP
			if (balasPep==0) {
				tiempoRecarga += 1.0 / 60;
				
				if (tiempoRecarga >= 3) {
					balasPep=3;
					tiempoRecarga=0;
					try {
						Herramientas.play("sonidos/recarga.wav");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			if(balasPep!=0) {
				for(int i=1;i<=balasPep;i++) {
					dibujarBalas(i*30+20,150);
				}
			}
			
			if (entorno.sePresiono('v') && balasPep>0) {
				disparos.add(new BolaFuego(this.pep.getX(), this.pep.getY() + this.pep.getAlto() / 4, 50, 30, null,
						direcBola));
				balasPep--;
				try {
					Herramientas.play("sonidos/disparo.wav");
				} catch (Exception e) {
					e.printStackTrace();
				}
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

		// ROKU

		if (this.roku != null) {

			this.roku.dibujar(entorno);
			// ESCUDO2
			this.escudo2.setX(this.roku.getX());
			this.escudo2.setY(this.roku.getY());
			this.escudo2.setAlto(this.roku.getAlto());
			this.escudo2.setAncho(this.roku.getAncho());
			if (entorno.estaPresionada(entorno.TECLA_ABAJO) && this.escudo2.getVida() > 0) {
				this.escudo2.dibujar(entorno);
			}
			if (entorno.seLevanto(entorno.TECLA_ABAJO) && this.escudo2.getVida() > 0) {
				this.escudo2.setVida(this.escudo2.getVida() - 1);

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

			if (this.roku.rokuSobreIsla(this.islas.getIslas()) == false && this.roku.dentroDelEntorno(entorno)) {
				this.roku.moverAbajo();
			}

			if (entorno.sePresiono(entorno.TECLA_ARRIBA) && this.roku.rokuSobreIsla(this.islas.getIslas())) {
				this.roku.iniciarSalto();
			}

			this.roku.actualizarSalto(this.islas.getIslas());

			// muerte por limite
			if (!this.roku.dentroDelEntorno(entorno)) {
				try {
					Herramientas.play("sonidos/muerte.wav");
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (this.roku.getVida() > 1) {
					this.roku.setVida(this.roku.getVida() - 1);
					this.roku.setX(400);
					this.roku.setY(1);
				} else {
					this.roku = null;
					try {
						Herramientas.play("sonidos/perder.wav");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			// DISPARO ROKU
			if (balasRoku==0) {
				tiempoRecarga2 += 1.0 / 60;
				
				
				if (tiempoRecarga2 >= 3) {
					balasRoku=3;
					tiempoRecarga2=0;
					try {
						Herramientas.play("sonidos/recarga.wav");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			
			if(balasRoku!=0) {
				for(int i=1;i<=balasRoku;i++) {
					dibujarBalas(i*30+650,150);
				}
			}
			
			if (entorno.sePresiono('l') && balasRoku>0) {
				disparos2.add(new BolaFuego(this.roku.getX(), this.roku.getY() + this.roku.getAlto() / 4, 50, 30, null,
						direcBola2));
				balasRoku--;
				try {
					Herramientas.play("sonidos/disparo.wav");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// BOLA DE FUEGO 2
			
			for (int i = 0; i < disparos2.size(); i++) {
				BolaFuego bola2 = disparos2.get(i);
				if (bola2 != null) {
					// MOVILIDAD BOLA
					if (bola2.isDirec()) {
						bola2.moverDerecha();
					}
					if (!bola2.isDirec()) {
						bola2.moverIzquierda();
					}
					bola2.dibujar(entorno);
					if (!bola2.dentroDelEntorno(entorno)) {
						bola2 = null;
						disparos2.remove(i);
						i--;
					}
				}
			}

		}

		if(nivel<ultimo) {
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
					if (gnomo.gnomoSobreIsla(this.islas.getIslas())) {
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
				if (tortugas.size() < nivel * 2 + 2) {
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
					if (tortuga.tortugaSobreIsla(this.islas.getIslas())
							&& !tortuga.tortugaSobreCasa(casa) /* && !tortuga.islaOcupada(islas, tortugas) */) {
						tortuga.setAterrizado(true);
						if (tortuga.isMovimientiIzquierda()) {
							tortuga.moverIzquierda();
						} else {
							tortuga.moverDerecha();
						}
						if (!tortuga.tortugaSobreIsla(this.islas.getIslas())) { // si se va de limites de isla vuelve a
																				// entrar con direccion
							// opuesta
							tortuga.moverDerecha();
							tortuga.setMovimientoIzquierda(false);
							if (!tortuga.tortugaSobreIsla(this.islas.getIslas())) {
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
		}
		
		if(nivel<ultimo) {
	//		COLISION 1 PEP
			for (int j = 0; j < gnomos.size(); j++) {	
				Gnomo gnomo = gnomos.get(j);
				for (int i = 0; i < tortugas.size(); i++) {
					Tortuga tortuga = tortugas.get(i);
	
					// rescate pep
					if (this.pep != null && this.pep.getY() > 300 && gnomo != null
							&& hayColision(this.pep.getX(), this.pep.getY(), this.pep.getAncho(), this.pep.getAlto(),
									gnomo.getX(), gnomo.getY(), gnomo.getAncho(), gnomo.getAlto())) {
						tiempoUltimoSalvado = tiempoJuego;
						pepSalvados++;
						gnomosSalvados++;
						if (gnomosSalvados == finalizar * 10) {
							try {
								Herramientas.play("sonidos/ganar.wav");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if (gnomosSalvados == 1 || gnomosSalvados == 5 || gnomosSalvados == 15 || gnomosSalvados == 25) {
							try {
								Herramientas.play("sonidos/logrosalva.wav");
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							try {
								Herramientas.play("sonidos/salvado.wav");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						gnomo = null;
						gnomos.remove(j);
						j--;
						if (this.escudo.getVida() < 3) {
							this.escudo.setVida(this.escudo.getVida() + 1);
						}
					}
	
					// rescate roku
					if (this.roku != null && this.roku.getY() > 300 && gnomo != null
							&& hayColision(this.roku.getX(), this.roku.getY(), this.roku.getAncho(), this.roku.getAlto(),
									gnomo.getX(), gnomo.getY(), gnomo.getAncho(), gnomo.getAlto())) {
						tiempoUltimoSalvado = tiempoJuego;
						rokuSalvados++;
						gnomosSalvados++;
						if (gnomosSalvados == finalizar * 10) {
							try {
								Herramientas.play("sonidos/ganar.wav");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if (gnomosSalvados == 1 || gnomosSalvados == 5 || gnomosSalvados == 15 || gnomosSalvados == 25) {
							try {
								Herramientas.play("sonidos/logrosalva.wav");
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							try {
								Herramientas.play("sonidos/salvado.wav");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						gnomo = null;
						gnomos.remove(j);
						j--;
						if (this.escudo2.getVida() < 3) {
							this.escudo2.setVida(this.escudo2.getVida() + 1);
						}
					}
	
					// muerte pep
					if (this.pep != null && tortuga != null
							&& !((entorno.estaPresionada('s') || entorno.estaPresionada(entorno.TECLA_ABAJO))
									&& this.escudo.getVida() > 0)
							&& hayColision(this.pep.getX(), this.pep.getY(), this.pep.getAncho(), this.pep.getAlto(),
									tortuga.getX(), tortuga.getY(), tortuga.getAncho(), tortuga.getAlto())) {
						try {
							Herramientas.play("sonidos/muerte.wav");
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (this.pep.getVida() > 1) {
							this.pep.setVida(this.pep.getVida() - 1);
							this.pep.setX(400);
							this.pep.setY(1);
						} else {
							this.pep = null;
							try {
								Herramientas.play("sonidos/perder2.wav");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
	
					// muerte roku
					if (this.roku != null && tortuga != null
							&& !((entorno.estaPresionada('s') || entorno.estaPresionada(entorno.TECLA_ABAJO))
									&& this.escudo2.getVida() > 0)
							&& hayColision(this.roku.getX(), this.roku.getY(), this.roku.getAncho(), this.roku.getAlto(),
									tortuga.getX(), tortuga.getY(), tortuga.getAncho(), tortuga.getAlto())) {
						try {
							Herramientas.play("sonidos/muerte.wav");
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (this.roku.getVida() > 1) {
							this.roku.setVida(this.roku.getVida() - 1);
							this.roku.setX(400);
							this.roku.setY(1);
						} else {
							this.roku = null;
							try {
								Herramientas.play("sonidos/perder.wav");
							} catch (Exception e) {
								e.printStackTrace();
							}
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
						pepAsesinatos++;
						tortugasAsesinadas++;
						tiempoUltimaMuerte = tiempoJuego;
						if (tortugasAsesinadas == 1 || tortugasAsesinadas == 5 || tortugasAsesinadas == 10
								|| tortugasAsesinadas == 20) {
							try {
								Herramientas.play("sonidos/logro2.wav");
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							try {
								Herramientas.play("sonidos/asesinato2.wav");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
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
						rokuAsesinatos++;
						tortugasAsesinadas++;
						tiempoUltimaMuerte = tiempoJuego;
						if (tortugasAsesinadas == 1 || tortugasAsesinadas == 5 || tortugasAsesinadas == 10
								|| tortugasAsesinadas == 20) {
							try {
								Herramientas.play("sonidos/logro2.wav");
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							try {
								Herramientas.play("sonidos/asesinato2.wav");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						tortuga = null;
						tortugas.remove(i);
						i--;
						bola2 = null;
						disparos2.remove(j);
						j--;
					}
				}
			}			
		}
		if(nivel==ultimo) {
			// BATALLA FINALLLL
			for (int w = 0; w < disparos.size(); w++) {
				BolaFuego bola = disparos.get(w);
				//pep mata a roku
				if (bola != null && this.roku != null &&    !(entorno.estaPresionada(entorno.TECLA_ABAJO)
						&& this.escudo2.getVida() > 0) && hayColision(bola.getX(), bola.getY(), bola.getAncho(),
						bola.getAlto(), this.roku.getX(), this.roku.getY(), this.roku.getAncho(), this.roku.getAlto())) {
					bola = null;
					disparos.remove(w);
					w--;
					try {
						Herramientas.play("sonidos/muerte.wav");
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (this.roku.getVida() > 1) {
						this.roku.setVida(this.roku.getVida() - 1);
						this.roku.setX(400);
						this.roku.setY(1);
					} else {
						this.roku = null;
						try {
							Herramientas.play("sonidos/perder.wav");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			for (int j = 0; j < disparos2.size(); j++) {
				BolaFuego bola2 = disparos2.get(j);
				//roku mata a pep
				if (bola2 != null && this.pep != null &&  !(entorno.estaPresionada('s')
						&& this.escudo.getVida() > 0) && hayColision(bola2.getX(), bola2.getY(), bola2.getAncho(),
						bola2.getAlto(), this.pep.getX(), this.pep.getY(), this.pep.getAncho(), this.pep.getAlto())) {
					bola2 = null;
					disparos2.remove(j);
					j--;
					try {
						Herramientas.play("sonidos/muerte.wav");
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (this.pep.getVida() > 1) {
						this.pep.setVida(this.pep.getVida() - 1);
						this.pep.setX(400);
						this.pep.setY(1);
					} else {
						this.pep = null;
						try {
							Herramientas.play("sonidos/perder2.wav");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}

			for (int w = 0; w < disparos.size(); w++) {
				BolaFuego bola = disparos.get(w);
					for (int j = 0; j < disparos2.size(); j++) {
					BolaFuego bola2 = disparos2.get(j);
					//choque de bolas de fuego
					if (bola2 != null && bola != null && hayColision(bola2.getX(), bola2.getY(), bola2.getAncho(),
							bola2.getAlto(), bola.getX(), bola.getY(), bola.getAncho(), bola.getAlto())) {
						bola2 = null;
						bola = null;
						disparos2.remove(j);
						disparos.remove(w);
						j--;
						w--;
					}
				}
			}
					
			
		}
	}


	public void dibujarMenuFinal(String mensajeFinal) {

		Color fondoTransparente = new Color(0, 0, 0, 150);

		entorno.dibujarRectangulo(400, 300, 800, 600, 0, fondoTransparente);

		if (juegoGanado) {
			entorno.cambiarFont("Arial", 36, Color.GREEN);
		} else {
			entorno.cambiarFont("Arial", 36, Color.RED);
		}
		entorno.escribirTexto(mensajeFinal, 310, 200);

		entorno.cambiarFont("Arial", 24, Color.WHITE);
		entorno.escribirTexto("Tiempo jugado: " + tiempoJuego + " segundos", 250, 250);
		entorno.escribirTexto("Gnomos salvados: " + gnomosSalvados, 250, 290);
		entorno.escribirTexto("Gnomos muertos: " + gnomosMuertos, 250, 330);
		entorno.escribirTexto("Tortugas asesinadas: " + tortugasAsesinadas, 250, 370);
		entorno.escribirTexto("Nivel: " + nivel, 250, 410);
		entorno.escribirTexto("PEP: ", 50, 250);
		entorno.escribirTexto(pepSalvados + " SALVADAS ", 50, 300);
		entorno.escribirTexto(pepAsesinatos + " ASESINATOS ", 50, 350);
		entorno.escribirTexto("ROKU : ", 600, 250);
		entorno.escribirTexto(rokuSalvados + " SALVADAS ", 600, 300);
		entorno.escribirTexto(rokuAsesinatos + " ASESINATOS ", 600, 350);

		entorno.escribirTexto("Presioná 'R' para reiniciar o 'Q' para salir", 180, 470);

		if (entorno.sePresiono('r')) {
			reiniciarJuego();
		} else if (entorno.sePresiono('q')) {
			System.exit(0);
		}
	}

	public void reiniciarJuego() {
		juegoTerminado = false;
		tiempoJuego = 0;
		gnomos.clear();
		tortugas.clear();
		disparos.clear();
		disparos2.clear();
		disparos2.clear();
		gnomosSalvados = 0;
		gnomosMuertos = 0;
		tortugasAsesinadas = 0;
		nivel = 0;
		pep = new Pep(50, 460, 50, 30, 0, 3, 0, null);
		roku = new Roku(750, 460, 50, 30, 0, 3, 0, null);
		escudo = new Escudo(this.pep.getX(), this.pep.getY(), this.pep.getAlto(), this.pep.getAncho(), 3, null);
		escudo2 = new Escudo(this.roku.getX(), this.roku.getY(), this.roku.getAlto(), this.roku.getAncho(), 3, null);
		rokuAsesinatos = 0;
		pepAsesinatos = 0;
		pepSalvados = 0;
		rokuSalvados = 0;
	}

	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
