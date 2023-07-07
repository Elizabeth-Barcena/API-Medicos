package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class ValidadosHorarioAntecedenciaAgendamento implements ValidadorAgendamentoDeConsulta {
    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if( diferencaEmMinutos < 30){
            throw  new ValidationException("Consulta deve ser agendada com antecedência minima de 30 minutos");
        }
    }

}
