package hospital.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface for Hospital Appointment Service
 * This interface defines the contract for remote hospital services
 * @author YourName
 */
public interface HospitalService extends Remote {
    
    /**
     * Retrieves list of available doctors
     * @return String array containing doctor names
     * @throws RemoteException if remote communication fails
     */
    String[] getAvailableDoctors() throws RemoteException;
    
    /**
     * Books an appointment for a patient with specified doctor
     * @param doctorName Name of the doctor
     * @param patientName Name of the patient
     * @return Confirmation message with booking details
     * @throws RemoteException if remote communication fails
     */
    String bookAppointment(String doctorName, String patientName) throws RemoteException;
}