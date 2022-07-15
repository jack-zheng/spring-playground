package dynamicproxy;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {

        ISound dog = new Dog();
        ISound proxyObj = (ISound) Proxy.newProxyInstance(Client.class.getClassLoader(), Dog.class.getInterfaces(), new MyInvocationHandler(dog));
        proxyObj.makeSound();

        IJump p2 = (IJump) Proxy.newProxyInstance(Client.class.getClassLoader(), new Class[]{IJump.class}, new MyInvocationHandler(dog));
        p2.jump();
    }
}
