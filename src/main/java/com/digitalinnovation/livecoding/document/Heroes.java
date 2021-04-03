package com.digitalinnovation.livecoding.document;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@DynamoDBTable(tableName ="Heroes_Api_Table")

public class Heroes {
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUniverse() {
    return universe;
  }

  public void setUniverse(String universe) {
    this.universe = universe;
  }

  public int getFilms() {
    return films;
  }

  public void setFilms(int films) {
    this.films = films;
  }

  @Schema(description = "ID do herói.",
          example = "1", required = true)
  @Id
  @DynamoDBHashKey (attributeName = "id")
  private String id;

  @Schema(description = "Nome do herói.",
          example = "Shazam", required = true)
  @DynamoDBAttribute (attributeName = "name")
  private String name;

  @Schema(description = "Universo no qual o herói está inserido.",
          example = "DC", required = true)
  @DynamoDBAttribute (attributeName = "universe")
  private String universe;

  @Schema(description = "Quantidade de filmes solo que o herói possui.",
          example = "1", required = true)
  @DynamoDBAttribute (attributeName = "films")
  private int films;


  public Heroes(String id, String name, String universe, int films) {
    this.id = id;
    this.name = name;
    this.universe = universe;
    this.films = films;
  }

}

