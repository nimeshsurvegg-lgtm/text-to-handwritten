# ✨ Procedural Handwriting Generator

A Java-based command-line tool that dynamically converts digital text into realistic, organic handwriting. 

Unlike standard text-to-image converters that use static fonts, this program uses **Procedural Generation** to create a unique handwriting profile for every single run. By subtly randomizing slant, character width, baseline bounce, and individual letter tilt, no two documents will ever look mathematically identical.

![Example Output](assets/handwritten1.png) 
*(Note to developer: Create a folder named `assets`, upload one of your best generated PNGs there, and it will show up here!)*

## 🚀 Features

* **Procedural Uniqueness:** Generates a brand-new font profile (shear, scale, tilt, bounciness) every time you run the script.
* **Human Error Simulation:** Features a 0.2% chance to naturally misspell a word and organically strike it out with a pen line.
* **Multiple Input Methods:** Type your text directly into the terminal or fetch it from a `.txt` file.
* **Customizable Canvas:**
  * **Paper Types:** Blank White, Lined (no margin), Lined (with red margin), or Vintage Parchment.
  * **Pen Colors:** Standard Black, Ink Blue, or Red.
* **Dynamic Word Wrapping:** Automatically calculates margins and wraps text to fit a standard portrait page.

## 🛠️ Prerequisites

* **Java Development Kit (JDK):** Ensure you have Java 8 or higher installed on your machine.
* *(Optional but Highly Recommended):* For absolute hyper-realism, install a free handwritten font on your OS like [Caveat](https://fonts.google.com/specimen/Caveat) or [Dancing Script](https://fonts.google.com/specimen/Dancing+Script).

## 💻 How to Run

1. Clone this repository to your local machine:
   ```bash
   git clone [https://github.com/YOUR-USERNAME/text-to-handwritten.git](https://github.com/YOUR-USERNAME/text-to-handwritten.git)
