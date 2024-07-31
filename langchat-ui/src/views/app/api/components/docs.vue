<!--
  - Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
  -
  - Licensed under the GNU Affero General Public License, Version 3 (the "License");
  - you may not use this file except in compliance with the License.
  - You may obtain a copy of the License at
  -
  -     https://www.gnu.org/licenses/agpl-3.0.html
  -
  - Unless required by applicable law or agreed to in writing, software
  - distributed under the License is distributed on an "AS IS" BASIS,
  - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  - See the License for the specific language governing permissions and
  - limitations under the License.
  -->

<script lang="ts" setup>
  import hljs from 'highlight.js';
  import javascript from 'highlight.js/lib/languages/javascript';

  hljs.registerLanguage('javascript', javascript);

  const url = `http://langchat.cn`;
  const request = `
POST /v1/chat/completions HTTP/1.1
Content-Type: application/json
Authorization: 'Bearer YOUR_ACCESS_TOKEN'
Body:
{
    "messages": [
        { "role": "user", "content": "你好" }
    ]
}
  `;

  const response = `
data: {"choices": [{"index": 0, "delta": {"content": "你好！"}, "finish_reason": null}], "session_id": null}

data: {"choices": [{"index": 0, "delta": {"content": "我能"}, "finish_reason": null}], "session_id": null}

data: {"choices": [{"index": 0, "delta": {"content": "为你"}, "finish_reason": null}], "session_id": null}

data: {"choices": [{"index": 0, "delta": {"content": "做些什么？"}, "finish_reason": null}], "session_id": null}

data: {"choices": [{"index": 0, "delta": {}, "finish_reason": "stop", "usage": {"prompt_tokens": 9, "completion_tokens": 6, "total_tokens": 15}}], "session_id": null}
  `;

  const demo = `
const url = 'http://langchat.cn/v1/chat/completions';
const data = {
    "messages": [
        { "role": "user", "content": "你好" }
    ]
};

fetch(url, {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer YOUR_ACCESS_TOKEN'
  },
  body: JSON.stringify(data)
})
.then(response => {
  if (!response.ok) {
    throw new Error('Network response was not ok ' + response.statusText);
  }
  return response.json();
})
.then(jsonData => {
  console.log('Success:', jsonData);
})
.catch(error => {
  console.error('Error:', error);
});
  `;
</script>

<template>
  <div class="p-4 bg-white h-full overflow-auto rounded">
    <n-config-provider :hljs="hljs" class="flex flex-col gap-4">
      <div>
        <n-alert title="API URL" type="info" />
        <div class="bg-[#18181c] mt-2 py-2 px-4 overflow-x-auto rounded">
          <n-code :code="url" class="text-white" language="JavaScript" />
        </div>
      </div>

      <div>
        <n-alert title="Request" type="info" />
        <div class="bg-[#18181c] mt-2 py-2 px-4 overflow-x-auto rounded">
          <n-code :code="request" class="text-white" language="JavaScript" />
        </div>
      </div>

      <div>
        <n-alert title="Response（Stream）" type="info" />
        <div class="bg-[#18181c] py-2 mt-2 px-4 overflow-x-auto rounded">
          <n-code :code="response" class="text-white" language="JavaScript" />
        </div>
      </div>

      <div>
        <n-alert title="API请求示例" type="info" />
        <div class="bg-[#18181c] mt-2 py-2 px-4 overflow-x-auto rounded">
          <n-code :code="demo" class="text-white" language="javascript" />
        </div>
      </div>
    </n-config-provider>
  </div>
</template>

<style lang="less" scoped></style>
