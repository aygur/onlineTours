package com.naraikin.onlinetours.common;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by dmitrii on 25.02.17.
 */

public class FilterConnect implements Filter {
    static Logger logger = Logger.getLogger(FilterConnect.class);

    private ArrayList<String> urlList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("avoid-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");

        urlList = new ArrayList<String>();

        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());
        }
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String url = request.getServletPath();
        boolean allowedRequest = false;
        boolean isStatic = false;

        if(urlList.contains(url)) {
            allowedRequest = true;
            logger.trace("authorized access request = " + url);
        } else {
            if(url.contains("webjars") || url.contains("statics")){
                logger.trace("All ok - static " + url);
                chain.doFilter(request, response);
                return;
            }
            logger.trace("else " + url);
        }

        HttpSession session = request.getSession(false);
        Object login = null;
        if(session != null) {
            login = session.getAttribute("login");
        }
        if( !allowedRequest && login == null){
            logger.trace("Unauthorized access request");
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
