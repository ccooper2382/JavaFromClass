/**
 * Class represents a persons contact information
 */

import java.util.Comparator;
import java.util.Objects;


public class Contact implements Comparable {


    /**
     * Contacts first name
     */
    private final String firstName;

    private final String lastName;

    /**
     * Contacts email address
     */
    private final String email;

    /**
     * Contacts phone number
     */
    private final String phone;


    /**
     * Constructs and initalizes the Contact object
     * @param firstName
     * @param lastName
     * @param email
     * @param phone
     */
    public Contact(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    /**
     * parses a line from a binary file into a Contact object
     * @param line
     * @return Contact object
     */
    public static Contact parse(String line) {
        String[] contactInfo = line.split(" ");
        return new Contact(contactInfo[0], contactInfo[1], contactInfo[2], contactInfo[3]);
    }


    public static final Comparator<Contact> LAST_NAME_COMPARATOR = new Comparator<Contact>() {
        @Override
        public int compare(Contact o1, Contact o2) {
            return o1.lastName.compareTo(o2.lastName);
        }
    };

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, phone);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;
        if (!(obj instanceof Contact)) {
            return false;
        }
        var that = (Contact) obj;
        return Objects.equals(firstName, that.lastName)
                && Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s %s %s%n  ", this.firstName, this.lastName, this.email, this.phone);
    }


}

