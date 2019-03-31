package com.dongbin.design.pattern.builder;

public interface ParlourBuilder extends Buildable.Builder<ParlourBuilder, Parlour> {

    ParlourBuilder wall(String wall);

    ParlourBuilder TV(String TV);

    ParlourBuilder sofa(String sofa);
}
