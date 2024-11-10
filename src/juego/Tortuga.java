package juego;

import java.awt.Image;
import java.util.ArrayList;

import entorno.Entorno;
import entorno.Herramientas;

public class Tortuga {

	private double x;
	private double y;
	private double alto;
	private double ancho;
	private double velocidad;
	private double vida;
	private double daño;
	private Image imagen;
	private boolean aterrizado;
	private boolean movimientoIzquierda;
	private double velocidadVertical;
	private double velocidadHorizontal;
	
	

	
	public Tortuga(double x, double y, double alto, double ancho, double velocidad, double vida, double daño,
			Image imagen, boolean aterrizado, boolean movimientoIzquierda, double velocidadvertical,double velocidadHorizontal) {
		super();
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.velocidad = velocidad;
		this.vida = vida;
		this.daño = daño;
		this.imagen = imagen;
		this.aterrizado = aterrizado;
		this.movimientoIzquierda = movimientoIzquierda;
		this.velocidadVertical =  velocidadvertical;
		this.velocidadHorizontal = velocidadHorizontal;
		
	}


	// Dibuja la tortuga
    public void dibujar(Entorno entorno) {
        if (imagen == null) {
            imagen = Herramientas.cargarImagen("imagenes/tortuga.png");
        }
        entorno.dibujarImagen(imagen, this.x, this.y, 0, 0.05);
    }
    
   
   


	public boolean isMovimientoIzquierda() {
		return movimientoIzquierda;
	}


	public void setMovimientoIzquierda(boolean movimientoIzquierda) {
		this.movimientoIzquierda = movimientoIzquierda;
	}


	public boolean isAterrizado() {
		return aterrizado;
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




	public double getDaño() {
		return daño;
	}




	public void setDaño(double daño) {
		this.daño = daño;
	}




	public Image getImagen() {
		return imagen;
	}




	public void setImagen(Image imagen) {
		this.imagen = imagen;
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

	
	public double getVelocidadVertical() {
		return velocidadVertical;
	}
	
	public void setVelocidadVertical(double velociadVertical) {
		this.velocidadVertical = velociadVertical;
	}
	

	public double getVelocidadHorizontal() {
		return velocidadHorizontal;
	}

	public void setVelocidadHorizontal(double velocidadHorizontal) {
		this.velocidadHorizontal = velocidadHorizontal;
	}

	


	private Tortuga getIsla() {
		// TODO Auto-generated method stub
		return null;
	}



	public void moverAbajo() {
		this.y = this.y + 2;
	}
	
   
	
	
	// DIRECCION
	public void moverDerecha() {
		this.x = this.x + 0.5;
	}

	public void moverIzquierda() {
		this.x = this.x - 0.5;
	}
	
	public boolean isMovimientiIzquierda() {
		
		return movimientoIzquierda;
	}
	
	public void direccionTortuga() {
		double numeroRandom = Math.random();
		this.movimientoIzquierda = numeroRandom >= 0.5;
		
	}
	
	
	

	//resetea el estado de aterrizaje
	
	public void resetaerAterrizado() {
		aterrizado = false;
	}
	

	
	//Verifica si la tortuga esta sobre una isla
	
	public boolean tortugaSobreCasa(Casa casa) {
		if(this.x >= casa.getX() - casa.getAncho() /2-30 && this.x <= casa.getX() +
				casa.getAncho()/2+30) {
			if (this.y + this.alto /2 >= casa.getY() - casa.getAlto() /2-40 && 
					this.y + this.alto / 2 <= casa.getY() + casa.getAlto() / 2+40) { 
                return true; 
			}
		
		}
		return false;
	}
	
	public boolean tortugaSobreIsla(ArrayList<Isla> islas) {
		for (Isla isla : islas) {
			if (this.x >= isla.getX() - isla.getAncho() / 2 && this.x <= isla.getX() + isla.getAncho() / 2) {
				if (this.y + this.alto / 2 >= isla.getY() - isla.getAlto() / 2
						&& this.y + this.alto / 2 <= isla.getY() + isla.getAlto() / 2) 
	                return true;
	            }
	        }
	        return false;		
	}

	public boolean dentroDelEntorno(Entorno entorno) {
		return this.y <= entorno.alto() && this.x <= entorno.ancho()+10;
	}
	
	public boolean islaOcupada(ArrayList<Isla> islas, ArrayList<Tortuga> tortugas) {   // sin terminar
		for (Isla isla : islas) {
			for(Tortuga tortu : tortugas) {
				if(tortu.tortugaSobreIsla(islas)) {
					return true;
				}
			}
		}
		return false;
	}


	

}