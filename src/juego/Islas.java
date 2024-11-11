package juego;

import java.util.ArrayList;

public class Islas {
	private ArrayList<Isla> islas;

	public Islas() {
		this.islas = new ArrayList<>();

		// Inicialización de las islas en el constructor
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
	}

	// Método para obtener todas las islas
	public ArrayList<Isla> getIslas() {
		return islas;
	}

}
