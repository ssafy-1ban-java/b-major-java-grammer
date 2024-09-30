package org.example.inheritance;
class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public void speak() {
        System.out.println("개가 말합니다: 멍멍");
    }
}