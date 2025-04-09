public class compiler {
    public static void main(String[] args) {
        int[] supported_formats = {61, 71};
        make_mcmeta("A crafting Tweaks Data Pack", 61, supported_formats);

        RecipeGen.make_stonecutter_rec("", "", "input", "output", 1);
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


    // takes in the description (as a string), format (as an int), and supported format (as an int array)
    // then generates the mcmeta file for the pack.
    // TODO: make path creation dynamic
    private static void make_mcmeta(String desc, int format, int[] supported_format) {
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

        supported_formats.update_data("[61, 71]");

        pack.update_data(description);
        pack.update_data(pack_format);
        pack.update_data(supported_formats);
        tester.add_component(pack);
        tester.make_json_file("pack.mcmeta");
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
