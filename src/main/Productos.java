package main;

import processing.core.PApplet;
import processing.core.PImage;

public class Productos {

	private int posx;
	private int posy;
	private PImage imagen;
	private PApplet app;
	private String texto;
	private String formfecha;

	public Productos(int i, int j, PImage imagen, PApplet app, String texto, String formfecha) {
		// TODO Auto-generated constructor stub

		this.posx = i;
		this.posy = j;
		this.imagen = imagen;
		this.app = app;
		this.setTexto(texto);
		this.setFormfecha(formfecha);

	}
	

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}
	
	public PImage getImagen() {
		return imagen;
	}


	public void setImagen(PImage imagen) {
		this.imagen = imagen;
	}

	public PApplet getApp() {
		return app;
	}

	public void setApp(PApplet app) {
		this.app = app;
	}
	
	
	public void mostrar(int i) {
		// TODO Auto-generated method stub
		
		app.image(imagen,posx,(20)+posy*(i));
		app.fill(0);
		app.text("Pedido numero "+(i+1),posx+120,(40)+posy*(i));
		app.text("Hora en que se recibe el pedido: "+formfecha,posx+120,(70)+posy*(i));

	}
	
	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}

	
	public String getFormateado() {
		return formfecha;
	}


	private void setFormfecha(String formfecha) {
		// TODO Auto-generated method stub
		this.formfecha = formfecha;

	}


}
