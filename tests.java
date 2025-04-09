public class tests {
    public static void main(String[] args) {
        test1();
    } 

    public static void test1() {
        JsonFormatter tester = new JsonFormatter();
        JsonFormatter.Component comp1 = tester.get_component("Test", JsonFormatter.Component.ComponentType.LIST, 0);
        JsonFormatter.Component comp2 = tester.get_component("Little Test", JsonFormatter.Component.ComponentType.VALUE, 1);
        JsonFormatter.Component comp3 = tester.get_component("Little Test", JsonFormatter.Component.ComponentType.VALUE, 1);
        JsonFormatter.Component comp4 = tester.get_component("Little Test", JsonFormatter.Component.ComponentType.VALUE, 1);
        JsonFormatter.Component comp5 = tester.get_component("Little Test", JsonFormatter.Component.ComponentType.VALUE, 1);

        comp2.update_data("dummy value");
        comp3.update_data("dummy value");
        comp4.update_data("dummy value");
        comp5.update_data("dummy value");

        comp1.update_data(comp2);
        comp1.update_data(comp3);
        comp1.update_data(comp4);
        comp1.update_data(comp5);
        tester.add_component(comp1);
        tester.add_component(comp1);
        tester.make_json_file("test_1_output.json");
    }
}
