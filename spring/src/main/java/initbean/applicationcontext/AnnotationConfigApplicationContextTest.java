package initbean.applicationcontext;

import initbean.JavaConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * spring生命周期围绕这5个类来讲
 *  org.springframework.context.annotation.internalConfigurationAnnotationProcessor -> {RootBeanDefinition@1051} "Root bean: class [org.springframework.context.annotation.ConfigurationClassPostProcessor]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null"
 *  org.springframework.context.event.internalEventListenerFactory -> {RootBeanDefinition@1053} "Root bean: class [org.springframework.context.event.DefaultEventListenerFactory]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null"
 *  org.springframework.context.event.internalEventListenerProcessor -> {RootBeanDefinition@1055} "Root bean: class [org.springframework.context.event.EventListenerMethodProcessor]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null"
 *  org.springframework.context.annotation.internalAutowiredAnnotationProcessor -> {RootBeanDefinition@1057} "Root bean: class [org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null"
 *  org.springframework.context.annotation.internalCommonAnnotationProcessor -> {RootBeanDefinition@1059} "Root bean: class [org.springframework.context.annotation.CommonAnnotationBeanPostProcessor]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null"
 *  org.springframework.context.annotation.internalRequiredAnnotationProcessor -> {RootBeanDefinition@1061} "Root bean: class [org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null"
 *
 */
public class AnnotationConfigApplicationContextTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(JavaConfig.class);

        context.refresh();
    }
}