package br.com.catolica.crud_spring.controller;

import br.com.catolica.crud_spring.model.Contato;
import br.com.catolica.crud_spring.model.dto.request.EntregadorRequestDTO;
import br.com.catolica.crud_spring.model.Entregador;
import br.com.catolica.crud_spring.repository.ContatoJdbcDAO;
import br.com.catolica.crud_spring.repository.EntregadorJdbcDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/entregador")
public class EntregadorController {

    @Autowired
    private EntregadorJdbcDAO entregadorDao;

    @Autowired
    private ContatoJdbcDAO contatoDao;

    @PostMapping
    public ResponseEntity<Entregador> criarEntregador(@RequestBody EntregadorRequestDTO request) {
        Contato contatoSalvo = contatoDao.save(request.getContato());
        Entregador entregador = new Entregador();
        entregador.setNome(request.getNome());
        entregador.setContatoId(contatoSalvo.getId());

        Entregador entregadorSalvo = entregadorDao.save(entregador);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entregadorSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(entregadorSalvo);
    }

    @GetMapping
    public List<Entregador> listarTodos() {
        return entregadorDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entregador> buscarPorId(@PathVariable int id) {
        Entregador entregador = entregadorDao.findById(id);
        if (entregador == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entregador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarEntregador(@PathVariable int id, @RequestBody Entregador entregadorAtualizado) {
        entregadorDao.update(id, entregadorAtualizado);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEntregador(@PathVariable int id) {
        Entregador entregador = entregadorDao.findById(id);
        if (entregador == null) {
            return ResponseEntity.notFound().build();
        }
        entregadorDao.delete(id);
        contatoDao.delete(entregador.getContatoId());
        return ResponseEntity.noContent().build();
    }
}