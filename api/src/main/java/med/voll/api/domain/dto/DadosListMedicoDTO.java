package med.voll.api.domain.dto;

import med.voll.api.domain.especialidade.Especialidade;
import med.voll.api.domain.medico.Medico;

public record DadosListMedicoDTO(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListMedicoDTO(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
