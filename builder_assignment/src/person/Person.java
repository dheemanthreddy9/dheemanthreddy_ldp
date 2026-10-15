package person;


public class Person {

    private final String firstName;
    private final String lastName;
    private final int age;
    private final String address;
    private final String email;
    private final String phone;
    private final String gender;
    private final String occupation;
    private final String nationality;
    private final String maritalStatus;

    private Person(PersonBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.address = builder.address;
        this.email = builder.email;
        this.phone = builder.phone;
        this.gender = builder.gender;
        this.occupation = builder.occupation;
        this.nationality = builder.nationality;
        this.maritalStatus = builder.maritalStatus;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getGender() { return gender; }
    public String getOccupation() { return occupation; }
    public String getNationality() { return nationality; }
    public String getMaritalStatus() { return maritalStatus; }

    @Override
    public String toString() {
        return "Person ["
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


    public static class PersonBuilder {
        private final String firstName;
        private final String lastName;

        private int age;
        private String address;
        private String email;
        private String phone;
        private String gender;
        private String occupation;
        private String nationality;
        private String maritalStatus;

        public PersonBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public PersonBuilder age(int age) {
            this.age = age;
            return this;
        }

        public PersonBuilder address(String address) {
            this.address = address;
            return this;
        }

        public PersonBuilder email(String email) {
            this.email = email;
            return this;
        }

        public PersonBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public PersonBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public PersonBuilder occupation(String occupation) {
            this.occupation = occupation;
            return this;
        }

        public PersonBuilder nationality(String nationality) {
            this.nationality = nationality;
            return this;
        }

        public PersonBuilder maritalStatus(String maritalStatus) {
            this.maritalStatus = maritalStatus;
            return this;
        }

        public Person build() {
            if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty()) {
                throw new IllegalStateException("First name and Last name are mandatory.");
            }
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative.");
            }
            return new Person(this);
        }
    }
}
