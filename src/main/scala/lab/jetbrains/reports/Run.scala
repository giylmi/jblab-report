package lab.jetbrains.reports

import java.io.FileInputStream
import java.util.Properties

import scalaj.http.{HttpRequest, Http}

/**
 * Created by a.gilmullin on 15.06.2015.
 */
class Run {

    def main (args: Array[String]) {

        print("Loading properties...")
        val properties = new Properties()
        properties.load(new FileInputStream("setting.properties"))

        val baseUrl: String = properties.getProperty("base_url")
        //auth
        print("Authenticating...")
        val response: HttpRequest = Http(baseUrl).postForm(
            Seq("login" -> properties.getProperty("login"),
                "password" -> properties.getProperty("password")))

        val arguments: Map[String, String] =
            args.filter(s => s.startsWith("--"))
                .map(s => {
                    val pos = s.indexOf('=')
                    (s.substring(2, if (pos == -1) s.length else pos), if (pos == -1) "enabled" else s.substring(pos + 1))
                }).toMap
        
    }
}
