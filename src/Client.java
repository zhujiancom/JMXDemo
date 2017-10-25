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

        //ObjectName��������ǰ��ע��ʱ��ı���һ��
        ObjectName mbeanName = new ObjectName("jmxBean:name=hello");

        System.out.println("Domains ....");
        String[] domains = mbsc.getDomains();

        for(int i=0;i<domains.length;i++)
        {
            System.out.println("doumain[" + i + "]=" + domains[i] );
        }

        System.out.println("MBean count = " + mbsc.getMBeanCount());
        //����ָ��Mbean���ض�����ֵ
        //�����setAttribute��getAttribute����ֻ�����bean������
        //�����getName����setName���в�����ֻ��ʹ��Name����Ҫȥ��������ǰ׺
        mbsc.setAttribute(mbeanName, new Attribute("Name","����"));
        String name = (String)mbsc.getAttribute(mbeanName, "Name");
        System.out.println("name=" + name);

        HelloWorldMBean proxy = MBeanServerInvocationHandler.
                newProxyInstance(mbsc, mbeanName, HelloWorldMBean.class, false);
        proxy.printHello();
        proxy.printHello("migu");
        //invoke����bean�ķ�����ֻ��Է��������Եķ���
        //����invoke���ܶ�getName�������е���
        mbsc.invoke(mbeanName, "printHello", null, null);
    }
}
