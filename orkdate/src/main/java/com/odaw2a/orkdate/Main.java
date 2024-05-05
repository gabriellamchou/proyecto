package com.odaw2a.orkdate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.odaw2a.orkdate.domain.Personaje;
import com.odaw2a.orkdate.domain.Rol;
import com.odaw2a.orkdate.domain.Usuario;
import com.odaw2a.orkdate.services.PersonajeService;
import com.odaw2a.orkdate.services.UsuarioService;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner initData(UsuarioService usuarioService, PersonajeService personajeService) {
		return args -> {
			
			Usuario user0 = new Usuario(null, "user0", "1234", Rol.USUARIO);
			usuarioService.añadir(user0);
			Usuario admin = new Usuario(null, "admin", "1234", Rol.ADMIN);
			usuarioService.añadir(admin);
			Usuario user1 = new Usuario(null, "user1", "1234", Rol.USUARIO);
			usuarioService.añadir(user1);

			Personaje user0Char1 = new Personaje(null, "user0Char1", "Pícaro", "De edad madura y sedentario, viste con ropa seria, pero le gustan los colores vivos, y la comida sencilla. Muy aficionado a las flores y le encantaban los anillos de humo. Considerado un hobbit de lo más excelente y audaz.", admin);
			personajeService.añadir(user0Char1, user0);
			Personaje adminChar1 = new Personaje(null, "adminChar1", "Mago", "Es un istar, uno de los espíritus maia enviados a la Tierra Media durante la Tercera Edad del Sol para ayudar a sus habitantes en la lucha contra el «señor oscuro» Sauron.", admin);
			personajeService.añadir(adminChar1, admin);
			Personaje adminChar2 = new Personaje(null, "adminChar2", "Ladrón", "Frodo pasó su infancia en Casa Brandi, en Los Gamos, viviendo con la familia de su madre. Los padres de Frodo murieron cuando él tenía doce años, en un accidente mientras navegaban en un bote.", admin);
			personajeService.añadir(adminChar2, admin);
			Personaje user1Char = new Personaje(null, "user1Char", "Bárbaro", "Hijo de un herrero de las tierras norteñas de Cimmeria, nació en un campo de batalla. A la edad de tan solo quince años tomó parte en el saqueo de Venarium, y poco después se unió a una banda de Aesir.", user1);
			personajeService.añadir(user1Char, user1);
		};
	}

}
