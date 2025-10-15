package factory;

interface Document {
    void open();
    void save();
}

class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening a Word document (.docx)");
    }

    @Override
    public void save() {
        System.out.println("Saving as Word format...");
    }
}

class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening a PDF document (.pdf)");
    }

    @Override
    public void save() {
        System.out.println("Saving as PDF format...");
    }
}

abstract class Application {
    public void newDocument() {
        Document doc = createDocument();
        doc.open();
        doc.save();
    }

    protected abstract Document createDocument();
}

class WordApplication extends Application {
    @Override
    protected Document createDocument() {
        return new WordDocument();
    }
}

class PdfApplication extends Application {
    @Override
    protected Document createDocument() {
        return new PdfDocument();
    }
}

public class Main {
    public static void main(String[] args) {
        Application wordApp = new WordApplication();
        wordApp.newDocument();

        System.out.println();

        Application pdfApp = new PdfApplication();
        pdfApp.newDocument();
    }
}
