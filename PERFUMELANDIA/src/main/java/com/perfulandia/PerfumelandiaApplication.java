package com.perfulandia;

import com.perfulandia.model.Producto;
import com.perfulandia.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class PerfumelandiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerfumelandiaApplication.class, args);
	}

	@Bean
	CommandLineRunner precargarProducto(ProductoRepository productoRepository) {
		return args -> {
			if (productoRepository.count() == 0) {
				Producto producto = new Producto();
				producto.setNombre("Perfume Aqua");
				producto.setPrecio(new BigDecimal("19990.00"));
				productoRepository.save(producto);
			}
		};
	}
}
