package ptteng.model;

import java.io.Serializable;

public class Person implements Serializable{

    private static final long serialVersionUID = 1L;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}
