package com.dongbin.design.pattern.db20190331;

public class TextFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.setMsg(request.getMsg() + "---TextFilter");
        filterChain.doFilter(request, response, filterChain);
        response.setMsg(response.getMsg() + "---TextFilter");
    }
}
