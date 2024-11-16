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

import lombok.Getter;
import lombok.Setter;
import org.dromara.x.file.storage.core.file.FileWrapper;
import org.dromara.x.file.storage.core.file.FileWrapperAdapter;
import org.springframework.web.multipart.MultipartFile;

/**
 * MultipartFile 文件包装适配器
 */
@Getter
@Setter
public class MultipartFileWrapperAdapter implements FileWrapperAdapter {

    @Override
    public boolean isSupport(Object source) {
        return source instanceof MultipartFile || source instanceof MultipartFileWrapper;
    }

    @Override
    public FileWrapper getFileWrapper(Object source, String name, String contentType, Long size) {
        if (source instanceof MultipartFileWrapper) {
            return updateFileWrapper((MultipartFileWrapper) source, name, contentType, size);
        } else {
            MultipartFile file = (MultipartFile) source;
            if (name == null) name = file.getOriginalFilename();
            if (contentType == null) contentType = file.getContentType();
            if (size == null) size = file.getSize();
            return new MultipartFileWrapper(file, name, contentType, size);
        }
    }
}
