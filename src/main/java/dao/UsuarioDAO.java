package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class UsuarioDAO {
	
	private Connection connection;
	
	public UsuarioDAO() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin objeto, Long userLogado) throws Exception {
		
		if(objeto.isNovo()) {
		
		String sql = "INSERT INTO model_login (nome, email, login, senha, usuario_id, perfil, sexo, cep, logradouro, bairro, localidade, uf, numero)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		PreparedStatement preparedSQL = connection.prepareStatement(sql);
		preparedSQL.setString(1, objeto.getNome());
		preparedSQL.setString(2, objeto.getEmail());
		preparedSQL.setString(3, objeto.getLogin());
		preparedSQL.setString(4, objeto.getSenha());
		preparedSQL.setLong(5, userLogado);
		preparedSQL.setString(6, objeto.getPerfil());
		preparedSQL.setString(7, objeto.getSexo());
		preparedSQL.setString(8, objeto.getCep());
		preparedSQL.setString(9, objeto.getLogradouro());
		preparedSQL.setString(10, objeto.getBairro());
		preparedSQL.setString(11, objeto.getLocalidade());
		preparedSQL.setString(12, objeto.getUf());
		preparedSQL.setString(13, objeto.getNumero());
		
		preparedSQL.execute();
		
		connection.commit();
		
		if (objeto.getFotouser() != null && !objeto.getFotouser().isEmpty()) {
			sql = "update model_login set fotouser =?, extensaofotouser = ? where login = ?";
			preparedSQL = connection.prepareStatement(sql);
			preparedSQL.setString(1, objeto.getFotouser());
			preparedSQL.setString(2, objeto.getExtensaofotouser());
			preparedSQL.setString(3, objeto.getLogin());
			
			preparedSQL.execute();
			connection.commit();
			
		}
		
		 } else {
				String sql = "UPDATE model_login SET  nome=?, email=?, login=?, senha=?, perfil=?, sexo=?, cep=?,"
						+ " logradouro=?, bairro=?, localidade=?, uf=?, numero=? WHERE id = "+objeto.getId()+" ;";
				PreparedStatement prepareSql = connection.prepareStatement(sql);
				prepareSql.setString(1, objeto.getNome());
				prepareSql.setString(2, objeto.getEmail());
				prepareSql.setString(3, objeto.getLogin());
				prepareSql.setString(4, objeto.getSenha());
				prepareSql.setString(5, objeto.getPerfil());
				prepareSql.setString(6, objeto.getSexo());
				prepareSql.setString(7, objeto.getCep());
				prepareSql.setString(8, objeto.getLogradouro());
				prepareSql.setString(9, objeto.getBairro());
				prepareSql.setString(10, objeto.getLocalidade());
				prepareSql.setString(11, objeto.getUf());
				prepareSql.setString(12, objeto.getNumero());
				
				prepareSql.executeUpdate();
				connection.commit();
				
				if (objeto.getFotouser() != null && !objeto.getFotouser().isEmpty()) {
					sql = "update model_login set fotouser =?, extensaofotouser = ? where id = ?";
					prepareSql = connection.prepareStatement(sql);
					prepareSql.setString(1, objeto.getFotouser());
					prepareSql.setString(2, objeto.getExtensaofotouser());
					prepareSql.setLong(3, objeto.getId());
					
					prepareSql.execute();
					connection.commit();
					
				}
				
			}
			return this.consultaUsuario(objeto.getLogin(),userLogado);
		
	}
	
	public int totalPagina(Long userLogado) throws Exception {
		String sql = "select count(1) as total from model_login where usuario_id = "+userLogado;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		resultado.next();
		Double cadastros = resultado.getDouble("total");
		Double porpagina = 5.0;
		Double pagina = cadastros / porpagina;
		Double resto = pagina % 2;
		if(resto > 0) {
			pagina ++;
		}
		return pagina.intValue() ;
	}
	
	public List<ModelLogin> consultaUsuarioListPaginada(Long userLogado, Integer offset) throws Exception{
			
			List<ModelLogin> retorno = new ArrayList<ModelLogin>();
			String sql = "select * from model_login where useradmin is false and usuario_id = "+userLogado+ " order by nome offset "+offset+" limit 5 ;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()) {
				ModelLogin modelLogin = new ModelLogin();
				modelLogin.setEmail(resultado.getString("email"));
				modelLogin.setId(resultado.getLong("id"));
				modelLogin.setLogin(resultado.getString("login"));
				modelLogin.setNome(resultado.getString("nome"));
				//modelLogin.setSenha(resultado.getString("senha"));
				modelLogin.setPerfil(resultado.getString("perfil"));
				modelLogin.setSexo(resultado.getString("sexo"));
				
				retorno.add(modelLogin);
			}
			
			return retorno;
		}
	
	public List<ModelLogin> consultaUsuarioList(Long userLogado) throws Exception{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		String sql = "select * from model_login where useradmin is false and usuario_id = "+userLogado+ " limit 5 ;";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			//modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			
			retorno.add(modelLogin);
		}
		
		return retorno;
	}
	public int consultaUsuarioListTotalPaginacao(String nome, Long userLogado) throws Exception{

		String sql = "select count(1) as total from model_login where nome like ?  and useradmin is false  and usuario_id = ?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" +nome+ "%");
		statement.setLong(2, userLogado);
		
		ResultSet resultado = statement.executeQuery();
		
		resultado.next();
		Double cadastros = resultado.getDouble("total");
		Double porpagina = 5.0;
		Double pagina = cadastros / porpagina;
		Double resto = pagina % 2;
		if(resto > 0) {
			pagina ++;
		}
		return pagina.intValue() ;
		
		
	}
	
	public List<ModelLogin> consultaUsuarioList(String nome, Long userLogado) throws Exception{
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		String sql = "select * from model_login where nome like ?  and useradmin is false  and usuario_id = ? limit 5;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" +nome+ "%");
		statement.setLong(2, userLogado);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			//modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			
			retorno.add(modelLogin);
		}
		
		return retorno;
	}
	public List<ModelLogin> consultaUsuarioListOffSet(String nome, Long userLogado, int offset) throws Exception{
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		String sql = "select * from model_login where nome like ?  and useradmin is false  and usuario_id = ? order by nome offset "+offset+" limit 5;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" +nome+ "%");
		statement.setLong(2, userLogado);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			//modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			
			retorno.add(modelLogin);
		}
		
		return retorno;
	}
	
	public ModelLogin consultaUsuarioLogado (String login) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where login = '"+login+"';";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setUseradmin(resultado.getBoolean("useradmin"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotouser(resultado.getString("fotouser"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));
		}
		
		return modelLogin;
	}
	
