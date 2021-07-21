//Acadêmicos: Javan Moisés Girardi - RA 202112335
//Renato Hiroshi Guimaraes Hatano - RA 202026299

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class TestaPrograma {
	static Scanner ler = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		Connection conexao = null;

		String menu = ("\n1. Cadastro de proprietário" 
		+ "\n2. Remoção de proprietário"
		+ "\n3. Busca de proprietário pelo e-mail"
		+ "\n4. Relatório de todos os proprietários de veículos, considerando o nome do proprietário em ordem alfabética, a placa e a descrição"
			+ "do ou dos veículos os quais ele é proprietário" 
		+ "\n5. Altera proprietário"
		+ "\n6. Cadastro de veículo" 
		+ "\n7. Remoção de veículo" 
		+ "\n8. Busca veículo pela placa"
		+ "\n9. Relatório de todos os veículos de uma dada cor, com o nome do proprietário e a quantidade de portas"
		+ "\n10. Busca veículos pela cor" 
		+ "\n11. Busca veículos pela quantidade de portas"
		+ "\n12. Altera veículo" 
		+ "\n13. Sair\n\n");

		int opcao = 13;

		do {
			System.out.println(menu);

			System.out.print("Digite uma opção: ");
			opcao = ler.nextInt();
			ler.nextLine();

			switch (opcao) {
			case 1: {//INSERE PROPRIETARIO
				Proprietario propLido = lerDadosProprietario();
				insereProprietario(propLido, conexao);
				System.out.println("Proprietário Cadastrado.");
				break;
			}

			case 2: {//REMOVE PROP. PELO EMAIL
				int idDoProprietarioSeraRemovido;
				System.out.println("Digite o Id do Proprietário a ser removido");
				idDoProprietarioSeraRemovido = ler.nextInt();
				removerProprietarioPeloId(idDoProprietarioSeraRemovido, conexao);
				System.out.println("Proprietário Excluído.");
				break;
			}

			
			case 3: {// BUSCA PROPRIETARIO PELO EMAIL
				String emailBuscado;
				System.out.println("Digite o e-mail da pessoa que será buscada: ");
				emailBuscado = ler.next();

				Proprietario pEncontrado = buscarProprietarioPeloEmail(emailBuscado, conexao);

				if (pEncontrado != null) {
					mostraProprietario(pEncontrado);
				} else {
					System.out.println("Não há pessoa cadastrada com este email.");
				}
	
				 break; }
			
			case 4: {//FAZ RELATÓRIO TODOS PROPRIETARIOS ORDEM ALFABETICA + PLACA + DESCRIÇÃO VEÍCULOS
			     RelatorioProprietarios(conexao);
			   			   				
  			    break;					
			}
			
				case 5: {// ALTERA DADOS PROPRIETÁRIO
					String emailBuscado;
					System.out.println("Digite o email do proprietário que deseja alterar os dados: ");
					emailBuscado = ler.nextLine();
					Proprietario pSendoAlterado = buscarProprietarioPeloEmail(emailBuscado, conexao);
					if (pSendoAlterado == null) {
						System.out.println("Pessoa não encontrada!");
					} else {
						mostraProprietario(pSendoAlterado);
						System.out.println("Agora, vamos atualizar os dados do proprietario!\n");
						pSendoAlterado = lerDadosProprietario();
						alteraProprietario(pSendoAlterado, emailBuscado, conexao);
					}

					break;
				}
			
			case 6: {//INSERE VEÍCULO
				Veiculo veiculoLido = lerDadosVeiculo();
				insereVeiculo(veiculoLido, conexao);
				System.out.println("Veículo Cadastrado.");
				break;
			}

			case 7: {//REMOVE VEÍCULO PELA PLACA 
				String placaVeiculoSerRemobido;
				System.out.println("Digite a placa do veículo a ser removido");
				placaVeiculoSerRemobido = ler.nextLine();
				removerVeiculoPelaPLaca(placaVeiculoSerRemobido, conexao);
				System.out.println("Veículo Excluído!");
				break;

			}
			
			case 8: {// BUSCA VEICULO PELA PLACA
				String placaBuscada;
				System.out.println("Digite a placa do veículo que será buscado: ");
				placaBuscada = ler.nextLine();

				Veiculo vEncontrado = buscaVeiculoPelaPlaca(placaBuscada, conexao);

				if (vEncontrado != null) {
					mostraVeiculo(vEncontrado);
				} else {
					System.out.println("Não há veículo cadastrado com esta placa.");
				}

				break;
			}
			  
			case 9: {// RELATÓRIO TODOS VEÍCULOS COR/ NOME PROPRIETARIO / QUANTIDADE PORTAS
				String corBuscada;
				System.out.println("Digite a cor dos veículos a serem buscados: ");
				corBuscada = ler.next();
				
				relatorioVeiculoCompleto(corBuscada, conexao);
				
				break;
			}
			 
			case 10: { // BUSCA VEICULO PELA COR

				String corBuscada;
				System.out.println("Digite a cor do veículo que será buscado: ");
				corBuscada = ler.next();

				List<Veiculo> cEncontrada = buscaVeiculoPelaCor(corBuscada, conexao);

				if (cEncontrada != null) {
					mostraListVeiculo(cEncontrada);
				} else {
					System.out.println("Não há veículo cadastrado com esta cor.");
				}

				break;
			}

			case 11: {// BUSCA VEICULO PELA QUANTIDADE DE PORTAS

				int quantidadePortasBuscada;
				System.out.println("Digite a quantidade de portas a ser buscada: ");
				quantidadePortasBuscada = ler.nextInt();

				ArrayList<Veiculo> vetPortas = listaQtdadePortasVeículos(quantidadePortasBuscada, conexao);
				System.out.println("Veículo encontrados");

				for (int i = 0; i < vetPortas.size(); i++) {
					Veiculo v = vetPortas.get(i);
					mostraVeiculo(v);
				}
			}
				break;
			
	
			case 12: {// ALTERA DADOS DO VEÍCULO

				String placaBuscada;
				System.out.println("Digite a placa do veículo que deseja alterar os dados: ");
				placaBuscada = ler.nextLine();
				Veiculo vSendoAlterado = buscaVeiculoPelaPlaca(placaBuscada, conexao);
				if (vSendoAlterado == null) {
					System.out.println("Veículo não encontrado!");
				} else {
					mostraVeiculo(vSendoAlterado);
					System.out.println("Agora, vamos atualizar os dados do veiculo!\n");
					vSendoAlterado = lerDadosVeiculo();
					alteraVeiculo(vSendoAlterado, placaBuscada, conexao);
				}
			}

			break;
			
		case 13: {// Sair
			System.out.println("\nObrigado por utilizar o sistema");
			break;
		}

		default: {
			System.out.println("\nOpção inválida!!!!");
			break;
		}

		}

	} while (opcao != 13);

	ler.close();
}
		
	// métodos para entrada e saída de dados pelo console

	public static Proprietario lerDadosProprietario() {
		Locale.setDefault(Locale.US);

		String nome;
		String email;
		float peso;
		String sexo;
		String cnh;

		System.out.println("Digite os dados do proprietário a ser cadastrado");

		System.out.println("Nome: ");
		nome = ler.nextLine();

		System.out.println("Email: ");
		email = ler.nextLine();

		System.out.println("Digite o peso: ");
		peso = ler.nextFloat();
		ler.nextLine();

		System.out.println("Digite seu gênero (masculino ou feminino): ");
		sexo = ler.nextLine();

		System.out.println("Digite o número da CNH: ");
		cnh = ler.nextLine();
			

		Proprietario p1 = new Proprietario(nome, email, peso, sexo, cnh);

		return p1;

	}

	public static Veiculo lerDadosVeiculo() {

		String placa;
		String cor;
		String descricaoMarcaModelo;
		int ano;
		int quantidadePortas;
		int idProprietario;
	
		System.out.println("Digite os dados do veículo a ser cadastrado");

		System.out.println("Placa: ");
		placa = ler.nextLine();
		System.out.println("Cor: ");
		cor = ler.nextLine();
		System.out.println("Marca e Modelo: ");
		descricaoMarcaModelo = ler.nextLine();
		System.out.println("Ano de fabricação: ");
		ano = ler.nextInt();
		System.out.println("Quantidade de portas do veículo: ");
		quantidadePortas = ler.nextInt();
		System.out.println("Digite o Id do Proprietário: ");
		idProprietario = ler.nextInt();
		
		Veiculo v1 = new Veiculo(placa, cor, descricaoMarcaModelo, ano, quantidadePortas, idProprietario);

		return v1;
	}
	
	public static void mostraProprietario(Proprietario pEncontrado) {
		System.out.println("id "+ pEncontrado.getId());
		System.out.println("Nome: "+ pEncontrado.getNome());
		System.out.println("Email: "+ pEncontrado.getEmail());
		System.out.println("Peso: "+ pEncontrado.getPeso());
		System.out.println("Sexo: "+ pEncontrado.getSexo());
		System.out.println("CNH: "+ pEncontrado.getCnh());
		}
		
	public static void mostraVeiculo(Veiculo vEncontrado) {
		System.out.println("idProprietario "+ vEncontrado.getIdProprietario());
		System.out.println("Placa: "+ vEncontrado.getPlaca());
		System.out.println("Cor: "+ vEncontrado.getCor());
		System.out.println("Descrição: "+ vEncontrado.getDescricaoMarcaModelo());
		System.out.println("Ano: "+ vEncontrado.getAno());	
		System.out.println("Quantidade de Portas: "+ vEncontrado.getQuantidadePortas());
	
		}
	
	public static void mostraListVeiculo(List<Veiculo> veiculoEncontrado) {
		for(int i = 0; i<veiculoEncontrado.size(); i++) {
		System.out.println("idProprietario "+ veiculoEncontrado.get(i).getIdProprietario());	
		System.out.println("Placa: "+ veiculoEncontrado.get(i).getPlaca());
		System.out.println("Cor: "+ veiculoEncontrado.get(i).getCor());
		System.out.println("Descrição: "+ veiculoEncontrado.get(i).getDescricaoMarcaModelo());
		System.out.println("Ano: "+ veiculoEncontrado.get(i).getAno());	
		System.out.println("Quantidade de Portas: "+ veiculoEncontrado.get(i).getQuantidadePortas());
		}
	}
	
		
	// CASE 1 - Insere Proprietário
	public static void insereProprietario(Proprietario propLido, Connection conexao) throws SQLException {
		conexao = ConexaoBD.getInstance();

		String sql = "insert into proprietario (nome, email, peso, sexo, cnh) values(?,?,?,?,?)";

		PreparedStatement stmt = conexao.prepareStatement(sql);

		stmt.setString(1, propLido.getNome());
		stmt.setString(2, propLido.getEmail());
		stmt.setFloat(3, propLido.getPeso());
		stmt.setString(4, propLido.getSexo());
		stmt.setString(5, propLido.getCnh());

		stmt.execute();

		stmt.close();

	}

	// CASE 2 - Remove Proprietário
	public static void removerProprietarioPeloId(int idDoProprietarioSeraRemovido, Connection conexao)
			throws SQLException {
		conexao = ConexaoBD.getInstance();
		
		String sqlVeiculo = "delete from veiculo where idProprietario = ?";
		PreparedStatement stmtVeiculo = conexao.prepareStatement(sqlVeiculo);
		stmtVeiculo.setInt(1, idDoProprietarioSeraRemovido);

		stmtVeiculo.execute();

		stmtVeiculo.close();

		String sqlProprietario = "delete from proprietario where idProprietario = ?";
		PreparedStatement stmtProprietario = conexao.prepareStatement(sqlProprietario);
		stmtProprietario.setInt(1, idDoProprietarioSeraRemovido);

		stmtProprietario.execute();

		stmtProprietario.close();

	}

	// CASE 3 - Busca Proprietário Pelo Email
	public static Proprietario buscarProprietarioPeloEmail(String emailBuscado, Connection conexao)
			throws SQLException {
		conexao = ConexaoBD.getInstance();

		String sql = "select * from proprietario where email LIKE ?";

		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, emailBuscado);
		ResultSet resultado = stmt.executeQuery();

		Proprietario p = null;
		if (resultado.next()) {
			p = new Proprietario(resultado.getInt("idProprietario"), resultado.getString("nome"), resultado.getString("email"), resultado.getFloat("peso"),
					resultado.getString("sexo"), resultado.getString("cnh"));
		};

		resultado.close();

		return p;

	}
		
	//CASE 4 - 
	
	public static void RelatorioProprietarios(Connection conexao) throws SQLException {
	   	    
	    conexao = ConexaoBD.getInstance();
		String sql = "SELECT p.nome, v.placa, v.descricaoMarcaModelo FROM veiculo v INNER JOIN proprietario p ON p.idProprietario = v.idProprietario order by p.nome";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet resultado = stmt.executeQuery();

		while(resultado.next()) {
			System.out.println("\n" + resultado.getString("nome")+ " " + resultado.getString("placa")+" "+resultado.getString("descricaoMarcaModelo"));
				
		}    
		
		resultado.close();
		stmt.close();
			
	}
			
	//CASE 5 - Altera dados do proprietário
	
		public static void alteraProprietario (Proprietario pSendoAlterado, String emailBuscado, Connection conexao) throws SQLException {
			conexao = ConexaoBD.getInstance();
			
			String sql = "UPDATE proprietario SET nome = ?,  email = ?, peso = ?, sexo = ?, cnh = ?"
					+ "WHERE email LIKE ?";
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, pSendoAlterado.getNome());
			stmt.setString(2, pSendoAlterado.getEmail());
			stmt.setFloat(3, (float) pSendoAlterado.getPeso());
			stmt.setString(4, pSendoAlterado.getSexo());
			stmt.setString(5, pSendoAlterado.getCnh());
			stmt.setString(6, emailBuscado);
			
			stmt.execute();
			stmt.close();
			
			
		}
		
		
		
	//CASE 6 - Insere Veículo	
	public static void insereVeiculo(Veiculo veiculoLido, Connection conexao) throws SQLException {
		conexao = ConexaoBD.getInstance();

		String sql = "insert into veiculo (placa, cor, descricaoMarcaModelo, ano, quantidadePortas, idProprietario) values(?,?,?,?,?,?)";

		PreparedStatement stmt = conexao.prepareStatement(sql);

		stmt.setString(1, veiculoLido.getPlaca());
		stmt.setString(2, veiculoLido.getCor());
		stmt.setString(3, veiculoLido.getDescricaoMarcaModelo());
		stmt.setInt(4, veiculoLido.getAno());
		stmt.setInt(5, veiculoLido.getQuantidadePortas());
		stmt.setInt(6, veiculoLido.getIdProprietario());

		stmt.execute();

		stmt.close();

	}

	// CASE 7 - Remove Veículo Pela Placa
	public static void removerVeiculoPelaPLaca(String placaVeiculoSerRemobido, Connection conexao) throws SQLException {
		conexao = ConexaoBD.getInstance();

		String sql = "delete from veiculo where placa LIKE ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, placaVeiculoSerRemobido);

		stmt.execute();

		stmt.close();

	}

	// CASE 8 - Busca Veículo Pela Placa
	public static Veiculo buscaVeiculoPelaPlaca(String placaBuscada, Connection conexao) throws SQLException {
		conexao = ConexaoBD.getInstance();

		String sql = "select * from veiculo where placa LIKE ?";

		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, placaBuscada);
		ResultSet resultado = stmt.executeQuery();

		Veiculo v = null;
		if (resultado.next()) {
			v = new Veiculo(resultado.getString("placa"), resultado.getString("cor"),
					resultado.getString("descricaoMarcaModelo"), resultado.getInt("ano"),
					resultado.getInt("quantidadePortas"), resultado.getInt("idProprietario"));
		};

		resultado.close();

		return v;

	}

	// CASE 9 -
	public static void relatorioVeiculoCompleto(String corBuscada, Connection conexao) throws SQLException {
		conexao = ConexaoBD.getInstance();
		String sql = "SELECT p.nome, v.descricaoMarcaModelo, v.cor, v.quantidadePortas FROM veiculo v INNER JOIN proprietario p ON p.idProprietario = v.idProprietario WHERE v.cor LIKE ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, corBuscada);
		ResultSet resultado = stmt.executeQuery();

		boolean achou = false;
				
		while(resultado.next()) {
			System.out.println(resultado.getString("nome")+" "+ resultado.getString("descricaoMarcaModelo")+" "+ resultado.getString("cor")+" "+resultado.getString("quantidadePortas")+" portas");
		achou = true;		
		}    
		if (!achou) {
			System.out.println("Cor não encontrada!");
		}
		
		resultado.close();
		stmt.close();
	}

	// CASE 10 - Busca Veículo Pela Cor
	public static List<Veiculo> buscaVeiculoPelaCor(String corBuscada, Connection conexao) 
			throws SQLException {
		conexao = ConexaoBD.getInstance();

		String sql = "select * from veiculo where cor = ?";

		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, corBuscada);
		ResultSet resultado = stmt.executeQuery();

		List<Veiculo> c = new ArrayList<Veiculo>();
		while (resultado.next()) {
			c.add(new Veiculo(resultado.getString("placa"), resultado.getString("cor"),
					resultado.getString("descricaoMarcaModelo"), resultado.getInt("ano"),
					resultado.getInt("quantidadePortas"), resultado.getInt("idProprietario")));
		};
		
		if (c.size()==0) {
			System.out.println("Cor não encontrada!");
		}
		resultado.close();
		return c;
		
	}

	// CASE 11 - Busca Veículo Pela Quantidade De Portas
	
	public static ArrayList <Veiculo> listaQtdadePortasVeículos(int quantidadePortas, Connection conexao)
			throws SQLException {
		conexao = ConexaoBD.getInstance();
		
		ArrayList <Veiculo> vetPortas = new ArrayList<Veiculo>(); 
		
		String sql = "select * from veiculo where quantidadePortas = ?";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, quantidadePortas);
		ResultSet resultado = stmt.executeQuery();
		
		Veiculo v = null;
		
		while (resultado.next()) {
			v = new Veiculo(
				resultado.getString("placa"),
				resultado.getString("cor"),
				resultado.getString("descricaoMarcaModelo"),
				resultado.getInt("ano"),
				resultado.getInt("quantidadePortas"),
				resultado.getInt("idProprietario")
					);
			
			vetPortas.add(v);
					
		}
		
		
		resultado.close();
		stmt.close();
		
		return vetPortas;
	}
	


	// CASE 12 - Altera dados do veículo
		public static void alteraVeiculo(Veiculo vSendoAlterado, String placaBuscada, Connection conexao)
				throws SQLException {
			conexao = ConexaoBD.getInstance();

			String sql = "UPDATE veiculo SET placa = ?,  cor = ?, descricaoMarcaModelo = ?, ano = ?, quantidadePortas = ? WHERE placa LIKE ?";

			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, vSendoAlterado.getPlaca());
			stmt.setString(2, vSendoAlterado.getCor());
			stmt.setString(3, vSendoAlterado.getDescricaoMarcaModelo());
			stmt.setInt(4, vSendoAlterado.getAno());
			stmt.setInt(5, vSendoAlterado.getQuantidadePortas());
			stmt.setString(6, placaBuscada);

			stmt.execute();
			stmt.close();

		}

}
