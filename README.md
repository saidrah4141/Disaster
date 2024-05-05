# How to Use Relief Center Interface

To utilize the relief center interface, follow these steps:

1. **Choose Your Role**: Upon accessing the interface, you'll have two options:
   - **Central Worker**: An inquirer responsible for viewing and managing inquiries.
   - **Location Worker**: Manages supplies and information for disaster victims.

2. **Adding and Editing Disaster Victims**:
   - When adding a new victim, provide their first name and entry date in the specified format.
   - To edit a victim's information, select their first name (not case-sensitive). You'll be presented with all associated information, including medical records, date of birth, age, last name, personal belongings, gender, dietary restrictions, family relations, and social worker ID.

3. **Allocating Supplies**:
   - Ensure the availability of resources in the facility before allocation. Overallocation will prompt a message indicating insufficient resources.
   - Exhausting a resource will remove it from the resources list.

4. **Managing Relationships**:
   - Relationships can only be established among victims within the facility.
   - Creating a relationship automatically updates the family relations of the related victims and prevents duplicate relations.

5. **Assigning Social Workers**:
   - Choose from available social workers accessed from a database.
   - Assigning a social worker ID to a victim requires exiting to the main menu and re-entering as a central worker in the same execution session.

6. **Viewing Pending Inquiries**:
   - As a central worker, select "Pending Inquiries" and enter the worker's ID to view victims assigned to that inquirer.
   - Creating an inquiry stores the information in the database and sets the victim's social worker ID to 0, removing them from pending inquiries.

7. **Limitations**:
   - Each victim can only be assigned one social worker at a time.
   - Multiple assignments to the same victim are prohibited.
# Disaster