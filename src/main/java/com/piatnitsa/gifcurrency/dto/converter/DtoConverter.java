package com.piatnitsa.gifcurrency.dto.converter;

public interface DtoConverter<D, E> {

    E toEntity(D dto);
}
