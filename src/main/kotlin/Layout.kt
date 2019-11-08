import tornadofx.*

class Layout : View() {

    companion object {
    }

    override val root = vbox {

        primaryStage.sizeToScene()
    }
}