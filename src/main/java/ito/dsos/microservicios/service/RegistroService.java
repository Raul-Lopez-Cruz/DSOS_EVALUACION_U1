package ito.dsos.microservicios.service;

import ito.dsos.microservicios.model.RegistroModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RegistroService {

    final Log LOG = LogFactory.getLog(RegistroService.class);

    public void createRegistro(RegistroModel nuevoRegistro);

    public Boolean existeNumControl(Integer id);

    public void save(RegistroModel nuevoRegistro);

    public void delete(Integer id);

    public void update(RegistroModel actualizaRegistro, Integer id);

    public Optional<RegistroModel> getById(Integer id);

    public List<RegistroModel> getAll();

}