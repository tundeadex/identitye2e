package task.directory.model;

public class Cars {
    public String regNo;
    public String make;
    public String color;

    public Cars(String regNo, String make, String color) {
        this.regNo = regNo;
        this.make = make;
        this.color = color;
    }

    public String toString() {
        return String.format("\nRegistration Number: %s \nMake: %s \nColor: %s \n", this.regNo, this.make, this.color);
    }
}
