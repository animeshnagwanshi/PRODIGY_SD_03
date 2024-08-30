import java.io.*;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    
    public class ContactManager {
        private static final String FILE_NAME = "contacts.ser";
        private List<contact> contacts;
    
        public ContactManager() {
            contacts = loadContacts();
        }
    
        public void addContact(String name, String phone, String email) {
            contacts.add(new contact(name, phone, email));
            saveContacts();
        }
    
        public void viewContacts() {
            if (contacts.isEmpty()) {
                System.out.println("No contacts available.");
            } else {
                for (int i = 0; i < contacts.size(); i++) {
                    System.out.println((i + 1) + ". " + contacts.get(i));
                }
            }
        }
    
        public void editContact(int index, String name, String phone, String email) {
            if (index >= 0 && index < contacts.size()) {
                contact contact = contacts.get(index);
                contact.setName(name);
                contact.setPhoneNumber(phone);
                contact.setEmail(email);
                saveContacts();
            } else {
                System.out.println("Invalid contact index.");
            }
        }
    
        public void deleteContact(int index) {
            if (index >= 0 && index < contacts.size()) {
                contacts.remove(index);
                saveContacts();
            } else {
                System.out.println("Invalid contact index.");
            }
        }
    
        private void saveContacts() {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
                out.writeObject(contacts);
            } catch (IOException e) {
                System.out.println("Error saving contacts: " + e.getMessage());
            }
        }
    
        @SuppressWarnings("unchecked")
        private List<contact> loadContacts() {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                return (List<contact>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                return new ArrayList<>();
            }
        }
    
        public static void main(String[] args) {
            ContactManager manager = new ContactManager();
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;
    
            while (!exit) {
                System.out.println("Contact Management System");
                System.out.println("1. Add Contact");
                System.out.println("2. View Contacts");
                System.out.println("3. Edit Contact");
                System.out.println("4. Delete Contact");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
    
                switch (choice) {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter phone number: ");
                        String phone = scanner.nextLine();
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();
                        manager.addContact(name, phone, email);
                        break;
                    case 2:
                        manager.viewContacts();
                        break;
                    case 3:
                        System.out.print("Enter the contact index to edit: ");
                        int editIndex = scanner.nextInt() - 1;
                        scanner.nextLine();  // Consume newline
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new phone number: ");
                        String newPhone = scanner.nextLine();
                        System.out.print("Enter new email: ");
                        String newEmail = scanner.nextLine();
                        manager.editContact(editIndex, newName, newPhone, newEmail);
                        break;
                    case 4:
                        System.out.print("Enter the contact index to delete: ");
                        int deleteIndex = scanner.nextInt() - 1;
                        scanner.nextLine();  // Consume newline
                        manager.deleteContact(deleteIndex);
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
    
            scanner.close();
        }
    }
    
    

