package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.SingleConnectionBanco;
import model.ModelTelefone;

public class TelefoneDAO {
	
	private Connection connection;
	
	public TelefoneDAO() {
		connection = SingleConnectionBanco.getConnection();
		
		
		
	}
	
	public void gravaTelefone(ModelTelefone modelTelefone)  throws Exception{
		String sql = "insert into telefone (numero, usuario_pai_id, usuario_cad_id) values (?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, modelTelefone.getNumero());
		preparedStatement.setLong(2, modelTelefone.getUsuario_pai_id().getId());
		preparedStatement.setLong(3, modelTelefone.getUsuario_cad_id().getId());
		
		preparedStatement.execute();
		connection.commit();
		
		
	}

}
