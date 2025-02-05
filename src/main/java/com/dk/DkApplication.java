package com.dk;

import com.dk.modelo.Role;
import com.dk.modelo.Usuario;
import com.dk.servicio.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DkApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DkApplication.class, args);
		System.out.println("                    ======================================================================");
		System.out.println("                    ========================= End of Charge ==============================");
		System.out.println("                    ======================================================================");

	}
	@Bean
	public CommandLineRunner initData(UserServiceImpl service) {
		return (args) -> {
			// agregar admin
			if(service.cantEmailsExist("drx4ss@gmail.com")<1){
				service.crear(new Usuario("dkadmin", "drx4ss@gmail.com", Role.ADMIN, passwordEncoder.encode("admin")));
			}
			if(service.cantEmailsExist("pety@email.com")<1){
				service.crear(new Usuario("pety", "pety@email.com", Role.USER, passwordEncoder.encode("pety")));
			}
			if(service.cantEmailsExist("agus@email.com")<1){
				service.crear(new Usuario("agus", "agus@email.com", Role.ADMIN, passwordEncoder.encode("agus")));
			}

		};
	}
}
