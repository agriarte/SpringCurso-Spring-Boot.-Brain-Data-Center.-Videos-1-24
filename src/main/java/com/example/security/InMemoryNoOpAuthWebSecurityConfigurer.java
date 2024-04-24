package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class InMemoryNoOpAuthWebSecurityConfigurer {

    // En este bean se definen los usuarios. Sin credenciales no se accede a la web
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}123")
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("{noop}456")
                .roles("ADMIN", "USER")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Configuración del formulario de inicio de sesión
                .formLogin(form -> form
                // Especifica la página de inicio de sesión
                .loginPage("/registro")
                // Permite que todos puedan acceder a la página de inicio de sesión
                .permitAll()
                )
                // Configuración del logout
                .logout((logout) -> logout
                // Especifica la URL a la que se redirigirá después de cerrar sesión
                .logoutSuccessUrl("/registro")
                )
                // Manejo de excepciones
                .exceptionHandling(handling -> handling
                // Configura la página de acceso denegado
                .accessDeniedPage("/errores/error403")
                );

        http.authorizeHttpRequests(
                request -> request
                        // Las rutas destinadas para todos
                        .requestMatchers("/").permitAll()
                        .requestMatchers(
                                // Las rutas destinadas solo para el rol "ADMIN"
                                "/salvar/**",
                                "/modificar/**",
                                "/borrar/**",
                                "/borrar_queryparameter/**",
                                "/anexar/**"
                        ).hasRole("ADMIN")
                        // Todas las demás solicitudes requieren autenticación
                        .anyRequest().authenticated()
        )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
