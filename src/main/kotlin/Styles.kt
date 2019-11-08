import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px

class Styles : Stylesheet() {

    companion object {
        val title by cssclass()
        val startButton by cssclass()
    }

    init {
        title {
            fontSize = 30.px
            fontWeight = FontWeight.BOLD
        }

        label {
            fontWeight = FontWeight.BOLD
        }

        startButton {
           and(hover) {
                backgroundColor += Color.RED
            }
            prefWidth = 500.px
        }

        textField {
            //backgroundColor += Color.RED
            borderWidth += box(3.px)
            //borderColor += box(Color.WHITE)
            //borderRadius += box(7.px)
        }
    }
}