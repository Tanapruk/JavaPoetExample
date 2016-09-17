
#[Java Poet](https://github.com/square/javapoet) Example

Use Java Library instead of Android because 
Javax.lang.model.element.Modifier is not available on Android.

###Create Java Module
File > New > New Module > Java Library 

###Add Dependencies
```
compile 'com.squareup:javapoet:1.7.0'
```

###Singleton 

###Singleton Builder
```Java
String className = "YourSingletonName";

FieldSpec fieldSpec = FieldSpec.builder(ClassName.bestGuess(className), "instance")
        .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
        .build();

MethodSpec methodSpec = MethodSpec.methodBuilder("getInstance")
        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
        .returns(ClassName.bestGuess(className))
        .beginControlFlow("if (instance == null)")
        .addCode("instance = new " + className + "();\n")
        .endControlFlow()

        .addStatement("return instance")
        .build();

TypeSpec typeSpec = TypeSpec.classBuilder(className)
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
