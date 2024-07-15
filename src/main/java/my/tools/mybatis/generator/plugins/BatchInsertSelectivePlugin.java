package my.tools.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.*;

import java.util.List;

//TODO: 有BUG和缺陷，待修复和优化
//可以在xml的<table>标签下加入自定义的属性<property name="exampleProperty" value="exampleValue"/>，用于配置自定义的插件。
public class BatchInsertSelectivePlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        Method method = new Method("batchInsertSelective");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        FullyQualifiedJavaType parameterType = FullyQualifiedJavaType.getNewListInstance();
        parameterType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        method.addParameter(new Parameter(parameterType, "list"));
        interfaze.addMethod(method);
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement rootElement = document.getRootElement();
        XmlElement insertElement = new XmlElement("insert");
        insertElement.addAttribute(new Attribute("id", "batchInsertSelective"));
        insertElement.addAttribute(new Attribute("parameterType", "java.util.List"));

        insertElement.addElement(new TextElement("insert into " + introspectedTable.getFullyQualifiedTableNameAtRuntime()));

        XmlElement trimElementColumns = new XmlElement("trim");
        trimElementColumns.addAttribute(new Attribute("prefix", "("));
        trimElementColumns.addAttribute(new Attribute("suffix", ")"));
        trimElementColumns.addAttribute(new Attribute("suffixOverrides", ","));

        XmlElement trimElementValues = new XmlElement("trim");
        trimElementValues.addAttribute(new Attribute("prefix", "values ("));
        trimElementValues.addAttribute(new Attribute("suffix", ")"));
        trimElementValues.addAttribute(new Attribute("suffixOverrides", ","));

        for (IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
            XmlElement ifElementColumn = new XmlElement("if");
            ifElementColumn.addAttribute(new Attribute("test", "list[0]." + introspectedColumn.getJavaProperty() + " != null"));
            ifElementColumn.addElement(new TextElement(introspectedColumn.getActualColumnName() + ","));
            trimElementColumns.addElement(ifElementColumn);

            XmlElement ifElementValue = new XmlElement("if");
            ifElementValue.addAttribute(new Attribute("test", "item." + introspectedColumn.getJavaProperty() + " != null"));
            ifElementValue.addElement(new TextElement("#{item." + introspectedColumn.getJavaProperty() + ",jdbcType=" + introspectedColumn.getJdbcTypeName() + "},"));
            trimElementValues.addElement(ifElementValue);
        }

        insertElement.addElement(trimElementColumns);
        insertElement.addElement(new TextElement("<foreach collection=\"list\" item=\"item\" separator=\",\">"));
        insertElement.addElement(trimElementValues);
        insertElement.addElement(new TextElement("</foreach>"));

        rootElement.addElement(insertElement);
        return true;
    }
}
