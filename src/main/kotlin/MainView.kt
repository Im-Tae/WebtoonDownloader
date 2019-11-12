import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.image.Image
import javafx.stage.StageStyle
import tornadofx.*
import java.io.File

class MainView : View() {
    val titleId = SimpleStringProperty()
    val startNumber = SimpleStringProperty()
    val endNumber = SimpleStringProperty()
    var dir: File? = null

    override val root = form {

        title = " Webtoon Downloader"
        setStageIcon(Image("logo.png"))


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

        hbox(alignment = Pos.CENTER) {

                button("Target Directory") {
                    hboxConstraints { marginRight = 10.0 }
                    addClass(Styles.targetButton)
                    action { dir = chooseDirectory("Select Target Directory") }
                }

                button("Start Download") {
                    addClass(Styles.startButton)
                    action {
                        alertMessage(dir)
                        if (checkNotnull(titleId.value, startNumber.value, endNumber.value)) {
                            DownloadManager(titleId.value.toInt(), startNumber.value.toInt(), endNumber.value.toInt())
                        }
                    }
                }
            }
        }

    fun checkNotnull(titleId: String?, startNumber: String?, endNumber: String?): Boolean {
        if((titleId != null && startNumber != null && endNumber != null) && (titleId != "" && startNumber != "" && endNumber != "")) return true

        return false
    }

    fun alertMessage(dir: File?) {
        var alert = Alert(Alert.AlertType.ERROR)
        alert.setTitle("Warning")
        alert.setHeaderText(null)
        alert.initStyle(StageStyle.UTILITY)

        if (!checkNotnull(titleId.value, startNumber.value, endNumber.value))  {
            alert.setContentText("Please fill in the blank")
            alert.showAndWait()
        } else {
            if (dir == null) {
                alert.setContentText("Please select the directory")
                alert.showAndWait()
            }
        }
    }
}