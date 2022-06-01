package br.com.fiap.progamer.bean;


import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;

import br.com.fiap.progamer.dao.UserDao;
import br.com.fiap.progamer.model.User;
import br.com.fiap.progamer.service.UploadService;

@Named
@RequestScoped
public class UserBean {
	
	private User user = new User();
	
	
	
	@Inject
	private UserDao dao;
	
	private UploadedFile image;
	
	public String save() {
		System.out.println(user);
		
		String path = UploadService.write(image, "user");
		user.setImagePath(path);
		
		dao.create(user);
		
		FacesMessage facesMessage = new FacesMessage("Usuário cadastrado com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		
		return "users";
	}
	
	public List<User> list() {
		return dao.listAll();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}



}
