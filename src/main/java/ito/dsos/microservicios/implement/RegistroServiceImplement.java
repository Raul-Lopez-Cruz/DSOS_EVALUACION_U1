package ito.dsos.microservicios.implement;

import ito.dsos.microservicios.model.RegistroModel;
import ito.dsos.microservicios.repository.RegistroRepository;

import ito.dsos.microservicios.service.RegistroService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroServiceImplement implements RegistroService {

    private final Log LOG= LogFactory.getLog(RegistroService.class);

    @Autowired
    private RegistroRepository registroRepository;

    @Override
    public void createRegistro(RegistroModel registro) {
        registroRepository.save(registro);
    }

    public RegistroServiceImplement(RegistroRepository registroRepository){
        this.registroRepository = registroRepository;
    }

    public void save(RegistroModel nuevaRegistro){
        registroRepository.save(nuevaRegistro);
    }

    public void delete(Integer id){
        registroRepository.deleteById(id);
    }

    @Override
    public void update(RegistroModel compraUpdate, Integer id){
        RegistroModel registroModel = registroRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("El registro no existe"));
        registroRepository.save(registroModel);
    }

    public Optional<RegistroModel> getById(Integer id){
        return registroRepository.findById(id);
    }

    public List<RegistroModel> getAll(){
        return registroRepository.findAll();
    }
}
