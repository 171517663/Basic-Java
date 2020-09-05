package proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class BirdProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib:========>这里是对目标类进行增强！！！");
        System.out.println(obj.getClass());
        System.out.println(method.getName());
        System.out.println(objects);
        System.out.println(methodProxy.getClass());
        Object object = methodProxy.invokeSuper(obj, objects);
        new Exception("hahahaha").printStackTrace();
        System.out.println(objects.length);
        return object;
    }
}
