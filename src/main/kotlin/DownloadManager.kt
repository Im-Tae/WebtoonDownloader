import org.jsoup.Jsoup
import tornadofx.runAsync
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.UnknownHostException

class DownloadManager( private var titleId: Int, private var startNumber: Int, private var endNumber: Int, private var dir: String ) {

    private var saveDir: String? = null
    private var url:String? = null

     fun download() {

        for (i in startNumber..endNumber) {
            url = "http://comic.naver.com/webtoon/detail.nhn?titleId=$titleId&no=$i"

            val createFolder = File(dir, "$i" + "화")

            if(!createFolder.exists()) createFolder.mkdir()

            saveDir = dir + File.separator + i + "화"
            getImageSource(url!!, saveDir!!)

            if (getImageSource(url!!, saveDir!!)) { println("${i}화 다운 완료!") }
        }
         MainView.noticeMessage("downloadSuccess")
    }
    private fun getImageSource(url: String, dir: String): Boolean {

        try {
            val doc = Jsoup.connect(url).get()
            val images = doc.select(".wt_viewer > img")

            for (i in 0 until images.size) {
                val src = images[i].attr("src")

                runAsync {
                    downloadImage(url, dir, src, i + 1)
                }

                println("현재 총 " + images.size + "개 중 " + (i + 1) + "개 다운로드 완료")
            }
            return true
        } catch (e: UnknownHostException) {
            MainView.noticeMessage("wifiError")
        }
        return false
    }


    private fun downloadImage(url: String, dir: String, src: String, page: Int) {

        try {
            val idx = src.lastIndexOf(".")
            val ext = src.substring(idx)

            val saveFile = File(dir + File.separator + page + ext)
            val fileOutputStream = FileOutputStream(saveFile)

            val connection = URL(src).openConnection() as HttpURLConnection
            connection.connectTimeout = 40000
            connection.requestMethod = "GET"
            connection.setRequestProperty("Referer", url)
            connection.setRequestProperty("User-Agent", "Mozilla/5.0")
            val inputStream = connection.inputStream

            val buffer = ByteArray(1024 * 1024)
            var len: Int
            while (true) {
                len = inputStream.read(buffer)
                if (len <= 0) {
                    break
                }
                fileOutputStream.write(buffer, 0, len)
            }
            fileOutputStream.close()
            inputStream.close()

        } catch (e: Exception) {
            println(page.toString() + "번 이미지 다운중 오류 발생")
        }
    }

}