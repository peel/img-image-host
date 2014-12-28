import scopt.OptionParser
import akka.actor.ActorSystem

object ImgHostApp {

  private case class Conf(interface: String = FALLBACK_INTERFACE, port: Int = FALLBACK_PORT)
  private val FALLBACK_INTERFACE = "127.0.0.1"
  private val FALLBACK_PORT = 8080


  private val parser = new OptionParser[Conf]("img-host"){
    head("img-host", "1.x")
    opt[String]('i',"interface")
      .text(s"sets host interface; default: $FALLBACK_INTERFACE")
      .optional()
      .action{(interface, conf)=>
        conf.copy(interface=interface)
      }
    opt[Int]('p',"port")
      .text(s"sets port; default: $FALLBACK_PORT")
      .optional()
      .action{(port, conf)=>
        conf.copy(port = port)
      }
    help("help")
      .text("prints this usage text")
  }

  def main(args: Array[String]):Unit={
    for (conf <- parser.parse(args, Conf())) {
      val system = ActorSystem("echo")
      new ImgHost(conf.interface, conf.port)(system).run()
      system.awaitTermination()
    }
  }
}
