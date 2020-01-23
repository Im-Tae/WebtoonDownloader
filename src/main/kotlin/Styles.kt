import javafx.scene.effect.DropShadow
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import org.w3c.dom.css.RGBColor
import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px

class Styles : Stylesheet() {

    companion object {
        val title1 by cssclass()
        val startButton by cssclass()
        val targetButton by cssclass()
        val title2 by cssclass()
    }

    init {
        title1 {
            fontSize = 30.px
            fontWeight = FontWeight.BOLD
        }

        title2 {
            fontSize = 35.px
            fontWeight = FontWeight.BOLD
            textFill = Color.rgb(3,223,101)
        }

        label {
            fontWeight = FontWeight.BOLD
        }

        startButton {
            fontWeight = FontWeight.BOLD
            fontFamily = "Comic Sans MS"
            fontSize = 15.px
            and(hover) {
                effect = DropShadow(10.0, Color.BLACK)
                //backgroundColor += Color.RED
                //textFill = Color.WHITE
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