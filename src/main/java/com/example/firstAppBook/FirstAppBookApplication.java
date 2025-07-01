package com.example.firstAppBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
public class FirstAppBookApplication {

	/**
	 * Main method to run the Spring Boot application.
	 *
	 * @param args command line arguments
	 */

	public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, IOException {

		SpringApplication.run(FirstAppBookApplication.class, args);
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

		/*
		  @Override
    	protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Cloning will create a new instance
    }

	}*/
	}
}
