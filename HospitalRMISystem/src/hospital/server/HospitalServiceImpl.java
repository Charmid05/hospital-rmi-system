package hospital.server;

import hospital.interfaces.HospitalService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of HospitalService interface
 * Handles doctor availability and appointment booking logic
 * @author Aziz Khamis Salim
 */
public class HospitalServiceImpl extends UnicastRemoteObject implements HospitalService {
    
    // Dummy data for available doctors
    private final String[] doctors = {
        "Dr. Aziz Khamis Salim (Cardiologist)",
        "Dr. James Mwangi (Pediatrician)", 
        "Dr. Grace Achieng (Dermatologist)"
    };
    
    // Store booked appointments (Doctor -> List of Patients)
    private final Map<String, List<String>> appointments;
    
    /**
     * Constructor - initializes the service implementation
     * @throws RemoteException if remote object creation fails
     */
    public HospitalServiceImpl() throws RemoteException {
        super();
        appointments = new HashMap<>();
        // Initialize empty appointment lists for each doctor
        for (String doctor : doctors) {
            appointments.put(doctor, new ArrayList<>());
        }
        System.out.println("Hospital Service Implementation initialized successfully!");
    }
    
    /**
     * Returns list of available doctors
     * @return String array of doctor names
     * @throws RemoteException if remote communication fails
     */
    @Override
    public String[] getAvailableDoctors() throws RemoteException {
        System.out.println("Client requested available doctors list");
        return doctors.clone(); // Return copy to prevent modification
    }
    
    /**
     * Books an appointment for a patient with specified doctor
     * @param doctorName Name of the doctor
     * @param patientName Name of the patient
     * @return Confirmation message
     * @throws RemoteException if remote communication fails
     */
    @Override
    public String bookAppointment(String doctorName, String patientName) throws RemoteException {
        System.out.println("Booking request received - Doctor: " + doctorName + ", Patient: " + patientName);
        
        // Validate input
        if (doctorName == null || doctorName.trim().isEmpty()) {
            return "Error: Doctor name cannot be empty";
        }
        if (patientName == null || patientName.trim().isEmpty()) {
            return "Error: Patient name cannot be empty";
        }
        
        // Check if doctor exists
        boolean doctorExists = false;
        for (String doctor : doctors) {
            if (doctor.equalsIgnoreCase(doctorName.trim())) {
                doctorName = doctor; // Use the exact doctor name format
                doctorExists = true;
                break;
            }
        }
        
        if (!doctorExists) {
            return "Error: Doctor '" + doctorName + "' not found. Please check available doctors.";
        }
        
        // Check if patient already has appointment with this doctor
        List<String> doctorAppointments = appointments.get(doctorName);
        if (doctorAppointments.contains(patientName.trim())) {
            return "Error: Patient '" + patientName + "' already has an appointment with " + doctorName;
        }
        
        // Book the appointment
        doctorAppointments.add(patientName.trim());
        String confirmationMessage = "SUCCESS: Appointment booked for " + patientName + 
                                   " with " + doctorName + 
                                   ". Appointment ID: APT" + System.currentTimeMillis();
        
        System.out.println("Appointment booked successfully: " + confirmationMessage);
        return confirmationMessage;
    }
}