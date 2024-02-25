import processing.core._
import scala.math

class Mandelbrot extends PApplet {
    override def settings(): Unit = {
        size(1920, 1080)
    }

    override def draw(): Unit = {
        noLoop()
        background(255)
        loadPixels()

        var w: Float = 4
        var h: Float = (w * height) / width

        var xmin: Float = -w/2
        var ymin: Float = -h/2

        var maxIterations = 100

        var xmax = xmin + w
        var ymax = ymin + h

        var dx = (xmax - xmin) / width
        var dy = (ymax - ymin) / height

        var y: Float = ymin

        for (j: Int <- 0 to height - 1) {
            var x = xmin

            for (i: Int <- 0 to width - 1) {
                var a = x
                var b = y
                var n = 0
                var max: Float = 4.0
                var absOld: Float = 0.0
                var convergeNumber: Float = maxIterations

                var break: Boolean = false
                while (n < maxIterations && !break) {
                    var aa: Float = a * a
                    var bb: Float = b * b
                    var abs: Float = math.sqrt(aa + bb).toFloat

                    if (abs > max) {
                        var diffToLast: Float = (abs - absOld)
                        var diffToMax: Float = (max - absOld)
                        convergeNumber = n + diffToMax / diffToLast
                        break = true
                    } else {
                        var twoab: Float = (2.0 * a * b).toFloat
                        a = aa - bb + x
                        b = twoab + y
                        n += 1
                        absOld = abs
                    }
                }

                if (n == maxIterations) {
                    pixels(i + j * width) = color(0)
                } else {
                    var norm: Float = PApplet.map(convergeNumber, 0, maxIterations, 0, 1)
                    pixels(i + j * width) = color(PApplet.map(math.sqrt(norm).toFloat, 0, 1, 0, 255))
                }
                x += dx
            }
            y += dy
        }
        updatePixels()
    }
}

@main
def Mandelbrot_main(): Unit = {
    PApplet.main("Mandelbrot")
}
