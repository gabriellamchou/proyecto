package com.odaw2a.orkdate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.odaw2a.orkdate.domain.Rol;
import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.services.UsuarioService;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner initData(UsuarioService usuarioService) {
		return args -> {
			
			Usuario admin = new Usuario(null, "admin", "1234", Rol.ADMIN);

			usuarioService.añadir(admin);
			
		};
	}

}
