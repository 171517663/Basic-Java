nio
 java.nio.channels.Channel
     本地文件channel  |--FileChannel
     网络channel     |--SocketChannel
                    |--ServerSocketChannel
                    |--DatagramChannel

 获取通道的方法
      1.java通过支持io的类提供了getChannel()方法
         1.本地io
             FileInputStream/FileOutputStream
             RandomAccessFile
         2.网络io
             Socket
             ServerSocket
             DatagramSocket

      2.各个Channel提供了静态方法open()

      3.Files.newByteChannel()

        //selector.select()是阻塞方法，直到监听到事件返回
        //selector.select(longtime)阻塞时间longtime，未监听到事件责返回
        //selector.wakeup()阻塞在select()方法的selector返回
        //selector.selectNow()立刻返回
        //selector.selectedKeys().iterator()获取到监听到事件的selectedkeys
        //SelectionKey.channel()获取到对应的channel



==================================================================================================

线程池

    1.线程的创建和销毁耗费资源大，涉及到用户态内核态切换
    2.线程池适用于  单个线程处理时间短、需要线程数多的任务。
    
    优点：
        1.减少了重复创建和销毁线程的动作
        2.任务到的时候，不用再创建新线程，可以直接执行
        3.提高线程的可管理性
        
        ctrl+shift+alt+u
        ctrl+t
        
        public ThreadPoolExecutor(int corePoolSize,
                                  int maximumPoolSize,
                                  long keepAliveTime,
                                  TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue,
                                  ThreadFactory threadFactory,
                                  RejectedExecutionHandler handler)
         corePoolSize
         maximumPoolSize  
         keepAliveTime
         workQueue    
         
         向线程池添加任务===>达到数目corePoolSize===
         ====>向workQueue里面添加新的任务=======>队列满了创建新的线程===
         ====>达到maximumPoolSize数目=====>走拒绝策略（）
         
         =======>线程工作完了======>keepAliveTime时间都没有工作，回收最大线程     
         
===============================================================================================

SPI
    1.spi打破了双亲委派机制
        Thread.currentThread().setContextClassLoader();通过这个方法直接指定classloader加载/META-INF/services文件下的类。
        var2 = Class.forName(var1, false, this.loader);加载类，this.loader是线程上下文类加载器。
    2.spi使用的方法
        在/META-INF/services准备好文件
        a.Iterator<InterfaceA> providers = Service.providers(InterfaceA.class);
          while(providers.hasNext()) {InterfaceA ser = providers.next();ser.print();}
        b.ServiceLoader<InterfaceA> load = ServiceLoader.load(InterfaceA.class);
          Iterator<InterfaceA> iterator = load.iterator();
          while(iterator.hasNext()) {InterfaceA ser = iterator.next();ser.print();}

jdbc中的spi
    java.sql.DriverManager通过spi机制装载数据库驱动类，
    数据库驱动在静态代码块中，把自己注册到DriverManager中，如下：
```java
public class Driver extends NonRegisteringDriver implements java.sql.Driver {
    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException var1) {
            throw new RuntimeException("Can't register driver!");
        }
    }
}
```
        
==================================================================================================

ClassLoader
    ClassLoader的各子孙类继承loadClass方法并且通常不会复写（Overriding），
    所以每个类加载器的loadClass方法都会先调用parent类加载器（各种类型的类加载器在构造时都会传入一个parent类加载器）
    的loadClass进行加载，失败之后才会调用本类的findClass进行加载，这种工作模式被称为“双亲委派模型”。

==================================================================================================
CGLIB
    FastClass机制：将类的方法信息解析出来，然后为其建立索引。调用的时候，只要传索引，就能找到相应的方法进行调用。
    1.为所有的方法建立索引
    2.调用前先根据方法信息寻找到索引
    3.调用时根据索匹配相应的方法进行直接调用
    CGLIB在字节码层面将方法和索引的对应关系建立，避免了反射调用

===================================================================================================
Lock
    ReentrantLock公平锁？非公平锁？
        公平锁获取锁
        final void lock() {
            acquire(1);
        }
        非公平锁获取锁
        final void lock() {
            if (compareAndSetState(0, 1))
                setExclusiveOwnerThread(Thread.currentThread());    //先直接CAS获取锁，获取失败再进行排队
            else
                acquire(1);
        }
        非公平锁第一次获取锁的时候，先直接CAS获取锁，获取失败再进行排队。
     AQS？
        获取锁
        public final void acquire(int arg) {
            if (!tryAcquire(arg) &&         //该方法由各个锁自己实现，获取锁。
                acquireQueued(addWaiter(Node.EXCLUSIVE), arg))  //AQS实现，获取锁失败，进入这个方法。将线程排队，阻塞线程。
                selfInterrupt();
        }
        释放锁
        public final boolean release(int arg) {
            if (tryRelease(arg)) {          //该方法由各个类自己实现，释放锁。
                Node h = head;
                if (h != null && h.waitStatus != 0)
                    unparkSuccessor(h);     //AQS实现，如果排队的线程不为空，则按队列顺序唤醒线程。
                return true;
            }
            return false;
        }
        //Acquires in shared mode, aborting if interrupted.        
        public final void acquireSharedInterruptibly(int arg)
                throws InterruptedException {
            if (Thread.interrupted())
                throw new InterruptedException();
            if (tryAcquireShared(arg) < 0)      //tryAcquireShared(arg)由各个类实现，
                                                  当他大于0的时候代表Attempts to acquire in shared mode成功
                doAcquireSharedInterruptibly(arg);      //阻断当前线程
        }
        public final boolean releaseShared(int arg) {
            if (tryReleaseShared(arg)) {  //如果返回true，则permit a waiting acquire (shared or exclusive) to succeed
                doReleaseShared();
                return true;
            }
            return false;
        }     		


