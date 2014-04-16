package threads;

import java.util.concurrent.*;

public class Principal {
	
	public static void main(String[] args) {
		Buffer buffer = new Buffer();
		Coordenador coordenador = new Coordenador();
		Escritor [] arrayEscritores = new Escritor[100];
		Leitor [] arrayLeitores = new Leitor[100];
		Future fLeitor, fEscritor;
		
		ScheduledExecutorService escritores = Executors.newSingleThreadScheduledExecutor();
		for(int i = 0 ; i < 100 ; i++) {
			Escritor escritor = new Escritor(coordenador, buffer, "Escritor"+i);
			arrayEscritores[i] = escritor;
		}
		coordenador.adicionarEscritores(arrayEscritores);
	
		for(int i = 0 ; i < 100 ; i++) {
			 fEscritor = escritores.schedule(coordenador.getEscritores()[i], i*100, TimeUnit.MILLISECONDS);
			 coordenador.getEscritores()[i].recebeFuture(fEscritor);
		}
		
		ExecutorService leitores = Executors.newFixedThreadPool(4);
		for(int i = 0 ; i < 100 ; i++) {
			Leitor leitor = new Leitor(coordenador, buffer, "Leitor"+i);
			arrayLeitores[i] = leitor;
		}
		coordenador.adicionarLeitores(arrayLeitores);
		
		for(int i = 0 ; i < 100 ; i++) {
			fLeitor = leitores.submit(coordenador.getLeitores()[i]);
			coordenador.getLeitores()[i].recebeFuture(fLeitor);
		}
		
		System.out.println("ACABOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOU");
	
	}
}
