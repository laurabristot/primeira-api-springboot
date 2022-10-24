package br.com.futurodev.primeiraapi.controllers;

import br.com.futurodev.primeiraapi.dto.TelefoneDTO;
import br.com.futurodev.primeiraapi.dto.UsuarioDTO;
import br.com.futurodev.primeiraapi.input.UsuarioInput;
import br.com.futurodev.primeiraapi.model.TelefoneModel;
import br.com.futurodev.primeiraapi.model.UsuarioModel;
import br.com.futurodev.primeiraapi.repository.UsuarioRepository;
import br.com.futurodev.primeiraapi.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;


    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioInput usuarioInput){
        UsuarioModel usu = cadastroUsuarioService.salvar(toDomainObject(usuarioInput));
        return new ResponseEntity<UsuarioDTO>(toModel(usu), HttpStatus.CREATED);
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioDTO> atualizar(@RequestBody UsuarioInput usuarioInput){
        UsuarioModel usu = cadastroUsuarioService.salvar(toDomainObject(usuarioInput));
        return new ResponseEntity<UsuarioDTO>(toModel(usu), HttpStatus.OK);
    }

    @DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idUsuario){
        cadastroUsuarioService.delete(idUsuario);
        return new ResponseEntity<String>("usuário deletado com sucesso", HttpStatus.OK);
    }

    @GetMapping(value = "/{idUsuario}", produces = "application/json")
    public ResponseEntity<UsuarioDTO> getUserById(@PathVariable(value = "idUsuario") Long idUsuario){

        UsuarioModel usu = cadastroUsuarioService.getUserById(idUsuario);
        UsuarioDTO usuarioDTO = toModel(usu);


        return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.OK);
    }

    private UsuarioDTO toModel(UsuarioModel usu) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usu.getId());
        usuarioDTO.setNome(usu.getNome());
        usuarioDTO.setLogin(usu.getLogin());


        for (int i = 0; i<usu.getTelefones().size(); i++) {
            TelefoneDTO telefoneDTO = new TelefoneDTO();
         telefoneDTO.setId(usu.getTelefones().get(i).getId());
         telefoneDTO.setNumero(usu.getTelefones().get(i).getNumero());
         telefoneDTO.setTipo(usu.getTelefones().get(i).getTipo());

         usuarioDTO.getTelefones().add(telefoneDTO);
        }

        return usuarioDTO;
    }

    private UsuarioModel toDomainObject(UsuarioInput usu){
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setId(usu.getId());
        usuarioModel.setNome(usu.getNome());
        usuarioModel.setLogin(usu.getLogin());
        usuarioModel.setSenha(usu.getSenha());


        for (int i = 0; i<usu.getTelefones().size(); i++) {
            TelefoneModel telefoneModel = new TelefoneModel();
            telefoneModel.setId(usu.getTelefones().get(i).getId());
            telefoneModel.setNumero(usu.getTelefones().get(i).getNumero());
            telefoneModel.setTipo(usu.getTelefones().get(i).getTipo());

            usuarioModel.getTelefones().add(telefoneModel);
        }


        return usuarioModel;
    }

//    @GetMapping(value = "/buscarPorNome", produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<List<UsuarioModel>> getUserByName(@RequestParam (name = "nome") String nome){
//        List<UsuarioModel> usuarios = usuarioRepository.getUserByName(nome);
//        return new ResponseEntity<List<UsuarioModel>>(usuarios, HttpStatus.OK);
//    }

}
