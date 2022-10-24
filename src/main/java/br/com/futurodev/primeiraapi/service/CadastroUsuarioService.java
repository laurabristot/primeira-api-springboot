package br.com.futurodev.primeiraapi.service;

import br.com.futurodev.primeiraapi.model.UsuarioModel;
import br.com.futurodev.primeiraapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioModel salvar(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    public void delete (Long usuarioId){
        usuarioRepository.deleteById(usuarioId);
    }

    public UsuarioModel getUserById(Long usuario) {
        return usuarioRepository.findById(usuario).get();
    }

}
