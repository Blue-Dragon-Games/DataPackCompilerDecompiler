public class QuickGen {
    public static void overdye(String path) {
        //overdye list
        String dyes[] = {
            "white",
            "light_gray",
            "gray",
            "black",
            "brown",
            "red",
            "orange",
            "yellow",
            "lime",
            "green",
            "cyan",
            "light_blue",
            "blue",
            "purple",
            "magenta",
            "pink"
        };
        String dyeable[] = {
            "_wool",
            "_terracotta",
            "_concrete",
            "_concrete_powder"
        };

        for (String dye : dyes) {
            for (String target : dyeable) {
                for (String dye2 : dyes) {
                    if (dye.equals(dye2)) {
                        continue;
                    }
                    String list[] = {dye+"_dye", dye2+target};
                    RecipeGen.make_dye_recipe(path, dye+(target.replace("_","")), dye+dye2+".json", list, dye+target, 8);
                }
            }
        }
    }
}
