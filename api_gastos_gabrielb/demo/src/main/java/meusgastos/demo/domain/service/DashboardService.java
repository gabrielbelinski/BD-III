package meusgastos.demo.domain.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import meusgastos.demo.domain.Enum.ETipoTitulo;
import meusgastos.demo.domain.dto.dashboard.DashboardResponseDTO;
import meusgastos.demo.domain.dto.titulos.TituloResponseDTO;

@Service
public class DashboardService {
    
    @Autowired
    private TituloService tituloService;

    public DashboardResponseDTO obterFluxoDeCaixa(String periodoInicial, String periodoFinal){
        List<TituloResponseDTO> titulos = tituloService.obterPorDataDeVencimento(periodoInicial, periodoFinal);
        Double totalPagar = 0.0, totalReceber = 0.0;
        List<TituloResponseDTO> titulosPagar = new ArrayList<>();
        List<TituloResponseDTO> titulosReceber = new ArrayList<>();
        Double saldo = 0.0;
        for(TituloResponseDTO titulo : titulos){
            if(titulo.getTipo() == ETipoTitulo.APAGAR){
                totalPagar += titulo.getValor();
                titulosPagar.add(titulo);
            }
            else{
                totalReceber += titulo.getValor();
                titulosReceber.add(titulo);
            }
        }
        saldo = totalReceber - totalPagar;
        return new DashboardResponseDTO(totalPagar, totalReceber, saldo, titulosPagar, titulosReceber);
    }

}
