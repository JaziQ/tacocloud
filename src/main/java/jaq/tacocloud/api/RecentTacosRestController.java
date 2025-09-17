package jaq.tacocloud.api;

import jaq.tacocloud.Taco;
import jaq.tacocloud.data.TacoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
public class RecentTacosRestController {
    private final TacoRepo tacoRepo;

    @Autowired
    public RecentTacosRestController(TacoRepo tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
    @ResponseBody
    public CollectionModel<TacoEntityModel> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepo.findAll(page).getContent();

        CollectionModel<TacoEntityModel> recentResources =
                new TacoEntityModelAssembler().toCollectionModel(tacos);

        recentResources.add(
                WebMvcLinkBuilder.linkTo(
                                methodOn(DesignTacoRestController.class).recentTacos())
                        .withRel("recents")
        );
        return recentResources;
    }
}