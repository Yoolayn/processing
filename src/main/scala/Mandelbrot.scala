import processing.core._
import scala.math

class Mandelbrot extends PApplet {

    val maxIterations: Int = 100
    val max: Float = 4.0

    override def settings(): Unit = {
        size(1920, 1080)
    }

    override def draw(): Unit = {
        noLoop()
        background(255)
        loadPixels()

        val w: Float = 4
        val h: Float = (w * height) / width

        val xmin: Float = -w/2
        val ymin: Float = -h/2

        val xmax = xmin + w
        val ymax = ymin + h

        val dx = (xmax - xmin) / width
        val dy = (ymax - ymin) / height

        def fillPixels(): Unit = {
            @annotation.tailrec
            def helper(y: Float, iter: Int): Unit = {
                if (iter > height - 1) return
                forLoop(xmin, y, iter, dx)
                helper(y + dy, iter + 1)
            }
            helper(ymin, 0)
        }

        fillPixels()
        updatePixels()
    }

    def forLoop(x: Float, y: Float, outerIter: Int, dx: Float): Unit = {
        @annotation.tailrec
        def helper(iter: Int, x: Float): Unit = {
            if (iter > width - 1) return
            val (n, convergeNumber) = while_loop(x, y)

            if (n == maxIterations) {
                pixels(iter + outerIter * width) = color(0)
            } else {
                val norm: Float = PApplet.map(convergeNumber, 0, maxIterations, 0, 1)
                pixels(iter + outerIter * width) = color(PApplet.map(math.sqrt(norm).toFloat, 0, 1, 0, 255))
            }
            helper(iter + 1, x + dx)
        }
        helper(0, x)
    }

    def while_loop(x: Float, y: Float): (Int, Float) = {
        @annotation.tailrec
        def helper(a: Float, b: Float, n: Int, absOld: Float): (Int, Float) = {
            val aa: Float = a * a
            val bb: Float = b * b
            val abs: Float = math.sqrt(aa + bb).toFloat

            if (abs > max) {
                val diffToLast: Float = (abs - absOld)
                val diffToMax: Float = (max - absOld)
                val newConvNum = n + diffToMax / diffToLast
                (n, newConvNum)
            } else if (n < maxIterations) {
                val twoab: Float = (2.0 * a * b).toFloat
                val newA = aa - bb + x
                val newB = twoab + y
                helper(newA, newB, n + 1, abs)
            } else {
                (n, maxIterations)
            }
        }
        helper(x, y, 0, 0.0)
    }

}

@main
def Mandelbrot_main(): Unit = {
    PApplet.main("Mandelbrot")
}
