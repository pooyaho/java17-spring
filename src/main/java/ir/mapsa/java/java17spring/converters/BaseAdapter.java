package ir.mapsa.java.java17spring.converters;

import java.util.List;


public interface BaseAdapter<D,E> {
    E convertDto(D dto);
    List<E> convertDtos(List<D> dto);
    D convertEntity(E dto);
    List<D> convertEntities(List<E> dto);
}
