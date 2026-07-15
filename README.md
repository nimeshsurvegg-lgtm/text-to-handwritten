✨ Procedural Handwriting Generator
Welcome! This tool dynamically generates a unique font profile to convert your digital text into natural, human-like handwriting[cite: 1]. Unlike standard static fonts, this generator introduces procedural variations and realistic human errors to create authentic-looking handwritten documents[cite: 1].

📖 Table of Contents
Features

Prerequisites

Installation

Usage

How It Works

Output

Contributing

License

🚀 Features
Procedural Variability: Automatically generates a unique handwriting profile for every session by randomly adjusting global shear, width scale, and character tilt variance[cite: 1].

Human Mistake Simulation: Randomly scrambles words longer than two characters and draws a realistic strike-through line over them to mimic natural writing errors[cite: 1].

Multiple Input Methods: Accepts manual text input via the terminal or reads directly from an existing .txt file[cite: 1].

Customizable Paper: Supports four background types: Blank White, Lined (Without Margin), Lined (With Red Margin), and Realistic/Vintage Parchment[cite: 1].

Ink Colors: Choose between Black, Blue, and Red pen colors[cite: 1].

Dynamic Formatting: Automatically handles word wrapping and margins to keep text strictly within the bounds of the generated page[cite: 1].

⚙️ Prerequisites
To run this project, you need:

Java Development Kit (JDK) 8 or higher installed on your machine.

A terminal or command-line interface.

🛠️ Installation
Clone this repository or download the source code.

Ensure the main source file is named TextToHandwritten.java[cite: 1].

Open your terminal and navigate to the directory containing the file.

Compile the Java file using the following command:

Bash
javac TextToHandwritten.java
💻 Usage
After compiling, run the program with:

Bash
java TextToHandwritten
The application will launch an interactive terminal menu[cite: 1]. Follow the on-screen prompts to generate your document:

Choose Input Method: Select 1 for manual text entry (type END on a new line to finish) or 2 to specify a .txt file path[cite: 1].

Set Font Size: Enter a numeric value for the font size, such as 28, 32, or 40[cite: 1].

Select Paper Type: Choose from the four available background styles (1-4)[cite: 1].

Select Pen Color: Choose your ink color from Black, Blue, or Red (1-3)[cite: 1].

🧠 How It Works (Under the Hood)
The TextToHandwritten class utilizes standard Java graphics libraries (java.awt, java.awt.image.BufferedImage) to render text onto a digital canvas[cite: 1].

Font Distortion: It takes a base "Serif" italicized font and applies an AffineTransform[cite: 1]. The transformation shears and scales the font based on randomized procedural variables generated at runtime[cite: 1].

Character Rendering: The drawSmoothWord method renders text character-by-character, applying slight vertical offsets and random rotational tilt to simulate the inconsistency of human handwriting[cite: 1].

📄 Output
The generated document is a high-resolution 1200x1600 pixel image[cite: 1].

It is automatically saved in your current working directory as a .png file[cite: 1].

The program safely auto-increments filenames (e.g., handwritten1.png, handwritten2.png) so your previous generations are never accidentally overwritten[cite: 1].
