# build.sh

#!/bin/bash
docker buildx build -t registry.cn-beijing.aliyuncs.com/langchat/langchat --platform=linux/arm64,linux/amd64 ../ --push
