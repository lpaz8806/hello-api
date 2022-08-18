package se.jensensthlm.filmstaden;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/v1/filmstaden/actors")
public class ActorsController {
    private ActorsRepository actorsRepository = new ActorsRepository();

    @GetMapping
    public ResponseEntity<List<ActorOutDto>> getAll() {
        try {
            var actors = mapActors(actorsRepository.getAll());
            return ResponseEntity.ok().body(actors);
        }
        catch (SQLException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ActorOutDto> getById(@PathVariable long id) {
        try {
            var actor = actorsRepository.getById(id);
            if(actor == null)
                return ResponseEntity.notFound().build();
            var actorDto = mapActor(actor);
            return ResponseEntity.ok().body(actorDto);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    private ActorOutDto mapActor(Actor actor) {
        return new ActorOutDto(
                actor.id(),
                "%s %s".formatted(actor.firstName(), actor.lastName()),
                actor.bornOn()
        );
    }
    private List<ActorOutDto> mapActors(List<Actor> actors) {
        return actors.stream()
                .map(this::mapActor)
                .toList();
    }
}
