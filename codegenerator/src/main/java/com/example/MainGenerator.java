package com.example;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.lang.model.element.Modifier;

public class MainGenerator {

    public static void main(String[] args) throws IOException {
        createSingleton("NextzySingleton0");
        createSingleton("NextzySingleton1");
        createSingleton("NextzySingleton2");
        createSingleton("NextzySingleton3");
    }

    public static void createSingleton(String className) throws IOException {
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

        String packageName = "com.tanapruk.javapoetexample";

        String mainPath = Paths.get(".").toAbsolutePath().normalize().toString();
        String javaPath = "/app/src/main/java";
        String completePath = mainPath + javaPath;

        JavaFile javaFile = JavaFile
                .builder(packageName, typeSpec)
                .indent("    ")
                .build();
        javaFile.writeTo(new File(completePath));
    }
}
