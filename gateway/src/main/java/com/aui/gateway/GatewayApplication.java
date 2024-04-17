package com.aui.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

@SpringBootApplication()
public class GatewayApplication {


	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(
			RouteLocatorBuilder builder,
			@Value("${aui.patient.url}") String patientUrl,
			@Value("${aui.doctor.url}") String doctorUrl,
			@Value("${aui.gateway.host}") String host
	) {
		return builder
				.routes()
				.route("patients", route -> route
						.host(host)
						.and()
						.path("/patients", "/patients/**")
						.uri(patientUrl)
				)
				.route("doctors", route -> route
						.host(host)
						.and()
						.path("/doctors", "/doctors/**")
						.uri(doctorUrl)

				)
				.build();
	}
	@Bean
	public CorsWebFilter corsWebFilter() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.addAllowedHeader("*");
		corsConfig.addAllowedMethod("*");
		corsConfig.addAllowedOrigin("http://localhost:4200");

		UrlBasedCorsConfigurationSource source =
				new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		return new CorsWebFilter(source);

	}
}
