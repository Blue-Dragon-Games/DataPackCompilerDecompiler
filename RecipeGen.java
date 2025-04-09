/// /////////////////////////////////////////////////////////////////////
/// Class that holds the functions to automatically generate recipes ///
/// /////////////////////////////////////////////////////////////////////
public class RecipeGen {
    // used to autogenerate a shapeless crafting recipe
    public static void make_shapeless(String path, String name, String inputs[], String output, int quant) {
        JsonFormatter maker = new JsonFormatter();

        // creating the type such that it is a stone cutter recipe
        JsonFormatter.Component type = maker.get_component("\"type\"", JsonFormatter.Component.ComponentType.VALUE, 1);
        type.update_data(" \"crafting_shapeless\"");
        maker.add_component(type);

        // creating the ingredient
        //TODO: this has to be a proper list, and need to figure out how this sort of recipe works


        /* valid format:
{
    "type": "crafting_shapeless",
    "ingredients": [
      "minecraft:gunpowder", "minecraft:diamond", "minecraft:diamond", "minecraft:diamond"
    ],
    "result": {
      "id": "minecraft:diamond",
      "count": 3
    }
  }
         */
        JsonFormatter.Component ingredient = maker.get_component("\"ingredients\"", JsonFormatter.Component.ComponentType.ARRAY, 1);
        for (int i = 0; ((i<9)&&(i<inputs.length)); i++) {
            ingredient.add_to_array(" \"minecraft:" + inputs[i] + "\"");
        }
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

        maker.make_json_file((path + name).toLowerCase());

    }

    // used to autogenerate a stonecutter recipe
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

        maker.make_json_file((path + name).toLowerCase());
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