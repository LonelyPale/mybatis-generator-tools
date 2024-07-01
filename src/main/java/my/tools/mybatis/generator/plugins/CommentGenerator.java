package my.tools.mybatis.generator.plugins;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

/**
 * 自定义注释生成器
 */
public class CommentGenerator extends DefaultCommentGenerator {
    private boolean suppressDate;
    private boolean suppressAllComments;
    private boolean addRemarkComments = false;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 设置用户配置的参数
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
        suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));
        suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
        addRemarkComments = StringUtility.isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));
    }

    /**
     * 给文件添加注释
     */
    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        if (suppressAllComments || suppressDate) {
            return;
        }

        compilationUnit.addFileCommentLine("/**");
        compilationUnit.addFileCommentLine("* Created by Mybatis Generator on " + dateFormat.format(new Date()));
        compilationUnit.addFileCommentLine("*/");
        compilationUnit.addFileCommentLine("");
    }

    /**
     * 给模型类添加注释
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (suppressAllComments || !addRemarkComments) {
            return;
        }

        String tableName = introspectedTable.getFullyQualifiedTable().toString();
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + tableName);

        String remarks = introspectedTable.getRemarks();
        if (remarks != null && !remarks.isEmpty()) {
            topLevelClass.addJavaDocLine(" * " + remarks);
        }

        topLevelClass.addJavaDocLine(" */");
    }

    /**
     * 给字段添加注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }

        String columnName = introspectedColumn.getActualColumnName();
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + columnName);

        String remarks = introspectedColumn.getRemarks();
        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
            String[] remarkLines = remarks.split(System.lineSeparator());
            for (String remarkLine : remarkLines) {
                field.addJavaDocLine(" * " + remarkLine);
            }
        }

        field.addJavaDocLine(" */");
    }

    /**
     * Example.java
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
    }

    /**
     * Example.java 子类
     */
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
    }

    /**
     * Example.java 子类
     */
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
    }

    /**
     * Mapper.java
     */
    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
    }

    /**
     * Mapper.xml
     */
    @Override
    public void addComment(XmlElement xmlElement) {
    }
}
