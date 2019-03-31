package com.dongbin.design.pattern.db20190331;

public interface Filter {

    void doFilter(Request request, Response response, FilterChain filterChain);
}
