package br.com.futurodev.primeiraapi.controllers;

import br.com.futurodev.primeiraapi.dto.TelefoneDTO;
import br.com.futurodev.primeiraapi.dto.UsuarioDTO;
import br.com.futurodev.primeiraapi.input.UsuarioInput;
import br.com.futurodev.primeiraapi.model.TelefoneModel;
import br.com.futurodev.primeiraapi.model.UsuarioModel;
import br.com.futurodev.primeiraapi.service.CasdastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private CasdastroUsuarioService casdastroUsuarioService;


   /* @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioModel> cadastrar(@RequestBody UsuarioModel usuario){
        UsuarioModel usu = casdastroUsuarioService.salvar(usuario);
        return new ResponseEntity<UsuarioDTO>(toModel(usu), HttpStatus.CREATED);
    }*/

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioInput usuarioInput){
        // converte UsuarioInput em UsuarioModel
        UsuarioModel usu = toDomainObject(usuarioInput);
        // chama nosso service para salvar o UsuarioModel no banco de dados
        casdastroUsuarioService.salvar(usu);

        // montamos o retorno com a reposta da requisição, convertemos UsuarioModel em RepresentationModel
        return new ResponseEntity<UsuarioDTO>(toModel(usu), HttpStatus.CREATED);

    }


 /*   @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioDTO> atualizar(@RequestBody UsuarioModel usuario){
        UsuarioModel usu = casdastroUsuarioService.salvar(usuario);
        return new ResponseEntity<UsuarioDTO>(toModel(usu), HttpStatus.OK);

    }*/


    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioDTO> atualizar(@RequestBody UsuarioInput usuarioInput){
        UsuarioModel usuario = casdastroUsuarioService.salvar(toDomainObject(usuarioInput));
        return new ResponseEntity<UsuarioDTO>(toModel(usuario), HttpStatus.OK);

    }

    @DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idUsuario){
         casdastroUsuarioService.delete(idUsuario);
         return new ResponseEntity<String>("Usuário deletado com sucesso!",HttpStatus.OK);
    }


 /*   @GetMapping(value = "/{idUsuario}", produces = "application/json")
    public ResponseEntity<UsuarioModel> getUserById(@PathVariable(value = "idUsuario") Long idUsuario){
        UsuarioModel usu =  casdastroUsuarioService.getUserById(idUsuario);
        return new ResponseEntity<UsuarioModel>(usu, HttpStatus.OK);
    }*/

    @GetMapping(value = "/{idUsuario}", produces = "application/json")
    public ResponseEntity<UsuarioDTO> getUserById(@PathVariable(value = "idUsuario") Long idUsuario){
         UsuarioModel usu =  casdastroUsuarioService.getUserById(idUsuario);

        UsuarioDTO usuarioDTO = toModel(usu);

        return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.OK);

    }

    @GetMapping(value = "/buscarPorNome", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<UsuarioDTO>> getUserByName(@RequestParam (name = "nome") String nome){
        // obtem a lista de usuario do tipo Model, nossas entidades
        List<UsuarioModel> usuarios = casdastroUsuarioService.getUserByName(nome);

        // nos convertemos o objeto do tipo UsuarioModel para RepresentationModel (DTO)
        List<UsuarioDTO> usuariosRepresentationModel = toCollectionModel(usuarios);
        return new ResponseEntity<List<UsuarioDTO>>(usuariosRepresentationModel,HttpStatus.OK);
    }


    // converte um objeto do tipo UsuarioModel para um objeto do tipo RepresentationModel
    private UsuarioDTO toModel(UsuarioModel usu) {

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usu.getId());
        usuarioDTO.setNome(usu.getNome());
        usuarioDTO.setLogin(usu.getLogin());
        usuarioDTO.setSenha(usu.getSenha());
        usuarioDTO.setDataCadastro(usu.getDataCadastro());
        usuarioDTO.setDataAtualizacao(usu.getDataAtualizacao());


        for (int i=0; i<usu.getTelefones().size(); i++){

            TelefoneDTO telefoneDTO = new TelefoneDTO();
            telefoneDTO.setTipo(usu.getTelefones().get(i).getTipo());
            telefoneDTO.setNumero(usu.getTelefones().get(i).getNumero());
            telefoneDTO.setId(usu.getTelefones().get(i).getId());

            usuarioDTO.getTelefones().add(telefoneDTO);
        }

        return usuarioDTO;
    }

    // Converte uma lista de objetos do tipo UsuarioModel para uma lista de objetos do tipo UsuarioDTO
    private List<UsuarioDTO> toCollectionModel(List<UsuarioModel> usuariosModel){
        return usuariosModel.stream()
                .map(usuarioModel -> toModel(usuarioModel))
                .collect(Collectors.toList());

    }


    // Converter um objeto do tipo UsuarioInput para UsuarioModel
    private UsuarioModel toDomainObject(UsuarioInput usuarioInput){

        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setId(usuarioInput.getId());
        usuarioModel.setNome(usuarioInput.getNome());
        usuarioModel.setLogin(usuarioInput.getLogin());
        usuarioModel.setSenha(usuarioInput.getSenha());



        for (int i=0; i<usuarioInput.getTelefones().size(); i++){
            TelefoneModel telefoneModel = new TelefoneModel();
            telefoneModel.setTipo(usuarioInput.getTelefones().get(i).getTipo());
            telefoneModel.setNumero(usuarioInput.getTelefones().get(i).getNumero());
            telefoneModel.setId(usuarioInput.getTelefones().get(i).getId());
            telefoneModel.setUsuario(usuarioModel);

            usuarioModel.getTelefones().add(telefoneModel);

        }


        return usuarioModel;

    }
}
