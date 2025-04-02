import java.io.FileWriter;
import java.io.IOException;

public class compiler {
    public static void main(String[] args) {
        int[] list = {61, 71};
        try (FileWriter fw = new FileWriter("pack.mcmeta")) {
            fw.write(get_mcmeta("", 61, list));
        } catch (IOException e) {
            System.err.println("Error writing to file using FileWriter: " + e.getMessage());
        }
    }


    private static String get_mcmeta(String description, int format, int[] supported_formats) {
        String formats = "[" + supported_formats[0];
        for (int i = 1; i< supported_formats.length; i++) {
            formats = formats + ", " + supported_formats[i];
        }
        formats = formats + "]";
        return
            "{\n" + //
            "  \"pack\": {\n" + //
            "    \"description\": \""+description+"\",\n" + //
            "    \"pack_format\": "+format+",\n" + //
            "    \"supported_formats\": "+formats+"\n" + //
            "  }\n" + //
            "}";
    }
}
