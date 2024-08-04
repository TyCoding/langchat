FROM node:lts-alpine AS build

# 安装 pnpm
RUN npm install pnpm -g

# 设置工作目录
WORKDIR /app

# 复制 package.json 和 pnpm-lock.yaml 文件
COPY ./package.json ./pnpm-lock.yaml /app/

# 安装依赖
RUN pnpm install
RUN pnpm install chalk
RUN pnpm install fs-extra

# 复制所有源代码
COPY . /app

# 运行构建
RUN pnpm run build

# 使用 Nginx 官方镜像
FROM nginx:alpine

# 复制构建产物到 Nginx 的默认静态文件目录
COPY --from=build /app/dist /usr/share/nginx/html

# 复制自定义的 Nginx 配置文件
COPY ./docker/nginx.conf /etc/nginx/nginx.conf

# 暴露端口
EXPOSE 80

# 启动 Nginx
CMD ["nginx", "-g", "daemon off;"]
