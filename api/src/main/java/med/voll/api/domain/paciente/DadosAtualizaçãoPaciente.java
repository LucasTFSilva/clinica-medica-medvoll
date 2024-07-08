package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEnderecoDTO;

public record DadosAtualizaçãoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        DadosEnderecoDTO endereco
) {
}
