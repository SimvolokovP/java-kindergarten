package models;

public class Child extends Human {
    private double height;
    private double weight;

    public Child(int id, String name, int age, String gender, double height, double weight) {
        super(id, name, age, gender);
        this.height = height;
        this.weight = weight;
    }

    public Child(String name, int age, String gender, double height, double weight) {
        super(name, age, gender);
        this.height = height;
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Child{" +
                "height=" + height +
                ", weight=" + weight +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }

}
