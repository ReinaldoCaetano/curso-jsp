package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class UsuarioDAO {
	
	private Connection connection;
	
	public UsuarioDAO() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public void gravarUsuario(ModelLogin objeto) throws Exception {
		
		String sql = "INSERT INTO model_login (nome, email, login, senha) VALUES (?, ?, ?, ?);";
		
		PreparedStatement preparedSQL = connection.prepareStatement(sql);
		preparedSQL.setString(1, objeto.getNome());
		preparedSQL.setString(2, objeto.getEmail());
		preparedSQL.setString(3, objeto.getLogin());
		preparedSQL.setString(4, objeto.getSenha());
		preparedSQL.execute();
		
		connection.commit();
		
	}
}
