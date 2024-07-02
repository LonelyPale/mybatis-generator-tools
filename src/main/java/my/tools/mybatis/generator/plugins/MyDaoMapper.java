package my.tools.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.List;

public class MyDaoMapper extends PluginAdapter {
    private static final FullyQualifiedJavaType pkClass = new FullyQualifiedJavaType("java.lang.Long");
    private static FullyQualifiedJavaType exampleClass;
    private static FullyQualifiedJavaType modelClass;
    private static String baseDaoPackage;

    @Override
    public boolean validate(List<String> warnings) {
        // 获取并验证自定义属性
        baseDaoPackage = properties.getProperty("baseDaoPackage");
        System.out.printf("MyDaoMapper: baseDaoPackage=%s%n", baseDaoPackage);
        return StringUtility.stringHasValue(baseDaoPackage);
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        System.out.println("MyDaoMapper: 1.1-generator javaModelGenerator: modelExampleClassGenerated");
        exampleClass = topLevelClass.getType();
        return true;
    }

    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        System.out.println("MyDaoMapper: 1.2-generator javaModelGenerator: modelBaseRecordClassGenerated");
        modelClass = topLevelClass.getType();
        return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        System.out.println("MyDaoMapper: 1.3-generator javaModelGenerator: modelRecordWithBLOBsClassGenerated");
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        System.out.println("MyDaoMapper: 2.0-generator javaClientGenerator: clientGenerated");

        FullyQualifiedJavaType baseDao = new FullyQualifiedJavaType(baseDaoPackage);
        baseDao.addTypeArgument(modelClass);
        baseDao.addTypeArgument(pkClass);
        baseDao.addTypeArgument(exampleClass);

        interfaze.addImportedType(modelClass);
        interfaze.addImportedType(exampleClass);
        interfaze.addImportedType(baseDao);
        interfaze.addSuperInterface(baseDao);
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        System.out.println("MyDaoMapper: 3.0-generator sqlMapGenerator: sqlMapDocumentGenerated");
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    @Override
    public boolean clientCountByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientDeleteByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }
}
