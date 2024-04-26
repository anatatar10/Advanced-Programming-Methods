package domain;

public class Patient implements Identifiable{

    private int id, phoneNumber;
    private String name, city;

    public Patient(String name, int id, String city, int phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.city = city;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int idToSet) {
        this.id = id;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumberToSet) {
        this.phoneNumber = phoneNumberToSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameToSet) {
        this.name = nameToSet;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String cityToSet) {
        this.city = cityToSet;
    }

    @Override
    public String toString() {
        return "Patient { name = '" + this.name + "\'" +
                ", id = " + id + ", city = " + city + ", phone number = " + phoneNumber + " }";
    }
    @Override
    public boolean equals(Object objectToCompare)
    {
        if(this == objectToCompare)
        {
            return true;
        }
        if(objectToCompare.getClass() != Patient.class)
        {
            return false;
        }
        Patient patientToCompare = (Patient)objectToCompare;
        return this.id == patientToCompare.id && this.phoneNumber == patientToCompare.phoneNumber &&
                this.name.equals(patientToCompare.name) && this.city.equals(patientToCompare.city);
    }


}
