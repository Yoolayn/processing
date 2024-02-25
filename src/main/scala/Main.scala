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

        fill(91, 206, 250) // blue
        rect(0, 0, 960, 108)
        rect(0, 432, 960, 108)

        fill(245, 169, 184) // pink
        rect(0, 108, 960, 108)
        rect(0, 324, 960, 108)

        fill(255, 255, 255) // white
        rect(0, 216, 960, 108)
    }
}

@main
def transflag_main() = {
    PApplet.main("Transflag")
}
