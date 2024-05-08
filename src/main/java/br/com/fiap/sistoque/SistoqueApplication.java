package br.com.fiap.sistoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@Controller
@EnableCaching
@OpenAPIDefinition(
	info = @Info(
		title = "API do Sistoque",
		description = "App de sistema de gerenciamento de um sistema de estoque de material escolar",
		contact = @Contact(name = "Igor Silva", email = "igor@email.com"),
		version = "1.0.0"
	)
)
public class SistoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistoqueApplication.class, args);
	}
	
	@RequestMapping
	@ResponseBody
	public String home() {
		return "SisToque";
	}
}
