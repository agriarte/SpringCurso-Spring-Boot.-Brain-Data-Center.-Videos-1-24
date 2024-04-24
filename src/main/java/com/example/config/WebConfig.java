package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

/**
 * 
 * WebConfig: Esta es una clase de configuración de Spring que implementa WebMvcConfigurer, 
 * lo que indica que va a configurar características relacionadas con MVC (Modelo-Vista-Controlador) para una aplicación web.
 * 
 * Con esta clase y los archivos messages properties se crea la magia para internaciolizar la app. La selección de idioma se 
 * hace desde archivo plantilla.html. Funcionamiento:
 * 
 * 1- Inicialmente se define como idioma local el "es" en el método localeResolver().
 * 2- Cuando un usuario hace clic en el enlace de selección de idioma, ejemplo: <a th:href="@{/(lang=en)}">en</a>, lo que sucede 
 * es que el parámetro lang con el valor "en" se añade a la URL. Por ejemplo, si la URL actual es http://example.com, después de 
 * hacer clic en el enlace, se convierte en http://example.com/?lang=en.
 * 3-El interceptor LocaleChangeInterceptor detecta este cambio en la URL y actualiza el locale en consecuencia. En este caso, 
 * cambiaría el locale a inglés (en). 
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     *
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        Locale spanishLocale = new Locale.Builder()
                .setLanguage("es")
                .build();
        slr.setDefaultLocale(spanishLocale);
        return slr;
    }

    /**
     *
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    /**
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    /**
     * Este método se sobrescribe para configurar vistas (páginas) sin necesidad de un controlador. En este caso, se establece 
     * una vista llamada "inicio" para la ruta "/" (la raíz de la aplicación). 
     * @param ingreso 
     */
    @Override
    public void addViewControllers(ViewControllerRegistry ingreso) {
        ingreso.addViewController("/").setViewName("inicio");
        ingreso.addViewController("/registro");
        ingreso.addViewController("/errores/error403").setViewName("/errores/error403");
    }
    
    
    
}
