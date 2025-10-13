/* 
// import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

public class BouncingBalls 
{
    public static void main(String[] args) 
    {
        int N = Integer.parseInt(args[0]); // Number of balls
        Balls[] balls = new Balls[N]; // Array to store the balls
        for (int i = 0; i < N; i++) 
        {
            balls[i] = new Balls(); // Create a new ball object
        }
        while (true) 
        {
            StdDraw.clear(); // Clear the drawing canvas
            for (int i = 0; i < N; i++) 
            {
                balls[i].move(0.5); // Move each ball
                balls[i].draw(); // Draw each ball
            }
            StdDraw.show(50); // Show the drawing with a delay
        }
    }
}
class Balls 
{
    private double rx, ry; // Position of the ball
    private double vx, vy; // Velocity of the ball
    private double radius; // Radius of the ball

    public Balls() 
    {
        // Initialize the ball with random position and velocity
        rx = Math.random(); // Random x position
        ry = Math.random(); // Random y position
        vx = Math.random() / 10; // Random x velocity
        vy = Math.random() / 10; // Random y velocity
        radius = 0.05; // Fixed radius
    }

    public void move(double dt) 
    {
        if (rx + vx * dt < radius || rx + vx * dt > 1 - radius) // Check for collision with walls
        {
            vx = -vx; // Reverse x velocity on collision with wall
        }
        {
            vx = -vx; // Reverse x velocity on collision with wall
        }
        if (ry + vy * dt < radius || ry + vy * dt > 1 - radius) 
        {
            vy = -vy; // Reverse y velocity on collision with wall
        }
        rx += vx * dt; // Update x position
        ry += vy * dt; // Update y position
    }

    public void draw() 
    {
        // StdDraw.setPenColor(StdDraw.BLUE); // Set pen color to blue
        StdDraw.filledCircle(rx, ry, radius); // Draw the ball as a filled circle
    }
}

*/