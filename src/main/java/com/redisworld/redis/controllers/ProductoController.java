package com.redisworld.redis.controllers;

import com.redisworld.redis.dto.Producto;
import com.redisworld.redis.dto.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductoController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoController(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    @Cacheable(value = "producto", key = "#productoId")
    @RequestMapping(value = "/get/{productoId}", method = RequestMethod.GET)
    public Producto getProducto(@PathVariable String productoId) {
        LOG.info("Obteniendo Producto con Id {}.", productoId);
        int i = Integer.parseInt(productoId);
        return productoRepository.findById(i).orElse(null);
    }

    @CachePut(value = "producto", key = "#producto.id")
    @PutMapping("/add")
    public Producto addProducto(@RequestBody Producto producto) {
        productoRepository.save(producto);
        LOG.info("Producto agregado: {}", producto);
        return producto;
    }

    @CacheEvict(value = "producto", allEntries = true)
    @DeleteMapping("/delete/{productoId}")
    public void deleteByID(@PathVariable Integer productoId) {
        LOG.info("Eliminando producto con Id {}", productoId);
        Optional<Producto> pr = productoRepository.findById(productoId);
        if (!pr.isEmpty()) {
            Producto producto = pr.get();
            productoRepository.delete(producto);

        }
    }
}
