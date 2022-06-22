package com.mi.Gestion_Stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import com.mi.Gestion_Stock.repositories.ProviderRepository;

@SpringBootApplication
public class GestionStockApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(GestionStockApplication.class, args);
		ProviderRepository providerRepository=ctx.getBean(ProviderRepository.class);
	}

}
