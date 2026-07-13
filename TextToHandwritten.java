import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

public class TextToHandwritten {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 1600; 
    private static final Random random = new Random();

    // Tamed Procedural Variables
    private static double globalShear;
    private static double widthScale;
    private static double tiltVariance;

    public static void main(String[] args) {
        generateUniqueHandwritingProfile();

        Scanner scanner = new Scanner(System.in);

        // --- NEW TERMINAL WELCOME & TITLE ---
        System.out.println("=======================================================");
        System.out.println("       ✨ PROCEDURAL HANDWRITING GENERATOR ✨          ");
        System.out.println("=======================================================");
        System.out.println("Welcome! This tool dynamically generates a unique font ");
        System.out.println("profile to convert your digital text into natural,     ");
        System.out.println("human-like handwriting.                                ");
        System.out.println("-------------------------------------------------------");
        
        System.out.println("1. Enter text manually");
        System.out.println("2. Fetch text from a .txt file");
        System.out.print("Choose an option (1/2): ");
        int textOption = scanner.nextInt();
        scanner.nextLine(); 

        String text = "";
        if (textOption == 1) {
            System.out.println("Enter your text (Type 'END' on a new line to finish):");
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = scanner.nextLine();
                if (line.equals("END")) break;
                sb.append(line).append(" ");
            }
            text = sb.toString();
        } else {
            System.out.print("Enter the file path (e.g., input.txt): ");
            String filePath = scanner.nextLine();
            try {
                text = readStringFromFile(filePath);
            } catch (IOException e) {
                System.out.println("Error reading file. Exiting.");
                return;
            }
        }

        System.out.print("Enter font size (e.g., 28, 32, 40): ");
        int fontSize = scanner.nextInt();

        System.out.println("\nSelect Paper Type:");
        System.out.println("1. Blank White");
        System.out.println("2. Lined (Without Margin)");
        System.out.println("3. Lined (With Red Margin)");
        System.out.println("4. Realistic/Vintage Parchment");
        System.out.print("Choose (1-4): ");
        int paperType = scanner.nextInt();

        System.out.println("\nSelect Pen Color:");
        System.out.println("1. Black");
        System.out.println("2. Blue");
        System.out.println("3. Red");
        System.out.print("Choose (1-3): ");
        int colorType = scanner.nextInt();

        Color penColor = Color.BLACK;
        if (colorType == 2) penColor = new Color(20, 40, 150); 
        if (colorType == 3) penColor = new Color(180, 20, 20); 

        System.out.println("\nGenerating document... Please wait.");
        generateDocument(text, fontSize, paperType, penColor);
        scanner.close();
    }

    private static void generateUniqueHandwritingProfile() {
        globalShear = (random.nextDouble() - 0.5) * 0.2; 
        widthScale = 0.95 + (random.nextDouble() * 0.1); 
        tiltVariance = 0.02; 
    }

    private static void generateDocument(String text, int fontSize, int paperType, Color penColor) {
        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        drawBackground(g2d, paperType, fontSize);

        Font baseFont = new Font("Serif", Font.ITALIC, fontSize);
        
        AffineTransform fontTransform = new AffineTransform();
        fontTransform.shear(globalShear, 0); 
        fontTransform.scale(widthScale, 1.0); 
        Font uniqueFont = baseFont.deriveFont(fontTransform);
        
        g2d.setFont(uniqueFont);
        g2d.setColor(penColor);
        FontMetrics fm = g2d.getFontMetrics();

        int leftMargin = (paperType == 3) ? 180 : 80;
        int rightMargin = WIDTH - 80;
        int currentX = leftMargin;
        int currentY = 120; 
        int lineHeight = (int) (fontSize * 1.8); 

        String[] words = text.split("\\s+");

        for (String word : words) {
            if (random.nextDouble() < 0.002 && word.length() > 2) {
                String mistake = scrambleWord(word);
                int mistakeWidth = fm.stringWidth(mistake);
                
                if (currentX + mistakeWidth > rightMargin) {
                    currentX = leftMargin;
                    currentY += lineHeight;
                }
                
                drawSmoothWord(g2d, mistake, currentX, currentY, fm);
                g2d.setStroke(new BasicStroke(2)); 
                g2d.drawLine(currentX - 2, currentY - (fontSize/3), currentX + mistakeWidth + 2, currentY - (fontSize/4) + random.nextInt(3));
                
                currentX += mistakeWidth + fm.charWidth(' '); 
            }

            int wordWidth = fm.stringWidth(word);
            
            if (currentX + wordWidth > rightMargin) {
                currentX = leftMargin;
                currentY += lineHeight;
                if (currentY > HEIGHT - 80) break;
            }

            currentX = drawSmoothWord(g2d, word, currentX, currentY, fm) + fm.charWidth(' ');
        }

        g2d.dispose();
        saveImage(img);
    }

    private static int drawSmoothWord(Graphics2D g2d, String text, int startX, int y, FontMetrics fm) {
        int currentX = startX;

        for (char c : text.toCharArray()) {
            int yOffset = (random.nextDouble() > 0.8) ? (random.nextBoolean() ? 1 : -1) : 0; 
            double angle = (random.nextDouble() - 0.5) * tiltVariance; 
            
            AffineTransform originalTransform = g2d.getTransform();
            g2d.translate(currentX, y + yOffset);
            g2d.rotate(angle);
            
            g2d.drawString(String.valueOf(c), 0, 0);
            g2d.setTransform(originalTransform);
            
            currentX += fm.charWidth(c); 
        }
        return currentX;
    }

    private static void drawBackground(Graphics2D g2d, int paperType, int fontSize) {
        if (paperType == 4) g2d.setColor(new Color(240, 235, 215)); 
        else g2d.setColor(Color.WHITE); 
        
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
        int lineHeight = (int) (fontSize * 1.8);

        if (paperType == 2 || paperType == 3) {
            g2d.setColor(new Color(173, 216, 230, 150)); 
            g2d.setStroke(new BasicStroke(1));
            for (int y = 120; y < HEIGHT; y += lineHeight) {
                g2d.drawLine(0, y, WIDTH, y);
            }
        }

        if (paperType == 3) {
            g2d.setColor(new Color(255, 100, 100, 180)); 
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(150, 0, 150, HEIGHT);
            g2d.drawLine(155, 0, 155, HEIGHT); 
        }
    }

    private static String scrambleWord(String word) {
        if (word.length() <= 2) return word;
        char[] chars = word.toCharArray();
        int idx = random.nextInt(chars.length - 1);
        char temp = chars[idx];
        chars[idx] = chars[idx + 1];
        chars[idx + 1] = temp;
        return new String(chars);
    }

    private static String readStringFromFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private static void saveImage(BufferedImage img) {
        int counter = 1;
        File file;
        do {
            file = new File("handwritten" + counter + ".png");
            counter++;
        } while (file.exists());

        try {
            ImageIO.write(img, "png", file);
            System.out.println("\n✅ Success! Image saved as: " + file.getName());
            System.out.println("=======================================================\n");
        } catch (IOException e) {
            System.out.println("\n❌ Failed to save image: " + e.getMessage());
        }
    }
}