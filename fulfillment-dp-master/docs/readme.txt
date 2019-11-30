呆萝卜的骨架
名称:com.mallcai.fulfillment.dp:maven-mallcai-archetype

发布新版本骨架, 请使用如下顺序:

1.在项目根目录/mallcai-archetype 下
执行命令: mvn archetype:create-from-project

2.修改发布仓库
修改当前文件下pom.xml文件, 在<project> </project> 标签最后添加如下内容
 <distributionManagement>
		<repository>
			<id>releases</id>
			<name>Nexus Release Repository</name>
			<url>http://maven.caicaivip.com/content/repositories/releases/</url>
		</repository>
		<snapshotRepository>
			<id>snapshots</id>
			<name>Nexus Snapshot Repository</name>
			<url>http://maven.caicaivip.com/content/repositories/snapshots/</url>
		</snapshotRepository>
	</distributionManagement>

3.发布骨架项目至仓库
执行命令: mvn deploy

4.如发布成功, 正常情况下, 已经可以使用该骨架作为创建项目的模板了.
