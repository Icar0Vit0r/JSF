package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import banco.BancoCrud;
import bean.UsuarioBean;

public class UsuarioModel {
	
    public void gravarUsuario (UsuarioBean usuario){
		
		BancoCrud ba = new BancoCrud();
		Connection conn = null;
		
		conn = ba.getConnection();
		
		try {
			if (conn != null) {
				
				PreparedStatement st =  conn.prepareStatement("EXECUTE PROCEDURE GRAVAR_USUARIO(?,?,?)");
				
				st.setString(1, usuario.getNome());
				st.setString(2, usuario.getLogin());
				st.setString(3, usuario.getSenha());
				
				st.execute();
				
				st.close();
				conn.close();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public void alterarUsuario(UsuarioBean usuario) {
    	
		BancoCrud bc = new BancoCrud();
		Connection conn = null;
		
		try {
			conn = (Connection) bc.getConnection();
			
			if (conn != null) {
				
				PreparedStatement st = ((java.sql.Connection) conn).prepareStatement("UPDATE USUARIO SET NOME = ?, SENHA = ? WHERE CODIGO = ?;");
				
				
				st.setString(1, usuario.getNome());
				st.setString(2, usuario.getSenha());
				st.setInt(3, Integer.parseInt(usuario.getCodigo()));
				
				st.execute();
				
				st.close();
				conn.close();				
			}
		} catch (Exception e) {
			e.printStackTrace();
	    }
   }
    
    public void apagarUsuario(UsuarioBean usuario) {
		
		BancoCrud bc = new BancoCrud();
		Connection conn = null;
		
		try {
			
			conn = (Connection) bc.getConnection();
			if (conn != null) {
				
				PreparedStatement st = ((java.sql.Connection) conn).prepareStatement("DELETE FROM USUARIO WHERE CODIGO = ?;");
				
				st.setString(1, usuario.getCodigo());
				
				st.execute();
				
				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    } 
    
    public ArrayList<UsuarioBean> getListarUsuario() {
		
		UsuarioBean usuario = new UsuarioBean();
		BancoCrud bc = new BancoCrud();
		Connection conn = null;
		
		ArrayList<UsuarioBean> lista = new ArrayList<UsuarioBean>();
		
		try {
			
			conn = (Connection) bc.getConnection();
			if (conn != null) {
				
				PreparedStatement st = ((java.sql.Connection) conn).prepareStatement("SELECT MAX(AVG(NOTA1 + NOTA2)) AS MAIORMEDIA FROM USUARIO");
				
				ResultSet rs = st.executeQuery();
				
				while(rs.next()) {
					usuario = new UsuarioBean();
					
					usuario.setCodigo(rs.getString("codigo"));
					usuario.setLogin(rs.getString("login"));
					usuario.setNome(rs.getString("nome"));
					usuario.setSenha(rs.getString("senha"));
					
					lista.add(usuario);
				}
				
				st.close();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
			
	}
}
