package com.dreamy.generator.plugins;


import com.dreamy.generator.shard.table.PartitionedTable;
import com.dreamy.generator.shard.table.SegmentTable;
import com.dreamy.utils.DataSource;
import com.dreamy.utils.StringUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import com.dreamy.utils.DataSource.ShardType;

import java.util.regex.Pattern;

/**
 * @author lrh
 * @create 2014年12月22日上午9:36:55
 */
public class ShardTablePlugin extends BasePlugin {

	private static final DataSource DATA_SOURCE = new DataSource();
	private static final String DATA_SOURCE_PRE = "DataSource.";
	private static final String FULL_DATE_TYPE = "java.util.Date";
	private static final String SHARD_TABLE_SUFIX_PATTERN = "_(\\d{4}_\\d{2}|\\d{1,3})";
	private String tableName = null;

	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		FullyQualifiedJavaType dateType = new FullyQualifiedJavaType(FULL_DATE_TYPE);
		topLevelClass.addImportedType(dateType);
		// 添加page字段
		Field shardDate = new Field();
		shardDate.setVisibility(JavaVisibility.PRIVATE);
		shardDate.setType(dateType);
		shardDate.setName("shardDate");
		commentGenerator.addFieldComment(shardDate, introspectedTable);
		topLevelClass.addField(shardDate);

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("setShardDate");
		method.addParameter(new Parameter(dateType, "shardDate"));
		method.addBodyLine("this.shardDate = shardDate;");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);

		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("getShardDate");
		method.setReturnType(dateType);
		method.addBodyLine("return this.shardDate;");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		return Boolean.TRUE;
	}

	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		String shardType = introspectedTable.getTableConfigurationProperty("shardType");
		String shardBy = introspectedTable.getTableConfigurationProperty("shardBy");
		String tableNum = introspectedTable.getTableConfigurationProperty("tableNum");
		String shardKey = introspectedTable.getTableConfigurationProperty("shardKey");
		String partitionedTable = introspectedTable.getTableConfigurationProperty("partitionedTable");
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotEmpty(shardType) || StringUtils.isNotEmpty(shardBy) || StringUtils.isNotEmpty(tableNum)) {
			FullyQualifiedJavaType segmentType = new FullyQualifiedJavaType(SegmentTable.class.getCanonicalName());
			interfaze.addImportedType(segmentType);
			interfaze.addImportedType(new FullyQualifiedJavaType(ShardType.class.getCanonicalName()));
			sb.setLength(0);
			sb.append("@SegmentTable(");
			sb.append("table=\"").append(tableName).append("\"");
			if (StringUtils.isNotEmpty(shardType)) {
				sb.append(",");
				sb.append("shardType=").append("ShardType.").append(shardType.toUpperCase());
			}
			if (StringUtils.isNotEmpty(shardBy)) {
				sb.append(",");
				sb.append("shardBy=\"").append(shardBy).append("\"");
			}
			if (StringUtils.isNotEmpty(tableNum)) {
				sb.append(",");
				sb.append("tableNum=").append(tableNum);
			}
			if (StringUtils.isNotEmpty(shardKey)) {
				sb.append(",");
				sb.append("shardKey=\"").append(shardKey).append("\"");
			}
			sb.append(")");
			interfaze.addAnnotation(sb.toString());
		}
		if (StringUtils.isNotEmpty(partitionedTable)) {
			FullyQualifiedJavaType segmentType = new FullyQualifiedJavaType(PartitionedTable.class.getCanonicalName());
			interfaze.addImportedType(segmentType);
			interfaze.addImportedType(new FullyQualifiedJavaType(DataSource.class.getCanonicalName()));
			sb.setLength(0);
			sb.append("@PartitionedTable(");
			sb.append(transferStrToDataSource(partitionedTable)).append(")");
			interfaze.addAnnotation(sb.toString());
		}
		return Boolean.TRUE;
	}

	private String transferStrToDataSource(String val) {
		for (java.lang.reflect.Field field : DATA_SOURCE.getClass().getFields()) {
			field.setAccessible(true);
			try {
				if (val.equals(field.get(DATA_SOURCE))) {
					return DATA_SOURCE_PRE + field.getName();
				}
			} catch (Exception e) {
			}
		}
		return "";
	}

	public void initialized(IntrospectedTable introspectedTable) {
		tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();
		String newTableName = Pattern.compile(SHARD_TABLE_SUFIX_PATTERN).matcher(tableName).replaceAll("").trim();
		introspectedTable.setSqlMapAliasedFullyQualifiedRuntimeTableName(newTableName);
		introspectedTable.setSqlMapFullyQualifiedRuntimeTableName(newTableName);
		tableName = newTableName;
	}
}
