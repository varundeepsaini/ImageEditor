import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import java.awt.Desktop
import java.io.IOException
import kotlin.math.exp

fun main() {
    // Initialize the input image
    println("Welcome to my Image Editor")
    println("Enter the file path (Absolute or Relative to The Directory Where the Programme is running)")
    val inputImagePath = readln()
    val inputImage = ImageIO.read(File(inputImagePath))
    val width = inputImage.width
    val height = inputImage.height

    while (true) {
        println(
            """
            Enter the desired option (The number mapped to the action):
            1. Grayscale the Image
            2. Change Brightness
            3. Change Contrast
            4. Flip the Image
            5. Rotate the Image
            6. Blur the Image
            7. Apply Filters
            8. Exit The Programme
            """.trimIndent()
        )

        val optionSelectedByUser: String? = readlnOrNull()
        var outputImage: BufferedImage = inputImage

        when (optionSelectedByUser) {
            "1" -> {
                applyGrayscaleFilter(outputImage, inputImage, width, height)
            }
            "2" -> {
                println("Enter the brightness adjustment value (e.g., -50 to make it darker, 50 to make it brighter): ")
                val brightnessAdjustment = readlnOrNull()?.toInt() ?: 0
                changeBrightnessOfImage(outputImage, inputImage, brightnessAdjustment, width, height)
            }
            "3" -> {
                println("Enter the contrast adjustment value (e.g., -50 to make it darker, 50 to make it brighter): ")
                val contrastAdjustment = readlnOrNull()?.toInt() ?: 0
                changeContrastOfImage(outputImage, inputImage, contrastAdjustment, width, height)
            }
            "4" -> {
                println("How do you want to flip the Image ? [1. Horizontally | 2. Vertically]")
                val choiceOfFlip = readln().toInt()
                flipImage(outputImage, inputImage, choiceOfFlip, width, height)
            }
            "5" -> {
                println("How do you want to rotate the Image ? [1. Clockwise | 2. AntiClockwise]")
                val choiceOfRotate = readln().toInt()
                rotateImage(outputImage, inputImage, choiceOfRotate, width, height)
            }
            "6" -> {
                println("Enter blur intensity (e.g., 1 for light blur, 5 for strong blur)")
                val blurIntensity: Int = readln().toInt()
                blurImage(outputImage, inputImage, blurIntensity, width, height)
            }
            "7" -> {
                println(
                    """Select The Filters You Want to Apply on the Image
                    1. Vintage
                    2. CyberPunk
                    3. Funky
                    4. Invert Colors
                    5. Black and White
                    """.trimIndent()
                )
                val filterChoice: Int = readln().toInt()
                applyFilters(outputImage, inputImage,filterChoice, width, height)
            }
            "8" -> {
                println("Exiting the program.")
                break
            }
            else -> println("Invalid option. Please choose a valid operation.")
        }

        println("Enter the output file path (Absolute or Relative to The Directory Where the Programme is running)")
        val outputImagePath = readlnOrNull()

        ImageIO.write(outputImage, "jpg", outputImagePath?.let { File(it) })

        println("Result saved to $outputImagePath")

        println("Do you want to open the image in a viewer? [1. Yes | 2. No]")
        val wantToOpenImageInViewer = readln()
        if (wantToOpenImageInViewer == "1") {
            openImageInDefaultViewer(outputImagePath)
        }
    }
    println("Thank You for Using This Editor")
}

fun applyGrayscaleFilter(outputImage : BufferedImage,inputImage: BufferedImage, width: Int, height: Int) {

    for (x in 0..<width) {
        for (y in 0..<height) {
            val color = Color(inputImage.getRGB(x, y))
            val red = color.red
            val green = color.green
            val blue = color.blue
            val grayValue = (red + green + blue) / 3

            val grayColor = Color(grayValue, grayValue, grayValue)
            outputImage.setRGB(x, y, grayColor.rgb)
        }
    }
    print("Image Processing Successful. GrayScale Completed")
}


