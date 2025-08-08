package dto;

public record ProductUpdateDto(
        String name,
        Double price,
        Integer quantity
) {}
