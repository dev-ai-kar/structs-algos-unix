```java
/**************************
 * Particle.java    
 * 
 * This file is not meant to run it is just how a simulation code would look like.
 * It is a simulation of particles bouncing off walls and each other.
 * **********************/

public class Particle {
    private double rx, ry; // Position of the particle
    private double vx, vy; // Velocity of the particle
    private double radius; // Radius of the particle

    public Particle(double x, double y, double vx, double vy, double radius) {
        this.rx = x; // Initialize x position
        this.ry = y; // Initialize y position
        this.vx = vx; // Initialize x velocity
        this.vy = vy; // Initialize y velocity
        this.radius = radius; // Initialize radius
    }

    public void move(double dt) {
        if (rx + vx * dt < radius || rx + vx * dt > 1 - radius) {
            vx = -vx; // Reverse x velocity on collision with wall
        }
        if (ry + vy * dt < radius || ry + vy * dt > 1 - radius) {
            vy = -vy; // Reverse y velocity on collision with wall
        }
        rx += vx * dt; // Update x position
        ry += vy * dt; // Update y position
    }

    public void draw() {
        StdDraw.filledCircle(rx, ry, radius); // Draw the particle as a filled circle
    }

    // Predict collision with another particle or a wall
    public double timeToHit(Particle that) {
        // Calculate the time until this particle hits the other particle
        if (this == that) return Double.POSITIVE_INFINITY; // No collision with itself
        double dx = that.rx - this.rx; // Difference in x position
        double dy = that.ry - this.ry; // Difference in y position
        double dvx = that.vx - this.vx; // Difference in x velocity
        double dvy = that.vy - this.vy; // Difference in y velocity
        double dvdr = dvx * dx + dvy * dy; // Dot product of velocity and position differences
        if (dvdr > 0) return Double.POSITIVE_INFINITY; // No collision if moving apart
        double dvdv = dvx * dvx + dvy * dvy; // Square of the difference in velocity
        double drdr = dx * dx + dy * dy; // Square of the distance between particles
        double sigma = this.radius + that.radius; // Sum of radii
        double d = dvdr * dvdr - dvdv * (drdr - sigma * sigma); // Discriminant of the quadratic equation
        if (d < 0) return Double.POSITIVE_INFINITY; // No collision if discriminant is negative
        return -(dvdr + Math.sqrt(d)) / dvdv; // Time until collision
    }
    
    public double timeToHitVerticalWall() {
        // Calculate the time until this particle hits a vertical wall
        if (vx > 0) return (1 - rx - radius) / vx; // Time to hit right wall
        if (vx < 0) return (rx - radius) / vx; // Time to hit left wall
        return Double.POSITIVE_INFINITY; // No collision if velocity is zero
    }
    
    public double timeToHitHorizontalWall() {
        if (vy > 0) return (1 - ry - radius) / vy; // Time to hit top wall
        if (vy < 0) return (ry - radius) / vy; // Time to hit bottom wall
        return Double.POSITIVE_INFINITY; // No collision if velocity is zero
    }

    public void bounceOff(Particle that) {
        // Reverse velocities upon collision with another particle
        double dx = that.rx - this.rx; // Difference in x position
        double dy = that.ry - this.ry; // Difference in y position
        double dvx = that.vx - this.vx; // Difference in x velocity
        double dvy = that.vy - this.vy; // Difference in y velocity
        double dvdr = dvx * dx + dvy * dy; // Dot product of velocity and position differences
        double dist = this.radius + that.radius; // Sum of radiicity
        double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist); // Impulse
        double Jx = J * dx / dist; // Impulse in x direction
        double Jy = J * dy / dist; // Impulse in y direction
        this.vx += Jx / this.mass; // Update x velocity
        this.vy += Jy / this.mass; // Update y velocity
        that.vx -= Jx / that.mass; // Update x velocity of the other particle
        that.vy -= Jy / that.mass; // Update y velocity of the other particle
        this.count++; // Increment collision count for this particle
        that.count++; // Increment collision count for the other particle

}

    public void bounceOffVerticalWall() {
        vx = -vx; // Reverse x velocity on collision with vertical wall
    }

    public void bounceOffHorizontalWall() {
        vy = -vy; // Reverse y velocity on collision with horizontal wall
    }

}
```