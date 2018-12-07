package com.groupsoftware.pontointeligente.config;

import com.groupsoftware.pontointeligente.security.utils.JwtTokenUtil;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Essa é uma implementação que faz uma configuração automatica de acesso sem necessidade de gerarmos o token.
 * Assim podemos verificar todos os endpoints através da url http://localhost:8080/swagger-ui.html
 *
 * @author Luiz Alberto
 */
@Configuration
@Profile("dev")
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.groupsoftware.pontointeligente.controllers"))
                .paths(PathSelectors.any()).build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Ponto Inteligente API")
                .description("Documentação da API de acesso aos endpoints do Ponto Inteligente.").version("1.0")
                .build();
    }

    @Bean
    public SecurityConfiguration security() {
        String token;
        try {
            //Usuario cadastrado no flyway(V2__admin_padrao.sql)
            UserDetails userDetails = this.userDetailsService.loadUserByUsername("admin@luiz.com");
            token = this.jwtTokenUtil.obterToken(userDetails);
        } catch (Exception e) {
            token = "";
        }

        //Retorna o token com a flag Bearer
        return new SecurityConfiguration(null, null, null, null, "Bearer " + token, ApiKeyVehicle.HEADER,
                "Authorization", ",");
    }
}
