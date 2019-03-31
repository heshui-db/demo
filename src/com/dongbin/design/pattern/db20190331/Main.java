package com.dongbin.design.pattern.db20190331;

public class Main {
    public static void main(String[] args) {
        Request request = new Request();
        request.setMsg("request");
        Response response = new Response();
        response.setMsg("response");

        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new HtmlFilter())
                .addFilter(new TextFilter());

        FilterChain filterChain1 = new FilterChain();

        filterChain.addFilter(filterChain1);

        filterChain.doFilter(request, response, filterChain);

        System.out.println(request.getMsg());
        System.out.println(response.getMsg());
    }
}