public ModelLogin consultaUsuario (String login) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where login = '"+login+"' and useradmin is false ;";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setUseradmin(resultado.getBoolean("useradmin"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotouser(resultado.getString("fotouser"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));

		}
		
		return modelLogin;
	}
	
	public ModelLogin consultaUsuario (String login, Long userLogado) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where login = '"+login+"' and useradmin is false and usuario_id ="+userLogado+";";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotouser(resultado.getString("fotouser"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));

		}
		
		return modelLogin;
	}
	public ModelLogin consultaUsuarioId (String id, Long userLogado) throws Exception {
			
			ModelLogin modelLogin = new ModelLogin();
			
			String sql = "select * from model_login where id = ? and useradmin is false and usuario_id = ? ;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, Long.parseLong(id));
			statement.setLong(2, userLogado);
			
			ResultSet resultado = statement.executeQuery();
			
			while (resultado.next()) {
				modelLogin.setId(resultado.getLong("id"));
				modelLogin.setEmail(resultado.getString("email"));
				modelLogin.setLogin(resultado.getString("login"));
				modelLogin.setNome(resultado.getString("nome"));
				modelLogin.setSenha(resultado.getString("senha"));
				modelLogin.setPerfil(resultado.getString("perfil"));
				modelLogin.setSexo(resultado.getString("sexo"));
				modelLogin.setFotouser(resultado.getString("fotouser"));
				modelLogin.setExtensaofotouser(resultado.getString("extensaofotouser"));
				modelLogin.setCep(resultado.getString("cep"));
				modelLogin.setLogradouro(resultado.getString("logradouro"));
				modelLogin.setBairro(resultado.getString("bairro"));
				modelLogin.setLocalidade(resultado.getString("localidade"));
				modelLogin.setUf(resultado.getString("uf"));
				modelLogin.setNumero(resultado.getString("numero"));

			}
			
			return modelLogin;
		}
	
	
	public ModelLogin consultaUsuarioId (Long id) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where id = ? and useradmin is false ;";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
	
		
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotouser(resultado.getString("fotouser"));
			modelLogin.setExtensaofotouser(resultado.getString("extensaofotouser"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));

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
		
	
	
	
	public void deletarUsuario(String idUsuario) throws Exception {
		String sql = "delete from model_login where id = ? and useradmin is false;";
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong(1, Long.parseLong(idUsuario));
		prepareSql.executeUpdate();
		connection.commit();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
