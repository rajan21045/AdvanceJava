// Gives us access to the Introspector, BeanInfo and PropertyDescriptor classes
import java.beans.*;

// Demo of "implicit introspection": Java discovers a class's properties
// automatically from its getter/setter naming (getX / setX), with no extra config
public class implicit_introspection {
    public static void main(String[] args) throws Exception {

        // Analyze the Person class and collect info about its properties.
        // Passing Object.class as the "stop class" tells Java to ignore anything
        // inherited from Object, so the "class" property (from getClass()) is skipped
        BeanInfo info = Introspector.getBeanInfo(Person.class, Object.class);

        // Loop over every property that was discovered (here: age and name)
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {

            // Print the property name, its type, and the names of its getter and setter
            System.out.println(pd.getName() + " : " + pd.getPropertyType().getSimpleName()
                + " | getter=" + pd.getReadMethod().getName()    // method that reads the value
                + " | setter=" + pd.getWriteMethod().getName()); // method that writes the value
        }
    }
}

// A simple JavaBean: private fields with public getters and setters
class Person {
    private String name; // property "name"
    private int age;     // property "age"

    // Getter for name: "getName" makes Java detect a property called "name"
    public String getName() { return name; }

    // Setter for name: "setName" makes the property writable
    public void setName(String name) { this.name = name; }

    // Getter for age
    public int getAge() { return age; }

    // Setter for age
    public void setAge(int age) { this.age = age; }
}