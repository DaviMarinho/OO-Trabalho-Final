package dados;

public class Saudavel extends Paciente{
	private Integer idade;

	public Saudavel(Integer indentificador, StringBuilder nomeCompleto, Character genero, Integer idade) {
		super(indentificador, nomeCompleto, genero);
		setIdade(idade);
	}
	
	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}
}
