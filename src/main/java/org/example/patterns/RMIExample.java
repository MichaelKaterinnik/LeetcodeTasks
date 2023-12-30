package org.example.patterns;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implement the logic of the run method in SERVER_THREAD. It will simulate the server part:
 * 1) Initialize the registry field, which will receive and process requests on port 2099.
 * 2) Create two objects - Cat and Dog.
 * 3) Use the UnicastRemoteObject class to create Remote objects for the created Cat and Dog.
 * Remote objects will be able to accept calls to their methods using the selected port (2099).
 * 4) For Cat and Dog, add to the registry a connection between a unique text key and a Remote object. Create a text
 * key yourself.
 * 5) When any exception occurs, output its stack trace to the System.err stream.
 */
public class RMIExample {
    public static Registry registry;

    // Pretend we're starting an RMI client as the CLIENT_THREAD thread
    public static Thread CLIENT_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (String bindingName : registry.list()) {
                    Animal service = (Animal) registry.lookup(bindingName);
                    service.printName();
                    service.speak();
                }
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
        }
    });

    // Pretend we're starting an RMI server as the SERVER_THREAD thread
    public static Thread SERVER_THREAD = new Thread(new Runnable() {
        public static final String CAT_BINDING_NAME = "remote.cat";
        public static final String DOG_BINDING_NAME = "remote.dog";
        @Override
        public void run() {
            //напишите тут ваш код
            try {
                Animal cat = new Cat("murka");
                Animal dog = new Dog("bobick");
                registry = LocateRegistry.createRegistry(2099);
                Remote remoteCat = UnicastRemoteObject.exportObject(cat, 0);
                Remote remoteDog = UnicastRemoteObject.exportObject(dog, 1);
                registry.bind(CAT_BINDING_NAME, remoteCat);
                registry.bind(DOG_BINDING_NAME, remoteDog);

            } catch (RemoteException | AlreadyBoundException e) {
                e.printStackTrace(System.err);
            }
        }
    });

    public static void main(String[] args) throws InterruptedException {
        // Starting an RMI server thread
        SERVER_THREAD.start();
        Thread.sleep(1000);
        // Start the client
        CLIENT_THREAD.start();
    }


    public interface Animal extends Remote {
        void speak() throws RemoteException;

        void printName() throws RemoteException;
    }

    public static class Cat implements Animal {

        private final String name;

        public Cat(String name) {
            this.name = name;
        }

        @Override
        public void speak() throws RemoteException {
            System.out.println("meow");
        }

        @Override
        public void printName() throws RemoteException {
            System.out.print("Cat " + name + " ");
        }
    }

    public static class Dog implements Animal {

        private final String name;

        public Dog(String name) {
            this.name = name;
        }

        @Override
        public void speak() throws RemoteException {
            System.out.println("woof");
        }

        @Override
        public void printName() throws RemoteException {
            System.out.print("Dog " + name + " ");
        }
    }
}
