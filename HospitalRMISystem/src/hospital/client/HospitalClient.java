package hospital.client;

import hospital.interfaces.HospitalService;
import java.rmi.Naming;
import java.util.Scanner;

/**
 * Hospital RMI Client Application
 * Connects to remote hospital service and handles user interactions
 * @author YourName
 */
public class HospitalClient {
    
    private static HospitalService hospitalService;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        
        try {
            System.out.println("=== MetroCare Hospital - Patient Portal ===");
            System.out.println("Connecting to hospital server...");
            
            // Connect to remote service
            hospitalService = (HospitalService) Naming.lookup("//localhost:1099/HospitalService");
            System.out.println("Connected to hospital service successfully!\n");
            
            // Start client menu
            showMainMenu();
            
        } catch (Exception e) {
            System.err.println("Client error: " + e.getMessage());
            System.err.println("Please ensure the server is running!");
        } finally {
            scanner.close();
        }
    }
    
    /**
     * Displays main menu and handles user choices
     */
    private static void showMainMenu() {
        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. View Available Doctors");
            System.out.println("2. Book Appointment");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    viewAvailableDoctors();
                    break;
                case "2":
                    bookAppointment();
                    break;
                case "3":
                    System.out.println("Thank you for using MetroCare Hospital services!");
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }
    
    /**
     * Displays list of available doctors
     */
    private static void viewAvailableDoctors() {
        try {
            System.out.println("\n=== AVAILABLE DOCTORS ===");
            String[] doctors = hospitalService.getAvailableDoctors();
            
            if (doctors.length == 0) {
                System.out.println("No doctors available at the moment.");
            } else {
                for (int i = 0; i < doctors.length; i++) {
                    System.out.println((i + 1) + ". " + doctors[i]);
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error retrieving doctors: " + e.getMessage());
        }
    }
    
    /**
     * Handles appointment booking process
     */
    private static void bookAppointment() {
        try {
            System.out.println("\n=== BOOK APPOINTMENT ===");
            
            // Show available doctors first
            String[] doctors = hospitalService.getAvailableDoctors();
            if (doctors.length == 0) {
                System.out.println("No doctors available for booking.");
                return;
            }
            
            System.out.println("Available Doctors:");
            for (int i = 0; i < doctors.length; i++) {
                System.out.println((i + 1) + ". " + doctors[i]);
            }
            
            // Get patient name
            System.out.print("\nEnter patient name: ");
            String patientName = scanner.nextLine().trim();
            
            if (patientName.isEmpty()) {
                System.out.println("Patient name cannot be empty!");
                return;
            }
            
            // Get doctor selection
            System.out.print("Select doctor (enter number 1-" + doctors.length + "): ");
            String doctorChoice = scanner.nextLine().trim();
            
            try {
                int doctorIndex = Integer.parseInt(doctorChoice) - 1;
                if (doctorIndex < 0 || doctorIndex >= doctors.length) {
                    System.out.println("Invalid doctor selection!");
                    return;
                }
                
                String selectedDoctor = doctors[doctorIndex];
                
                // Confirm booking
                System.out.println("\nBooking Details:");
                System.out.println("Patient: " + patientName);
                System.out.println("Doctor: " + selectedDoctor);
                System.out.print("Confirm booking (y/n): ");
                
                String confirm = scanner.nextLine().trim().toLowerCase();
                if (confirm.equals("y") || confirm.equals("yes")) {
                    // Book appointment
                    String result = hospitalService.bookAppointment(selectedDoctor, patientName);
                    System.out.println("\n" + result);
                } else {
                    System.out.println("Booking cancelled.");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
            
        } catch (Exception e) {
            System.err.println("Error booking appointment: " + e.getMessage());
        }
    }
}