==========================================================================================================
Collection
    queue队列：FIFO
    stack堆栈：FILO
    
SynchronousQueue
    public void put(E e)        //Adds the specified element to this queue, waiting if necessary for
                                * another thread to receive it.
    public boolean offer(E e)       //Inserts the specified element into this queue, if another thread is
                                    * waiting to receive it.立刻返回
    public E take()     //Retrieves and removes the head of this queue, waiting if necessary
                        * for another thread to insert it.
    public E poll()     //Retrieves and removes the head of this queue, if another thread
                        * is currently making an element available.立刻返回

HashMap
    数组+链表
    通过key的hash快速找到对应的值
    

==========================================================================================================
redis
    启动服务：redis-server redis.windows.conf
    客户端连接redis服务：redis-cli -p port
    shutdown关闭连接，exit退出
    用例eg：
		----
		字符串
        set key value   (set sex male)
        get key         (get sex)
        select 8 切换到第八个数据库(databases 16  默认16个数据库)
        dbsize  当前数据库数据数量
        keys *      查看所有key值
        flushdb     清空当前库
        flushall    清空所有库
        exist key   (exist sex)存在则返回1
        move key 7  把key值数据移动到7号数据库
        expire key 10   (expire sex 10)     key数据10s后过期
        ttl key     (ttl sex)   查看还有多少时间过期
        type key    查看数据类型
        append key str      追加字符串
        strenlen key    查看字符串长度
        incr    自加1
        decr    自减1
        incrby key 5   自增5
        decrby key 5   自减5
        getrange key 3 5    截取第4-6个字符串
        getrange key 3 -1   从第4个字符串开始一直截取到最后一个字符串
        setrange key 5 str  从第6个字符串开始替换str
        setex key time value    设置key的值为value，同时超时时间为time
        setnx key value     如果值不存在，设置key,value
        mset k1 v1 k2 v2 k3 v3  设置map
        mget k1 k2 k3
        msetnx  k1 v1 k2 v2     不存在则设置，原子操作
        getset key newvalue     获取值打印，并设置新值
		----
		列表
        lpush list11 v1 v2 v3   从左边加入list
        lrange list1 0 -1       打印list所有值
        rpush list2 v1 v2 v3    从右边加入list
        lpop list2              从左边移除一个
        rpop list2              从右边移除一个
        lrem list1 num value (lrem list1 2 a)       移除list1中值为a的2个数据
        ltrim list1 1 2         截取list1中第2-3个元素到list1中
        lpoplpush list1 list2   从list1移动到list2
        lset list index value (lset list1 2 aa)     设置下标为2的值为value
        linset list before value1 value2     在value1前面插入value2
		----
		集合
        sadd myset a            在set集合myset中加入a
        smembers myset          打印myset
        sismember myset a       a是不是myset的元素？
        srem myset a            移除a
        srandmember myset 2     从myset随机取两个值
        spop myset              移除一个元素
        smove set1 set2 value   set1的value移动到set2
        sdiff set1 set2         set1和set2的差集
        sunion set1 set2        并集
        sinter set1 set2        交集
		----
		哈希命令
        hset myhash field1 value1
        hget myhash field
        hmset myhash field1 value1 field2 value2
        hmget myhash field1 field2
        hgetall myhash
        hdel myhash field1 
        hlen myhash
        hexists myhash field1
        hkeys myhash
        hvals myhash
        hincrby myhash field1 2     自增2
        hestnx myhash field1 aaa
		----
		有序集合
        zadd myzset v1 v2                   向有序set加元素
        zrangebyscope myzset -inf +inf      升序
        zrangebyscope myzset +inf -inf      降序
        zrangebyscope myzset -inf +inf withscores
	----
	持久化机制
		rdb
		----
			RDB持久化是指在指定的时间间隔内将内存中的数据集快照写入磁盘，实际操作过程是fork一个子进程，先将数据集写入临时文件，
			写入成功后，再替换之前的文件，用二进制压缩存储。		
			----
			配置策略
				save 60 900 （60秒内修改900次则触发rdb）
			----
			适合大规模的数据恢复
			适合对数据的完整性要不高的场景
			间隔一段时间进行，如果最后一次宕机了，最后一次的数据就丢失了
			效率高，可以极大的避免服务进程执行IO操作。
			由于RDB是通过fork子进程来协助完成数据持久化工作的，因此，如果当数据集较大时，可能会导致整个服务器停止服务几百毫秒，甚至是1秒钟。
		----
		aof
		AOF持久化以日志的形式记录服务器所处理的每一个写、删除操作，查询操作不会记录，以文本的方式记录，可以打开文件看到详细的操作记录。
			----
			在Redis的配置文件中存在三种同步方式，它们分别是：
			appendfsync always     #每次有数据修改发生时都会写入AOF文件。
			appendfsync everysec  #每秒钟同步一次，该策略为AOF的缺省策略。
			appendfsync no          #从不同步。高效但是数据不会被持久化
			appendfilename "file.aof"	持久化文件名
			----
			myfile.aof文件被破坏时，可以用工具修复
			redis-check-aof --fix myfile.aof
			----
			该机制可以带来更高的数据安全性，即数据持久性。
			每秒同步也是异步完成的，其效率也是非常高的，所差的是一旦系统出现宕机现象，那么这一秒钟之内修改的数据将会丢失。
			而每修改同步，我们可以将其视为同步持久化，即每次发生的数据变化都会被立即记录到磁盘中。可以预见，这种方式在效率上是最低的。
		----
		对于相同数量的数据集而言，AOF文件通常要大于RDB文件。RDB 在恢复大数据集时的速度比 AOF 的恢复速度要快。
		根据同步策略的不同，AOF在运行效率上往往会慢于RDB。总之，每秒同步策略的效率是比较高的，同步禁用策略的效率和RDB一样高效。
		当两种方式同时开启时，数据恢复Redis会优先选择AOF恢复。
		----
		rdb经常在主从复制中使用，rdb文件在从机上面
		如果enable AOF丢失数据不会超过2s
		如果不enable aof，仅靠Master-Slave Replication也可以实现高可用，还可以省掉一大笔IO操作。但是主机从机都宕机，会丢失十几分钟的数据。启动的时候，会比较
		两个RDB文件，用较新的那个，微博就是这种架构。
		----
	----
	redis乐观锁
		悲观锁：认为什么时候都会出现问题，无论做什么都会加锁
		乐观锁：认为什么时候都不会出问题，所以不会上锁。更新数据的时候去判断一下在此期间是否有人更新过数据
		----
		Redis事务命令主要包括 WATCH, EXEC, DISCARD, MULTI。
		----
		1）Watch 命令是Exec命令的执行条件；也就是说，如果Watch的Key没有被修改则Redis执行事务，否则（Watch的key被其他事务修改了）事务不会被执行。
		2）Watch 命令可以被调用多次，一个Watch 命令可以监控多个key。Watch 命令调用即启动监控功能，从Watch 命令开始点到执行EXEC命令终止。
		   一旦EXEC被调用，所有的键都将不被监视，无论所讨论的事务是否被中止。关闭客户端连接也会触发所有的键被取消监视。
		3）Watch 命令给事务提供check-and-set (CAS) 机制。
		watch key
		unwatch，命令用于取消事务，放弃执行事务块内的所有命令
		如果在执行 WATCH 命令之后， EXEC 命令或 DISCARD 命令先被执行了的话，那么就不需要再执行 UNWATCH 了。	
	----
    redis事务
        redis的事务不保证原子性
        事务所有的命令会被序列化
        事务没有隔离级别的概念
            multi   //开启事务
            命令1
            命令2
            ...
            exec    //执行事务
            discard //取消事务
            watch key   //watch相当于redis的乐观锁
    springboot 2.x版本之后用lettuce
                     之前用redis
    redis订阅发布
		subscribe huilin
		publish huilin "mymessage"
	redis集群环境
		主从复制、读写分离：只有主节点能写，数据的复制是单向的，只有主节点能写到从节点，从节点处理读请求、不处理写请求
		只有从节点需要配置,命令info replication查看当前节点状态	//可发现默认配置是主节点
			127.0.0.1:6379[2]> info replication
			# Replication
			role:master		//当前节点是主节点
			connected_slaves:0		//从节点为0个
		如何配置从节点？
			命令配置(暂时的)
			slaveof 127.0.0.1 6379		//当前节点配置为127.0.0.1:6379的从节点
			info replication			//查看当前节点状态
			文件配置（永久的）
			service.conf中配置replicaof ip:port
		主节点宕机再连接，依旧满足主从规则。从节点宕机，再连接，缺失的数据会补齐。（因为只要重新连到主机，会自动全量复制）
		当一个节点既是主节点又是从节点的时候，主节点宕机，这个中间节点
			slaveof no one，转为主节点。
		哨兵模式？
			自动选主节点的模式。
			创建哨兵配置文件	sentinel.conf  sentinel monitor myredis 127.0.0.1 6379 1 //监控主节点6379，如果主节点挂掉会在从节点中选出一个当主节点
																							6379再起来也不会是主节点，而是从节点。
			启动哨兵			redis-sentinel sentinel.conf
        缓存雪崩是什么？如何应对？
        缓存击穿是什么？如何应对？
        
