import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonFormatter {

    ArrayList<Component> list;

    public JsonFormatter() {
        list = new ArrayList<Component>();
    }

    public String get_json() {
        StringBuilder json = new StringBuilder("{\n");
        for (Component item : this.list) {
            json.append("\t").append(item.toString());
        }
        json.append("end}");
        json = new StringBuilder(json.toString().replace(",\nend}", "\n}"));
        return json.toString();
    }

    public void make_json_file(String path) {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write(get_json());
        } catch (IOException e) {
            System.err.println("Error writing to file using FileWriter: " + e.getMessage());
        }
    }

    public Component get_component(String label, Component.ComponentType type, int depth) {
        return new Component(label, type, depth);
    }

    public void add_component(Component comp) {
        list.add(comp);
    }

    public class Component {
        String label;
        Data data;
        int depth;

        public enum ComponentType {
            VALUE,
            LIST,
            BUNDLE,
            ARRAY
        }

        private class Data {
            ComponentType type;
            String line;
            ArrayList<Component> components;
            ArrayList<String> values;
            int depth;

            private Data(ComponentType type, int depth) {
                this.depth = depth;
                this.type = type;
                if ((type != ComponentType.VALUE)&&(type!=ComponentType.ARRAY)) {
                    components = new ArrayList<Component>();
                }
                if (type == ComponentType.ARRAY) {
                    values = new ArrayList<String>();
                }
            }

            // only valid to call this function if the type is value
            // does nothing otherwise
            private void update_value(String line) {
                if (type == ComponentType.VALUE) {
                    this.line = line;
                }
                if (type == ComponentType.ARRAY) {
                    values = new ArrayList<String>();
                }
            }

            // only valid to call this function if the type is not value
            // does nothing otherwise
            private void add_component(Component item) {
                if ((type == ComponentType.LIST) || (type == ComponentType.BUNDLE)) {
                    components.add(item);
                }
            }

            // only valid to call this function if the type is Array
            // does nothing otherwise.
            private void add_to_array(String item) {
                if (type == ComponentType.ARRAY) {
                    values.add(item);
                }
            }

            @Override
            public String toString() {
                if (type == ComponentType.VALUE) {
                    return line;
                } else if (type == ComponentType.BUNDLE) {
                    StringBuilder tab_pad = new StringBuilder();
                    tab_pad.append("\t".repeat(Math.max(0, depth)));
                    StringBuilder val = new StringBuilder("{\n");
                    for (Component item : components) {
                        val.append(tab_pad).append(item);
                    }
                    val.append("end");
                    val = new StringBuilder(val.toString().replace(",\nend", "\n" + tab_pad + "}"));
                    return val.toString();
                } else if (type == ComponentType.ARRAY) {
                    StringBuilder tab_pad = new StringBuilder();
                    tab_pad.append("\t".repeat(Math.max(0, depth)));
                    StringBuilder val = new StringBuilder("[\n");
                    for (String item : values) {
                        val.append(tab_pad).append(item).append(",\n");
                    }
                    val.append("end");
                    val = new StringBuilder(val.toString().replace(",\nend", "\n" + tab_pad + "]"));
                    return val.toString();
                } else if (type == ComponentType.LIST) {
                    //TODO: Test this
                    StringBuilder tab_pad = new StringBuilder();
                    tab_pad.append("\t".repeat(Math.max(0, depth)));
                    StringBuilder val = new StringBuilder("[\n");
                    for (Component item : components) {
                        val.append(tab_pad).append("{\n");
                        val.append(tab_pad).append(item);
                        val.delete(val.length()-2, val.length()-1);
                        val.append(tab_pad).append("},\n");
                    }
                    val.append("end");
                    val = new StringBuilder(val.toString().replace(",\nend", "\n" + tab_pad + "]"));
                    return val.toString(); 
                }
                return null;
            }
        }


        // constructor for each component in the json file
        // inputs are the label of this component, type of this data tag, and the depth of it.
        // the type tells you if this is an array, a list, a bundle, or a simple value
        // Depth value tells you how many tabs should be displayed before this row 
        // the depth has format implications for value types other than value
        //
        // you will have to add the data stored in this component after creating the component
        public Component(String label, ComponentType type, int depth) {
            this.depth = depth;
            this.label = label;
            data = new Data(type, depth + 1);
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

        public void add_to_array(String value) {
            data.add_to_array(value);
        }

        @Override
        public String toString() {
            depth++;
            String val = label + " : " + data.toString() + ",\n";
            depth--;
            return val;
        }
    }
}
