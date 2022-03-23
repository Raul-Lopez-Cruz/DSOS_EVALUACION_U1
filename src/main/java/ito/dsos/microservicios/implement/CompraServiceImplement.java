package ito.dsos.microservicios.implement;

import ito.dsos.microservicios.model.CompraModel;
import ito.dsos.microservicios.repository.CompraRepository;
import ito.dsos.microservicios.service.CompraService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraServiceImplement implements CompraService{

    private final Log LOG= LogFactory.getLog(CompraService.class);

    @Autowired
    private CompraRepository compraRepository;

    @Override
    public void createCompra(CompraModel compra) {
        compraRepository.save(compra);
    }

    public CompraServiceImplement(CompraRepository compraRepository){
        this.compraRepository = compraRepository;
    }

    public void save(CompraModel nuevaCompra){

        compraRepository.save(nuevaCompra);
    }

    public void delete(Integer id){
        compraRepository.deleteById(id);
    }

    @Override
    public void update(CompraModel compraUpdate, Integer id){
        CompraModel compraModel = compraRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("La compra no existe"));
        compraModel.setFechaCompra(compraUpdate.getFechaCompra());
        compraModel.setCostoTotal(compraUpdate.getCostoTotal());
        compraRepository.save(compraModel);
    }

    public Optional<CompraModel> getById(Integer id){
        return compraRepository.findById(id);
    }

    public List<CompraModel> getAll(){
        return compraRepository.findAll();
    }
}
