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

package cn.tycoding.langchat.upms.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.upms.entity.SysDept;
import cn.tycoding.langchat.upms.mapper.SysDeptMapper;
import cn.tycoding.langchat.upms.service.SysDeptService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 部门表(Dept)表服务实现类
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Service
@RequiredArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Override
    public List<SysDept> list(SysDept sysDept) {
        return baseMapper.selectList(new LambdaQueryWrapper<SysDept>()
                .orderByAsc(SysDept::getOrderNo));
    }

    @Override
    public List<Tree<Object>> tree(SysDept sysDept) {
        List<SysDept> sysDeptList = baseMapper.selectList(new LambdaQueryWrapper<SysDept>()
                .ne(sysDept.getId() != null, SysDept::getId, sysDept.getId()));

        // 构建树形结构
        List<TreeNode<Object>> nodeList = CollUtil.newArrayList();
        sysDeptList.forEach(t -> {
            TreeNode<Object> node = new TreeNode<>(
                    t.getId(),
                    t.getParentId(),
                    t.getName(),
                    0
            );
            node.setExtra(Dict.create().set("orderNo", t.getOrderNo()).set("des", t.getDes()));
            nodeList.add(node);
        });
        return TreeUtil.build(nodeList, "0");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        List<SysDept> list = baseMapper.selectList(new LambdaQueryWrapper<SysDept>().eq(SysDept::getParentId, id));
        if (!list.isEmpty()) {
            throw new ServiceException("该部门包含子节点，不能删除");
        }
        baseMapper.deleteById(id);
    }
}
