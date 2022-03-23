package ito.dsos.microservicios.controller;

import ito.dsos.microservicios.model.CompraModel;
import ito.dsos.microservicios.service.CompraService;
import ito.dsos.microservicios.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

@RequestMapping(path = "/api/compras")
@RestController
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping("/")
    public String index(){
        return "<h1>Server is running...</h1>\n" +
                "<h3>Seems like you're lost, check the microservice documentation here: <a href=\"https://documenter.getpostman.com/view/19268315/UVsHSnQ3\">Postman documentation</a></h3>";
    }

    @GetMapping("/registros")
    public List<CompraModel> getAll(){
        return compraService.getAll();
    }

    @GetMapping("/registros/{id}")
    public CustomResponse getOne(@PathVariable String id){
        return new CustomResponse(200, compraService.getById(Integer.parseInt(id)), "OK");
    }

    @PostMapping("/comprar")
    public CustomResponse nuevaCompra(@ModelAttribute CompraModel compra){
        if(compra == null)
            return new CustomResponse(204,null,"Sin contenido");
        compra.setFechaCompra(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        compraService.createCompra(compra);
        return new CustomResponse(201, compra, "Creado");
    }

    @Transactional
    @PutMapping("/registros/{id}/editar")
    public CustomResponse putPayment(@PathVariable String id){
        CompraModel compra = compraService.getById(Integer.parseInt(id))
                .orElseThrow(() -> new NoSuchElementException("No existe registro con el ID: "+id));
        compraService.save(compra);
        return new CustomResponse(200, compra, "Ok");
    }

    @DeleteMapping("/borrar/{id}")
    public CustomResponse deletePayment(@PathVariable String id){
        CompraModel compra = compraService.getById(Integer.parseInt(id))
                .orElseThrow(() -> {
                    return new NoSuchElementException("No existe registro con el ID: "+id);
                });
        compraService.delete(Integer.parseInt(id));
        return new CustomResponse(200, null, "OK");
    }
}
