public class StaticContextTest {

    private class A {

    }

    public static void main(String[] args) {

        StaticContextTest staticContextTest = new StaticContextTest();
        A a = staticContextTest.new A();
    }
}
