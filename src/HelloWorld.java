public class HelloWorld implements HelloWorldMBean{
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void printHello() {
        System.out.println("Hello World, "+name);
    }

    @Override
    public void printHello(String name) {
        System.out.println("Hello, "+name);
    }
}
