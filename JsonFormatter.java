import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonFormatter {

    Component[] list;

    public String get_json() {
        String json = "{\n";
        for (Component item : this.list) {
            json += "\t"+item.toString();
        }
        json += "end}";
        json = json.replace(",\nend}", "\n}");
        return json;
    }

    public void make_json_file(String path) {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write(get_json());
        } catch (IOException e) {
            System.err.println("Error writing to file using FileWriter: " + e.getMessage());
        }
    }

    public class Component {
        String label;
        Data data;
        int depth;

        private class Data {
            boolean list;
            String line;
            ArrayList<Component> components;

            private Data(boolean list) {
                this.list = list;
                if (list) {
                    components = new ArrayList<Component>();
                }
            }
        }


        // constructor for each component in the json file
        // inputs are the label for the component
        // and a boolean that signifies whether or not 
        // the componet will have a list in its data element.
        //
        // you will have to update the component with its desired
        // data values after creating the component.
        public Component(String label, boolean list) {
            depth = 0;
            this.label = label;
            data = new Data(list);
        }

        public void update_data() {
        }

        @Override
        public String toString() {
            depth++;
            String val = label+":" + data.toString()+",";
            depth--;
            return val;
        }
    }
}
