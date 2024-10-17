import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TextEditor {
    private List<String> textHistory = new ArrayList<>();
    private int currentVersion = -1;

    public void show() {
        if (textHistory.isEmpty()) {
            System.out.println("Text editor kosong.");
        } else {
            System.out.println("Isi Text Editor:");
            System.out.println(getCurrentText());
        }
    }

    public void write(String text) {
        if (currentVersion < textHistory.size() - 1) {
            textHistory.subList(currentVersion + 1, textHistory.size()).clear();
        }
        textHistory.add(text);
        currentVersion++;
        System.out.println("Teks ditambahkan: " + text);
        showCurrentText();
    }

    public void undo() {
        if (currentVersion > 0) {
            currentVersion--;
            System.out.println("Undo berhasil.");
        } else {
            System.out.println("Tidak ada yang bisa di-undo.");
        }
        showCurrentText();
    }

    public void redo() {
        if (currentVersion < textHistory.size() - 1) {
            currentVersion++;
            System.out.println("Redo berhasil.");
        } else {
            System.out.println("Tidak ada yang bisa di-redo.");
        }
        showCurrentText();
    }

    private void showCurrentText() {
        if (currentVersion >= 0 && currentVersion < textHistory.size()) {
            System.out.println("Teks saat ini: " + getCurrentText());
        } else {
            System.out.println("Text editor kosong.");
        }
    }

    private String getCurrentText() {
        return currentVersion >= 0 ? textHistory.get(currentVersion) : "";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditor textEditor = new TextEditor();
        boolean running = true;

        while (running) {
            System.out.println("\nMenu Text Editor:");
            System.out.println("1. Tampilkan Teks");
            System.out.println("2. Tambah Teks");
            System.out.println("3. Undo");
            System.out.println("4. Redo");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    textEditor.show();
                    break;
                case 2:
                    System.out.print("Masukkan teks: ");
                    String newText = scanner.nextLine();
                    textEditor.write(newText);
                    break;
                case 3:
                    textEditor.undo();
                    break;
                case 4:
                    textEditor.redo();
                    break;
                case 5:
                    running = false;
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Opsi tidak valid. Silakan coba lagi.");
            }
        }
        scanner.close();
    }
}