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

import html2canvas from 'html2canvas';
import jspdf from 'jspdf';

/**
 * 根据文件url获取文件名
 * @param url 文件url
 */
function getFileName(url: string) {
  const num = url.lastIndexOf('/') + 1;
  let fileName = url.substring(num);
  //把参数和文件名分割开
  fileName = decodeURI(fileName.split('?')[0]);
  return fileName;
}

/**
 * 根据文件地址下载文件
 * @param {*} sUrl
 */
export function downloadByUrl({
  url,
  target = '_blank',
  fileName,
}: {
  url: string;
  target?: '_self' | '_blank';
  fileName?: string;
}): Promise<boolean> {
  // 是否同源
  const isSameHost = new URL(url).host == location.host;
  return new Promise<boolean>((resolve, reject) => {
    if (isSameHost) {
      const link = document.createElement('a');
      link.href = url;
      link.target = target;

      if (link.download !== undefined) {
        link.download = fileName || getFileName(url);
      }

      if (document.createEvent) {
        const e = document.createEvent('MouseEvents');
        e.initEvent('click', true, true);
        link.dispatchEvent(e);
        return resolve(true);
      }

      if (url.indexOf('?') === -1) {
        url += '?download';
      }

      window.open(url, target);
      return resolve(true);
    } else {
      const canvas = document.createElement('canvas');
      const img = document.createElement('img');
      img.setAttribute('crossOrigin', 'Anonymous');
      img.src = url;
      img.onload = () => {
        canvas.width = img.width;
        canvas.height = img.height;
        const context = canvas.getContext('2d')!;
        context.drawImage(img, 0, 0, img.width, img.height);
        // window.navigator.msSaveBlob(canvas.msToBlob(),'image.jpg');
        // saveAs(imageDataUrl, '附件');
        canvas.toBlob((blob) => {
          const link = document.createElement('a');
          // @ts-ignore
          link.href = window.URL.createObjectURL(blob);
          link.download = getFileName(url);
          link.click();
          URL.revokeObjectURL(link.href);
          resolve(true);
        }, 'image/jpeg');
      };
      img.onerror = (e) => reject(e);
    }
  });
}

export function downloadBlob(blob: any, filename: string) {
  const url = URL.createObjectURL(blob);
  const link = document.createElement('a');
  link.href = url;
  link.download = filename;
  document.body.append(link);
  link.click();
  link.remove();
  URL.revokeObjectURL(url);
}

export async function downloadPng(eleId: string, filename: string) {
  const ele = document.getElementById(eleId);
  const canvas = await html2canvas(ele as HTMLDivElement, {
    useCORS: true,
  });
  const imgUrl = canvas.toDataURL('image/png');
  if (imgUrl.length < 8) {
    return;
  }
  const tempLink = document.createElement('a');
  tempLink.style.display = 'none';
  tempLink.href = imgUrl;
  tempLink.setAttribute('download', filename + '.png');
  if (typeof tempLink.download === 'undefined') tempLink.setAttribute('target', '_blank');
  document.body.appendChild(tempLink);
  tempLink.click();
}

export function downloadSvg(eleId: string, filename: string) {
  const mermaidDiv = document.getElementById(eleId);
  const svg = mermaidDiv!.querySelector('svg') as any;
  if (svg == null) {
    return;
  }
  const data = new XMLSerializer().serializeToString(svg);
  const blob = new Blob([data], { type: 'image/svg+xml' });
  const url = URL.createObjectURL(blob);
  const link = document.createElement('a');
  link.href = url;
  link.download = filename + '.svg';
  document.body.append(link);
  link.click();
  link.remove();
  URL.revokeObjectURL(url);
}

export async function downloadPdf(eleId: string, filename: string) {
  const ele = document.getElementById(eleId);
  const canvas = await html2canvas(ele as HTMLDivElement, {
    useCORS: true,
  });
  const imgUrl = canvas.toDataURL('image/png');
  if (imgUrl.length < 8) {
    return;
  }
  const pdf = new jspdf();
  pdf.addImage({
    imageData: imgUrl,
    x: 10,
    y: 10,
    width: 100,
    height: 100,
    format: 'PNG',
  });
  pdf.save(filename + '.pdf');
}
