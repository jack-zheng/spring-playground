package dynamicproxy;

public class Dog implements ISound, IJump{
    @Override
    public void makeSound() {
        System.out.println("wang, wang...");
    }

    @Override
    public void jump() {
        System.out.println("dog jump...");
    }
}