fun changeBrightnessOfImage(outputImage : BufferedImage,inputImage: BufferedImage, brightnessAdjustment: Int, width: Int, height: Int){

    for (x in 0..<width) {
        for (y in 0..<height) {
            val color = Color(inputImage.getRGB(x, y))

            // Adjust brightness
            val newRed = (color.red + brightnessAdjustment).coerceIn(0, 255)
            val newGreen = (color.green + brightnessAdjustment).coerceIn(0, 255)
            val newBlue = (color.blue + brightnessAdjustment).coerceIn(0, 255)

            val newColor = Color(newRed, newGreen, newBlue)
            outputImage.setRGB(x, y, newColor.rgb)
        }
    }
    println("Image Processing Completed. Brightness altered by a factor of $brightnessAdjustment .")
}

fun changeContrastOfImage(outputImage : BufferedImage,inputImage: BufferedImage, contrastAdjustment: Int, width: Int, height: Int) {

    for (x in 0..<width) {
        for (y in 0..<height) {
            val color = Color(inputImage.getRGB(x, y))

            // Adjust contrast
            val newRed = ((color.red - 128) * contrastAdjustment + 128).coerceIn(0, 255)
            val newGreen = ((color.green - 128) * contrastAdjustment + 128).coerceIn(0, 255)
            val newBlue = ((color.blue - 128) * contrastAdjustment + 128).coerceIn(0, 255)

            val newColor = Color(newRed, newGreen, newBlue)
            outputImage.setRGB(x, y, newColor.rgb)
        }
    }
    println("Image Processing Successful. Contrast Altered By a Factor Of $contrastAdjustment .")
}



fun flipImage(outputImage : BufferedImage,inputImage : BufferedImage, choiceOfFlip: Int, width: Int, height: Int) {

        for (x in 0..<width) {
            for (y in 0..<height) {
                val color = inputImage.getRGB(x, y)
                if (choiceOfFlip == 1){
                    outputImage.setRGB(width - x - 1, y, color)
                }else{
                    outputImage.setRGB(x, height - y - 1, color)
                }
            }
        }

}


fun rotateImage(outputImage : BufferedImage,inputImage : BufferedImage, choiceOfRotate : Int, width: Int, height: Int){

    for (x in 0..<width) {
        for (y in 0..<height) {
            val color = inputImage.getRGB(x, y)
            if (choiceOfRotate == 1){
                outputImage.setRGB(height - y - 1, x, color)
            }else{
                outputImage.setRGB(y, width - x - 1, color)
            }
        }
    }

}


