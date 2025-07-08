          
          
   # Hospital Appointment Booking System - Java RMI by Aziz Khamis Salim

 # Project Overview
MetroCare Hospital is a distributed appointment booking system built using Java RMI (Remote Method Invocation). This system allows patients to remotely check doctor availability and book appointments, eliminating the need for physical queuing at the hospital.

  # Features
•	Remote Doctor Lookup: View available doctors from any client terminal
•	Appointment Booking: Book appointments with specific doctors
•	Duplicate Prevention: Prevents double-booking for the same patient-doctor combination
•	Input Validation: Comprehensive error handling and user input validation
•	Multi-client Support: Multiple clients can connect simultaneously
•	Real-time Updates: Instant booking confirmations with unique appointment IDs

   # System Architecture
[image](https://github.com/user-attachments/assets/d3725652-9211-4b4c-b073-47b101036973)



   #   Project Structure

HospitalRMISystem/
├── src/
│   ├── hospital/
│   │   ├── interfaces/
│   │   │   └── HospitalService.java      # Remote interface
│   │   ├── server/
│   │   │   ├── HospitalServiceImpl.java  # Service implementation
│   │   │   └── HospitalServer.java       # Server startup
│   │   └── client/
│   │       └── HospitalClient.java       # Client application
├── README.md
└── docs/
    └── user-guide.md

   #     Prerequisites

•	Java: JDK 8 or higher
•	IDE: NetBeans IDE (recommended) or any Java IDE
•	Network: Local network access (for distributed deployment)
•	OS: Windows, macOS, or Linux


   #     Installation & Setup Clone/Download Project

    git clone <https://github.com/Charmid05/hospital-rmi-system.git>

1. cd HospitalRMISystem
2. Open in NetBeans
3.	Open NetBeans IDE
4.	File → Open Project
5.	Navigate to HospitalRMISystem folder
6.	Click "Open Project"
7. Build Project
8.	Right-click on project name
9.	Select "Build"
10. Ensure no compilation errors


#    Running the Application

   #   Step 1: Start the Server

1.	Navigate to hospital.server.HospitalServer
2.	Right-click → "Run File" (or press Shift+F6)
3.	Wait for confirmation message: 
4.	=== MetroCare Hospital Server Ready ===Server is running and waiting for client connections...

   # Step 2: Start the Client
   
1.	Navigate to hospital.client.HospitalClient
2.	Right-click → "Run File" (or press Shift+F6)
3.	Client menu should appear: 
4.	=== MetroCare Hospital - Patient Portal ===Connected to hospital service successfully!=== MAIN MENU ===1. View Available Doctors2. Book Appointment3. Exit
