package uk.ac.ebi.ddi.ddicloudconfig.filters;

import org.springframework.cloud.config.server.support.EnvironmentPropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ReplaceEnvironmentFilter implements Filter {

    private StandardEnvironment environment;

    @Override
    public void destroy() { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        ResponseWrapper capturingResponseWrapper = new ResponseWrapper((HttpServletResponse) response);
        filterChain.doFilter(request, capturingResponseWrapper);
        if (response.getContentType() != null && response.getContentType().contains("application/json")) {
            String content = capturingResponseWrapper.getCaptureAsString();
            String res = EnvironmentPropertySource.resolvePlaceholders(environment, content);
            response.setContentLength(res.length());
            response.getWriter().write(res);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        environment = new StandardEnvironment();
    }
}
