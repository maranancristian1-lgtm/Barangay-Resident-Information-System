<h1 align = "center"> 💡Barangay-Resident-Information-System💡 </h1>
<h2 align = "center"> IT 2112 </h2>
<p align = "center">
Aluan Mark Justin<br/>
Maranan Cristian I.<br/>
Ramirez Dennis Alfred D.
</p>

# 📜 Description / Overview
The Barangay Resident Information System manages resident records by storing key details and categorizing residents as Child, Adult, or Senior. It allows adding, updating, deleting, and searching by name or house number, using encapsulation, inheritance, polymorphism, and abstraction to ensure organized, secure, and efficient data management.

# 🧑‍💻 Users can:
📝 List Infomation<br/>
👀 View Information<br/>
🔨 Modify or update specific information

# 📚 Project Structure:

1. Resident (Abstract Class)
   - Holds common resident attributes: name, birthdate, gender, house number, address, contact.
   - Defines abstract methods `getCategory()` and `checkEligibility()` for subclasses to implement.

2. ChildResident, AdultResident, SeniorResident (Subclasses)
   - Extend Resident and provide specific implementations for category and eligibility rules.

3. BarangaySystem (Manager Class)
   - Manages `resident` records: add, update, delete, search, and list residents.
   - Maintains the ArrayList of all residents and handles sorting and validation.
   - 
# 🛞 How to Run the Program

1. Open your terminal in the folder where your .java files are located (for example, src/ if you have all files there).

2. Compile the program using the javac command:
```
javac main.java
```
3. Run the program using the java command:
```
java main
```
4. main Class
   - Contains the main method and menu interface for user interaction.
   - Controls program flow and calls BarangaySystem methods based on user choices.

# 😊 System Features

* **Add Resident:** Create a new resident record and categorize automatically.
* **View Resident List:** Display all residents alphabetically with key details.
* **Search by Name:** Find a resident’s full information using their name.
* **Search by House Number:** Find all residents living at a specific house number.
* **Update Resident Information:** Edit address, house number, gender, or contact number.
* **Delete Resident:** Remove a resident’s record permanently.
* **Check Eligibility:** View eligibility based on resident category (Child, Adult, Senior).
  
# 🦾 Object-oriented Priciples

### **💊 Encapsulation** <br/>
In the code, all the attributes of the` Resident` class such as `name`, `birthdate`, `gender`, `houseNumber`, `address`, and `contactNumber` are declared as private. Access to these fields is controlled through public getter and setter methods, ensuring that the resident information can only be read or modified in a controlled way, such as when updating a resident’s address, gender, or contact number.

```java
abstract class Resident {
    private String name;
    private String birthdate;
    private String gender;
    private String houseNumber;
    private String address;
    private String contactNumber;

    public String getName() { return name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public void setAddress(String address) { this.address = address; }
    public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
}
```

### **🧬 Inheritance** <br/>
The classes `ChildResident`, `AdultResident`, and `SeniorResident` inherit from the abstract `Resident` class. This allows them to reuse the common attributes and methods defined in `Resident`, such as `getName()`, `getAge()`, and `getCategory()`, while adding their own specific behaviors like eligibility rules.
```java
class ChildResident extends Resident {
    public ChildResident(String name, String birthdate, String gender, String houseNumber, String address, String contactNumber) {
        super(name, birthdate, gender, houseNumber, address, contactNumber);
    }
    public String getCategory() { return "Child"; }
    public String checkEligibility() { return "Guardian verification required"; }
}
```

### **🎭 Polymorphism** <br/>
Polymorphism is used when the system handles different types of residents (child, adult, senior) through a common `Resident` reference. For example, the `ArrayList<Resident>` can store any type of resident, and calling methods like `getCategory()` or `checkEligibility()` on a `Resident` object automatically executes the appropriate version for the actual resident type.
```java
ArrayList<Resident> records = new ArrayList<>();

Resident r = categorize(name, birthdate, gender, houseNumber, address, contactNumber);
records.add(r);

// Polymorphic method call
System.out.println("Category: " + r.getCategory());
System.out.println("Eligibility: " + r.checkEligibility());
```

### **💡 Abstraction** <br/>
Abstraction is applied through the abstract `Resident `class, which defines essential methods like `getCategory()` and `checkEligibility()` without providing their implementation. Each subclass `(ChildResident``, AdultResident` `SeniorResident`) provides its own implementation of these methods, allowing the system to work with residents at a general level without worrying about the details of each type.
```java
abstract class Resident {
    public abstract String getCategory();
    public abstract String checkEligibility();
}
```

