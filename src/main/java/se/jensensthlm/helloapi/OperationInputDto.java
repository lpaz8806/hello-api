package se.jensensthlm.helloapi;

// DTO stands for Data Transfer Object
// MUST NOT HAVE BEHAVIOUR
public record OperationInputDto(String op, Double x, Double y) { }