==================================================================================================================
LongAdder和AtomicLong？
    AtomicLong->循环执行CAS，直到执行成功。
    LongAdder->CAS更新值->成功：更新到base字段；失败：继续执行->更新到Cell[] cells数组。

================================================================================================================
memcached
    启动：memcached -u root -p 11211 -d
    连接服务：telnet -p 127.0.0.1 11211            
    命令：states
		  get
		  set   //set 0 5 6  (5s过期，6长度)
				//123456	 设置6长度的值
		  append
		  cas
		  delete
		  flash_all
	memcached存储的数据在重启之后就没有了
	memcached申请内存以slab page为单位申请：1M
			 分配到对应的slab class 
			 slab class：里面有多个slab page
			 slab page：最大为1M，分配到1个或多个chunk
			 chunk：实际存储单位为96-1M，由生长因子决定
	memcached数据缓存策略
			 LRU分段缓存策略
			 temp 存放定时失效数据
			 hot
			 warm
			 cold
	集群方案
			-客户端支持
			客户端根据key通过hash算法或者其他一致性算法，选择server
			-Twemproxy
			代理中间件帮我们选择server
			一致性hash算法->一致性hash环，顺时针命中离自己最近的服务器，增加一个服务器，只有1/2n的点会失效，命中不均匀
			->如何解决命中均匀？->提前分配虚拟节点，但是也不能保证绝对均匀。->ConsistenHash提供一致性hash算法
        
