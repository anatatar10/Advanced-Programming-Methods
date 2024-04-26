package repository;

import domain.Patient;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class PatientRepositoryBinaryFile extends FileRepository<Patient,Integer> {
    public PatientRepositoryBinaryFile(String filename) {
        super(filename);
    }

    @Override
    protected void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName)))
        {
            this.listOfItems = (Map<Integer, Patient>) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void writeFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName)))
        {
            oos.writeObject(this.listOfItems);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}