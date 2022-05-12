package dataclass;

public class Users {

    private final String name;
    private final String phoneNumber;
    private final String cityName;

    public Users(String name, String phoneNumber, String cityName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.cityName = cityName;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCityName() {
        return cityName;
    }
}

