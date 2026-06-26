class Employee {
    private String employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(String employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId +
               ", Name: " + name +
               ", Position: " + position +
               ", Salary: " + salary;
    }
}

class EmployeeManagementSystem {
    private Employee[] employees;
    private int count;

    public EmployeeManagementSystem(int size) {
        employees = new Employee[size];
        count = 0;
    }

    public void addEmployee(Employee employee) {
        if (count < employees.length) {
            employees[count] = employee;
            count++;
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Cannot add employee. Array is full.");
        }
    }

    public Employee searchEmployee(String employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                return employees[i];
            }
        }
        return null;
    }

    public void displayEmployees() {
        if (count == 0) {
            System.out.println("No employees to display.");
            return;
        }

        System.out.println("Employee List:");
        for (int i = 0; i < count; i++) {
            System.out.println(employees[i]);
        }
    }

    public boolean deleteEmployee(String employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[count - 1] = null;
                count--;
                System.out.println("Employee deleted successfully.");
                return true;
            }
        }
        System.out.println("Employee not found.");
        return false;
    }
}

public class EmployeeManagementApp {
    public static void main(String[] args) {
        EmployeeManagementSystem system = new EmployeeManagementSystem(5);

        system.addEmployee(new Employee("E101", "Asha", "Developer", 50000));
        system.addEmployee(new Employee("E102", "Rahul", "Tester", 45000));
        system.addEmployee(new Employee("E103", "Meena", "Manager", 70000));

        system.displayEmployees();

        System.out.println();
        Employee found = system.searchEmployee("E102");
        if (found != null) {
            System.out.println("Search Result:");
            System.out.println(found);
        } else {
            System.out.println("Employee not found.");
        }

        System.out.println();
        system.deleteEmployee("E102");
        system.displayEmployees();
    }
}