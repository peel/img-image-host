import akka.event.Logging
import akka.http.Http
import akka.actor.ActorSystem
import akka.http.model.Multipart.FormData
import akka.http.server.Directives
import akka.stream.FlowMaterializer

class ImgHost(interface: String, port: Int)(implicit system: ActorSystem) extends Directives{
  import system.dispatcher

  private val log = Logging(system, getClass)

  private implicit val flow = FlowMaterializer()

  def run(): Unit = {
    Http(system).bind(interface,port).startHandlingWith(route)
  }

  private def route =
    path("images"){
      post {
        entity(as[FormData]){data =>
          complete{
            //TODO: set image id
            //TODO: send to S3 upload actor
            s"uploaded: ${data.hashCode}"
          }
        }
      }
    } ~
    path("image" / RestPath) { imageId =>
      get {
       complete {
        //TODO: send to S3 download actor
         s"downloaded: $imageId"
       }
      }
    }
}
