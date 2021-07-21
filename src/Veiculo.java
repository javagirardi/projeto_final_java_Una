
public class Veiculo {

	private String placa;
	private String cor;
	private String descricaoMarcaModelo;
	private int ano;
	private int quantidadePortas;
	private int idProprietario;

	public int getIdProprietario() {
		return idProprietario;
	}

	public void setIdProprietario(int idProprietario) {
		this.idProprietario = idProprietario;
	}

	public Veiculo() {

	}

	public Veiculo(String placa, String cor, String descricaoMarcaModelo, int ano, int quantidadePortas, int idProprietario) {

		this.placa = placa;
		this.cor = cor;
		this.descricaoMarcaModelo = descricaoMarcaModelo;
		this.ano = ano;
		this.quantidadePortas = quantidadePortas;
		this.idProprietario = idProprietario;
						
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getDescricaoMarcaModelo() {
		return descricaoMarcaModelo;
	}

	public void setDescricaoMarcaModelo(String descricaoMarcaModelo) {
		this.descricaoMarcaModelo = descricaoMarcaModelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getQuantidadePortas() {
		return quantidadePortas;
	}

	public void setQuantidadePortas(int quantidadePortas) {
		this.quantidadePortas = quantidadePortas;
	}

	
}


	