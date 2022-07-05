import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Application {

    public static void main(String[] args) throws IOException {
        leituraArquivoIO();
        leituraArquivoIOTryWithResources();
        criarArquivoIO();
        deleteArquivoIO();

        leituraArquivoNIO();
        //deleteArquivoNIO();
        //propriedadesNIO();
        listarDiretorioNIO();
        //filtrarArquivoNIO();
        //arquivosTempNIO();
        //criarArquivoNIO();
    }

    private static void criarArquivoNIO() {
        Path path = Paths.get( "file2.txt");
        try {
            Files.writeString(path, "Editado via java 2\n", StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void arquivosTempNIO() {
        try {
            Path path = Files.createTempFile("arquivo_", ".tmp");
            System.out.println(path);
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void filtrarArquivoNIO() {
        Path path = Paths.get(System.getProperty("user.home"));
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.txt")) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void listarDiretorioNIO() {
        Path path = Paths.get(System.getProperty("user.home"));
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void propriedadesNIO() {
        Path file = Paths.get("file2.txt");
        System.out.println("Existe? " + Files.exists(file));

        Path dir = Paths.get(System.getProperty("user.home"));
        System.out.println("Diretório? " + Files.isDirectory(dir));

        Path novaPasta = Paths.get(System.getProperty("user.home"), "exemplo/arquivos");
        Path novaPastaTeste = Paths.get(System.getProperty("user.home"), "exemplo/arquivos/teste");
        try {
            Files.createDirectories(novaPasta);
            Files.createDirectory(novaPastaTeste);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteArquivoNIO() {
        Path dir = Paths.get("file2.txt");
        try {
            Files.delete(dir);
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado");
        }
    }

    private static void leituraArquivoNIO() {
        Path dir = Paths.get("file.txt");
        try {
            var file = Files.lines(dir);
            file.forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteArquivoIO() {
        File file = new File("file_new.txt");
        file.delete();
    }

    private static void criarArquivoIO() throws IOException {
        File file = new File("file_new.txt");
        try (var writter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(writter)) {

            bufferedWriter.write("Let`s Code !!!");
        }
    }

    private static void leituraArquivoIOTryWithResources() {
        File file = new File("file.txt");
        try (var reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void leituraArquivoIO() throws IOException {
        File file = new File("file.txt");

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            reader.close();
        }
    }
}
