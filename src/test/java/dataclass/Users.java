package dataclass;

public class Users {

    private final String city;
    private final String name;
    private final String phone;

    public Users(String name, String phoneNumber, String cityName) {
        this.name = name;
        this.phone = phoneNumber;
        this.city = cityName;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }
}

