package hospital.server;

import hospital.interfaces.HospitalService;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Hospital RMI Server
 * Sets up RMI registry and binds the hospital service
 * @author YourName
 */
public class HospitalServer {
    
    public static void main(String[] args) {
        try {
            System.out.println("Starting MetroCare Hospital RMI Server...");
            
            // Create RMI registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("RMI Registry created on port 1099");
            
            // Create hospital service implementation
            HospitalService hospitalService = new HospitalServiceImpl();
            
            // Bind the service to the registry
            Naming.rebind("//localhost:1099/HospitalService", hospitalService);
            System.out.println("Hospital Service bound to registry");
            
            System.out.println("=== MetroCare Hospital Server Ready ===");
            System.out.println("Server is running and waiting for client connections...");
            System.out.println("Press Ctrl+C to stop the server");
            
            // Keep server running
            while (true) {
                Thread.sleep(1000);
            }
            
        } catch (Exception e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}