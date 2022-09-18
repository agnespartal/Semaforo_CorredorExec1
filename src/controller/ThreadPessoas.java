package controller;

import java.util.concurrent.Semaphore;

public class ThreadPessoas extends Thread {

	private int pessoa;
	private Semaphore semaforo;
	
	public ThreadPessoas(int pessoa, Semaphore semaforo) {
		this.pessoa = pessoa; 
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		caminhando();
		//Inicio Seçao critica
		try {
			semaforo.acquire();
			cruzarPorta();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		//Fim Seção critica
		sair();
	}

	private void caminhando() {
		int corredor = 200;
		int deslocamento = (int)((Math.random() * 3) + 4);
		int tempo = 1000;
		int percorrido = 0;
		while (percorrido < corredor) {
			percorrido += deslocamento;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("#"+pessoa+ " ja andou " + percorrido+ "m");
		}
		System.out.println("Elemento "+pessoa+ " chegou");
	}

	private void cruzarPorta() {
		System.out.println("Elemento "+pessoa+ " abriu a porta");
		int tempo = (int)((Math.random() * 2) + 1);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void sair() {
		System.out.println("Elemento "+pessoa+ " cruzou a porta");
	}

}
