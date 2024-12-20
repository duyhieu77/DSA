package Entity;

public class Student {
    private String name;
    private double score;
    private int age;

    public Student() {;}


    public Student(String name, int age, double score) {
        this.name = name;
        this.score = score;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student infor: " +
                "name= " + name +
                ", score= " + score +
                ", age= " + age;
    }
}
