import domain.Patient;
import repository.Repository;
import service.Service;
import ui.UI;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Repository patientsRepository = new Repository();
        Service patientsService = new Service(patientsRepository);
        UI patientsUi = new UI(patientsService);
        patientsUi.run();
    }
    }
