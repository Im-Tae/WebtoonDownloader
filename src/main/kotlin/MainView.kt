import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import tornadofx.*

class MainView : View() {
    val titleId = SimpleStringProperty()
    val startNumber = SimpleStringProperty()
    val endNumber = SimpleStringProperty()

    override val root = form {

        hbox(alignment = Pos.CENTER) { label("Webtoon Downloader") { addClass(Styles.title) } }

        fieldset {

            field("Title Id ") {
                textfield(titleId) {
                    promptText = "Title Id를 입력해주세요."
                }
            }
            field("Start Number") {
                textfield(startNumber) {
                    promptText = "Start Number를 입력해주세요."
                }
            }
            field("End Number") {
                textfield(endNumber) {
                    promptText = "End Number를 입력해주세요."
                }
            }
        }

            hbox(alignment = Pos.CENTER) { button("Start Download") { addClass(Styles.startButton); action { if ((titleId.value) != null && (titleId.value) != "") information(titleId.value) } } } }
    }