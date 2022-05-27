package br.com.senai.manutencaosenaiapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.manutencaosenaiapi.entity.Login;
import br.com.senai.manutencaosenaiapi.repository.LoginsRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginsRepository loginsRepository;
	
	public Login logar(String login, String senha, String perfil) {
		Login loginEncontrado = loginsRepository.logarPor(login, senha, perfil);
		Preconditions.checkNotNull(loginEncontrado, "Usuario e /ou senha invalidos."
				+ " Verifique os dados e tente novamente");
		return loginEncontrado;
	}

}
