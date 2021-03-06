
#[Java Poet](https://github.com/square/javapoet) Example

Use Java Library instead of Android because 
Javax.lang.model.element.Modifier is [not available on Android](https://github.com/square/javapoet#methods).

###Create Java Module
File > New > New Module > Java Library 

###Add Dependencies
```
compile 'com.squareup:javapoet:1.7.0'
```

#Singleton 

###Singleton Builder
```Java
String className = "YourSingletonName";

 ClassName classNameObject = ClassName.bestGuess(className);
        FieldSpec fieldSpec = FieldSpec.builder(classNameObject, "instance")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                .build();

        MethodSpec methodSpec = MethodSpec.methodBuilder("getInstance")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(classNameObject)
                .beginControlFlow("if (instance == null)")
                .addCode("instance = new $T();\n", classNameObject)
                .endControlFlow()

                .addStatement("return instance")
                .build();

        TypeSpec typeSpec = TypeSpec.classBuilder(classNameObject)
                .addModifiers(Modifier.PUBLIC)
                .addField(fieldSpec)
                .addMethod(methodSpec)
                .build();
```

###Path
```Java
String mainPath = Paths.get(".").toAbsolutePath().normalize().toString();
String javaPath = "/app/src/main/java";
String completePath = mainPath + javaPath;
```

###Package Name
```Java
 String packageName = "com.tanapruk.javapoetexample";
 ```
        
###File Creation
```Java
        JavaFile javaFile = JavaFile
                .builder(packageName, typeSpec)
                .indent("    ")
                .build();
        javaFile.writeTo(new File(completePath));
```
