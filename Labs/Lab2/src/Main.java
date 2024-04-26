import domain.Appointment;
import domain.Patient;
import exceptions.DuplicateItemException;
import exceptions.ItemNotFound;
import repository.AppointmentRepository;
import repository.MemoryRepository;
import repository.PatientRepository;
import service.DentalService;
import ui.UI;

import java.time.LocalDate;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws DuplicateItemException, ItemNotFound {
        PatientRepository patientRepo = new PatientRepository();
        Patient patient1 = new Patient("Charles", 16, "Monaco", 1616);
        Patient patient2 = new Patient("Lewis", 44, "London", 4444);
        Patient patient3 = new Patient("Max", 33, "Amsterdam", 3333);
        patientRepo.addItem(patient1);
        patientRepo.addItem(patient2);
        patientRepo.addItem(patient3);
        System.out.println("List of patients: ");
        //System.out.println(patientRepo.getAllItems());

        AppointmentRepository appointmentRepo = new AppointmentRepository();
        Appointment appointment1 = new Appointment(16, patient1, LocalDate.of(2023,12,12));
        Appointment appointment2 = new Appointment(33, patient2, LocalDate.of(2024,05,30));
        appointmentRepo.addItem(appointment1);
        appointmentRepo.addItem(appointment2);
        System.out.println("List of appointments: ");
        System.out.println(appointmentRepo.getAllItems());


        PatientRepository patientsRepository = new PatientRepository();
        AppointmentRepository appointmentsRepository = new AppointmentRepository();
        DentalService patientsDentalService = new DentalService(patientRepo,  appointmentsRepository);
        UI patientsUi = new UI(patientsDentalService);
        patientsUi.run();

    }
}
