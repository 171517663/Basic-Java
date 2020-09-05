package com.test.annotation.importAnno;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class ObjImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String[] strArry = new String[]{"com.test.annotation.importAnno.TestA"};
        return strArry;
    }
}
