package buyash.controller;

import buyash.client.Client;
import buyash.entity.Animal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class Controller {
    private Client client = new Client();

    @FXML
    private Label label;
    @FXML
    private Button add_button;

    @FXML
    private TableColumn<?, ?> age_column;

    @FXML
    private TextField age_field;

    @FXML
    private TableColumn<?, ?> color_column;

    @FXML
    private TextField color_field;

    @FXML
    private TableColumn<?, ?> country_column;

    @FXML
    private TextField country_field;

    @FXML
    private Button delete_button;

    @FXML
    private TableColumn<?, ?> id_column;

    @FXML
    private TextField id_field;

    @FXML
    private TableColumn<?, ?> name_column;

    @FXML
    private TextField name_field;

    @FXML
    private Button save_button;

    @FXML
    private Button show_button;

    @FXML
    private TableColumn<?, ?> species_column;

    @FXML
    private TableColumn<?, ?> sex_column;

    @FXML
    private TextField species_field;

    @FXML
    private TextField sex_field;

    @FXML
    private TableView<Animal> table_view;

    private ObservableList<Animal> observableAnimals;

    @FXML
    void initialize() {
        country_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                country_field.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        sex_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                sex_field.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        name_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                name_field.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        color_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                color_field.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        species_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                species_field.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        age_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                age_field.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        id_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                id_field.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    void show(ActionEvent event) throws IOException {
        List<Animal> animals = client.getAnimals();
        observableAnimals = FXCollections.observableArrayList();
        observableAnimals.addAll(animals);
        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        color_column.setCellValueFactory(new PropertyValueFactory<>("color"));
        age_column.setCellValueFactory(new PropertyValueFactory<>("age"));
        country_column.setCellValueFactory(new PropertyValueFactory<>("country"));
        species_column.setCellValueFactory(new PropertyValueFactory<>("species"));
        sex_column.setCellValueFactory(new PropertyValueFactory<>("sex"));
        table_view.setItems(observableAnimals);
        delete_button.setDisable(false);
    }

    @FXML
    void save(ActionEvent event) throws IOException {
        if (name_field.getText().equals("") || country_field.getText().equals("") || sex_field.getText().equals("") || age_field.getText().equals("") || id_field.getText().equals("") || species_field.getText().equals("")) {
            label.setText("Please enter data");
        }
    else { Animal animal = new Animal();
            animal.setId(Integer.parseInt(id_field.getText()));
            animal.setName(name_field.getText());
            animal.setColor(color_field.getText());
            animal.setAge(Integer.parseInt(age_field.getText()));
            animal.setCountry(country_field.getText());
            animal.setSpecies(species_field.getText());
            animal.setSex(sex_field.getText());
            client.addAnimal(animal);
            observableAnimals.add(animal);
            save_button.setDisable(true);
            add_button.setDisable(false);
            id_field.clear();
            name_field.clear();
            age_field.clear();
            color_field.clear();
            country_field.clear();
            species_field.clear();
            sex_field.clear();
            id_field.setDisable(true);
            name_field.setDisable(true);
            age_field.setDisable(true);
            color_field.setDisable(true);
            country_field.setDisable(true);
            species_field.setDisable(true);
            sex_field.setDisable(true);
            label.setText("");}
    }

    @FXML
    void delete(ActionEvent event) throws IOException {
        Animal animal = table_view.getSelectionModel().getSelectedItem();
        observableAnimals.remove(animal);
        client.deleteAnimal(animal);
        table_view.refresh();
    }

    @FXML
    void add(ActionEvent event) {
        save_button.setDisable(false);
        id_field.setDisable(false);
        name_field.setDisable(false);
        age_field.setDisable(false);
        color_field.setDisable(false);
        country_field.setDisable(false);
        add_button.setDisable(true);
        species_field.setDisable(false);
        sex_field.setDisable(false);
    }

}