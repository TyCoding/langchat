# 文档

## PgVector

本项目采用Pgvector作为向量库，主要原因是因为和MySQL类似的脚本语法，并且可以通过支持PGSQL的软件（例如Navicat连接）可视化预览向量数据。

虽然本项目支持的向量库很多，但是能私有化部署并且提供可视化操作页面的很少。

### 部署

这里只考虑Docker部署方式，可以参考 [./pgvector/docker-compose.yml](./pgvector/docker-compose.yml) 目录的compose文件。

但是由于docker hub 禁止访问，垃圾镜像会比较麻烦，这里作者自己打包了一个pgvector镜像，或者也可以使用代理方式拉取：

```yaml
    image: registry.cn-beijing.aliyuncs.com/langchat/pgvector
#    image: docker.m.daocloud.io/pgvector/pgvector:0.7.2-pg16
```