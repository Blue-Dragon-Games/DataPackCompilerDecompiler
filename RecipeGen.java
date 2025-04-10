/// /////////////////////////////////////////////////////////////////////
/// Class that holds the functions to automatically generate recipes ///
/// /////////////////////////////////////////////////////////////////////

import java.io.File;

public class RecipeGen {

    // used to autogenerate shaped recipes
    public static void make_shaped(String path, String namespace, String name, String shape_raw[], STuple key_raw[], String output, int quant) {
        JsonFormatter maker = new JsonFormatter();
        
        // creating the type such that it is a shapeless crafting recipe
        JsonFormatter.Component type = maker.get_component("\"type\"", JsonFormatter.Component.ComponentType.VALUE, 1);
        type.update_data("\"minecraft:crafting_shaped\"");
        maker.add_component(type);

        // create the pattern that the crafting recipe uses
        JsonFormatter.Component shape = maker.get_component("\"pattern\"", JsonFormatter.Component.ComponentType.ARRAY, 1);
        for (int i = 0; ((i<3)&&(i<shape_raw.length)); i++) {
            shape.add_to_array("\"" + shape_raw[i] + "\"");
        }
        maker.add_component(shape);

        // create the key that the pattern uses
        JsonFormatter.Component key = maker.get_component("\"key\"", JsonFormatter.Component.ComponentType.BUNDLE, 1);
        for (int i = 0; ((i<9)&&(i<key_raw.length)); i++) {
            JsonFormatter.Component curr = maker.get_component("\""+key_raw[i].get_first()+"\"", JsonFormatter.Component.ComponentType.VALUE, 2);
            curr.update_data("\"minecraft:" + key_raw[i].get_second() +"\"");
            key.update_data(curr);
        }
        maker.add_component(key);

        // creating the result
        JsonFormatter.Component result = maker.get_component("\"result\"", JsonFormatter.Component.ComponentType.BUNDLE, 1);
        JsonFormatter.Component id = maker.get_component("\"id\"", JsonFormatter.Component.ComponentType.VALUE, 2);
        JsonFormatter.Component count = maker.get_component("\"count\"", JsonFormatter.Component.ComponentType.VALUE, 2);

        id.update_data("\"minecraft:" + output + "\"");
        count.update_data(Integer.toString(quant));

        result.update_data(id);
        result.update_data(count);

        maker.add_component(result);

        String dir = path + "/data/" + namespace + "/recipe/";
        new File(dir).mkdirs();
        maker.make_json_file(dir + name.toLowerCase());

    }

    // used to create a shaped recipe that is one item surrounded by another item
    // first input of the inputs array is the center item, second is the surrounding item
    public static void make_dye_recipe(String path, String namespace, String name, String inputs[], String output, int quant) {
        String pattern[] = {"222", "212", "222"};
        STuple keys0 = new STuple("1", inputs[0]);
        STuple keys1 = new STuple("2", inputs[1]);
        STuple[] keys = {keys0, keys1};
        make_shaped(path, namespace, name, pattern, keys, output, quant);
    }


    // used to autogenerate a shapeless crafting recipe
    public static void make_shapeless(String path, String namespace, String name, String inputs[], String output, int quant) {
        JsonFormatter maker = new JsonFormatter();

        // creating the type such that it is a shapeless crafting recipe
        JsonFormatter.Component type = maker.get_component("\"type\"", JsonFormatter.Component.ComponentType.VALUE, 1);
        type.update_data("\"minecraft:crafting_shapeless\"");
        maker.add_component(type);

        // decompose ingredients to correct format
        JsonFormatter.Component ingredient = maker.get_component("\"ingredients\"", JsonFormatter.Component.ComponentType.ARRAY, 1);
        for (int i = 0; ((i<9)&&(i<inputs.length)); i++) {
            ingredient.add_to_array("\"minecraft:" + inputs[i] + "\"");
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

        String dir = path + "/data/" + namespace + "/recipe/";
        new File(dir).mkdirs();
        maker.make_json_file(dir + name.toLowerCase());
    }

    // used to autogenerate a stonecutter recipe
    public static void make_stonecutter_rec(String path, String name, String input, String output, int quant) {
        JsonFormatter maker = new JsonFormatter();

        // creating the type such that it is a stone cutter recipe
        JsonFormatter.Component type = maker.get_component("\"type\"", JsonFormatter.Component.ComponentType.VALUE, 1);
        type.update_data("\"minecraft:stonecutting\"");
        maker.add_component(type);

        // creating the ingredient
        JsonFormatter.Component ingredient = maker.get_component("\"ingredient\"", JsonFormatter.Component.ComponentType.VALUE, 1);
        ingredient.update_data("\"minecraft:" + input + "\"");
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

        maker.make_json_file(path + name.toLowerCase());
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