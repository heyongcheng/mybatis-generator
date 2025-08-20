package cn.he.tools.mybatis;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.kotlin.KotlinFile;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Set;

public class CleanCommentGenerator extends DefaultCommentGenerator {

    private final Properties properties = new Properties();
    private boolean suppressDate = false;
    private boolean suppressAllComments = false;
    private boolean addRemarkComments = false;
    private SimpleDateFormat dateFormat;
    private FullyQualifiedJavaType generatedImport = new FullyQualifiedJavaType("jakarta.annotation.Generated");

    public void addConfigurationProperties(Properties props) {
        this.properties.putAll(props);
        this.suppressDate = StringUtility.isTrue(this.properties.getProperty("suppressDate"));
        this.suppressAllComments = StringUtility.isTrue(this.properties.getProperty("suppressAllComments"));
        this.addRemarkComments = StringUtility.isTrue(this.properties.getProperty("addRemarkComments"));
        if (StringUtility.isTrue(this.properties.getProperty("useLegacyGeneratedAnnotation"))) {
            this.generatedImport = new FullyQualifiedJavaType("javax.annotation.Generated");
        }

        String dateFormatString = this.properties.getProperty("dateFormat");
        if (StringUtility.stringHasValue(dateFormatString)) {
            this.dateFormat = new SimpleDateFormat(dateFormatString);
        }
        super.addConfigurationProperties(props);
    }

    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if (!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            innerClass.addJavaDocLine("/**");
            sb.append(introspectedTable.getFullyQualifiedTable());
            innerClass.addJavaDocLine(sb.toString());
            this.addJavadocTag(innerClass, false);
            innerClass.addJavaDocLine(" */");
        }
    }

    protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
    }

    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        if (!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            innerClass.addJavaDocLine("/**");
            sb.append(introspectedTable.getFullyQualifiedTable());
            innerClass.addJavaDocLine(sb.toString());
            this.addJavadocTag(innerClass, markAsDoNotDelete);
            innerClass.addJavaDocLine(" */");
        }
    }

    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (!this.suppressAllComments && this.addRemarkComments) {
            topLevelClass.addJavaDocLine("/**");
            String remarks = introspectedTable.getRemarks();
            if (this.addRemarkComments && StringUtility.stringHasValue(remarks)) {
                String[] remarkLines = remarks.split(System.getProperty("line.separator"));
                String[] var5 = remarkLines;
                int var6 = remarkLines.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    String remarkLine = var5[var7];
                    topLevelClass.addJavaDocLine(" *   " + remarkLine);
                }
            }
            String s = " * database table: " + introspectedTable.getFullyQualifiedTable();
            topLevelClass.addJavaDocLine(s);
            topLevelClass.addJavaDocLine(" */");
        }
    }

    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        if (!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            innerEnum.addJavaDocLine("/**");
            sb.append(introspectedTable.getFullyQualifiedTable());
            innerEnum.addJavaDocLine(sb.toString());
            this.addJavadocTag(innerEnum, false);
            innerEnum.addJavaDocLine(" */");
        }
    }

    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (!this.suppressAllComments) {
            field.addJavaDocLine("/**");
            String remarks = introspectedColumn.getRemarks();
            if (this.addRemarkComments && StringUtility.stringHasValue(remarks)) {
                String[] remarkLines = remarks.split(System.getProperty("line.separator"));
                String[] var6 = remarkLines;
                int var7 = remarkLines.length;

                for(int var8 = 0; var8 < var7; ++var8) {
                    String remarkLine = var6[var8];
                    field.addJavaDocLine(" *   " + remarkLine);
                }
            }

            //field.addJavaDocLine(" *");
            this.addJavadocTag(field, false);
            field.addJavaDocLine(" */");
        }
    }

    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        if (!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            field.addJavaDocLine("/**");
            sb.append(introspectedTable.getFullyQualifiedTable());
            field.addJavaDocLine(sb.toString());
            this.addJavadocTag(field, false);
            field.addJavaDocLine(" */");
        }
    }

    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        if (!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            method.addJavaDocLine("/**");
            sb.append(introspectedTable.getFullyQualifiedTable());
            method.addJavaDocLine(sb.toString());
            this.addJavadocTag(method, false);
            method.addJavaDocLine(" */");
        }
    }

    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

    }

    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {

    }

    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

    }

    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {

    }
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

    }

    private String getGeneratedAnnotation(String comment) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("@Generated(");
        if (this.suppressAllComments) {
            buffer.append('"');
        } else {
            buffer.append("value=\"");
        }

        buffer.append(MyBatisGenerator.class.getName());
        buffer.append('"');
        if (!this.suppressDate && !this.suppressAllComments) {
            buffer.append(", date=\"");
            buffer.append(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now()));
            buffer.append('"');
        }

        if (!this.suppressAllComments) {
            buffer.append(", comments=\"");
            buffer.append(comment);
            buffer.append('"');
        }

        buffer.append(')');
        return buffer.toString();
    }

    public void addFileComment(KotlinFile kotlinFile) {
    }
}