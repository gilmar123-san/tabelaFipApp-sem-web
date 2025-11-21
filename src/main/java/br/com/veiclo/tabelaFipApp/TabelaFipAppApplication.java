package br.com.veiclo.tabelaFipApp;

import br.com.veiclo.tabelaFipApp.model.menu.MenuPrincipal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabelaFipAppApplication implements CommandLineRunner {

	private MenuPrincipal menu = new MenuPrincipal();

	public static void main(String[] args) {
		SpringApplication.run(TabelaFipAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menu.exibeMenu();
	}
}
