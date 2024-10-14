package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
			private Entorno entorno;
			private Gnomo gnomo;
			private Tortuga tortuga;
			private Pep pep;
//			private Personaje bolaFuego;
//			private Personaje Bomba;
			private Casa casa;
			private Isla [] islas;
//			private Personaje escudo;
			
			// Variables y métodos propios de cada grupo
			// ...
			public void dibujarFondo() {
				Image imagenFondo = Herramientas.cargarImagen("imagenes/fondo.jpg");
				entorno.dibujarImagen(imagenFondo, 400, 300, Math.toRadians(0), 0.7);
			}
			

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		
		
		//inicio casa
		this.casa=new Casa(400,60,50,50,null);
		//islas
		this.islas=new Isla[15];
		//1
		islas[0] =new Isla(400,100,30,100, null);
		//2
		islas[1]=new Isla(300,200,30,100,null);
		//3
		islas[2]=new Isla(500,200,30,100,null);
		//4
		islas[3]=new Isla(210,300,30,100,null);
		//5
		islas[4]=new Isla(400,300,30,100,null);
		//6
		islas[5]=new Isla(590,300,30,100,null);
		//7
		islas[6]=new Isla(137.5,400,30,100,null);
		//8
		islas[7]=new Isla(305,400,30,100,null);
		//9
		islas[8]=new Isla(490,400,30,100,null);
		//10
		islas[9]=new Isla(662.5,400,30,100,null);
		//11
		islas[10]=new Isla(50,500,30,100,null);
		//12
		islas[11]=new Isla(225,500,30,100,null);
		//13
		islas[12]=new Isla(400,500,30,100,null);
		//14
		islas[13]=new Isla(575,500,30,100,null);
		//15
		islas[14]=new Isla(750,500,30,100,null);
		//inicio pep
		this.pep=new Pep(50,460,50,30,0,0,0,null);
		//inicio escudo
//		this.escudo=new Personaje(pep.getX(), pep.getY(),pep.getAlto(),pep.getAncho()+20,0,0,0,null);
		//inicio gnomo
		this.gnomo=new Gnomo(pep.getX()+50, pep.getY(),pep.getAlto(),pep.getAncho()+20,0,0,0,null);
		//inicio tortuga
		this.tortuga=new Tortuga(pep.getX()+100, pep.getY(),pep.getAlto(),pep.getAncho()+20,0,0,0,null);
		
		
		// Inicia el juego!
		this.entorno.iniciar();
	}
	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */

	public void tick() {
		// Procesamiento de un instante de tiempo
				// ...
		dibujarFondo();
//      dibujo casa
		this.casa.dibujar(entorno);
		//dibujo islas
		for(int i=0;i<15;i++) {
			this.islas[i].dibujar(entorno);
		}
		//tamaño de pep
//		Color miColor = Color.decode("#FF0000"); // Rojo
//
//		entorno.dibujarRectangulo(50,460,30,50,0,miColor);
		//dibujo a pep
		this.pep.dibujar(entorno);
//		 //dibujo escudo
//		this.escudo.dibujar(entorno);
		//dibujo gnomo
		this.gnomo.dibujar(entorno);
		//dibujo escudo
		this.tortuga.dibujar(entorno);

	}



	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
