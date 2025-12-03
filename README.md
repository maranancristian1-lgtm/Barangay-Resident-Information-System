# Barangay-Resident-Information-System
IT 2112
  
Aluan Mark Justin

Maranan Cristian I.

Ramirez Dennis Alfred D.

# Description / Overview
The Barangay Resident Information System manages resident records by storing key details and categorizing residents as Child, Adult, or Senior. It allows adding, updating, deleting, and searching by name or house number, using encapsulation, inheritance, polymorphism, and abstraction to ensure organized, secure, and efficient data management.

# Users can:
List Infomation

View Information

Modify or update specific information

# Project Structure:

1. Resident (Abstract Class)
   - Holds common resident attributes: name, birthdate, gender, house number, address, contact.
   - Defines abstract methods `getCategory()` and `checkEligibility()` for subclasses to implement.

2. ChildResident, AdultResident, SeniorResident (Subclasses)
   - Extend Resident and provide specific implementations for category and eligibility rules.

3. BarangaySystem (Manager Class)
   - Manages `resident` records: add, update, delete, search, and list residents.
   - Maintains the ArrayList of all residents and handles sorting and validation.

4. main Class
   - Contains the main method and menu interface for user interaction.
   - Controls program flow and calls BarangaySystem methods based on user choices.
