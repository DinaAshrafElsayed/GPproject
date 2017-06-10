/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.iti.shareit.view.filters;

/**
 *
 * @author Dina Ashraf
 */
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession userSession = req.getSession(false);

            // supposdly add home page and viewItems page about us 
            //all pages that doesnt need login
            // or make filter on specfic pages instead easier
            String reqURI = req.getRequestURI();
            if (reqURI.contains("items.xhtml") || reqURI.contains("About_us.xhtml")
                    || reqURI.contains("Contact_us.xhtml") || reqURI.contains("fag.xhtml")
                    || reqURI.contains("advancedSearch.xhtml") ) {
                System.out.println("contiune with no redirect pages that dont need authentication");
                chain.doFilter(request, response);
            } else if (reqURI.contains("/register.xhtml") && userSession != null && userSession.getAttribute("userDto") != null) {
                System.out.println("redirect to home trying to access register while logged in");
                resp.sendRedirect(req.getContextPath() + "/faces/pages/items.xhtml");
            } else if (userSession != null && userSession.getAttribute("userDto") != null) {
                System.out.println("contiune with no redirect since trying access pages while logged in");
                chain.doFilter(request, response);
            } else if (!reqURI.contains("/register.xhtml") && (userSession == null || userSession.getAttribute("userDto") == null)) {
                System.out.println("redirect to register trying to access pages with no login");
                resp.sendRedirect(req.getContextPath() + "/faces/pages/register.xhtml");
            } else {
                System.out.println("trying to register while not logged it");
                chain.doFilter(request, response);
            }
        } catch (IOException | ServletException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}
