import domain.Appointment;
import domain.Patient;
import exceptions.DuplicateItemException;
import exceptions.ItemNotFound;
import repository.*;

import java.io.FileReader;
import java.io.IOException;

import java.time.LocalDate;
import java.util.Properties;



// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws DuplicateItemException, ItemNotFound {
//        IRepository<Patient, Integer> repoPatient = null;
//        IRepository<Appointment, Integer> repoAppointment = null;
//
//        try (FileReader fr = new FileReader("settings.properties"))
//        {
//            Properties props = new Properties();
//            props.load(fr);
//
//            String repoType = props.getProperty("repositoryType");
//            String sourceNamePatient = props.getProperty("repositoryPatientsLocation");
//            String sourceNameAppointment = props.getProperty("repositoryAppointmentsLocation");
//            switch (repoType)
//            {
//                case "inmemory":
//                    repoPatient = new PatientRepository();
//                    repoAppointment = new AppointmentRepository();
//                    break;
//                case "textfile":
//                    repoPatient = new PatientRepositoryTextFile(sourceNamePatient);
//                    repoAppointment = new AppointmentRepositoryTextFile(sourceNameAppointment);
//                    break;
//                case "binaryfile":
//                    repoPatient = new PatientRepositoryBinaryFile(sourceNamePatient);
//                    repoAppointment = new AppointmentRepositoryBinaryFile(sourceNameAppointment);
//                    break;
//
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        Patient patient1 = new Patient("Charles", 16, "Monaco", 1616);
//        Patient patient2 = new Patient("Lewis", 44, "London", 4444);
//        try {
//            repoPatient.addItem(patient1);
//        } catch (DuplicateItemException e) {
//            throw new RuntimeException(e);
//        }
//
//        for (Patient patient: repoPatient.getAllItems())
//            System.out.println(patient);

       // test bianry files
        Patient patient1 = new Patient("Charles", 16, "Monaco", 1616);
        Patient patient2 = new Patient("Lewis", 44, "London", 4444);
        Appointment appointment1 = new Appointment(16, patient1, LocalDate.of(2023, 12, 12));
        Appointment appointment2 = new Appointment(33, patient2, LocalDate.of(2024, 05, 30));


        IRepository<Appointment, Integer> appointmentRepositoryBinaryFile = new AppointmentRepositoryBinaryFile("appointments.txt");

//        try {
//            appointmentRepositoryBinaryFile.addItem(appointment1);
//        }catch (DuplicateItemException e)
//        {
//            System.out.println("Item already exists");
//        }
//        try {
//            appointmentRepositoryBinaryFile.addItem(appointment2);
//        }catch (DuplicateItemException e)
//        {
//            System.out.println("Item already exists");
//        }
            for(Appointment appointment: appointmentRepositoryBinaryFile.getAllItems())
            {
                System.out.println(appointment);
            }

        }

    }



