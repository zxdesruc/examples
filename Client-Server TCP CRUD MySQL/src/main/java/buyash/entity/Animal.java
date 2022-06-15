package buyash.entity;

public class Animal {

    private int id;
    private String name;
    private int age;
    private String color;
    private String species;
    private String country;
    private String sex;

    public Animal() {
    }

    public static Animal buildAnimal(String[] data) {
        int id = Integer.parseInt(data[0]);
        String name = data[1];
        int age = Integer.parseInt(data[2]);
        String color = data[3];
        String country = data[4];
        String species = data[5];
        String sex = data[6];
        return new Animal(id, name, age, color, country, species, sex);
    }

    public Animal(int id, String name, int age, String color, String species, String country, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.color = color;
        this.species = species;
        this.country = country;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {this.species = species;}
    public String getSex() {return sex;}

    public void setSex(String sex) {
        this.sex = sex;
    }


    @Override
    public String toString() {
        return id + " " + name + " " + age + " " + color + " " + country + " " + species + " " + sex;
    }

}
