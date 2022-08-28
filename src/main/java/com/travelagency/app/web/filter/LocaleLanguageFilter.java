package com.travelagency.app.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter class which set the attribute of locale to change language
 */
@WebFilter(filterName = "LocaleLanguageFilter", urlPatterns = {"/*"})
public class LocaleLanguageFilter implements Filter {
    private static final String LOCALE = "locale";

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getParameter(LOCALE) != null) {
            req.getSession().setAttribute(LOCALE, req.getParameter(LOCALE));
        }
        chain.doFilter(request, response);
    }
}
