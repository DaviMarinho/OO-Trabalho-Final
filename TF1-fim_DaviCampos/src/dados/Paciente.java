package dados;

public class Paciente {
	private StringBuilder nomeCompleto;
	private Character genero;
	private Integer identificador;

	public Paciente(Integer identificador, StringBuilder nomeCompleto, Character genero) {
		setIdentificador(identificador);
		setNomeCompleto(nomeCompleto);
		setGenero(genero);
	}

	public StringBuilder getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(StringBuilder nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Character getGenero() {
		return genero;
	}

	public void setGenero(Character genero) {
		this.genero = genero;
	}

	public Integer getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}

}
