package es.jllopezalvarez.ejemplos.genericos.clases;

public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void talk() {
        System.out.println("Miau...");
    }
}
