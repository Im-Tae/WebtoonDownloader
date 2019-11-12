import javafx.scene.effect.DropShadow
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
        val targetButton by cssclass()
    }

    init {
        title {
            fontSize = 30.px
            fontWeight = FontWeight.BOLD
        }

        label {
            fontWeight = FontWeight.BOLD
            fontFamily = "Comic Sans MS"
        }

        startButton {
            fontWeight = FontWeight.BOLD
            fontFamily = "Comic Sans MS"
            fontSize = 15.px
            and(hover) {
                effect = DropShadow(10.0, Color.BLACK)
                backgroundColor += Color.RED
                textFill = Color.WHITE
            }
            prefWidth = 300.px
        }

        targetButton {
            fontWeight = FontWeight.BOLD
            fontFamily = "Comic Sans MS"
            fontSize = 15.px
            prefWidth = 180.px
            and(hover) {
                effect = DropShadow(10.0, Color.BLACK)
            }
        }

        textField {
            //backgroundColor += Color.RED
            borderWidth += box(3.px)
            //borderColor += box(Color.WHITE)
            //borderRadius += box(7.px)
        }
    }
}