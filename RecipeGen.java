////////////////////////////////////////////////////////////////////////
/// Class that holds the functions to automatically generate recipes ///
////////////////////////////////////////////////////////////////////////
public class RecipeGen {
    public static String stonecutter_rec(String input, String result, int quant) {
            return
                "{\n" + //
                "    \"type\": \"minecraft:stonecutting\",\n" + //
                "    \"ingredient\": \"minecraft:"+input+"\",\n" + //
                "    \"result\": {\n" + //
                "      \"id\": \"minecraft:"+result+"\",\n" + //
                "      \"count\": "+Integer.toString(quant)+"\n" + //
                "    }\n" + //
                "}";
    }
}