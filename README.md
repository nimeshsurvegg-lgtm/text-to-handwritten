# ✨ Procedural Handwriting Generator 

![Java Version](https://img.shields.io/badge/Java-8%2B-blue.svg)
![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)
![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)

> **Status:** 🚧 Actively under development. Features and documentation are being updated frequently.

Welcome! This tool dynamically generates a unique font profile to convert your digital text into natural, human-like handwriting. Unlike standard static fonts, this generator introduces procedural variations and realistic human errors to create authentic-looking handwritten documents.

---

## 📖 Table of Contents
- [Features](#-features)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Usage](#-usage)
- [How It Works](#-how-it-works)
- [Output](#-output)
- [Contributing](#-contributing)
- [License](#-license)

---

## 🚀 Features

* **Procedural Variability:** Automatically generates a unique handwriting profile for every session by randomly adjusting global shear, width scale, and character tilt variance.
* **Human Mistake Simulation:** Randomly scrambles words longer than two characters and draws a realistic strike-through line over them to mimic natural writing errors.
* **Multiple Input Methods:** Accepts manual text input via the terminal or reads directly from an existing `.txt` file.
* **Customizable Paper:** Supports four background types: Blank White, Lined (Without Margin), Lined (With Red Margin), and Realistic/Vintage Parchment.
* **Ink Colors:** Choose between Black, Blue, and Red pen colors.
* **Dynamic Formatting:** Automatically handles word wrapping and margins to keep text strictly within the bounds of the generated page.

---

## ⚙️ Prerequisites

To run this project, you need:
* Java Development Kit (JDK) 8 or higher installed on your machine.
* A terminal or command-line interface.

---

## 🛠️ Installation

1.  Clone this repository or download the source code.
2.  Ensure the main source file is named `TextToHandwritten.java`.
3.  Open your terminal and navigate to the directory containing the file.
4.  Compile the Java file using the following command:

    `javac TextToHandwritten.java`

---

## 💻 Usage

After compiling, run the program with:

`java TextToHandwritten`

The application will launch an interactive terminal menu. Follow the on-screen prompts to generate your document:

1.  **Choose Input Method:** Select `1` for manual text entry (type `END` on a new line to finish) or `2` to specify a `.txt` file path.
2.  **Set Font Size:** Enter a numeric value for the font size, such as `28`, `32`, or `40`.
3.  **Select Paper Type:** Choose from the four available background styles (1-4).
4.  **Select Pen Color:** Choose your ink color from Black, Blue, or Red (1-3).

---

## 🧠 How It Works (Under the Hood)

The `TextToHandwritten` class utilizes standard Java graphics libraries (`java.awt`, `java.awt.image.BufferedImage`) to render text onto a digital canvas. 

* **Font Distortion:** It takes a base "Serif" italicized font and applies an `AffineTransform`. The transformation shears and scales the font based on randomized procedural variables generated at runtime.
* **Character Rendering:** The `drawSmoothWord` method renders text character-by-character, applying slight vertical offsets and random rotational tilt to simulate the inconsistency of human handwriting.

---

## 📄 Output

* The generated document is a high-resolution `1200x1600` pixel image.
* It is automatically saved in your current working directory as a `.png` file.
* The program safely auto-increments filenames (e.g., `handwritten1.png`, `handwritten2.png`) so your previous generations are never accidentally overwritten.

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! 

1. Fork the project.
2. Create your feature branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a Pull Request.

## 📜 License

Distributed under the MIT License. See `LICENSE` for more information.
