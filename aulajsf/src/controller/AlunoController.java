package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import bean.AlunoBean;
import bean.UsuarioBean;
import model.AlunoModel;

@ManagedBean
public class AlunoController {
   AlunoBean ab = new AlunoBean();
   AlunoModel am = new AlunoModel();
   DataModel<AlunoBean> listaUsuario;
   
   public AlunoBean getAb() {
	   return ab;
   }

   public void setAb(AlunoBean ab) {
	   this.ab = ab;
   }
   
   public void gravarAluno() { 
	   am.gravarAluno(ab); 
   }
   
   public float mediaAluno() {
	   return am.mediaAluno();
   }
   
   public DataModel<AlunoBean> getListarAlunos(){
   	
   	if (listaUsuario == null) {
   	  listaUsuario = new ListDataModel<AlunoBean>(am.getListarAlunos());
   	}
   	
   	return listaUsuario;
   }
   
   public void apagarAluno() {
	   ab = (AlunoBean) listaUsuario.getRowData();
   	   am.apagarAluno(ab);
   }
   
   
   
}
