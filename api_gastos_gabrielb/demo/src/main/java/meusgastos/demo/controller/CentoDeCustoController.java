package meusgastos.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import meusgastos.demo.domain.dto.centrodecusto.CentroDeCustoRequestDTO;
import meusgastos.demo.domain.dto.centrodecusto.CentroDeCustoResponseDTO;
import meusgastos.demo.domain.service.CentroDeCustoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/centrodecustos")
public class CentoDeCustoController {
    @Autowired
    private CentroDeCustoService centroDeCustoService;

    @GetMapping
    public ResponseEntity<List<CentroDeCustoResponseDTO>> obterTodos(){
        return ResponseEntity.ok(centroDeCustoService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentroDeCustoResponseDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(centroDeCustoService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<CentroDeCustoResponseDTO> cadastrar(@RequestBody CentroDeCustoRequestDTO dto){
        CentroDeCustoResponseDTO centroDeCusto = centroDeCustoService.cadastrar(dto);
        return new ResponseEntity<>(centroDeCusto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentroDeCustoResponseDTO> atualizar(@PathVariable Long id, @RequestBody CentroDeCustoRequestDTO dto){
        CentroDeCustoResponseDTO responseDTO = centroDeCustoService.atualizar(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        centroDeCustoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
