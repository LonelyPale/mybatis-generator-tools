package my.tools.mybatis.generator.plugins;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

public class LombokPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * 基础字段模型类
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        generatorDefaultLombok(topLevelClass); // 注解
        generatorDefaultSerialVersionUID(topLevelClass); // 序列化
        return true;
    }

    /**
     * 大字段模型类
     */
    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        generatorDefaultLombok(topLevelClass);
        generatorDefaultSerialVersionUID(topLevelClass);
        return true;
    }

    /**
     * 不生成getter
     */
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    /**
     * 不生成setter
     */
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    /**
     * 注解
     * @param topLevelClass 要操作的类
     */
    private void generatorDefaultLombok(TopLevelClass topLevelClass) {
        // 添加domain的import
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addImportedType("lombok.NoArgsConstructor");
        topLevelClass.addImportedType("lombok.AllArgsConstructor");
        topLevelClass.addImportedType("lombok.EqualsAndHashCode");

        // 添加domain的注解
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@AllArgsConstructor");
        topLevelClass.addAnnotation("@EqualsAndHashCode(callSuper = false)");

        // 添加domain的注释
        //topLevelClass.addJavaDocLine("/**");
        //topLevelClass.addJavaDocLine("* Created by Mybatis Generator on " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //topLevelClass.addJavaDocLine("*/");
    }

    /**
     * 序列化
     * @param topLevelClass 要操作的类
     */
    private void generatorDefaultSerialVersionUID(TopLevelClass topLevelClass) {
        FullyQualifiedJavaType serializable = new FullyQualifiedJavaType("java.io.Serializable");
        topLevelClass.addSuperInterface(serializable);
        topLevelClass.addImportedType("java.io.Serializable");
        FullyQualifiedJavaType longType = new FullyQualifiedJavaType("long");

        Field field = new Field("serialVersionUID", longType);
        field.setFinal(true);
        field.setInitializationString("1L");
        field.setName("serialVersionUID");
        field.setStatic(true);
        field.setType(longType);
        field.setVisibility(JavaVisibility.PRIVATE);
        topLevelClass.getFields().add(0, field);
    }

}
