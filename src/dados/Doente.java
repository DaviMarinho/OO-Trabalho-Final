package dados;

public class Doente extends Paciente {
	public Character estadoDeSaude;

	public Doente(Integer indentificador, StringBuilder nomeCompleto, Character genero, Character estadoDeSaude) {
		super(indentificador, nomeCompleto, genero);
		setEstadoDeSaude(estadoDeSaude);
	}

	public Character getEstadoDeSaude() {
		return estadoDeSaude;
	}

	public void setEstadoDeSaude(Character estadoDeSaude) {
		this.estadoDeSaude = estadoDeSaude;
	}
}
