```java
public class Event {
    private double time; // Time of the event
    private Particle a, b; // Particles involved in the event
    private int countA, countB; // Number of collisions for each particle

    public Event(double time, Particle a, Particle b) {
        this.time = time; // Initialize event time
        this.a = a; // Initialize first particle
        this.b = b; // Initialize second particle
        countA = 0; // Initialize collision count for first particle
        countB = 0; // Initialize collision count for second particle
    }

    public int compareTo(Event that) {
        // Compare events based on time
        return this.time - that.time;
    }

    public boolean isValid() {
        // Check if the event is valid based on collision counts
        return a.count == countA && b.count == countB;
    }
}
```