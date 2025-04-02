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

        public enum ComponentType {
            VALUE,
            LIST,
            BUNDLE
        }

        private class Data {
            ComponentType type;
            String line;
            ArrayList<Component> components;

            private Data(ComponentType type) {
                this.type = type;
                if (type != ComponentType.VALUE) {
                    components = new ArrayList<Component>();
                }
            }

            // only valid to call this function if the type is value
            // does nothing otherwise
            private void update_value(String line) {
                if (type == ComponentType.VALUE) {
                    this.line = line;
                }
            }

            // only valid to call this function if the type is not value
            // does nothing otherwise
            private void add_component(Component item) {
                if (type != ComponentType.VALUE) {
                    components.add(item);
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
        public Component(String label, ComponentType type) {
            depth = 0;
            this.label = label;
            data = new Data(type);
        }

        // way to add the line of data to the data value
        // if the designated componet has the "value" data type
        public void update_data(String line) {
            data.update_value(line);
        }

        // way to add a component to the data value
        // if the designated componenet is not of the "value" data type
        public void update_data(Component comp) {
            data.add_component(comp);
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
