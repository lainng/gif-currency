package com.piatnitsa.gifcurrency.dto.converter;

/**
 * This interface provides tools converting {@link D} DTOs to {@link E} entities.
 * @param <D> DTO type.
 * @param <E> entity type.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public interface DtoConverter<D, E> {

    /**
     * Converts the {@link D} DTO to the {@link E} entity.
     * @param dto {@link D} DTO.
     * @return converted {@link E} entity.
     */
    E toEntity(D dto);
}
