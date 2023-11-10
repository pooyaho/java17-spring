package ir.mapsa.java.java17spring.controllers;

import ir.mapsa.java.java17spring.converters.BaseAdapter;
import ir.mapsa.java.java17spring.models.validations.GeneralValidationGroup;
import ir.mapsa.java.java17spring.models.validations.NotNullGroup;
import ir.mapsa.java.java17spring.services.AbstractMongoService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAbstractMongoController.class);



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
        LOGGER.trace("find by id called with id :"+id);


        return adapter.convertEntity(service.findById(id).orElse(null));
    }

    @GetMapping("/")
    public List<D> findByAll(HttpServletRequest request) {
        if (1 == 1) {
            throw new RuntimeException();
        }
        return adapter.convertEntities(service.findAll());
    }

    @PostMapping("/search")
    public List<D> findByExample(@Validated(GeneralValidationGroup.class) @RequestBody D dto) {
        return adapter.convertEntities(service.findBySample(adapter.convertDto(dto)));
    }
}
