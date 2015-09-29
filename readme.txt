部署jtxweb注意事项
1、如果web.xml没有改动，打包时删掉web.xml，如果有改动，先下载修改该文件再上传
2、如果application.properties没有改动，打包时删掉，如有改动，先下载修改该文件再上传，确保lucene.path与lucene.mode正确
3、定时器配置没改动，打包时删掉spring-quartz.xml，或者把定时器开关打开