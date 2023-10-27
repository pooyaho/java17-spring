package ir.mapsa.java.java17spring.controllers;

import ir.mapsa.java.java17spring.converters.BaseAdapter;
import ir.mapsa.java.java17spring.services.AbstractMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseAbstractMongoController<D, E, S extends AbstractMongoService<E, ?>> {
    @Autowired
    protected BaseAdapter<D, E> adapter;
    @Autowired
    protected S service;


    @PostMapping
    @Transactional
    @CacheEvict(cacheNames = "CONTROLLER_CACHE",key = "#dto.id",condition = "#dto.id!=null")
    @CachePut(cacheNames = "CONTROLLER_CACHE",key = "#result.id",condition = "#dto.id==null")
    public D save(@Validated({NotNullGroup.class, GeneralValidationGroup.class}) @RequestBody D dto) {
        return adapter.convertEntity(service.save(adapter.convertDto(dto)));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = "CONTROLLER_CACHE",key = "#id")
    public void remove(@PathVariable String id) {
        service.removeById(id);
    }

    @GetMapping("/{id}")
    @Cacheable(cacheNames = "CONTROLLER_CACHE", key = "#id")
    public D findById(@PathVariable String id) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return adapter.convertEntity(service.findById(id).orElse(null));
    }

    @GetMapping("/")
    public List<D> findByAll() {
        return adapter.convertEntities(service.findAll());

    }

    @PostMapping("/search")
    public List<D> findByExample(@Validated(GeneralValidationGroup.class) @RequestBody D dto) {
        return adapter.convertEntities(service.findBySample(adapter.convertDto(dto)));
    }
}
