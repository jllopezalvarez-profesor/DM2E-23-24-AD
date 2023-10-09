package es.jllopezalvarez.sakila.api.controllers;

import es.jllopezalvarez.sakila.api.dto.UpdateActorFilmDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("actor")
public class ActorController {

    @PostMapping("updatefilms")
    public void updateActorFilmsA(@RequestBody UpdateActorFilmDto updateActorFilmInfo){
        // Todo: llamar a la clase de accesa a datos para hacer la operación en BD.
        System.out.println(updateActorFilmInfo);
    }

    @PostMapping("{id}/updatefilms")
    public void updateActorFilmsB(@PathVariable int id, @RequestBody List<Integer> filmIds){
        // Todo: llamar a la clase de accesa a datos para hacer la operación en BD.
        System.out.println("Actualizando películas del actor " + id);
        System.out.println(filmIds);
    }

}
