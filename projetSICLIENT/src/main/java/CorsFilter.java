import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4000"); // Autoriser l'accès à partir de toutes les origines
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // Autoriser les méthodes HTTP spécifiées
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Autoriser les en-têtes spécifiés
        chain.doFilter(req, res);
    }

    // Implémentations des méthodes init() et destroy() à ajouter selon les besoins
}