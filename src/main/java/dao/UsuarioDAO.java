package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class UsuarioDAO {
	
	private Connection connection;
	
	public UsuarioDAO() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin objeto) throws Exception {
		
		String sql = "INSERT INTO model_login (nome, email, login, senha) VALUES (?, ?, ?, ?);";
		
		PreparedStatement preparedSQL = connection.prepareStatement(sql);
		preparedSQL.setString(1, objeto.getNome());
		preparedSQL.setString(2, objeto.getEmail());
		preparedSQL.setString(3, objeto.getLogin());
		preparedSQL.setString(4, objeto.getSenha());
		preparedSQL.execute();
		
		connection.commit();
		
		return this.consultaUsuario(objeto.getLogin());
		
	}
	
	
	public ModelLogin consultaUsuario (String login) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where login = '"+login+"'";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
		}
		
		return modelLogin;
	}
	
	public boolean validarLogin(String login) throws Exception{
		String sql = "select count (1) > 0 as existe from model_login where login = '"+login+"';";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		resultado.next();
		return resultado.getBoolean("existe");
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
