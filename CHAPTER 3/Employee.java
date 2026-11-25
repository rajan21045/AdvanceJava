// Create a public class
// Implement Serializable (for persistence)
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    // Declare private properties
    private String name;
    private int salary;

    // Add event support (notifies listeners when a property changes)
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // Add a public no-arg constructor
    public Employee() { }

    // Write public getters and setters
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

    // Main method for running the bean with java Employee.java
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