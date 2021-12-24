/**
 * This class represents a list of Contact objects
 *
 *
 */

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ContactSet {

    /**
     * the name of the file that stores the contact information
     */
    private static final String FILE_NAME = "contacts.bin";

    /**
     * Holds the Contact objects provided by the user or parsed from a binary file
     */
    private final Set<Contact> contacts;

    /**
     * constructs and initalizes the ContactSet object
     */
    public ContactSet() {
        this.contacts = new TreeSet<Contact>(Contact.LAST_NAME_COMPARATOR);
    }

    /**
     * adds a Contact object to the ContactSet
     * @param contact
     */
    public void add(Contact contact) {
        contacts.add(contact);
    }


    public Set<Contact> getContacts() {
        return this.contacts;
    }

    /**
     * Checks if a particular object is contained in the ContactSet
     * @param contact
     * @return
     */
    public boolean contains(Contact contact) {

        if (contacts.contains(contact)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * saves the current ContactSet to a binary file
     */
    public void save() {
        try (var writer = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(FILE_NAME)))) {
            for (var contact : this.contacts) {
                writer.writeUTF(String.valueOf(contact));
            }
        } catch (IOException iOEx) {
            System.out.println(iOEx.getMessage());
        }
    }


    @Override
    public String toString() {
        System.out.printf("%-14s %-24s %-15s%n", "Name", "Email", "Phone number");
        System.out.println("-------------------------------------------------------");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }

        return "";
    }

    @Override
    public int hashCode() {return Objects.hash(contacts); }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;
        if (!(obj instanceof ContactSet)) {
            return false;
        }
        var that = (ContactSet) obj;
        return  Objects.equals(contacts, that.contacts);
    }
}
