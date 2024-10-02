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
        int size = contacts.size();
        int earliestindex = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (j == i) {
                    earliestindex = i;
                } else {
                    String earliestname = contacts.get(earliestindex).getName().toLowerCase();
                    String curname = contacts.get(j).getName().toLowerCase(); 
                    if (curname.compareTo(earliestname) < 0) {
                        earliestindex = j;
                    }
                }
            }
            contacts.swap(earliestindex, i);
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
            String name = user.next();
            if (name.toLowerCase().equals("exit")) {
                user.close();
                System.exit(0);
            }
            Contact answer = app.binarysearch(name.toLowerCase());
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