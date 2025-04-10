import java.io.File;

public class compiler {
    public static void main(String[] args) {
        int[] supported_formats = {61, 71};
        ensure_output_dir_exists("output");
        make_mcmeta("output","A crafting Tweaks Data Pack", 61, supported_formats);

        QuickGen.overdye("output");
        String list[] = {"sugar", "rotten_flesh"};
        String ame[] = {"amethyst_block"};
        String qtz[] = {"quartz_block"};
        RecipeGen.make_dye_recipe("output", "craftingtweak", "rottenfleshtoleather.json", list, "leather", 4);
        RecipeGen.make_shapeless("output", "craftingtweak", "amethyst.json", ame, "amethyst_shard", 4);
        RecipeGen.make_shapeless("output", "craftingtweak", "quartz.json", qtz, "quartz", 4);

        /*String shape[] = {"123","123","123"};
        STuple keys0 = new STuple("1", "diamond");
        STuple keys1 = new STuple("2", "kelp");
        STuple keys2 = new STuple("3", "gunpowder");
        STuple[] keys = {keys0, keys1, keys2};

        RecipeGen.make_shaped("output", "crafting" ,"testshaped.json", shape, keys,"diamond", 64);*/

        /*RecipeGen.make_stonecutter_rec("", "test", "input", "output", 1);
        
        String testing[] = {"diamond", "diamond", "diamond"};
        RecipeGen.make_shapeless("", "SevenSS.json", testing, "kelp", 1);
        /*JsonFormatter tester = new JsonFormatter();
        //JsonFormatter.Component test1 = tester.get_component("\"Pack\"",JsonFormatter.Component.ComponentType.VALUE,1);
        //test1.update_data("Item");
        //tester.add_component(test1);
        JsonFormatter.Component pack = tester.get_component("\"pack\"", JsonFormatter.Component.ComponentType.BUNDLE, 1);
        JsonFormatter.Component description = tester.get_component("\"description\"",JsonFormatter.Component.ComponentType.VALUE,2);
        JsonFormatter.Component pack_format = tester.get_component("\"pack_format\"",JsonFormatter.Component.ComponentType.VALUE,2);
        JsonFormatter.Component supported_formats = tester.get_component("\"supported_formats\"",JsonFormatter.Component.ComponentType.VALUE,2);
        
        description.update_data("\"test\""); 
        pack_format.update_data("61");
        supported_formats.update_data("[61, 71]");

        pack.update_data(description);
        pack.update_data(pack_format);
        pack.update_data(supported_formats);
        tester.add_component(pack);
        tester.make_json_file("pack.mcmeta");*/
        /* 
        int[] list = {61, 71};
        try (FileWriter fw = new FileWriter("test.mcmeta")) {
            fw.write(get_mcmeta("", 61, list));
        } catch (IOException e) {
            System.err.println("Error writing to file using FileWriter: " + e.getMessage());
        } */
    }

    private static void ensure_output_dir_exists(String output_dir_name) {
        try{
            if(!(new File(output_dir_name).mkdirs())) {
                System.out.println("Failed to create output directory \"" + output_dir_name +"\"!");
            }
        } catch (SecurityException e) {
            System.out.println("Could not create output directory \"" + output_dir_name +"\"!\n" + e.getLocalizedMessage());
        }
    }


    // takes in the description (as a string), format (as an int), and supported format (as an int array)
    // then generates the mcmeta file for the pack.
    // TODO: make path creation dynamic
    private static void make_mcmeta(String path, String desc, int format, int[] supported_format) {
        JsonFormatter tester = new JsonFormatter();
        JsonFormatter.Component pack = tester.get_component("\"pack\"", JsonFormatter.Component.ComponentType.BUNDLE, 1);
        JsonFormatter.Component description = tester.get_component("\"description\"", JsonFormatter.Component.ComponentType.VALUE, 2);
        JsonFormatter.Component pack_format = tester.get_component("\"pack_format\"", JsonFormatter.Component.ComponentType.VALUE, 2);
        JsonFormatter.Component supported_formats = tester.get_component("\"supported_formats\"", JsonFormatter.Component.ComponentType.VALUE, 2);

        description.update_data("\"" + desc + "\"");
        pack_format.update_data(Integer.toString(format));


        // What's this for now? Any code using this variable has been commented out, but that may just be for testing.
        StringBuilder formats = new StringBuilder("[");
        for (int i : supported_format) {
            formats.append(i).append(",");
        }
        formats.append("/");
        formats = new StringBuilder(formats.toString().replace(",/", "]"));

        supported_formats.update_data(formats.toString());

        pack.update_data(description);
        pack.update_data(pack_format);
        pack.update_data(supported_formats);
        tester.add_component(pack);
        tester.make_json_file(path + "/pack.mcmeta");
        /* 
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
        */
    }
}
