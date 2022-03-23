package ito.dsos.microservicios.service;

import ito.dsos.microservicios.model.CompraModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CompraService {

    final Log LOG= LogFactory.getLog(CompraService.class);

    public void createCompra(CompraModel compra);

    public void save(CompraModel nuevaCompra);

    public void delete(Integer id);

    public void update(CompraModel compraUpdate, Integer id);

    public Optional<CompraModel> getById(Integer id);

    public List<CompraModel> getAll();

}
