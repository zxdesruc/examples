package buyash.server;

import buyash.entity.Animal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/animals?characterEncoding=" +
            "UTF-8&useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT&allowPublicKeyRetrieval=true";

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        ServerSocket server = new ServerSocket(8001);
        System.out.println("Ready for clients");
        while (true) {
            Socket socket = server.accept();
            System.out.println(socket.getInetAddress().getHostName() + " connected");
            ServerThread thread = new ServerThread(socket,
                    DriverManager.getConnection(DATABASE_URL, "root", "root"));
            thread.start();
        }
    }
}

class ServerThread extends Thread {

    private OutputStreamWriter writer;
    private BufferedReader reader;
    private Connection databaseConnection;
    private Socket client;
    private static final String GET_QUERY = "SELECT * FROM animals";
    private static final String DELETE_QUERY = "DELETE FROM animals WHERE id = ?";
    private static final String ADD_QUERY = "INSERT INTO animals(id, name, age, color, country,species, sex) VALUES(?, ?, ?, ?, ?, ?, ?)";

    public ServerThread(Socket client, Connection databaseConnection) throws IOException {
        writer = new OutputStreamWriter(client.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.databaseConnection = databaseConnection;
        this.client = client;
    }

    public void run() {
        try {
            System.out.println(this.getName());
            String[] data = reader.readLine().split(" ");
            String command = data[0];
            Animal animal = new Animal();
            if (data.length > 1) {
                animal = Animal.buildAnimal(Arrays.copyOfRange(data, 1, 9));
            }

            System.out.println("Executing: " + command + " command...");
            switch (command) {
                case "add":
                    add(animal);
                    writer.write("Executed successfully\n");
                    break;
                case "delete":
                    delete(animal);
                    writer.write("Executed successfully\n");
                    break;
                case "get":
                    List<Animal> animals = getAnimals();
                    writer.write(animals.size() + "\n");
                    writer.flush();

                    for (Animal s : animals) {
                        writer.write(s.toString() + "\n");
                        writer.flush();
                    }
                    break;
            }
            System.out.println("Executed successfully");
            client.close();
        } catch (IOException | SQLException e) {
            System.out.println("Failed to execute command" + e.getMessage());
        }
    }

    private List<Animal> getAnimals() throws SQLException {
        List<Animal> animals = new ArrayList<>();
        PreparedStatement statement = databaseConnection.prepareStatement(GET_QUERY);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Animal animal = new Animal();
            animal.setId(resultSet.getInt("id"));
            animal.setName(resultSet.getString("name"));
            animal.setAge(resultSet.getInt("age"));
            animal.setColor(resultSet.getString("color"));
            animal.setCountry(resultSet.getString("country"));
            animal.setSpecies(resultSet.getString("species"));
            animal.setSex(resultSet.getString("sex"));
            animals.add(animal);
        }
        return animals;
    }

    private void delete(Animal animal) throws SQLException {
        PreparedStatement statement = databaseConnection.prepareStatement(DELETE_QUERY);
        statement.setInt(1, animal.getId());
        statement.executeUpdate();
    }

    private void add(Animal animal) throws SQLException {
        PreparedStatement statement = databaseConnection.prepareStatement(ADD_QUERY);
        statement.setInt(1, animal.getId());
        statement.setString(2, animal.getName());
        statement.setInt(3, animal.getAge());
        statement.setString(4, animal.getColor());
        statement.setString(5, animal.getCountry());
        statement.setString(6, animal.getSpecies());
        statement.setString(7, animal.getSex());
        statement.executeUpdate();
    }
}


