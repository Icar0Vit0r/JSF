package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import banco.BancoCrud;
import bean.AlunoBean;
import bean.UsuarioBean;

public class AlunoModel {
	
   public void gravarAluno(AlunoBean aluno) {
	   BancoCrud ba = new BancoCrud();
	   Connection conn = null;
	   
	   conn = ba.getConnection();
	   
	   try {
		   if(conn != null) {
			   PreparedStatement st =  conn.prepareStatement("EXECUTE PROCEDURE GRAVAR_ALUNO2(?,?,?,?)");
			   st.setString(1, aluno.getNome()); 
			   st.setString(2, aluno.getMatricula());
			   st.setString(3, aluno.getNota());
			   st.setFloat(4, aluno.getMedia());
			   
			   st.execute();
			   st.close();
			   conn.close();
		   }
	   }catch(Exception e){
		   e.printStackTrace();
	   }
   }
   
   public float mediaAluno() {
	   float maiorMedia = 0;
	   
	   BancoCrud ba = new BancoCrud();
	   Connection conn = null;
	   
	   conn = ba.getConnection();
	   
	   try {
		   if(conn != null) {
			   PreparedStatement st = conn.prepareStatement("SELECT MAX(MEDIA) AS MAIORMEDIA FROM ALUNO2");
			   ResultSet rs = st.executeQuery(); 
			   if(rs.next()) {
				   maiorMedia = rs.getFloat("MAIORMEDIA");
			   }
		   }
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   
	   return maiorMedia;
   }
   
   public void apagarAluno(AlunoBean aluno) {
		
 		BancoCrud bc = new BancoCrud();
 		Connection conn = null;
 		
 		try {
 			
 			conn = (Connection) bc.getConnection();
 			if (conn != null) {
 				
 				PreparedStatement st = conn.prepareStatement("DELETE FROM ALUNO2 WHERE MATRICULA = ?");
 				
 				st.setString(1, aluno.getMatricula());
 				
 				st.execute();
 				
 				st.close();
 				conn.close();
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
     } 
   
   public ArrayList<AlunoBean> getListarAlunos(){
	   AlunoBean ab = new AlunoBean();
	   BancoCrud ba = new BancoCrud();
	   ArrayList<AlunoBean> lista = new ArrayList<AlunoBean>();
	   Connection conn = null;
	   conn = ba.getConnection();
	   
	   try {
		   if(conn != null) {
			   PreparedStatement st = conn.prepareStatement("SELECT NOME, MATRICULA, MEDIA FROM ALUNO2 ORDER BY MEDIA DESC");
			   ResultSet rs = st.executeQuery();
			   
			   while(rs.next()) {
				   ab = new AlunoBean();
				   ab.setNome(rs.getString("NOME"));
				   ab.setMatricula(rs.getString("MATRICULA"));
				   ab.setMedia(rs.getFloat("MEDIA"));
				   
				   lista.add(ab);
			   }
			   
			   st.close();
			   conn.close();
		   }
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   
	   return lista;
   }
}
