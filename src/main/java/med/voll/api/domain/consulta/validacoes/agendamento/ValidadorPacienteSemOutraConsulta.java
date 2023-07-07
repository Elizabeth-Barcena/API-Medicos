package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsulta implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private ConsultaRepository repository;
    public void validar(DadosAgendamentoConsulta dados){
        var primeiroHorario =dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        if (pacientePossuiOutraConultaNoDia){
            throw  new ValidationException("Paciente já possui uma consulta agendada nesse dia");
        }
    }


}