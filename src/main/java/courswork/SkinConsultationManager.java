package courswork;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Dumidu
 */
public interface SkinConsultationManager {
    void addNewDoctor(Doctor doctor);
    void removeDoctor(String doctor_medical_num);
    void printTheListOfDoctors();
    void saveInFile(String storeName) throws IOException;
    void removePatient(String patientID);
    void printTheListOfPatient();
    void removeConsultation(String patientID) throws IOException;
    void printTheListOfConsultation();
    void generateReport();
    ArrayList<Doctor> returnDoctorArrayList();
    ArrayList<Patient> returnPatientArrayList();
    ArrayList<Consultation> returnConsultationArrayList();
    int return_memebrs_can_hold_center();

}
