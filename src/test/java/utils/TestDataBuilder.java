package utils;

/**
 * Test Data Builder - Fluent builder for test data
 * Example: new TestDataBuilder().withUsername("test").withPassword("pass").build()
 */
public class TestDataBuilder {
    
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    
    public TestDataBuilder withUsername(String username) {
        this.username = username;
        return this;
    }
    
    public TestDataBuilder withPassword(String password) {
        this.password = password;
        return this;
    }
    
    public TestDataBuilder withEmail(String email) {
        this.email = email;
        return this;
    }
    
    public TestDataBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    
    public TestDataBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    
    public TestDataBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }
    
    public TestDataBuilder withAddress(String address) {
        this.address = address;
        return this;
    }
    
    public TestData build() {
        return new TestData(username, password, email, firstName, lastName, phone, address);
    }
    
    // Predefined test data sets
    public static TestData validUser() {
        return new TestDataBuilder()
            .withUsername(Constants.Credentials.VALID_USERNAME)
            .withPassword(Constants.Credentials.VALID_PASSWORD)
            .withEmail("valid@example.com")
            .build();
    }
    
    public static TestData invalidUser() {
        return new TestDataBuilder()
            .withUsername(Constants.Credentials.INVALID_USERNAME)
            .withPassword(Constants.Credentials.INVALID_PASSWORD)
            .build();
    }
    
    /**
     * Test Data Object
     */
    public static class TestData {
        public final String username;
        public final String password;
        public final String email;
        public final String firstName;
        public final String lastName;
        public final String phone;
        public final String address;
        
        public TestData(String username, String password, String email, 
                       String firstName, String lastName, String phone, String address) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.address = address;
        }
        
        @Override
        public String toString() {
            return "TestData{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
        }
    }
}