fun blurImage(outputImage : BufferedImage,inputImage : BufferedImage,blurIntensity : Int,width : Int, height: Int): BufferedImage {
    val kernelSize = blurIntensity * 2 + 1
    val kernel = FloatArray(kernelSize)

    val sigma = blurIntensity / 3.0f
    val twoSigmaSquare = 2.0f * sigma * sigma
    val sigmaRoot = Math.sqrt(twoSigmaSquare * Math.PI).toFloat()

    var total = 0.0f

    for (x in -blurIntensity..blurIntensity) {
        val distance = x * x
        kernel[x + blurIntensity] = (exp((-distance / twoSigmaSquare).toDouble()) / sigmaRoot).toFloat()
        total += kernel[x + blurIntensity]
    }

    // Normalize the kernel
    for (i in kernel.indices) {
        kernel[i] /= total
    }

    for (x in 0..<width) {
        for (y in 0..<height) {
            var r = 0.0f
            var g = 0.0f
            var b = 0.0f

            for (dx in -blurIntensity..blurIntensity) {
                val px = (x + dx).coerceIn(0, width - 1)
                val pixelColor = Color(inputImage.getRGB(px, y))
                val weight = kernel[dx + blurIntensity]

                r += pixelColor.red * weight
                g += pixelColor.green * weight
                b += pixelColor.blue * weight
            }

            val blurredColor = Color(r.toInt(), g.toInt(), b.toInt())
            outputImage.setRGB(x, y, blurredColor.rgb)
        }
    }

    return outputImage

}
fun applyFilters(outputImage: BufferedImage,inputImage: BufferedImage,filterChoice : Int, width: Int, height: Int) : BufferedImage {

        when (filterChoice) {
            1 -> {
                // Vintage filter: Apply a sepia tone effect
                for (x in 0..<width) {
                    for (y in 0..<height) {
                        val color = Color(inputImage.getRGB(x, y))
                        val r = (color.red * 0.393 + color.green * 0.769 + color.blue * 0.189).coerceIn(0.0, 255.0)
                        val g = (color.red * 0.349 + color.green * 0.686 + color.blue * 0.168).coerceIn(0.0, 255.0)
                        val b = (color.red * 0.272 + color.green * 0.534 + color.blue * 0.131).coerceIn(0.0, 255.0)
                        val newColor = Color(r.toInt(), g.toInt(), b.toInt())
                        outputImage.setRGB(x, y, newColor.rgb)
                    }
                }
            }
            2 -> {
                // Cyberpunk filter: Increase contrast and apply a blue/purple tint
                val contrastFactor = 1.5
                val tintColor = Color(128, 0, 128) // Purple
                for (x in 0..<width) {
                    for (y in 0..<height) {
                        val color = Color(inputImage.getRGB(x, y))
                        val newRed = ((color.red - 128) * contrastFactor + tintColor.red).coerceIn(0.0, 255.0)
                        val newGreen = ((((color.green - 128) * contrastFactor) + tintColor.green)).coerceIn(0.0, 255.0)
                        val newBlue = ((color.blue - 128) * contrastFactor + tintColor.blue).coerceIn(0.0, 255.0)
                        val newColor = Color(newRed.toInt(), newGreen.toInt(), newBlue.toInt())
                        outputImage.setRGB(x, y, newColor.rgb)
                    }
                }
            }
            3 -> {
                // Funky filter: Apply a pixelate effect
                val pixelSize = 10
                val numXBlocks = width / pixelSize
                val numYBlocks = height / pixelSize
                for (xBlock in 0..<numXBlocks) {
                    for (yBlock in 0..<numYBlocks) {
                        val startX = xBlock * pixelSize
                        val startY = yBlock * pixelSize
                        val blockColor = Color(inputImage.getRGB(startX, startY))
                        for (x in startX..<startX + pixelSize) {
                            for (y in startY..<startY + pixelSize) {
                                outputImage.setRGB(x, y, blockColor.rgb)
                            }
                        }
                    }
                }
            }
            4 -> {
              // Invert Colors
                for (x in 0..<width) {
                    for (y in 0..<height) {
                        val color = Color(inputImage.getRGB(x, y))
                        val invertedColor = Color(255 - color.red, 255 - color.green, 255 - color.blue)
                        outputImage.setRGB(x, y, invertedColor.rgb)
                    }
                }
            }
            5 -> {
                // Black and White
                for (x in 0..<width) {
                    for (y in 0..<height) {
                        val color = Color(inputImage.getRGB(x, y))
                        val luminance = 0.299 * color.red + 0.587 * color.green + 0.114 * color.blue
                        val newColor = Color(luminance.toInt(), luminance.toInt(), luminance.toInt())
                        outputImage.setRGB(x, y, newColor.rgb)
                    }
                }
            }
        }
        return outputImage
    }



fun openImageInDefaultViewer(imageFilePath: String?) {
    val imageFile = imageFilePath?.let { File(it) }

    if (imageFile != null) {
        if (!imageFile.exists()) {
            println("The image file does not exist.")
            return
        }
    }

    if (!Desktop.isDesktopSupported()) {
        println("Desktop operations are not supported on this platform.")
        return
    }

    val desktop = Desktop.getDesktop()

    try {
        desktop.open(imageFile)
    } catch (e: IOException) {
        println("An error occurred while opening the image: ${e.message}")
    }
}

