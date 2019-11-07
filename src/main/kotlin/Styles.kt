import javafx.scene.paint.Color
import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.px

class Styles : Stylesheet() {

    init {
        button {
            and(hover) {
                backgroundColor += Color.RED
            }
        }

        Companion.textField {
            //backgroundColor += Color.RED
            borderColor += box(Color.RED)
            borderRadius += box(7.px)
        }

        textField {

        }
    }
}