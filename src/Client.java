import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class Client {
    public static void main(String[] args) throws Exception{
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/JmxDemoServer");
        JMXConnector jmxc = JMXConnectorFactory.connect(url,null);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        //ObjectName的名称与前面注册时候的保持一致
        ObjectName mbeanName = new ObjectName("jmxBean:name=hello");

        System.out.println("Domains ....");
        String[] domains = mbsc.getDomains();

        for(int i=0;i<domains.length;i++)
        {
            System.out.println("doumain[" + i + "]=" + domains[i] );
        }

        System.out.println("MBean count = " + mbsc.getMBeanCount());
        //设置指定Mbean的特定属性值
        //这里的setAttribute、getAttribute操作只能针对bean的属性
        //例如对getName或者setName进行操作，只能使用Name，需要去除方法的前缀
        mbsc.setAttribute(mbeanName, new Attribute("Name","杭州"));
        String name = (String)mbsc.getAttribute(mbeanName, "Name");
        System.out.println("name=" + name);

        HelloWorldMBean proxy = MBeanServerInvocationHandler.
                newProxyInstance(mbsc, mbeanName, HelloWorldMBean.class, false);
        proxy.printHello();
        proxy.printHello("migu");
        //invoke调用bean的方法，只针对非设置属性的方法
        //例如invoke不能对getName方法进行调用
        mbsc.invoke(mbeanName, "printHello", null, null);
    }
}
