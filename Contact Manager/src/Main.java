import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {
    /**
     * the name of the file that stores the contact information
     */
    private static final String FILE_NAME = "contacts.bin";

    public static void main(String[] args) {

        var contactsList = new ContactSet();
        var keepGoing = true;
        while (keepGoing) {
            var choice = Menu.getUserChoice("1 - List entries \n2 - Add entry \n3 - Exit \nl - Load");

            switch (choice) {
                case "1":
                    System.out.println(contactsList);
                    break;
                case "2":
                    var contactValid = false;
                    while (!contactValid) {
                        var firstName = Menu.getString("Enter First Name :");
                        var lastName = Menu.getString("Enter Last Name: ");
                        var email = Menu.getString("Enter Email: ");
                        var phone = Menu.getString("Enter Phone Number: ");
                        var newContact = new Contact(firstName, lastName, email, phone);
                        if (contactsList.contains(newContact)) {
                            System.out.println("Contact already exists. Try again.");
                        } else {
                            contactsList.add(newContact);
                            contactValid = true;
                            contactsList.save();
                            System.out.println("Contact added");
                        }
                    }
                    break;
                case "3":
                    keepGoing = false;
                    break;
                case "l":
                case "L":

                    if (Files.exists(Paths.get(FILE_NAME))) {
                        try (var in = new DataInputStream(new BufferedInputStream(new FileInputStream(FILE_NAME)))) {
                            while (in.available() > 0) {
                                var contact = in.readUTF();

                                String[] contactInfo = contact.split("/n");

                                for (var i = 0; i < contactInfo.length; i++) {
                                   contactsList.add(Contact.parse(contactInfo[i]));
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("No file to load");
                    }
                default:
                    break;
            }
        }

    }
}