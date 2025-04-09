/// /////////////////////////////////////////////////////////////////////
/// Class that holds the functions to automatically generate recipes ///
/// /////////////////////////////////////////////////////////////////////
public class RecipeGen {
    public static void make_stonecutter_rec(String path, String name, String input, String output, int quant) {
        JsonFormatter maker = new JsonFormatter();

        // creating the type such that it is a stone cutter recipe
        JsonFormatter.Component type = maker.get_component("\"type\"", JsonFormatter.Component.ComponentType.VALUE, 1);
        type.update_data(" \"minecraft:stonecutting\"");
        maker.add_component(type);

        // creating the ingredient
        JsonFormatter.Component ingredient = maker.get_component("\"ingredient\"", JsonFormatter.Component.ComponentType.VALUE, 1);
        ingredient.update_data(" \"minecraft:" + input + "\"");
        maker.add_component(ingredient);

        // creating the result
        JsonFormatter.Component result = maker.get_component("\"result\"", JsonFormatter.Component.ComponentType.BUNDLE, 1);
        JsonFormatter.Component id = maker.get_component("\"id\"", JsonFormatter.Component.ComponentType.VALUE, 2);
        JsonFormatter.Component count = maker.get_component("\"count\"", JsonFormatter.Component.ComponentType.VALUE, 2);

        id.update_data("\"minecraft:" + output + "\"");
        count.update_data(Integer.toString(quant));

        result.update_data(id);
        result.update_data(count);

        maker.add_component(result);

        maker.make_json_file(path + name);
    }

    /*  This function is now deprecated, updated version has been created.
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
    } */
}