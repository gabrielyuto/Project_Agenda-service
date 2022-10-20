package pdev.com.agenda.api.mapper;

import pdev.com.agenda.api.request.PacienteRequest;
import pdev.com.agenda.api.response.PacienteResponse;
import pdev.com.agenda.domain.entity.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteMapper {
    public static Paciente toPaciente(PacienteRequest request) {
        Paciente paciente = new Paciente();
        paciente.setNome(request.getNome());
        paciente.setSobrenome(request.getSobrenome());
        paciente.setEmail(request.getEmail());
        paciente.setCpf(request.getCpf());

        return paciente;
    }

    public static PacienteResponse toPacienteResponse(Paciente paciente) {
        PacienteResponse response = new PacienteResponse();
        response.setId(paciente.getId());
        response.setSobrenome(paciente.getSobrenome());
        response.setNome(paciente.getNome());
        response.setEmail(paciente.getEmail());
        response.setCpf(paciente.getCpf());

        return response;
    }

    public static List<PacienteResponse> toPacienteResponseList(List<Paciente> pacientes){
        List<PacienteResponse> responses = new ArrayList<>();
        for (Paciente paciente : pacientes){
            responses.add(toPacienteResponse(paciente));
        }
        return responses;
    }
}
