package com.digitalinnovation.livecoding.controller;

import com.digitalinnovation.livecoding.document.Heroes;
import com.digitalinnovation.livecoding.repository.HeroesRepository;
import com.digitalinnovation.livecoding.service.HeroesService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.digitalinnovation.livecoding.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@RestController
@Slf4j
public class HeroesController {
  HeroesService heroesService;

  HeroesRepository heroesRepository;

  private static final org.slf4j.Logger log =
    org.slf4j.LoggerFactory.getLogger(HeroesController.class);

  public HeroesController(HeroesService heroesService, HeroesRepository heroesRepository) {
    this.heroesService = heroesService;
    this.heroesRepository = heroesRepository;
  }

  @Operation(
          summary = "Busca todos os heróis cadastrados.",
          description = "Retorna um array de Heróis.",
          tags = "Heroes"
  )
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Requisição bem sucedida.", content =
          @Content(mediaType = "application/json", array =
          @ArraySchema(schema = @Schema(implementation = Heroes.class)))),
          @ApiResponse(responseCode = "400", description = "Requisição mal sucedida.",
                  content = @Content),
          @ApiResponse(responseCode = "404", description = "Registro não encontrado.",
                  content = @Content),
          @ApiResponse(responseCode = "500", description = "Requisição mal sucedida.",
                  content = @Content)})

  @GetMapping(HEROES_ENDPOINT_LOCAL)
  @ResponseStatus(HttpStatus.OK)
  public Flux<Heroes> getAllItems() {
    log.info("requesting the list off all heroes");
    return heroesService.findAll();

  }

  @Operation(
          summary = "Busca herói pelo ID.",
          description = "Retorna um único Herói que possui o ID informado.",
          tags = "Heroes"
  )
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Requisição bem sucedida.",
                  content = { @Content(mediaType = "application/json",
                          schema = @Schema(implementation = Heroes.class)) }),
          @ApiResponse(responseCode = "400", description = "Requisição mal sucedida.",
                  content = @Content),
          @ApiResponse(responseCode = "404", description = "Registro não encontrado.",
                  content = @Content),
          @ApiResponse(responseCode = "500", description = "Requisição mal sucedida.",
                  content = @Content)})

  @GetMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<ResponseEntity<Heroes>> findByIdHero(@PathVariable String id) {
    log.info("Requesting the hero with id {}", id);
    return heroesService.findByIdHero(id)
      .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
      .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @Operation(
          summary = "Cria heróis.",
          description = "Cadastra novos Heróis.",
          tags = "Heroes"
  )
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Requisição bem sucedida.",
                  content = { @Content(mediaType = "application/json",
                          schema = @Schema(implementation = Heroes.class)) }),
          @ApiResponse(responseCode = "400", description = "Requisição mal sucedida.",
                  content = @Content),
          @ApiResponse(responseCode = "404", description = "Registro não encontrado.",
                  content = @Content),
          @ApiResponse(responseCode = "500", description = "Requisição mal sucedida.",
                  content = @Content)})
  @PostMapping(HEROES_ENDPOINT_LOCAL)
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Heroes> createHero(@RequestBody Heroes heroes) {
    log.info("A new Hero was Created");
    return heroesService.save(heroes);

  }

  @Operation(
          summary = "Apaga heróis cadastrados.",
          description = "Apaga o herói que tiver o ID informado.",
          tags = "Heroes"
  )
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Requisição bem sucedida.",
                  content = @Content),
          @ApiResponse(responseCode = "400", description = "Requisição mal sucedida.",
                  content = @Content),
          @ApiResponse(responseCode = "404", description = "Registro não encontrado.",
                  content = @Content),
          @ApiResponse(responseCode = "500", description = "Requisição mal sucedida.",
                  content = @Content)})
  @DeleteMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
  public Mono<HttpStatus> deletebyIdHero(@PathVariable String id) {

    heroesService.deletebyIDHero(id);
    log.info("Deleting the hero with id {}", id);
    return Mono.just(HttpStatus.OK);
  }
}
