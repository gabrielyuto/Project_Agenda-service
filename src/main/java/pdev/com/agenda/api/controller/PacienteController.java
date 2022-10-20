package pdev.com.agenda.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdev.com.agenda.api.mapper.PacienteMapper;
import pdev.com.agenda.api.request.PacienteRequest;
import pdev.com.agenda.api.response.PacienteResponse;
import pdev.com.agenda.domain.entity.Paciente;
import pdev.com.agenda.domain.service.PacienteService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/paciente")
public class PacienteController {

  private final PacienteService service;

  @PostMapping
  public ResponseEntity<PacienteResponse> salvar(@RequestBody PacienteRequest request) {
    Paciente paciente = PacienteMapper.toPaciente(request);

    Paciente pacienteSalvo = service.salvar(paciente);
    PacienteResponse pacienteResponse = PacienteMapper.toPacienteResponse(pacienteSalvo);
    return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponse);
  }

  @GetMapping
  public ResponseEntity<List<PacienteResponse>> listarTodos() {
    List<Paciente> pacientes = service.listarTodos();
    List<PacienteResponse> pacienteResponses = PacienteMapper.toPacienteResponseList(pacientes);
    return ResponseEntity.status(HttpStatus.OK).body(pacienteResponses);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable Long id) {
    Optional<Paciente> optPaciente = service.buscarPorId(id);

    if(optPaciente.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.status(HttpStatus.OK).body(PacienteMapper.toPacienteResponse(optPaciente.get()));
  }

  @PutMapping
  public ResponseEntity<PacienteResponse> alterar(@RequestBody PacienteRequest request) {
    Paciente paciente = PacienteMapper.toPaciente(request);
    Paciente pacienteSalvo = service.salvar(paciente);
    PacienteResponse pacienteResponse = PacienteMapper.toPacienteResponse(pacienteSalvo);
    return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    service.deletar(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
