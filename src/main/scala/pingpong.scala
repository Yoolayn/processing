import processing.core._
import scala.util.Random

val RADIUS = 30

def get_rand(start: Int, stop: Int): Int = {
    start + Random.nextInt((stop - start) + 1)
}

class PingPong extends PApplet {

    var ball = Ball(this)

    override def settings(): Unit = {
        size(800, 600)
    }

    override def setup(): Unit = {
        background(0, 0, 0)
        ball = Ball(
            this,
            width/2,
            height/2,
            get_rand(-50, 50),
            get_rand(-50, 50),
            0.90
        )
    }

    override def draw(): Unit = {
        clear()

        fill(255, 255, 255)
        rect(0, 0, 10, height)
        rect(width - 10, 0, 10, height)
        fill(126, 33, 206)

        ball.move()
        ball.draw()
    }
}

class Ball(
    pa: PApplet,
    pX: Int,
    pY: Int,
    spX: Double,
    spY: Double,
    entropy: Double
) extends PApplet {

    var posX = pX
    var posY = pY
    var speedX = spX
    var speedY = spY

    def this(pa: PApplet) = this(pa, 0, 0, 0, 0, 1)
    def this(pa: PApplet, pX: Int, pY: Int) = this(pa, pX, pY, 0, 0, 1)

    def move(): Unit = {
        collision()
        posX += speedX.toInt
        posY += speedY.toInt
    }

    def collision(): Unit = {
        if (posX > pa.width - RADIUS/2 - 10 || posX < 0 + RADIUS/2 + 10) {
            if (posX > pa.width - RADIUS/2 - 10) {
                posX = pa.width - RADIUS/2 - 10
            } else {
                posX = 0 + RADIUS/2 + 10
            }
            speedX *= -1
            speedX = math.ceil(speedX * entropy)
        }
        if (posY > pa.height - RADIUS/2 || posY < 0 + RADIUS/2) {
            if (posY > pa.height - RADIUS/2) {
                posY = pa.height - RADIUS/2
            } else {
                posY = 0 + RADIUS/2
            }
            speedY *= -1
            speedY = math.ceil(speedY * entropy)
        }
    }

    override def draw(): Unit = {
        pa.ellipse(posX, posY, RADIUS, RADIUS)
    }
}

@main
def pingpong_main() = {
    PApplet.main("PingPong")
}
