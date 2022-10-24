package br.com.futurodev.primeiraapi.controllers;

import br.com.futurodev.primeiraapi.model.ProdutoModel;
import br.com.futurodev.primeiraapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarvalor/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    @GetMapping(value = "/mostrarnome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public String mostrarnome(@PathVariable String nome) {
        return "olá " + nome;
    }

    @RequestMapping(value = "/produto/{descricao}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String salvar(@PathVariable String descricao) {
        ProdutoModel produto = new ProdutoModel();
        produto.setDescricao(descricao);
        produtoRepository.save(produto); // grava o produto no banco de dados

        return "Produto " + descricao + " registrado com sucesso!";
    }

    @GetMapping(value = "/produtos")
    @ResponseBody // retorna os dados no corpo da resposta
    public ResponseEntity<List<ProdutoModel>> listarProdutos() {
        List<ProdutoModel> produtos = produtoRepository.findAll(); // consulta no banco de dados todos os produtos
        return new ResponseEntity<List<ProdutoModel>>(produtos, HttpStatus.OK); // retorna a lista em json
    }

    @PostMapping(value = "/produto/salvar") // mapeia a url
    @ResponseBody // descreve a resposta informando que o retorno será no corpo da requisição
    public ResponseEntity<ProdutoModel> salvar(@RequestBody ProdutoModel produto) { //aqui recebe os dados para salvar
        ProdutoModel prod = produtoRepository.save(produto);
        return new ResponseEntity<ProdutoModel>(prod, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/produto/delete")
    @ResponseBody // descricao da resposta
    public ResponseEntity<String> delete(@RequestParam Long idProduto){ // recebe da requisicao o parametro
        produtoRepository.deleteById(idProduto);

        return new ResponseEntity<String>("Produto deletado com sucesso", HttpStatus.OK);
    }

}