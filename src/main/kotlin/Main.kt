import javafx.stage.Stage
import tornadofx.App
import tornadofx.FX
import tornadofx.FX.Companion.primaryStage
import tornadofx.launch
import tornadofx.reloadStylesheetsOnFocus

class Main : App(MainView::class, Styles::class) {

    init { reloadStylesheetsOnFocus()  }

    override fun start(stage: Stage) {
        stage.width = 500.0
        stage.height = 250.0
        stage.isResizable = false
        super.start(stage)
        FX.registerApplication(this, primaryStage)
    }
}


fun main(args: Array<String>) = launch<Main>(args)