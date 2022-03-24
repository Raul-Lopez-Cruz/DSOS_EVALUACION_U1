package ito.dsos.microservicios.controller;

import ito.dsos.microservicios.model.RegistroModel;
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

    @GetMapping("/")
    public List<RegistroModel> getAll(){
        return registroService.getAll();
    }

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
        //RESPUESTA CUSTOM
        HashMap<String,Object> response=new HashMap<>();

        if(registro == null){
         response.put("ERROR","POST NULO");
         return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } else if(registro.getGenero() == null){
            response.put("ERROR","El género debe ser 'M' o 'H' únicamente");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if(registro.getMedidaCintura()==null || registro.getMedidaAltura()==null || registro.getMedidaAltura()==0 || registro.getMedidaCintura()==0){
            response.put("ERROR","Medidas vacías o iguales a cero");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        //DATOS ICA
        Double [][] arrValores = {{0.34,0.34},{0.42,0.41},{0.52,0.48},{0.57,0.53},{0.62,0.57},{0.63,0.58}};
        //CALCULAR ICA
        if (registro.getMedidaCintura()!=null && registro.getMedidaAltura()!=null){
            double ICA = registro.getMedidaCintura()/registro.getMedidaAltura();
            response.put("ica",ICA);
            int sexo;
            String nivel="";

                if (registro.getGenero().charValue()=='H')
                    sexo = 0;
                else
                    sexo = 1;

                if (ICA < arrValores[0][sexo]){
                    nivel="Delgadez severa";
                }else if (ICA>=arrValores[1][sexo] && ICA<arrValores[2][sexo]){
                    nivel="Delgadez leve";
                }else if (ICA>=arrValores[2][sexo]&& ICA<arrValores[3][sexo]){
                    nivel="Peso normal";
                }else if (ICA>=arrValores[3][sexo]&& ICA<arrValores[4][sexo]){
                    nivel="Sobrepeso";
                }else if (ICA>=arrValores[4][sexo]&& ICA<arrValores[5][sexo]){
                    nivel="Sobrepeso elevado";
                }else if (ICA>=arrValores[5][sexo]) {
                    nivel = "Obesidad mórbida";
                }

            response.put("nivel",nivel);
        }
        registroService.createRegistro(registro);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
