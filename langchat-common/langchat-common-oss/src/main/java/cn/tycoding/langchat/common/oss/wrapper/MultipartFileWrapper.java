/*
 * Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
 *
 * Licensed under the GNU Affero General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.tycoding.langchat.common.oss.wrapper;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dromara.x.file.storage.core.exception.FileStorageRuntimeException;
import org.dromara.x.file.storage.core.file.FileWrapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * MultipartFile 文件包装类
 */
@Getter
@Setter
@NoArgsConstructor
public class MultipartFileWrapper implements FileWrapper {
    private MultipartFile file;
    private String name;
    private String contentType;
    private InputStream inputStream;
    private Long size;

    public MultipartFileWrapper(MultipartFile file, String name, String contentType, Long size) {
        this.file = file;
        this.name = name;
        this.contentType = contentType;
        this.size = size;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if (inputStream == null) {
            inputStream = new BufferedInputStream(file.getInputStream());
        }
        return inputStream;
    }

    @Override
    public void transferTo(File dest) {
        // 在某些 SpringBoot 版本中，例如 2.4.6，此方法会调用失败，
        // 此时尝试手动将 InputStream 写入指定文件，
        // 根据文档来看 MultipartFile 最终都会由框架从临时目录中删除
        try {
            file.transferTo(dest);
            IoUtil.close(inputStream);
        } catch (Exception ignored) {
            try {
                FileUtil.writeFromStream(getInputStream(), dest);
            } catch (Exception e) {
                throw new FileStorageRuntimeException("文件移动失败", e);
            }
        }
    }

    @Override
    public boolean supportTransfer() {
        return true;
    }
}
