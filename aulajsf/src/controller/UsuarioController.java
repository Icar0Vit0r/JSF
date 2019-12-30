package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.event.RowEditEvent;

import bean.UsuarioBean;
import model.UsuarioModel;

@ManagedBean
public class UsuarioController {
    UsuarioBean usuario = new UsuarioBean();
    UsuarioModel usmodel = new UsuarioModel();
    DataModel<UsuarioBean> listaUsuario;
    
    public UsuarioBean getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}
    
    public void gravarUsuario() {
    	usmodel.gravarUsuario(usuario);
    }
     
    public DataModel<UsuarioBean> getListarUsuario(){
    	
    	if (listaUsuario == null) {
    	  listaUsuario = new ListDataModel<UsuarioBean>(usmodel.getListarUsuario());
    	}
    	
    	return listaUsuario;
    }
    
    public void alterarUsuario (RowEditEvent event) {
    	usuario = (UsuarioBean) event.getObject();
    	usmodel.alterarUsuario(usuario);
    }
    
    public void apagarUsuario() {
    	usuario = (UsuarioBean) listaUsuario.getRowData();
    	usmodel.apagarUsuario(usuario);
    }

	
}
