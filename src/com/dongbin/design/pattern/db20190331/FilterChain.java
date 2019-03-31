package com.dongbin.design.pattern.db20190331;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter {

    private List<Filter> filters = new ArrayList<>();
    private int index = 0;

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        if (index == filters.size()) {
            return;
        }

        Filter filter = filters.get(index++);
        filter.doFilter(request, response, filterChain);
    }

    public FilterChain addFilter(Filter filter) {
        this.filters.add(filter);
        return this;
    }

    public FilterChain removeFilter(Filter filter) {
        filters.remove(filter);
        return this;
    }

}
