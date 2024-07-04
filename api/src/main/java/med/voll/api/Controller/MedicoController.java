package med.voll.api.Controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosListMedicoDTO;
import med.voll.api.dto.DadosMedicoDTO;
import med.voll.api.dto.atualizarDadosMedicoDTO;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosMedicoDTO dados) {
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListMedicoDTO> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao)
                .map(DadosListMedicoDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid atualizarDadosMedicoDTO dados) {
        var medicos = repository.getReferenceById(dados.id());
        medicos.atualizarInformacoes(dados);
    }
}