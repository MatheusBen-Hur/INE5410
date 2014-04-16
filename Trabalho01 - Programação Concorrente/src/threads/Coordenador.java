package threads;

public class Coordenador {
	
	private Escritor[] escritores;
	private Leitor[] leitores;
	
	public Coordenador() {
		
	}
	
	public void adicionarEscritores(Escritor[] escritores) {
		this.escritores = escritores;
	}
	
	public void adicionarLeitores(Leitor[] leitores) {
		this.leitores = leitores;
	}
	
	public Escritor[] getEscritores() {
		return this.escritores;
	}
	
	public Leitor[] getLeitores() {
		return this.leitores;
	}
	
	public void bufferNaoVazio() {
		
		this.getLeitorNaoConcluido().getFuture().cancel(true);	
	}
	
	public void bufferNaoCheio() {
		this.getEscritorNaoConcluido().getFuture().cancel(true);
	}

	public Escritor getEscritorNaoConcluido() {
		for(int i=0 ; i < this.escritores.length ; i++) {
			if(!this.escritores[i].estaConcluido()) {
				return this.escritores[i];
			}
		}
		return this.escritores[0];
	}

	public Leitor getLeitorNaoConcluido() {
		for(int i=0 ; i < this.leitores.length ; i++){
			if(!this.leitores[i].estaConcluido()) {
				return this.leitores[i];
			}	
		}
		return this.leitores[0];
		
	}

}
