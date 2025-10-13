// Class definition (template in heap's method area)
class Person {
    // Instance variables (stored in heap)
    String name;
    int age;

    // Constructor (creates instances in heap)
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Method (stack frame when called)
    void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

public class MemoryDemo {
    public static void main(String[] args) {
        // Stack: 'p1' reference variable | Heap: Person object
        Person p1 = new Person("Alice", 25);
        
        // Primitive variable (stack)
        int[] numbers = new int[3]; // Array object in heap
        
        // Stack: 'numbers' reference | Heap: array of 3 integers
        numbers[0] = 10; // Modifying heap memory
        
        System.out.println("Before modification:");
        p1.display();
        System.out.println("Array[0]: " + numbers[0]);

        modifyData(p1, numbers); // Passing references by value
        
        System.out.println("\nAfter modification:");
        p1.display(); // Shows modified age
        System.out.println("Array[0]: " + numbers[0]); // Shows modified value
    }

    // New stack frame created for modifyData
    static void modifyData(Person person, int[] arr) {
        // Modifying heap through references
        person.age = 30;  // Affects original object
        arr[0] = 20;     // Affects original array
        
        // Local variable (stack) with new object (heap)
        @SuppressWarnings("unused")
        Person temp = new Person("Bob", 40);
        temp = null; // Eligible for garbage collection
        
        // Reassigning reference (won't affect original array)
        arr = new int[5]; // New array object in heap
        arr[0] = 100;    // Original array remains unchanged
    }
}