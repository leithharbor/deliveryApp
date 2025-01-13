package com.example.deliveryApp.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class URICheckFilter implements Filter {

    //허용되는 URL 패턴 목록
    private static final List<Pattern> ALLOWED_PATTERNS = new ArrayList<>();

    static {
        ALLOWED_PATTERNS.add(Pattern.compile("^/users/signup$"));
        ALLOWED_PATTERNS.add(Pattern.compile("^/users/\\d+$"));
        ALLOWED_PATTERNS.add(Pattern.compile("^/users/login$"));
        ALLOWED_PATTERNS.add(Pattern.compile("^/users/logout$"));
        ALLOWED_PATTERNS.add(Pattern.compile("^/stores$"));
        ALLOWED_PATTERNS.add(Pattern.compile("^/stores/\\d+$"));
        ALLOWED_PATTERNS.add(Pattern.compile("^/menus$"));
        ALLOWED_PATTERNS.add(Pattern.compile("^/menus/\\d+$"));
        ALLOWED_PATTERNS.add(Pattern.compile("^/orders$"));
        ALLOWED_PATTERNS.add(Pattern.compile("^/orders/\\d+$"));
        ALLOWED_PATTERNS.add(Pattern.compile("^/reviews$"));
        ALLOWED_PATTERNS.add(Pattern.compile("^/riviews\\d+$"));

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException,
            ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;
        String requestURI = httpRequest.getRequestURI();

        //허용되는 URL이 아닌 경우
        if (!isAllowedURL(requestURI)) {
            httpResponse.setContentType("text/html; charset=UTF-8");
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            httpResponse.getWriter().write("사이트에 연결할 수 없음. " + requestURI + "에 오타가 있는지 확인해주세요.");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /*허용되는 URL 인지 확인하는 메서드(URL 패턴 확인)*/
    private boolean isAllowedURL(String url) {
        for (Pattern pattern : ALLOWED_PATTERNS) {
            if (pattern.matcher(url).matches()) {
                return true;
            }
        }
        return false;
    }
}