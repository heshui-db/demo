package com.dongbin.generic;

import java.util.Date;

public class DatePair extends Pair<Date> {
    @Override
    public Date getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(Date value) {
        super.setValue(value);
    }

    public Date get(){
        return (Date) super.get();
    }
}
