Kotlin Image Editor
Overview

The Kotlin Image Editor is a command-line tool that allows you to perform various image processing operations on images. This application is built in Kotlin and provides a user-friendly interface to apply operations like grayscale conversion, brightness adjustment, contrast adjustment, flipping, rotation, Gaussian blur, and applying artistic filters to images.
Features

    Grayscale Conversion: Convert a color image into grayscale, replacing each pixel with a color that is the average of its red, green, and blue values.

    Brightness Adjustment: Adjust the brightness of an image by adding or subtracting a specified value from each pixel's RGB components.

    Contrast Adjustment: Modify the contrast of an image by applying a contrast adjustment factor to the RGB components of each pixel.

    Image Flipping: Flip an image horizontally or vertically to create mirror or inverted effects.

    Image Rotation: Rotate an image clockwise or counterclockwise to change its orientation.

    Gaussian Blur: Apply a Gaussian blur effect to the image, creating a soft, blurry appearance.

    Artistic Filters: Apply various artistic filters to your image, including Vintage, Cyberpunk, Funky, Invert Colors, and Black and White.

Usage

    Run the Program:
        Ensure you have Kotlin installed.
        Compile and run the program using kotlinc and kotlin commands or use an IDE like IntelliJ IDEA.
        Execute the program by running the main function in the ImageEditor.kt file.

    Input Image:
        You will be prompted to provide the path to the input image file (absolute or relative to the program directory).

    Choose an Operation:
        Select the desired image processing operation by entering the corresponding number.
        For operations that require additional input (e.g., brightness adjustment), provide the necessary values.

    Output Image:
        You will be prompted to specify the path for the output image (absolute or relative).
        The processed image will be saved as a JPG file at the provided location.

    Optional: View the Image:
        You can choose to open the processed image in the default image viewer.

    Exit:
        To exit the program, select the "Exit" option.


Detailed Documentation ---

1. Grayscale Conversion:

    In grayscale conversion, we iterate through every pixel in the image matrix. Each pixel contains Red (R), Green (G), and Blue (B) color components.
    To create grayscale, we apply the following formula to each pixel:
        Gray Value (GV) = (R + G + B) / 3
    This formula computes the average of the R, G, and B values of each pixel, effectively representing the pixel's brightness.
    We then set all three R, G, and B components of the pixel to this computed Gray Value.
    The result is that the image loses its color and becomes a grayscale image.

2. Brightness Adjustment:

    In brightness adjustment, we traverse every pixel in the image matrix.
    To increase or decrease brightness, we add or subtract a user-defined adjustment value from each of the R, G, and B components.
    The formula used is:
        New R = R + Adjustment
        New G = G + Adjustment
        New B = B + Adjustment
    We make sure that the new values stay within the valid color range of 0 to 255 by clamping them.
    This adjustment effectively brightens or darkens the pixel, controlling the overall image brightness.

3. Contrast Adjustment:

    Contrast adjustment involves processing each pixel in the image.
    To change contrast, we use a user-defined contrast adjustment factor (CAF).
    The formula for each component is:
        New R = CAF * (R - 128) + 128
        New G = CAF * (G - 128) + 128
        New B = CAF * (B - 128) + 128
    We adjust each component by first shifting it around a neutral value (128) and then scaling it by the CAF.
    The adjustment factor controls the difference between the dark and light areas in the image, affecting its overall contrast.

4. Image Flipping:

    Image flipping entails changing the arrangement of pixels.
    For horizontal flipping, we swap the positions of pixels from left to right.
    For vertical flipping, we exchange positions from top to bottom.
    This transformation results in a mirrored image, as if you held it up to a mirror or turned it upside down.

5. Image Rotation:

    Image rotation means repositioning pixels to achieve the desired rotation angle.
    When you rotate clockwise, we move each pixel to a new position in a clockwise direction.
    Counterclockwise rotation is similar but in the opposite direction.
    The process preserves the image's integrity while changing its orientation.

6. Gaussian Blur:

    Gaussian blur simulates the effect of looking through a translucent, blurred surface.
    To accomplish this, we use a Gaussian-shaped kernel (a small matrix).
    We slide this kernel over each pixel, calculating a weighted average of the pixel's color and its neighboring pixels' colors.
    The size and weights of the kernel depend on the blur intensity you specify.
    The result is a softened, less detailed appearance, akin to viewing the image through a foggy window.

7. Applying Filters:

    Filters are predefined transformations, each with a distinct visual style.
    For example, the "Vintage" filter alters each pixel's R, G, and B components using a specific mathematical formula.
    Filters like "Cyberpunk" modify pixel values to achieve unique visual effects.
    Each filter applies its distinct operation to pixels to create its artistic style.

These operations are applied pixel by pixel, employing precise mathematical calculations and user-defined parameters, to produce the desired image transformations.