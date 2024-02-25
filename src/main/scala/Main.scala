import processing.core.{PApplet}

class Transflag extends PApplet {

    override def settings(): Unit = {
        size(960, 540)
    }

    override def setup(): Unit = {
        background(0, 0, 0)
    }

    override def draw(): Unit = {
        noStroke()

        val fifth: Int = (height/5).toInt

        fill(91,  206,     250)
        rect(0,   0,       width, fifth)
        rect(0,   fifth*4, width, fifth)

        fill(245, 169,     184)
        rect(0,   fifth,   width, fifth)
        rect(0,   fifth*3, width, fifth)

        fill(255, 255,     255)
        rect(0,   fifth*2, width, fifth)
    }
}

@main
def transflag_main() = {
    PApplet.main("Transflag")
}
