package com.dongbin.design.pattern.builder;

public class Parlour implements Buildable<ParlourBuilder> {

    //墙
    private String wall;
    //电视
    private String TV;
    //沙发
    private String sofa;

    public String getWall() {
        return wall;
    }

    public void setWall(String wall) {
        this.wall = wall;
    }

    public String getTV() {
        return TV;
    }

    public void setTV(String TV) {
        this.TV = TV;
    }

    public String getSofa() {
        return sofa;
    }

    public void setSofa(String sofa) {
        this.sofa = sofa;
    }

    @Override
    public String toString() {
        return "wall:" + this.getWall() +
                ",TV:" + this.getTV() +
                ",sofa:" + this.getSofa();
    }

    @Override
    public ParlourBuilder toBuilder() {
        return new ParlourImplBuilder();
    }

    private static class ParlourImplBuilder implements ParlourBuilder {

        private Parlour parlour;

        public ParlourImplBuilder() {
            this.parlour = new Parlour();
        }


        @Override
        public ParlourBuilder wall(String wall) {
            this.parlour.setWall(wall);
            return this;
        }

        @Override
        public ParlourBuilder TV(String TV) {
            this.parlour.setTV(TV);
            return this;
        }

        @Override
        public ParlourBuilder sofa(String sofa) {
            this.parlour.setSofa(sofa);
            return this;
        }

        @Override
        public Parlour build() {
            return this.parlour;
        }

        @Override
        public ParlourBuilder from(Parlour in) {
            if (in != null) {
                this.parlour = in;
            }
            return this;
        }
    }
}
