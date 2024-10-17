package eu.andreatt.ejercicioe_dein.controller;

import eu.andreatt.ejercicioe_dein.model.Persona;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Controlador de la ventana modal para agregar una nueva persona.
 * Permite gestionar la información ingresada y agregarla a una lista de personas,
 * o cancelar la operación y cerrar la ventana.
 */
public class modalEController {

    @FXML
    private Button btnCancelar;  // Botón para cancelar la operación

    @FXML
    private Button btnGuardar;  // Botón para guardar una nueva persona

    @FXML
    private TextField txtApellido;  // Campo de texto para ingresar el apellido

    @FXML
    private TextField txtEdad;  // Campo de texto para ingresar la edad

    @FXML
    private TextField txtNombre;  // Campo de texto para ingresar el nombre

    private Persona persona;  // Objeto Persona que se creará a partir de los datos ingresados
    private ObservableList<Persona> personas;  // Lista observable que contiene las personas

    /**
     * Establece la lista de personas donde se añadirá la nueva persona.
     *
     * @param personas Lista observable de personas.
     */
    public void setPersonas(ObservableList<Persona> personas) {
        this.personas = personas;
    }

    /**
     * Maneja el evento de cancelar y no añadir persona a la lista.
     * Cierra la ventana.
     *
     * @param event El evento que desencadena la acción.
     */
    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();  // Obtiene la ventana actual
        stage.close();  // Cierra la ventana
    }

    /**
     * Maneja el evento de guardar una nueva persona.
     * Verifica que los datos ingresados sean válidos antes de agregar la persona a la lista.
     * Si los datos son válidos, agrega la persona y cierra la ventana.
     *
     * @param event El evento que desencadena la acción.
     */
    @FXML
    void guardar(ActionEvent event) {
        Window win = ((Button) event.getSource()).getScene().getWindow();  // Obtiene la ventana actual
        String errores = verificarInfo();  // Verifica la validez de la información ingresada

        if (errores.isEmpty()) {  // Si no hay errores
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            int edad = Integer.parseInt(txtEdad.getText());

            Persona nuevaPersona = new Persona(nombre, apellido, edad);  // Crea una nueva persona

            // Verificar si la persona ya existe en la lista
            if (personas.contains(nuevaPersona)) {
                mostrarAlertError(win, "Esa persona ya existe");  // Muestra alerta si la persona ya está en la lista
                limpiarCampos();  // Limpia los campos de texto
            } else {
                personas.add(nuevaPersona);  // Agrega la nueva persona a la lista
                limpiarCampos();  // Limpia los campos de texto
                Stage stage = (Stage) btnCancelar.getScene().getWindow();  // Cierra la ventana actual
                stage.close();
            }
        } else {
            mostrarAlertError(win, errores);  // Muestra errores si los hay
        }
    }

    /**
     * Muestra una alerta de error con un mensaje específico.
     *
     * @param win   La ventana sobre la que se mostrará la alerta.
     * @param error El mensaje de error a mostrar.
     */
    private void mostrarAlertError(Window win, String error) {
        mostrarAlert(win, Alert.AlertType.ERROR, "ERROR", error);  // Llama al método general para mostrar alerta de error
    }

    /**
     * Muestra una alerta de acuerdo al tipo, título y contenido especificados.
     *
     * @param win       La ventana sobre la que se mostrará la alerta.
     * @param alertType El tipo de alerta a mostrar.
     * @param title     El título de la alerta.
     * @param content   El contenido de la alerta.
     */
    private void mostrarAlert(Window win, Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);  // Crea una nueva alerta
        alert.initOwner(win);  // Establece la ventana principal
        alert.setHeaderText(null);  // Sin encabezado
        alert.setTitle(title);  // Establece el título de la alerta
        alert.setContentText(content);  // Establece el contenido de la alerta
        alert.showAndWait();  // Muestra la alerta y espera a que el usuario la cierre
    }

    /**
     * Devuelve el objeto Persona creado a partir de los datos ingresados.
     *
     * @return El objeto Persona.
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * Verifica la validez de la información ingresada en los campos de texto.
     * Verifica que los campos no estén vacíos y que la edad sea un número válido.
     *
     * @return Una cadena con los errores encontrados, o una cadena vacía si no se encuentran errores.
     */
    private String verificarInfo() {
        StringBuilder errores = new StringBuilder();

        if (txtNombre.getText().isEmpty()) {
            errores.append("El campo Nombre es obligatorio.\n");
        }

        if (txtApellido.getText().isEmpty()) {
            errores.append("El campo Apellido es obligatorio.\n");
        }

        if (txtEdad.getText().isEmpty()) {
            errores.append("El campo Edad es obligatorio.\n");
        } else {
            try {
                Integer.parseInt(txtEdad.getText());  // Intenta convertir el campo edad a un número
            } catch (NumberFormatException e) {
                errores.append("El campo Edad debe ser un número válido.\n");  // Agrega un error si no es un número válido
            }
        }

        return errores.toString();  // Retorna la cadena de errores
    }

    /**
     * Limpia los campos de texto del formulario.
     */
    private void limpiarCampos() {
        txtNombre.clear();  // Limpia el campo de nombre
        txtApellido.clear();  // Limpia el campo de apellido
        txtEdad.clear();  // Limpia el campo de edad
    }
}
