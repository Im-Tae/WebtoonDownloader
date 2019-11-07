import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class MainView : View() {
    val titleId = SimpleStringProperty()

    override val root = form {
        fieldset {
            field("Input") {
                textfield(titleId)
            }

            button("Commit") {
                action {
                    if ((titleId.value) != null && (titleId.value) != "") {
                        information(titleId.value)
                    }
                }
            }
        }
    }
}