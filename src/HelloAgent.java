import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloAgent {

    public static void main(String[] args) throws Exception{
        int rmiPort = 1099;

        String jmxServerName = "JmxDemoServer";

        Registry registry = LocateRegistry.createRegistry(rmiPort);

        MBeanServer mbs = MBeanServerFactory.createMBeanServer(jmxServerName);
//        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName helloName = new ObjectName("jmxBean:name=hello");

        mbs.registerMBean(new HelloWorld(),helloName);
//        Thread.sleep(60*60*1000);

        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:"+rmiPort+"/"+jmxServerName);
        JMXConnectorServer jmxConnServer = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
        System.out.println("begin rmi start");
        jmxConnServer.start();
        System.out.println("rmi start");
    }
}
