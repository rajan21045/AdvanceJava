// Step 1: Create a public class
// Step 5: Implement Serializable (for persistence)
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    // Step 2: Declare private properties
    private String name;
    private int salary;

    // Step 6: Add event support (notifies listeners when a property changes)
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // Step 3: Add a public no-arg constructor
    public Employee() { }

    // Step 4: Write public getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        support.firePropertyChange("name", oldName, name);
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        int oldSalary = this.salary;
        this.salary = salary;
        support.firePropertyChange("salary", oldSalary, salary);
    }

    // Methods to register and remove event listeners
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    // Business method
    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
    }

    // Step 7: Main method for running the bean with java Employee.java
    public static void main(String[] args) {
        Employee emp = new Employee();

        // Listen for property changes
        emp.addPropertyChangeListener((PropertyChangeEvent e) ->
            System.out.println("Property '" + e.getPropertyName() + "' changed from "
                + e.getOldValue() + " to " + e.getNewValue()));

        emp.setName("Ram Sharma");
        emp.setSalary(50000);

        System.out.println("---- Employee Details ----");
        emp.display();
    }
}