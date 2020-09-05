package com.test.annotation.importAnno;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.test.annotation.importAnno")
@Import({ObjNormal.class, ObjImportSelector.class, ObjConfigrationAnno.class, ObjImportBeanDefinitionRegistrar.class})
public class ImportConfig {
}