===============================================================================================================================
集群	主从
高可用	redis哨兵        
        
================================================================================================================================       
高并发缓存架构
	浏览器有缓存：浏览器发请求->后台返回响应状态->浏览器取用缓存
        
================================================================================================================================
springboot
	----
	指定了版本。没有指定的jar包，需要自己指定版本
		<parent>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-parent</artifactId>
			<version>2.3.1.RELEASE</version>
			<relativePath/> <!-- lookup parent from repository -->
		</parent>
	----
	@EnableAutoConfiguration 开启自动配置功能
	@AutoConfigurationPackage 扫描主类同目录的包，加入到IOC容器
	@Import(AutoConfigurationImportSelector.class) 加载META-INF/spring.factories文件中key值为EnableAutoConfiguration.class的类到IOC容器
	----
	controller
		@ResponseBody 返回的数据直接写给浏览器
		----
		@RestController等价于@Controller + @ResponseBody
	----
	@EnableAutoConfiguration
		|--	@AutoConfigurationPackage
		|		|--@Import(AutoConfigurationPackages.Registrar.class)
		|--@Import(AutoConfigurationImportSelector.class)
	----
	application.yml文件
		@ConfigurationProperties(prefix = "yamlobj")
		生效的前提是引入依赖：
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-configuration-processor</artifactId>
				<optional>true</optional>
			</dependency>
		----
		JSR303数据校验
			类上面@Validated
		----
		@PropertySource 加载 properties、yml 配置文件
		@ImportResource 加载xml配置文件
		----
		yml文件
			${person.last-name} 自己定义的值
			${random.uuid} 系统提供的值
		----
		profiles
			例子
			server:
				port: 8080
			spring:
				profiles:
					active: test    # 切换配置
			---
			# 开发环境
			server:
				port: 8081
			spring:
				profiles: dev
			---
			# 测试环境
			server:
				port: 8082
			spring:
				profiles: test
			---
			# 生产环境
			server:
				port: 8083
			spring:
				profiles: prop
		----
		运行jar包
		java -jar 包名 --spring.profiles.active=dev
		----
		配置文件生效的位置
			file:./config/
			file:./
			classpath:/config/
			classpath:/
		----
		配置优先级
			1.命令行最高
			2.jar包外部的配置文件
			3.jar包内部的配置文件
		----
		查看生效的配置
			debug=true，控制台打印生效的配置类
	----
	xxxAutoConfiguration
		实例解析
		@Configuration(proxyBeanMethods = false)	标明是一个配置类，也可以给IOC容器添加组件
		@EnableConfigurationProperties(CacheProperties.class)	启用指定类xxxProperties.class的@ConfigurationProperties功能
		spring底层@Conditional注解，根据不同条件，如果满足就配置生效
		@ConditionalOnClass(CacheManager.class)		判断有没有CacheManager.class类
		@ConditionalOnBean(CacheAspectSupport.class)	判断ioc容器中有没有CacheAspectSupport的bean实例
		@ConditionalOnMissingBean(value = CacheManager.class, name = "cacheResolver")	
		@AutoConfigureAfter({ CouchbaseAutoConfiguration.class, HazelcastAutoConfiguration.class,
				HibernateJpaAutoConfiguration.class, RedisAutoConfiguration.class })
		@Import({ CacheConfigurationImportSelector.class, CacheManagerEntityManagerFactoryDependsOnPostProcessor.class })
		public class CacheAutoConfiguration {}
	xxxProperties.class配合使用
	----
	日志
		日志框架
		日志门面 （日志的抽象层）					日志实现
		JCL(Jakarta Commons Logging) 				Log4j JUL(java.util.logging) Log4j2 Logback
		SLF4j(Simple Logging Facade for Java)		
		jboss-logging
		----
		日志的选择
			springboot：底层是spring框架，spring默认是jcl
			springboot默认选择SLF4J和logback
		----
			<dependency>
			  <groupId>org.springframework.boot</groupId>
			  <artifactId>spring-boot-starter-logging</artifactId>
			</dependency>
			|依赖
			↓
			<dependencies>
				<dependency>
				  <groupId>ch.qos.logback</groupId>
				  <artifactId>logback-classic</artifactId>
				</dependency>
				<dependency>
				  <groupId>org.apache.logging.log4j</groupId>
				  <artifactId>log4j-to-slf4j</artifactId>
				</dependency>
				<dependency>
				  <groupId>org.slf4j</groupId>
				  <artifactId>jul-to-slf4j</artifactId>
				</dependency>
			</dependencies>
			|依赖
			↓
			<dependency>
			  <groupId>org.slf4j</groupId>
			  <artifactId>slf4j-api</artifactId>
			</dependency>
			底层真正用得是SLF4J+logback
		----
		三方件用其他组件记录日志怎么办？
			把这个框架依赖的日志框架排除掉
			<dependency>
				<groupId>xxxx</groupId>
				<artifactId>xxxx</artifactId>
				<exclusions>
					<exclusion>
						<groupId>comms-logging</groupId>
						<artifactId>comms-logging</artifactId>
					</exclusion>
				</exclusions>
			</dependency>
		----
		如何切换到spring-boot-starter-log4j2？
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-web</artifactId>
				<exclusions>
					<exclusion>
						<artifactId>spring-boot-starter-logging</artifactId>
						<groupId>org.springframework.boot</groupId>
					</exclusion>
				</exclusions>
			</dependency>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-log4j2</artifactId>
			</dependency>
		----
		log的配置
			----
			Depending on your logging system, the following files are loaded:
			
			Logging System				Customization
			
			Logback						logback-spring.xml, logback-spring.groovy, logback.xml, or logback.groovy

			Log4j2						log4j2-spring.xml or log4j2.xml

			JDK (Java Util Logging)		logging.properties
			----
			1.直接在application.yml中配置
			2.在logback.xml中配置，能直接被日志框架识别
			3.在logback-spring.xml中配置，被SpringBoot识别。支持profiles。
				<springProfile name="dev | staging"></springProfile>
	----
	web
		----
		webjars
			所有的webjars都在classpath:/META-INF/resources/webjars/目录下找资源
			用依赖的方式导入
		----
		静态资源
			file:./config/
			file:./
			classpath:/config/
			classpath:/
		访问的时候，localhost:8080/xxx,对于xxx文件会去静态资源路径下去找
		----
		index.html
			访问localhost:8080，会去静态资源路径下找index.html
		----
		模板引擎
			springboot不支持jsp，用什么代替？模板引擎。
			----
			什么是模板引擎？
			JSP、Thymeleaf、Freemarker、Velocity
			总体思想：template+data------->输出页面
			----
			springboot提供thymeleaf
			----
		Thymeleaf
			----
			"classpath:/templates/"目录下生效
			----
			<html lang="en" xmlns:th="http://www.thymeleaf.org">引入
			----
		Spring MVC Auto-configuration
			The auto-configuration adds the following features on top of Spring’s defaults:
				
			Inclusion of ContentNegotiatingViewResolver and BeanNameViewResolver beans.
				自动配置了ViewResolver（视图解析器，根据方法的返回值得到视图解析对象view，视图对象决定如何渲染（转发？重定向？））
				ContentNegotiatingViewResolver：组合所有的视图解析器的
				如何定制：可以自己定制实现ViewResolver接口给容器中添加视图解析器，ContentNegotiatingViewResolver将其组合起来。
			Support for serving static resources, including support for WebJars (covered later in this document)).
				支持静态资源，包括webjars
			Automatic registration of Converter, GenericConverter, and Formatter beans.
				Converter：转化器，浏览器传过来的数据 类型转换使用
				GenericConverter：
				Formatter：格式化器，比如页面数据2020-01-01，我们要转换成Date
			Support for HttpMessageConverters (covered later in this document).
				HttpMessageConverters：springmvc用来转换http请求和响应的。
				自己给容器中加HttpMessageConverter，注册（@Bean）
			Automatic registration of MessageCodesResolver (covered later in this document).
				MessageCodesResolver定义错误代码生成规则（303校验）
			Static index.html support.
				支持首页index.html
			Custom Favicon support (covered later in this document).
				支持用户自定义图标Favicon
			Automatic use of a ConfigurableWebBindingInitializer bean (covered later in this document).
				我们可以配置自己的ConfigurableWebBindingInitializer
			----
			org.springframework.boot.autoconfigure.web：web所有的自动场景
			----
			@EnableWebMvc 表示全面接管springmvc配置
			----
			实现WebMvcConfigurer接口，定义自己的配置

