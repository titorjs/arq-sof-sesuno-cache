package com.redisworld.redis;

import com.redisworld.redis.dto.Producto;
import com.redisworld.redis.dto.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisApplication implements CommandLineRunner {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	private final ProductoRepository productoRepository;


	@Autowired
	public RedisApplication(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Populating embedded database here
		LOG.info("Saving Productos. Current book count is {}.", productoRepository.count());
		Producto p1 = new Producto(1, "Nombre 1");
		Producto p2 = new Producto(2, "Nombre 2");
		Producto p3 = new Producto(3, "Nombre 3");
		Producto p4 = new Producto(4, "Nombre 4");

		productoRepository.save(p1);
		productoRepository.save(p2);
		productoRepository.save(p3);
		productoRepository.save(p4);
		LOG.info("Done saving Productos. Data: {}.", productoRepository.findAll());

	}
}
