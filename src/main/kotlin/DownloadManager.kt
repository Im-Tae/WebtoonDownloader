import org.jsoup.Jsoup
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
            if(!createFolder.exists())
                createFolder.mkdir()

            saveDir = dir + File.separator + i + "화"
            getImageSource(url!!, saveDir!!)
        }


    }
    companion object {

        fun getImageSource(url: String, dir: String) {
            try {
                val doc = Jsoup.connect(url).get()
                val imgs = doc.select(".wt_viewer > img")

                for (i in 0 until imgs.size) {
                    val src = imgs[i].attr("src")


                    downloadImage(url, dir, src, i + 1)

                    System.out.println("현재 총 " + imgs.size + "개 중 " + (i + 1) + "개 다운로드 완료")
                }
            } catch (e: UnknownHostException) {
                MainView.alertMessage(1)
            }
        }

        fun downloadImage(url: String, dir: String, src: String, page: Int) {
            try {
                val idx = src.lastIndexOf(".")
                val ext = src.substring(idx)

                val saveFile = File(dir + File.separator + page + ext)
                val fileOutputStream = FileOutputStream(saveFile)

                val connection = URL(src).openConnection() as HttpURLConnection
                connection.setConnectTimeout(40000)
                connection.setRequestMethod("GET")
                connection.setRequestProperty("Referer", url)
                connection.setRequestProperty("User-Agent", "Mozilla/5.0")
                val inputStream = connection.getInputStream()

                val buffer = ByteArray(1024 * 1024)
                var len = 0
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




}