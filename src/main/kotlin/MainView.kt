import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import javafx.concurrent.Task
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.image.Image
import javafx.stage.StageStyle
import tornadofx.*
import java.io.File
import kotlin.concurrent.thread

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

        vbox {

        hbox(alignment = Pos.CENTER) {

            button("저장 경로") {
                hboxConstraints { marginRight = 10.0 }
                addClass(Styles.targetButton)
                action { dir = chooseDirectory("저장할 경로를 선택해주세요.") }
            }

            button("다운로드") {
                addClass(Styles.startButton)
                action {
                    if (checkNullAlertMessage(dir)) {
                        DownloadManager( titleId.value.toInt(), startNumber.value.toInt(), endNumber.value.toInt(), dir.toString() ).download()

                    }
                }
            }

        }
            progressbar {
                vboxConstraints { marginTop = 10.0 }

                thread {
                    for (i in 1..100) {
                        Platform.runLater { progress = i.toDouble() / 100.0 }
                        Thread.sleep(100)
                    }
                }
            }
        }
    }

    fun checkNull(titleId: String?, startNumber: String?, endNumber: String?): Boolean {
        if((titleId != null && startNumber != null && endNumber != null) && (titleId != "" && startNumber != "" && endNumber != "")) return true

        return false
    }

    fun checkNullAlertMessage(dir: File?): Boolean {
        var alert = Alert(Alert.AlertType.ERROR)
        alert.setTitle("경고")
        alert.setHeaderText(null)
        alert.initStyle(StageStyle.UTILITY)

        if (!checkNull(titleId.value, startNumber.value, endNumber.value))  {
            alert.setContentText("빈칸을 채워주세요.")
            alert.showAndWait()
            return false
        } else {
            if (dir == null) {
                alert.setContentText("경로를 선택해주세요.")
                alert.showAndWait()
                return false
            }
        }
        return true
    }

    companion object {
        fun alertMessage(check: Int) {
            var alert = Alert(Alert.AlertType.ERROR)
            alert.setTitle("경고")
            alert.setHeaderText(null)
            alert.initStyle(StageStyle.UTILITY)
            when(check) {
                1 -> alert.setContentText("와이파이 연결이 필요합니다.")
            }
            alert.showAndWait()
        }
    }

}