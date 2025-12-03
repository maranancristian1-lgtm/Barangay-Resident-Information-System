import java.util.*;
import java.time.*;
import java.time.format.DateTimeParseException;

abstract class Resident {
    private String name;
    private String birthdate; // YYYY-MM-DD
    private String gender;
    private String houseNumber;
    private String address;
    private String contactNumber;

    public Resident(String name, String birthdate, String gender, String houseNumber, String address, String contactNumber) {
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.houseNumber = houseNumber;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    public String getName() { return name; }
    public String getBirthdate() { return birthdate; }
    public String getGender() { return gender; }
    public String getHouseNumber() { return houseNumber; }
    public String getAddress() { return address; }
    public String getContactNumber() { return contactNumber; }

    public void setGender(String gender) { this.gender = gender; }
    public void setAddress(String address) { this.address = address; }
    public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public int getAge() {
        try {
            LocalDate birth = LocalDate.parse(birthdate);
            return Period.between(birth, LocalDate.now()).getYears();
        } catch (DateTimeParseException e) {
            return -1;
        }
    }

    public abstract String getCategory();
    public abstract String checkEligibility();
}

class ChildResident extends Resident {
    public ChildResident(String name, String birthdate, String gender, String houseNumber, String address, String contactNumber) {
        super(name, birthdate, gender, houseNumber, address, contactNumber);
    }
    public String getCategory() { return "Child"; }
    public String checkEligibility() { return "Guardian verification required"; }
}

class AdultResident extends Resident {
    public AdultResident(String name, String birthdate, String gender, String houseNumber, String address, String contactNumber) {
        super(name, birthdate, gender, houseNumber, address, contactNumber);
    }
    public String getCategory() { return "Adult"; }
    public String checkEligibility() { return "Check employment records"; }
}

class SeniorResident extends Resident {
    public SeniorResident(String name, String birthdate, String gender, String houseNumber, String address, String contactNumber) {
        super(name, birthdate, gender, houseNumber, address, contactNumber);
    }
    public String getCategory() { return "Senior"; }
    public String checkEligibility() { return "Eligible for senior benefits"; }
}

class BarangaySystem {
    private ArrayList<Resident> records = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public boolean isDuplicate(String name, String birthdate) {
        for (Resident r : records) {
            if (r.getName().equalsIgnoreCase(name) && r.getBirthdate().equals(birthdate)) return true;
        }
        return false;
    }

    public Resident categorize(String name, String birthdate, String gender, String houseNumber, String address, String contactNumber) {
        int age = new ChildResident(name, birthdate, gender, houseNumber, address, contactNumber).getAge();
        if (age <= 17) return new ChildResident(name, birthdate, gender, houseNumber, address, contactNumber);
        else if (age <= 59) return new AdultResident(name, birthdate, gender, houseNumber, address, contactNumber);
        else return new SeniorResident(name, birthdate, gender, houseNumber, address, contactNumber);
    }

    public void addResident(String name, String birthdate, String gender, String houseNumber, String address, String contactNumber) {
        if (isDuplicate(name, birthdate)) {
            System.out.println("Duplicate record detected. Cannot save.");
            return;
        }
        Resident r = categorize(name, birthdate, gender, houseNumber, address, contactNumber);
        records.add(r);
        System.out.println("Resident added successfully as " + r.getCategory());
    }

    public Resident findResident(String name) {
        for (Resident r : records) {
            if (r.getName().equalsIgnoreCase(name)) return r;
        }
        return null;
    }

    // Search by Name
    public void searchResidentByName() {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine();
        Resident r = findResident(name);
        if (r == null) {
            System.out.println("Resident not found.");
            return;
        }

        System.out.println("\n--- Resident Found ---");
        System.out.println("Name: " + r.getName());
        System.out.println("Birthdate: " + r.getBirthdate());
        System.out.println("Gender: " + r.getGender());
        System.out.println("House Number: " + r.getHouseNumber());
        System.out.println("Address: " + r.getAddress());
        System.out.println("Contact Number: " + r.getContactNumber());
        System.out.println("Age: " + r.getAge());
        System.out.println("Category: " + r.getCategory());
        System.out.println("Eligibility: " + r.checkEligibility());
    }

