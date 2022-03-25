package ito.dsos.microservicios.controller;

import ito.dsos.microservicios.model.RegistroModel;
import ito.dsos.microservicios.repository.RegistroRepository;
import ito.dsos.microservicios.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequestMapping(path = "/")
@RestController
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @Autowired
    private RegistroRepository registroRepository;

    @GetMapping("/")
    public List<RegistroModel> getAll(){
        return registroService.getAll();
    }

    //Método no utilizado: mala interpretación de las instrucciones
    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String, Object>> getOne(@PathVariable String id){
        Optional<RegistroModel> registro = registroService.getById(Integer.parseInt(id));
        //RESPUESTA CUSTOM
        HashMap<String,Object> response=new HashMap<>();
        if (registro.get()==null){
            response.put("ERROR","No hay datos para realizar el cálculo");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        double ICA = registro.get().getMedidaCintura()/registro.get().getMedidaAltura();
        response.put("ica",ICA);
        //DATOS ICA
        Double [][] arrValores = {{0.34,0.34},{0.42,0.41},{0.52,0.48},{0.57,0.53},{0.62,0.57},{0.63,0.58}};
        int sexo;
        String nivel="";

        for (int i=0;i<arrValores.length;i++){
            if (registro.get().getGenero() =='H')
                sexo = 0;
            else
                sexo = 1;
            if (ICA>=arrValores[i][sexo]){
                nivel="Delgadez leve";
            }if (ICA>=arrValores[i][sexo]){
                nivel="Peso normal";
            }if (ICA>=arrValores[i][sexo]){
                nivel="Sobrepeso";
            }if (ICA>=arrValores[i][sexo]){
                nivel="Sobrepeso elevado";
            }if (ICA>=arrValores[i][sexo]){
                nivel="Obesidad mórbida";
            } else {
                nivel="Delgadez severa";
            }
        }
        response.put("nivel",nivel);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<HashMap<String, Object>> nuevoRegistro(@ModelAttribute RegistroModel registro){
        //DATOS
        Double [][] arrValores = {{0.34,0.34},{0.42,0.41},{0.52,0.48},{0.57,0.53},{0.62,0.57},{0.63,0.58}};
        //VARIABLES
        HashMap<String,Object> response=new HashMap<>();
        boolean existe = false;
        boolean datosCompletos = true;
        //VERIFICA SI REGISTRO EXISTE, SI SÍ, TOMA VALORES DEL EXISTENTE
        if (registroRepository.existsById(registro.getNumControl())) {
            existe = true;
            int ID = registro.getNumControl();
            RegistroModel viejoRegistro = registroRepository.getById(ID);
            if (viejoRegistro.getGenero()==null){
                viejoRegistro.setGenero(registro.getGenero());
            }
            if (viejoRegistro.getMedidaAltura()==null){
                viejoRegistro.setMedidaAltura(registro.getMedidaAltura());
            }
            if (viejoRegistro.getMedidaCintura()==null){
                viejoRegistro.setMedidaCintura(registro.getMedidaCintura());
            }
            registro = viejoRegistro; //Utiliza los valores del viejo y nuevo registro
        }
        //VALIDACIÓN DEL REGISTRO NUEVO
        if(registro.getNumControl()==null){
         response.put("error","Se requiere de un número de control");
         return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
        //VERIFICAR QUE SE CUENTA CON LOS DATOS PARA CALCULAR EL ICA
        if(registro.getGenero() == null){
            datosCompletos=false;
        }else if (!(registro.getGenero()=='M' ||registro.getGenero()=='H')){
            datosCompletos=false;
            response.put("error","El género no ha sido guardado: debe ser H o M");
            registro.setGenero(null);
        }
        if(registro.getMedidaCintura()==null || registro.getMedidaAltura()==null){
            datosCompletos=false;
        }else{
            if (registro.getMedidaAltura()==0){
                datosCompletos=false;
                response.put("error","La medida de altura no ha sido guardada: no puede ser igual a 0");
                registro.setMedidaAltura(null);
            }
            if (registro.getMedidaCintura()==0){
                datosCompletos=false;
                response.put("error","La medida de cintura no ha sido guardada: no puede ser igual a 0");
                registro.setMedidaCintura(null);
            }
        }

        //SI NO HAY DATOS COMPLETOS:
        if (!datosCompletos){
            registroService.createRegistro(registro); //Guarda el registro
            response.put("ica","no se puede calcular");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        //CALCULAR ICA
        if (registro.getMedidaCintura()!=null && registro.getMedidaAltura()!=null){
            double ICA = Math.round((registro.getMedidaCintura()/registro.getMedidaAltura())*100.0)/100.0;
            response.put("ica",ICA);
            int sexo;
            String nivel="";
                if (registro.getGenero() =='H')
                    sexo = 0;
                else
                    sexo = 1;
                if (ICA <= arrValores[0][sexo]){
                    nivel="Delgadez severa";
                }else if (ICA>arrValores[0][sexo] && ICA<=arrValores[1][sexo]){
                    nivel="Delgadez leve";
                }else if (ICA>arrValores[1][sexo]&& ICA<=arrValores[2][sexo]){
                    nivel="Peso normal";
                }else if (ICA>arrValores[2][sexo]&& ICA<=arrValores[3][sexo]){
                    nivel="Sobrepeso";
                }else if (ICA>arrValores[3][sexo]&& ICA<=arrValores[4][sexo]){
                    nivel="Sobrepeso elevado";
                }else if (ICA>arrValores[4][sexo]) {
                    nivel = "Obesidad mórbida";
                }
            response.put("nivel",nivel);
        }
        registroService.createRegistro(registro);
        //SI EL REGISTRO EXISTÍA: HttpStatus = FOUND
        if (existe){
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }
        //SI EL REGISTRO NO EXISTÍA: HttpStatus = CREATED
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
