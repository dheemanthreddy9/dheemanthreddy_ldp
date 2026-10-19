package person;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== 1. Creating Person using Builder Pattern ===");
        Person p1 = new Person.PersonBuilder("kalyan", "Kumar")
                .age(25)
                .address("Hyderabad")
                .email("kalyan@mail.com")
                .phone("6352014854")
                .gender("Male")
                .occupation("Developer")
                .nationality("Indian")
                .maritalStatus("Single")
                .build();
        System.out.println("Builder Person created:\n" + p1);

        System.out.println("\n=== 2. Creating Person using Setters ===");
        PersonWithSetters p2 = new PersonWithSetters();
        p2.setFirstName("Ravi");
        p2.setLastName("Kumar");
        p2.setAge(25);
        p2.setAddress("Hyderabad");
        p2.setEmail("ravi@mail.com");
        p2.setPhone("7589633458");
        p2.setGender("Male");
        p2.setOccupation("Developer");
        p2.setNationality("Indian");
        p2.setMaritalStatus("Single");
        System.out.println("Setter Person created:\n" + p2);

        System.out.println("\n=== 3. Demonstrating Mutability vs Immutability ===");
        p2.setAge(30);
        System.out.println("After mutating p2 with setAge(30): " + p2);

        System.out.println("Builder person p1 remains unchanged (immutable): " + p1);

        System.out.println("\n=== 4. Reusing Builder for Multiple Instances ===");
        Person.PersonBuilder builder = new Person.PersonBuilder("Asha", "Rao").age(20);
        Person a1 = builder.build();
        builder.age(40);
        Person a2 = builder.build();
        System.out.println("Instance a1 age: " + a1.getAge());
        System.out.println("Instance a2 age: " + a2.getAge());
    }
}
