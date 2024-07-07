package med.voll.api.domain.dto;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.Medico;

public record RespostaPutDTO(Long id, String nome, String crm, String telefone, Endereco endereco) {

    public RespostaPutDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getTelefone(), medico.getEndereco());
    }
}
