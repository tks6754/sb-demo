package com.miao.spring.demo.config.generator;


import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.List;
import java.util.Properties;


public class BasePlugin extends PluginAdapter {
    public boolean validate(List<String> warnings) {
        return true;
    }


    /** sql 标签的 id 属性 */
    private String id = "base_column_list";

    @Override
    public void setContext(Context context) {
        super.setContext(context);
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);

        String id = this.properties.getProperty("id");
        if (StringUtility.stringHasValue(id)) {
            this.id = id;
        }

    }

    /**
     * 生成 Mapper 文件中的 Element
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        generateSqlBaseColumns(document, introspectedTable);
        return true;
    }

    /**
     * 生成包含全部列的 sql 元素
     */
    private void generateSqlBaseColumns(Document document, IntrospectedTable introspectedTable) {
        // 新建 sql 元素标签
        XmlElement sqlElement = new XmlElement("sql");
        // 新建 sql 元素属性
        Attribute attr = new Attribute("id", id);
        sqlElement.addAttribute(attr);
        // 获取全部列名称
        StringBuilder columnsBuilder = new StringBuilder();
        List<IntrospectedColumn> columnList = introspectedTable.getAllColumns();
        for (IntrospectedColumn column : columnList) {
            columnsBuilder.append(MyBatis3FormattingUtilities.getSelectListPhrase(column)).append(", ");
        }
        // 删除最后一个逗号
        String columns = columnsBuilder.substring(0, columnsBuilder.length() - 2);
        // 新建 sql 元素内容，填写列名称
        sqlElement.addElement(new TextElement(columns));
        // 将 sql 元素放到根元素下
        XmlElement rootElement = document.getRootElement();
        rootElement.addElement(new TextElement(""));
        rootElement.addElement(sqlElement);
        rootElement.addElement(new TextElement(""));
    }



    /**
     * 实体类字段
     * @param field
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        // 生成注释
        fieldAnnotation(field, introspectedColumn.getRemarks());
        // 生成注释结束

//        // 追加日期格式化注解
//        if (introspectedColumn.getJdbcTypeName() == "TIMESTAMP") {
//            topLevelClass.addImportedType(new FullyQualifiedJavaType(Annotation.JsonFormat.getClazz()));
//        }

        // tinyint数据（Byte）转换成（Integer）类型
        String a = field.getType().getShortName();
        if (a.equals("Byte")) {
            field.setType(new FullyQualifiedJavaType("java.lang.Integer"));
        }
        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }


    /**
     * set方法 false 不生成
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    /**
     * get方法 false 不生成
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }


    /**
     * insert 方法不生成
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze,
                                               IntrospectedTable introspectedTable) {
        return false;
    }
    public boolean clientInsertMethodGenerated(Method method,
                                               TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }
    public boolean sqlMapInsertElementGenerated(XmlElement element,
                                                IntrospectedTable introspectedTable) {
        return false;
    }

    /**
     * DeleteByPrimaryKey 不生成
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method,
                                                           Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method,
                                                           TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }
    public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element,
                                                            IntrospectedTable introspectedTable) {
        return false;
    }


    /**
     * UpdateByPrimaryKey 不生成
     * @param method
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    public boolean providerUpdateByPrimaryKeySelectiveMethodGenerated(
            Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        return false;
    }
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method,
                                                                    Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method,
                                                                    TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }


    /**
     * 不生成 selectByPrimaryKey
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }
    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }


    /**
     * 不生成 selectAll 方法
     * @param method
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    public boolean clientSelectAllMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return false;
    }
    public boolean clientSelectAllMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return false;
    }
    public boolean sqlMapSelectAllElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return false;
    }


    /**
     * 属性注释生成
     * @param field
     * @param explain
     */
    private static void fieldAnnotation(Field field, String explain) {
        // 生成注释
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(explain);
        field.addJavaDocLine(sb.toString());
        field.addJavaDocLine(" */");
        // 生成注释结束
    }


    /*enum LombokEnum {
        DATA("@Data", "lombok.Data"),;
        private final String annotation;
        private final String importedType;

        LombokEnum(String annotation, String importedType) {
            this.annotation = annotation;
            this.importedType = importedType;
        }
    }*/

}
