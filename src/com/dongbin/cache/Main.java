package com.dongbin.cache;

public class Main {

    public static void main(String[] args) {
        MyAccountService service = new MyAccountService();

        service.getAccountByName("dongbin");
        service.getAccountByName("dongbin");
        service.getAccountByName("dongbin");
    }
}
