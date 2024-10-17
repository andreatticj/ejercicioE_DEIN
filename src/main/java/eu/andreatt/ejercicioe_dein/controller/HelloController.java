package eu.andreatt.ejercicioe_dein.controller;

import eu.andreatt.ejercicioe_dein.model.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/**
 * Controlador de la interfaz gráfica de la aplicación que gestiona una tabla de personas.
 * Este controlador permite agregar, modificar y eliminar personas en la tabla.
 */
public class HelloController {

    @FXML
    private Button btnAgregarPersona;  // Botón para agregar una nueva persona

    @FXML
    private Button btnEliminar;  // Botón para eliminar una persona seleccionada

    @FXML
    private Button btnModificar;  // Botón para modificar una persona seleccionada


    @FXML
    private TableView<Persona> tabla;  // Tabla que muestra la lista de personas

    @FXML
    private TableColumn<Persona, String> colNombre;  // Columna para mostrar el nombre de la persona

    @FXML
    private TableColumn<Persona, String> colApellido;  // Columna para mostrar el apellido de la persona

    @FXML
    private TableColumn<Persona, Integer> colEdad;  // Columna para mostrar la edad de la persona

    /**
     * Inicializa las columnas de la tabla, estableciendo los valores de las propiedades de los objetos Persona.
     */
    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colApellido.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
        colEdad.setCellValueFactory(cellData -> cellData.getValue().edadProperty().asObject());
    }

    /**
     * Evento que se dispara al hacer clic en el botón para agregar una nueva persona.
     * Abre una ventana modal para ingresar los datos de la nueva persona.
     *
     * @param event Evento de acción que ocurre al hacer clic en el botón
     */
    @FXML
    void agregarPersona(ActionEvent event) {
        ventanaModal("Nueva Persona");
    }

    /**
     * Evento que se dispara al hacer clic en el botón para eliminar una persona seleccionada.
     *
     * @param event Evento de acción que ocurre al hacer clic en el botón
     */
    @FXML
    void eliminar(ActionEvent event) {
        // Implementación para eliminar una persona de la tabla

    }

    /**
     * Evento que se dispara al hacer clic en el botón para modificar una persona seleccionada.
     * Abre una ventana modal para editar los datos de la persona seleccionada.
     *
     * @param event Evento de acción que ocurre al hacer clic en el botón
     */
    @FXML
    void modificar(ActionEvent event) {
        ventanaModal("Editar Persona");
    }

    /**
     * Método que abre una ventana modal para agregar o modificar una persona.
     * La ventana se carga desde un archivo FXML, y se pasa la lista de personas
     * al controlador del modal para que pueda gestionar los datos.
     *
     * @param titulo El título de la ventana modal.
     */
    private void ventanaModal(String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/eu/andreatt/ejercicioe_dein/fxml/modalE.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del modal
            ModalEController modalController = loader.getController();

            // Pasar la lista de personas al controlador del modal
            modalController.setPersonas(tabla.getItems());

            // Crear la nueva escena y la ventana modal
            Scene newScene = new Scene(root);
            Stage newStage = new Stage();
            newStage.initModality(Modality.APPLICATION_MODAL);  // Define la ventana como modal
            newStage.setResizable(false);  // No permite redimensionar la ventana
            newStage.setWidth(300);  // Establece el ancho de la ventana
            newStage.setHeight(200);  // Establece la altura de la ventana
            newStage.setScene(newScene);  // Asocia la escena con la ventana
            newStage.setTitle(titulo);  // Título de la ventana

            // Mostrar la ventana modal y esperar a que se cierre
            newStage.showAndWait();

            // Actualizar la tabla una vez que se cierra el modal
            tabla.refresh();

        } catch (IOException e) {
            // Muestra un mensaje de error si ocurre una excepción al cargar el modal
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
