


* JNDI        %TOMCAT_BASE%/conf/context.xml

<Resource auth="Container" driverClassName="com.mysql.jdbc.Driver" maxActive="100" maxIdle="30" maxWait="10000"
              name="jdbc/activemq" password="root" type="javax.sql.DataSource"
              url="jdbc:mysql://172.16.2.133:3306/trade?useUnicode=true&amp;characterEncoding=UTF-8"
              username="root"/>

<Resource auth="Container" driverClassName="com.mysql.jdbc.Driver" maxActive="100" maxIdle="30" maxWait="10000"
              name="jdbc/activemq" password="" type="javax.sql.DataSource"
              url="jdbc:mysql://localhost:3306/trade?useUnicode=true&amp;characterEncoding=UTF-8"
              username="root"/>





