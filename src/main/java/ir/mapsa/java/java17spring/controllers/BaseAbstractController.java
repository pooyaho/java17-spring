package ir.mapsa.java.java17spring.controllers;

import ir.mapsa.java.java17spring.converters.BaseAdapter;
import ir.mapsa.java.java17spring.services.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseAbstractController<D,E> {
    @Autowired
    protected BaseAdapter<D,E> adapter;
    @Autowired
    protected AbstractService<E> service;


    @PostMapping
    @Transactional
    public void save(@RequestBody D dto) {
        service.save(adapter.convertDto(dto));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        service.removeById(id);
    }

    @GetMapping("/{id}")
    public D findById(@PathVariable Long id) {
        return adapter.convertEntity(service.findById(id).orElse(null));
    }

    @GetMapping("/")
    public List<D> findByAll() {
        return adapter.convertEntities( service.findAll());

    }

    @PostMapping("/search")
    public List<D> findByExample(@RequestBody D dto) {
        return adapter.convertEntities(service.findBySample(adapter.convertDto(dto)));
    }
}