    // Search by House Number
    public void searchResidentByHouseNumber() {
        System.out.print("Enter house number to search: ");
        String houseNum = sc.nextLine();
        boolean found = false;

        for (Resident r : records) {
            if (r.getHouseNumber().equalsIgnoreCase(houseNum)) {
                if (!found) System.out.println("\n--- Residents Found ---");
                System.out.println("Name: " + r.getName() + " | Category: " + r.getCategory() +
                        " | Gender: " + r.getGender() + " | Age: " + r.getAge() +
                        " | Address: " + r.getAddress() + " | Contact: " + r.getContactNumber());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No residents found with that house number.");
        }
    }

    public void residentList() {
        if (records.isEmpty()) {
            System.out.println("No resident records available.");
            return;
        }

        Collections.sort(records, (a, b) -> a.getName().compareToIgnoreCase(b.getName()));

        System.out.println("---- SORTED RESIDENT LIST (A-Z) ----");
        for (Resident r : records) {
            System.out.println(
                r.getName() + " | " + r.getCategory() +
                " | Age: " + r.getAge() +
                " | Gender: " + r.getGender() +
                " | House Number: " + r.getHouseNumber() +
                " | Address: " + r.getAddress() +
                " | Contact: " + r.getContactNumber()
            );
        }
    }

    public void updateResident(String name, Scanner sc) {
        Resident r = findResident(name);
        if (r == null) {
            System.out.println("Resident not found.");
            return;
        }

        System.out.println("-- Updating Information for " + r.getName() + " --");
        System.out.println("1. Update Address");
        System.out.println("2. Update House Number");
        System.out.println("3. Update Gender");
        System.out.println("4. Update Contact Number");
        System.out.print("Choose field to update: ");

        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Enter new address: ");
                r.setAddress(sc.nextLine());
                break;
            case 2:
                System.out.print("Enter new house number: ");
                r.setHouseNumber(sc.nextLine());
                break;
            case 3:
                System.out.print("Enter new gender: ");
                r.setGender(sc.nextLine());
                break;
            case 4:
                System.out.print("Enter new contact number: ");
                r.setContactNumber(sc.nextLine());
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Resident information updated successfully!");
    }

    public void deleteResident(String name) {
        Resident r = findResident(name);
        if (r == null) {
            System.out.println("Resident not found.");
            return;
        }
        records.remove(r);
        System.out.println("Resident deleted successfully.");
    }
}

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BarangaySystem system = new BarangaySystem();

        while (true) {
            System.out.println("\nBarangay Resident Information System");
            System.out.println("1. Add Resident");
            System.out.println("2. Search Resident by Name");
            System.out.println("3. Search Resident by House Number");
            System.out.println("4. Resident List");
            System.out.println("5. Update Resident Information");
            System.out.println("6. Delete Resident");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Birthdate (YYYY-MM-DD): ");
                    String bday = sc.nextLine();
                    System.out.print("Enter Gender: ");
                    String gender = sc.nextLine();
                    System.out.print("Enter House Number: ");
                    String houseNumber = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();
                    System.out.print("Enter Contact Number: ");
                    String contactNumber = sc.nextLine();
                    system.addResident(name, bday, gender, houseNumber, address, contactNumber);
                    break;

                case 2:
                    system.searchResidentByName();
                    break;

                case 3:
                    system.searchResidentByHouseNumber();
                    break;

                case 4:
                    system.residentList();
                    break;

                case 5:
                    System.out.print("Enter Name to Update: ");
                    system.updateResident(sc.nextLine(), sc);
                    break;

                case 6:
                    System.out.print("Enter Name to Delete: ");
                    system.deleteResident(sc.nextLine());
                    break;

                case 7:
                    System.out.println("Exiting system...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
