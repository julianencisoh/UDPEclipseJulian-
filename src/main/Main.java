package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

	private LinkedList<Productos> producto;
	PImage cono;
	PImage waffle;
	PImage payaso;
	PImage festival;

	int conteo = 0;
	DatagramSocket socket;
	String mensaje;
	String puerto;

	public static void main(String[] args) {
		PApplet.main("main.Main");

	}

	public void settings() {
		size(800, 800);
	}

	public void setup() {

		producto = new LinkedList<Productos>();

		cono = loadImage("fotos/cono.png");
		waffle = loadImage("fotos/waffle.png");
		festival = loadImage("fotos/festival.png");
		payaso = loadImage("fotos/payaso.png");

		new Thread(() -> {

			try {
				socket = new DatagramSocket(6000);

				// Recepcion del mensaje
				while (true) {
					byte[] buff = new byte[256];
					DatagramPacket packet = new DatagramPacket(buff, buff.length);

					System.out.println("Esperando.....");
					socket.receive(packet);
					String queproducto = new String(packet.getData()).trim();
					System.out.println(queproducto + packet.getSocketAddress());

					if (conteo < 5) {
						switch (queproducto) {

						case "cono":
							Calendar calendarcono = Calendar.getInstance();
							Date fechacono = calendarcono.getTime();

							String fechacasteadacono = fechacono.toString();
							System.out.println(fechacasteadacono);

							SimpleDateFormat formatcono = new SimpleDateFormat("HH:mm");

							String forcono = formatcono.format(fechacono);
							producto.add(new Productos(20, 70, cono, this, "cono", forcono));
							conteo += 1;
							break;

						case "waffle":
							Calendar calendarwaffle = Calendar.getInstance();
							Date fechawaffle = calendarwaffle.getTime();

							String fechacasteadawaffle = fechawaffle.toString();
							System.out.println(fechacasteadawaffle);

							SimpleDateFormat formatwaffle = new SimpleDateFormat("HH:mm");

							String forwaffle = formatwaffle.format(fechawaffle);
							producto.add(new Productos(20, 70, waffle, this, "waffle", forwaffle));
							conteo += 1;
							break;

						case "festival":
							Calendar calendarfestival = Calendar.getInstance();
							Date fechafestival = calendarfestival.getTime();

							String fechacasteadafestival = fechafestival.toString();
							System.out.println(fechacasteadafestival);

							SimpleDateFormat formatfestival = new SimpleDateFormat("HH:mm");

							String forfestival = formatfestival.format(fechafestival);
							producto.add(new Productos(20, 70, festival, this, "festival", forfestival));
							conteo += 1;
							break;

						case "payaso":
							Calendar calendarpayaso = Calendar.getInstance();
							Date fechapayaso = calendarpayaso.getTime();

							String fechacasteadapayaso = fechapayaso.toString();
							System.out.println(fechacasteadapayaso);

							SimpleDateFormat formatpayaso = new SimpleDateFormat("HH:mm");

							String forpayaso = formatpayaso.format(fechapayaso);
							producto.add(new Productos(20, 70, payaso, this, "payaso", forpayaso));
							conteo += 1;
							break;
						}

					}

				}

			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		).start();
	}

	public void draw() {
		background(255, 255, 255);

		for (int i = 0; i < producto.size(); i++) {
			producto.get(i).mostrar(i);
		}
	}

	public void mouseClicked() {
		new Thread(() -> {

			try {

				for (int i = 0; i < producto.size(); i++) {

					int x = producto.get(i).getPosx();
					int y = (producto.get(i).getPosy() * i) + 20;

					if (mouseX > x && mouseX < x + 120 && mouseY > y && mouseY < (y + 70)) {

						mensaje = "Listo el pedido numero " + (i + 1);
						System.out.println(mensaje);
						producto.remove(i);

					}
				}

				DatagramPacket packet = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length,
						InetAddress.getByName("127.0.0.1"), 63758);
				socket.send(packet);

			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}).start();
	}
}
