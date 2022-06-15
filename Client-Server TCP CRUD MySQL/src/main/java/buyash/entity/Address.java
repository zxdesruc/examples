package buyash.entity;

public class Address {

    private int id;
    private String name;
    private int buildingNumber;
    private String street;
    private String type;
    private String phoneNumber;

    public Address() {
    }

    public static Address buildAddress(String[] data) {
        int id = Integer.parseInt(data[0]);
        String name = data[1];
        int building = Integer.parseInt(data[3]);
        String street = data[2];
        String number = data[4];
        String type = data[5];
        return new Address(id, name, building, street, number, type);
    }

    public Address(int id, String name, int buildingNumber, String street, String type, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public int getId() {
        return id;
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

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + street + " " + buildingNumber + " " + phoneNumber + " " + type;
    }


}
