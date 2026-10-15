package person;


public class PersonWithSetters {
    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String email;
    private String phone;
    private String gender;
    private String occupation;
    private String nationality;
    private String maritalStatus;

    public PersonWithSetters() {
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { this.occupation = occupation; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getMaritalStatus() { return maritalStatus; }
    public void setMaritalStatus(String maritalStatus) { this.maritalStatus = maritalStatus; }

    @Override
    public String toString() {
        return "PersonWithSetters ["
                + "firstName=" + firstName
                + ", lastName=" + lastName
                + ", age=" + age
                + ", address=" + address
                + ", email=" + email
                + ", phone=" + phone
                + ", gender=" + gender
                + ", occupation=" + occupation
                + ", nationality=" + nationality
                + ", maritalStatus=" + maritalStatus
                + "]";
    }
}