=================================================================================================================================
spring
	@Import注解，把实例加入spring的IOC容器中的方式
		1、直接填class数组方式，将class加入到IOC容器
		2、ImportSelector方式，获取selectImports()方法返回的类名，来将类加入到IOC容器
		3、ImportBeanDefinitionRegistrar方式
			{RootBeanDefinition root = new RootBeanDefinition(TestB.class);
			 registry.registerBeanDefinition("testB", root);}
			上面例子将TestB.class加入到IOC容器。
	----
	@Bean
		1.@Configuration
		  public class AppConfig {
		  	@Bean
		  	public MyService myService() {
		  		return new MyServiceImpl();
		  	}
		  }
		  等价于
		  <beans>
		  	<bean id="myService" class="com.acme.services.MyServiceImpl"/>
		  </beans>
		2.带参的方法
		  @Bean
		  public MyService myService(User user)
		  要求User类以及注入容器
	----
	@Value
		1.@Value(${}) 对应到application.yml里面的值
		2.@Value(#{}) SpEL表达式
		3.@Value("string")

===============================================================================================================
docker
	类似于虚拟机，但不是虚拟机，是一个轻量级容器技术，实现了虚拟机技术里面的资源隔离。
	性能远高于虚拟机。
	----
	linux安装mysql，配置好----->用docker打包成镜像----->发布镜像---->新环境想要安装mysql，直接用docker运行mysql镜像
	----
	核心概念
		docker主机：安装了docker程序的机器（docker直接安装在操作系统之上）
		docker客户端：连接docker主机进行操作
		docker仓库：保存各种打包好的软件镜像
		docker镜像：软件打包好的镜像
		docker容器：镜像启动后的实例称为一个容器。容器式独立运行的一个或一组应用
	----
	使用docker步骤
		1.安装docker
		2.去docker仓库找到软件对应的镜像
		3.使用docker运行这个镜像，这个镜像就会生成一个docker容器
		4.对容器的启动停止，就是对软件的启动停止
		----
		安装docker
		1.uname -r  查看内核版本，需要高于3.10
		2.yum install docker  安装docker
		3.systemctl start docker
		4.docker -v   查看版本
		5.systemctl enable docker  开机就启动docker
		6.systemctl stop docker
		----
		用docker安装软件
		docker search mysql				查找镜像
		docker pull mysql:版本号 		下载镜像
		docker images					查看所有镜像
		docker rmi + imageid			删除镜像
		----
		用docker运行镜像
		docker --name mytomcat -d tomcat:latest		mytomcat是给这个镜像实例取的名字，-d表示后台运行
		docker ps				查看运行中容器的状态
		docker stop names/containerid
		docker ps -a			查看所有docker容器，包括停止运行的
		docker rm + id			删除容器
		docker --name myname -d -p 8081:8080 tomcat:latest		把虚拟机的8081映射到容器的8080端口
		service firewalld status
		service firewalld stop
		docker log  +containerID		查看容器启动日志
		一个镜像可以启动多个容器，每个容器都是独立的互不干扰
		启动mysql实例 docker --name mysql01 -d -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 tomcat	其中-e表示加参数
		docker xxxxxxx -v /conf/mysql:/etc/mysql/conf.d tomcat  其中-v表示把主机的文件夹/conf/mysql挂载到/etc/mysql/conf.d文件夹下面，可更改mysql配置文件
====================================================================================================================
Git
	git status
	git add filename.java			提交到暂存区
	git commit -m "提交描述"
	下载仓库git clone https://github.com/171517663/MyJava
	删除文件 1.先在本地删除  2.git rm 文件名  3.git commit -m '描述字符串' 
	git push
	查看配置信息  git config --list
	---
	git config  credential.helper store
	这里没有--global意思是指只对这个仓库生效，建议以后都不要加--global
	
=================================================================================================================================
springboot工程笔记
	1.导入网页模板
	2.配置好index.html页面
		thymeleaf解析的html页面需要这个标签：<html lang="en" xmlns:th="http://www.thymeleaf.org">
		方法一：
			@Controller
			public class HelloController {
				@RequestMapping({"/", "/index.html"})
				public String index() {
					return "index";
				}
			}
		方法二：
		    @Bean
			public WebMvcConfigurer webMvcConfigurer() {
				WebMvcConfigurer config = new WebMvcConfigurer() {
					@Override
					public void addViewControllers(ViewControllerRegistry registry) {
						registry.addViewController("/").setViewName("index");
						registry.addViewController("/index.html").setViewName("index");
					}
				};
				return config;
			}
		方法三：
			@Configuration
			public class MyMvcConfig implements WebMvcConfigurer {
				@Override
				public void addViewControllers(ViewControllerRegistry registry) {
					registry.addViewController("/").setViewName("index");
					registry.addViewController("/index.html").setViewName("index");
				}
			}
		所有自定义的WebMvcConfigurer一起起作用
	3.引入webjars
		webjars资源访问规则：/webjars/**===>/META-INF/resources/webjars/*
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>bootstrap</artifactId>
			<version>4.5.2</version>
		</dependency>
		---
		thymeleaf
		<link href="asserts/css/bootstrap.min.css" th:href="@{bootstrap/4.5.2/css/bootstrap.css}" rel="stylesheet">
		@{}获取连接
		#{}获取国际化信息
		
	4.编写国际化文件
		springboot提供的管理国际化资源文件的类MessageSourceAutoConfiguration.class
		----
		安排好国际化文件
		login_en.properties
		login_en_US.properties
		login_zh_CN.properties
		login.properties
		在application.properties中设置spring.messages.basename=i18n.login
		----
		请求头决定了用哪种语言显示，可以用浏览器调语言
		Accept-Language: zh-CN,zh;q=0.9,en;q=0.8    #这个表明优先zh-CN、其次zh
	5.自定义LocaleResolver
		1.Locale resolveLocale(HttpServletRequest httpServletRequest)   返回自己的Locale
			根据请求带的信息来区分区域信息
			    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
					String l = httpServletRequest.getParameter("l");
					Locale locale = Locale.getDefault();
					if(!StringUtils.isEmpty(l)) {
						String[] str = l.split("_");
						locale = new Locale(str[0], str[1]);
					}
					return locale;
				}
		2.把自己定义的LocaleResolver传入到IOC容器。
	6.提交登陆功能
		设置好表单提交的url和method
		<form class="form-signin" action="/user/login" method="post">
		---
		后台接收方法写好
		@PostMapping("/user/login")
		public String login(String username, String password, Map map) {
			if("123456".equals(password) && !StringUtils.isEmpty(username)) {
				return "dashboard";
			} else {
				map.put("msg", "登陆错误");
				return "login";
			}
		}
		---
		tips：spring.thymeleaf.cache=false禁用缓存
			  ctrl+F9
			  不同重启，可以直接刷新静态资源
	7.登陆失败提示
		<p style="color:red" th:text="${msg}" th:if="${not #strings.isEmpty(msg)}"></p>
	8.表单重复提交？
		当重复访问localhost:8080/user/login的时候，会重发送登陆请求
		localhost:8080/user/login----》重定向到localhost:8080/user/main.html---》转发到dashboard.html
		----
		转发：由服务器端进行的页面跳转
			地址栏不发生变化，显示的是上一个页面的地址
			请求次数：只有1次请求
			根目录：http://localhost:8080/项目地址/，包含了项目的访问地址
			请求域中数据不会丢失
		重定向：由浏览器端进行的页面跳转
			地址栏：显示新的地址
			请求次数：2次
			根目录：http://localhost:8080/ 没有项目的名字
			请求域中的数据会丢失，因为是2次请求
			区别			转发forward()		重定向sendRedirect()
			根目录			包含项目访问地址	没有项目访问地址
			地址栏			不会发生变化		会发生变化
			哪里跳转		服务器端进行的跳转	浏览器端进行的跳转
			请求域中数据	不会丢失			会丢失
	9.拦截器
		防止不用登陆就能访问到。
		注册拦截器，并指定拦截的路径、排除拦截的路径。其中，new LoginHandlerInterceptor()中定义了拦截规则。
		    public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                    .excludePathPatterns("/", "/login.html", "/index.html", "/webjars/**",
                            "/user/login", "/**/*.css",
                            "/**/*.js", "/**/*.png", "/**/*.jpg",
                            "/**/*.jpeg", "/**/*.gif");
            }
	10.	CRUD-员工管理页面
		指定好员工管理页面的跳转链接<a class="nav-link" href="#" th:href="@{/emps}">
		后台接受请求，响应
	11.thymeleaf公共页面抽取
		1.抽取公共片段
		<div th:fragment="topbar">
			xxxxxx
		</div>	
		2.插入片段
		遵循thymeleaf文件名解析规则
		<div th:insert="footer :: copy"></div> 
		~{templateName::selector}
		~{templateName::fragmentName}
		----
		th:insert是最简单的：它将简单地插⼊指定宿主标签的标签体中。 
		th:replace实际上⽤指定的⽚段替换其宿主标签。 
		th:include类似于th:insert，⽽不是插⼊⽚段，它只插⼊此⽚段的内 容。
		<body>
			<div th:insert="footer :: copy"></div> 
			<div th:replace="footer :: copy"></div> 
			<div th:include="footer :: copy"></div> 
		</body>
		效果等价于
		<body>
		... 
			<div> <footer> &copy; 2011 The Good Thymes Virtual Grocery </footer> </div> 
			<footer> &copy; 2011 The Good Thymes Virtual Grocery </footer>
			<div> &copy; 2011 The Good Thymes Virtual Grocery </div> 
		</body>
	12.展示员工信息
		<tbody>
			<tr th:each="emp:${emps}" >
				<td th:text="${emp.id}"></td>
				<td>[[${emp.lastName}]]</td>
				<td th:text="${emp.email}"></td>
				<td th:text="${emp.gender}=='0'?'女':'男'"></td>
				<td th:text="${emp.department.departmentName}"></td>
				<td th:text="${#dates.format(emp.birth, 'dd/MMM/yyyy HH:mm')}" ></td>
				<td>
					<button class="btn btn-sm btn-primary">编辑</button>
					<button class="btn btn-sm btn-danger">删除</button>
				</td>
			</tr>
		</tbody>
	13.错误页面处理机制
		浏览器
			接收error页面
		客户端
			接收json串
		如何区分浏览器和客户端？
			Content-Type: text/html;charset=UTF-8，浏览器请求头优先接收html数据
			客户端不指定接收数据类型
	14.配置嵌入式servlet
		定制配置
		@Bean
		WebServerFactoryCustomizer webServerFactoryCustomizer() {
			return new WebServerFactoryCustomizer() {
				public void customize(WebServerFactory factory) {
					((ConfigurableWebServerFactory )factory).setPort(8081);
				}
			};
		}
		注意：
			xxxxCustomizer用来定制配置，用代码设置一些初始值
			xxxxConfigurer用来帮我们扩展配置，
		-----
	15.注册servlet,filter,listener
		ServletRegistrationBean
		FilterRegistrationBean
		ServletListenerRegistrationBean
			---
			注册servlet
			@Bean
			ServletRegistrationBean servletRegistrationBean() {
				ServletRegistrationBean servlet = new ServletRegistrationBean(new Myservlet(), "/myServlet");
				return servlet;
			}
			这个servlet与DispatcherServlet同为servlet，是最基本的web开发中的
	16.使用其他Servlet容器
		Jetty(长链接，长时间连接)
		Undertow(不支持jsp，高性能、非阻塞、并发性能好)
		F4查看类继承关系
		----
		先排除tmcat依赖
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                    <groupId>org.springframework.boot</groupId>
                </exclusion>
            </exclusions>
        </dependency>
		引入jetty
		<dependency>
            <artifactId>spring-boot-starter-jetty</artifactId>
            <groupId>org.springframework.boot</groupId>
        </dependency>
	17.嵌入式servlet容器自动配置
		@Configuration(proxyBeanMethods = false)
		class ServletWebServerFactoryConfiguration {
		@Configuration(proxyBeanMethods = false)
		@ConditionalOnClass({ Servlet.class, Tomcat.class, UpgradeProtocol.class })
		@ConditionalOnMissingBean(value = ServletWebServerFactory.class, search = SearchStrategy.CURRENT)
		//之前没有自己定义的嵌入式容器工厂ServletWebServerFactory.class
		static class EmbeddedTomcat {
			@Bean
			TomcatServletWebServerFactory tomcatServletWebServerFactory(
					ObjectProvider<TomcatConnectorCustomizer> connectorCustomizers,
					ObjectProvider<TomcatContextCustomizer> contextCustomizers,
					ObjectProvider<TomcatProtocolHandlerCustomizer<?>> protocolHandlerCustomizers) {
				TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
				factory.getTomcatConnectorCustomizers()
						.addAll(connectorCustomizers.orderedStream().collect(Collectors.toList()));
				factory.getTomcatContextCustomizers()
						.addAll(contextCustomizers.orderedStream().collect(Collectors.toList()));
				factory.getTomcatProtocolHandlerCustomizers()
						.addAll(protocolHandlerCustomizers.orderedStream().collect(Collectors.toList()));
				return factory;
			}

		}}
	18.使用外置的servlet容器
		内置servlet容器
			优点：简单、便捷
			缺点：不支持jsp、优化定制复杂
		外置servlet使用方法
			将springboot打包成war包----》装载到tomcat容器---》启动tomcat容器
			pom.xml里面tomcat指定成<scope>provided</scope>
		jar包：sprongboot启动---》启动IOC容器----》创建嵌入式servlet容器
		war包：启动服务器tomcat---》服务器启动springboot应用【springbootservletInitializer】----》启动ioc容器
===========================================================================================================================
SpringData
	1.springboot配置mysql
		spring:
		  datasource:
			password: 123456
			username: root
			url: jdbc:mysql://127.0.0.1:3306/jdbc?serverTimezone=UTC
			driver-class-name: com.mysql.jdbc.Driver
		导入依赖
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
	2.	
		
		
=================================================================================================================================		
数据库基本概念
	1.最基本的连接数据库方法
		//1、注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//数据库连接所需参数
		String user = "root";
		String password = "123456";
		String url = "jdbc:mysql://localhost:3306/sampledb?useUnicode=true&characterEncoding=utf-8";
		//2、获取连接对象
		Connection conn = DriverManager.getConnection(url, user, password);
	2.连接池
		原始的：应用程序----》从DriverManager获取连接
		连接池：应用程序----》从DataSource获取连接----》从连接池获取连接《-----DriverManager将连接放到连接池
	3.DataSource的形式是JNDI （Java Naming Directory Interface）
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		














		
			
           