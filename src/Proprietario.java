
public class Proprietario {

	private int id;
	private String nome;
	private String email;
	private float peso;
	private String sexo;
	private String cnh;
	
	public Proprietario () {};

	public Proprietario(int id, String nome, String email, float peso, String sexo, String cnh) {

		this.id = id;
		this.nome = nome;
		this.email = email;
		this.peso = peso;
		this.sexo = sexo;
		this.cnh = cnh;
				
	}
	
	public Proprietario(String nome, String email, float peso, String sexo, String cnh) {
		
		this.nome = nome;
		this.email = email;
		this.peso = peso;
		this.sexo = sexo;
		this.cnh = cnh;
				
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

}	