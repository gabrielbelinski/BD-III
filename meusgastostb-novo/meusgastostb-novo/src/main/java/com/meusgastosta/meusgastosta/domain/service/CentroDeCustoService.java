package com.meusgastosta.meusgastosta.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.meusgastosta.meusgastosta.domain.dto.centrodecusto.CentroDeCustoRequestDTO;
import com.meusgastosta.meusgastosta.domain.dto.centrodecusto.CentroDeCustoResponseDTO;
import com.meusgastosta.meusgastosta.domain.exception.ResourceNotFoundException;
import com.meusgastosta.meusgastosta.domain.model.CentroDeCusto;
import com.meusgastosta.meusgastosta.domain.model.Usuario;
import com.meusgastosta.meusgastosta.domain.repository.CentroDeCustoRepository;

@Service
public class CentroDeCustoService implements ICRUDService
<CentroDeCustoRequestDTO, CentroDeCustoResponseDTO> {
@Autowired
private CentroDeCustoRepository centroDeCustoRepository;
@Autowired
private ModelMapper mapper;

    @Override
    public List<CentroDeCustoResponseDTO> obterTodos() {
       Usuario usuario = (Usuario) SecurityContextHolder
       .getContext().getAuthentication().getPrincipal();
       List<CentroDeCusto> lista = centroDeCustoRepository
       .findByUsuario(usuario);
       return lista.stream().map(centroDeCusto -> mapper
       .map(centroDeCusto, CentroDeCustoResponseDTO.class))
       .collect(Collectors.toList());
    }

    @Override
    public CentroDeCustoResponseDTO obterPorId(Long id) {
       Optional<CentroDeCusto> optCentroDeCusto = centroDeCustoRepository
       .findById(id);
       if(optCentroDeCusto.isEmpty()){
        throw new ResourceNotFoundException("Não foi possível encontrar o centro de custo com o id: " + id);
       }
       Usuario usuario = (Usuario) SecurityContextHolder.getContext()
       .getAuthentication().getPrincipal();
       if(optCentroDeCusto.get().getUsuario().getId() != usuario.getId()){
         throw new ResourceNotFoundException("O Centro de Custo de id: " +
         id + "não pertence a esse usuário");
       }
       return mapper.map(optCentroDeCusto.get(), 
       CentroDeCustoResponseDTO.class);
    }

    @Override
    public CentroDeCustoResponseDTO cadastrar(CentroDeCustoRequestDTO dto) {
       CentroDeCusto centroDeCusto = mapper.map(dto, 
       CentroDeCusto.class);
       Usuario usuario = (Usuario) SecurityContextHolder
       .getContext().getAuthentication().getPrincipal();
       centroDeCusto.setUsuario(usuario);
       centroDeCusto.setId(null);
       centroDeCusto = centroDeCustoRepository.save(centroDeCusto);
       return mapper.map(centroDeCusto, 
       CentroDeCustoResponseDTO.class);
    }

    @Override
    public CentroDeCustoResponseDTO atualizar(Long id, CentroDeCustoRequestDTO dto) {
        obterPorId(id);
        CentroDeCusto centroDeCusto = mapper.map(dto, 
        CentroDeCusto.class);
        Usuario usuario = (Usuario) SecurityContextHolder
        .getContext().getAuthentication().getPrincipal();
        centroDeCusto.setUsuario(usuario);
        centroDeCusto.setId(id);
        centroDeCusto = centroDeCustoRepository.save(centroDeCusto);
        return mapper.map(centroDeCusto, 
        CentroDeCustoResponseDTO.class);
    }

    @Override
    public void deletar(Long id) {
       obterPorId(id);
       centroDeCustoRepository.deleteById(id);
    }
    
}