## 🫶 Example Output
```
Barangay Resident Information System
1. Add Resident
2. Search Resident by Name
3. Search Resident by House Number
4. Resident List
5. Update Resident Information
6. Delete Resident
7. Exit
Choose option:
```
### 🟥 Output when you choose option 1:
**All output of option 1 - 7.**
```
Choose option: 1
Enter Name: Juan Dela Cruz
Enter Birthdate (YYYY-MM-DD): 2010-05-12
Enter Gender: Male
Enter House Number: 12B
Enter Address: 123 Barangay Street, Cityville
Enter Contact Number: 09171234567
Resident added successfully as Child
```
### 🟧 Output when you choose option 2:
```
Choose option: 2
Enter name to search: Juan Dela cruz

--- Resident Found ---
Name: Juan Dela Cruz
Birthdate: 2010-05-12
Gender: Male
House Number: 12B
Address: 123 Barangay Street, Cityville
Contact Number: 09171234567
Age: 15
Category: Child
Eligibility: Guardian verification required
```
### 🟨 Output when you choose option 3:
```
Choose option: 3
Enter house number to search: 12B

--- Residents Found ---
Name: Juan Dela Cruz | Category: Child | Gender: Male | Age: 15 | Address: 123 Barangay Street, Cityville | Contact: 09171234567
```
### 🟩 Output when you choose option 4:
```
Choose option: 4
---- SORTED RESIDENT LIST (A-Z) ----
Juan Dela Cruz | Child | Age: 15 | Gender: Male | House Number: 12B | Address: 123 Barangay Street, Cityville | Contact: 09171234567
Lolo Pedro Reyes | Senior | Age: 75 | Gender: Male | House Number: 3C | Address: 88 Old Town Street, Cityville | Contact: 09189876543
Maria Santos | Adult | Age: 40 | Gender: Female | House Number: 7A | Address: 45 Riverside Avenue, Cityville | Contact: 092212345678
```
### 🟦 Output when you choose option 5:
```
Choose option: 5
Enter Name to Update: Juan Dela Cruz
-- Updating Information for Juan Dela Cruz --
1. Update Address
2. Update House Number
3. Update Gender
4. Update Contact Number
5. Update Name
6. Update Birthdate
Choose field to update: 4
Enter new contact number: 09542375644
Resident information updated successfully!
```
### 🟪 Output when you choose option 6:
```
Choose option: 6
Enter Name to Delete: Juan Dela Cruz
Resident deleted successfully.
```
### 🟫 Output when you choose option 7:
```
Choose option: 7
Exiting system...
```

## Contributors

<table>
<tr>
    <th> &nbsp; </th>
    <th> Name </th>
    <th> Role </th>
</tr>
<tr>
    <td><img src="static/marieemoiselle.JPG" width="100" height="100"> </td>
    <td><strong>Fatima Marie P. Agdon, MSCS</strong> <br/>
    <a href="https://github.com/marieemoiselle" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=pink" alt="marieemoiselle's GitHub">
        </a>
    </td>
    <td>Project Leader/System Architect</td>
</tr>
<tr>
    <td><img src="https://github.com/maranancristian1-lgtm/Barangay-Resident-Information-System/blob/4c23adc2531559e2845d124d49e298a44c407d13/img/4e836e88-da60-4ae5-b88a-670e8bcb0c70.jpg width="100" height="100"> </td>
    <td><strong>Jei Q. Pastrana, MSIT</strong> <br/>
    <a href="https://github.com/jeisquaredd" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=blue" alt="jeisquaredd's GitHub">
        </a>
    </td>
    <td>File Handling Specialist</td>
</tr>
<tr>
    <td><img src="static/renzmarrion.jpg" width="100" height="100"> </td>
    <td><strong>Renz Marrion O. Perez</strong> <br/>
    <a href="https://github.com/digZy030509" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-%23121011.svg?logo=github&logoColor=green" alt="digZy030509's GitHub">
        </a>
    </td>
    <td>Feature Developer</td>
</tr>
</table>

##  Acknowledgment

We sincerely express our gratitude to our instructor for the guidance and support provided throughout the completion of this project. We also extend our appreciation to our classmates and peers for their cooperation and encouragement during the development process.

## DISCLAIMER
This project and its contents are provided for example and learning purposes only. Students are encouraged to use it as a reference and not copy it in its entirety.


