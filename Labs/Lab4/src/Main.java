import domain.Appointment;
import domain.Patient;
import exceptions.DuplicateItemException;
import exceptions.ItemNotFound;
import repository.*;
import service.DentalService;
import ui.UI;

import java.io.FileReader;
import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;



// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws DuplicateItemException, ItemNotFound {
        IRepository<Patient, Integer> repoPatient = null;
        IRepository<Appointment, Integer> repoAppointment = null;

        try (FileReader fr = new FileReader("settings.properties"))
        {
            Properties props = new Properties();
            props.load(fr);

            String repoType = props.getProperty("repositoryType");
            String sourceNamePatient = props.getProperty("repositoryPatientsLocation");
            String sourceNameAppointment = props.getProperty("repositoryAppointmentsLocation");
            switch (repoType)
            {
                case "inmemory":
                    repoPatient = new PatientRepository();
                    repoAppointment = new AppointmentRepository();

                    Patient patient1 = new Patient("Charles", 16, "Monaco", 1616);
                    Patient patient2 = new Patient("Lewis", 44, "London", 4444);
                    Patient patient3 = new Patient("Max", 11, "Amsterdam", 1111);
                    Patient patient4 = new Patient("Laura", 123, "London", 1231);
                    Patient patient5 = new Patient("Cosmin", 3254, "London", 1243331);

                    Appointment appointment1 = new Appointment(4,patient3, LocalDate.of(2025,11,11));
                    Appointment appointment2 = new Appointment(3, patient2, LocalDate.of(2024,12,12));

                    PatientRepository patientRepository = new PatientRepository();
                    AppointmentRepository appointmentRepository = new AppointmentRepository();
                    DentalService dentalService = new DentalService(patientRepository,appointmentRepository);
                    dentalService.addPatient("Charles", 16, "Monaco", 1616);
                    dentalService.addPatient("Lewis", 44, "London", 4444);
                    dentalService.addPatient("Max", 11, "Amsterdam", 1111);
                    dentalService.addPatient("Laura", 123, "London", 1231);
                    dentalService.addPatient("Ana", 411, "Monaco", 341);
                    dentalService.addPatient("Cosmin", 3254, "London", 1243331);
                    dentalService.addAppointment(1, patient1, LocalDate.of(2024,01,01));
                    dentalService.addAppointment(2,patient1,LocalDate.of(2025,11,11));
                    dentalService.addAppointment(3,patient3,LocalDate.of(2025,02,02));
                    dentalService.addAppointment(4,patient4,LocalDate.of(2025,03,03));
                    dentalService.addAppointment(5, patient5, LocalDate.of(2024,11,11));
                    UI ui = new UI(dentalService);
                    ui.run();
                    break;
                case "textfile":
                    repoPatient = new PatientRepositoryTextFile(sourceNamePatient);
                    repoAppointment = new AppointmentRepositoryTextFile(sourceNameAppointment);
                    break;
                case "binaryfile":
                    repoPatient = new PatientRepositoryBinaryFile(sourceNamePatient);
                    repoAppointment = new AppointmentRepositoryBinaryFile(sourceNameAppointment);
                    break;
                case "database":
                    repoPatient = new PatientDatabaseRepository(sourceNamePatient);
                    repoAppointment = new AppointmentsDatabaseRepository(sourceNameAppointment);
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Patient patient2 = new Patient("Lewis", 44, "London", 4444);
        Appointment appointment = new Appointment(1,patient2,LocalDate.of(2023,11,11));
        repoPatient.addItem(patient2);
        repoAppointment.addItem(appointment);

        //test bianry files
//
//        Appointment appointment1 = new Appointment(16, patient1, LocalDate.of(2023, 12, 12));
//        Appointment appointment2 = new Appointment(33, patient2, LocalDate.of(2024, 05, 30));
//
//
//        IRepository<Patient, Integer> patientRepo = new PatientRepositoryBinaryFile("patients.bin");
//
//        try {
//            patientRepo.addItem(patient1);
//        }catch (DuplicateItemException e)
//        {
//            System.out.println("Item already exists");
//        }
//        try {
//            patientRepo.addItem(patient2);
//        }catch (DuplicateItemException e)
//        {
//            System.out.println("Item already exists");
//        }
//        for (Patient patient : patientRepo.getAllItems()) {
//            System.out.println(patient);
//        }

    }

}



