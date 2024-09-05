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

package cn.tycoding.langchat.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.tycoding.langchat.biz.dto.DocSliceNumDTO;
import cn.tycoding.langchat.biz.entity.AigcDocs;
import cn.tycoding.langchat.biz.entity.AigcDocsSlice;
import cn.tycoding.langchat.biz.entity.AigcKnowledge;
import cn.tycoding.langchat.biz.mapper.AigcDocsMapper;
import cn.tycoding.langchat.biz.mapper.AigcDocsSliceMapper;
import cn.tycoding.langchat.biz.mapper.AigcKnowledgeMapper;
import cn.tycoding.langchat.biz.service.AigcKnowledgeService;
import cn.tycoding.langchat.biz.vo.FileAnalysisMonitorVO;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.common.utils.QueryPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AigcKnowledgeServiceImpl extends ServiceImpl<AigcKnowledgeMapper, AigcKnowledge> implements AigcKnowledgeService {

    private final AigcDocsMapper aigcDocsMapper;
    private final AigcDocsSliceMapper aigcDocsSliceMapper;

    @Override
    @Transactional
    public void addDocs(AigcDocs data) {
        data.setCreateTime(new Date());
        aigcDocsMapper.insert(data);
    }

    @Override
    @Transactional
    public void updateDocs(AigcDocs data) {
        aigcDocsMapper.updateById(data);
    }

    @Override
    @Transactional
    public void addDocsSlice(AigcDocsSlice data) {
        data.setCreateTime(new Date())
                .setWordNum(data.getContent().length())
                .setStatus(true)
        ;
        aigcDocsSliceMapper.insert(data);
    }

    @Override
    @Transactional
    public void updateDocsSlice(AigcDocsSlice data) {
        aigcDocsSliceMapper.updateById(data);
    }

    @Override
    public List<String> listSliceVectorIdsOfDoc(String docsId) {
        LambdaQueryWrapper<AigcDocsSlice> selectWrapper = Wrappers.<AigcDocsSlice>lambdaQuery()
                .select(AigcDocsSlice::getVectorId)
                .eq(AigcDocsSlice::getDocsId, docsId);
        List<String> vectorIds = aigcDocsSliceMapper.selectList(selectWrapper)
                .stream()
                .map(AigcDocsSlice::getVectorId)
                .toList();
        log.debug("slices of doc: [{}], count: [{}]", docsId, vectorIds.size());
        return vectorIds;
    }

    @Override
    @Transactional
    public void removeSlicesOfDoc(String docsId) {
        LambdaQueryWrapper<AigcDocsSlice> deleteWrapper = Wrappers.<AigcDocsSlice>lambdaQuery()
                .eq(AigcDocsSlice::getDocsId, docsId);
        int count = aigcDocsSliceMapper.delete(deleteWrapper);
        log.debug("remove all slices of doc: [{}], count: [{}]", docsId, count);
    }

    @Override
    public IPage<FileAnalysisMonitorVO> fileAnalysisMonitor(QueryPage queryPage, String userId) {
        IPage<FileAnalysisMonitorVO> result = new Page<>();
        List<FileAnalysisMonitorVO> resultList = new ArrayList<>();
        IPage<AigcDocs> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        IPage<AigcDocs> iPage = aigcDocsMapper.selectPage(page,
                new LambdaQueryWrapper<AigcDocs>()
                        .select(AigcDocs::getName, AigcDocs::getId, AigcDocs::getSliceNum, AigcDocs::getSliceStatus)
                        .eq(AigcDocs::getUserId, userId)
        );
        List<AigcDocs> records = iPage.getRecords();
        if (CollectionUtil.isEmpty(records)) {
            throw new ServiceException("未上传文档，无数据");
        }
        List<String> docIds = records.stream().map(AigcDocs::getId).toList();
        List<DocSliceNumDTO> docSliceNumByDocId = aigcDocsSliceMapper.getDocSliceNumByDocId(docIds);
        if (CollectionUtil.isEmpty(docSliceNumByDocId)) {
            return null;
        }
        Map<String, Integer> coutMap = docSliceNumByDocId.stream().collect(Collectors.toMap(DocSliceNumDTO::getDocsId, DocSliceNumDTO::getCount));
        for (AigcDocs aigcDocs : records) {
            String docId = aigcDocs.getId();
            FileAnalysisMonitorVO fileAnalysisMonitorVO = new FileAnalysisMonitorVO();
            if (!aigcDocs.getSliceStatus()) {
                fileAnalysisMonitorVO.setStatus(0);
                fileAnalysisMonitorVO.setSpeed(0.0);
            } else {
                Integer sliceNum = coutMap.get(docId);
                if (sliceNum.compareTo(aigcDocs.getSliceNum()) == 0) {
                    fileAnalysisMonitorVO.setStatus(1);
                } else {
                    fileAnalysisMonitorVO.setStatus(0);
                }
                fileAnalysisMonitorVO.setSpeed((double) sliceNum / aigcDocs.getSliceNum() * 100);
            }
            fileAnalysisMonitorVO.setName(aigcDocs.getName());
            fileAnalysisMonitorVO.setDocsId(docId);
            resultList.add(fileAnalysisMonitorVO);
        }
        result.setRecords(resultList);
        result.setTotal(iPage.getTotal());
        result.setCurrent(iPage.getCurrent());
        result.setSize(iPage.getSize());
        result.setPages(iPage.getPages());
        return result;
    }
}

