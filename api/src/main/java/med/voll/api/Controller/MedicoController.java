package med.voll.api.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.DadosListMedicoDTO;
import med.voll.api.domain.medico.DadosMedicoDTO;
import med.voll.api.domain.medico.RespostaPutDTO;
import med.voll.api.domain.medico.atualizarDadosMedicoDTO;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    //Criando funções CRUD
    //Create
    //ResponseEntity → Inclusão de retorno com resposta como boas práticas de API.
    //Queremos que as operações retornem o código 201: Created, para registrar cadastro criado
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosMedicoDTO dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);
        //Uri necessária para uso de front-end e mobile
        //Criamos um builder, usamos o método path
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new RespostaPutDTO(medico));
    }

    //READ
    @GetMapping
    public ResponseEntity<Page<DadosListMedicoDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
//        var token = ...;
//        if (token == null){
//
//    }

    var page = repository.findAllByAtivoTrue(paginacao).map(DadosListMedicoDTO::new);
            return ResponseEntity.ok(page);
}

//UPDATE
@PutMapping
@Transactional
public ResponseEntity atualizar(@RequestBody @Valid atualizarDadosMedicoDTO dados) {
    var medicos = repository.getReferenceById(dados.id());
    medicos.atualizarInformacoes(dados);
    return ResponseEntity.ok(new RespostaPutDTO(medicos));
}

//DELETE
@DeleteMapping("/{id}")
@Transactional
public ResponseEntity excluir(@PathVariable Long id) {
    var medicos = repository.getReferenceById(id);
    medicos.desativar(id);
    return ResponseEntity.noContent().build();
}

//Método de detalhamento de médico
@GetMapping("/{id}")
public ResponseEntity detalhar(@PathVariable Long id) {
    var medicos = repository.getReferenceById(id);
    return ResponseEntity.ok(new RespostaPutDTO(medicos));
}
}