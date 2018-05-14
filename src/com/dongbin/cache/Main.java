package com.dongbin.cache;

public class Main {

    public static void main(String[] args) {
        MyAccountService service = new MyAccountService();

        service.getAccoutByName("dongbin");
        service.getAccoutByName("dongbin");
        service.getAccoutByName("dongbin");
    }
}
