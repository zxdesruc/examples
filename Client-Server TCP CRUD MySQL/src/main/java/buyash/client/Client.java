package buyash.client;

import buyash.entity.Animal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private Socket socket;
    private OutputStreamWriter writer;
    private BufferedReader reader;
    private String host = "localhost";
    private int port = 8001;

    public List<Animal> getAnimals() throws IOException {
        socket = new Socket(host, port);
        writer = new OutputStreamWriter(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer.write("get\n");
        writer.flush();
        List<Animal> animals = new ArrayList<>();
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] data = reader.readLine().split(" ");
            Animal animal = Animal.buildAnimal(data);
            animals.add(animal);
        }
        reader.close();
        writer.close();
        socket.close();
        return animals;
    }


    public void addAnimal(Animal animal) throws IOException {
        socket = new Socket(host, port);
        writer = new OutputStreamWriter(socket.getOutputStream());
        writer.write("add " + animal.toString() + "\n");
        writer.flush();
        writer.close();
        socket.close();
    }

    public void deleteAnimal(Animal animal) throws IOException {
        socket = new Socket(host, port);
        writer = new OutputStreamWriter(socket.getOutputStream());
        writer.write("delete " + animal.toString() + "\n");
        writer.flush();
        writer.close();
        socket.close();
    }

}
