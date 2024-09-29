import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ContactApp {
    private MyArrayList<Contact> contacts = new MyArrayList<>();

    public ContactApp() {
        File text = new File("FacultyStaff.txt");
        Scanner myReader;
        try {
            myReader = new Scanner(text);
            String line;
            while (myReader.hasNextLine()) {
                line = myReader.nextLine();
                String[] splitline = line.split("\t");
                Contact professor = new Contact(splitline[0], splitline[1]);
                contacts.add(professor);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }
        this.sortcontacts();
    }

    private void sortcontacts() {
        String earliestname = "";
        int earliestindex = 0;
        int unsortedindex = 0;
        while (true) {
            for (int i = unsortedindex; i < contacts.size(); i++) {
                if (i == unsortedindex) {
                    earliestname = contacts.get(i).getName();
                } else if (earliestname.compareTo(contacts.get(i).getName()) > 0) {
                    earliestname = contacts.get(i).getName();
                    earliestindex = i;
                }
            }
            Contact temporary = contacts.remove(unsortedindex);
            contacts.add(unsortedindex, contacts.get(earliestindex-1));
            contacts.remove(earliestindex);
            contacts.add(earliestindex, temporary);
            unsortedindex++;
            if (unsortedindex == contacts.size() - 1) {
                break;
            }
        }
    }

    public Contact binarysearch(String name) {
        int min = 0;
        int max = contacts.size();
        while (min <= max) {
            String middle = contacts.get((max + min) / 2).getName().toLowerCase();
            if (middle.startsWith(name)) {
                return contacts.get((max + min) / 2);
            } else if (min == max) {
                return null;
            } else if (name.compareTo(middle) < 0) {
                max = (max + min) / 2 - 1;
            } else {
                min = (max + min) / 2 + 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ContactApp app = new ContactApp();
        Scanner user = new Scanner(System.in);
        while (true) {
            System.out.println("What name would you like to search for? (Type exit to quit) ");
            String name = user.next().toLowerCase();
            if (name.toLowerCase().equals("exit")) {
                user.close();
                System.exit(0);
            }
            Contact answer = app.binarysearch(name);
            if (answer == null) {
                System.out.println("Sorry, but we could not find the name: " + name);
                System.out.println();
            } else {
                System.out.println("The office found is: " + answer.getOffice() + " (" + answer.getName() + ")");
                System.out.println();
            }
        }
    }
}