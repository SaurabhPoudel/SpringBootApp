package com.example.firstAppBook;

import com.example.firstAppBook.config.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
@EnableConfigurationProperties(CorsProperties.class)// Enable configuration properties for CORS settings
public class FirstAppBookApplication {

	/**
	 * Main method to run the Spring Boot application.
	 *
	 * @param args command line arguments
	 */

	public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, IOException {

		SpringApplication.run(FirstAppBookApplication.class, args);
	}
}
		// Example usage of Singleton pattern with MongoDBDataSource
		//MongoDBDataSource instance1 = MongoDBDataSource.getInstance();
/*
		// Breaking Singleton using Reflection
		// This will break the singleton pattern by using reflection to access the private constructor
		Constructor<MongoDBDataSource> constructor = MongoDBDataSource.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		MongoDBDataSource instance2 = constructor.newInstance();
		System.out.println("Instance 1: " + instance1.hashCode());
		System.out.println("Instance 2: " + instance2.hashCode());

//
		// Breaking Singleton using Serialization
		MongoDBDataSource instance1 = MongoDBDataSource.getInstance();

		// Serialize Singleton
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
		out.writeObject(instance1);
		out.close();
		// Deserialize Singleton
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("singleton.ser"));
		MongoDBDataSource instance2 = (MongoDBDataSource) in.readObject();
		in.close();
		System.out.println("Instance 1: " + instance1.hashCode());
		System.out.println("Instance 2: " + instance2.hashCode());
		if (instance1 == instance2) {
			System.out.println("Singleton pattern is broken!");
		} else {
			System.out.println("Singleton pattern is intact.");
		}
		// Breaking Singleton using Cloning

 */

		/*
		  @Override
    	protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Cloning will create a new instance
    }

	}*/
// This is a simple Spring Boot application that demonstrates the use of design patterns and principles.
// It includes examples of the Singleton pattern, Factory pattern, and SOLID principles.
// The application is structured to follow best practices in software design, making it easy to maintain and extend.
// The application can be run using the main method, which starts the Spring Boot application.
// This application is a demonstration of various design patterns and principles in Java.
// The code includes examples of the Singleton pattern, Factory pattern, and SOLID principles.
// The application is structured to follow best practices in software design, making it easy to maintain and extend.
// The application can be run using the main method, which starts the Spring Boot application.
// This code is a simple Spring Boot application that demonstrates the use of design patterns and principles.

// Example usage of SOLID principles in Java
/*
// Violates OCP
class Rectangle {
	public double length;
	public double width;
}
class AreaCalculator {
	public double calculateArea(Object shape) {
		if (shape instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) shape;
			return rectangle.length * rectangle.width;
		}
		// If we add more shapes, this method would need to change
		return 0;
	}
}
	abstract class Shape {
		public abstract double calculateArea();
	}
	class Rectangle extends Shape {
		public double length;
		public double width;
		public Rectangle(double length, double width) {
			this.length = length;
			this.width = width;
		}
		@Override
		public double calculateArea() {
			return length * width;
		}
	}
	class Circle extends Shape {
		public double radius;
		public Circle(double radius) {
			this.radius = radius;
		}
		@Override
		public double calculateArea() {
			return Math.PI * radius * radius;
		}
	}
	class AreaCalculator {
		public double calculateArea(Shape shape) {
			return shape.calculateArea();
		}
	}

	// Violates LSP
	class Bird {
		public void fly() {
			System.out.println("Flying");
		}
	}
	class Ostrich extends Bird {
		@Override
		public void fly() {
			// Ostrich can't fly, so this violates LSP
			throw new UnsupportedOperationException("Ostrich can't fly");
		}
	}
	// Following LSP
	class Bird {
		public void makeSound() {
			System.out.println("Bird sound");
		}
	}
	class FlyingBird extends Bird {
		public void fly() {
			System.out.println("Flying");
		}
	}
	class Ostrich extends Bird {
		// Ostrich can still be a Bird without needing to fly
	}


	// Violates ISP
	interface Worker {
		void work();
		void eat();
	}
	class Developer implements Worker {
		@Override
		public void work() {
			System.out.println("Developer coding");
		}
		@Override
		public void eat() {
			System.out.println("Developer eating");
		}
	}
	class Robot implements Worker {
		@Override
		public void work() {
			System.out.println("Robot working");
		}
		@Override
		public void eat() {
			// Robot doesn't need to eat, violates ISP
			throw new UnsupportedOperationException("Robot doesn't eat");
		}
	}
	// Following ISP
	interface Workable {
		void work();
	}
	interface Eatable {
		void eat();
	}
	class Developer implements Workable, Eatable {
		@Override
		public void work() {
			System.out.println("Developer coding");
		}
		@Override
		public void eat() {
			System.out.println("Developer eating");
		}
	}
	class Robot implements Workable {
		@Override
		public void work() {
			System.out.println("Robot working");
		}
	}
	// Violates DIP
	class Light {
		public void turnOn() {
			System.out.println("Light is On");
		}
	}
	class Switch {
		private Light light; // High-level module depends on a low-level module
		public Switch(Light light) {
			this.light = light;
		}//tightly coupled
		public void operate() {
			light.turnOn();
		}
	}
	// Following DIP
	interface Switchable {
		void turnOn();
	}
	class Light implements Switchable {
		public void turnOn() {
			System.out.println("Light is On");
		}
	}
	class Fan implements Switchable {
		public void turnOn() {
			System.out.println("Fan is On");
		}
	}
	class Switch {
		private Switchable device; // High-level module depends on an abstraction
		public Switch(Switchable device) {
			this.device = device;
		}
		public void operate() {
			device.turnOn();
		} */