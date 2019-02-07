package com.dongbin.cache;

public class MyAccountService {

    private MyCacheManager<Account> manager;

    public MyAccountService() {
        this.manager = new MyCacheManager<>();
    }

    public Account getAccountByName(String name) {
        Account result = manager.get(name);
        if (result != null) {
            return result;
        }
        result = getFromDB(name);
        if (result != null) {
            manager.put(name, result);
        }
        return result;

    }

    private Account getFromDB(String name) {
        System.out.println("数据库查询Account，name:" + name);
        return new Account(name);
    }
}
