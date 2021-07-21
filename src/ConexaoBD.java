import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

	
	private static Connection conexao = null;

	private String fonte = "una_projeto_final";

	private ConexaoBD() {

		try {

			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + fonte, "root", "Jmg146786");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ocorreu um erro na conexao com o banco de dados!!!");
		}
	}

	public static Connection getInstance() {
		if (conexao == null) {
			new ConexaoBD();
		}
		return conexao;
	}

}