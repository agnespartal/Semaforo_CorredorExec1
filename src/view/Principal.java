package view;

import java.util.concurrent.Semaphore;

import controller.ThreadPessoas;

public class Principal {

	public static void main(String[] args) {
		
		int permissoes = 1;
		Semaphore semaphore = new Semaphore(permissoes);
		
		for (int pessoa = 1; pessoa < 5; pessoa++) {
			Thread elemento = new ThreadPessoas(pessoa, semaphore);
			elemento.start();
		}
	}

}
